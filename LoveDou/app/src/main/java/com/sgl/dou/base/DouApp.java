package com.sgl.dou.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by sugl on 2018/2/27 0027.
 */

public class DouApp extends Application {
    public static Context context;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}
