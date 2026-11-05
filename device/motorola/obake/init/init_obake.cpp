/*
   Copyright (c) 2013, The Linux Foundation. All rights reserved.

   Redistribution and use in source and binary forms, with or without
   modification, are permitted provided that the following conditions are
   met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.
    * Neither the name of The Linux Foundation nor the names of its
      contributors may be used to endorse or promote products derived
      from this software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
   WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
   ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
   BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
   CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
   SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
   BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
   WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
   OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
   IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

#include <stdlib.h>

#define _REALLY_INCLUDE_SYS__SYSTEM_PROPERTIES_H_
#include <sys/_system_properties.h>

#include <android-base/logging.h>
#include <android-base/properties.h>

#include "vendor_init.h"
#include "property_service.h"
#include "log.h"
#include "util.h"

#define ARRAY_SIZE(x) (sizeof(x)/sizeof(x[0]))

using android::base::GetProperty;
using android::init::property_set;

static void set_property(const std::string& name, const std::string& value)
{
    const uint32_t result = property_set(name, value);
    if (result != PROP_SUCCESS) {
        LOG(ERROR) << "Unable to set property '" << name << "' to '" << value
                   << "': error " << result;
    }
}

static void set_cmdline_properties()
{
    size_t i;

    struct {
        const char *src_prop;
        const char *dest_prop;
        const char *def_val;
    } prop_map[] = {
        { "ro.boot.device", "ro.hw.device", "obake", },
        { "ro.boot.hwrev", "ro.hw.hwrev", "0x8300", },
        { "ro.boot.radio", "ro.hw.radio", "0x2", },
    };

    for (i = 0; i < ARRAY_SIZE(prop_map); i++) {
        const std::string value = GetProperty(prop_map[i].src_prop,
                                              prop_map[i].def_val);
        set_property(prop_map[i].dest_prop, value);
    }
}

static void gsm_properties()
{
    set_property("ro.telephony.default_network", "9");
    set_property("telephony.lteOnGsmDevice", "1");
}

static void verizon_properties()
{
    set_property("ro.telephony.default_cdma_sub", "0");
    set_property("ro.cdma.home.operator.numeric", "311480");
    set_property("ro.cdma.home.operator.alpha", "Verizon");

    set_property("persist.radio.0x9e_not_callname", "1");
    set_property("persist.ril.max.crit.qmi.fails", "4");

    set_property("ril.subscription.types", "NV,RUIM");
    set_property("ro.cdma.subscribe_on_ruim_ready", "true");
    set_property("ro.mot.ignore_csim_appid", "true");
    set_property("ro.ril.svdo", "true");
    set_property("ro.telephony.default_network", "10");
    set_property("telephony.lteOnCdmaDevice", "1");
    set_property("telephony.rilV7NeedCDMALTEPhone", "true");
    
    set_property("ro.cdma.data_retry_config", "max_retries=infinite,0,0,10000,10000,100000,10000,10000,10000,10000,140000,540000,960000");
    set_property("ro.gsm.data_retry_config", "default_randomization=2000,max_retries=infinite,1000,1000,80000,125000,485000,905000");
    set_property("ro.gsm.2nd_data_retry_config", "max_retries=1,15000");
    set_property("ro.com.google.clientidbase.ms", "android-verizon");
    set_property("ro.com.google.clientidbase.am", "android-verizon");
    set_property("ro.com.google.clientidbase.yt", "android-verizon");
}

void vendor_load_properties()
{
    std::string platform;
    std::string radio;
    std::string carrier;
    std::string bootdevice;

    platform = GetProperty("ro.board.platform", "");
    if (platform != ANDROID_TARGET)
        return;

    set_cmdline_properties();

    radio = GetProperty("ro.boot.radio", "");
    carrier = GetProperty("ro.boot.carrier", "");
    bootdevice = GetProperty("ro.boot.device", "");
    if (bootdevice == "obakem") {
        /* xt1030 - obakem */
        set_property("ro.product.device", "obakem");
        set_property("ro.build.product", "obakem");
        set_property("ro.product.model", "DROID Mini");
        set_property("ro.audio.init", "obakem");
    } else if (bootdevice == "obake") {
        /* xt1080 - obake */
        set_property("ro.product.device", "obake");
        set_property("ro.build.product", "obake");
        set_property("ro.product.model", "DROID Ultra");
    } else if (bootdevice == "obake-maxx") {
        /* xt1080m - obake-maxx */
        set_property("ro.product.device", "obake-maxx");
        set_property("ro.build.product", "obake-maxx");
        set_property("ro.product.model", "DROID MAXX");
        set_property("ro.audio.init", "obake-maxx");
    } else {
        LOG(ERROR) << "Unsupported Obake boot device '" << bootdevice
                   << "' (radio '" << radio << "', carrier '" << carrier << "')";
    }

    /* shared */
    set_property("ro.build.description", "obake_verizon-user 4.4.4 SU6-7.2 3 release-keys");
    set_property("ro.build.fingerprint", "motorola/obake_verizon/obake:4.4.4/SU6-7.2/3:user/release-keys");

    /* fastboot oem config carrier switch */
    if (carrier == "vzw") {
        verizon_properties(); /* Default - set in FXZ */
    } else {
        gsm_properties(); /* Manually reconfigured */
    }

}
