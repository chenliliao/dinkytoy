package com.cll.toy.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cll on 2018/3/21.
 */

public class FloatWindowService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private WindowUtils.Builder mBuilder;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mBuilder = SmallWindowManager.SINGLETON.showFloatWindow(getApplicationContext());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SmallWindowManager.SINGLETON.dismissFloatWindow(mBuilder);
    }
}
