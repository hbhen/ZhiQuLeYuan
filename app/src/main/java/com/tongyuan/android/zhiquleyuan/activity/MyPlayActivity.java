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
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.utils.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2016/12/23.
 */

public class MyPlayActivity extends AppCompatActivity implements View.OnClickListener {

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
    private TextView phoneplay;
    private TextView toyplay;
    //private DiscView mDiscView;
    //boolean isPlaying=isPlaying();
    private TextView mTextView_start;
    private final String TAG = "play";
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
    private String mMusicImg;
    private String mMusicId;
    private String mMusicName;
    private String mMusicDur;
    private String mUrl;
    private String mFormatData;
    private String mPhoneNum;
    private String mToken;


    @Override
    protected void onCreate(@Nullable Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_playing_details);
//        Log.i(TAG, "onCreate: saveInstance"+saveInstance.toString());
        mTextView_start = (TextView) findViewById(R.id.tv_activity_playing_details_start);
        mSeekBar = (SeekBar) findViewById(R.id.sb_playing_details);
        mImageView = (ImageView) findViewById(R.id.iv_playing_details_slide);
        mPlayImageView = (ImageView) findViewById(R.id.iv_activity_playing_details_play);
        mTextView_playname = (TextView) findViewById(R.id.tv_activity_playing_details_playname);
        mTextView_duration = (TextView) findViewById(R.id.tv_activity_playing_details_duration);
        phoneplay = (TextView) findViewById(R.id.tv_activity_playing_details_phoneplay);
        toyplay = (TextView) findViewById(R.id.tv_activity_playing_details_toyplay);

        toyplay.setOnClickListener(this);
        phoneplay.setOnClickListener(this);

