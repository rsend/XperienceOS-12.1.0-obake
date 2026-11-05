# Copyright (C) 2009 The Android Open Source Project
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
#

LOCAL_PATH := $(call my-dir)

JAVA_LITE_SRC_FILES := \
    java/core/src/main/java/com/google/protobuf/AbstractMessageLite.java \
    java/core/src/main/java/com/google/protobuf/AbstractParser.java \
    java/core/src/main/java/com/google/protobuf/AbstractProtobufList.java \
    java/core/src/main/java/com/google/protobuf/ByteOutput.java \
    java/core/src/main/java/com/google/protobuf/ByteString.java \
    java/core/src/main/java/com/google/protobuf/BooleanArrayList.java \
    java/core/src/main/java/com/google/protobuf/CodedInputStream.java \
    java/core/src/main/java/com/google/protobuf/CodedOutputStream.java \
    java/core/src/main/java/com/google/protobuf/DoubleArrayList.java \
    java/core/src/main/java/com/google/protobuf/ExperimentalApi.java \
    java/core/src/main/java/com/google/protobuf/ExtensionLite.java \
    java/core/src/main/java/com/google/protobuf/ExtensionRegistryLite.java \
    java/core/src/main/java/com/google/protobuf/FieldSet.java \
    java/core/src/main/java/com/google/protobuf/FloatArrayList.java \
    java/core/src/main/java/com/google/protobuf/GeneratedMessageLite.java \
    java/core/src/main/java/com/google/protobuf/IntArrayList.java \
    java/core/src/main/java/com/google/protobuf/Internal.java \
    java/core/src/main/java/com/google/protobuf/InvalidProtocolBufferException.java \
    java/core/src/main/java/com/google/protobuf/LazyField.java \
    java/core/src/main/java/com/google/protobuf/LazyFieldLite.java \
    java/core/src/main/java/com/google/protobuf/LazyStringArrayList.java \
    java/core/src/main/java/com/google/protobuf/LazyStringList.java \
    java/core/src/main/java/com/google/protobuf/LongArrayList.java \
    java/core/src/main/java/com/google/protobuf/MapFieldLite.java \
    java/core/src/main/java/com/google/protobuf/MessageLite.java \
    java/core/src/main/java/com/google/protobuf/MessageLiteToString.java \
    java/core/src/main/java/com/google/protobuf/MessageLiteOrBuilder.java \
    java/core/src/main/java/com/google/protobuf/MutabilityOracle.java \
    java/core/src/main/java/com/google/protobuf/Parser.java \
    java/core/src/main/java/com/google/protobuf/ProtobufArrayList.java \
    java/core/src/main/java/com/google/protobuf/ProtocolStringList.java \
    java/core/src/main/java/com/google/protobuf/RopeByteString.java \
    java/core/src/main/java/com/google/protobuf/SmallSortedMap.java \
    java/core/src/main/java/com/google/protobuf/TextFormatEscaper.java \
    java/core/src/main/java/com/google/protobuf/UninitializedMessageException.java \
    java/core/src/main/java/com/google/protobuf/UnknownFieldSetLite.java \
    java/core/src/main/java/com/google/protobuf/UnmodifiableLazyStringList.java \
    java/core/src/main/java/com/google/protobuf/Utf8.java \
    java/core/src/main/java/com/google/protobuf/WireFormat.java

# This contains more source files than needed for the full version, but the
# additional files should not create any conflict.
JAVA_FULL_SRC_FILES := \
    $(call all-java-files-under, java/core/src/main/java) \
    src/google/protobuf/descriptor.proto

# Java nano library (for device-side users)
# =======================================================
include $(CLEAR_VARS)

LOCAL_MODULE := libprotobuf-java-nano
LOCAL_MODULE_TAGS := optional
LOCAL_SDK_VERSION := 9

LOCAL_SRC_FILES := $(call all-java-files-under, javanano/src/main/java/com/google/protobuf/nano)
LOCAL_SRC_FILES += $(call all-java-files-under, javanano/src/device/main/java/com/google/protobuf/nano)

LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_STATIC_JAVA_LIBRARY)

# Java nano library (for host-side users)
# =======================================================

# Java micro library (for device-side users)
# =======================================================
include $(CLEAR_VARS)

LOCAL_MODULE := libprotobuf-java-micro
LOCAL_MODULE_TAGS := optional
LOCAL_SDK_VERSION := 8

LOCAL_SRC_FILES := $(call all-java-files-under, javamicro/src/main/java/com/google/protobuf/micro)

LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_STATIC_JAVA_LIBRARY)

# Java micro library (for host-side users)
# =======================================================

# Java lite library (for device-side users)
# =======================================================
include $(CLEAR_VARS)

LOCAL_MODULE := libprotobuf-java-lite
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(JAVA_LITE_SRC_FILES)

LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_STATIC_JAVA_LIBRARY)

# Java lite library (for host-side users)
# =======================================================

# Java full library (for host-side users)
# =======================================================

# To test java proto params build rules.
# =======================================================

# To test Android-specific nanoproto features.
# =======================================================
