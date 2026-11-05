# Copyright 2011, The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH:= $(call my-dir)

# Include res dir from chips
chips_dir := ../../../frameworks/opt/chips/res

#Include res dir from libraries
appcompat_dir := ../../../$(SUPPORT_LIBRARY_ROOT)/v7/appcompat/res
photo_dir := ../../../frameworks/opt/photoviewer/res ../../../frameworks/opt/photoviewer/appcompat/res
gridlayout_dir := ../../../$(SUPPORT_LIBRARY_ROOT)/v7/gridlayout/res
bitmap_dir := ../../../frameworks/opt/bitmap/res
datetimepicker_dir := ../../../frameworks/opt/datetimepicker/res
res_dirs := res $(appcompat_dir) $(chips_dir) $(photo_dir) $(gridlayout_dir) $(bitmap_dir) $(datetimepicker_dir)

##################################################
# Build APK

##################################################
# Build all sub-directories

include $(call all-makefiles-under,$(LOCAL_PATH))