        mFormatData = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        mToken = SPUtils.getString(this, "TOKEN", "");
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey("recommandlistbean")) {
//            从推荐资源传过来的intent
            DiscoveryListResultBean.BODYBean.LSTBean recommandlistbean = bundle.getParcelable("recommandlistbean");
            mMusicImg = recommandlistbean.getIMG();
            mMusicId = recommandlistbean.getID(); //拿这个colid去请求本机播放申请
            mMusicName = recommandlistbean.getNAME();
            mMusicDur = recommandlistbean.getDUR();

        } else if (bundle != null && bundle.containsKey("secondcategorylistbean")) {
            //从DiscoveryFragment传过来的intent
            DiscoveryGridSecondaryResultBean.BODYBean.LSTBean secondcategorylistbean = bundle.getParcelable("secondcategorylistbean");
            mMusicImg = secondcategorylistbean.getIMG();
            mMusicId = secondcategorylistbean.getID();//拿这个colid去请求本机播放申请
            mMusicName = secondcategorylistbean.getNAME();
            mMusicDur = secondcategorylistbean.getDUR();
            Log.i(TAG, "recommandid:secondcategorylistbean " + mMusicId);
        } else if (bundle != null && bundle.containsKey("toydetailsrecommandlistbean")) {
            //从toydetailsfragment传过来的bundle
            DiscoveryListResultBean.BODYBean.LSTBean toydetailslistbean = bundle.getParcelable("toydetailsrecommandlistbean");
            mMusicImg = toydetailslistbean.getIMG();
            mMusicId = toydetailslistbean.getID();//拿这个colid去请求本机播放申请
            mMusicName = toydetailslistbean.getNAME();
            mMusicDur = toydetailslistbean.getDUR();
            Log.i(TAG, "recommandid:toydetailsrecommandlistbean " + mMusicId);
        }
        getLocalPlayApplication(mMusicId, mPhoneNum, mFormatData, mToken);
        mMediaPlayer = new MediaPlayer();


        Glide.with(this).load(mMusicImg).asBitmap().centerCrop().into(new BitmapImageViewTarget(mImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(),
                        resource);
                roundedBitmapDrawable.setCircular(true);
                mImageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
//        }
        mTextView_playname.setText(mMusicName);
        mTextView_duration.setText(mMusicDur);
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

    private void getLocalPlayApplication(String musicId, String phoneNum, String formatTime, String token) {

        Retrofit retrofit2 = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface1 = retrofit2.create(AllInterface.class);
        Gson gson1 = new Gson();
        LocalPlayApplyReqBean.BODYBean bodyBean1 = new LocalPlayApplyReqBean.BODYBean(musicId);
        LocalPlayApplyReqBean localPlayApplyReqBean = new LocalPlayApplyReqBean("REQ", "PLAY", phoneNum, formatTime, bodyBean1,
                "", token, "1");
        String s1 = gson1.toJson(localPlayApplyReqBean);
        Call<LocalPlayApplyResBean> localPlayApplyResBeanCall = allInterface1.LOCAL_PLAY_APPLY_RES_BEAN_CALL(s1);
        localPlayApplyResBeanCall.enqueue(new Callback<LocalPlayApplyResBean>() {
            @Override
            public void onResponse(Call<LocalPlayApplyResBean> call, Response<LocalPlayApplyResBean> response) {
                if (response.body().getCODE().equals("-700")) {
                    ToastUtil.showToast(getApplicationContext(), "资源不存在");
                    return;
                } else if (response.body().getCODE().equals("0")) {
                    mUrl = response.body().getBODY().getURL();
                    Log.i(TAG, "onResponse: murl" + mUrl.toString());

                }
            }

            @Override
            public void onFailure(Call<LocalPlayApplyResBean> call, Throwable t) {

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
            Log.i(TAG, "playMusic: murl" + mUrl.toString());
            mMediaPlayer.setDataSource(this, Uri.parse(mUrl));
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (mSeekBar != null)
                        mSeekBar.setMax(mp.getDuration());
                    long duration = mp.getDuration();
                    Log.i("111111", duration + "ms");
                    if (duration != -1)
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_activity_playing_details_phoneplay:
                //第一次 ,进入myplay界面,肯定显示的是phoneplay的按钮被选中
                ToastUtil.showToast(this,"手机");
                break;
            case R.id.tv_activity_playing_details_toyplay:
                //推送当前正在播放的或者当前的歌曲给玩具,如果没有播放(后台的接口写的是传玩具ID和资源ID),所以,播不播放都可以推送,
                //1,能推送,那能不能取消推送呢?
                //2,推送到玩具以后,应该跳转的界面是哪个?
                pushMusicToToy();
                ToastUtil.showToast(this,"玩具");
                break;
        }
    }

    private void pushMusicToToy() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        System.out.println("mtoyid"+ToySelectorFragment.mToyid);
//        ControlToyPlayMusicReqBean.ParamBean paramBean = new ControlToyPlayMusicReqBean.ParamBean(ToySelectorFragment.mToyid, "1", mMusicId,
//                "0");
//        ControlToyPlayMusicReqBean control_play = new ControlToyPlayMusicReqBean("control_play", paramBean, mToken);
        ControlToyPlayMusicReqBean.ParamBean paramBean = new ControlToyPlayMusicReqBean.ParamBean("201705081005211016644025", "1", "201705151824191016803202",
                "0");
        ControlToyPlayMusicReqBean control_play = new ControlToyPlayMusicReqBean("control_play", paramBean, mToken);
        Gson gson=new Gson();
        String s = gson.toJson(control_play);
        System.out.println("ssssssss"+s);
        Call<ControlToyPlayMusicResBean> controlToyPlayMusicResBeanCall = allInterface.CONTROL_TOY_PLAY_MUSIC_RES_BEAN_CALL(s);
        controlToyPlayMusicResBeanCall.enqueue(new Callback<ControlToyPlayMusicResBean>() {
            @Override
            public void onResponse(Call<ControlToyPlayMusicResBean> call, Response<ControlToyPlayMusicResBean> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<ControlToyPlayMusicResBean> call, Throwable t) {

            }
        });

    }
}
