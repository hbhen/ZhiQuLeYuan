package com.tongyuan.android.zhiquleyuan.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.base.BaseActivity;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.player.MusicPlayer;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
import com.tongyuan.android.zhiquleyuan.utils.Util;
import com.tongyuan.android.zhiquleyuan.view.IconTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by android on 2016/12/23.
 */
public class MyPlayActivity extends BaseActivity {

    @Bind(R.id.player_phone)
    IconTextView phoneView;
    @Bind(R.id.player_toy)
    IconTextView toyView;
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
    @Bind(R.id.player_lighting)
    ImageView lightingView;

    //更新播放进度的显示
    private static final int UPDATE_PLAY_PROGRESS_SHOW = 1;

    private ObjectAnimator mRotateObjectAnimator;
    private boolean isLooping = false;

    private final static String TAG = "gengen";

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

    private String mToken;
    private ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> list;
    private int playPosition;

    /**
     * 激活音乐播放页
     * @param context       上下文
     * @param list          播放列表
     * @param playPosition  播放列表中哪一项
     */
    public static void launch(Context context, ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> list, int playPosition) {
        Intent it = new Intent(context, MyPlayActivity.class);
        it.putParcelableArrayListExtra("list", list);
        it.putExtra("position", playPosition);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_playing_details);
        ButterKnife.bind(this);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));

        mToken = SPUtils.getString(this, "TOKEN", "");
        list = getIntent().getParcelableArrayListExtra("list");
        playPosition = getIntent().getIntExtra("position", 0);

        playMusic(true);
        mRotateObjectAnimator = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360);
        mRotateObjectAnimator.setDuration(20000);
        mRotateObjectAnimator.setInterpolator(new LinearInterpolator());
        mRotateObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        phoneView.setSelected(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        showPauseView();
    }

    @OnClick({R.id.player_back, R.id.player_phone, R.id.player_toy, R.id.player_collection,
            R.id.player_play_btn, R.id.player_loop, R.id.player_pre, R.id.player_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.player_back:
                finish();
                break;
            case R.id.player_phone:
                phoneView.setSelected(true);
                toyView.setSelected(false);
                //ToastUtil.showToast(this,"手机");
                break;
            case R.id.player_toy:
                //推送当前正在播放的或者当前的歌曲给玩具,如果没有播放(后台的接口写的是传玩具ID和资源ID),所以,播不播放都可以推送,
                //1,能推送,那能不能取消推送呢?
                //2,推送到玩具以后,应该跳转的界面是哪个?
                phoneView.setSelected(false);
                toyView.setSelected(true);
                pushMusicToToy();
                //ToastUtil.showToast(this,"玩具");
                break;
            case R.id.player_collection:

                break;
            case R.id.player_play_btn:
                playOrPause();
                break;
            case R.id.player_loop:
                MusicPlayer.setLooping(!isLooping);
                isLooping = !isLooping;
                break;
            case R.id.player_pre:
                if(playPosition==0) {
                    return;
                }
                --playPosition ;
                playMusic(false);
                break;
            case R.id.player_next:
                if(playPosition == list.size() - 1) {
                    return;
                }
                ++playPosition;
                playMusic(false);
                break;
        }
    }

    private void playMusic(boolean isFromCreate) {
        DiscoveryListResultBean.BODYBean.LSTBean bean = list.get(playPosition);
        Glide.with(this).load(bean.getIMG()).asBitmap().placeholder(R.drawable.player_cover_default).centerCrop().into(new BitmapImageViewTarget(mImageView) {

            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(),
                        resource);
                roundedBitmapDrawable.setCircular(true);
                mImageView.setImageDrawable(roundedBitmapDrawable);
            }
        });

        titleTextView.setText(bean.getNAME());
        if(!isFromCreate) {
            openAndStart();
        }
    }

    private void playOrPause() {
        Log.i("gengen", "playOrPause " + isBound);
        if(!isBound)
            return;
        if(!MusicPlayer.isPrepared()) {
            return;
        }
        if (!MusicPlayer.isPlaying()) {
            MusicPlayer.start();
            showStartView();
        } else {
            MusicPlayer.pause();
            showPauseView();
        }
    }

    private void showStartView() {
        if(!mRotateObjectAnimator.isRunning())
            mRotateObjectAnimator.start();
        mPlayBtn.setImageResource(R.drawable.play_stop_pressed240);
        updatePlayProgressShow();
    }

    private void showPauseView() {
        mRotateObjectAnimator.cancel();
        mPlayBtn.setImageResource(R.drawable.play_pressed_240);
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
    }

    /**更新播放进度的显示**/
    private void updatePlayProgressShow() {
        int currentPosition = MusicPlayer.getCurrentPosition();
        startTimeView.setText(Util.formatMillis(currentPosition));

        progressBar.setProgress(currentPosition);
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
        mHandler.sendEmptyMessageDelayed(UPDATE_PLAY_PROGRESS_SHOW, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onPrepared() {
        int duration = MusicPlayer.getDuration();
        Log.i("gengen", "onPrepared..."+duration);
        if(duration != 0) {
            if(progressBar != null)
                progressBar.setMax(MusicPlayer.getDuration());
            durationView.setText(Util.formatMillis(duration));
        }
    }

    @Override
    protected void onError() {

    }

    @Override
    protected void onCompleted() {
        showPauseView();
    }

    @Override
    protected void bindSuccess() {
        //和服务绑定成功后才能操作MediaPlayer
        String mMusicId = list.get(playPosition).getID();
        if(mMusicId != null) {
            Log.i("gengen", "open...");
            MusicPlayer.open(mMusicId);
        }
    }

    private void openAndStart() {
        Log.i("gengen", "openAndStart...");
        String mMusicId = list.get(playPosition).getID();
        if(mMusicId != null) {
            MusicPlayer.openAndStart(mMusicId);
        }
        showStartView();
    }

    @Override
    protected void isSimplePlayUrl() {
        onPrepared();
        showStartView();
    }


    private void pushMusicToToy() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        ControlToyPlayMusicReqBean.ParamBean paramBean = new ControlToyPlayMusicReqBean.ParamBean("201705081005211016644025", "1", "201705151824191016803202",
                "0");
        ControlToyPlayMusicReqBean control_play = new ControlToyPlayMusicReqBean("control_play", paramBean, mToken);
        Gson gson=new Gson();
        String s = gson.toJson(control_play);
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
