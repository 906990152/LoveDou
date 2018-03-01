package com.sgl.dou.rxretrofitlibrary;

import android.app.Application;

/**
 * 全局app
 */

public class RxRetrofitApp  {
    private static Application application;
    public static boolean isDebug;

    public static void init(Application app){
        setApplication(app);
    }

    public static Application getApplication() {
        return application;
    }

    private static void setApplication(Application application) {
        RxRetrofitApp.application = application;
    }

}
