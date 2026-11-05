package org.xperience.imsradio;

import android.app.Application;
import android.content.Intent;
import android.os.SystemProperties;

/** Starts the IMS radio adapter as soon as this persistent process is created. */
public final class ImsRadioApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (SystemProperties.getBoolean("persist.ims.radio_adapter.enabled", true)) {
            startService(new Intent(this, ImsRadioAdapterService.class));
        }
    }
}
