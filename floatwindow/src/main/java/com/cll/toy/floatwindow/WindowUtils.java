package com.cll.toy.floatwindow;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by cll on 2018/3/21.
 */

public class WindowUtils {


    public static class Builder{

        private Context mContext;
        private View mView;
        private int mX;
        private int mY;
        private int mWidth;
        private int mHeight;
        private int mType;
        private WindowManager mWindowManager;
        private WindowManager.LayoutParams mParams;
        public Builder(final Context context){
            mContext = context;
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            mParams = new WindowManager.LayoutParams();
        }
        public Builder setFloatView(final View view){
            mView = view;
            return this;
        }

        public Builder setXY(final int x, final int y){
            mX = x;
            mY = y;
            return this;
        }

        public Builder setWidthAndHeight(final int width, final int height){
            mWidth = width;
            mHeight = height;
            return this;
        }

        public Builder setDesktopShow(final boolean isShow){
            mType = isShow ? WindowManager.LayoutParams.TYPE_PHONE : WindowManager.LayoutParams.TYPE_TOAST;
            return this;
        }

//        public Builder setWindowType(final ){
//
//        }

        public Builder create(){
            if (mParams != null){
                mParams.width = mWidth;
                mParams.height = mHeight;
                mParams.type = mType;
                mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mParams.gravity = Gravity.LEFT | Gravity.TOP;
                mParams.x = mX;
                mParams.y = mY;
            }
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mContext.startActivity(new Intent(mContext,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
            moveTouch();
            return this;
        }

        public void addView(){
            if (mView != null && mParams != null){
                mWindowManager.addView(mView, mParams);
            }
        }

        public void dismissWindow(){
            if (mView != null && mWindowManager != null){
                mWindowManager.removeView(mView);
            }
        }

        private int a;
        private int b;
        private void moveTouch(){
            if (mView == null){
                return;
            }
            mView.setOnTouchListener(new View.OnTouchListener() {

                private float lastX;
                private float lastY;
                private float changeX;
                private float changeY;
                private int newX;
                private int newY;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            lastX =  event.getRawX();
                            lastY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            changeX = event.getRawX() - lastX;
                            changeY = event.getRawY() - lastY;
                            newX += changeX / 10;
                            newY += changeY / 10;
                            if (mParams != null && mWindowManager != null && mView != null){
                                mParams.x = newX;
                                mParams.y = newY;
                                mWindowManager.updateViewLayout(mView, mParams);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
//                            int x1 = (int)event.getRawX() ;
//                            int y1 = (int)event.getRawY() ;
                            break;
                    }
                    return false;
                }
            });
        }

    }
}
