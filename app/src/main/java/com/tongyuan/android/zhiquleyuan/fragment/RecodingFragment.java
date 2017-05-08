package com.tongyuan.android.zhiquleyuan.fragment;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.RecodingAdapter;
import com.tongyuan.android.zhiquleyuan.adapter.RecordAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.db.DBHelper;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by android on 2016/12/3.
 */

public class RecodingFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mRecordingButton;
    private AbsListView mLv_recoding;
    private File mFile;
    private RecodingAdapter mAdapter;
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
    private boolean isPlaying = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recordingRoot = initView(inflater);
        mDbHelper = new DBHelper(getActivity());
        return recordingRoot;

    }

    private View initView(LayoutInflater inflater) {
        View recordingRoot = inflater.inflate(R.layout.fragment_recoding, null);
        View itemRecordingHeader = inflater.inflate(R.layout.item_play_list_header, null);
        //录音布局
        mRecordingFrag = (RelativeLayout) recordingRoot.findViewById(R.id.rl_fragment_recording_recording);
        //录音完成布局
        mCompleteFrag = (RelativeLayout) recordingRoot.findViewById(R.id.rl_fragment_recording_complete);

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

        mLv_recoding = (AbsListView) recordingRoot.findViewById(R.id.lv_recoding);
        mItemRecordingListHeader = itemRecordingHeader.findViewById(R.id.tv_item_play_list_header);
        mControl = (RelativeLayout) recordingRoot.findViewById(R.id.rl_fragment_recoding_control);

        mBackArrow = (ImageView) recordingRoot.findViewById(R.id.iv_fragment_recording_back);
        //找到recording录音的布局
        mRecordingLayout = (RelativeLayout) mLv_recoding.findViewById(R.id.rl_fragment_recording_recording);
        mRecordingComplete = (RelativeLayout) mLv_recoding.findViewById(R.id.rl_fragment_recording_complete);

        mTextview_startrecording = (TextView) recordingRoot.findViewById(R.id.tv_startrecording);
        recordList = new ArrayList<File>();
//        View mHeadview = inflater.inflate(R.layout.fragment_recoding_headerview, null);
//        mHeadTitle = (TextView) mHeadview.findViewById(R.id.tv_recoding_title);

        return recordingRoot;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        //展示本地的recording,没有就去网络获取

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
                ToastUtil.showToast(getContext(), "点击了share");
                break;
            //推送到玩具
            case R.id.iv_fragent_recording_toy:
                ToastUtil.showToast(getContext(), "点击了send2toy");
                break;
            //编辑
            case R.id.iv_fragent_recording_editor:
                ToastUtil.showToast(getContext(), "点击了editor");
                break;
            //向下移动
            case R.id.iv_fragent_recording_down:

                ToastUtil.showToast(getContext(), "点击了downaction");
                break;
            //试听
            case R.id.iv_recoding_tool_trylistener:
                //播放刚录完的音频,获得刚录完的音频的路劲

                if (mMediaPlayer == null) {
                    mMediaPlayer = new MediaPlayer();
                }
//                if (!mMediaPlayer.isPlaying()) {
//                if (!isPlaying) {
//                    //如果没有播放,就让他播放,并且变换播放的图片,隐藏相应的布局
//                    mTryListener.setVisibility(View.INVISIBLE);
//                    startPlayRecord();
//                    isPlaying = true;
//                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//                            isPlaying = false;
//                            stopAnimation();
//                            mPlayStopCircle.setVisibility(View.INVISIBLE);
//                            mPlayStop.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                    ToastUtil.showToast(getContext(), "开始试听");
//                } else if (isPlaying == true) {
//                    stopPlayRecord();
//                    isPlaying = false;
//                    ToastUtil.showToast(getContext(), "结束试听");
//                }
//                } else {
//                    stopPlayRecord();
//                isPlaying = false;
//                ToastUtil.showToast(getContext(), "结束试听");
//                }
                if (!isPlaying){

                }
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

            default:
                break;
        }
    }

    private void SaveRecording() {
        if (isPlaying == true) {
            stopPlayRecord();
        }
        recordList.add(mFile);
        showFile(recordList);
        mRecordingFrag.setVisibility(View.VISIBLE);
        mCompleteFrag.setVisibility(View.INVISIBLE);
    }

    private void reRecording() {
        if (isPlaying == true) {
            isPlaying = false;
            stopPlayRecord();
            mCompleteFrag.setVisibility(View.GONE);
            mRecordingFrag.setVisibility(View.VISIBLE);
        }
        mCompleteFrag.setVisibility(View.GONE);
        mRecordingFrag.setVisibility(View.VISIBLE);
    }

    //播放录音
    private void startPlayRecord() {

//        if (mMediaPlayer == null) {
//            mMediaPlayer = new MediaPlayer();
//        }

        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mFile.getAbsolutePath());
            mMediaPlayer.prepare();
            mMediaPlayer.start();

            mPlayStop.setVisibility(View.VISIBLE);
            mPlayStopCircle.setVisibility(View.VISIBLE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        startAnimation();

    }

    //停止播放录音
    private void stopPlayRecord() {

        mMediaPlayer.pause();

//        mMediaPlayer.reset();

//        mPlayStop.setVisibility(View.INVISIBLE);
//        mTryListener.setVisibility(View.VISIBLE);
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
                mMediaRecorder = new MediaRecorder();
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

                String dir = Environment.getExternalStorageDirectory().getPath() + "/AAmart";
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/AAmart");
                if (!file.exists()) {
                    file.mkdirs();
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 " + "HH时mm分ss秒");
                String date = simpleDateFormat.format(new Date());
                mFile = new File(dir, date.toString() + ".mp3");
                mMediaRecorder.setOutputFile(mFile.getAbsolutePath());
                mMediaRecorder.prepare();
                mMediaRecorder.start();
//                startTimer();//时间计时

                isNotRecording = !isNotRecording;
                ToastUtil.showToast(getActivity(), "开始录音");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

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
            Date date = new Date(length);
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

    private void startTimer() {
        stopTimer();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimer();
            }


        }, 0, 1000);
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        showFile(recordList);

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}
