package com.bove.martin.moviedb;

import android.app.Application;
import android.content.Context;

/**
 * Created by Martín Bove on 03-Aug-20.
 * E-mail: mbove77@gmail.com
 */
public class MyApp extends Application {
    private static MyApp instance;
    public static Context context;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
