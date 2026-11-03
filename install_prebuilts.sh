#!/usr/bin/env bash
set -euo pipefail

# Same pinned-project/file-inventory layout as the xpe711 source release.
release_top=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)
android8_destination="$release_top/prebuilts"
android8_cache_root=
android8_verify=false
while (( $# )); do
    case "$1" in
        --verify) android8_verify=true; shift ;;
        --destination) android8_destination=$(realpath -m -- "$2"); shift 2 ;;
        --local-cache) android8_cache_root=$(realpath -e -- "$2"); shift 2 ;;
        *) echo "Unknown option: $1" >&2; exit 2 ;;
    esac
done

projects=(
    # BEGIN GENERATED PINS
  'abi-dumps/ndk|https://android.googlesource.com/platform/prebuilts/abi-dumps/ndk|9b4c58b905c1c92c1b8630d9da7c96b9a92e5ddc'
  'abi-dumps/vndk|https://android.googlesource.com/platform/prebuilts/abi-dumps/vndk|a0ad82df9e4ceacfc60f7ebbf9779d3d92eeb773'
  'android-emulator|https://android.googlesource.com/platform/prebuilts/android-emulator|400cd54dca1c329fd7f1c8cb9e40b1fbbf3f3af3'
  'build-tools|https://android.googlesource.com/platform/prebuilts/build-tools|88fa20009d5581163b4656d52e2fcc1f56042da6'
  'checkstyle|https://android.googlesource.com/platform/prebuilts/checkstyle|a8a50e922b39a2dba7e56030c660e1aabca168f3'
  'clang/host/darwin-x86|https://android.googlesource.com/platform/prebuilts/clang/host/darwin-x86|f59dae743731eef0cf012408603291c253eed5c3'
  'clang/host/linux-x86|https://android.googlesource.com/platform/prebuilts/clang/host/linux-x86|ce80566d209ef0fba56e5bd4cc4cf11435f83a71'
  'deqp|https://android.googlesource.com/platform/prebuilts/deqp|fcff955e99f3d02e7fe0b0a65818b5cc690594b7'
  'devtools|https://android.googlesource.com/platform/prebuilts/devtools|2538644348d06278149f8f918930d058cb4f32a7'
  'gcc/darwin-x86/aarch64/aarch64-linux-android-4.9|https://android.googlesource.com/platform/prebuilts/gcc/darwin-x86/aarch64/aarch64-linux-android-4.9|91a0cf84baffc9808b93554451e149b12f2ad6d2'
  'gcc/darwin-x86/arm/arm-eabi-4.8|https://android.googlesource.com/platform/prebuilts/gcc/darwin-x86/arm/arm-eabi-4.8|6d08ca9f45ff685648fd13c75bf5cac4b11c19bb'
  'gcc/darwin-x86/arm/arm-linux-androideabi-4.9|https://android.googlesource.com/platform/prebuilts/gcc/darwin-x86/arm/arm-linux-androideabi-4.9|20aadb937757fdff11c09010765f63c20ebb5042'
  'gcc/darwin-x86/host/i686-apple-darwin-4.2.1|https://android.googlesource.com/platform/prebuilts/gcc/darwin-x86/host/i686-apple-darwin-4.2.1|ec5aa66aaa4964c27564d0ec84dc1f18a2d72b7e'
  'gcc/darwin-x86/x86/x86_64-linux-android-4.9|https://android.googlesource.com/platform/prebuilts/gcc/darwin-x86/x86/x86_64-linux-android-4.9|4e41eb8af41e0ed06d8237a4de8c75e2c3698f79'
  'gcc/linux-x86/aarch64/aarch64-linux-android-4.9|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/aarch64/aarch64-linux-android-4.9|c9c4fb77a024d2610f83e54e536c99a964ea85ab'
  'gcc/linux-x86/arm/arm-eabi-4.8|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/arm/arm-eabi-4.8|26e93f6af47f7bd3a9beb5c102a5f45e19bfa38a'
  'gcc/linux-x86/arm/arm-linux-androideabi-4.9|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/arm/arm-linux-androideabi-4.9|43bf3bf040b873cb1334cdc0687a03d8d6f4b1c8'
  'gcc/linux-x86/host/x86_64-linux-glibc2.11-4.8|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/host/x86_64-linux-glibc2.11-4.8|a706ab3bc2ac5168166659d6423da4b8b8e8644b'
  'gcc/linux-x86/host/x86_64-linux-glibc2.15-4.8|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/host/x86_64-linux-glibc2.15-4.8|12eccb2e29b05b39fa83e5299b1f4486b9e743a4'
  'gcc/linux-x86/host/x86_64-w64-mingw32-4.8|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/host/x86_64-w64-mingw32-4.8|e76a9a506d7ad132f107eb2f7c27b6a8ccb68b91'
  'gcc/linux-x86/x86/x86_64-linux-android-4.9|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/x86/x86_64-linux-android-4.9|a57c546eec04da569ddd24dd72b0d57feb95845a'
  'gdb/darwin-x86|https://android.googlesource.com/platform/prebuilts/gdb/darwin-x86|0a8c90248264a8b26970b4473770bcc3df8515fe'
  'gdb/linux-x86|https://android.googlesource.com/platform/prebuilts/gdb/linux-x86|4adfde8bc82dd39f59e0445588c3e599ada477d2'
  'go/darwin-x86|https://android.googlesource.com/platform/prebuilts/go/darwin-x86|d240e4f742d2799a3b4bf1ba4d695d0e28dc9040'
  'go/linux-x86|https://android.googlesource.com/platform/prebuilts/go/linux-x86|9802e351bf16f8137807da945a4bc36371a16be6'
  'gradle-plugin|https://android.googlesource.com/platform/prebuilts/gradle-plugin|cd1b6806eae4eaa88d82ce61dbb7c2bcfea1f96a'
  'jdk/jdk8|https://android.googlesource.com/platform/prebuilts/jdk/jdk8|68dabb15582c2e86c9a5ef863e193892b7843792'
  'libs/libedit|https://android.googlesource.com/platform/prebuilts/libs/libedit|d32685dba4011664b590b94ad156bc734c2c9bb5'
  'maven_repo/android|https://android.googlesource.com/platform/prebuilts/maven_repo/android|c7fc1cdb67ff477e4ef1f7dee12568f223ea18c7'
  'maven_repo/bumptech|https://android.googlesource.com/platform/prebuilts/maven_repo/bumptech|268c5495b8003e09173a1ae7b1e9dc9b019e300d'
  'misc|https://android.googlesource.com/platform/prebuilts/misc|598387341a7104f2bd46fca3e2c1161bce609de3'
  'ndk|https://android.googlesource.com/platform/prebuilts/ndk|f39cb3e537ad90d4985fc372b339d466799d964e'
  'python/darwin-x86/2.7.5|https://android.googlesource.com/platform/prebuilts/python/darwin-x86/2.7.5|0c5958b1636c47ed7c284f859c8e805fd06a0e63'
  'python/linux-x86/2.7.5|https://android.googlesource.com/platform/prebuilts/python/linux-x86/2.7.5|49b50adb1a149100905b368570c2cb56cadc0854'
  'qemu-kernel|https://android.googlesource.com/platform/prebuilts/qemu-kernel|87e7c6027290300bfb92c082c4fa622e78b03359'
  'sdk|https://android.googlesource.com/platform/prebuilts/sdk|692a2a62d5862258c6a88d3b9bfcbc1dd0431ae8'
  'tools|https://android.googlesource.com/platform/prebuilts/tools|bd037f3696cc33196a876c34a7f5a0dcc6e089ee'
    # END GENERATED PINS
)

