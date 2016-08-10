// (c)2016 Flipboard Inc, All Rights Reserved.

package com.htf.rxretrofit;

import android.app.Application;

public class App extends Application {
    
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
