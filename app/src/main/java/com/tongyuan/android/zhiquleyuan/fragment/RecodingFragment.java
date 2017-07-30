package com.tongyuan.android.zhiquleyuan.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.DataSetObservable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.MainActivity;
import com.tongyuan.android.zhiquleyuan.activity.SetInitVolumeActivity;
import com.tongyuan.android.zhiquleyuan.adapter.RecordAdapter;
import com.tongyuan.android.zhiquleyuan.adapter.RecordingListAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseRecordingFragment;
import com.tongyuan.android.zhiquleyuan.bean.AddRecordingReqBean;
import com.tongyuan.android.zhiquleyuan.bean.AddRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.ChangeRecordingNameReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ChangeRecordingNameResBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayRecordingReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteRecordingReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryRecordingReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryRecordingResBean;
import com.tongyuan.android.zhiquleyuan.db.DBHelper;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.player.MusicPlayer;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.utils.Util;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.System.currentTimeMillis;

//import com.tongyuan.android.zhiquleyuan.adapter.RecordAdapter;


/**
 * Created by android on 2016/12/3.
 */

public class RecodingFragment extends BaseRecordingFragment implements View.OnClickListener {
    private static final String TAG = "222222";
    private ImageView mRecordingButton;
    private ListView mLv_recoding;
    private File mFile;
    //    private RecodingAdapter mAdapter;
    private LinearLayout mRecordingListHeader;
    private ImageView mShare;
    private ImageView mEditor;
    private ImageView mSend2Toy;
    private ImageView mDownAction;
    private View mItemRecordingListHeader;
    private RelativeLayout mRecordingLayout;
    private RelativeLayout mRecordingComplete;
    private MediaRecorder mMediaRecorder;
    private List<File> recordList;
    private Long mCount;
    private TextView mTextview_startrecording;
    private static int length;
    private RelativeLayout mRecordingFrag;
    private RelativeLayout mCompleteFrag;
    private View mHeadview;
    private TextView mHeadTitle;
    private DBHelper mDbHelper;
    private TextView mRecordingDuration;
    private TextView mRecordingDesc;
    private ImageView mReRecording;
    private ImageView mTryListener;
    private ImageView mSaveRecording;
    private RelativeLayout mControl;
    private MediaPlayer mMediaPlayer;
    private ImageView mBackArrow;
    private int recorderSecondsElapsed;
    private ImageView mPlayStop;
    private ImageView mPlayStopCircle;
    private RotateAnimation mRotateAnimation;

    private boolean isNotRecording = true;
    private boolean isRecording = false;
    private boolean isStartedAnimation = false;
    private boolean isRecordingPlaying = false;
    private FrameLayout mBottomControl;
    private MediaPlayer mMediaPlayer1;
    private String mToken;
    private String mPhoneNum;
    private SwipeRefreshLayout sprefresh;
    private SwipeMenuListView mSwipelistview;
    private SwipeMenuCreator mCreator;

    DataSetObservable dataSetObservable = new DataSetObservable();
    private RelativeLayout mPlayRecordingFrag;
    private TextView mPlayRecordingDesc;
    private ImageButton mSetVolume;
    private ImageView mPlayBack;
    private ImageView mPlayPrev;
    private ImageView mPlayNext;
    private ImageView mPlay;
    private ImageView mStop;
    private TextView mPlayBeginTime;
    private TextView mPlayendTime;
    private SeekBar mPlaySeekBar;
    private ImageView mStopCircle;
    private EditListener mEditListener;
    private String mPlayName;
    private int selectedPosition = -1;
    private String mUrl;
    private String mRecordingId;
    private String mTime;
    private int RECORDING = 1;
    private int PLAYRECORDING = 2;
    private int RRCORDINGCOMPLETE = 3;
    private List<QueryRecordingResBean.BODYBean.LSTBean> mLst;
    private String mDur;
    private ShareAction mShareAction;
    private UMShareListener mShareListener;

    public RecodingFragment() {
    }

    private static final int UPDATE_PLAY_PROGRESS_SHOW = 1;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recordingRoot = initView(inflater);
        mDbHelper = new DBHelper(getActivity());