verify_project() (
    set -euo pipefail
    local directory=$1 inventory_key=$2
    cd "$directory"
    sha256sum --check --quiet "$release_top/prebuilt-checksums/$inventory_key.sha256"
    while IFS=$'\t' read -r name target; do
        [[ -z "$name" ]] && continue
        if [[ ! -L "$name" || "$(readlink -- "$name")" != "$target" ]]; then
            echo "Missing or changed prebuilt symlink: $directory/$name" >&2
            exit 1
        fi
    done < "$release_top/prebuilt-checksums/$inventory_key.links.tsv"
    while IFS= read -r name; do
        [[ -z "$name" ]] && continue
        [[ -x "$name" ]] || { echo "Missing executable permission: $directory/$name" >&2; exit 1; }
    done < "$release_top/prebuilt-checksums/$inventory_key.executables.txt"
)

for entry in "${projects[@]}"; do
    IFS='|' read -r path url revision <<< "$entry"
    destination="$android8_destination/$path"
    inventory_key=${path//\//_}
    filelist="$release_top/prebuilt-filelists/$inventory_key.txt"
    [[ "$revision" =~ ^[0-9a-f]{40}$ ]] || { echo "Invalid revision: $path" >&2; exit 1; }
    [[ -s "$filelist" ]] || { echo "Missing inventory: $filelist" >&2; exit 1; }
    inventory_hash=$(cat "$filelist" "$release_top/prebuilt-checksums/$inventory_key."{sha256,links.tsv,executables.txt} | sha256sum)
    inventory_hash=${inventory_hash%% *}
    if [[ "$android8_verify" == true ]]; then
        verify_project "$destination" "$inventory_key"
        continue
    fi
    if [[ -e "$destination" || -L "$destination" ]]; then
        if [[ ! -L "$destination" && -f "$destination/.xpe-installed-revision" &&
              -f "$destination/.xpe-installed-inventory" &&
              "$(<"$destination/.xpe-installed-revision")" == "$revision" &&
              "$(<"$destination/.xpe-installed-inventory")" == "$inventory_hash" ]]; then
            verify_project "$destination" "$inventory_key"
            echo "Verified installed $path at $revision"
            continue
        fi
        echo "Refusing to replace existing $destination; expected an empty destination or matching pinned installation." >&2
        exit 1
    fi
    mkdir -p "$android8_destination"
    work_dir=$(mktemp -d "$android8_destination/.prebuilt-install.XXXXXX")
    (
        set -euo pipefail
        trap 'rm -rf -- "$work_dir"' EXIT
        mkdir -p "$work_dir/repo" "$work_dir/upstream" "$work_dir/files"
        if [[ -n "$android8_cache_root" ]]; then
            source_gitdir="$android8_cache_root/prebuilts/$path.git"
            [[ -d "$source_gitdir" ]] || { echo "Missing cache: $source_gitdir" >&2; exit 1; }
        else
            git -C "$work_dir/repo" init -q
            fetched=false
            for attempt in 1 2 3; do
                if timeout 3600 git -C "$work_dir/repo" -c http.lowSpeedLimit=1024 -c http.lowSpeedTime=120 \
                    fetch -q --no-tags --depth=1 "$url" "$revision"; then
                    fetched=true
                    break
                fi
                sleep "$attempt"
            done
            [[ "$fetched" == true ]] || exit 1
            [[ "$(git -C "$work_dir/repo" rev-parse FETCH_HEAD)" == "$revision" ]]
            source_gitdir="$work_dir/repo/.git"
        fi
        # Archive the selected top-level paths, then copy only the inventory.
        # This avoids extracting unselected Clang/NDK variants from their packs.
        mapfile -t archive_paths < <(cut -d/ -f1 "$filelist" | LC_ALL=C sort -u)
        git --git-dir="$source_gitdir" archive "$revision" -- "${archive_paths[@]}" | tar -xf - -C "$work_dir/upstream"
        rsync -a --files-from="$filelist" "$work_dir/upstream/" "$work_dir/files/"
        if [[ -d "$release_top/prebuilt-overrides/$path" ]]; then
            rsync -a "$release_top/prebuilt-overrides/$path/" "$work_dir/files/"
        fi
        verify_project "$work_dir/files" "$inventory_key"
        printf '%s\n' "$revision" > "$work_dir/files/.xpe-installed-revision"
        printf '%s\n' "$inventory_hash" > "$work_dir/files/.xpe-installed-inventory"
        mkdir -p "$(dirname "$destination")"
        mv "$work_dir/files" "$destination"
    )
    echo "Installed $path at $revision"
done
echo 'Pinned Android 8 prebuilts verified.'
