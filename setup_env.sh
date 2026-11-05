#!/usr/bin/env bash
# Source this from a fresh Bash terminal before building the three images.
if [[ "${BASH_SOURCE[0]}" == "$0" ]]; then
    echo 'Source this file: source ./setup_env.sh' >&2
    exit 2
fi

android8_release_top=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd -P) || return 1
if [[ "$android8_release_top" == *[[:space:]]* ]]; then
    echo 'The legacy Android build requires a checkout path without spaces.' >&2
    return 1
fi
cd -- "$android8_release_top" || return 1

android8_javac=$(dpkg -L openjdk-8-jdk-headless 2>/dev/null | sed -n '/\/bin\/javac$/p' | head -n 1)
if [[ -z "$android8_javac" || ! -x "$android8_javac" ]]; then
    echo 'OpenJDK 8 is missing; run bash ./install_deps_android8.sh.' >&2
    return 1
fi
if ! /usr/bin/python -c 'import sys; assert sys.version_info[0] == 2' 2>/dev/null; then
    echo 'Python 2 is required at /usr/bin/python; run bash ./install_deps_android8.sh.' >&2
    return 1
fi
for android8_tool in \
    prebuilts/build-tools/linux-x86/bin/ninja \
    prebuilts/clang/host/linux-x86/clang-4053586/bin/clang \
    prebuilts/clang/host/linux-x86/clang-3289846/bin/clang \
    prebuilts/gcc/linux-x86/arm/arm-linux-androideabi-4.9/bin/arm-linux-androideabi-gcc \
    prebuilts/misc/linux-x86/bison/bison \
    prebuilts/misc/linux-x86/flex/flex-2.5.39 \
    prebuilts/go/linux-x86/bin/go \
    prebuilts/sdk/tools/jack-admin; do
    if [[ ! -x "$android8_release_top/$android8_tool" ]]; then
        echo "Missing $android8_tool; run bash ./install_deps_android8.sh." >&2
        return 1
    fi
done
if [[ ! -r "$android8_release_top/prebuilts/sdk/tools/jack-tls-legacy.security" ]]; then
    echo 'The local Jack TLS policy is missing; rerun dependency setup.' >&2
    return 1
fi

# Bind all build state to this checkout, independently of its directory name.
# Exit this terminal before using another Android checkout.
unset OUT_DIR_COMMON_BASE TMPDIR ANDROID_BUILD_TOP ANDROID_PRODUCT_OUT OUT
unset SDCLANG SDCLANG_PATH SDCLANG_PATH_2 SDCLANG_LTO_DEFS
unset TARGET_PRODUCT TARGET_BUILD_VARIANT TARGET_BUILD_APPS BUILD_VAR_CACHE_READY
unset TARGET_NO_RECOVERY RECOVERY_VARIANT OBAKE_IMS_BOOT_CAPTURE
unset ANDROID_BUILD_PATHS ANDROID_PRE_BUILD_PATHS PYTHONPATH PYTHONHOME ANDROID_PYTHONPATH
export TOP="$android8_release_top"
export JAVA_HOME="${android8_javac%/bin/javac}"
export PATH="$JAVA_HOME/bin:$android8_release_top/prebuilts/gcc/linux-x86/arm/arm-linux-androideabi-4.9/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
export XPE_BUILD=obake
source "$android8_release_top/build/envsetup.sh" || return 1
export TARGET_PRODUCT=xpe_obake TARGET_BUILD_VARIANT=user TARGET_BUILD_TYPE=release
export WITH_TWRP=false TARGET_NO_RECOVERY=false
export LIBCORE_SKIP_TESTS=true
export OBAKE_SELINUX_PERMISSIVE=false SELINUX_IGNORE_NEVERALLOWS=false
export BUILD_NUMBER="${ANDROID8_BUILD_NUMBER:-android8-local}"
export USE_CCACHE=1 CCACHE_EXEC=/usr/bin/ccache CCACHE_COMPRESS=1
export OUT_DIR="$android8_release_top/out"
export CCACHE_DIR="$OUT_DIR/.ccache"
export JACK_HOME="$OUT_DIR/.jack-server"
export JACK_CLIENT_SETTING="$OUT_DIR/.jack-settings"
export TMPDIR="$OUT_DIR/.tmp"
mkdir -p "$TMPDIR" || return 1
export ANDROID_JACK_VM_ARGS='-Dfile.encoding=UTF-8 -XX:+TieredCompilation -Xmx6G'
export LANG=C LC_ALL=C
echo "Configured $TARGET_PRODUCT ($TARGET_BUILD_VARIANT); outputs: $OUT_DIR/target/product/obake"
unset android8_release_top android8_javac android8_tool