        Log.i("circlelife", "recordingfragment:onCreateView: went");
        return recordingRoot;

    }

    private View initView(LayoutInflater inflater) {
        View recordingRoot = inflater.inflate(R.layout.fragment_recoding, null);
        View itemRecordingHeader = inflater.inflate(R.layout.item_play_list_header, null);

        //整个framelayout的布局
        mBottomControl = (FrameLayout) recordingRoot.findViewById(R.id.fl_fragment_recording_control);
        //录音布局
        mRecordingFrag = (RelativeLayout) recordingRoot.findViewById(R.id.rl_fragment_recording_recording);
        //录音完成布局
        mCompleteFrag = (RelativeLayout) recordingRoot.findViewById(R.id.rl_fragment_recording_complete);
        //录音播放布局
        mPlayRecordingFrag = (RelativeLayout) recordingRoot.findViewById(R.id.rl_fragment_recording_playrecording);


        //录音播放的布局view
        //文件名称
        mPlayRecordingDesc = (TextView) mPlayRecordingFrag.findViewById(R.id.tv_fragment_playrecoding_desc);
        //设置音量
        mSetVolume = (ImageButton) mPlayRecordingFrag.findViewById(R.id.ib_fragment_recoding_setvolume);
        //返回键
        mPlayBack = (ImageView) mPlayRecordingFrag.findViewById(R.id.iv_fragment_playrecording_back);
        //上一首
        mPlayPrev = (ImageView) mPlayRecordingFrag.findViewById(R.id.iv_recoding_prev);
        //下一首
        mPlayNext = (ImageView) mPlayRecordingFrag.findViewById(R.id.iv_recoding_next);
        //播放键
        mPlay = (ImageView) mPlayRecordingFrag.findViewById(R.id.iv_recoding_play);
        //播放暂停键
        mStop = (ImageView) mPlayRecordingFrag.findViewById(R.id.iv_recoding_stop);
        //开始时间
        mPlayBeginTime = (TextView) mPlayRecordingFrag.findViewById(R.id.tv_fragment_recoding_begintime);
        //结束时间
        mPlayendTime = (TextView) mPlayRecordingFrag.findViewById(R.id.tv_fragment_recoding_endtime);
        //播放进度条
        mPlaySeekBar = (SeekBar) mPlayRecordingFrag.findViewById(R.id.seekbar);
        //彩色环
        mStopCircle = (ImageView) mPlayRecordingFrag.findViewById(R.id.iv_recoding_stopcircle);


        mRecordingDesc = (TextView) recordingRoot.findViewById(R.id.tv_fragment_recoding_desc);
        mRecordingDuration = (TextView) recordingRoot.findViewById(R.id.tv_fragment_recoding_duration);
        mReRecording = (ImageView) recordingRoot.findViewById(R.id.iv_recoding_tool_reRecording);
        mTryListener = (ImageView) recordingRoot.findViewById(R.id.iv_recoding_tool_trylistener);
        mSaveRecording = (ImageView) recordingRoot.findViewById(R.id.iv_recoding_tool_saverecording);

        //试听停止,中间的暂停图标
        mPlayStop = (ImageView) recordingRoot.findViewById(R.id.iv_recoding_tool_trylistener_stop);

        //试听停止,中间暂停的彩色圆圈
        mPlayStopCircle = (ImageView) recordingRoot.findViewById(R.id.iv_recoding_tool_trylistener_stopcircle);

        mShare = (ImageView) recordingRoot.findViewById(R.id.iv_fragent_recording_share);
        mEditor = (ImageView) recordingRoot.findViewById(R.id.iv_fragent_recording_editor);
        mSend2Toy = (ImageView) recordingRoot.findViewById(R.id.iv_fragent_recording_toy);
        mDownAction = (ImageView) recordingRoot.findViewById(R.id.iv_fragent_recording_down);
        mRecordingButton = (ImageView) recordingRoot.findViewById(R.id.iv_recoding_tool_startrecoding);

        mLv_recoding = (ListView) recordingRoot.findViewById(R.id.lv_recoding);
        mItemRecordingListHeader = itemRecordingHeader.findViewById(R.id.tv_item_play_list_header);
        mControl = (RelativeLayout) recordingRoot.findViewById(R.id.rl_fragment_recoding_control);

        mBackArrow = (ImageView) recordingRoot.findViewById(R.id.iv_fragment_recording_back);
        //找到recording录音的布局
        mRecordingLayout = (RelativeLayout) mLv_recoding.findViewById(R.id.rl_fragment_recording_recording);
        mRecordingComplete = (RelativeLayout) mLv_recoding.findViewById(R.id.rl_fragment_recording_complete);

        mTextview_startrecording = (TextView) recordingRoot.findViewById(R.id.tv_startrecording);
        recordList = new ArrayList<File>();
        mMediaPlayer = new MediaPlayer();
//        View mHeadview = inflater.inflate(R.layout.fragment_recoding_headerview, null);
//        mHeadTitle = (TextView) mHeadview.findViewById(R.id.tv_recoding_title);

//        mhalder = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Log.i("555555", "scan file over...");
//                showFile(recordList);
//            }
//        };

        sprefresh = (SwipeRefreshLayout) recordingRoot.findViewById(R.id.sprefresh);
        mSwipelistview = (SwipeMenuListView) recordingRoot.findViewById(R.id.lv_recoding);
        mSwipelistview.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        sprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                queryRecordingList();
                sprefresh.setRefreshing(false);
            }

        });

        return recordingRoot;

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mShareListener = new CustomShareListener(getActivity());
        mShareAction = new ShareAction(getActivity());

        initData();
        initListener();

        Log.i("circlelife", "recordingfragment:onActivityCreated: went");
        //TODO 展示本地的recording,没有就去网络获取

    }

    private void initData() {
//    QueryRecordingReqBean.BODYBean bodyBean=new QueryRecordingReqBean.BODYBean("","10","1");
////    QueryRecordingReqBean queryRecordingReqBean = new QueryRecordingReqBean(bodyBean);
//    BaseRequest baseRequest = new BaseRequest(getContext(), bodyBean, "MYREC");
//    Call<SuperModel<QueryRecordingResBean>> queryRecordingResBeanResult = RequestManager.getInstance().queryRecordingResBean(baseRequest);
        queryRecordingList();


    }

    private void initListener() {
        mRecordingButton.setOnClickListener(this);
        mSend2Toy.setOnClickListener(this);
        mEditor.setOnClickListener(this);
        mDownAction.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mReRecording.setOnClickListener(this);
        mTryListener.setOnClickListener(this);
        mSaveRecording.setOnClickListener(this);
        mBackArrow.setOnClickListener(this);
        mPlayStop.setOnClickListener(this);
        mPlayBack.setOnClickListener(this);
        mPlaySeekBar.setOnClickListener(this);
        mPlay.setOnClickListener(this);
        mPlayPrev.setOnClickListener(this);
        mPlayNext.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mSetVolume.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //录音
            case R.id.iv_recoding_tool_startrecoding:
                ToastUtil.showToast(getContext(), "点击了recoding");
                startRecoding();
                break;
            //分享
            case R.id.iv_fragent_recording_share:
                if (selectedPosition == -1) {
                    ToastUtil.showToast(getContext(), "当前没有选中录音,请选择录音之后分享");
                    return;
                }

                mShareAction
                        .withText(SPUtils.getString(getContext(), "address", ""))
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.SMS)
                        .setPlatform(SHARE_MEDIA.WEIXIN).setCallback(mShareListener)
                        .open();

                ToastUtil.showToast(getContext(), "点击了share");

                break;
            //推送到玩具
            case R.id.iv_fragent_recording_toy:
                //判断当前的布局是不是播放的布局,如果是播放的布局才能推送给玩具

