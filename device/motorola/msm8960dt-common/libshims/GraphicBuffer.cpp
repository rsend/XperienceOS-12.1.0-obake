/*
 * Copyright (C) 2016 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <new>
#include <string>

#include <ui/GraphicBuffer.h>

/*
 * Legacy camera blobs construct GraphicBuffer with the pre-Oreo four-argument
 * ABI.  Oreo retains the equivalent constructor with an added requestor name,
 * so initialize the object through that supported implementation.
 */
extern "C" void _ZN7android13GraphicBufferC1Ejjij(
        android::GraphicBuffer* buffer, uint32_t width, uint32_t height,
        android::PixelFormat format, uint32_t usage) {
    new (buffer) android::GraphicBuffer(width, height, format, usage,
            std::string("legacy-camera"));
}
