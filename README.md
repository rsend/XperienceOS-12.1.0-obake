# XPerienceOS 12.1.0 for Motorola Droid Mini

This is the integrated Android 8.1.0 source for the Motorola Droid Mini
XT1030 (`obake`). It includes the device, kernel, framework, HAL, recovery,
and vendor files from the working port, including local modifications.

Project sources are ordinary files in this repository. No `repo sync`,
roomservice resolution, or submodule update is needed. The dependency installer
downloads Ubuntu packages and the pinned historical toolchain inputs, using
the same setup model as the xpe711 release.

## Build on Ubuntu 20.04 amd64

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