//                Log.i(TAG, "itemClick:recordingfragment mToyId"+toyid);

                if (ToySelectorFragment.mToyId == null) {
                    ToastUtil.showToast(getContext(), "当前没有选中玩具");
                    return;
                } else if (selectedPosition == -1) {
                    ToastUtil.showToast(getContext(), "当前没有选中的item,请选择item之后再推送给玩具");
                    return;
                }
                sendRecordingToToy(ToySelectorFragment.mToyId);
                ToastUtil.showToast(getContext(), "点击了send2toy");

                break;

            //编辑
            case R.id.iv_fragent_recording_editor:
                //我想干什么,我想在我点编辑按钮的时候,如果当前有选中的item,那么就拿到当前的选中的item的recording的名字,然后弹dialog,修改这个名字.
                //当前没有选中item的时候,告知用户,当前没有选择item,让用户选择以后再进行编辑的操作.
                //点击dialog的确定以后,底下的播放界面的名字,和listview上的名字都改变,并且上传到服务器,然后刷新列表
                //TODO 编辑的功能,逻辑混乱,捋一捋
                Log.i("xuanzhong", "是否选中" + mSwipelistview.isSelected());
                if (selectedPosition == -1) {
                    ToastUtil.showToast(getActivity(), "您没有选择要编辑的录音,请选择后再进行编辑");
                    return;
                }
                showEditDialog();

                editRecordingFile();
                ToastUtil.showToast(getContext(), "点击了editor");
                break;
            //向下移动
            case R.id.iv_fragent_recording_down:
                moveToBottom();
                ToastUtil.showToast(getContext(), "点击了downaction");
                break;


            //试听
            case R.id.iv_recoding_tool_trylistener:
                //播放刚录完的音频,获得刚录完的音频的路劲

//                if (mMediaPlayer == null) {
//                    mMediaPlayer = new MediaPlayer();
//                }

                Log.i("555555", "isplaying2: ");
//                    isRecordingPlaying = true;
                mTryListener.setVisibility(View.INVISIBLE);
                startPlayRecord();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        isRecordingPlaying = false;
                        stopAnimation();
                        mPlayStopCircle.setVisibility(View.INVISIBLE);
                        mPlayStop.setVisibility(View.INVISIBLE);

                    }
                });
                break;
            case R.id.iv_recoding_tool_trylistener_stop:
                stopPlayRecord();
                Log.i("555555", "isplaying1: ");
