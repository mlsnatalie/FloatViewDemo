package com.example.menglingshuai.floatviewdemo;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

public class PipLauncher {
    private static PipLauncher instance;
    public HashMap<String, PipActivity> pipCache = new HashMap();
    private Context context;
    private boolean isInit;

    public PipLauncher() {
    }

    public static PipLauncher getInstance() {
        if (instance == null) {
            Class var0 = PipLauncher.class;
            synchronized(PipLauncher.class) {
                if (instance == null) {
                    instance = new PipLauncher();
                }
            }
        }

        return instance;
    }

    public void init(Context context) {
        if (!this.isInit) {
            this.isInit = true;
            this.context = context.getApplicationContext();
        }

    }

    public Context getContext() {
        return this.context;
    }

    public <T extends PipActivity> T getPipInstance(String tag, Class<T> tClass) {
        try {
            T t = (T) this.pipCache.get(tag);
            if (t == null) {
                t = (T) tClass.newInstance();
                this.pipCache.put(tag, t);
            }

            return t;
        } catch (InstantiationException var4) {
            var4.printStackTrace();
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public <T extends PipActivity> void startPip(String tag, Class<T> tClass) {
        T t = this.getPipInstance(tag, tClass);
        if (t != null) {
            t.startPip();
        }

    }
//
    public void closePip(String tag) {
        PipActivity pipActivity = (PipActivity)this.pipCache.get(tag);
        if (pipActivity != null) {
            this.pipCache.remove(tag);
            pipActivity.closePip();
        }

    }

    public void hidePip(String tag) {
        PipActivity pipActivity = (PipActivity)this.pipCache.get(tag);
        if (pipActivity != null) {
            pipActivity.hide();
        }

    }

    public void showPip(String tag) {
        PipActivity pipActivity = (PipActivity)this.pipCache.get(tag);
        if (pipActivity != null) {
            pipActivity.show();
        }

    }

//    public void showToast(Toast toast) {
//        (new PipToast(toast)).startPip();
//    }
//
//    public void showToast(String message) {
//        Toast toast = Toast.makeText(this.context, message, 0);
//        (new PipToast(toast)).startPip();
//    }
//
//    public void showDialog(Dialog dialog) {
//        (new PipDialog(dialog)).show();
//    }

    public void removePipCache(String tag) {
        this.pipCache.remove(tag);
    }
}
