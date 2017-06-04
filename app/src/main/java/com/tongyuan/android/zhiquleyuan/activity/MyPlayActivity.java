package com.tongyuan.android.zhiquleyuan.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.base.BaseActivity;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
import com.tongyuan.android.zhiquleyuan.utils.Util;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * Created by android on 2016/12/23.
 */

public class MyPlayActivity extends BaseActivity {

    @Bind(R.id.player_details_name)
    TextView titleTextView;
    @Bind(R.id.player_collection)
    TextView collectView;
    @Bind(R.id.player_progress)
    ProgressBar progressBar;
    @Bind(R.id.player_start_time)
    TextView startTimeView;
    @Bind(R.id.player_duration_time)
    TextView durationView;
    @Bind(R.id.player_play_btn)
    ImageView mPlayBtn;
    @Bind(R.id.iv_playing_details_slide)
    ImageView mImageView;

    //更新播放进度的显示
    private static final int UPDATE_PLAY_PROGRESS_SHOW = 1;

    private MediaPlayer mMediaPlayer;
    boolean pause = true;
    private ObjectAnimator mRotateObjectAnimator;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PLAY_PROGRESS_SHOW:
                    //一收到msg消息就走更新播放进度的方法
                    updatePlayProgressShow();
                    break;
            }
        }
    };

    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_details);
        ButterKnife.bind(this);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));

        mMediaPlayer = new MediaPlayer();
        Bundle extras = getIntent().getExtras();
        final Intent intent = getIntent();
        LocalPlayApplyResBean musicinfo = extras.getParcelable("musicinfo");
        String musicimg = intent.getExtras().getString("musicimg");
        String musicname = intent.getExtras().getString("musicname");
        titleTextView.setText(musicname);
        mUrl = musicinfo.getURL();
        Glide.with(this).load(musicimg).asBitmap().centerCrop().into(new BitmapImageViewTarget(mImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                mImageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
        mMediaPlayer = MediaPlayer.create(this, R.raw.bumie);

        mRotateObjectAnimator = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360);
        mRotateObjectAnimator.setDuration(20000);
        mRotateObjectAnimator.setInterpolator(new LinearInterpolator());
        mRotateObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
    }

    @OnClick({R.id.player_back, R.id.player_phone, R.id.player_toy, R.id.player_collection,
            R.id.player_play_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.player_back:
                finish();
                break;
            case R.id.player_phone:

                break;
            case R.id.player_toy:

                break;
            case R.id.player_collection:

                break;
            case R.id.player_play_btn:
                playOrPause();
                break;
        }
    }

    private void playOrPause() {
        if (pause && !mMediaPlayer.isPlaying()) {
            Intent intent1 = new Intent();
            //网络播放应在子线程中
            intent1.putExtra("musicurl", mUrl);
            intent1.putExtra("playstate", 1);//播放状态
            intent1.setClass(getApplicationContext(), MusicPlayerService.class);
            startService(intent1);
            playMusic();
            mRotateObjectAnimator.start();
            pause = false;
            updatePlayProgressShow();
        } else {
            pauseMusic();
            mRotateObjectAnimator.cancel();
            pause = true;
        }
    }

    //更新播放进度的显示
    private void updatePlayProgressShow() {
        int currentPosition = mMediaPlayer.getCurrentPosition();
        startTimeView.setText(Util.formatMillis(currentPosition));

        progressBar.setProgress(currentPosition);
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
        mHandler.sendEmptyMessageDelayed(UPDATE_PLAY_PROGRESS_SHOW, 1000);
    }


    private void playMusic() {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(this, Uri.parse(mUrl));
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if(progressBar != null)
                        progressBar.setMax(mp.getDuration());
                    int duration = mp.getDuration();
                    if(duration != -1)
                        durationView.setText(Util.formatMillis(duration));
                }
            });
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.start();
        mPlayBtn.setImageResource(R.drawable.play_stop_pressed240);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mImageView.clearAnimation();
            }
        });

    }

    private void pauseMusic() {
        mMediaPlayer.pause();
        mPlayBtn.setImageResource(R.drawable.play_pressed_240);
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
