package com.shc.north;

import android.app.Application;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class App extends Application {
    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public static App getApp(){
        return app;
    }
}