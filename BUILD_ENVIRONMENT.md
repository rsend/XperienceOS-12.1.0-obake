# Android 8 image build environment

The supported host is Ubuntu 20.04 amd64 with OpenJDK 8 and Python 2.7.
`install_deps_android8.sh` installs host packages and restores the exact
prebuilt inventory through `install_prebuilts.sh`, immutable Git commits,
`prebuilt-filelists/`, and `prebuilt-checksums/`.

The toolchain set retains the Clang variants, ARM/host GCC, Go bootstrap,
SDK/NDK inputs, Bison 2.7, Flex 2.5.39, and supporting files used by the
successful development build. The two local Jack tool repairs are applied
from `prebuilt-overrides/` and checked against their final hashes. The
installer does not fetch ROM source projects or start a server.

## Setup and build

```sh
bash ./install_deps_android8.sh
source ./setup_env.sh
./jack.sh 6
m -j4 bootimage systemimage recoveryimage
```

`setup_env.sh` derives the checkout path from its own location, changes to
that root, selects Java 8 and `xpe_obake-user`, and binds all output, cache,
and Jack paths to the checkout. It removes inherited Android/Python path
settings rather than relying on another tree's build environment. Source it
in a fresh terminal; leave that terminal before working in another tree.

The default build number is `android8-local`, so an exported source copy builds
without Git metadata. To label an image with a source revision, set
`ANDROID8_BUILD_NUMBER` before sourcing setup. XPerience's build label also
contains the build date. This setup reproduces build inputs and configuration;
it does not promise byte-identical images across different build dates.

The build requires no submodules. The original checkout's 11 unpopulated
gitlinks belong to optional tests/research or another Motorola device; their
identities are retained in `source-provenance/unpopulated-gitlinks.tsv`.
Nested `.git` metadata and `.gitmodules` files are excluded. The unselected
`tools/external/gradle` distributions are also omitted; the recorded image
dependency graph has no references to that project. Production source and
its local changes otherwise retain their original relative layout.

## Validation boundaries

The dependency installer has reconstructed and verified all 44,133 inventory
entries from 18 pinned projects. The retained revisions were previously checked
against public upstream commits; a real-download installer regression also
passed. The trimmed inventories have been verified locally. Full fresh-VM
Ubuntu package bootstrap remains a separate check.

The trimmed source passes build-file generation and dry runs for boot, system,
and recovery. Compile-command and target-path comparisons, kernel dependency
validation, and the remaining manual build gate are documented in
[source-provenance/BUILD_SCOPE.md](source-provenance/BUILD_SCOPE.md).
