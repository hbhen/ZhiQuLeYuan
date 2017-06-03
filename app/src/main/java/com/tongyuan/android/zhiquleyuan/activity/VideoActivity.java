package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
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

    @Override
    protected void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_videocall);

        //TODO  进来的时候还要获取一个玩具的初始音量 现在不加了


        mMy_video = (VideoView) findViewById(R.id.surfaceView5);
        mOther_video = (VideoView) findViewById(R.id.surfaceView6);
        mOther_video.setZOrderOnTop(true);
        //停止视频
        mStopCall = (ImageView) findViewById(R.id.iv_fragment_videocall_stopcall);
        //不显示视频

        mNoVideo = (ImageView) findViewById(R.id.iv_fragment_videocall_novideo);

        //找到控制音量调整的接口

        //seekbar
        mSeekBar = (SeekBar) findViewById(R.id.volume_line);
        ImageButton volume_reduce = (ImageButton) findViewById(R.id.ib_volume_reduce);//音量加
        ImageButton volume_add = (ImageButton) findViewById(R.id.ib_volume_add);//音量减
        //音量的数字显示
        mVolume_text = (TextView) findViewById(R.id.tv_activity_videocall);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        mRoomid = extras.getString("roomid");
        mToken = extras.getString("token");
        mToyid = extras.getString("toyid");

        usefront = hasfront = Session.getInstance().Init(this, "demo", "", true);
        mCams = Session.getInstance().getCameraInfo();
        EnterMeeting();

//        seekBar.setOnClickListener(this);
        mSeekBar.setMax(100);
        //TODO 给textview设置一个当前的音量值,这个值是从网络获取的 ,如果没有获取到就默认给50,这个值需不需要传给玩具再说!
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVolume_text.setText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //在松手的时候,把text通过网络传给玩具,通过接口传到服务器上
            }
        });
        volume_reduce.setOnClickListener(this);
        volume_add.setOnClickListener(this);
        mStopCall.setOnClickListener(this);
        mNoVideo.setOnClickListener(this);

//        mMy_video.setOnClickListener(this);
        mOther_video.setOnClickListener(this);
        Session.getInstance().registerListener(this);
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

        Session.getInstance().joinmeeting(ip, port, "user" + uid, meetingId, "", uid, 0, null);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_fragment_videocall_stopcall:

//                Session.getInstance().LeaveMeeting();
//                showFragment(ToyDetailsFragment.class.getSimpleName());
                Stop();
                StopCallServer();
                finish();
                ToastUtil.showToast(this, "取消");
                break;
            case R.id.iv_fragment_videocall_novideo:
                ToastUtil.showToast(this, "不看视频");
//                if (isShowVideo) {
//                    isShowVideo = !isShowVideo;
//                    //不显示
////                    mMy_video.setBackgroundResource(R.drawable.videobackground_3x);
//                } else {
//                    isShowVideo = !isShowVideo;
//                }

                break;
            case R.id.surfaceView6:
                if (!change) {
                    Log.d("surfaceview", "surfaceview: +change1");
                    Session.getInstance().PlayVideo(0, true, mOther_video, 0, 0, 1, 1, 0, false, 1, 0);
                    Session.getInstance().PlayVideo(_watchingPeerID, true, mMy_video, 0, 0, 1, 1, 65, false, 1, 0);
                    change = true;
                } else {
                    Log.d("surfaceview", "surfaceview: +change2");
                    Session.getInstance().PlayVideo(0, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
                    Session.getInstance().PlayVideo(_watchingPeerID, true, mOther_video, 0, 0, 1, 1, 65, false, 1, 0);
                    change = false;
                }
                break;
//            case R.id.volume_line:
//
//                break;
            case R.id.ib_volume_add:

                //先过去seekbar的progress的值
                int progressreduce = mSeekBar.getProgress();
                if (progressreduce <= 100) {
                    progressreduce = progressreduce + 5;
                    mSeekBar.setSecondaryProgress(progressreduce);
                    String s = String.valueOf(progressreduce);
                    mVolume_text.setText(s);
                    //然后请求网络,把数据传到网络上去
                }

                break;
            case R.id.ib_volume_reduce:
                //先过去seekbar的progress的值
                int progressadd = mSeekBar.getProgress();
                if (progressadd >= 0) {
                    progressadd = progressadd - 5;
                    mSeekBar.setSecondaryProgress(progressadd);
                    String s = String.valueOf(progressadd);
                    mVolume_text.setText(s);
                    //然后请求网络,把数据传到网络上去

                }

                break;
            default:

                break;
        }
    }

    private void seeMe() {
        Log.d("surfaceview", "surfaceview3 ");

        if (_myPeerID == 0)
            return;

        if (!_sendingVideo) {
            Session.getInstance().PlayVideo(0, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
            _sendingVideo = true;

        } else {
            Session.getInstance().PlayVideo(0, false, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
            _sendingVideo = false;

        }
    }

    private void seeYou() {
        Log.d("surfaceview", "surfaceview4 ");
        if (_userList.size() == 0)
            return;

        if (_watchingPeerID == 0) {
            int peerID = _userList.get(0);
            Session.getInstance().PlayVideo(peerID, true, mOther_video, 0, 0, 1, 1, 1, false, 0, 0);
            Log.d("surfaceview", "surfaceview5 ");
            _watchingPeerID = peerID;
        } else {
            Session.getInstance().PlayVideo(_watchingPeerID, false, mOther_video, 0, 0, 1, 1, 1, false, 1, 0);
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

        seeYou();
        //		}
        if (!_serverMix && hasAudio)
            Session.getInstance().playAudio(peerId);
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
        seeMe();
//        seeYou();
        Session.getInstance().requestSpeaking(_myPeerID);
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
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        CallToToyReq.ParamBean paramBean = new CallToToyReq.ParamBean(mToyid, "2", mRoomid);
        CallToToyReq callToToyReq = new CallToToyReq("contact_toy", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(callToToyReq);
        Call<CallToToyRes> callToToyResCall = allInterface.CALL_TO_TOY_RES_CALL(s);
        callToToyResCall.enqueue(new Callback<CallToToyRes>() {
            @Override
            public void onResponse(Call<CallToToyRes> call, Response<CallToToyRes> response) {
                String s1 = response.body().toString();
                Log.i("55555", "onResponse: stopcallserver" + s1);
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
