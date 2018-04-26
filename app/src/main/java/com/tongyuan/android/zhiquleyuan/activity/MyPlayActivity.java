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
import com.tongyuan.android.zhiquleyuan.bean.AddInCollectionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.AddInCollectionResBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.MusicPlayerBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.player.MusicPlayer;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.utils.Util;
import com.tongyuan.android.zhiquleyuan.view.IconTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2016/12/23.
 */

public class MyPlayActivity extends BaseActivity {

    @BindView(R.id.player_phone)
    IconTextView phoneView;
    @BindView(R.id.player_toy)
    IconTextView toyView;
    @BindView(R.id.player_details_name)
    TextView titleTextView;
    @BindView(R.id.player_collection)
    TextView collectView;
    @BindView(R.id.player_progress)
    ProgressBar progressBar;
    @BindView(R.id.player_start_time)
    TextView startTimeView;
    @BindView(R.id.player_duration_time)
    TextView durationView;
    @BindView(R.id.player_play_btn)
    ImageView mPlayBtn;
    @BindView(R.id.iv_playing_details_slide)
    ImageView mImageView;
    @BindView(R.id.player_loop)
    ImageView loopView;
    @BindView(R.id.iv_myplayactivity_playlist)
    ImageView mPlayList;

    private final static int single = 1;
    private final static int circle = 2;
    private final static int random = 3;

    private int playState = single;
    private final String TAG = getClass().getSimpleName();
    //更新播放进度的显示
    private static final int UPDATE_PLAY_PROGRESS_SHOW = 1;
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

    public static ArrayList<? extends MusicPlayerBean> list;
    public static int playPosition;

    /**
     * 激活音乐播放页
     *
     * @param context      上下文
     * @param list         播放列表
     * @param playPosition 播放列表中哪一项
     */

    public static void launch(Context context, ArrayList<? extends MusicPlayerBean> list, int playPosition) {
        Intent it = new Intent(context, MyPlayActivity.class);
        it.putParcelableArrayListExtra("list", list);
        it.putExtra("position", playPosition);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }

    public static ArrayList<? extends MusicPlayerBean> getPlayList() {
        return list;
    }

    public static int getCurrentPosition() {
        return playPosition;
    }

    public static String getCurrentId() {
        return list.get(playPosition).getID();
    }

    @Override
    protected void onCreate(@Nullable Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_playing_details);
        ButterKnife.bind(this);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));
        list = getIntent().getParcelableArrayListExtra("list");
        LogUtil.i(TAG, "list.size();" + list.size());
        LogUtil.i(TAG, "list.size();" + list.get(0));
        playPosition = getIntent().getIntExtra("position", 0);
        String img = list.get(playPosition).getIMG();
        showAlumImg(img);
        titleTextView.setText(list.get(playPosition).getNAME());
