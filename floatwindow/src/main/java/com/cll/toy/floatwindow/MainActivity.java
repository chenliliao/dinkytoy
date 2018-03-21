package com.cll.toy.floatwindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initWidgets();
    }


    private Button mWindowButton;
    private void initWidgets() {
        mWindowButton = (Button) findViewById(R.id.show_or_hide_window);
        initClickListener();
    }

    private void initClickListener() {
        mWindowButton.setOnClickListener(mWindowClickListener);
    }

    private View.OnClickListener mWindowClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, FloatWindowService.class);
            if (SmallWindowManager.isOpen()){
                stopService(intent);
            }else{
                startService(intent);
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }
}