//                    isRecordingPlaying = false;

                ToastUtil.showToast(getContext(), "结束试听");
                break;
            //重录
            case R.id.iv_recoding_tool_reRecording:
                //不保存当前的音频文件,删除缓存,返回到录音的界面.
                reRecording();
                ToastUtil.showToast(getContext(), "点击了reRecording");
                break;
            //保存
            case R.id.iv_recoding_tool_saverecording:
                SaveRecording();
                ToastUtil.showToast(getContext(), "点击了saverecording");
                break;
            //返回
            case R.id.iv_fragment_recording_back:
                mMediaPlayer = null;
                mMediaRecorder = null;
                mRecordingFrag.setVisibility(View.VISIBLE);
                mCompleteFrag.setVisibility(View.INVISIBLE);
                break;

            //播放界面的播放按钮
            case R.id.iv_recoding_play:
                if (selectedPosition == -1) {
                    return;
                }
                playOrPause(true);
                ToastUtil.showToast(getContext(), "点击播放");
                break;
            case R.id.iv_recoding_stop:
                playOrPause(false);
                break;

            case R.id.iv_recoding_prev:
                if (selectedPosition == -1) {
                    return;
                }
                if (selectedPosition == 0) {
                    return;
                }
                --selectedPosition;
                mRecordingId = mLst.get(selectedPosition).getID();
                playMusic(false);
                break;
            case R.id.iv_recoding_next:
                if (selectedPosition == mLst.size() - 1) {
                    return;
                }
                ++selectedPosition;
                mRecordingId = mLst.get(selectedPosition).getID();
                playMusic(false);
                break;
            //播放界面的返回
            case R.id.iv_fragment_playrecording_back:
                showRecordingPlayView(false);
                selectedPosition = -1;
            case R.id.seekbar:
                break;
            case R.id.ib_fragment_recoding_setvolume:
                Intent intent = new Intent();

                intent.setClass(getContext(), SetInitVolumeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void playMusic(boolean isFromCreate) {
        if (!isFromCreate) {
            openAndStart();
        }
    }

    private void openAndStart() {
        if (mRecordingId != null) {
            MusicPlayer.openAndStart(mRecordingId);
        }
    }

    private void playOrPause(boolean isPlay) {
        MainActivity activity = (MainActivity) getActivity();
        if (!activity.isBound())
            return;
        if (!MusicPlayer.isPrepared() && isPlay) {
            MusicPlayer.openAndStart(mLst.get(selectedPosition).getID());
            return;
        }
        if (isPlay) {
            if (MusicPlayerService.isPlayUrl(mRecordingId)) {
                MusicPlayer.start();
            } else {
                MusicPlayer.openAndStart(mLst.get(selectedPosition).getID());
            }
            showStartView();
        } else {
            MusicPlayer.pause();
            showPauseView();
        }
    }


    private void sendRecordingToToy(String toyid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        System.out.println("mtoyid" + toyid);

        ControlToyPlayRecordingReqBean.ParamBean paramBean = new ControlToyPlayRecordingReqBean.ParamBean(toyid, "1", mRecordingId,
                "0");
        ControlToyPlayRecordingReqBean controlToyPlayRecordingReqBean = new ControlToyPlayRecordingReqBean("control_play", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(controlToyPlayRecordingReqBean);
        System.out.println("ssssssss" + s);
        Call<ControlToyPlayRecordingResBean> controlToyPlayRecordingResBeanCall = allInterface.CONTROL_TOY_PLAY_RECORDING_RES_BEAN_CALL(s);
        controlToyPlayRecordingResBeanCall.enqueue(new Callback<ControlToyPlayRecordingResBean>() {
            @Override
            public void onResponse(Call<ControlToyPlayRecordingResBean> call, Response<ControlToyPlayRecordingResBean> response) {

                System.out.println(response.body().toString());

            }

            @Override
            public void onFailure(Call<ControlToyPlayRecordingResBean> call, Throwable t) {

            }
        });

    }

    public void showEditDialog() {
        LayoutInflater factory = LayoutInflater.from(getContext());//提示框
        //这里必须是final的
        final View view = factory.inflate(R.layout.alertdialogedittext, null);
        //获得输入框对象
        final EditText editText = (EditText) view.findViewById(R.id.edittext);

        editText.setText(mPlayName);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("修改录音的名字");
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editTrim = editText.getText().toString().trim();
                mPlayRecordingDesc.setText(editTrim);
                changeRecordingName(editTrim);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    private void changeRecordingName(String editTrim) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        ChangeRecordingNameReqBean.BODYBean bodyBean = new ChangeRecordingNameReqBean.BODYBean("RADIO", mRecordingId, editTrim);

        ChangeRecordingNameReqBean changeRecordingNameReqBean = new ChangeRecordingNameReqBean("REQ", "ARES", mPhoneNum, mTime, bodyBean, "",
                mToken, "1");


        Gson gson = new Gson();
        String babyListJson = gson.toJson(changeRecordingNameReqBean);
        Call<ChangeRecordingNameResBean> babyListResult = allInterface.CHANGE_RECORDING_NAME_RES_BEAN_CALL(babyListJson);
        babyListResult.enqueue(new Callback<ChangeRecordingNameResBean>() {
            @Override
            public void onResponse(Call<ChangeRecordingNameResBean> call, Response<ChangeRecordingNameResBean> response) {

                Log.i("555555", "recordingfragment+(changerecordingnamebean)onResponse: " + response.body().getBODY().toString());
                //改变完了以后去访问网络,刷新listview
                queryRecordingList();
            }

            @Override
            public void onFailure(Call<ChangeRecordingNameResBean> call, Throwable t) {

                Log.i("555555", "recordingfragment+(changerecordingnamebean)onFailure: " + t.toString());

            }
        });

    }


    public void setOnEditListener(EditListener editListener) {
        mEditListener = editListener;
    }

    private void queryRecordingList() {
        mToken = SPUtils.getString(getContext(), "TOKEN", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(date);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryRecordingReqBean.BODYBean bodyBean = new QueryRecordingReqBean.BODYBean("", "10", "1");
        QueryRecordingReqBean queryRecordingReqBean = new QueryRecordingReqBean("REQ", "MYREC", mPhoneNum, mTime, bodyBean, "", mToken, "1");

        Gson gson = new Gson();
        String babyListJson = gson.toJson(queryRecordingReqBean);
        Call<QueryRecordingResBean> babyListResult = allInterface.QUERY_RECORDING_RES_BEAN_CALL(babyListJson);
        babyListResult.enqueue(new Callback<QueryRecordingResBean>() {


            private RecordingListAdapter mRecordingListAdapter;

            @Override
            public void onResponse(Call<QueryRecordingResBean> call, final Response<QueryRecordingResBean> response) {
                if (response.body() != null && response.body().getCODE().equals("0")) {
                    Log.i("1111111", "queryRecordingList:response" + response.body().getBODY().toString());
                    mLst = response.body().getBODY().getLST();//这里拿的是所有的录音的列表
                    Log.i(TAG, "onResponse: mlist" + mLst.toString());
                    final List<String> list = new ArrayList<String>();

                    for (int i = 0; i < mLst.size(); i++) {
                        String id = mLst.get(i).getID();
                        list.add(id);
                    }
                    Log.i("555555", "onResponse:+list的长度: " + list.size() + "list的内容:");
                    mRecordingListAdapter = new RecordingListAdapter(getContext(), mLst);
                    mSwipelistview.setAdapter(mRecordingListAdapter);
                    mSwipelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            /**1,点击item,显示录音播放界面
                             //2,拿到item的信息(name,duration),展示到播放界面上
                             //3,点击播放,开始播放,seekbar的begintime开始走 ,seekbar的进度也跟着走,同时播放按钮变成暂停按钮
                             //4,点击暂停,录音播放暂停,seekbar停止进度,记住当前的播放位置,并停留在当前的显示位置
                             //5,点击上一首,去list列表获取上一首的信息,如果是第一首就仍然是第一首,点击上一首,直接开始播放
                             //6,点击下一首,去list列表获取下一首的信息,如果是最后一首就仍然是最后一首,点击下一首,直接开始播放
                             //7,播放结束的监听,当音乐播放完,进度条停止,暂停按钮变成播放按钮
                             //8,点击音量控制,去设置初始化音量界面,设置音量并上传到服务器
                             **/
                            //定义一个选中状态
                            mSwipelistview.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                            mSwipelistview.setItemChecked(position, true);
                            //展示的时候同时保存当前的选中的录音文件的播放地址.
                            SPUtils.putString(getContext(), "address", response.body().getBODY().getLST().get(position).getID());
                            //1,点击item,显示录音播放界面

                            //保存position
                            selectedPosition = position;
                            showRecordingPlayView(true);
                            //拿到item的信息(name,duration),展示到播放界面上
                            //播放的id
//                            Log.i(TAG, "onItemClick: list"+list.toString());
                            mRecordingId = list.get(selectedPosition);
                            Log.i(TAG, "onItemClick: mrecoding" + mRecordingId);
                            //2,拿到item的信息(name,duration),展示到播放界面上
                            mPlayName = mLst.get(position).getNAME();
                            mDur = mLst.get(position).getDUR();
                            mPlayRecordingDesc.setText(mPlayName);
                            mPlayendTime.setText(mDur);
//                            //带着id 去服务器申请本地播放
//                            getLocalPlayApply(mToken, mPhoneNum, mRecordingId, mTime);

                            ToastUtil.showToast(getContext(), "点击的是:" + selectedPosition);

                        }
                    });
                    SwipeMenuCreator mCreator = new SwipeMenuCreator() {
                        @Override
                        public void create(SwipeMenu menu) {
                            SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                            deleteItem.setTitle("删除");
                            deleteItem.setBackground(R.color.redFont);
                            deleteItem.setWidth(dp2px(70));
                            deleteItem.setTitleSize(16);

                            deleteItem.setTitleColor(R.color.white);
                            menu.addMenuItem(deleteItem);

                        }
                    };

                    mSwipelistview.setMenuCreator(mCreator);
                    mSwipelistview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                            deleteRecording(mTime, position, mLst);
                            mLst.remove(position);
                            mRecordingListAdapter.notifyDataSetChanged();
                            ToastUtil.showToast(getContext(), "点击删除");
                            return false;
                        }
                    });

                    mSwipelistview.setSmoothScrollbarEnabled(true);
                    mSwipelistview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
                    mSwipelistview.setOpenInterpolator(new AccelerateInterpolator());
                    mSwipelistview.setCloseInterpolator(new AccelerateInterpolator());

                }
            }

            @Override
            public void onFailure(Call<QueryRecordingResBean> call, Throwable t) {

            }
        });

    }

    private void getLocalPlayApply(String token, String phoneNum, String recordingId, String time) {
        LocalPlayApplyReqBean.BODYBean bodyBean1 = new LocalPlayApplyReqBean.BODYBean(recordingId);

        BaseRequest baseRequest = new BaseRequest(getContext(), bodyBean1, "PLAY");
        Call<SuperModel<LocalPlayApplyResBean>> localPlayApplyResBeanCall = RequestManager.getInstance().requestMusicDetail(baseRequest);
        localPlayApplyResBeanCall.enqueue(new Callback<SuperModel<LocalPlayApplyResBean>>() {
            @Override
            public void onResponse(Call<SuperModel<LocalPlayApplyResBean>> call, Response<SuperModel<LocalPlayApplyResBean>> response) {
                if (response.body().CODE.equals("-700")) {
                    ToastUtil.showToast(getContext(), "资源不存在");
                    return;
                } else if (response.body().CODE.equals("0")) {
                    mUrl = response.body().BODY.getURL();
                    Log.i(TAG, "onResponse: murl" + mUrl.toString());


                }
            }

            @Override
            public void onFailure(Call<SuperModel<LocalPlayApplyResBean>> call, Throwable t) {

            }
        });

    }


    private void showRecordingPlayView(boolean b) {
        if (b) {
            mPlayRecordingFrag.setVisibility(View.VISIBLE);
            mCompleteFrag.setVisibility(View.GONE);
            mRecordingFrag.setVisibility(View.GONE);
            mBottomControl.setVisibility(View.VISIBLE);
            playOrPause(false);
        } else {
            mPlayRecordingFrag.setVisibility(View.GONE);
            mCompleteFrag.setVisibility(View.GONE);
            mRecordingFrag.setVisibility(View.VISIBLE);
        }
    }

    private void deleteRecording(String time, int position, List<QueryRecordingResBean.BODYBean.LSTBean> lst) {
        String recordingId = lst.get(position).getID();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        DeleteRecordingReqBean.BODYBean bodyBean = new DeleteRecordingReqBean.BODYBean(recordingId);
        DeleteRecordingReqBean deleteRecordingReqBean = new DeleteRecordingReqBean("REQ", "DRES", mPhoneNum, time, bodyBean, "", mToken, "1");

        Gson gson = new Gson();
        String babyListJson = gson.toJson(deleteRecordingReqBean);
        Call<DeleteRecordingResBean> babyListResult = allInterface.DELETE_RECORDING_RES_BEAN_CALL(babyListJson);
        babyListResult.enqueue(new Callback<DeleteRecordingResBean>() {
            @Override
            public void onResponse(Call<DeleteRecordingResBean> call, Response<DeleteRecordingResBean> response) {
                Log.i("555555", "recordingfragment+(deleteRecording)onResponse: " + response.body().getBODY().toString());
            }

            @Override
            public void onFailure(Call<DeleteRecordingResBean> call, Throwable t) {
                Log.i("555555", "recordingfragment+(deleteRecording)onFailure: " + t.toString());
            }
        });
    }

    private void editRecordingFile() {

        mSwipelistview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
    }

    private void moveToBottom() {

//        ObjectAnimator objectAnimator = new ObjectAnimator();
//        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        Log.i("55555", "moveToBottom: +"+mControl);
//        mControl.measure(w, h);
//        float measuredHeight = mControl.getMeasuredHeight();
//        float heightPixels = getResources().getDisplayMetrics().heightPixels;
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mControl, "Y", heightPixels - measuredHeight, heightPixels);
//        objectAnimator.setDuration(300);
//        objectAnimator.start();

        if (mBottomControl.getVisibility() == View.VISIBLE) {
            mBottomControl.setVisibility(View.GONE);
        } else {
            mBottomControl.setVisibility(View.VISIBLE);
        }
    }


    private void SaveRecording() {
        if (isRecordingPlaying) {
            stopPlayRecord();
        }
        //往服务器上存储一份
        saveRecrodingToServer();
        recordList.add(mFile);
//        showFile(recordList);
        mRecordingFrag.setVisibility(View.VISIBLE);
        mCompleteFrag.setVisibility(View.INVISIBLE);
    }

    private void saveRecrodingToServer() {
        String phoneNum = SPUtils.getString(getContext(), "phoneNum", "");
        String token = SPUtils.getString(getContext(), "TOKEN", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        Gson gson = new Gson();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(date);
        String name = mFile.getName();
        AddRecordingReqBean.BODYBean bodyBean = new AddRecordingReqBean.BODYBean("RADIO", "", name);
        AddRecordingReqBean addRecordingReqBean = new AddRecordingReqBean("REQ", "ARES", phoneNum, time, bodyBean, "", token, "1");

        String s = gson.toJson(addRecordingReqBean);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("params", s);
        builder.addFormDataPart("RADIO", mFile.getName(), requestFile);

        List<MultipartBody.Part> parts = builder.build().parts();
        Call<AddRecordingResBean> babyInfoResultBeanCall = allInterface.ADD_RECORDING_RES_BEAN_CALL(parts);


        babyInfoResultBeanCall.enqueue(new Callback<AddRecordingResBean>() {

            @Override
            public void onResponse(Call<AddRecordingResBean> call, Response<AddRecordingResBean> response) {

                Log.i("upload", "body=" + response.message() + " " + response.body().getMSG());
//                String code = response.body().getCODE();
                Log.i("recodingFragment:upLoad", response.body().toString());
//                if (code.equals("0")) {
//                    finish();
//                    setResult(BabyInfoListActivity.SuccessCode);
//                }
            }

            @Override
            public void onFailure(Call<AddRecordingResBean> call, Throwable t) {
                Log.i("upload", "onFailure...");
            }
        });
    }

    private void reRecording() {
        if (isRecordingPlaying) {
            stopPlayRecord();
            mCompleteFrag.setVisibility(View.GONE);
            mRecordingFrag.setVisibility(View.VISIBLE);
            isRecordingPlaying = false;
        }
        mCompleteFrag.setVisibility(View.GONE);
        mRecordingFrag.setVisibility(View.VISIBLE);
    }

    //    private boolean isPlaying = false;
    //播放录音
    private void startPlayRecord() {

        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }

        try {
            if (isRecordingPlaying) {
                mMediaPlayer.start();
            } else {
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(mFile.getAbsolutePath());
                mMediaPlayer.prepare();
                mMediaPlayer.start();

            }
            mPlayStop.setVisibility(View.VISIBLE);
            mPlayStopCircle.setVisibility(View.VISIBLE);
            isRecordingPlaying = true;

        } catch (IOException e) {
            e.printStackTrace();

        }
        startAnimation();

    }

    //停止播放录音
    private void stopPlayRecord() {
        mMediaPlayer.pause();
        stopAnimation();
    }

    //开始动画
    private void startAnimation() {
        if (mRotateAnimation == null) {
            mRotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        if (isStartedAnimation == false) {

            isStartedAnimation = true;
            mRotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mRotateAnimation.setDuration(3000);
            mRotateAnimation.setRepeatCount(Animation.INFINITE);
            LinearInterpolator linearInterpolator = new LinearInterpolator();
            mRotateAnimation.setInterpolator(linearInterpolator);
            mRotateAnimation.setFillAfter(true);
            mPlayStopCircle.setAnimation(mRotateAnimation);
            mRotateAnimation.start();
        }
    }

    //停止动画
    private void stopAnimation() {
        if (isStartedAnimation == true) {

            isStartedAnimation = false;
            mRotateAnimation.cancel();
//            mRotateAnimation.reset();
            mPlayStopCircle.setVisibility(View.GONE);
            mPlayStop.setVisibility(View.GONE);
            mTryListener.setVisibility(View.VISIBLE);
        }
    }

    //录音
    private void startRecoding() {
        if (isNotRecording) {
            mRecordingButton.setImageResource(R.drawable.play_stop_240);
            try {
                long l1 = System.currentTimeMillis();

                mMediaRecorder = new MediaRecorder();
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

                String dir = Environment.getExternalStorageDirectory().getPath() + "/AAmart";
                File file = new File(dir);
                if (!file.exists()) {
                    file.mkdirs();
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 " + "HH时mm分ss秒");
                String date = simpleDateFormat.format(new Date());
                mFile = new File(dir, date.toString() + ".mp3");
                mMediaRecorder.setOutputFile(mFile.getAbsolutePath());
                mMediaRecorder.prepare();
                mMediaRecorder.start();
                long l2 = System.currentTimeMillis();
                Log.i("55555", "startRecoding: " + (l2 - l1));
                startTimer();//时间计时

                isNotRecording = !isNotRecording;
                ToastUtil.showToast(getActivity(), "开始录音");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            long end = System.currentTimeMillis();
            long durat = end - l;
            Log.i("555555", "haha: " + durat);
            //没有录音状态
            mRecordingButton.setImageResource(R.drawable.recording_pressed_240);
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;

            //点击结束以后,地下的录音界面应该消失,并且显示录音完成与界面
            mRecordingFrag.setVisibility(View.INVISIBLE);

            mRecordingDesc.setText(mFile.getName());
            long length = mFile.length();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            Date date = new Date(durat);
            String duration = simpleDateFormat.format(date);
            mRecordingDuration.setText(duration);
            mCompleteFrag.setVisibility(View.VISIBLE);

//            mDbHelper.addRecording(mFile.getName(), mFile.getPath(), mFile.length());

            //让完成录音fragment显示,录音fragment隐藏,并显示信息

            //将文件展示到listview上面, 文件以list的形式保存起来

            isNotRecording = !isNotRecording;
            ToastUtil.showToast(getActivity(), "结束录音");

        }
    }

    long l;

    private void startTimer() {
        l = currentTimeMillis();
        Timer timer = new Timer();
        int MaxTime = 10 * 60;

//        stopTimer();
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                updateTimer();
//            }
//
//
//        }, 0, 1000);
    }

    private void stopTimer() {

    }

    private void updateTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isRecording) {
                    recorderSecondsElapsed++;
                }
            }
        });
    }

    private void showFile(List<File> recordList) {
        Log.i("555555", "recordList.size=" + recordList.size());
        RecordAdapter recodAdapter = new RecordAdapter(getActivity(), recordList);
        mLv_recoding.setAdapter(recodAdapter);
        mLv_recoding.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(getContext(), "长按了:" + position);
                return false;
            }
        });
        mLv_recoding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                mLv_recoding.getChildAt(position).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "点击" + pos);
                    }
                });
            }
        });
