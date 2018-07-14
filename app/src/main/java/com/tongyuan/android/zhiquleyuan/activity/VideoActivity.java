package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyVolumeReq;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyVolumeRes;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtil;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.view.GlideCircleTransform;
import com.weiyicloud.whitepad.ControlMode;
import com.weiyicloud.whitepad.SharePadMgr;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.emm.meeting.MeetingUser;
import info.emm.meeting.Session;
import info.emm.meeting.SessionInterface;
import info.emm.sdk.VideoView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class VideoActivity extends AppCompatActivity implements View.OnClickListener, SessionInterface {
    private final String TAG = VideoActivity.class.getSimpleName();
    static public int WEIYI_VIDEO_OUT_SLOW = 1;       //视频发送速度慢
    static public int WEIYI_VIDEO_OUT_DISCONNECT = 2; //视频发送连接断开重连
    static public int WEIYI_VIDEO_IN_SLOW = 3;        //视频接收速度慢
    static public int WEIYI_VIDEO_IN_DISCONNECT = 4;  //视频接收连接断开重连
    static public int WEIYI_AUDIO_DISCONNECT = 10;    //音频连接断开重连
    static public int WEIYI_AUDIO_PERMISSION = 11;    //无法开启麦克风
    public VideoView mMy_video, mOther_video;

    public boolean hasfront = false;
    public boolean usefront = false;

    final private boolean hasAudio = true;

    public int _watchingPeerID = 0;
    public int _myPeerID = 0;
    public boolean _sendingVideo = false;
    public boolean _serverMix = false;
    public int _codec = 1;
    public ArrayList<Integer> _userList = new ArrayList<Integer>();
    public boolean _playAudio = false;
    public int uid;
    public boolean _freeSpeak = false;

    public static final String UPDATE_URL = "http://u.weiyicloud.com/";
    public static final String HOCKEY_APP_HASH = "dedae71020c1c014120ef0153cb8457c";
    public int _warningtime = 0;

    private ImageView bottom_action_end_call;
    private boolean change = false;
    private int _xDelta;
    private int _yDelta;
    public List<Integer> mCams = null;
    private ImageView mStopCall;
    private ImageView mNoVideo;
    private boolean isShowVideo = true;
    private String mRoomid;
    private String mToken;
    private String mToyid;
    private SeekBar mSeekBar;
    private TextView mVolume_text;
    private String mVolumeString = "50";
    private int mVolumeInt;
    private ImageView mBabyImg;
    private TextView mBabyName;
    private String mUserFlag;
    private String mBabyimgString;
    private String mBabynameString;
    private int toyId;
    private int tvId;

    public static void launch(Context context, String babyimgString, String babynameString, String roomid, String token, String toyId, String tvId) {
        Intent it = new Intent(context, VideoActivity.class);
        it.putExtra("babyimgString", babyimgString);
        it.putExtra("babynameString", babynameString);
        it.putExtra("roomid", roomid);
        it.putExtra("token", token);
        it.putExtra("toyId", toyId);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }

    public void setBabyImage(String babyImage) {
        mBabyimgString = babyImage;
    }

    @Override
    protected void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_videocall);

        int toyvolume = SPUtil.getInstance().getInt("toyvolume", 0);
        LogUtil.i("newbi  :" + String.valueOf(toyvolume));

        mMy_video = (VideoView) findViewById(R.id.surfaceView5);
