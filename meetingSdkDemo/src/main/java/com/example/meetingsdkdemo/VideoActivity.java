//package com.example.meetingsdkdemo;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.hardware.Camera;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Toast;
//
//import com.weiyicloud.whitepad.ControlMode;
//import com.weiyicloud.whitepad.SharePadMgr;
//
//import org.json.JSONObject;
//
//import info.emm.meeting.MeetingUser;
//import info.emm.meeting.Session;
//import info.emm.meeting.SessionInterface;
//import info.emm.sdk.VideoView;
//
///**
// * Created by android on 2017/2/12.
// */
//
//public class SecondActivity extends AppCompatActivity implements SessionInterface {
//
//    static public int WEIYI_VIDEO_OUT_SLOW = 1;       //视频发送速度慢
//    static public int WEIYI_VIDEO_OUT_DISCONNECT = 2; //视频发送连接断开重连
//    static public int WEIYI_VIDEO_IN_SLOW = 3;        //视频接收速度慢
//    static public int WEIYI_VIDEO_IN_DISCONNECT = 4;  //视频接收连接断开重连
//    static public int WEIYI_AUDIO_DISCONNECT = 10;    //音频连接断开重连
//    static public int WEIYI_AUDIO_PERMISSION = 11;    //无法开启麦克风
//
//    public int _warningtime = 0;
//    VideoView surfaceView1;
//    VideoView surfaceView2;
//    boolean IsClient;
//    int _myPeerID;
//    int _clientID;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.second_activity);
//        Session.getInstance().registerListener(this);
//        Session.getInstance().registerWhiteBroad(SharePadMgr.getInstance());
//         Session.getInstance().Init(getApplicationContext(), "demo", "", true);
//         Session.getInstance().getCameraInfo();
//        Start(false, 0);
//        surfaceView2 = (VideoView) findViewById(R.id.surfaceView6);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//        {
//            boolean checkPermissionResult = checkSelfPermissions();
//            if (checkPermissionResult) {
//                configs();
//            }
//        }else
//        {
//            configs();
//        }
//
//
//
//
//
//
//    }
//    public void configs(){
////        Session.getInstance().registerWhiteBroad(SharePadMgr.getInstance());
//         surfaceView1 = (VideoView) findViewById(R.id.surfaceView1);
//
//
//
//
//
//        surfaceView2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (!imm.isActive()) {
//                    return;
//                }
////        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//                if (!IsClient){
//                    Session.getInstance().PlayVideo(_clientID , true, surfaceView1, 0, 0, 1, 1,1, false, 0, 0);
//                    Session.getInstance().PlayVideo(_myPeerID, true, surfaceView2, 0, 0, 1, 1, 1, false, 0, 0);
//                }else {
//                    Session.getInstance().PlayVideo(_clientID, true, surfaceView2, 0, 0, 1, 1, 1, false, 0, 0);
//                    Session.getInstance().PlayVideo(_myPeerID, true, surfaceView1, 0, 0, 1, 1, 1, false, 0, 0);
//                }
//                IsClient = !IsClient;
//            }
//        });
//
//
//
//
//
//    }
//    public boolean checkSelfPermission(String permission, int requestCode)
//    {
////        log.debug("checkSelfPermission " + permission + " " + requestCode);
//        if (ContextCompat.checkSelfPermission(this,
//                permission)
//                != PackageManager.PERMISSION_GRANTED)
//        {
//
//            ActivityCompat.requestPermissions(this,
//                    new String[]{permission},
//                    requestCode);
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean checkSelfPermissions()
//    {
//        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, ConstantApp.PERMISSION_REQ_ID_RECORD_AUDIO))
//        {
//            return true;
//        }
//
//        return false;
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String permissions[], @NonNull int[] grantResults)
//    {
////        log.debug("onRequestPermissionsResult " + requestCode + " " + Arrays.toString(permissions) + " " + Arrays.toString(grantResults));
//        switch (requestCode) {
//            case ConstantApp.PERMISSION_REQ_ID_RECORD_AUDIO: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    checkSelfPermission(Manifest.permission.CAMERA, ConstantApp.PERMISSION_REQ_ID_CAMERA);
//                } else {
//                    finish();
//                }
//                break;
//            }
//            case ConstantApp.PERMISSION_REQ_ID_CAMERA: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                {
//                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, ConstantApp.PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE);
//                } else {
//                    finish();
//                }
//                break;
//            }
//            case ConstantApp.PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE:
//            {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                {
////                    String rid = JPushInterface.getRegistrationID(getApplicationContext());
////                    Log.i("33",rid+"");
//                    configs();
////                    Intent intent = new Intent(this, com.toy.video.mytoy.ui.MainActivity.class);
////                    intent.putExtra("rid",rid);
////                    startActivity(intent);
//                } else {
//                    finish();
//                }
//                break;
//            }
//        }
//    }
//
//    public void Start(boolean serverMix, int codec)
//    {
////        _buttonStart.setEnabled(false);
////        _activity._serverMix = serverMix;
////        _activity._codec = codec;
////        _activity._warningtime = 0;
//
//        int uid = (int)(Math.random() * 100000);
//        String ip = "test.weiyicloud.com".toString();
//        int port  = 81;
//        String meetingId = "777777";
//        Session.getInstance().setWebHttpServerAddress(ip+":"+port);
//        Session.getInstance().switchCamera(false);
//        Session.getInstance().setCameraQuality(false);
//        Session.getInstance().setLoudSpeaker(true);
//
//        SharePadMgr.getInstance().setShareControl(Session.getInstance());
//        SharePadMgr.getInstance().setAppContext(this);
//        SharePadMgr.getInstance().setControlMode(ControlMode.fullcontrol);
//        SharePadMgr.getInstance().setClient(Session.getInstance().client);
//        Session.getInstance().joinmeeting(ip, port, "user" +uid , meetingId, "", uid, 0, null);
//
//    }
//
//    /**
//     * 提示权限开启
//     */
//    @Override
//    public void onWarning(int warning)
//    {
//        String warningString;
//        if(warning == WEIYI_VIDEO_OUT_SLOW)
//            warningString = getString(R.string.weiyi_video_out_slow);
//        else if(warning == WEIYI_VIDEO_OUT_DISCONNECT)
//            warningString = getString(R.string.weiyi_video_out_disconnect);
//        else if(warning == WEIYI_VIDEO_IN_SLOW)
//            warningString = getString(R.string.weiyi_video_in_slow);
//        else if(warning == WEIYI_VIDEO_IN_DISCONNECT)
//            warningString = getString(R.string.weiyi_video_in_disconnect);
//        else if(warning == WEIYI_AUDIO_DISCONNECT)
//            warningString = getString(R.string.weiyi_audio_disconnect);
//        else if(warning == WEIYI_AUDIO_PERMISSION)
//            warningString = getString(R.string.weiyi_audio_pernission);
//        else
//            warningString = getString(R.string.weiyi_other);
//
//        _warningtime++;
////        log("Warning" + _warningtime + " " + warningString);
//    }
//    /**
//     * 链接服务器
//     */
//    @Override
//    public void onConnect(int status, int quality)
//    {
//        Log.i("aaa","onConnect " + status + " " + quality);
////        _pager.setCanScroll(true);
//        if(status == 0){
//            int meetingType = Session.getInstance().getMeetingType();
//            if(meetingType == 0 || meetingType == 1 || meetingType == 2 || meetingType == 3 || meetingType == 4 || meetingType == 5 ||
// meetingType == 6){
//            }else{
//                Session.getInstance().LeaveMeeting();
//                Toast.makeText(this, getString(R.string.meeting),Toast.LENGTH_LONG).show();
//            }
//        }else{
////            if(_fragment_meeting._buttonStart != null)
////                _fragment_meeting._buttonStart.setEnabled(true);
////            Session.getInstance().setM_bInmeeting(false);
////            onConnect(SecondActivity.this, status);
//        }
//    }
//
//    /**
//     * 调用会议中某人的某个与会者定义的方法，被调用者会收到回调
//     * name:消息名称
//     * peerID:消息发送者id
//     * params:消息数据
//     *
//     *查看视频   不查看视频
//     */
//    @Override
//    public void onCallClientFunction(String name, int peerID, Object params) {
//        // TODO Auto-generated method stub
//        Log.i("aaa","onCallClientFunction Name"+name +"peerId"+peerID +"params"+params);
//
//    }
//
//    /**
//     * 断开连接
//     */
//    @Override
//    public void onDisConnect(int arg0) {
//        Log.i("aaa","onDisconnect " + arg0);
////        _myPeerID = 0;
////        _watchingPeerID = 0;
////        uid = 0;
////        _userList.clear();
////        _pager.setCanScroll(false);
////        if(_fragment_meeting._buttonSend != null)
////        {
////            _fragment_meeting._buttonSend.setText(R.string.main_send_video);
////            _fragment_meeting._buttonWatch.setText(R.string.main_watch_video);
////            _fragment_meeting._buttonFreeSpeak.setText(R.string.main_free_speaker);
////            _fragment_meeting._buttonStart.setEnabled(true);
////        }
//    }
//
//    /**
//     * 用户出席会议成功
//     */
//    @Override
//    public void onEnablePresence(int peerID) {
//
//        _myPeerID = peerID;
//        Session.getInstance().PlayVideo(_myPeerID, true, surfaceView1, 0, 0, 1, 1, 1, false, 0, 0);
//
////        sendText(0, "i am "+android.os.Build.MODEL+android.os.Build.VERSION.RELEASE, android.os.Build.MODEL);
//    }
//
//    /**
//     *  文本消息
//     * fromid:发消息的user id
//     * type：消息类型
//     * msg：消息内容
//     */
//    @Override
//    public void onRecTextMsg(int fromid, int type, String msg, JSONObject arg3) {
////        OnReceiveText(fromid,type, msg, arg3);
//    }
//
//
//    @Override
//    public void onRemoteDelMsg(String msgName,int fromID,int associatedUserID,
//                               String id,String associatedMsgID,Object body) {
//        Log.i("aaa","onRemoteDelMsg msgName"+msgName+"fromId"+fromID);
//    }
//
//    /**
//     * 收到远程消息
//     * msgName：消息名
//     * fromId：发消息的user的id
//     * associatedUserID：关联userid
//     *
//     */
//    @Override
//    public void onRemotePubMsg(String msgName,int fromID,int associatedUserID,
//                               String id,String associatedMsgID,Object body) {
//        Log.i("aaa","onRemotePubMsg msgName"+msgName+"fromId"+fromID);
//
//    }
//    /**
//     * 用户进入会议
//     */
//    @Override
//    public void onUserIn(int peerId,boolean inList)
//    {
////        Session.getInstance().PlayVideo(peerId, true, surfaceView2, 0, 0, 1, 1, 0, false, 0, 0);
//        _clientID = peerId;
////        Session.getInstance().PlayVideo(peerId, true, surfaceView2, 0, 0, 1, 1, 0, false, 0, 0);
//
//        Log.i("aaa","ClientFunc_UserIn " + peerId);
////        //		if(!inList){
////        _userList.add(peerId);
////        //		}
////        if(!_serverMix && hasAudio)
////            Session.getInstance().playAudio(peerId);
//    }
//
//
//    /**
//     * 用户退出会议
//     */
//    @Override
//    public void onUserOut(MeetingUser arg0) {
//        Log.i("aaa","ClientFunc_UserOut " + arg0.getPeerID());
////        _userList.remove(new Integer(arg0.getPeerID()));
////
////        if(!_serverMix && hasAudio)
////            Session.getInstance().unplayAudio(arg0.getPeerID());
////        if(_watchingPeerID == arg0.getPeerID())
////        {
////            _watchingPeerID = 0;
////            if(_fragment_meeting != null && _fragment_meeting._buttonWatch != null)
////                _fragment_meeting._buttonWatch.setText(R.string.main_watch_video);
////        }
//
//    }
//
//    /**
//     * 有与会者User属性发生变化
//     * peerID:变化user的id
//     * arg0:变化的属性
//     */
//    @Override
//    public void onUserPropertyChange(int peerID, JSONObject arg0) {
//        Log.i("aaa","UserChange PeerID" + peerID+"JsonObject"+ arg0);
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Session.getInstance().LeaveMeeting();
////        _myPeerID = 0;
////        uid = 0;
////        _watchingPeerID = 0;
////        _userList.clear();
//    }
//
//
//    @Override
//    public void ChangeAudioStatus(int arg0, int arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void onCameraDidOpen(Camera arg0, boolean arg1, int arg2) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void onCameraWillClose(Camera arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onPhotoTaken(boolean b, byte[] bytes) {
//
//    }
//
//
//    @Override
//    public void onPresentComplete() {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void showpage() {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void syncVideoModeChange(boolean arg0, boolean arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//
//
//    @Override
//    public void onGotMeetingProperty(JSONObject arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void onServerRecording(boolean arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//
////    private static void onConnect(Activity activity, int nRet)
////    {
////        Log.e("emm", "check meeting failed and result="+nRet);
////        if(activity==null){
////            return;
////        }
////        if (nRet == 4008) {
////            Toast.makeText(activity,R.string.checkmeeting_error_4008, Toast.LENGTH_LONG).show();
////        } else if (nRet == 4110) {
////            Toast.makeText(activity,R.string.checkmeeting_error_4110, Toast.LENGTH_LONG).show();
////        } else if (nRet == 4007) {
////            Toast.makeText(activity,R.string.checkmeeting_error_4007, Toast.LENGTH_LONG).show();
////        } else if (nRet == 3001) {
////            Toast.makeText(activity,R.string.checkmeeting_error_3001, Toast.LENGTH_LONG).show();
////        } else if (nRet == 3002) {
////            Toast.makeText(activity,R.string.checkmeeting_error_3002, Toast.LENGTH_LONG).show();
////        } else if (nRet == 3003) {
////            Toast.makeText(activity,R.string.checkmeeting_error_3003, Toast.LENGTH_LONG).show();
////        } else if (nRet == 4109) {
////            Toast.makeText(activity,R.string.checkmeeting_error_4109, Toast.LENGTH_LONG).show();
////        } else if (nRet == 4103) {
////            Toast.makeText(activity,R.string.checkmeeting_error_4103, Toast.LENGTH_LONG).show();
////        } else {
////            Toast.makeText(activity,R.string.WaitingForNetwork, Toast.LENGTH_LONG).show();
////        }
////
////    }
//
//
//    @Override
//    public void onCallSipACK(int arg0, int arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void onFocusSipChange(int arg0, int arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void onFocusUserChange(int arg0, int arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void onVideoSizeChanged(int arg0, int arg1, int arg2, int arg3) {
//        // TODO Auto-generated method stub
//
//    }
//
//
//    @Override
//    public void onWhitePadPageCount(int arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//}
package com.example.meetingsdkdemo;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.weiyicloud.whitepad.ControlMode;
import com.weiyicloud.whitepad.Face_Share_Fragment;
import com.weiyicloud.whitepad.SharePadMgr;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;
import net.hockeyapp.android.Strings;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.emm.meeting.MeetingUser;
import info.emm.meeting.Session;
import info.emm.meeting.SessionInterface;

