package com.tongyuan.android.zhiquleyuan.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tongyuan.android.zhiquleyuan.interf.OnMediaChangeListener;

import java.util.HashSet;

/**
 * 播放服务端
 * Created by android on 2017/3/13.
 */

public class MusicPlayerService extends Service {
    MediaPlayer mMediaPlayer;
    MediaBinder mMediaBinder;
    HashSet<OnMediaChangeListener> mOnMediaChangeListeners;

    @Override
    public void onCreate() {
        super.onCreate();

        mMediaBinder = new MediaBinder();
        mOnMediaChangeListeners = new HashSet<OnMediaChangeListener>();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mOnMediaChangeListeners.clear();
        mMediaPlayer.stop();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMediaBinder;
    }




    //通过 binder来连接activity和service
    public class MediaBinder extends Binder {
        public void addMediaChangeListener(OnMediaChangeListener listener) {
            if (listener != null) {
                mOnMediaChangeListeners.add(listener);
            }
        }

        public void removeMediaChangeListener(OnMediaChangeListener listener) {
            if (listener != null) {
                mOnMediaChangeListeners.remove(listener);
            }
        }

        public void play() {
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer.create(MusicPlayerService.this, Uri.parse(""));
            }
            notifyAllPlay();
        }

        public void pause() {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
            }
            notifyAllPause();
        }

        public void stop() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
            }
            notifyAllStop();
        }

        public boolean isPlaying() {
            if (mMediaPlayer != null) {
                return mMediaBinder.isPlaying();
            }
            return false;
        }

        public int getDuration() {
            if (mMediaPlayer != null) {
                mMediaPlayer.getDuration();
            }
            return -1;
        }

        public int getCurrentPosition() {
            if (mMediaPlayer != null) {
                mMediaPlayer.getCurrentPosition();
            }
            return -1;
        }

        public void seek(int sec) {
            if (mMediaPlayer != null) {
                mMediaPlayer.seekTo(sec);

            }
        }

    }

    private void notifyAllStop() {

    }

    private void notifyAllPause() {

    }

    private void notifyAllPlay() {

    }

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            notifyAllCompletion();
        }
    };

    private void notifyAllCompletion() {

    }
}