// 其他的视频不显示了
//        mOther_video = (VideoView) findViewById(R.id.surfaceView6);
//        mOther_video.setZOrderOnTop(true);

        //停止视频
        mStopCall = (ImageView) findViewById(R.id.iv_fragment_videocall_stopcall);

        //不显示视频
        mNoVideo = (ImageView) findViewById(R.id.iv_fragment_videocall_novideo);

        //找到控制音量调整的接口

        //seekbar
        mSeekBar = (SeekBar) findViewById(R.id.volume_line);
        mVolumeInt = Integer.parseInt(mVolumeString);
        mSeekBar.setProgress(mVolumeInt);
        ImageButton volume_reduce = (ImageButton) findViewById(R.id.ib_volume_reduce);//音量加
        ImageButton volume_add = (ImageButton) findViewById(R.id.ib_volume_add);//音量减

        //音量的数字显示
        mVolume_text = (TextView) findViewById(R.id.tv_activity_videocall);
        //宝宝的头像
        mBabyImg = (ImageView) findViewById(R.id.iv_activity_videocall_babyimg);
        //宝宝的名字
        mBabyName = (TextView) findViewById(R.id.tv_activity_videocall_babyname);

        Intent intent = getIntent();
        mBabyimgString = intent.getStringExtra("babyimgString");
        mBabynameString = intent.getStringExtra("babynameString");
        mRoomid = intent.getStringExtra("roomid");
        mToken = intent.getStringExtra("token");
        mToyid = intent.getStringExtra("toyId");
        if (mBabyimgString == null) {
            ToastUtil.showToast(this, "二维码错误");
            return;
        }
        if (mBabyimgString.equals("")) {
            Glide.with(this).load(R.mipmap.default_babyimage).asBitmap().centerCrop().transform(new GlideCircleTransform(this)).into(mBabyImg);
        } else {
            Glide.with(this).load(mBabyimgString).asBitmap().centerCrop().transform(new GlideCircleTransform(this)).into(mBabyImg);
        }
        mBabyName.setText(mBabynameString);


        usefront = hasfront = Session.getInstance().Init(this, "demo", "", true);
        mCams = Session.getInstance().getCameraInfo();
        EnterMeeting();

//        seekBar.setOnClickListener(this);
        mSeekBar.setProgress(toyvolume);
        mVolume_text.setText(String.valueOf(toyvolume));
        mSeekBar.setMax(100);
        //TODO 给textview设置一个当前的音量值,这个值是从网络获取的 ,如果没有获取到就默认给50,这个值需不需要传给玩具再说!
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LogUtil.i("video", "onProgressChanged: " + progress);
                mVolumeString = String.valueOf(progress);
                mVolume_text.setText(mVolumeString);
                mVolumeInt = Integer.parseInt(mVolumeString);

                LogUtil.i("video", "onProgressChanged----changelistenera: " + mVolumeString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                LogUtil.i("videovolume", "volume" + progress);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //在松手的时候,把text通过网络传给玩具,通过接口传到服务器上
                int progress = seekBar.getProgress();
                String volume = String.valueOf(progress);
                pushValueToToy(volume);
                LogUtil.i("video", "onProgressChanged:(stoptrackingtouch " + progress);
            }
        });
        volume_reduce.setOnClickListener(this);
        volume_add.setOnClickListener(this);
        mStopCall.setOnClickListener(this);
        mNoVideo.setOnClickListener(this);

