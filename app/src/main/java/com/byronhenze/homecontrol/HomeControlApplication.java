package com.byronhenze.homecontrol;

import android.app.Application;
import android.content.Context;

public class HomeControlApplication extends Application {
    private static HomeControlApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        this.setAppContext(getApplicationContext());
    }

    public static HomeControlApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public void setAppContext(Context mAppContext) {
        HomeControlApplication.mAppContext = mAppContext;
    }
}
