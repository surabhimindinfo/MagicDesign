package com.deepit.magicdesign;

import android.app.Application;
import android.content.Context;

public class MagicDesignApplication extends Application {

    private static MagicDesignApplication instance;
    private Context applicationContext;

    public static MagicDesignApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = this;
    }
}
