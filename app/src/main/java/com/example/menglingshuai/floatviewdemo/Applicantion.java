package com.example.menglingshuai.floatviewdemo;

import android.app.Application;

public class Applicantion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PipLauncher.getInstance().init(this);
    }
}
