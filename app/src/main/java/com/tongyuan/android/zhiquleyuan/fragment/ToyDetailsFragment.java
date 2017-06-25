package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.MyPlayActivity;
import com.tongyuan.android.zhiquleyuan.activity.VideoActivity;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryListViewAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListRequsetBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoReq;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoRes;
import com.tongyuan.android.zhiquleyuan.bean.Items;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tongyuan.android.zhiquleyuan.R.id.iv_toy_details_call;

/**
 * Created by android on 2017/1/9.
 */
public class ToyDetailsFragment extends BaseFragment implements View.OnClickListener {

    private View mToyDetails;
    private ImageView mCall;
    private TextView mToyManager;
    private ImageView mIv_fragment_toy_details_babyImg;
    private ImageView mIv_fragment_toy_details_toyimg;
    private TextView mTv_fragment_toy_details_acttime;
    private TextView mTv_fragment_toy_details_babyName;
    private TextView mTv_fragment_toy_details_toytype;
    private Bundle mArguments;
    public static final String TAG = "555555";
    private ListView mListviewRecommand;
    private String mToycode;
    private String mBabyimg;
    private String mFormatTime;
    private SingleToyInfoRESBean.BODYBean mResponse;
    private ToyManagerFragment mToyManagerFragment;
    private String mImg;
    private String mCode;
    private String mId;
    private String mName;
    private String mOwnername;
    private String mOwnerid;
    private String mToyId;
    private String mToken;
    private String mPhoneNum;
    private String mTime;
    private String mRoomid;
    //    private VideoFragment mVideoFragment;
    private TextView mToyIsPlaying;
    private ImageView mCallSignal;
    private ImageView mCallButtery;
    private ImageView mCallMIc;
    private ImageView mCallCamera;
    private TextView mUnbindToy;
    private SingleToyInfoRESBean.BODYBean singleresponse;
    private DiscoveryListViewAdapter mLAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: went");
        mToyDetails = inflater.inflate(R.layout.fragment_toy_details, null);
//        FrameLayout framelayoutControl =  (FrameLayout) mToyDetails.findViewById(R.id.fl_fragment_toy_details_playcontrol);
        mListviewRecommand = (ListView) mToyDetails.findViewById(R.id.lv_fragment_toy_details_recommand);

        mCall = (ImageView) mToyDetails.findViewById(iv_toy_details_call);
        mToyManager = (TextView) mToyDetails.findViewById(R.id.tv_toy_details_manager);
        mIv_fragment_toy_details_babyImg = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_babyImg);
        mIv_fragment_toy_details_toyimg = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_toyimg);
        mTv_fragment_toy_details_acttime = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_acttime);
        mTv_fragment_toy_details_babyName = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_babyName);
        mTv_fragment_toy_details_toytype = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_toytype);
        mUnbindToy = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_unbind);

        //设置状态信息的图片
        mCallSignal = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_signal);
        mCallButtery = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_buttery);
        mCallMIc = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_mic);
        mCallCamera = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_camera);

        mToyIsPlaying = (TextView) mToyDetails.findViewById(R.id.tv_toy_details_playing);

        mToyManagerFragment = new ToyManagerFragment();

