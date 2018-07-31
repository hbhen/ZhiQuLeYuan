package com.tongyuan.android.zhiquleyuan.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyVolumeReq;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyVolumeRes;
import com.tongyuan.android.zhiquleyuan.bean.JonToyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.JonToyResBean;
import com.tongyuan.android.zhiquleyuan.bean.QuitToyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QuitToyResBean;
import com.tongyuan.android.zhiquleyuan.bean.ShutDownRoomReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ShutDownRoomResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtil;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.weiyicloud.whitepad.ControlMode;
import com.weiyicloud.whitepad.SharePadMgr;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import info.emm.meeting.MeetingUser;
import info.emm.meeting.MyWatch;
import info.emm.meeting.Session;
import info.emm.meeting.SessionInterface;
import info.emm.sdk.VideoView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.name;

/**
 * Created by DTC on 2018/7/24.
 */

public class MultiVideoActivity extends AppCompatActivity implements SessionInterface, View.OnClickListener {
    private final String TAG = MultiVideoActivity.class.getSimpleName();
    static public int WEIYI_VIDEO_OUT_SLOW = 1;       //视频发送速度慢
    static public int WEIYI_VIDEO_OUT_DISCONNECT = 2; //视频发送连接断开重连
    static public int WEIYI_VIDEO_IN_SLOW = 3;        //视频接收速度慢
    static public int WEIYI_VIDEO_IN_DISCONNECT = 4;  //视频接收连接断开重连
    static public int WEIYI_AUDIO_DISCONNECT = 10;    //音频连接断开重连
    static public int WEIYI_AUDIO_PERMISSION = 11;    //无法开启麦克风
    public VideoView mMy_video, mOther_video;
    public VideoView firstVideo, secondVideo, thirdVideo, forthVideo, fifthVideo;

    public boolean hasfront = false;
    public boolean usefront = false;

    final private boolean hasAudio = true;

    public int _watchingPeerID = 0;
    public int _firstPeerID = -1;
    public int _secondPeerID = -1;
    public int _myPeerID = 0;
    public boolean _sendingVideo = false;
    public boolean _serverMix = false;
    public int _codec = 1;
    public ArrayList<Integer> _userList = new ArrayList<Integer>();
    TreeMap<Integer, String> treeMap = new TreeMap();
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
    //    private String mToyid;
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
    private Button mAddToy;
    ArrayList<String> toyList;
    private String[] mToyids;
    private ArrayList<String> mToyidss;
    private String[] mToyidArr;
    private int whichVideo = 1;

    public static void launch(Context context, String roomid, String token, String[] toyId) {
        Intent it = new Intent(context, MultiVideoActivity.class);
        it.putExtra("roomid", roomid);
        it.putExtra("token", token);
        it.putExtra("toyId", toyId);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }

    public static void launch(Context context, String roomid, String token, String[] item, ArrayList<String> idList) {

        Intent it = new Intent(context, MultiVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("toyidss", idList);
        it.putExtra("roomid", roomid);
        it.putExtra("token", token);
        it.putExtra("toyId", item);
        it.putExtra("toyids", idList);
        it.putExtra("toyidsss", bundle);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }

    public void setBabyImage(String babyImage) {
        mBabyimgString = babyImage;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.surfaceView1:
//                for (int i = 0; i < Session.getInstance().getM_nWatchVideoIDs().size(); i++) {
//                    MyWatch myWatch = Session.getInstance().getM_nWatchVideoIDs().get(i);
//                    int peerid = myWatch.getPeerid();
//                    LogUtil.i(TAG, peerid + ";");
//                }
//                for (int i = 0; i < _userList.size(); i++) {
//                    MeetingUser meetingUser = Session.getInstance().getM_thisUserMgr().getMeetingUser(_userList.get(i));
//                    String name = meetingUser.getName();
//                    LogUtil.i(TAG, "name : " + name + ";");
//                }
                final AlertDialog.Builder firstVideoBuilder = new AlertDialog.Builder(this);
                firstVideoBuilder.setTitle("踢出玩具")
                        .setMessage("是否要将当前玩具踢出会议室")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showToast(MultiVideoActivity.this, "name : " + name);
//                                quitToy(mRoomid, mToyidArr[1]);
                                if (_firstPeerID != -1 && _userList.size() != 0) {

                                    String name = Session.getInstance().getM_thisUserMgr().getUser(_firstPeerID).getName();
                                    StopCallServer(name);
                                }else{
                                    ToastUtil.showToast(MultiVideoActivity.this,"无玩具可以踢出");
                                }
                            }
                        });
                firstVideoBuilder.show();


                break;
            case R.id.surfaceView2:
//                for (int i = 0; i < Session.getInstance().getM_nWatchVideoIDs().size(); i++) {
//                    MyWatch myWatch = Session.getInstance().getM_nWatchVideoIDs().get(i);
//                    int peerid = myWatch.getPeerid();
//                    LogUtil.i(TAG, peerid + ";");
//                }
//                for (int i = 0; i < _userList.size(); i++) {
//                    MeetingUser meetingUser = Session.getInstance().getM_thisUserMgr().getMeetingUser(_userList.get(i));
//                    String name = meetingUser.getName();
//                    LogUtil.i(TAG, "name : " + name + ";");
//                }
                final AlertDialog.Builder secondVideoBuilder = new AlertDialog.Builder(this);
                secondVideoBuilder.setTitle("踢出玩具")
                        .setMessage("是否要将当前玩具踢出会议室")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showToast(MultiVideoActivity.this, "name : " + name);
//                                quitToy(mRoomid, mToyidArr[1]);
                                if (_secondPeerID != -1 && _userList.size() != 0) {
                                    String name = Session.getInstance().getM_thisUserMgr().getUser(_secondPeerID).getName();
                                    StopCallServer(name);
                                }else{
                                    ToastUtil.showToast(MultiVideoActivity.this,"无玩具可以踢出");
                                }
                            }


                        });
                secondVideoBuilder.show();

                break;
            case R.id.addToy:
                //当前的界面是真
                ToastUtil.showToast(this, "添加");
                dialogMoreChoice(mRoomid);
                break;
            case R.id.iv_fragment_videocall_stopcall:

                dada();
//                Stop();
                //一对一视频通话关闭
//                StopCallServer();
//                quitToyDialogChoice()
                if (_userList.size() > 0) {
                    ToastUtil.showToast(MultiVideoActivity.this, "请先将玩具踢出,然后关闭会议室");
                    return;
                }
                shutDownRoom(mRoomid);
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
                    Session.getInstance().PlayVideo(_myPeerID, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
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
//                    pushValueToToy(volumeAdd);
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
//                    pushValueToToy(volumeReduce);

                }

                break;
            default:

                break;

        }


    }

    @Override
    protected void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_multivideocall);

        int toyvolume = SPUtil.getInstance().getInt("toyvolume", 0);
//        LogUtil.i("newbi  :" + String.valueOf(toyvolume));
        mAddToy = (Button) findViewById(R.id.addToy);
        //主屏幕
        mMy_video = (VideoView) findViewById(R.id.surfaceView5);
// 其他的视频不显示了
        firstVideo = (VideoView) findViewById(R.id.surfaceView1);
        secondVideo = (VideoView) findViewById(R.id.surfaceView2);
//        thirdVideo = (VideoView) findViewById(R.id.surfaceView3);
//        forthVideo = (VideoView) findViewById(R.id.surfaceView4);
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
//        //宝宝的头像
//        mBabyImg = (ImageView) findViewById(R.id.iv_activity_videocall_babyimg);
//        //宝宝的名字
//        mBabyName = (TextView) findViewById(R.id.tv_activity_videocall_babyname);

        Intent intent = getIntent();
//        mBabyimgString = intent.getStringExtra("babyimgString");
//        mBabynameString = intent.getStringExtra("babynameString");
        mRoomid = intent.getStringExtra("roomid");
        mToken = intent.getStringExtra("token");
        Bundle toyidsss = intent.getBundleExtra("toyidsss");
        mToyidss = toyidsss.getStringArrayList("toyidss");
//        mToyids = intent.getStringArrayExtra("toyid");
        LogUtil.i(TAG, "toyids:" + mToyidss.size() + "");
        String item[] = new String[mToyidss.size()];
        mToyidArr = mToyidss.toArray(item);
