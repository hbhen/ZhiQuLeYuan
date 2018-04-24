package com.tongyuan.android.zhiquleyuan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

/**
 * Created by DTC on 2018/2/27.
 */

public class DownloadedReceiver extends BroadcastReceiver {
    private static final String TAG = "TAG";

    @Override
    public void onReceive(Context context, Intent intent) {
        
        if (intent.getAction().equals("android.intent.action.DOWNLOAD_COMPLETE")) {
            ToastUtil.showToast(context, "下载完成");
            LogUtil.i(TAG, "onReceive: ");
        }
    }
}
