#!/usr/bin/env bash
# Bootstrap the Android 8 build dependencies on Ubuntu 20.04 amd64.
set -euo pipefail
android8_setup_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd -P)

usage() {
    cat <<'EOF'
Usage: bash install_deps_android8.sh [--prebuilts-only | --verify] [tool options]

Default: install Ubuntu host packages, then restore and verify pinned prebuilts.
Run as a regular user on Ubuntu 20.04 amd64; sudo is used for apt only.
No Android source checkout, build, Jack startup, or global shell edit is done.

--prebuilts-only  Skip apt when host packages are already installed.
--verify         Check the installed prebuilt files without downloading.
Tool options: --destination DIR; --local-cache REPO_PROJECTS_DIR (offline audit).
EOF
}
android8_mode=install
case "${1:-}" in
    -h|--help) usage; exit 0 ;;
    --prebuilts-only) android8_mode=prebuilts; shift ;;
    --verify) android8_mode=verify; shift ;;
    --*) echo 'Select --prebuilts-only before tool options.' >&2; exit 2 ;;
esac

if [[ ! -r /etc/os-release ]]; then
    echo 'Cannot identify the host OS.' >&2; exit 1
fi
source /etc/os-release
if [[ "${ID:-}" != ubuntu || "${VERSION_ID:-}" != 20.04 || "$(dpkg --print-architecture)" != amd64 ]]; then
    echo 'Supported host: Ubuntu 20.04 amd64.' >&2; exit 1
fi

if [[ "$android8_mode" == install ]]; then
    if (( EUID == 0 )); then
        echo 'Run as your regular build user; sudo is used for apt only.' >&2; exit 1
    fi
    command -v sudo >/dev/null || { echo 'Install sudo first.' >&2; exit 1; }
    sudo apt-get update
    # Minimal Ubuntu installations may not have the universe component enabled.
    sudo apt-get install -y --no-install-recommends software-properties-common ca-certificates
    sudo add-apt-repository -y universe
    sudo apt-get update
    mapfile -t android8_packages < "$android8_setup_root/dependency-pins/host-packages.txt"
    sudo apt-get install -y --no-install-recommends "${android8_packages[@]}"
fi

command -v git >/dev/null || { echo 'Git is required for dependency setup.' >&2; exit 1; }
if [[ "$android8_mode" == verify ]]; then
    exec bash "$android8_setup_root/install_prebuilts.sh" --verify "$@"
fi
bash "$android8_setup_root/install_prebuilts.sh" "$@"
echo 'Android 8 dependencies are ready. No Android source or images were built.'
