/*
 * Copyright (c) 2013, The Linux Foundation. All rights reserved.
 * Copyright (c) 2015, The CyanogenMod Project
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * *    * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *     * Neither the name of The Linux Foundation nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
#define LOG_NIDEBUG 0

#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>

#define LOG_TAG "QCOM PowerHAL"
#include <utils/Log.h>
#include <hardware/hardware.h>
#include <hardware/power.h>

#include "utils.h"
#include "metadata-defs.h"
#include "hint-data.h"
#include "performance.h"
#include "power-common.h"

static int current_power_profile = PROFILE_BALANCED;

#define LAUNCH_BOOST_DURATION_MS 10000
#define INTERACTION_BOOST_DURATION_MS 500
#define INTERACTION_BOOST_MAX_DURATION_MS 5000
#define CPU_BOOST_PULSE_PATH \
    "/sys/module/cpu_boost/parameters/boostpulse_ms"

static int boost_cpu(int duration_ms)
{
    char duration[16];

    snprintf(duration, sizeof(duration), "%d", duration_ms);
    if (sysfs_write(CPU_BOOST_PULSE_PATH, duration) != 0) {
        ALOGE("Failed to request a %d ms CPU boost", duration_ms);
        return -1;
    }

    return 0;
}

int get_number_of_profiles() {
    return 3;
}

/**
 * If target is 8064:
 *     return 1
 * else:
 *     return 0
 */
static int is_target_8064(void)
{
    static int is_8064 = -1;
    int soc_id;

    if (is_8064 >= 0)
        return is_8064;

    soc_id = get_soc_id();
    if (soc_id == 153)
        is_8064 = 1;
    else
        is_8064 = 0;

    return is_8064;
}

static int profile_high_performance_8960[] = {
    CPUS_ONLINE_MIN_2,
};

static int profile_high_performance_8064[] = {
    CPUS_ONLINE_MIN_4,
};

static int profile_power_save_8960[] = {
    /* Don't do anything for now */
};

static int profile_power_save_8064[] = {
    CPUS_ONLINE_MAX_LIMIT_2,
};

static void set_power_profile(int profile) {

    if (profile == current_power_profile)
        return;

    ALOGV("%s: profile=%d", __func__, profile);

    if (current_power_profile != PROFILE_BALANCED) {
        undo_hint_action(DEFAULT_PROFILE_HINT_ID);
        ALOGV("%s: hint undone", __func__);
    }

    if (profile == PROFILE_HIGH_PERFORMANCE) {
        int *resource_values = is_target_8064() ?
            profile_high_performance_8064 : profile_high_performance_8960;

        perform_hint_action(DEFAULT_PROFILE_HINT_ID,
            resource_values, ARRAY_SIZE(resource_values));
        ALOGD("%s: set performance mode", __func__);
    } else if (profile == PROFILE_POWER_SAVE) {
        int* resource_values = is_target_8064() ?
            profile_power_save_8064 : profile_power_save_8960;

        perform_hint_action(DEFAULT_PROFILE_HINT_ID,
            resource_values, ARRAY_SIZE(resource_values));
        ALOGD("%s: set powersave", __func__);
    }

    current_power_profile = profile;
}

int power_hint_override(__attribute__((unused)) struct power_module *module,
        power_hint_t hint, void *data)
{
    int duration;
    if (hint == POWER_HINT_SET_PROFILE) {
        set_power_profile(*(int32_t *)data);
        return HINT_HANDLED;
    }

    /* Custom profiles already own the CPU policy. */
    if (current_power_profile != PROFILE_BALANCED) {
        return HINT_HANDLED;
    }

    if (hint == POWER_HINT_LAUNCH) {
        /* A null data pointer is the framework's launch-end notification. */
        if (data == NULL) {
            return HINT_HANDLED;
        }

        boost_cpu(LAUNCH_BOOST_DURATION_MS);
        return HINT_HANDLED;
    }

    if (hint == POWER_HINT_INTERACTION) {
        duration = INTERACTION_BOOST_DURATION_MS;
        if (data != NULL && *(int32_t *)data > 0) {
            duration = *(int32_t *)data;
            if (duration > INTERACTION_BOOST_MAX_DURATION_MS) {
                duration = INTERACTION_BOOST_MAX_DURATION_MS;
            }
        }

        boost_cpu(duration);
        return HINT_HANDLED;
    }

    return HINT_NONE;
}
