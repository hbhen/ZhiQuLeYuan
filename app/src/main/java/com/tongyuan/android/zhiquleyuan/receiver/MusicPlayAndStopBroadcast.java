package com.tongyuan.android.zhiquleyuan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by DTC on 2018/4/24.
 */
/*
* 废弃 , 不用 .
* */
public class MusicPlayAndStopBroadcast extends BroadcastReceiver {

    int isPlaying = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case "com.tongyuan.android.zhiquleyuan.ACTION_PLAY":
                isPlaying = 1;
                intent.putExtra("isplaying", isPlaying);
                break;
            case "com.tongyuan.android.zhiquleyuan.ACTION_STOP":
                isPlaying = 0;
                intent.putExtra("isplaying", isPlaying);
                break;
            default:
                break;
        }
    }
}
