# Obake image-build scope

This release targets `xpe_obake-user` on Ubuntu 20.04 amd64 and produces
`boot.img`, `system.img`, and `recovery.img`, with SELinux enforcing.

The working files occupy approximately 1.59 GiB, excluding Git objects,
installed prebuilts, and generated output. Filesystem allocation varies.
The tree includes 290 retained source-project origins. Project files are ordinary
files; no submodule initialization or upstream source synchronization is needed.

The release excludes unselected source projects, other-device payloads, SDK
publication/documentation inputs, CTS/VTS suites, optional developer tools,
unused resources, and kernel code outside the recorded Obake configuration.
Windows host-tool variants are disabled; libcore test-suite generation is
explicitly disabled by `setup_env.sh`. Some test-named libraries remain because
they are Android platform APIs used by production apps. Kernel Kconfig/Kbuild
support, exported headers, compiler bootstrap fixtures, and required generators
remain build inputs.

Selected IMS inputs originate in the Addison and Cedric vendor directories.
Those specific APK/JAR/JNI inputs are used by Obake; their directory names do
not mean those devices' complete source or vendor trees are included.

The installer restores 18 pinned prebuilt projects (44,133 inventory entries),
with file hashes, symlink targets, and executable modes checked. Unused Darwin,
GDB, emulator, and alternate compiler projects are no longer fetched. The two
local SDK Jack compatibility overrides remain in `prebuilt-overrides/`.

## Credential and DRM cleanup

The ChromeOS service-account private key, test logins, documentation/sample/test
API credentials, both Obake Widevine engines, and the Quark/Shamu key-bearing
Widevine firmware are absent. The installed engine's copy and extraction rules
are removed. Widevine-protected playback may fail. The unused Dialer location
implementation containing map API keys is also outside this source scope.

The bundled Google API key was removed from Dialer's voicemail transcription
configuration; the default API key is now empty. Transcription defaults
disabled. Deployments must supply their own credential to use that service.
No remote credential-validity checks were performed. Public Android development signing keys remain necessary build
inputs. The public Qualcomm SSG app signing certificate also remains because
SELinux mac_permissions generation reads it through `ssg/keys.conf`; no SSG
private signing key is included; an OpenSSH private-key-format marker in its parser is not a key.

## Validation and remaining gate

Build-file generation and a dry run of all three image targets pass. The
recorded target-installed paths match the prior build except for the removed
Widevine engine. All 20,606 common C/C++ object compile commands and 278 Jack
compile commands match the prior build. The retained runtime sources and
vendor binaries checked against snapshot `5700673` are byte-identical.

Kernel validation covers the unchanged generated configuration, preparation,
exported-header generation, dependency preprocessing for 2,233 kernel units,
five host generator units, and five ARM/XZ compression units. Disposable kernel
output was used for dependency queries; no image was compiled by the assistant.

The user completed a manual build of all three images after the trim repairs.
The build resumed incrementally between repairs; a further build from clean
output has not yet been checked. Output hashes are recorded in
`IMAGE_BUILD_VALIDATION.json`.
Device validation remains separate. The original development tree, initial-state
reconstruction, excluded material, and audit logs are retained privately outside
this published directory. They are not build dependencies and must not be added
to the release repository. Local Git recovery objects are likewise not project
working files. The successful manual build satisfied the gate for removing
obsolete local Git recovery objects.

The ARM/XZ kernel compression wrapper `scripts/xz_wrap.sh` is retained as
a required build input; the host dependency list explicitly includes `xz-utils`.
Its ARM compression/decompression round trip has been checked independently.

Assembler include dependencies are retained explicitly: libjpeg SIMD macro
includes and Opus `celt/arm/armopts_gnu.s`. Validation assembled all 50
recorded libjpeg Yasm commands and the recorded Opus ARM assembly command.
These inputs are read by the assemblers rather than ordinary C header scanning.

The Bionic header versioner requires Clang builtin headers through its
`versioner-dependencies/common/clang-builtins` symlink. These headers and
their includes are retained; full versioner preprocessing has been verified.

NDK header installation rules are retained because the platform native builds
consume the generated NDK sysroot through `ndk.timestamp`. Their headers
remain build dependencies even though the release does not publish an SDK.
Validation installed 1,357 header targets and compiled the affected ART,
BuiltInPrintService, and LatinIME source files successfully.

API stub generation consumes `libcore/known_oj_tags.txt` as a tag definition
input, alongside framework tag, manifest, overview, and API history files.
These are required build inputs even with HTML documentation disabled.
