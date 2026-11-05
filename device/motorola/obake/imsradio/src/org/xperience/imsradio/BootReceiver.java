package org.xperience.imsradio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;

public final class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (SystemProperties.getBoolean("persist.ims.radio_adapter.enabled", true)) {
            context.startService(new Intent(context, ImsRadioAdapterService.class));
        }
    }
}