//        mMy_video.setOnClickListener(this);
//        mOther_video.setOnClickListener(this);
        Session.getInstance().registerListener(this);
        Session.getInstance().registerWhiteBroad(SharePadMgr.getInstance());

    }

    private void EnterMeeting() {
        Start(false, 0);
    }

    public void Start(boolean serverMix, int codec) {
        _serverMix = serverMix;
        _codec = codec;
        _warningtime = 0;

        uid = (int) (Math.random() * 100000);
        String ip = "www.weiyicloud.com";
        int port = 80;
//        String meetingId = "565955139";

//        String meetingId = mRoomid;
//        String ip = "test.weiyicloud.com";
//        String meetingId = "666666";
//        int port = 81;
//        String meetingId = "777777";
        String meetingId = mRoomid;
        Session.getInstance().setWebHttpServerAddress(ip + ":" + port);
        mUserFlag = "phone";
        Session.getInstance().switchCamera(usefront);

//        Session.getInstance().setCameraQuality(_checkHQ.isChecked());

        Session.getInstance().setLoudSpeaker(true);
        Session.getInstance().setCameraQuality(true);

        SharePadMgr.getInstance().setShareControl(Session.getInstance());
        SharePadMgr.getInstance().setAppContext(this);
        SharePadMgr.getInstance().setControlMode(ControlMode.fullcontrol);
        SharePadMgr.getInstance().setClient(Session.getInstance().client);

        /*
        * ip:服务器地址;
        * port:服务器端口;
        * uid:用户昵称;
        * meetingid:房间号;
        * meetingpwd:房间密码;
        * thirduid:指定一个用户id,在会议中识别身份;
        * usertype:用户身份:发布者1,观看者2,还有0;
        * paramMap:null即可
        * */

        Session.getInstance().joinmeeting(ip, port, mUserFlag + uid, meetingId, "", uid, 1, null);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_fragment_videocall_stopcall:
                Stop();
                StopCallServer();
                finish();
                break;
            case R.id.iv_fragment_videocall_novideo:
//                Session.getInstance().unplayVideo(_watchingPeerID, 0);
                //Session.getInstance().unwatchOtherVideo(_watchingPeerID,0);

//                Session.getInstance().PlayVideo(_myPeerID, false, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);

                if (isShowVideo) {
                    isShowVideo = !isShowVideo;
                    ToastUtil.showToast(this, "不看视频");
//                    TODO 添加不看视频的图片变换 mNoVideo.setImageResource(R.drawable.);

                    mMy_video.setBackgroundResource(R.drawable.videobackground_3x);
                } else {
                    isShowVideo = !isShowVideo;
                    ToastUtil.showToast(this, "看视频");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mMy_video.setBackground(null);
                    }
                    Session.getInstance().PlayVideo(_watchingPeerID, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
                }

                break;
            //不显示小的视频窗口了,也不让他有点击切换的事件了
//            case R.id.surfaceView6:
//                if (!change) {
//                    LogUtil.d("surfaceview", "surfaceview: +change1");
//                    Session.getInstance().PlayVideo(0, true, mOther_video, 0, 0, 1, 1, 0, false, 1, 0);
//                    Session.getInstance().PlayVideo(_watchingPeerID, true, mMy_video, 0, 0, 1, 1, 65, false, 1, 0);
//                    change = true;
//                } else {
//                    LogUtil.d("surfaceview", "surfaceview: +change2");
//                    Session.getInstance().PlayVideo(0, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
//                    Session.getInstance().PlayVideo(_watchingPeerID, true, mOther_video, 0, 0, 1, 1, 65, false, 1, 0);
//                    change = false;
//                }
//                break;

            case R.id.ib_volume_add:
                //先过去seekbar的progress的值
//                mVolumeInt = Integer.parseInt(mVolumeString);
//                int progressreduce = mVolumeInt;

                if (mVolumeInt <= 100) {
                    mVolumeInt = mVolumeInt + 1;
                    if (mVolumeInt > 100) {
                        mVolumeInt = 100;
                    }
//                    mSeekBar.setProgress(mVolumeInt);
                    String volumeAdd = String.valueOf(mVolumeInt);
                    mVolume_text.setText(volumeAdd);
                    mSeekBar.setProgress(mVolumeInt);
                    //然后请求网络,把数据传到网络上去
                    pushValueToToy(volumeAdd);
                }

                break;
            case R.id.ib_volume_reduce:
                //先过去seekbar的progress的值

                if (mVolumeInt >= 0) {
                    mVolumeInt = mVolumeInt - 1;
                    if (mVolumeInt < 0) {
                        mVolumeInt = 0;

                    }

//                    mSeekBar.setProgress(mVolumeInt);
//                    int secondaryProgress = mSeekBar.getSecondaryProgress();
//                    secondaryProgress = mVolumeInt;
                    String volumeReduce = String.valueOf(mVolumeInt);
                    mVolume_text.setText(volumeReduce);
                    mSeekBar.setProgress(mVolumeInt);
                    //然后请求网络,把数据传到网络上去
                    pushValueToToy(volumeReduce);

                }

                break;
            default:

                break;

        }

    }

    private void pushValueToToy(String volume) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        ControlToyVolumeReq.ParamBean paramBean = new ControlToyVolumeReq.ParamBean(mToyid, volume);
        ControlToyVolumeReq controlToyVolumeReq = new ControlToyVolumeReq("volume", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(controlToyVolumeReq);
        Call<ControlToyVolumeRes> controlToyVolumeResCall = allInterface.CONTROL_TOY_VOLUME_RES_CALL(s);
        controlToyVolumeResCall.enqueue(new Callback<ControlToyVolumeRes>() {
            @Override
            public void onResponse(Call<ControlToyVolumeRes> call, Response<ControlToyVolumeRes> response) {
                if (response.body().getCode().equals("0")) {
                    ToastUtil.showToast(getApplicationContext(), "推送成功");
                } else if (response.body().getCode().equals("-10009")) {
                    ToastUtil.showToast(getApplicationContext(), "玩具未登录,为获取到玩具DEVCODE");
                } else if (response.body().getCode().equals("-10006")) {
                    ToastUtil.showToast(getApplicationContext(), "用户未登录");
                }
            }

            @Override
            public void onFailure(Call<ControlToyVolumeRes> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), R.string.network_error);
                LogUtil.i(TAG, t.toString());


            }
        });

    }

    private void seeMe() {
        LogUtil.d("surfaceview", "surfaceview3 ");

        if (_myPeerID == 0)
            return;

        if (!_sendingVideo) {
            Session.getInstance().PlayVideo(0, true, mMy_video, 0, 0, 0, 0, 0, false, 0, 0);
            _sendingVideo = true;

        } else {
            Session.getInstance().PlayVideo(0, false, mMy_video, 0, 0, 0, 0, 0, true, 0, 0);
            _sendingVideo = false;

        }

    }

    private void seeYou() {
        LogUtil.d("surfaceview", "surfaceview4 ");

        if (_userList.size() == 0)
            return;
        if (_watchingPeerID == 0) {
            int peerID = _userList.get(0);//这里就是获取id的,userlist是装在房间里的用户的人数的容器.
            Session.getInstance().PlayVideo(peerID, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
            Session.getInstance().requestSpeaking(peerID);
            LogUtil.d("surfaceview", "surfaceview5 ");
            _watchingPeerID = peerID;
        } else {
            Session.getInstance().PlayVideo(_watchingPeerID, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
            Session.getInstance().requestSpeaking(_watchingPeerID);
            _watchingPeerID = 0;
        }
    }

    @Override
    public void onWarning(int warning) {
        String warningString;
        if (warning == WEIYI_VIDEO_OUT_SLOW)
            warningString = getString(R.string.weiyi_video_out_slow);
        else if (warning == WEIYI_VIDEO_OUT_DISCONNECT)
            warningString = getString(R.string.weiyi_video_out_disconnect);
        else if (warning == WEIYI_VIDEO_IN_SLOW)
            warningString = getString(R.string.weiyi_video_in_slow);
        else if (warning == WEIYI_VIDEO_IN_DISCONNECT)
            warningString = getString(R.string.weiyi_video_in_disconnect);
        else if (warning == WEIYI_AUDIO_DISCONNECT)
            warningString = getString(R.string.weiyi_audio_disconnect);
        else if (warning == WEIYI_AUDIO_PERMISSION)
            warningString = getString(R.string.weiyi_audio_pernission);
        else
            warningString = getString(R.string.weiyi_other);

        _warningtime++;
    }

    @Override
    public void onConnect(int status, int i1) {
        if (status == 0) {
            int meetingType = Session.getInstance().getMeetingType();
            if (meetingType == 0 || meetingType == 1 || meetingType == 2 || meetingType == 3 || meetingType == 4 || meetingType == 5 || meetingType
                    == 6) {

            } else {
                Session.getInstance().LeaveMeeting();
//                Toast.makeText(this, getString(R.string.meeting),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onDisConnect(int i) {
        _myPeerID = 0;
        _watchingPeerID = 0;
        uid = 0;
        _userList.clear();
    }

    @Override
    public void onUserIn(int peerId, boolean b) {
        _userList.add(peerId);
        MeetingUser user = Session.getInstance().getM_thisUserMgr().getUser(peerId);
        String name = user.getName();
        if (name.contains("toy")) {
            toyId = peerId;
            seeYou();
        }

        if (name.contains("tv")) {
            tvId = peerId;
            seeYou();
//            Session.getInstance().PlayVideo(toyId, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
        }
    }

    @Override
    public void onUserOut(MeetingUser meetingUser) {
        _userList.remove(new Integer(meetingUser.getPeerID()));
        if (!_serverMix && hasAudio)
            Session.getInstance().unplayAudio(meetingUser.getPeerID());
        if (_watchingPeerID == meetingUser.getPeerID()) {
            _watchingPeerID = 0;
        }
    }

    @Override
    public void onEnablePresence(int peerID) {
        _myPeerID = peerID;
        //画中画的时候可以开启
//        seeMe();
        seeYou();
        _freeSpeak = true;
        sendText(0, "i am " + android.os.Build.MODEL + android.os.Build.VERSION.RELEASE, android.os.Build.MODEL);
    }

    public void sendText(int toWhom, String text, String myname) {
        Session.getInstance().sendTextMessage(toWhom, text, null);
    }

    @Override
    public void onCallClientFunction(String s, int i, Object o) {

    }

    @Override
    public void onUserPropertyChange(int i, JSONObject jsonObject) {

    }

    @Override
    public void onRemotePubMsg(String s, int i, int i1, String s1, String s2, Object o) {

    }

    @Override
    public void onRemoteDelMsg(String s, int i, int i1, String s1, String s2, Object o) {

    }

    @Override
    public void onRecTextMsg(int i, int i1, String s, JSONObject jsonObject) {

    }

    @Override
    public void onCameraWillClose(Camera camera) {

    }

    @Override
    public void onPhotoTaken(boolean b, byte[] bytes) {

    }

    @Override
    public void onCameraDidOpen(Camera camera, boolean b, int i) {

    }

    @Override
    public void ChangeAudioStatus(int i, int i1) {

    }

    @Override
    public void syncVideoModeChange(boolean b, boolean b1) {

    }

    @Override
    public void showpage() {

    }

    @Override
    public void onPresentComplete() {
        Session.getInstance().requestSpeaking(_myPeerID);

    }

    @Override
    public void onVideoSizeChanged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void onGotMeetingProperty(JSONObject jsonObject) {

    }

    @Override
    public void onServerRecording(boolean b) {

    }

    @Override
    public void onFocusUserChange(int i, int i1) {

    }

    @Override
    public void onWhitePadPageCount(int i) {

    }

    @Override
    public void onFocusSipChange(int i, int i1) {

    }

    @Override
    public void onCallSipACK(int i, int i1) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Stop();
        //通知服务器,退出
        StopCallServer();
    }

    private void StopCallServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        CallToToyReq.ParamBean paramBean = new CallToToyReq.ParamBean(mToyid, "2", mRoomid, "");
        CallToToyReq callToToyReq = new CallToToyReq("contact_toy", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(callToToyReq);
        Call<CallToToyRes> callToToyResCall = allInterface.CALL_TO_TOY_RES_CALL(s);
        callToToyResCall.enqueue(new Callback<CallToToyRes>() {
            @Override
            public void onResponse(Call<CallToToyRes> call, Response<CallToToyRes> response) {
                String s1 = response.body().toString();
                LogUtil.i("55555", "onResponse: stopcallserver" + s1);
            }

            @Override
            public void onFailure(Call<CallToToyRes> call, Throwable t) {

            }
        });

    }

    private void Stop() {

        Session.getInstance().StopSpeaking();
        _freeSpeak = false;
        Session.getInstance().LeaveMeeting();
        _myPeerID = 0;
        _watchingPeerID = 0;
        _userList.clear();

    }

}
