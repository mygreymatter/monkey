package com.mayo.godko;

import android.app.Application;

/**
 * Created by mayo on 2/9/15.
 */
public class MonkeyApp extends Application {
    private static MonkeyApp mInstance = null;

    public static MonkeyApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();

    }

}
