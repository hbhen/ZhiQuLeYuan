package com.tongyuan.android.zhiquleyuan.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tongyuan.android.zhiquleyuan.base.BaseActivity;
import com.tongyuan.android.zhiquleyuan.player.MusicAidlStub;

/**
 * 播放服务端
 * Created by android on 2017/3/13.
 */

public class MusicPlayerService extends Service {
    private MusicAidlStub musicAidlStub ;
    private NotificationManager mNotificationManager;
    public static final int CODE_prepared = 1;
    public static final int CODE_error    = 2;
    public static final int CODE_complete = 3;
    public static final int CODE_playing  = 4;

    @Override
    public void onCreate() {
        super.onCreate();
        musicAidlStub = new MusicAidlStub(this, this);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    public void sendBroadCastToReceiver(int code) {
        Intent intent =null;
        switch (code) {
            case CODE_prepared:
                intent = new Intent(BaseActivity.PLAYER_PREPARED);
                break;
            case CODE_error:
                intent = new Intent(BaseActivity.PLAYER_ERROR);
                break;
            case CODE_complete:
                intent = new Intent(BaseActivity.PLAYER_COMPLETE);
                break;
            case CODE_playing:
                intent = new Intent(BaseActivity.PLAYER_PLAYING);
                break;
        }
        sendBroadcast(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            musicAidlStub.release();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicAidlStub;
    }

}
