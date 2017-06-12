package com.tongyuan.android.zhiquleyuan.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;

import java.io.IOException;

/**
 *
 * Created by zgg on 2017/6/7.
 */

class MultiPlayer implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener{
    private static final String TAG = "MultiPlayer";
    private static final boolean D = false;
    private MediaPlayer mMediaPlayer;

    public MultiPlayer() {
//        mService = new WeakReference<MusicPlayerService>(service);
    }

    public void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

//    private MediaPlayer mCurrentMediaPlayer = new MediaPlayer();
//    private MediaPlayer mNextMediaPlayer;
//
//    private boolean isFirstLoad = true;
//    private boolean mIsInitialized = false;
//
//    private String mNextMediaPath;
//    private int sencondaryPosition = 0;
//
//    private Handler mHandler;
//    private Handler handler = new Handler();
//
//    private boolean mIsTrackPrepared = false;
//    private boolean mIsTrackNet = false;
//    private boolean mIsNextTrackPrepared = false;
//    private boolean mIsNextInitialized = false;
//    private boolean mIllegalState = false;
//
//    public void setDataSource(final String path) {
//        mIsInitialized = setDataSourceImpl(mCurrentMediaPlayer, path);
//        if (mIsInitialized) {
//            setNextDataSource(null);
//        }
//    }
//
//    private void setNextDataSource(final String path) {
//        mNextMediaPath = null;
//        mIsNextInitialized = false;
//        try {
//            mCurrentMediaPlayer.setNextMediaPlayer(null);
//        } catch (IllegalArgumentException e) {
//            Log.i(TAG, "Next media player is current one, continuing");
//        } catch (IllegalStateException e) {
//            Log.e(TAG, "Media player not initialized!");
//            return;
//        }
//        if (mNextMediaPlayer != null) {
//            mNextMediaPlayer.release();
//            mNextMediaPlayer = null;
//        }
//        if (path == null) {
//            return;
//        }
//        mNextMediaPlayer = new MediaPlayer();
////        mNextMediaPlayer.setWakeMode(mService.get(), PowerManager.PARTIAL_WAKE_LOCK);
//        mNextMediaPlayer.setAudioSessionId(getAudioSessionId());
//
//        if (setNextDataSourceImpl(mNextMediaPlayer, path)) {
//            mNextMediaPath = path;
//            mCurrentMediaPlayer.setNextMediaPlayer(mNextMediaPlayer);
//            // mHandler.post(setNextMediaPlayerIfPrepared);
//
//        } else {
//            if (mNextMediaPlayer != null) {
//                mNextMediaPlayer.release();
//                mNextMediaPlayer = null;
//            }
//        }
//    }
//
//    private boolean setDataSourceImpl(final MediaPlayer player, final String path) {
//        mIsTrackNet = false;
//        mIsTrackPrepared = false;
//        try {
//            player.reset();
//            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            if (path.startsWith("content://")) {
//                player.setOnPreparedListener(null);
//                player.setDataSource(MainApplication.context, Uri.parse(path));
//                player.prepare();
//                mIsTrackPrepared = true;
//                player.setOnCompletionListener(this);
//
//            } else {
//                player.setDataSource(path);
//                player.setOnPreparedListener(preparedListener);
//                player.prepareAsync();
//                mIsTrackNet = true;
//            }
//            if(mIllegalState){
//                mIllegalState = false;
//            }
//
//        } catch (final IOException todo) {
//            return false;
//        } catch (final IllegalArgumentException todo) {
//            return false;
//        } catch (final IllegalStateException todo){
//            todo.printStackTrace();
//            if(!mIllegalState){
//                if (D) Log.e(TAG, "current mediaPlayer invoke IllegalState");
//                mCurrentMediaPlayer = null;
//                mCurrentMediaPlayer = new MediaPlayer();
//                mCurrentMediaPlayer.setWakeMode(mService.get(), PowerManager.PARTIAL_WAKE_LOCK);
//                mCurrentMediaPlayer.setAudioSessionId(getAudioSessionId());
//                setDataSourceImpl(mCurrentMediaPlayer,path);
//                mIllegalState = true;
//            }else {
//                if (D) Log.e(TAG, "current mediaPlayer invoke IllegalState ,and try set again failed ,setNext");
//                mIllegalState = false;
//                return false;
//            }
//        }
//
//        player.setOnErrorListener(this);
//        player.setOnBufferingUpdateListener(bufferingUpdateListener);
//        return true;
//    }
//
//    private boolean setNextDataSourceImpl(final MediaPlayer player, final String path) {
//
//        mIsNextTrackPrepared = false;
//        try {
//            player.reset();
//            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            if (path.startsWith("content://")) {
//                player.setOnPreparedListener(preparedNextListener);
//                player.setDataSource(MainApplication.context, Uri.parse(path));
//                player.prepare();
//
//
//            } else {
//                player.setDataSource(path);
//                player.setOnPreparedListener(preparedNextListener);
//                player.prepare();
//                mIsNextTrackPrepared = false;
//            }
//
//        } catch (final IOException todo) {
//
//            return false;
//        } catch (final IllegalArgumentException todo) {
//
//            return false;
//        }
//        player.setOnCompletionListener(this);
//        player.setOnErrorListener(this);
//        //  player.setOnBufferingUpdateListener(this);
//        return true;
//    }
//
//    public void setHandler(final Handler handler) {
//        mHandler = handler;
//    }
//
//
//    public boolean isInitialized() {
//        return mIsInitialized;
//    }
//
//    public boolean isTrackPrepared() {
//        return mIsTrackPrepared;
//    }
//
//
//    public void start() {
//        if (D) Log.d(TAG, "mIsTrackNet, " + mIsTrackNet);
//        if (!mIsTrackNet) {
//            mService.get().sendUpdateBuffer(100);
//            sencondaryPosition = 100;
//            mCurrentMediaPlayer.start();
//        } else {
//            sencondaryPosition = 0;
//            mService.get().loading(true);
//            handler.postDelayed(startMediaPlayerIfPrepared, 50);
//        }
//        mService.get().notifyChange(MUSIC_CHANGED);
//    }
//
//
//
//    private Runnable setNextMediaPlayerIfPrepared = new Runnable() {
//        int count = 0;
//
//        @Override
//        public void run() {
//            if (mIsNextTrackPrepared && mIsInitialized) {
//
////                    mCurrentMediaPlayer.setNextMediaPlayer(mNextMediaPlayer);
//            } else if (count < 60) {
//                handler.postDelayed(setNextMediaPlayerIfPrepared, 100);
//            }
//            count++;
//        }
//    };
//
//    private Runnable startMediaPlayerIfPrepared = new Runnable() {
//
//        @Override
//        public void run() {
//            if (D) Log.d(TAG, "mIsTrackPrepared, " + mIsTrackPrepared);
//            if (mIsTrackPrepared) {
//                mCurrentMediaPlayer.start();
//                final long duration = duration();
//                if (mService.get().mRepeatMode != REPEAT_CURRENT && duration > 2000
//                        && position() >= duration - 2000) {
//                    mService.get().gotoNext(true);
//                    Log.e("play to go", "");
//                }
//                mService.get().loading(false);
//            } else {
//                handler.postDelayed(startMediaPlayerIfPrepared, 700);
//            }
//        }
//    };
//
//
//    public void stop() {
//        handler.removeCallbacks(setNextMediaPlayerIfPrepared);
//        handler.removeCallbacks(startMediaPlayerIfPrepared);
//        mCurrentMediaPlayer.reset();
//        mIsInitialized = false;
//        mIsTrackPrepared = false;
//    }
//
//
//    public void release() {
//        mCurrentMediaPlayer.release();
//    }
//
//
//    public void pause() {
//        handler.removeCallbacks(startMediaPlayerIfPrepared);
//        mCurrentMediaPlayer.pause();
//    }
//
//
//    public long duration() {
//        if (mIsTrackPrepared) {
//            return mCurrentMediaPlayer.getDuration();
//        }
//        return -1;
//    }
//
//
//    public long position() {
//        if (mIsTrackPrepared) {
//            try {
//                return mCurrentMediaPlayer.getCurrentPosition();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return -1;
//    }
//
//    public long secondPosition() {
//        if (mIsTrackPrepared) {
//            return sencondaryPosition;
//        }
//        return -1;
//    }
//
//
//    public long seek(final long whereto) {
//        mCurrentMediaPlayer.seekTo((int) whereto);
//        return whereto;
//    }
//
//
//    public void setVolume(final float vol) {
//        try {
//            mCurrentMediaPlayer.setVolume(vol, vol);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public int getAudioSessionId() {
//        return mCurrentMediaPlayer.getAudioSessionId();
//    }
//
//    public void setAudioSessionId(final int sessionId) {
//        mCurrentMediaPlayer.setAudioSessionId(sessionId);
//    }
//
//
//    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//            if(isFirstLoad){
//                long seekpos = mService.get().mLastSeekPos;
//                Log.e(TAG,"seekpos = " + seekpos);
//                seek(seekpos >= 0 ? seekpos : 0);
//                isFirstLoad = false;
//            }
//            // mService.get().notifyChange(TRACK_PREPARED);
//            mService.get().notifyChange(META_CHANGED);
//            mp.setOnCompletionListener(MultiPlayer.this);
//            mIsTrackPrepared = true;
//        }
//    };
//
//    private MediaPlayer.OnPreparedListener preparedNextListener = new MediaPlayer.OnPreparedListener() {
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//            mIsNextTrackPrepared = true;
//        }
//    };
//
//    private MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
//
//        @Override
//        public void onBufferingUpdate(MediaPlayer mp, int percent) {
//            if (sencondaryPosition != 100)
//                mService.get().sendUpdateBuffer(percent);
//            sencondaryPosition = percent;
//        }
//    };
//
//    @Override
//    public boolean onError(final MediaPlayer mp, final int what, final int extra) {
//        Log.w(TAG, "Music Server Error what: " + what + " extra: " + extra);
//        switch (what) {
//            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
//                final MediaService service = mService.get();
//                final TrackErrorInfo errorInfo = new TrackErrorInfo(service.getAudioId(),
//                        service.getTrackName());
//
//                mIsInitialized = false;
//                mIsTrackPrepared = false;
//                mCurrentMediaPlayer.release();
//                mCurrentMediaPlayer = new MediaPlayer();
//                mCurrentMediaPlayer.setWakeMode(service, PowerManager.PARTIAL_WAKE_LOCK);
//                Message msg = mHandler.obtainMessage(SERVER_DIED, errorInfo);
//                mHandler.sendMessageDelayed(msg, 2000);
//                return true;
//            default:
//                break;
//        }
//        return false;
//    }
//
//    @Override
//    public void onCompletion(final MediaPlayer mp) {
//        if (D)Log.w(TAG, "completion");
//        if (mp == mCurrentMediaPlayer && mNextMediaPlayer != null) {
//            mCurrentMediaPlayer.release();
//            mCurrentMediaPlayer = mNextMediaPlayer;
//            mNextMediaPath = null;
//            mNextMediaPlayer = null;
//            mHandler.sendEmptyMessage(TRACK_WENT_TO_NEXT);
//        } else {
//            mService.get().mWakeLock.acquire(30000);
//            mHandler.sendEmptyMessage(TRACK_ENDED);
//            mHandler.sendEmptyMessage(RELEASE_WAKELOCK);
//        }
//    }

}
