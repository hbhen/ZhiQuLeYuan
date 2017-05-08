package com.tongyuan.android.zhiquleyuan.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by android on 2017/2/6.
 */

public class BroadCastManager {
    private static BroadCastManager broadCastManager=new BroadCastManager();
    public static BroadCastManager getInstance(){
        return broadCastManager;
    }
    //注册广播接收者
    public void registerReceiver(Activity activity, BroadcastReceiver receiver, IntentFilter filter){
        activity.registerReceiver(receiver,filter);
    }
    //注销广播接收者
    public void sendBroadCast(Activity activity, Intent intent){
        activity.sendBroadcast(intent);
    }

}