//        playMusic(true);
        initAnimator();
        phoneView.setSelected(true);

    }

    private void initAnimator() {
        mRotateObjectAnimator = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360);
        mRotateObjectAnimator.setDuration(20000);
        mRotateObjectAnimator.setInterpolator(new LinearInterpolator());
        mRotateObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MusicPlayer.isPlaying()) {
            showStartView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        showPauseView();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @OnClick({R.id.player_back, R.id.player_phone, R.id.player_toy, R.id.player_collection,
            R.id.player_play_btn, R.id.player_loop, R.id.player_pre, R.id.player_next, R.id.iv_myplayactivity_playlist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_myplayactivity_playlist:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("playlist", list);
                intent.putExtra("playlistbundle", bundle);
                intent.putExtra("entry", "myplayactivity");
                intent.setClass(this, PlayingListActivity.class);
//                intent.putExtra("playlist", list);
//                ArrayList<String> strings = new ArrayList<>();
//                intent.putExtra("playlist", strings);
                startActivity(intent);
                break;
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
//                phoneView.setSelected(false);
//                toyView.setSelected(true);
                pushMusicToToy();

                break;
            case R.id.player_collection:
                collectionRes();
                break;
            case R.id.player_play_btn:
                playOrPause();
                break;
            case R.id.player_loop:
                if (playState == single) {
                    playState = circle;
                    loopView.setImageResource(R.drawable.play_loop_playlist_ico_20_3x);
                } else if (playState == circle) {
                    playState = random;
                    loopView.setImageResource(R.drawable.play_randomize_ico_20_3x);
                } else if (playState == random) {
                    playState = single;
                    loopView.setImageResource(R.drawable.play_loop_track_ico_20_3x);
                }

                if (playState == circle) {
                    MusicPlayer.setLooping(true);// 列表循环的功能
                } else {
                    MusicPlayer.setLooping(false);
                }
                if (playState == single) {

//                    添加一个功能,单曲循环的功能
                }
                if (playState == random) {
//                    添加一个功能 随机播放的功能
                }
                break;
            case R.id.player_pre:
                if (playPosition == 0) {
                    return;
                }
                --playPosition;
                restartRotateObjectAnimator();
                playMusic(false);
                break;
            case R.id.player_next:
                if (playPosition == list.size() - 1) {
                    return;
                }
                ++playPosition;
                restartRotateObjectAnimator();
                playMusic(false);

                break;
        }
    }

    private void collectionRes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        AddInCollectionReqBean.BODYBean.LSTBean lstBean = new AddInCollectionReqBean.BODYBean.LSTBean(list.get(playPosition).getID());
        ArrayList arraylist = new ArrayList();
        arraylist.add(lstBean);
        AddInCollectionReqBean.BODYBean bodyBean = new AddInCollectionReqBean.BODYBean(arraylist);
        AddInCollectionReqBean addInCollectionReqBean = new AddInCollectionReqBean("REQ", "FAVRES", SPUtils.getString(this, "phoneNum", ""), new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(this, "token", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(addInCollectionReqBean);
        Call<AddInCollectionResBean> addInCollectionResBeanCall = allInterface.ADDINCOLLECTION_RES_BEAN_CALL(s);
        addInCollectionResBeanCall.enqueue(new Callback<AddInCollectionResBean>() {
            @Override
            public void onResponse(Call<AddInCollectionResBean> call, Response<AddInCollectionResBean> response) {
                if (response != null && response.body().getCODE().equals("0")) {
                    ToastUtil.showToast(getApplicationContext(), "收藏成功");
                }
            }

            @Override
            public void onFailure(Call<AddInCollectionResBean> call, Throwable t) {
            }
        });
    }

    private void playMusic(boolean isFromCreate) {
        MusicPlayerBean bean = list.get(playPosition);
        LogUtil.i(TAG, "bean . getIMG():" + bean.getIMG());
        showAlumImg(bean.getIMG());
        LogUtil.i(TAG, "bean . getName():" + bean.getNAME());
        titleTextView.setText(bean.getNAME());
        if (!isFromCreate) {
            openAndStart();
        }
    }

    private void showAlumImg(String img) {
        if (img.equals("") || img == null) {
            Glide.with(this).load(R.drawable.player_cover_default).asBitmap().centerCrop().into(new BitmapImageViewTarget(mImageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(),
                            resource);
                    roundedBitmapDrawable.setCircular(true);
                    mImageView.setImageDrawable(roundedBitmapDrawable);
                }
            });
        } else {
            Glide.with(this).load(img).asBitmap().into(new BitmapImageViewTarget(mImageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(),
                            resource);
                    roundedBitmapDrawable.setCircular(true);
                    mImageView.setImageDrawable(roundedBitmapDrawable);
                }
            });
        }
    }

    private void playOrPause() {
        if (!isBound)
            return;
        if (!MusicPlayer.isPrepared()) {
            MusicPlayer.openAndStart(list.get(playPosition).getID());
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
        if (!mRotateObjectAnimator.isRunning())
            mRotateObjectAnimator.start();
        mPlayBtn.setImageResource(R.drawable.play_stop_pressed240);
        updatePlayProgressShow();
    }

    private void showPauseView() {
        mRotateObjectAnimator.cancel();
        mPlayBtn.setImageResource(R.drawable.play_pressed_240);
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
    }

    private void restartRotateObjectAnimator() {
        if (mRotateObjectAnimator.isRunning()) {
            mRotateObjectAnimator.cancel();
            mRotateObjectAnimator.start();
        }

    }

    /**
     * 更新播放进度的显示
     **/
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
    }

    @Override
    protected void onPrepared() {
        showStartView();
        int duration = MusicPlayer.getDuration();
        if (duration != 0) {
            if (progressBar != null)
                progressBar.setMax(MusicPlayer.getDuration());
            durationView.setText(Util.formatMillis(duration));
        }
    }

    @Override
    protected void onError() {


    }

    @Override
    protected void onCompleted() {
        if (playState == single) {
            showPauseView();
            progressBar.setProgress(MusicPlayer.getDuration());
            startTimeView.setText(Util.formatMillis(MusicPlayer.getDuration()));
        } else if (playState == random) {
            Random random = new Random(47);
            int index = random.nextInt(list.size());
            MusicPlayer.openAndStart(list.get(index).getID());
        } else if (playState == circle) {

        }
    }

    @Override
    protected void bindSuccess() {
        //和服务绑定成功后才能操作MediaPlayer
        /*String mMusicId = list.get(playPosition).getID();
        if(mMusicId != null) {
            LogUtil.i("gengen", "open...");
            MusicPlayer.open(mMusicId);
        }*/
        openAndStart();
    }

    private void openAndStart() {
        String mMusicId = list.get(playPosition).getID();
        if (mMusicId != null) {
            MusicPlayer.openAndStart(mMusicId);
        }
        //showStartView();
    }

    @Override
    protected void isSimplePlayUrl() {
        onPrepared();
        showStartView();
    }

    /**
     * 推送到玩具播放
     */
    private void pushMusicToToy() {
        String resId = list.get(playPosition).getID();  //"201705151824191016803202"
        ControlToyPlayMusicReqBean.ParamBean paramBean = new ControlToyPlayMusicReqBean.ParamBean(ToySelectorFragment.mToyId, "1", resId, "0");
        Call<ControlToyPlayMusicResBean> result = RequestManager.getInstance().ToyPlayCommand(mContext, paramBean);
        result.enqueue(new Callback<ControlToyPlayMusicResBean>() {
            @Override
            public void onResponse(Call<ControlToyPlayMusicResBean> call, Response<ControlToyPlayMusicResBean> response) {
                System.out.println(response.body().toString());
                if ("0".equals(response.body().getCode())) {
                    MusicPlayer.stop();
                    Intent it = new Intent(MyPlayActivity.this, MainActivity.class);
                    startActivity(it);

                } else {
                    ToastUtil.showToast(mContext, response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ControlToyPlayMusicResBean> call, Throwable t) {
                ToastUtil.showToast(mContext, R.string.network_error);
            }
        });

    }
}
