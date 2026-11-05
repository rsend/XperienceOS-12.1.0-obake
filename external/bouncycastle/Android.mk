#
# Copyright (C) 2010 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
LOCAL_PATH := $(call my-dir)

# Only the provider and signing-tool libraries needed by the three images.
# OCSP testing classes and unbundled/hostdex test variants are excluded.
all_bcprov_src_files := $(call all-java-files-under,bcprov/src/main/java)
android_bcprov_src_files := $(filter-out bcprov/src/main/java/org/bouncycastle/crypto/digests/AndroidDigestFactoryBouncyCastle.java,$(all_bcprov_src_files))
ri_bcprov_src_files := $(filter-out bcprov/src/main/java/org/bouncycastle/crypto/digests/AndroidDigestFactoryOpenSSL.java bcprov/src/main/java/org/bouncycastle/crypto/digests/OpenSSLDigest.java,$(all_bcprov_src_files))
all_bcpkix_src_files := $(call all-java-files-under,bcpkix/src/main/java)

include $(CLEAR_VARS)
    LOCAL_MODULE := bouncycastle-nojarjar
    LOCAL_MODULE_TAGS := optional
    LOCAL_SRC_FILES := $(android_bcprov_src_files)
    LOCAL_JAVA_LIBRARIES := core-oj core-libart conscrypt
    LOCAL_NO_STANDARD_LIBRARIES := true
    LOCAL_JAVA_LANGUAGE_VERSION := 1.7
    include $(BUILD_STATIC_JAVA_LIBRARY)

include $(CLEAR_VARS)
    LOCAL_MODULE := bouncycastle
    LOCAL_MODULE_TAGS := optional
    LOCAL_STATIC_JAVA_LIBRARIES := bouncycastle-nojarjar
    LOCAL_JAVA_LIBRARIES := core-oj core-libart conscrypt
    LOCAL_NO_STANDARD_LIBRARIES := true
    LOCAL_JARJAR_RULES := $(LOCAL_PATH)/jarjar-rules.txt
    LOCAL_JAVA_LANGUAGE_VERSION := 1.7
    include $(BUILD_JAVA_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := bouncycastle-host
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := $(ri_bcprov_src_files)
LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_HOST_JAVA_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := bouncycastle-bcpkix-host
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := $(all_bcpkix_src_files)
LOCAL_JAVA_LIBRARIES := bouncycastle-host
LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_HOST_JAVA_LIBRARY)
