package dev.farjana.guideapp;

import android.app.Application;

import com.onesignal.OneSignal;

public class Signal extends Application {
    private static final String ONESIGNAL_APP_ID = "@string/one_signal_appId";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}