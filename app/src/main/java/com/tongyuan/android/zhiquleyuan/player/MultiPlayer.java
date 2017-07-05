package com.tongyuan.android.zhiquleyuan.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by zgg on 2017/6/7.
 */

class MultiPlayer implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {
    private static final String TAG = "MultiPlayer";
    private static final boolean D = false;
    private final WeakReference<MusicPlayerService> mService;
    private MediaPlayer mCurrentMediaPlayer = new MediaPlayer();
    private Context context;

    private boolean isPrepared = false;
    private String playUrl;
    private HashMap<String, String> urlMap = new HashMap<>();
    private boolean openAndStart = false;

    MultiPlayer(Context context, MusicPlayerService service) {
        this.context = context;
        mService = new WeakReference<>(service);
    }

    void openMediaPlayer(String musicId) {
        openAndStart = false;
        if(urlMap.containsKey(musicId)) {
            String url = urlMap.get(musicId);
            if(url.equals(playUrl)) {
                mService.get().sendBroadCastToReceiver(MusicPlayerService.CODE_playing);
            } else {
                setDataSource(url);
            }
        } else {
            getPlayUrlByMusicID(context, musicId);
        }
    }

    void openAndStart(String musicId) {
        openAndStart = true;
        if(urlMap.containsKey(musicId)) {
            String url = urlMap.get(musicId);
            if(url.equals(playUrl)) {
                if(!isPlaying())
                    start();
                mService.get().sendBroadCastToReceiver(MusicPlayerService.CODE_playing);
            } else {
                setDataSource(url);
            }
        } else {
            getPlayUrlByMusicID(context, musicId);
        }
    }

    private void setDataSource(String url) {
        playUrl = url;
        isPrepared = false;
        mCurrentMediaPlayer.reset();
        try {
            mCurrentMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mCurrentMediaPlayer.setDataSource(playUrl);
            mCurrentMediaPlayer.setOnPreparedListener(this);
            mCurrentMediaPlayer.setOnBufferingUpdateListener(this);
            mCurrentMediaPlayer.setOnErrorListener(this);
            mCurrentMediaPlayer.setOnCompletionListener(this);
            mCurrentMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPlayUrlByMusicID(final Context context, final String musicId) {
        LocalPlayApplyReqBean.BODYBean bodyBean1 = new LocalPlayApplyReqBean.BODYBean(musicId);
        BaseRequest baseRequest = new BaseRequest(context, bodyBean1, "PLAY");
        Call<SuperModel<LocalPlayApplyResBean>> localPlayApplyResBeanCall = RequestManager.getInstance().requestMusicDetail(baseRequest);

        localPlayApplyResBeanCall.enqueue(new Callback<SuperModel<LocalPlayApplyResBean>>() {
            @Override
            public void onResponse(Call<SuperModel<LocalPlayApplyResBean>> call, Response<SuperModel<LocalPlayApplyResBean>> response) {
                if("0".equals(response.body().CODE)){
                    LocalPlayApplyResBean bean = response.body().BODY;
                    setDataSource(bean.getURL());
                    urlMap.put(musicId, bean.getURL());
                } else {
                    ToastUtil.showToast(context, response.body().MSG);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<LocalPlayApplyResBean>> call, Throwable t) {
                ToastUtil.showToast(context, "请求播放地址失败！！！");
            }
        });
    }

    void start() {
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

    boolean isPrepared() {
        return isPrepared;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        playUrl = null;
        isPrepared = false;
        mService.get().sendBroadCastToReceiver(MusicPlayerService.CODE_complete);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mService.get().sendBroadCastToReceiver(MusicPlayerService.CODE_error);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPrepared = true;
        if(openAndStart)
            start();
        mService.get().sendBroadCastToReceiver(MusicPlayerService.CODE_prepared);
    }

    public void release(){
        urlMap.clear();
        urlMap = null;
    }


}
