# XPerienceOS 12.1.0 for Motorola Droid Mini

This a heavily AI-assisted port of Android 8.1.0 source for the Motorola Droid Mini
XT1030 (`obake`). 

IT IS INCOMPLETE; my goal is to port as far "up" as I can - which appears to be about up to Android 14. I just needed to make space on my development machine and am happy to publish this as-is - if only as a way for me to get it back in case I need it.

This particular repository produces working builds, but several features are missing. I spent more time fixing these in Android 10 (upcoming).

This is the last port (together with my other repository XPerienceOS-11.1.2-obake) based on XperienceOS; the next versions are all going to be based on LineageOS.

NOT WORKING: 
- VoLTE call volume
- WiFi calling / video calling over LTE - out of scope
- Hold / resume / call joining
- DTMF menu controls
- No automatic SIM handling; hard coded to T-mobile
- Roaming
- WiFi / BLE may be broken under enforcing SELinux, I stopped fixing this when I started working on Android 10.

The following is a Codex-generated (but slightly edited) how-to; mostly a summary of how to deploy this if anyone wants to try it.

It includes the device, kernel, framework, HAL, recovery,
and vendor files from the working port, including local modifications.

Project sources are ordinary files in this repository. No `repo sync`,
roomservice resolution, or submodule update is needed. The dependency installer
downloads Ubuntu packages and the pinned historical toolchain inputs, using
the same setup model as the xpe711 release.

## Ubuntu 20.04 x64 - this is the only tested build environment 

I have developed and tested this on a fresh install of 20.04 running in Virtualbox on a Windows guest. The following instructions set up the entire needed environment and produce the build outputs.

Clone or copy the complete contents of this directory into a path without
spaces. Open a fresh Bash terminal in that checkout and run as a regular user:

```sh
bash ./install_deps_android8.sh
source ./setup_env.sh
./jack.sh 6
m -j4 bootimage systemimage recoveryimage
```

The installer uses `sudo` for Ubuntu packages. The build itself runs as your
regular user. Jack is installed locally on its first use; an existing host
Jack installation is not required. The original tree completed these image
targets on a 12 GiB VM with a 6 GiB Jack heap at `-j4`; higher parallelism
previously exhausted RAM and swap.

The three outputs are:

```text
out/target/product/obake/boot.img
out/target/product/obake/system.img
out/target/product/obake/recovery.img
```

All project paths are resolved from this checkout. Build outputs, ccache,
temporary files, and Jack's certificates and state stay below its `out/`.
Use a fresh terminal for each Android tree. No personal shell configuration
or path from the original development machine is required.

## Release scope and validation

The proprietary Widevine engine was removed to exclude its embedded provisioning
credential, following the xpe711 release model. Widevine protected playback may
fail. See [credential cleanup](source-provenance/credential-cleanup.md).

The configuration is `xpe_obake-user` with SELinux enforcing and the bundled
Oreo recovery configuration. The build instructions produce all three image
targets together. Setup disables the development permissive override and
keeps SELinux neverallow checking enabled. They do not provision persistent carrier-specific modem/EFS
state. VoLTE on the development phone required separate provisioning; a fresh
Android installation alone does not reproduce that device state.

The trimmed release successfully built all three images after required build
inputs were restored. The manual build resumed incrementally through those
repairs. Device validation and a further build from clean output remain separate
checks. See `source-provenance/IMAGE_BUILD_VALIDATION.json` for output hashes.

This is an Obake image-build tree. Unselected devices, tests, SDK publishing,
and developer tooling are excluded. See [build scope and validation](source-provenance/BUILD_SCOPE.md).
The original development tree and excluded files are preserved outside this
published directory and are not required by these build commands.

See [BUILD_ENVIRONMENT.md](BUILD_ENVIRONMENT.md) for dependency and path details,
[KERNEL_SOURCE.md](KERNEL_SOURCE.md) for kernel provenance, and
`source-provenance/` for upstream revisions, local changes, and staging scope.
Keep existing copyright and license notices with their source files.
