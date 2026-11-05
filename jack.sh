#!/usr/bin/env bash
set -euo pipefail
android8_top=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd -P)
if [[ $# -ne 1 || ! "$1" =~ ^[1-9][0-9]*$ ]]; then
    echo 'Usage: ./jack.sh HEAP_GIB (for example: ./jack.sh 6)' >&2
    exit 2
fi
if [[ ! -x "${JAVA_HOME:-}/bin/java" ]]; then
    echo 'Source ./setup_env.sh first to select OpenJDK 8.' >&2
    exit 1
fi
export PATH="$JAVA_HOME/bin:$PATH"
android8_admin="$android8_top/prebuilts/sdk/tools/jack-admin"
android8_state="$android8_top/out/.jack-server"
export JACK_SERVER_VM_ARGUMENTS="-Dfile.encoding=UTF-8 -XX:+TieredCompilation -Xmx${1}g"
if [[ ! -d "$android8_state" ]]; then
    "$android8_admin" install-server \
        "$android8_top/prebuilts/sdk/tools/jack-launcher.jar" \
        "$android8_top/prebuilts/sdk/tools/jack-server-4.11.ALPHA.jar"
fi
if [[ ! -r "$android8_state/launcher.jar" || ! -r "$android8_state/server-1.jar" ]]; then
    echo "Incomplete Jack installation in $android8_state; inspect it before retrying." >&2
    exit 1
fi
android8_status=0
"$android8_admin" kill-server || android8_status=$?
if [[ "$android8_status" != 0 && "$android8_status" != 2 ]]; then
    exit "$android8_status"
fi
"$android8_admin" start-server
"$android8_admin" update jack \
    "$android8_top/prebuilts/sdk/tools/jacks/jack-4.32.CANDIDATE.jar" 4.32.CANDIDATE
