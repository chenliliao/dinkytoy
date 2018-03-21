package com.cll.toy.floatwindow;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

/**
 * Created by cll on 2018/3/21.
 */

public enum SmallWindowManager {

    SINGLETON;

    private static boolean isOpen = false;
    private SurfaceView mSurfaceView;
    private Camera mCamera;
    public WindowUtils.Builder showFloatWindow(Context context){
        if (isOpen){
            return null;
        }
        final View view = LayoutInflater.from(context).inflate(R.layout.window_layout, null);
        mSurfaceView = (SurfaceView) view.findViewById(R.id.preview_layout);
        mSurfaceView.getHolder().addCallback(mCallback);
        WindowUtils.Builder builder = new WindowUtils.Builder(context)
                .setFloatView(view)
                .setWidthAndHeight(200,200)
                .setXY(0,0)
                .setDesktopShow(true)
                .create();
        builder.addView();
        isOpen = true;
        return builder;
    }

    public void dismissFloatWindow(WindowUtils.Builder builder){
        if (!isOpen){
            return ;
        }
        if (builder != null){
            builder.dismissWindow();
        }
        isOpen = false;
    }

    private SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                if (mCamera == null){
                    mCamera = Camera.open();
                }
                mCamera.setPreviewDisplay(holder);
                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mCamera != null){
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
        }
    };

    public static boolean isOpen(){
        return isOpen;
    }
}
