package com.yumi.android;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;


/**
 * Created by hjl on 2017/10/25.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
