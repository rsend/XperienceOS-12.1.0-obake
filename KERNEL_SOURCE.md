# Kernel source and provenance

The boot and recovery images use the Linux 3.4.113-based source included at
[`kernel/motorola/ghost/`](kernel/motorola/ghost/). Its starting repository is
the XPerience Motorola Ghost kernel project, checked out at
`434e4c04740a0ce8fb2d0a03d198c0a39ed23523` (`earlymount system`). Exact
upstream URLs and the original project revision are recorded in
`source-provenance/projects.tsv`.

The complete working kernel source is included as ordinary files, together
with the device defconfig and build integration. Local bring-up, Binder,
seccomp, power, audio/voice, and diagnostic changes are retained in the source;
their paths are listed in `source-provenance/local-changes.tsv`. The original
commit ID identifies the starting point, rather than every modification in
this integrated tree. The release Git commit identifies the final source used
for a release build.

The kernel incorporates work by Linux, Motorola, Qualcomm, XPerience, and
other contributors. Existing copyright and attribution notices, `COPYING`,
`CREDITS`, and `MAINTAINERS` are retained in the kernel tree. The kernel's
license text is [COPYING](kernel/motorola/ghost/COPYING). Source and build
scripts belong with the corresponding distributed boot/recovery binaries;
this provenance note does not replace the original notices or license text.
