package com.shc.north;

import android.app.Application;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class App extends Application {
    public static final String MAX = "10";
    private static App app;

    private String a = null;
    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public static App getApp(){
        return app;
    }

    public void setContent() {
        
    }
}