//        mLv_recoding.addHeaderView(mHeadTitle);

    }


    private Handler mhalder;

    @Override
    public void onResume() {
        super.onResume();

        String dir = Environment.getExternalStorageDirectory().getPath() + "/AAmart";
        final File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                File[] files = file.listFiles();
                recordList.addAll(Arrays.asList(files));
//                mhalder.sendEmptyMessage(1);
            }
        }).start();
        Log.i("circlelife", "recordingfragment:onResume: went");

        if (MusicPlayer.isPlaying() && MusicPlayerService.isPlayUrl(mRecordingId)) {
            showStartView();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        showPauseView();
    }

    @Override
    public void onPrepared() {
        showStartView();
        int duration = MusicPlayer.getDuration();
        if (duration != 0) {
            if (mPlaySeekBar != null)
                mPlaySeekBar.setMax(MusicPlayer.getDuration());
            mPlayendTime.setText(Util.formatMillis(duration));
            mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
            mHandler.sendEmptyMessage(UPDATE_PLAY_PROGRESS_SHOW);
        }
    }

    @Override
    public void onError() {

    }

    @Override
    public void onCompleted() {
//        if(playState == single) {
        showPauseView();
//            mPlaySeekBar.setProgress(MusicPlayer.getDuration());
//            mPlayBeginTime.setText(Util.formatMillis(MusicPlayer.getDuration()));
//        } else if(playState == random) {
//            Random random = new Random(47);
//            int index = random.nextInt(list.size());
//            MusicPlayer.openAndStart(list.get(index).getID());
//        }
    }

    @Override
    public void bindSuccess() {
        openAndStart();
    }

    @Override
    public void isSimplePlayUrl() {
        onPrepared();
        showStartView();
    }

    private void showStartView() {
        mPlay.setVisibility(View.GONE);
        mStop.setVisibility(View.VISIBLE);
        mStopCircle.setVisibility(View.VISIBLE);

    }

    private void showPauseView() {
        mPlay.setVisibility(View.VISIBLE);
        mStop.setVisibility(View.GONE);
        mStopCircle.setVisibility(View.GONE);
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
        mPlaySeekBar.setProgress(0);
        mPlayBeginTime.setText(Util.formatMillis(0));
        mPlayendTime.setText(mDur);
    }

    public interface EditListener {
        void EditRecordingName();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("circlelife", "recordingfragment:onStop: went");
    }

    private void updatePlayProgressShow() {

        int currentPosition = MusicPlayer.getCurrentPosition();
        Log.i("handler", "position=" + currentPosition);
        mPlayBeginTime.setText(Util.formatMillis(currentPosition));
        mPlaySeekBar.setProgress(currentPosition);
        mHandler.removeMessages(UPDATE_PLAY_PROGRESS_SHOW);
        mHandler.sendEmptyMessageDelayed(UPDATE_PLAY_PROGRESS_SHOW, 1000);
    }

    private class CustomShareListener implements UMShareListener {
        private WeakReference<MainActivity> mWeakReference;

        public CustomShareListener(Activity activity) {
            mWeakReference = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {
            ToastUtil.showToast(getContext(), "fragment");

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.e(TAG, "onresult:+recoding+1 ");
            ToastUtil.showToast(getActivity(), platform + " 分享成功啦1");
            if (platform != SHARE_MEDIA.WEIXIN && platform != SHARE_MEDIA.SMS) {
                ToastUtil.showToast(getActivity(), platform + " 分享成功啦2");
//                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable throwable) {
            Log.e(TAG, "onError:+recoding ", throwable);
            if (platform != SHARE_MEDIA.WEIXIN && platform != SHARE_MEDIA.SMS) {
                ToastUtil.showToast(mWeakReference.get(), "分享失败");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Log.e(TAG, "oncancel:+recoding +1");
            ToastUtil.showToast(mWeakReference.get(), platform + "分享取消了");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ShareAction shareAction = new ShareAction(getActivity());

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }
}
