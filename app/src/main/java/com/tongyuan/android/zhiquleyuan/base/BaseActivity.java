package com.tongyuan.android.zhiquleyuan.base;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tongyuan.android.zhiquleyuan.player.MusicPlayer;

import java.lang.ref.WeakReference;

/**
 * Activity的基类
 * Created by zgg on 2017/6/3.
 */

public abstract class BaseActivity extends AppCompatActivity implements ServiceConnection {

    public static final String PLAYER_PREPARED = "toy.player.prepared";
    public static final String PLAYER_ERROR    = "toy.player.error";
    public static final String PLAYER_COMPLETE = "toy.player.complete";

    protected MusicPlayer.ServiceToken mToken;
    private PlaybackStatus mPlaybackStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("gengen", "baseActivity onCreate");
        mToken = MusicPlayer.bindToService(this, this);
        mPlaybackStatus = new PlaybackStatus(this);
        IntentFilter f = new IntentFilter();
        f.addAction(PLAYER_PREPARED);
        f.addAction(PLAYER_ERROR);
        f.addAction(PLAYER_COMPLETE);
        registerReceiver(mPlaybackStatus, f);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicPlayer.unBindFromService(mToken);
        unregisterReceiver(mPlaybackStatus);
    }

    protected abstract void onPrepared();

    protected abstract void onError();

    protected abstract void onCompleted();

    private final static class PlaybackStatus extends BroadcastReceiver {

        private final WeakReference<BaseActivity> mReference;
        public PlaybackStatus(final BaseActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            BaseActivity baseActivity = mReference.get();
            if (baseActivity == null)
                return;
            if (action.equals(PLAYER_PREPARED)) {
                baseActivity.onPrepared();
            } else if(action.equals(PLAYER_ERROR)){
                baseActivity.onError();
            } else if(action.equals(PLAYER_COMPLETE)) {
                baseActivity.onCompleted();
            }
        }
    }
}
