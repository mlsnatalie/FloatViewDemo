package com.example.menglingshuai.floatviewdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PipTouchListener implements View.OnTouchListener {

    private static final String TAG = "PipTouchListener";
    private float mPrevX;
    private float mPrevY;
    private float downX;
    private float downY;
    private PipActivity pipActivity;
    private boolean isAutoToEdge;

    public PipTouchListener(PipActivity pipActivity) {
        this.pipActivity = pipActivity;
    }

    public void setAutoToEdge(boolean autoToEdge) {
        this.isAutoToEdge = autoToEdge;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getActionMasked()) {
            case 0:
                this.mPrevX = event.getRawX();
                this.mPrevY = event.getRawY();
                this.downX = event.getRawX();
                this.downY = event.getRawY();
                break;
            case 1:
            case 3:
                this.updateViewToEdge();
                float upX = event.getRawX();
                float upY = event.getRawY();
                if (Math.abs(this.downX - upX) < 10.0F && Math.abs(this.downY - upY) < 10.0F) {
                    return false;
                }

                return true;
            case 2:
                float deltaX = event.getRawX() - this.mPrevX;
                float deltaY = event.getRawY() - this.mPrevY;
                if ((this.pipActivity.windowManagerParams.gravity & 80) == 80) {
                    deltaY = -deltaY;
                }

                if ((this.pipActivity.windowManagerParams.gravity & 5) == 5) {
                    deltaX = -deltaX;
                }

                this.mPrevX = event.getRawX();
                this.mPrevY = event.getRawY();
                int x = (int)((float)this.pipActivity.windowManagerParams.x + deltaX);
                int y = (int)((float)this.pipActivity.windowManagerParams.y + deltaY);

                try {
                    this.updateViewLayout(x, y);
                } catch (Exception var9) {
                    ;
                }
        }

        return false;
    }

    private void updateViewLayout(int x, int y) {
        if (this.pipActivity.getView() == null) {
            Log.d("PipTouchListener", "floatView is null");
        } else if (this.pipActivity.getView().getParent() == null) {
            Log.d("PipTouchListener", "floatView not add to windowManager");
        } else {
            if (x < 0) {
                x = 0;
            }

            int screenWidth = this.pipActivity.getResources().getDisplayMetrics().widthPixels;
            int screenHeight = this.pipActivity.getResources().getDisplayMetrics().heightPixels;
            if (x > screenWidth - this.pipActivity.getWidth()) {
                x = screenWidth - this.pipActivity.getWidth();
            }

            if (y < 0) {
                y = 0;
            }

            if (y > screenHeight - this.pipActivity.getHeight()) {
                y = screenHeight - this.pipActivity.getHeight();
            }

            this.pipActivity.updatePosition(x, y);
        }
    }

    private void updateViewToEdge() {
        if (this.isAutoToEdge) {
            if (this.pipActivity.windowManagerParams != null && this.pipActivity.getView() != null) {
                int startX = this.pipActivity.windowManagerParams.x;
                int destX = 0;
                int screenWidth = this.pipActivity.getResources().getDisplayMetrics().widthPixels;
                if (this.pipActivity.windowManagerParams.x > (screenWidth - this.pipActivity.getView().getWidth()) / 2) {
                    destX = screenWidth - this.pipActivity.getView().getWidth();
                }

                ValueAnimator valueAnimator = ObjectAnimator.ofInt(new int[]{startX, destX});
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (Integer)animation.getAnimatedValue();
                        PipTouchListener.this.updateViewLayout(value, PipTouchListener.this.pipActivity.windowManagerParams.y);
                    }
                });
                valueAnimator.setDuration(200L);
                valueAnimator.start();
            } else {
                Log.d("PipTouchListener", "floatView or windowManagerParams is null");
            }
        }
    }
}
