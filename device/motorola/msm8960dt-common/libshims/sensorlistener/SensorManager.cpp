/*
 * Copyright (C) 2010 The Android Open Source Project
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

#define LOG_TAG "LegacyCameraSensors"

#include <stdint.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

#include <binder/IBinder.h>
#include <binder/IServiceManager.h>

#include <utils/Errors.h>
#include <utils/RefBase.h>
#include <utils/Singleton.h>
#include <utils/String16.h>
#include <utils/String8.h>

#include "gui/ISensorEventConnection.h"
#include "gui/ISensorServer.h"
#include "gui/Sensor.h"
#include "gui/SensorEventQueue.h"
#include "gui/SensorManager.h"

namespace android {

ANDROID_SINGLETON_STATIC_INSTANCE(SensorManager)

static_assert(sizeof(SensorManager) == 36,
              "legacy Motorola SensorManager ABI size changed");

SensorManager::SensorManager()
    : mSensorList(nullptr) {
    assertStateLocked();
}

SensorManager::~SensorManager() {
    free(mSensorList);
}

void SensorManager::sensorManagerDied() {
    Mutex::Autolock lock(mLock);
    mSensorServer.clear();
    free(mSensorList);
    mSensorList = nullptr;
    mSensors.clear();
}

status_t SensorManager::assertStateLocked() const {
    bool reconnect = mSensorServer == nullptr;
    if (!reconnect) {
        reconnect = IInterface::asBinder(mSensorServer)->pingBinder() != NO_ERROR;
    }

    if (!reconnect) {
        return NO_ERROR;
    }

    mSensorServer.clear();
    free(mSensorList);
    mSensorList = nullptr;
    mSensors.clear();

    const String16 serviceName("sensorservice");
    status_t err = NAME_NOT_FOUND;
    for (int attempt = 0; attempt < 4; ++attempt) {
        err = getService(serviceName, &mSensorServer);
        if (err != NAME_NOT_FOUND) {
            break;
        }
        usleep(250000);
    }
    if (err != NO_ERROR) {
        ALOGE("Unable to get sensorservice: %d", err);
        return err;
    }
    if (mSensorServer == nullptr) {
        ALOGE("sensorservice returned a null interface");
        return NO_INIT;
    }

    class DeathObserver : public IBinder::DeathRecipient {
    public:
        explicit DeathObserver(SensorManager& manager) : mManager(manager) {}

        void binderDied(const wp<IBinder>& who) override {
            ALOGW("sensorservice died [%p]", who.unsafe_get());
            mManager.sensorManagerDied();
        }

    private:
        SensorManager& mManager;
    };

    mDeathObserver = new DeathObserver(*const_cast<SensorManager*>(this));
    err = IInterface::asBinder(mSensorServer)->linkToDeath(mDeathObserver);
    if (err != NO_ERROR) {
        ALOGE("Unable to monitor sensorservice: %d", err);
        mDeathObserver.clear();
        mSensorServer.clear();
        return err;
    }

    // Native camera services have no application package attribution.
    mSensors = mSensorServer->getSensorList(String16());
    const size_t count = mSensors.size();
    if (count == 0) {
        return NO_ERROR;
    }

    mSensorList =
            static_cast<Sensor const**>(malloc(count * sizeof(*mSensorList)));
    if (mSensorList == nullptr) {
        ALOGE("Unable to allocate sensor pointer list");
        mSensors.clear();
        return NO_MEMORY;
    }

    for (size_t i = 0; i < count; ++i) {
        mSensorList[i] = mSensors.array() + i;
    }
    return NO_ERROR;
}

ssize_t SensorManager::getSensorList(Sensor const* const** list) const {
    if (list == nullptr) {
        return BAD_VALUE;
    }

    Mutex::Autolock lock(mLock);
    const status_t err = assertStateLocked();
    if (err != NO_ERROR) {
        return static_cast<ssize_t>(err);
    }

    *list = mSensorList;
    return static_cast<ssize_t>(mSensors.size());
}

Sensor const* SensorManager::getDefaultSensor(int type) {
    Mutex::Autolock lock(mLock);
    if (assertStateLocked() != NO_ERROR) {
        return nullptr;
    }

    for (size_t i = 0; i < mSensors.size(); ++i) {
        if (mSensorList[i]->getType() == type) {
            return mSensorList[i];
        }
    }
    return nullptr;
}

sp<SensorEventQueue> SensorManager::createEventQueue() {
    Mutex::Autolock lock(mLock);
    if (assertStateLocked() != NO_ERROR) {
        return nullptr;
    }

    sp<ISensorEventConnection> connection =
            mSensorServer->createSensorEventConnection(
                    String8("legacy-camera"), 0, String16());
    if (connection == nullptr) {
        ALOGE("sensorservice rejected the legacy camera event queue");
        return nullptr;
    }
    return new SensorEventQueue(connection);
}

} // namespace android