//        initView();
        getListRaw();
        return mToyDetails;
    }


    private void getListRaw() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);

        DiscoveryListRequsetBean.BODYBean bodyBean = new DiscoveryListRequsetBean.BODYBean("10", "1");
        DiscoveryListRequsetBean discoveryListRequsetBean = new DiscoveryListRequsetBean("REQ", "QRYREC", SPUtils.getString(getActivity(),
                "phoneNum", ""),
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(getActivity(), "TOKEN", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(discoveryListRequsetBean);
        Call<DiscoveryListResultBean> discoveryListResult = allInterface.getDiscoveryListResult(s);
        discoveryListResult.enqueue(new Callback<DiscoveryListResultBean>() {
            @Override
            public void onResponse(Call<DiscoveryListResultBean> call, final Response<DiscoveryListResultBean> response) {
                if (response != null && !response.body().getCODE().equals(0)) {
                    //返回的list是一个空list
                    Log.d(TAG, "onResponse: " + SPUtils.getString(getActivity(), "TOKEN", ""));
                    List<Items> list = new ArrayList<Items>();
                    list.add(new Items("第一种布局", null));
                    list.add(new Items(null, "第二种布局"));
                    mLAdapter = new DiscoveryListViewAdapter(getContext(), list, response);
                    mListviewRecommand.setAdapter(mLAdapter);
                    mListviewRecommand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            /*DiscoveryListResultBean.BODYBean.LSTBean lstBean = response.body().getBODY().getLST().get(position);
                            Log.i(TAG, "onItemClick: response:item" + response.body().getBODY().getLST().get(position).getID());
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), MyPlayActivity.class);
                            Bundle bundle = new Bundle();
                            //responselist为空，lstbean不能用
                            bundle.putParcelable("toydetailsrecommandlistbean", lstBean);
                            intent.putExtras(bundle);
                            startActivity(intent);*/

                            MyPlayActivity.launch(getActivity(), mLAdapter.getList(), position);


                            ToastUtil.showToast(getActivity(), "当前点击的是:" + position);
                        }
                    });


                } else {
                    ToastUtil.showToast(getActivity(), "shibai1");
                }
            }

            @Override
            public void onFailure(Call<DiscoveryListResultBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "shibai2");
            }
        });
    }


    public void setData(SingleToyInfoRESBean.BODYBean response, String image) {
        Log.i(TAG, "setData: went");
        this.mResponse = response;
        this.mBabyimg = image;
        mToyId = response.getID();
        mToken = SPUtils.getString(getContext(), "TOKEN", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        String acttime = response.getACTTIME();
        mFormatTime = acttime;
        try {
            mFormatTime = simpleDateFormat1.format(simpleDateFormat.parse(acttime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //保不齐以后要用这些参数,先取出来
        mImg = response.getIMG();
        mCode = response.getCODE();
        mId = response.getID();
        mName = response.getNAME();
        mOwnerid = response.getOWNERID();
        mOwnername = response.getOWNERNAME();
        Log.i(TAG, "mbabyimg: " + mBabyimg.toString());
        initView();
    }

    private void initView() {

        if (mListviewRecommand == null)
            return;
        mTv_fragment_toy_details_acttime.setText(mFormatTime);
        mTv_fragment_toy_details_toytype.setText(mName);
        Glide.with(getActivity()).load(mImg).asBitmap().into(mIv_fragment_toy_details_toyimg);

        if (mBabyimg.isEmpty()) {
            Glide.with(getActivity()).load(R.mipmap.default_babyimage).asBitmap().into(new BitmapImageViewTarget(mIv_fragment_toy_details_babyImg) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    mRoundedBitmapDrawable.setCircular(true);
                    mIv_fragment_toy_details_babyImg.setImageDrawable(mRoundedBitmapDrawable);
                }
            });

        } else {
            Glide.with(getActivity()).load(mBabyimg).asBitmap().into(new BitmapImageViewTarget(mIv_fragment_toy_details_babyImg) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    mRoundedBitmapDrawable.setCircular(true);
                    mIv_fragment_toy_details_babyImg.setImageDrawable(mRoundedBitmapDrawable);
                }
            });
        }
        mCall.setOnClickListener(this);
        mToyManager.setOnClickListener(this);
        mUnbindToy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case iv_toy_details_call:
                //通话控制
                CallToToy();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fl_fragmentcontainer, mVideoFragment).commit();

                ToastUtil.showToast(getActivity(), "跳转到通话界面");//是fragemnt界面

//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fl_fragmentcontainer, new VideoFragment()).commit();
                break;
            case R.id.tv_toy_details_manager:

                ToastUtil.showToast(getActivity(), "跳转到玩具管理界面");
                if (SPUtils.getString(getActivity(), "ID", "").equals(mOwnerid)) {
                    //TODO setdat
                    mToyManagerFragment.setData(mResponse, mFormatTime, mBabyimg);

//                    FragmentManager fragmentManager1 = getFragmentManager();
//                    fragmentManager1.beginTransaction().replace(R.id.fl_fragmentcontainer, mToyManagerFragment).commit();
                    showFragment(mToyManagerFragment);
                } else {
                    ToastUtil.showToast(getActivity(), "您当前不是管理员,不能管理玩具");
                }
                break;
            case R.id.tv_fragment_toy_details_unbind:
                //TODO
