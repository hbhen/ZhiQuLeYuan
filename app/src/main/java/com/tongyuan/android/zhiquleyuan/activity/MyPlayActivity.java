package com.tongyuan.android.zhiquleyuan.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;
import com.tongyuan.android.zhiquleyuan.utils.Util;

import java.io.IOException;

/**
 * Created by android on 2016/12/23.
 */

public class MyPlayActivity extends AppCompatActivity {

    //更新播放进度的显示
    private static final int UPDATE_PLAY_PROGRESSS_SHOW = 1;
    private ImageView mImageView;
    private TextView mCollection;
    private SeekBar mSeekBar;
    private ImageView mPlayImageView;
    private Animation mOperationAnim;
    private MediaPlayer mMediaPlayer;
    private RotateAnimation mRotateAnimation;
    boolean pause = true;
    private ObjectAnimator mRotateObjectAnimator;
    private TextView mTextView_duration;
    private TextView mTextView_playname;
    //private DiscView mDiscView;
    //boolean isPlaying=isPlaying();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PLAY_PROGRESSS_SHOW:
                    //一收到msg消息就走更新播放进度的方法
                    updatePlayProgressShow();
                    break;
            }
        }
    };
    private TextView mTextView_start;
    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_details);
        mMediaPlayer = new MediaPlayer();
        mTextView_start = (TextView) findViewById(R.id.tv_activity_playing_details_start);
        mSeekBar = (SeekBar) findViewById(R.id.sb_playing_details);
        mImageView = (ImageView) findViewById(R.id.iv_playing_details_slide);
        mPlayImageView = (ImageView) findViewById(R.id.iv_activity_playing_details_play);
        mTextView_playname = (TextView) findViewById(R.id.tv_activity_playing_details_playname);
        mTextView_duration = (TextView) findViewById(R.id.tv_activity_playing_details_duration);
        Bundle extras = getIntent().getExtras();
        final Intent intent = getIntent();
        LocalPlayApplyResBean musicinfo = extras.getParcelable("musicinfo");
        String musicimg = intent.getExtras().getString("musicimg");
        String musicname = intent.getExtras().getString("musicname");
        mUrl = musicinfo.getBODY().getURL();
        String dur = musicinfo.getBODY().getDUR();//后台返回的值是 空
        Glide.with(this).load(musicimg).asBitmap().centerCrop().into(new BitmapImageViewTarget(mImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                mImageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
        //格式化时间
//        mTextView_duration.setText(Util.formatMillis(mMediaPlayer.getDuration()));
        updatePlayProgressShow();
        mMediaPlayer = MediaPlayer.create(this, R.raw.bumie);

        mRotateObjectAnimator = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360);
        mRotateObjectAnimator.setDuration(20000);
        mRotateObjectAnimator.setInterpolator(new LinearInterpolator());
        mRotateObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        视频进度改变的监听器
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {

                    mMediaPlayer.seekTo(progress);//实现跳转

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                isTrackingTouch=false;

            }

        });
        MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        };

        mPlayImageView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

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
                } else {
                    pauseMusic();
                    mRotateObjectAnimator.cancel();
                    pause = true;
                }
            }
        });
    }

    //更新播放进度的显示
    private void updatePlayProgressShow() {
        int currentPosition = mMediaPlayer.getCurrentPosition();
        mTextView_start.setText(Util.formatMillis(currentPosition));


        mSeekBar.setProgress(currentPosition);
        mHandler.sendEmptyMessageDelayed(UPDATE_PLAY_PROGRESSS_SHOW, 30);
    }


    private void playMusic() {

        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(this, Uri.parse(mUrl));
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if(mSeekBar != null)
                        mSeekBar.setMax(mp.getDuration());
                    long duration = mp.getDuration();
                    Log.i("111111", duration+"ms");
                    if(duration != -1)
                        mTextView_duration.setText(Util.formatMillis(mp.getDuration()));
                }
            });
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.start();
        mPlayImageView.setImageResource(R.drawable.play_stop_pressed240);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("111111", "onCompletion");
                mp.stop();
                mImageView.clearAnimation();
            }
        });

//        mOperationAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
//        LinearInterpolator linearInterpolator = new LinearInterpolator();
//        mOperationAnim.setInterpolator(linearInterpolator);
//        Log.i("1", "playMusic: " + mOperationAnim);
//
//        if (mOperationAnim != null) {
//            mImageView.startAnimation(mOperationAnim);
//        }
//
//        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mp.stop();
//                mImageView.clearAnimation();
//            }
//        });
    }

    private void pauseMusic() {
        mMediaPlayer.pause();
        mPlayImageView.setImageResource(R.drawable.play_pressed_240);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
