package com.tongyuan.android.zhiquleyuan.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.tongyuan.android.zhiquleyuan.base.BaseActivity;
import com.tongyuan.android.zhiquleyuan.player.MusicAidlStub;

/**
 * 播放服务端
 * Created by android on 2017/3/13.
 */

public class MusicPlayerService extends Service {
    private static MusicAidlStub musicAidlStub ;
    //private NotificationManager mNotificationManager;
    public static final int CODE_prepared = 1;
    public static final int CODE_error    = 2;
    public static final int CODE_complete = 3;
    public static final int CODE_playing  = 4;

    @Override
    public void onCreate() {
        super.onCreate();
        musicAidlStub = new MusicAidlStub(this, this);
        //mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

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

    public static void launchService(Context mContext , String musicId) {
        Intent it = new Intent(mContext, MusicPlayerService.class);
        it.putExtra("musicId", musicId);
        mContext.startService(it);
    }

    public static void stopService(Context mContext , boolean isStop) {
        Intent it = new Intent(mContext, MusicPlayerService.class);
        it.putExtra("isStop", isStop);
        mContext.startService(it);
    }

    public static boolean isPlayUrl(String musicId) {
        try {
            return musicAidlStub.isPlayUrl(musicId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null)
            return super.onStartCommand(intent, flags, startId);
        String musicId = intent.getStringExtra("musicId");
        boolean isStop = intent.getBooleanExtra("isStop", false);
        if(musicId != null) {
            try {
                musicAidlStub.openAndStart(musicId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if(isStop) {
            try {
                musicAidlStub.stop();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
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
