package com.tongyuan.android.zhiquleyuan.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 *
 * Created by zgg on 2017/6/7.
 */

class MultiPlayer implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {
    private static final String TAG = "MultiPlayer";
    private static final boolean D = false;
    //    private MediaPlayer mMediaPlayer;
    private final WeakReference<MusicPlayerService> mService;
    private MediaPlayer mCurrentMediaPlayer = new MediaPlayer();
    private MediaPlayer mNextMediaPlayer;

    private boolean isPrepared = false;
    private String playUrl;
    //private MediaPlayer mNextMediaPlayer;

    MultiPlayer(MusicPlayerService service) {
        mService = new WeakReference<MusicPlayerService>(service);
    }

    void setDataSource(String path) {
        //第一次进来
        //第二次进来，正在播放前一个音乐，或者暂停
        Log.e("gengen", "setDataSource..");
        mCurrentMediaPlayer.reset();
        try {
            mCurrentMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mCurrentMediaPlayer.setDataSource(path);
            mCurrentMediaPlayer.setOnPreparedListener(this);
            mCurrentMediaPlayer.setOnBufferingUpdateListener(this);
            mCurrentMediaPlayer.setOnErrorListener(this);
            mCurrentMediaPlayer.setOnCompletionListener(this);
            mCurrentMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        Log.e("gengen", "start..");
        if (!isPrepared)
            return;
        if (!mCurrentMediaPlayer.isPlaying())
            mCurrentMediaPlayer.start();
    }

    void pause() {
        if (!isPrepared)
            return;
        if (mCurrentMediaPlayer.isPlaying())
            mCurrentMediaPlayer.pause();
    }

    void stop() {
        if (isPrepared)
            mCurrentMediaPlayer.stop();
    }

    void setLooping(boolean looping) {
        mCurrentMediaPlayer.setLooping(looping);
    }

    boolean isPlaying() {
        return mCurrentMediaPlayer.isPlaying();
    }

    int getCurrentPosition() {
        return mCurrentMediaPlayer.getCurrentPosition();
    }

    int getDuration() {
        return mCurrentMediaPlayer.getDuration();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e("gengen", "onError");
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("gengen", "onPrepared...");
        isPrepared = true;
//        start();
        mService.get().sendPreparedNotify();
    }


}
