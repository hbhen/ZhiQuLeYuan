package com.tongyuan.android.zhiquleyuan.base;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity的基类
 * Created by zgg on 2017/6/3.
 */

public class BaseActivity extends AppCompatActivity implements ServiceConnection {


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
