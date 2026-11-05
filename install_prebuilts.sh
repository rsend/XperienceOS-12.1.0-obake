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
  'build-tools|https://android.googlesource.com/platform/prebuilts/build-tools|88fa20009d5581163b4656d52e2fcc1f56042da6'
  'checkstyle|https://android.googlesource.com/platform/prebuilts/checkstyle|a8a50e922b39a2dba7e56030c660e1aabca168f3'
  'clang/host/linux-x86|https://android.googlesource.com/platform/prebuilts/clang/host/linux-x86|ce80566d209ef0fba56e5bd4cc4cf11435f83a71'
  'devtools|https://android.googlesource.com/platform/prebuilts/devtools|2538644348d06278149f8f918930d058cb4f32a7'
  'gcc/linux-x86/arm/arm-linux-androideabi-4.9|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/arm/arm-linux-androideabi-4.9|43bf3bf040b873cb1334cdc0687a03d8d6f4b1c8'
  'gcc/linux-x86/host/x86_64-linux-glibc2.15-4.8|https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/host/x86_64-linux-glibc2.15-4.8|12eccb2e29b05b39fa83e5299b1f4486b9e743a4'
  'go/linux-x86|https://android.googlesource.com/platform/prebuilts/go/linux-x86|9802e351bf16f8137807da945a4bc36371a16be6'
  'jdk/jdk8|https://android.googlesource.com/platform/prebuilts/jdk/jdk8|68dabb15582c2e86c9a5ef863e193892b7843792'
  'libs/libedit|https://android.googlesource.com/platform/prebuilts/libs/libedit|d32685dba4011664b590b94ad156bc734c2c9bb5'
  'maven_repo/android|https://android.googlesource.com/platform/prebuilts/maven_repo/android|c7fc1cdb67ff477e4ef1f7dee12568f223ea18c7'
  'maven_repo/bumptech|https://android.googlesource.com/platform/prebuilts/maven_repo/bumptech|268c5495b8003e09173a1ae7b1e9dc9b019e300d'
  'misc|https://android.googlesource.com/platform/prebuilts/misc|598387341a7104f2bd46fca3e2c1161bce609de3'
  'ndk|https://android.googlesource.com/platform/prebuilts/ndk|f39cb3e537ad90d4985fc372b339d466799d964e'
  'python/linux-x86/2.7.5|https://android.googlesource.com/platform/prebuilts/python/linux-x86/2.7.5|49b50adb1a149100905b368570c2cb56cadc0854'
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
