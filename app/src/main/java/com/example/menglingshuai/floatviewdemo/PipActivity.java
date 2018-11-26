package com.example.menglingshuai.floatviewdemo;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public abstract class PipActivity {
    protected LayoutParams windowManagerParams;
    protected WindowManager windowManager;
    public boolean isHidden = true;
    private boolean isInit;
    private View view;

    public PipActivity() {
    }

    @SuppressLint("WrongConstant")
    private void init() {
        if (!this.isInit) {
            this.isInit = true;
            this.windowManager = (WindowManager)this.getContext().getSystemService("window");
            this.initWindowManagerParams();
            this.onCreate();
        }

    }

    public final void startPip() {
        this.init();
        if (this.view == null) {
            this.view = this.onCreateView(LayoutInflater.from(this.getContext()));
            if (this.view != null) {
                this.windowManager.addView(this.view, this.windowManagerParams);
            }

            this.onViewCreated(this.view);
            this.show();
        }

    }

    private void initWindowManagerParams() {
        if (VERSION.SDK_INT >= 26) {
            this.windowManagerParams = new LayoutParams(2038);
            this.windowManagerParams.type = 2038;
        } else {
            this.windowManagerParams = new LayoutParams(2003);
            this.windowManagerParams.type = 2003;
        }

        this.windowManagerParams.format = -3;
        this.windowManagerParams.flags = 40;
        this.windowManagerParams.gravity = 51;
        this.windowManagerParams.height = -2;
        this.windowManagerParams.width = -2;
        this.windowManagerParams.x = 0;
        this.windowManagerParams.y = 0;
    }

    public final Context getContext() {
        return PipLauncher.getInstance().getContext();
//        return this.getContext();
    }

    public final Resources getResources() {
        return this.getContext().getResources();
    }

    public final void requestLayout() {
        if (this.view != null) {
            this.windowManager.updateViewLayout(this.view, this.windowManagerParams);
        }

    }

    public final void updateSize(int width, int height) {
        this.windowManagerParams.width = width;
        this.windowManagerParams.height = height;
        this.requestLayout();
    }

    public final void updatePosition(int x, int y) {
        this.windowManagerParams.x = x;
        this.windowManagerParams.y = y;
        this.requestLayout();
    }

    public final boolean isShow() {
        return !this.isHidden;
    }

    public final View getView() {
        return this.view;
    }

    public final int getHeight() {
        return this.view != null ? this.view.getHeight() : 0;
    }

    public final int getWidth() {
        return this.view != null ? this.view.getWidth() : 0;
    }

    public final void closePip() {
        this.isInit = false;
        this.hide();
        this.onDestroyView();
        this.onDestroy();
        if (this.view != null) {
            this.windowManager.removeView(this.view);
            this.view = null;
        }

    }

    @SuppressLint("WrongConstant")
    public final void show() {
        if (this.isHidden) {
            this.isHidden = false;
            if (this.view != null) {
                this.view.setVisibility(0);
            }

            this.onShow();
        }

    }

    @SuppressLint("WrongConstant")
    public final void hide() {
        if (!this.isHidden) {
            this.isHidden = true;
            if (this.view != null) {
                this.view.setVisibility(8);
            }

            this.onHide();
        }

    }

    protected void onCreate() {
    }

    protected abstract View onCreateView(LayoutInflater var1);

    protected void onViewCreated(View view) {
    }

    protected void onShow() {
    }

    protected void onHide() {
    }

    protected void onDestroyView() {
    }

    protected void onDestroy() {
    }
}