/**
 * Created by lenovo on 2017/2/8.
 */
public class VideoActivity extends AppCompatActivity implements SessionInterface, View.OnClickListener, Face_Share_Fragment.penClickListener, View
        .OnTouchListener {

    static public int WEIYI_VIDEO_OUT_SLOW = 1;       //视频发送速度慢
    static public int WEIYI_VIDEO_OUT_DISCONNECT = 2; //视频发送连接断开重连
    static public int WEIYI_VIDEO_IN_SLOW = 3;        //视频接收速度慢
    static public int WEIYI_VIDEO_IN_DISCONNECT = 4;  //视频接收连接断开重连
    static public int WEIYI_AUDIO_DISCONNECT = 10;    //音频连接断开重连
    static public int WEIYI_AUDIO_PERMISSION = 11;    //无法开启麦克风

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
    public List<Integer> _cams = null;


    private info.emm.sdk.VideoView my_video, other_video;
    private ImageView bottom_action_end_call;
    private boolean change = false;
    private int _xDelta;
    private int _yDelta;
    public String userFlag = "phone";
//    private PercentRelativeLayout _root;
//    private CircleImageView call_toy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        usefront = hasfront = Session.getInstance().Init(getApplicationContext(), "demo", "", true);
        _cams = Session.getInstance().getCameraInfo();
        EnterMeeting();
        initView();
        initListener();


////        checkForCrashes();
        Session.getInstance().registerListener(this);
        Session.getInstance().registerWhiteBroad(SharePadMgr.getInstance());


    }


    private void initView() {
//        _root = (PercentRelativeLayout) findViewById(R.id._root);
//        other_video = (info.emm.sdk.VideoView) findViewById(R.id.surfaceView6);
//        other_video.setOnTouchListener(this);
        other_video = (info.emm.sdk.VideoView) findViewById(R.id.surfaceView5);
//        bottom_action_end_call = (ImageView) findViewById(R.id.bottom_action_end_call);
//        call_toy = (CircleImageView) findViewById(R.id.call_toy);
//        String wanju = SharePrefUtil.getString(this,"wanjuHead","");
//        if (!wanju.equals("")){
//            Picasso.with(this).load(wanju).into(call_toy);
//        }
    }

    private void initListener() {


//        other_video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!change) {
//                    Log.d("surfaceview", "surfaceview: +change1");
//
//                    Session.getInstance().PlayVideo(0, true, other_video, 0, 0, 1, 1, 0, false, 1, 0);
//                    Session.getInstance().PlayVideo(_watchingPeerID, true, my_video, 0, 0, 1, 1, 65, false, 1, 0);
//                    change = true;
//                } else {
//                    Log.d("surfaceview", "surfaceview: +change2");
//                    Session.getInstance().PlayVideo(0, true, my_video, 0, 0, 1, 1, 0, false, 1, 0);
//                    Session.getInstance().PlayVideo(_watchingPeerID, true, other_video, 0, 0, 1, 1, 65, false, 1, 0);
//                    change = false;
//                }
//            }
//        });

//        bottom_action_end_call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Stop();
//                finish();
//            }
//        });


    }

    public boolean onTouch(View view, MotionEvent event) {
        int X = (int) event.getRawX();
        int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;

                Log.i("aaab", "X" + X + "***" + "Y" + Y + "***" + "_xDelta" + _xDelta + "****" + "_yDelta" + _yDelta);

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                int XX = (int) event.getRawX();
                int YY = (int) event.getRawY();
                X = XX;
                Y = YY;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
//                layoutParams.rightMargin = -250;
//                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
//        _root.invalidate();
        return true;
    }


    private void checkForCrashes() {
        CrashManagerListener listener = new CrashManagerListener() {
            public String getStringForResource(int resourceID) {
                switch (resourceID) {
                    case Strings.CRASH_DIALOG_MESSAGE_ID:
                        return getResources().getString(R.string.crash_dialog_message);
                    case Strings.CRASH_DIALOG_NEGATIVE_BUTTON_ID:
                        return getResources().getString(R.string.crash_dialog_negative_button);
                    case Strings.CRASH_DIALOG_POSITIVE_BUTTON_ID:
                        return getResources().getString(R.string.crash_dialog_positive_button);
                    case Strings.CRASH_DIALOG_TITLE_ID:
                        return getResources().getString(R.string.crash_dialog_title);
                    default:
                        return null;
                }
            }
        };

        CrashManager.register(this, UPDATE_URL, HOCKEY_APP_HASH, listener);
    }


    public void sendText(int toWhom, String text, String myname) {
        Session.getInstance().sendTextMessage(toWhom, text, null);
    }


    private static void onConnect(Activity activity, int nRet) {
//        Log.e("emm", "check meeting failed and result="+nRet);
        if (activity == null) {
            return;
        }
        if (nRet == 4008) {
            Toast.makeText(activity, R.string.checkmeeting_error_4008, Toast.LENGTH_LONG).show();
        } else if (nRet == 4110) {
            Toast.makeText(activity, R.string.checkmeeting_error_4110, Toast.LENGTH_LONG).show();
        } else if (nRet == 4007) {
            Toast.makeText(activity, R.string.checkmeeting_error_4007, Toast.LENGTH_LONG).show();
        } else if (nRet == 3001) {
            Toast.makeText(activity, R.string.checkmeeting_error_3001, Toast.LENGTH_LONG).show();
        } else if (nRet == 3002) {
            Toast.makeText(activity, R.string.checkmeeting_error_3002, Toast.LENGTH_LONG).show();
        } else if (nRet == 3003) {
            Toast.makeText(activity, R.string.checkmeeting_error_3003, Toast.LENGTH_LONG).show();
        } else if (nRet == 4109) {
            Toast.makeText(activity, R.string.checkmeeting_error_4109, Toast.LENGTH_LONG).show();
        } else if (nRet == 4103) {
            Toast.makeText(activity, R.string.checkmeeting_error_4103, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, R.string.WaitingForNetwork, Toast.LENGTH_LONG).show();
        }

    }


    private void EnterMeeting() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (!imm.isActive()) {
//            return;
//        }
//        imm.hideSoftInputFromWindow(this.getWindowToken(), 0);

        Start(false, 0);
    }


    public void Start(boolean serverMix, int codec) {

        _serverMix = serverMix;
        _codec = codec;
        _warningtime = 0;

        uid = (int) (Math.random() * 100000);
        String ip = "test.weiyicloud.com";
        int port = 81;
        String meetingId = "666666";
        Session.getInstance().setWebHttpServerAddress(ip + ":" + port);
        Session.getInstance().switchCamera(usefront);
//        Session.getInstance().setCameraQuality(_checkHQ.isChecked());
        Session.getInstance().setLoudSpeaker(true);

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


    private void Stop() {
        Session.getInstance().StopSpeaking();
        _freeSpeak = false;
        Session.getInstance().LeaveMeeting();
        _myPeerID = 0;
        _watchingPeerID = 0;
        _userList.clear();
    }


    private void seeMe() {
        Log.d("surfaceview", "surfaceview3 ");

        if (_myPeerID == 0)
            return;

        if (!_sendingVideo) {
            Session.getInstance().PlayVideo(0, true, other_video, 0, 0, 1, 1, 0, false, 1, 0);
            _sendingVideo = true;

        } else {
            Session.getInstance().PlayVideo(0, false, other_video, 0, 0, 1, 1, 0, false, 1, 0);
            _sendingVideo = false;

        }
    }

    private void seeYou() {
        Log.d("surfaceview", "surfaceview4 ");
        if (_userList.size() == 0)
            return;

        if (_watchingPeerID == 0) {
            int peerID = _userList.get(0);
            Session.getInstance().PlayVideo(peerID, true, other_video, 0, 0, 1, 1, 1, false, 0, 0);
            Log.d("surfaceview", "surfaceview5 ");
            _watchingPeerID = peerID;
        } else {
            Session.getInstance().PlayVideo(_watchingPeerID, false, other_video, 0, 0, 1, 1, 1, false, 1, 0);
            _watchingPeerID = 0;
        }
    }


    @Override
    public void onClick(View v) {

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
    public void onUserOut(MeetingUser arg0) {

        _userList.remove(new Integer(arg0.getPeerID()));

        if (!_serverMix && hasAudio)
            Session.getInstance().unplayAudio(arg0.getPeerID());
        if (_watchingPeerID == arg0.getPeerID()) {
            _watchingPeerID = 0;

        }
    }

    @Override
    public void onEnablePresence(int peerID) {
        _myPeerID = peerID;
//        seeMe();
        seeYou();
        Session.getInstance().requestSpeaking(_myPeerID);
        _freeSpeak = true;
        sendText(0, "i am " + android.os.Build.MODEL + android.os.Build.VERSION.RELEASE, android.os.Build.MODEL);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Session.getInstance().LeaveMeeting();
        _myPeerID = 0;
        uid = 0;
        _watchingPeerID = 0;
        _userList.clear();
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
    public void OnPenClick(boolean b) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Stop();
    }
}
