package com.tongyuan.android.zhiquleyuan.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by android on 2016/12/20.
 */

public class ZhiQuLeYuanApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;


    }
}
