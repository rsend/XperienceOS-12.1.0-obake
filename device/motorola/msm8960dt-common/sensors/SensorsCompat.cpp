/*
 * Copyright (C) 2026 The XPerience Project
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

#define LOG_TAG "Msm8960SensorsCompat"

#include <dlfcn.h>
#include <errno.h>
#include <hardware/sensors.h>
#include <log/log.h>

#include <cstring>
#include <mutex>
#include <new>
#include <vector>

namespace {

constexpr char kLegacyHalPath[] =
        "/system/lib/hw/sensors.msm8960_legacy.so";

std::mutex gLegacyModuleLock;
void* gLegacyHandle;
sensors_module_t* gLegacyModule;

std::mutex gSensorListLock;
bool gSensorListInitialized;
std::vector<sensor_t> gSensorList;

struct CompatDevice {
    sensors_poll_device_1_t device;
    sensors_poll_device_t* legacy;
};

CompatDevice* asCompat(sensors_poll_device_t* device) {
    return reinterpret_cast<CompatDevice*>(device);
}

CompatDevice* asCompat(sensors_poll_device_1_t* device) {
    return reinterpret_cast<CompatDevice*>(device);
}

sensors_module_t* getLegacyModule() {
    std::lock_guard<std::mutex> lock(gLegacyModuleLock);
    if (gLegacyModule != nullptr) {
        return gLegacyModule;
    }

    gLegacyHandle = dlopen(kLegacyHalPath, RTLD_NOW | RTLD_LOCAL);
    if (gLegacyHandle == nullptr) {
        ALOGE("Unable to load legacy sensor HAL %s: %s",
              kLegacyHalPath, dlerror());
        return nullptr;
    }

    dlerror();
    auto* module = reinterpret_cast<sensors_module_t*>(
            dlsym(gLegacyHandle, HAL_MODULE_INFO_SYM_AS_STR));
    const char* error = dlerror();
    if (error != nullptr || module == nullptr) {
        ALOGE("Legacy sensor HAL %s has no %s symbol: %s", kLegacyHalPath,
              HAL_MODULE_INFO_SYM_AS_STR, error != nullptr ? error : "null symbol");
        dlclose(gLegacyHandle);
        gLegacyHandle = nullptr;
        return nullptr;
    }
    if (module->common.id == nullptr ||
            strcmp(module->common.id, SENSORS_HARDWARE_MODULE_ID) != 0 ||
            module->common.methods == nullptr || module->common.methods->open == nullptr ||
            module->get_sensors_list == nullptr) {
        ALOGE("Legacy sensor HAL %s has an invalid module interface", kLegacyHalPath);
        dlclose(gLegacyHandle);
        gLegacyHandle = nullptr;
        return nullptr;
    }

    gLegacyModule = module;
    return gLegacyModule;
}

int compatGetSensorsList(sensors_module_t*, const sensor_t** list) {
    if (list == nullptr) {
        return -EINVAL;
    }

    std::lock_guard<std::mutex> lock(gSensorListLock);
    if (!gSensorListInitialized) {
        sensors_module_t* module = getLegacyModule();
        if (module == nullptr) {
            *list = nullptr;
            return 0;
        }

        const sensor_t* legacyList = nullptr;
        const int count = module->get_sensors_list(module, &legacyList);
        if (count < 0 || (count > 0 && legacyList == nullptr)) {
            ALOGE("Legacy sensor HAL returned an invalid sensor list: count=%d list=%p",
                  count, legacyList);
            *list = nullptr;
            return 0;
        }

        gSensorList.assign(legacyList, legacyList + count);
        for (sensor_t& sensor : gSensorList) {
            // Fields after minDelay were reserved in the 0.1 ABI.  Publish
            // deterministic 1.3 values instead of exposing legacy padding.
            sensor.fifoReservedEventCount = 0;
            sensor.fifoMaxEventCount = 0;
            sensor.stringType = "";
            sensor.requiredPermission = "";
            sensor.maxDelay = 0;
            if (sensor.minDelay < 0) {
                sensor.flags = SENSOR_FLAG_ONE_SHOT_MODE;
            } else if (sensor.minDelay == 0) {
                sensor.flags = SENSOR_FLAG_ON_CHANGE_MODE;
            } else {
                sensor.flags = SENSOR_FLAG_CONTINUOUS_MODE;
            }
        }
        gSensorListInitialized = true;
    }

    *list = gSensorList.empty() ? nullptr : gSensorList.data();
    return static_cast<int>(gSensorList.size());
}

int compatActivate(sensors_poll_device_t* device, int handle, int enabled) {
    CompatDevice* compat = asCompat(device);
    if (compat == nullptr || compat->legacy == nullptr ||
            compat->legacy->activate == nullptr) {
        return -ENODEV;
    }
    return compat->legacy->activate(compat->legacy, handle, enabled);
}

int compatSetDelay(sensors_poll_device_t* device, int handle, int64_t periodNs) {
    CompatDevice* compat = asCompat(device);
    if (compat == nullptr || compat->legacy == nullptr ||
            compat->legacy->setDelay == nullptr) {
        return -ENODEV;
    }
    return compat->legacy->setDelay(compat->legacy, handle, periodNs);
}

int compatPoll(sensors_poll_device_t* device, sensors_event_t* data, int count) {
    CompatDevice* compat = asCompat(device);
    if (compat == nullptr || compat->legacy == nullptr ||
            compat->legacy->poll == nullptr) {
        return -ENODEV;
    }
    if (data == nullptr || count <= 0) {
        return -EINVAL;
    }
    return compat->legacy->poll(compat->legacy, data, count);
}

int compatBatch(sensors_poll_device_1_t* device, int handle, int,
                int64_t periodNs, int64_t) {
    // HAL 0.1 has no FIFO batching.  Applying the requested sampling period
    // provides the defined no-batching fallback.
    CompatDevice* compat = asCompat(device);
    if (compat == nullptr || compat->legacy == nullptr ||
            compat->legacy->setDelay == nullptr) {
        return -ENODEV;
    }
    return compat->legacy->setDelay(compat->legacy, handle, periodNs);
}

int compatFlush(sensors_poll_device_1_t*, int) {
    // The legacy HAL cannot wake a blocked poll to inject a flush-complete
    // event.  Report the unsupported operation explicitly.
    return -EINVAL;
}

int compatClose(hw_device_t* device) {
    if (device == nullptr) {
        return -EINVAL;
    }
    auto* compat = reinterpret_cast<CompatDevice*>(device);
    int result = 0;
    if (compat->legacy != nullptr && compat->legacy->common.close != nullptr) {
        result = compat->legacy->common.close(&compat->legacy->common);
    }
    delete compat;
    return result;
}

int compatOpen(const hw_module_t* module, const char* name, hw_device_t** device) {
    if (module == nullptr || name == nullptr || device == nullptr) {
        return -EINVAL;
    }
    *device = nullptr;

    sensors_module_t* legacyModule = getLegacyModule();
    if (legacyModule == nullptr) {
        return -ENODEV;
    }

    sensors_poll_device_t* legacyDevice = nullptr;
    const int openResult = sensors_open(&legacyModule->common, &legacyDevice);
    if (openResult != 0) {
        ALOGE("Legacy sensor HAL open failed: %d", openResult);
        return openResult;
    }
    if (legacyDevice == nullptr || legacyDevice->activate == nullptr ||
            legacyDevice->setDelay == nullptr || legacyDevice->poll == nullptr) {
        ALOGE("Legacy sensor HAL returned an incomplete polling interface");
        if (legacyDevice != nullptr && legacyDevice->common.close != nullptr) {
            legacyDevice->common.close(&legacyDevice->common);
        }
        return -ENODEV;
    }

    CompatDevice* compat = new (std::nothrow) CompatDevice{};
    if (compat == nullptr) {
        if (legacyDevice->common.close != nullptr) {
            legacyDevice->common.close(&legacyDevice->common);
        }
        return -ENOMEM;
    }

    compat->legacy = legacyDevice;
    compat->device.common.tag = HARDWARE_DEVICE_TAG;
    compat->device.common.version = SENSORS_DEVICE_API_VERSION_1_3;
    compat->device.common.module = const_cast<hw_module_t*>(module);
    compat->device.common.close = compatClose;
    compat->device.activate = compatActivate;
    compat->device.setDelay = compatSetDelay;
    compat->device.poll = compatPoll;
    compat->device.batch = compatBatch;
    compat->device.flush = compatFlush;

    *device = &compat->device.common;
    return 0;
}

hw_module_methods_t gCompatMethods = {
    .open = compatOpen,
};

}  // namespace

extern "C" sensors_module_t HAL_MODULE_INFO_SYM = {
    .common = {
        .tag = HARDWARE_MODULE_TAG,
        .module_api_version = SENSORS_MODULE_API_VERSION_0_1,
        .hal_api_version = HARDWARE_HAL_API_VERSION,
        .id = SENSORS_HARDWARE_MODULE_ID,
        .name = "MSM8960 legacy sensor compatibility HAL",
        .author = "The XPerience Project",
        .methods = &gCompatMethods,
    },
    .get_sensors_list = compatGetSensorsList,
};
