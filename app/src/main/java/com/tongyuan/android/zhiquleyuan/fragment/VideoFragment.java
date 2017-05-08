package com.tongyuan.android.zhiquleyuan.fragment;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tongyuan.android.zhiquleyuan.R;
import com.weiyicloud.whitepad.ControlMode;
import com.weiyicloud.whitepad.SharePadMgr;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.emm.meeting.MeetingUser;
import info.emm.meeting.Session;
import info.emm.meeting.SessionInterface;
import info.emm.sdk.VideoView;

/**
 * Created by android on 2017/2/28.
 */

public class VideoFragment extends Fragment implements View.OnClickListener, SessionInterface {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View videoCallView = inflater.inflate(R.layout.fragment_videocall, null);
        mMy_video = (VideoView) videoCallView.findViewById(R.id.surfaceView5);
        mOther_video = (VideoView) videoCallView.findViewById(R.id.surfaceView6);
        View viewById = getActivity().findViewById(R.id.ll_bottom);
        View viewById1 = getActivity().findViewById(R.id.fl_activity_main);
        viewById1.setVisibility(View.GONE);
        viewById.setVisibility(View.GONE);
        return videoCallView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        usefront = hasfront = Session.getInstance().Init(getActivity(), "demo", "", true);
        mCams = Session.getInstance().getCameraInfo();
        EnterMeeting();

        mMy_video.setOnClickListener(this);
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
        String ip = "test.weiyicloud.com";
//        String ip = "www.weiyicloud.com";
        int port = 81;
//        int port = 80;
//        String meetingId = "666666";
        String meetingId = "666666";
        Session.getInstance().setWebHttpServerAddress(ip + ":" + port);
        Session.getInstance().switchCamera(usefront);
//        Session.getInstance().setCameraQuality(_checkHQ.isChecked());
        Session.getInstance().setLoudSpeaker(true);
        Session.getInstance().setCameraQuality(false);

        SharePadMgr.getInstance().setShareControl(Session.getInstance());
        SharePadMgr.getInstance().setAppContext(getActivity());
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
        if (!change) {
            Log.d("surfaceview", "surfaceview: +change1");
//            Session.getInstance().PlayVideo(0, true, mOther_video, 0, 0, 1, 1, 0, false, 1, 0);
            Session.getInstance().PlayVideo(_watchingPeerID, true, mMy_video, 0, 0, 1, 1, 65, false, 1, 0);
            change = true;
        } else {
            Log.d("surfaceview", "surfaceview: +change2");
//            Session.getInstance().PlayVideo(0, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
            Session.getInstance().PlayVideo(_watchingPeerID, true, mOther_video, 0, 0, 1, 1, 65, false, 1, 0);
            change = false;
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