//                showDialog(); 需不需要给用户一个友好的提示,dialog?

                ToastUtil.showToast(getActivity(), "details  unbind");
        }

    }

    private void CallToToy() {
        final String token = SPUtils.getString(getActivity(), "TOKEN", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        CallToToyReq.ParamBean param = new CallToToyReq.ParamBean(mToyId, "1", "");
        CallToToyReq callToToyReq = new CallToToyReq("contact_toy", param, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(callToToyReq);
        Call<CallToToyRes> callToToyResCall = allInterface.CALL_TO_TOY_RES_CALL(s);
        callToToyResCall.enqueue(new Callback<CallToToyRes>() {
            @Override
            public void onResponse(Call<CallToToyRes> call, Response<CallToToyRes> response) {

                Log.i("555555", "onResponse:+response " + response.body().toString());
                mRoomid = response.body().getRoomid();
                if (mRoomid == null) {
                    ToastUtil.showToast(getActivity(), "房间号不存在");

                    if (response.body().getCode().equals("-10008")) {
                        ToastUtil.showToast(getActivity(), "推送失败");
                    } else if (response.body().getCode().equals("-10009")) {
                        ToastUtil.showToast(getActivity(), "玩具未登录");
                    } else if (response.body().getCode().equals("-10012")) {
                        ToastUtil.showToast(getActivity(), "玩具通话中");
                    }
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("roomid", mRoomid);
                bundle.putString("token", token);
                bundle.putString("toyid", mId);

                startActivity(new Intent(getActivity(), VideoActivity.class).putExtras(bundle));


//                FragmentManager fragmentManager = getFragmentManager();
//
//                //跳转到videofragment,暂时不用了,改成跳到一个新的页面,再从那个新页面跳转到videofragment
//                fragmentManager.beginTransaction().addToBackStack(ToyDetailsFragment.class.getSimpleName()).replace(R.id.fl_fragmentcontainer,
// mVideoFragment).commit();
                ToastUtil.showToast(getActivity(), "roomid" + mRoomid);
                Log.i("555555", "onResponse:+mRoomid " + mRoomid);

                //TODO 跳转到新的页面
//                CallWaitingConnectFragment callWaitingConnectFragment = new CallWaitingConnectFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fl_fragmentcontainer, callWaitingConnectFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();


            }

            @Override
            public void onFailure(Call<CallToToyRes> call, Throwable t) {
                Log.i("111111", t.toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //查询当前玩具是否正在播放音乐 3.4.43
        queryPlayingMusic();
    }

    private void queryPlayingMusic() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryPlayingMusicReqBean.BODYBean bodyBean = new QueryPlayingMusicReqBean.BODYBean(mToyId);
        QueryPlayingMusicReqBean queryPlayingMusicReqBean = new QueryPlayingMusicReqBean("REQ", "QPRES", mPhoneNum, mTime, bodyBean, "", "TOKEN",
                "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryPlayingMusicReqBean);
        Call<QueryPlayingMusicResBean> queryPlayingMusicResBeanCall = allInterface.QUERY_PLAYING_MUSIC_RES_BEAN_CALL(s);
        queryPlayingMusicResBeanCall.enqueue(new Callback<QueryPlayingMusicResBean>() {
            @Override
            public void onResponse(Call<QueryPlayingMusicResBean> call, Response<QueryPlayingMusicResBean> response) {
                Log.i("55555", response.body().toString());
                if (response.body().getBODY().getID().equals("")) {
                    ToastUtil.showToast(getContext(), "当前玩具没有正在播放的歌曲");
                    mToyIsPlaying.setVisibility(View.GONE);
                } else {
                    mToyIsPlaying.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<QueryPlayingMusicResBean> call, Throwable t) {

            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        SingleToyInfoRESBean.BODYBean response = mResponse;
    }

    @Override
    public void onResume() {
        super.onResume();
//        checkStateInfo();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);
    }

    private void checkStateInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/").addConverterFactory(GsonConverterFactory
                .create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        GetInstantStateInfoReq.BODYBean bodyBean = new GetInstantStateInfoReq.BODYBean(mToyId);
        GetInstantStateInfoReq getInstantStateInfoReq = new GetInstantStateInfoReq("REQ", "TOYSTATE", mPhoneNum, mTime, bodyBean, "", mToken, "1");

        Gson gson = new Gson();
        String s = gson.toJson(getInstantStateInfoReq);
        Call<GetInstantStateInfoRes> getInstantStateInfoResCall = allInterface.GET_INSTANT_STATE_INFO_RES_CALL(s);
        getInstantStateInfoResCall.enqueue(new Callback<GetInstantStateInfoRes>() {
            @Override
            public void onResponse(Call<GetInstantStateInfoRes> call, Response<GetInstantStateInfoRes> response) {
                if (!response.body().getCODE().equals("0")) {
                    ToastUtil.showToast(getActivity(), "拉取玩具即时状态返回的response为空");
                } else {
                    GetInstantStateInfoRes body = response.body();
                    String camera = response.body().getBODY().getCAMERA();
                    String elec = response.body().getBODY().getELEC();
                    String mic = response.body().getBODY().getMIC();
                    String online = response.body().getBODY().getONLINE();
                    String vol = response.body().getBODY().getVOL();
                    String wifi = response.body().getBODY().getWIFI();

                    //String值转化int
                    int wifiValue = Integer.parseInt(wifi);
//                    int wifiAbs = Math.abs(wifiValue);
                    int volValue = Integer.parseInt(vol);
                    int elecValue = Integer.parseInt(elec);

                    Log.i(TAG, "onResponse:camera" + camera);
                    Log.i(TAG, "onResponse:elec" + elec);
                    Log.i(TAG, "onResponse:mic" + mic);
                    Log.i(TAG, "onResponse:online" + online);
                    Log.i(TAG, "onResponse:vol" + vol);
                    Log.i(TAG, "onResponse:wifi" + wifi);

                    Log.i(TAG, "onResponse: wifiValue" + wifiValue);
                    Log.i(TAG, "onResponse: elecValue" + elecValue);
                    Log.i(TAG, "onResponse: volValue" + volValue);
                    //设置状态的图片

                    if (online.equals("0")) {
                        mCallMIc.setBackgroundResource(R.drawable.toy_recording_prohibit);
                        mCallCamera.setBackgroundResource(R.drawable.toy_webcam_prohibit);
                    } else if (online.equals("1")) {
                        mCallCamera.setBackgroundResource(R.drawable.toy_recording);
                        mCallCamera.setBackgroundResource(R.drawable.toy_webcam);
                    }

                    if (wifiValue == -200) {
                        ToastUtil.showToast(getActivity(), "当前玩具wifi异常");
                    }

                    if (wifiValue >= -100 && wifiValue <= -80) {//最弱信号
                        mCallSignal.setBackgroundResource(R.drawable.toy_signal_1);
                    } else if (wifiValue > -80 && wifiValue <= -60) {
                        mCallSignal.setBackgroundResource(R.drawable.toy_signal_2);
                    } else if (wifiValue > -60 && wifiValue <= -40) {
                        mCallSignal.setBackgroundResource(R.drawable.toy_signal_3);
                    } else if (wifiValue > -40 && wifiValue <= -20) {
                        mCallSignal.setBackgroundResource(R.drawable.toy_signal_4);
                    } else if (wifiValue > -20 && wifiValue <= 0) {
                        mCallSignal.setBackgroundResource(R.drawable.toy_signal_5);
                    }

                    if (elecValue >= 0 && elecValue <= 10) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_1);
                    } else if (elecValue > 10 && elecValue <= 20) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_2);
                    } else if (elecValue > 20 && elecValue <= 30) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_3);
                    } else if (elecValue > 30 && elecValue <= 40) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_4);
                    } else if (elecValue > 40 && elecValue <= 50) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_5);
                    } else if (elecValue > 50 && elecValue <= 70) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_6);
                    } else if (elecValue > 70 && elecValue <= 80) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_7);
                    } else if (elecValue > 80 && elecValue <= 90) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_8);
                    } else if (elecValue > 90 && elecValue <= 100) {
                        mCallButtery.setBackgroundResource(R.drawable.toy_battery_9);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetInstantStateInfoRes> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "拉取信息失败");
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
