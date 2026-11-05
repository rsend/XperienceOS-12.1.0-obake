# Unused credential cleanup

This pass removes credential-bearing material unused by the Droid Mini boot,
system, and recovery targets. It does not attempt to prune all optional source
projects or intentionally public test-key fixtures.

Removed: the ChromeOS toolchain service-account JSON, three ChromeOS enrollment
login files, sixteen unselected vendor API-key binaries, and the complete Quark
and Shamu Widevine firmware bundles. Vendor copy rules for removed blobs were
removed as well. These other devices are outside this release target.

The ChromeOS build-log utility now requires CHROMEOS_TOOLCHAIN_CREDENTIALS to
point to an external credentials file. ChromeOS OAuth/test API credentials are
empty and must be supplied locally to use those optional tools.

Generated documentation has no bundled search/Blogger API credentials. Search
is disabled unless DOCS_SEARCH_CX and DOCS_SEARCH_API_KEY are set on window;
Blogger links navigate directly unless DOCS_BLOGGER_API_KEY is configured.
The OkHttp URL-shortener sample requires an explicit
-Dokhttp.recipes.googleApiKey Java property.
CTS dynamic configuration is disabled by default; users may supply
--dynamic-config-url explicitly.

The subsequent runtime DRM cleanup removes the installed Obake Widevine engine,
its vendor copy rule, and its extraction-list entry. Both Obake engine copies
are now absent. The Quark/Shamu firmware carrying the shared RSA private key
was already removed in the unused-material pass. This follows the xpe711
engine-removal model; other DRM libraries and Android's generic DRM framework
remain. Widevine protected playback may fail after this change.

Dialer API integrations remain unchanged and still need review. Public Android
development signing keys and upstream cryptographic test fixtures remain.

Validation of the initial unused-material pass compared changed paths against
the saved command list for all three images: none appeared. The subsequent
Widevine engine removal changes a system-image input, so system.img must be
rebuilt and protected-playback behavior must be tested on the device. Boot and
recovery sources are unaffected. There was no out directory at cleanup time;
when applying this change to an existing build output, remove the old staged
system/vendor/lib/mediadrm/libwvdrmengine.so to avoid packaging a stale copy.
Modified Python files passed Python 2 syntax checks. No full image rebuild was
run for this cleanup; the user runs image builds manually.

Changed source files (including deletions):

- `vendor/motorola/addison/proprietary/vendor/lib/libwvhidl.so` (delete)
- `vendor/motorola/addison/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/addison/proprietary/vendor/lib64/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/athene/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/cedric/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/clark/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/griffin/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/msm8226-common/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/msm8610-common/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/msm8916-common/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/msm8960dt-common/proprietary/vendor/lib/libwvdrmengine.so` (delete)
- `vendor/motorola/omap4-common/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/qcom-common/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/quark/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/shamu/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/victara/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/quark/proprietary/vendor/firmware/widevine.b00` (delete)
- `vendor/motorola/quark/proprietary/vendor/firmware/widevine.b03` (delete)
- `vendor/motorola/quark/proprietary/vendor/firmware/widevine.mdt` (delete)
- `vendor/motorola/quark/proprietary/vendor/firmware/widevine.b02` (delete)
- `vendor/motorola/quark/proprietary/vendor/firmware/widevine.b01` (delete)
- `vendor/motorola/shamu/proprietary/vendor/firmware/widevine.b00` (delete)
- `vendor/motorola/shamu/proprietary/vendor/firmware/widevine.b03` (delete)
- `vendor/motorola/shamu/proprietary/vendor/firmware/widevine.mdt` (delete)
- `vendor/motorola/shamu/proprietary/vendor/firmware/widevine.b02` (delete)
- `vendor/motorola/shamu/proprietary/vendor/firmware/widevine.b01` (delete)
- `vendor/motorola/msm8916-common/msm8916-common-vendor.mk` (edit)
- `vendor/motorola/qcom-common/qcom-common-vendor-blobs.mk` (edit)
- `vendor/motorola/msm8610-common/msm8610-common-vendor-blobs.mk` (edit)
- `vendor/motorola/victara/victara-vendor-blobs.mk` (edit)
- `vendor/motorola/victara/victara-vendor.mk` (edit)
- `vendor/motorola/griffin/griffin-vendor.mk` (edit)
- `vendor/motorola/cedric/cedric-vendor.mk` (edit)
- `vendor/motorola/athene/athene-vendor.mk` (edit)
- `vendor/motorola/msm8226-common/msm8226-common-vendor.mk` (edit)
- `vendor/motorola/addison/addison-vendor.mk` (edit)
- `vendor/motorola/clark/clark-vendor.mk` (edit)
- `vendor/motorola/quark/quark-vendor.mk` (edit)
- `vendor/motorola/shamu/shamu-vendor.mk` (edit)
- `vendor/motorola/omap4-common/omap4-common-vendor-blobs.mk` (edit)
- `external/toolchain-utils/cros_utils/chromeos-toolchain-credentials.json` (delete)
- `external/autotest/client/site_tests/enterprise_KioskEnrollment/credentials.riseplayer` (delete)
- `external/autotest/client/site_tests/enterprise_KioskEnrollment/credentials.chromesign` (delete)
- `external/autotest/client/site_tests/enterprise_RemoraRequisition/credentials.txt` (delete)
- `external/toolchain-utils/cros_utils/buildbot_utils.py` (edit)
- `build/make/tools/droiddoc/templates-sac/assets/js/docs.js` (edit)
- `external/doclava/res/assets/templates-sdk/assets/js/docs.js` (edit)
- `external/autotest/client/cros/constants.py` (edit)
- `external/autotest/client/common_lib/cros/fake_device_server/client_lib/client_lib_test.py` (edit)
- `cts/common/host-side/tradefed/src/com/android/compatibility/common/tradefed/build/CompatibilityBuildProvider.java` (edit)
- `external/okhttp/samples/guide/src/main/java/com/squareup/okhttp/recipes/RequestBodyCompression.java` (edit)

Additional runtime DRM cleanup:

- `vendor/motorola/msm8960dt-common/proprietary/vendor/lib/mediadrm/libwvdrmengine.so` (delete)
- `vendor/motorola/msm8960dt-common/msm8960dt-common-vendor-blobs.mk` (edit)
- `device/motorola/msm8960dt-common/proprietary-files.txt` (edit)

## Subsequent source-scope reduction

The source snapshot was restored and the credential/Widevine removals verified
again. Unused projects and payloads are now excluded as described in
BUILD_SCOPE.md. This also excludes the AOSP-disabled Dialer location source
containing map keys. The voicemail transcription API default is still present
in compiled Dialer code and remains a separate review item. Earlier per-file
cleanup lists describe the pre-reduction pass, not the complete final scope.