//        mToyid = intent.getStringExtra("toyId");
//        if (mBabyimgString == null) {
//            ToastUtil.showToast(this, "二维码错误");
//            return;
//        }
//        if (mBabyimgString.equals("")) {
//            Glide.with(this).load(R.mipmap.default_babyimage).asBitmap().centerCrop().transform(new GlideCircleTransform(this)).into(mBabyImg);
//        } else {
//            Glide.with(this).load(mBabyimgString).asBitmap().centerCrop().transform(new GlideCircleTransform(this)).into(mBabyImg);
//        }
//        mBabyName.setText(mBabynameString);


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
//                pushValueToToy(volume);
                LogUtil.i("video", "onProgressChanged:(stoptrackingtouch " + progress);
            }
        });
//        volume_reduce.setOnClickListener(this);
//        volume_add.setOnClickListener(this);
        mAddToy.setOnClickListener(this);
        mStopCall.setOnClickListener(this);
//        mNoVideo.setOnClickListener(this);
        firstVideo.setOnClickListener(this);
        secondVideo.setOnClickListener(this);

//        mMy_video.setOnClickListener(this);
//        mOther_video.setOnClickListener(this);
        Session.getInstance().registerListener(this);
        Session.getInstance().registerWhiteBroad(SharePadMgr.getInstance());

    }

    private void EnterMeeting() {
        Start(false, 0);
    }

    public void Start(boolean serverMix, int codec) {
        LogUtil.i(TAG, "Start go !!");
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

    private void StopCallServer(String toyid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
//        CallToToyReq.ParamBean paramBean = new CallToToyReq.ParamBean(mToyid, "2", mRoomid, "");
        CallToToyReq.ParamBean paramBean = new CallToToyReq.ParamBean(toyid, "2", mRoomid, "");
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
//    private void quitToyDialogChoice() {
//        LogUtil.i(TAG, "toyidsss[]" + mToyidArr.length);
//        final boolean selected[] = {false, false, false, false, false};
//        AlertDialog.Builder builder = new AlertDialog.Builder(MultiVideoActivity.this, 3);
//        builder.setTitle("下课");
//        builder.setMultiChoiceItems(mToyidArr, selected, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                if (isChecked) {
////                    CallToToy(mToyids[which]);
//                    joinToy(mRoomid, mToyidArr[which], which);
//
//                    ToastUtil.showToast(MultiVideoActivity.this, mToyidArr[which] + "  :true");
//                } else {
//                    quitToy(mRoomid, mToyidArr[which]);
//                    ToastUtil.showToast(MultiVideoActivity.this, mToyidArr[which] + "  :false");
//
//                }
//            }
//
//            private void joinToy(String roomid, String toyItem, int which) {
//                Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                AllInterface allInterface = retrofit.create(AllInterface.class);
//                JonToyReqBean.BODYBean bodyBean = new JonToyReqBean.BODYBean(roomid, toyItem);
//                JonToyReqBean jonToyReqBean = new JonToyReqBean("REQ", "JONTOY", "", "", bodyBean, "", mToken, "1");
//                Gson gson = new Gson();
//                String s = gson.toJson(jonToyReqBean);
//                Call<JonToyResBean> jonToyResBeanCall = allInterface.JON_TOY_RES_BEAN_CALL(s);
//                jonToyResBeanCall.enqueue(new Callback<JonToyResBean>() {
//                    @Override
//                    public void onResponse(Call<JonToyResBean> call, Response<JonToyResBean> response) {
//                        if (response.body().getCODE().equals("0")) {
//                            LogUtil.i(TAG, response.body().getBODY().toString());
//                            ToastUtil.showToast(MultiVideoActivity.this, "成功连接玩具");
//
//                        } else {
//                            ToastUtil.showToast(MultiVideoActivity.this, response.body().getMSG());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<JonToyResBean> call, Throwable t) {
//                        LogUtil.i(TAG, t.toString());
//                    }
//                });
//
//            }
//
//
//        });
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                MultiVideoActivity.launch(MultiVideoActivity.this, roomid, mToken, toyItems);
//                dialog.dismiss();
//                ToastUtil.showToast(MultiVideoActivity.this, "确定");
//                //TODO 写入多方通话的方法
////                CallToToy();
//                // android会自动根据你选择的改变selected数组的值。
////                for (int i = 0; i < selected.length; i++) {
////                    Log.e("hongliang", "" + selected[i]);
////                }
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                MultiVideoActivity.launch(mContext,roomid,mToken,toyItems);
//                dialog.dismiss();
//                ToastUtil.showToast(MultiVideoActivity.this, "取消");
//                //TODO 写入多方通话的方法
////                CallToToy();
//                // android会自动根据你选择的改变selected数组的值。
////                for (int i = 0; i < selected.length; i++) {
////                    Log.e("hongliang", "" + selected[i]);
////                }
//            }
//        });
//        builder.show();
//    }

    private void shutDownRoom(String roomid) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        ShutDownRoomReqBean.BODYBean bodyBean = new ShutDownRoomReqBean.BODYBean(roomid);
        ShutDownRoomReqBean quitToyReqBean = new ShutDownRoomReqBean("REQ", "CLOSEROOM", "", "", bodyBean, "", mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(quitToyReqBean);
        Call<ShutDownRoomResBean> shutDownRoomResBeanCall = allInterface.SHUT_DOWN_ROOM_RES_BEAN_CALL(s);
        shutDownRoomResBeanCall.enqueue(new Callback<ShutDownRoomResBean>() {
            @Override
            public void onResponse(Call<ShutDownRoomResBean> call, Response<ShutDownRoomResBean> response) {
                if (response.body().getCODE().equals("0")) {
                    LogUtil.i(TAG, response.body().getBODY().toString());
                    ToastUtil.showToast(MultiVideoActivity.this, "成功关闭会议室");
                } else {
                    ToastUtil.showToast(MultiVideoActivity.this, response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<ShutDownRoomResBean> call, Throwable t) {
                LogUtil.i(TAG, t.toString());
            }
        });

    }

    private void dialogMoreChoice(final String roomid) {

//        final String items[] = {"student1", "student2", "student3", "student4", "student5"};
        LogUtil.i(TAG, "toyidsss[]" + mToyidArr.length);
        final boolean selected[] = {false, false, false, false, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(MultiVideoActivity.this, 3);
        builder.setTitle("开始上课");
        builder.setMultiChoiceItems(mToyidArr, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
//                    CallToToy(mToyids[which]);
                    joinToy(roomid, mToyidArr[which], which);

                    ToastUtil.showToast(MultiVideoActivity.this, mToyidArr[which] + "  :true");
                } else {
                    quitToy(roomid, mToyidArr[which]);
                    ToastUtil.showToast(MultiVideoActivity.this, mToyidArr[which] + "  :false");

                }
            }

            private void joinToy(String roomid, String toyItem, int which) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                AllInterface allInterface = retrofit.create(AllInterface.class);
                JonToyReqBean.BODYBean bodyBean = new JonToyReqBean.BODYBean(roomid, toyItem);
                JonToyReqBean jonToyReqBean = new JonToyReqBean("REQ", "JONTOY", "", "", bodyBean, "", mToken, "1");
                Gson gson = new Gson();
                String s = gson.toJson(jonToyReqBean);
                Call<JonToyResBean> jonToyResBeanCall = allInterface.JON_TOY_RES_BEAN_CALL(s);
                jonToyResBeanCall.enqueue(new Callback<JonToyResBean>() {
                    @Override
                    public void onResponse(Call<JonToyResBean> call, Response<JonToyResBean> response) {
                        if (response.body().getCODE().equals("0")) {
                            LogUtil.i(TAG, response.body().getBODY().toString());
                            ToastUtil.showToast(MultiVideoActivity.this, "成功连接玩具");

                        } else {
                            ToastUtil.showToast(MultiVideoActivity.this, response.body().getMSG());
                        }
                    }

                    @Override
                    public void onFailure(Call<JonToyResBean> call, Throwable t) {
                        LogUtil.i(TAG, t.toString());
                    }
                });

            }


        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                MultiVideoActivity.launch(MultiVideoActivity.this, roomid, mToken, toyItems);
                dialog.dismiss();
                ToastUtil.showToast(MultiVideoActivity.this, "确定");
                //TODO 写入多方通话的方法
//                CallToToy();
                // android会自动根据你选择的改变selected数组的值。
//                for (int i = 0; i < selected.length; i++) {
//                    Log.e("hongliang", "" + selected[i]);
//                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                MultiVideoActivity.launch(mContext,roomid,mToken,toyItems);
                dialog.dismiss();
                ToastUtil.showToast(MultiVideoActivity.this, "取消");
                //TODO 写入多方通话的方法
//                CallToToy();
                // android会自动根据你选择的改变selected数组的值。
//                for (int i = 0; i < selected.length; i++) {
//                    Log.e("hongliang", "" + selected[i]);
//                }
            }
        });
        builder.show();
    }

    private void quitToy(String roomid, String toyItem) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QuitToyReqBean.BODYBean bodyBean = new QuitToyReqBean.BODYBean(roomid, toyItem);
        QuitToyReqBean quitToyReqBean = new QuitToyReqBean("REQ", "QUITTOY", "", "", bodyBean, "", mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(quitToyReqBean);
        Call<QuitToyResBean> quitToyResBeanCall = allInterface.QUIT_TOY_RES_BEAN_CALL(s);
        quitToyResBeanCall.enqueue(new Callback<QuitToyResBean>() {
            @Override
            public void onResponse(Call<QuitToyResBean> call, Response<QuitToyResBean> response) {
                if (response.body().getCODE().equals("0")) {
                    LogUtil.i(TAG, response.body().getBODY().toString());
                    ToastUtil.showToast(MultiVideoActivity.this, "成功踢出玩具");
                } else {
                    ToastUtil.showToast(MultiVideoActivity.this, response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<QuitToyResBean> call, Throwable t) {
                LogUtil.i(TAG, t.toString());
            }
        });
    }

    private void pushValueToToy(String volume) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
//        ControlToyVolumeReq.ParamBean paramBean = new ControlToyVolumeReq.ParamBean(mToyid, volume);
        ControlToyVolumeReq.ParamBean paramBean = new ControlToyVolumeReq.ParamBean("", volume);
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
        LogUtil.i(TAG, "seeMe go !!");
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

    private void seeYou(int peerID) {
        LogUtil.i(TAG, "see you go !!");
        LogUtil.d("surfaceview", "surfaceview4 ");

        if (_userList.size() == 0)
            return;
//        if (_watchingPeerID == 0) {
//            peerID = _userList.get(0);//这里就是获取id的,userlist是装在房间里的用户的人数的容器.
//            Session.getInstance().PlayVideo(peerID, true, firstVideo, 0, 0, 1, 1, 0, false, 1, 0);
//            Session.getInstance().requestSpeaking(peerID);
//            LogUtil.d("surfaceview", "surfaceview5 ");
//            _watchingPeerID = peerID;
//        } else {
//            Session.getInstance().PlayVideo(_watchingPeerID, true, secondVideo, 0, 0, 1, 1, 0, false, 1, 0);
//            Session.getInstance().requestSpeaking(_watchingPeerID);
//            _watchingPeerID = 0;
//        }
        if (_firstPeerID == -1) {
            peerID = _userList.get(0);
            _firstPeerID = peerID;
            String name = Session.getInstance().getM_thisUserMgr().getUser(peerID).getName();
            LogUtil.i(TAG, "peerID (userin first) : " + peerID + "; " + "name : " + name);
            Session.getInstance().PlayVideo(_firstPeerID, true, firstVideo, 0, 0, 1, 1, 0, false, 1, 0);
//            Session.getInstance().unplayAudio(_firstPeerID);
            Session.getInstance().requestSpeaking(peerID);
        }
        if (_secondPeerID == -1 && _userList.size() > 1) {
            peerID = _userList.get(1);
            _secondPeerID = peerID;
            String name = Session.getInstance().getM_thisUserMgr().getUser(peerID).getName();
            LogUtil.i(TAG, "peerID (userin second) : " + peerID + "; " + "name : " + name);
            Session.getInstance().PlayVideo(_secondPeerID, true, secondVideo, 0, 0, 1, 1, 0, false, 1, 0);
//            Session.getInstance().unplayAudio(_secondPeerID);
            Session.getInstance().requestSpeaking(peerID);
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
        LogUtil.i(TAG, "onConnect go !! ");
        LogUtil.i(TAG, "i1: " + i1);
        LogUtil.i(TAG, "status: " + status);

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
    public void onUserIn(int peerId, boolean b) {
        int currentPosition = 0;
        LogUtil.i(TAG, "on onUserIn go !!");
        LogUtil.i(TAG, "peerid: " + peerId + "; boolean : " + b);
        if (_userList.size() != 0) {
            LogUtil.i(TAG, "name : " + _userList.get(0));
        }
//        LogUtil.i(TAG,"name : "+_userList.get(1));
        if (peerId != -1) {
            LogUtil.i(TAG, "peerid(onUserIn) : " + peerId);
        }
        treeMap.put(peerId, Session.getInstance().getM_thisUserMgr().getUser(peerId).getName());
        LogUtil.i(TAG, "treemap :" + treeMap.get(peerId));

        _userList.add(peerId);
        LogUtil.i(TAG, "userlist`s size : " + _userList.size());
        MeetingUser user = Session.getInstance().getM_thisUserMgr().getUser(peerId);
        String name = user.getName();
        LogUtil.i(TAG, "peerid: " + peerId + "; name : " + name);
        seeYou(peerId);
        dada();
//        if (name.contains("toy")) {
////            toyId = peerId;
//            seeYou(peerId);
//        }
//
//        if (name.contains("tv")) {
////            tvId = peerId;
//            seeYou(peerId);
////            Session.getInstance().PlayVideo(toyId, true, mMy_video, 0, 0, 1, 1, 0, false, 1, 0);
//        }
    }

    private void dada() {
        if (_userList.size() != 0) {
            switch (_userList.size()) {
                case 1:
                    Integer integer = _userList.get(0);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;

            }
            ArrayList<MyWatch> m_nWatchVideoIDs = Session.getInstance().getM_nWatchVideoIDs();
            ArrayList<MyWatch> watchVideoIDs = Session.getInstance().getWatchVideoIDs();
            if (m_nWatchVideoIDs != null && m_nWatchVideoIDs.size() != 0) {
                for (int i = 0; i < m_nWatchVideoIDs.size(); i++) {
                    MyWatch myWatch = m_nWatchVideoIDs.get(i);
                    int peerid = myWatch.getPeerid();

                    LogUtil.i(TAG, peerid + "上");
                }
            }
        }
    }


    @Override
    public void onUserOut(MeetingUser meetingUser) {
        LogUtil.i(TAG, "on onUserOut go !!");
        _userList.remove(new Integer(meetingUser.getPeerID()));
        treeMap.remove(meetingUser.getPeerID());
        if (!_serverMix && hasAudio)
            Session.getInstance().unplayAudio(meetingUser.getPeerID());
        if (_watchingPeerID == meetingUser.getPeerID()) {
            _watchingPeerID = 0;
        }
    }

    @Override
    public void onEnablePresence(int peerID) {
//        _myPeerID = peerID;
        //画中画的时候可以开启
//        seeMe();
        LogUtil.i(TAG, "peerID(onEnablePresence) : " + peerID);
        LogUtil.i(TAG, "userlist(onEnablePresence) : " + _userList.size());

        seeYou(peerID);
        _freeSpeak = true;
        sendText(0, "i am " + android.os.Build.MODEL + android.os.Build.VERSION.RELEASE, android.os.Build.MODEL);
    }

    @Override
    public void onDisConnect(int i) {
        LogUtil.i(TAG, "onDisconnect go !!");
        _myPeerID = 0;
        _watchingPeerID = 0;
        uid = 0;
        _userList.clear();
        Session.getInstance().LeaveMeeting();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Stop();
        //通知服务器,退出
//        StopCallServer();
        shutDownRoom(mRoomid);
    }

    private void StopCallServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
//        CallToToyReq.ParamBean paramBean = new CallToToyReq.ParamBean(mToyid, "2", mRoomid, "");
        CallToToyReq.ParamBean paramBean = new CallToToyReq.ParamBean("", "2", mRoomid, "");
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


}
