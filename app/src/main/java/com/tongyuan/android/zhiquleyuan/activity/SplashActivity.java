package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tongyuan.android.zhiquleyuan.R;

/**
 * Created by android on 2016/12/2.
 */

public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_splash);

        Log.e("wwwwwwww","wakeup");
        Handler handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Intent intent = new Intent(SplashActivity.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        }).start();

    }
}
