package com.tongyuan.android.zhiquleyuan.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tongyuan.android.zhiquleyuan.base.BaseActivity;
import com.tongyuan.android.zhiquleyuan.player.MusicAidlStub;

/**
 * 播放服务端
 * Created by android on 2017/3/13.
 */

public class MusicPlayerService extends Service {
//    MediaPlayer mMediaPlayer;
    private MusicAidlStub musicAidlStub ;
    private NotificationManager mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        musicAidlStub = new MusicAidlStub(this);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    public void sendPreparedNotify() {
        Intent intent = new Intent(BaseActivity.PLAYER_PREPARED);
        sendBroadcast(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicAidlStub;
    }

}
