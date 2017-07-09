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
import com.tongyuan.android.zhiquleyuan.activity.BindBabyActivity;
import com.tongyuan.android.zhiquleyuan.activity.MainActivity;
import com.tongyuan.android.zhiquleyuan.activity.MyPlayActivity;
import com.tongyuan.android.zhiquleyuan.activity.UnBindBabyActivity;
import com.tongyuan.android.zhiquleyuan.activity.VideoActivity;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryListViewAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListRequsetBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoReq;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdReq;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tongyuan.android.zhiquleyuan.R.id.iv_toy_details_call;
import static com.tongyuan.android.zhiquleyuan.R.id.tv_fragment_toy_details_babyName;

/**
 * Created by android on 2017/1/9.
 */
public class ToyDetailsFragment extends BaseFragment implements View.OnClickListener {

    private static final int UNBIND_BABY = 3002;
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
    private TextView mBindToy;
    private SingleToyInfoRESBean.BODYBean singleresponse;
    private DiscoveryListViewAdapter mLAdapter;
    private View mListviewtitle;
    private String mUserId;
    private static final int BIND_BABYTO_TOY = 3001;
    private String mBabyid;
    private List<QueryBabyListFromToyIdRes.BODYBean.LSTBean> mLst;
    private String mBabyName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: went");
        mToyDetails = inflater.inflate(R.layout.fragment_toy_details, null);
        mListviewtitle = inflater.inflate(R.layout.discovery_recyclerview_listview_title, null);


//        FrameLayout framelayoutControl =  (FrameLayout) mToyDetails.findViewById(R.id.fl_fragment_toy_details_playcontrol);
        mListviewRecommand = (ListView) mToyDetails.findViewById(R.id.lv_fragment_toy_details_recommand);

        mCall = (ImageView) mToyDetails.findViewById(iv_toy_details_call);
        mToyManager = (TextView) mToyDetails.findViewById(R.id.tv_toy_details_manager);
        mIv_fragment_toy_details_babyImg = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_babyImg);
        mIv_fragment_toy_details_toyimg = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_toyimg);
        mTv_fragment_toy_details_acttime = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_acttime);
        mTv_fragment_toy_details_babyName = (TextView) mToyDetails.findViewById(tv_fragment_toy_details_babyName);
        mTv_fragment_toy_details_toytype = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_toytype);
        mUnbindToy = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_unbind);//解绑玩具宝宝
        mBindToy = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_bind);//绑定玩具宝宝
        //设置状态信息的图片
        mCallSignal = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_signal);
        mCallButtery = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_buttery);
        mCallMIc = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_mic);
        mCallCamera = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_call_camera);

        mToyIsPlaying = (TextView) mToyDetails.findViewById(R.id.tv_toy_details_playing);
        mToyDetails.findViewById(R.id.back_btn).setOnClickListener(this);

        mToyManagerFragment = new ToyManagerFragment();

//        initView();
        getListRaw();
        return mToyDetails;
    }


    private void getListRaw() {
        DiscoveryListRequsetBean.BODYBean request = new DiscoveryListRequsetBean.BODYBean("10", "1");
        Call<SuperModel<DiscoveryListResultBean.BODYBean>> discoveryListResult = RequestManager.getInstance().getDiscoveryListResult(getContext(),
                request);
        discoveryListResult.enqueue(new Callback<SuperModel<DiscoveryListResultBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Response<SuperModel<DiscoveryListResultBean.BODYBean>>
                    response) {
                if ("0".equals(response.body().CODE)) {
                    //返回的list是一个空list
                    Log.d(TAG, "onResponse: " + SPUtils.getString(getActivity(), "TOKEN", ""));

                    mLAdapter = new DiscoveryListViewAdapter(getContext(), response.body().BODY.getLST());
                    mListviewRecommand.addHeaderView(mListviewtitle);
                    mListviewRecommand.setAdapter(mLAdapter);

                    mListviewRecommand.setHeaderDividersEnabled(false);
                    mListviewRecommand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            MyPlayActivity.launch(getActivity(), mLAdapter.getList(), position - 1);
                        }
                    });
                } else {
                    ToastUtil.showToast(getActivity(), response.body().MSG);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "shibai2");
            }
        });
    }


    public void setData(SingleToyInfoRESBean.BODYBean response, String image) {
        Log.i("timedate", "setData: went");
        mUserId = SPUtils.getString(getContext(), "ID", "");
        Log.i(TAG, "setData: went");
        this.mResponse = response;
        this.mBabyimg = image;
        mToyId = response.getID();
        mToken = SPUtils.getString(getContext(), "TOKEN", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");

        String acttime = response.getACTTIME();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSS");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parse = simpleDateFormat.parse(acttime);
            String formatTime = simpleDateFormat1.format(parse);
            mFormatTime = formatTime;
            Log.i(TAG, "toydetails toydetailsfragment1:" + formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "toydetails toydetailsfragment2:" + mFormatTime);
        Log.i(TAG, "toydetails toydetailsfragment3:" + acttime);
        //保不齐以后要用这些参数,先取出来
        mImg = response.getIMG();
        mCode = response.getCODE();
        mId = response.getID();
        mName = response.getNAME();
        mOwnerid = response.getOWNERID();
        mOwnername = response.getOWNERNAME();
        Log.i(TAG, "mbabyimg: " + mBabyimg.toString());

        //请求一次网络,查询当前玩具的宝宝信息: 3.4.24
        queryToyHasBindBaby();

    }

    private void queryToyHasBindBaby() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSS");
        String currentTime = simpleDateFormat.format(new Date());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryBabyListFromToyIdReq.BODYBean bodyBean = new QueryBabyListFromToyIdReq.BODYBean(mToyId, "10", "1");
        QueryBabyListFromToyIdReq queryBabyListFromToyIdReq = new QueryBabyListFromToyIdReq("REQ", "QRYTOYB", mPhoneNum, currentTime, bodyBean, "",
                mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryBabyListFromToyIdReq);

        Call<QueryBabyListFromToyIdRes> queryBabyListFromToyIdResCall = allInterface.QUERY_BABY_LIST_FROM_TOY_ID_CALL(s);
        queryBabyListFromToyIdResCall.enqueue(new Callback<QueryBabyListFromToyIdRes>() {
            @Override
            public void onResponse(Call<QueryBabyListFromToyIdRes> call, Response<QueryBabyListFromToyIdRes> response) {
                //如果当前的玩具绑定了宝宝,则显示"解绑宝宝",如果没有绑定宝宝,则显示"绑定宝宝"
                if (response == null) {
                    return;
                }
                mLst = response.body().getBODY().getLST();
                if (mLst.size() == 0) {
                    mBindToy.setVisibility(View.VISIBLE);
                    mUnbindToy.setVisibility(View.GONE);
                    initView();
                } else {
                    for (int i = 0; i < mLst.size(); i++) {
                        mBabyName = mLst.get(i).getNAME();
                    }
                    mBindToy.setVisibility(View.GONE);
                    mUnbindToy.setVisibility(View.VISIBLE);
                    initView();

                }
            }

            @Override
            public void onFailure(Call<QueryBabyListFromToyIdRes> call, Throwable t) {

            }
        });
    }

    private void initView() {
        checkStateInfo();
        Log.i("timedate", "initView: went");
        if (mListviewRecommand == null)
            return;
        mTv_fragment_toy_details_acttime.setText(mFormatTime);
        mTv_fragment_toy_details_toytype.setText(mName);
        Glide.with(mContext).load(mImg).asBitmap().into(mIv_fragment_toy_details_toyimg);

        if (mBabyimg == null||mLst.size()==0) {

            Glide.with(mContext).load(R.mipmap.default_babyimage).asBitmap().into(new BitmapImageViewTarget(mIv_fragment_toy_details_babyImg) {
                @Override
                protected void setResource(Bitmap resource) {
                    if(isDestory)
                        return;
                    RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    mRoundedBitmapDrawable.setCircular(true);
                    mIv_fragment_toy_details_babyImg.setImageDrawable(mRoundedBitmapDrawable);
                }
            });

        } else {

            Glide.with(mContext).load(mBabyimg).asBitmap().into(new BitmapImageViewTarget(mIv_fragment_toy_details_babyImg) {
                @Override
                protected void setResource(Bitmap resource) {
                    if(isDestory)
                        return;
                    RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    mRoundedBitmapDrawable.setCircular(true);
                    mIv_fragment_toy_details_babyImg.setImageDrawable(mRoundedBitmapDrawable);
                }
            });

        }
        if (mLst.size()==0){
            mTv_fragment_toy_details_babyName.setText(mOwnername);
        }else{
            mTv_fragment_toy_details_babyName.setText(mBabyName);
        }


        mCall.setOnClickListener(this);
        mToyManager.setOnClickListener(this);
        mUnbindToy.setOnClickListener(this);
        mBindToy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case iv_toy_details_call:
                //通话控制
                CallToToy();
                ToastUtil.showToast(getActivity(), "跳转到通话界面");//是fragemnt界面
                break;
            case R.id.tv_toy_details_manager:
                ToastUtil.showToast(getActivity(), "跳转到玩具管理界面");
//                if (SPUtils.getString(getActivity(), "ID", "").equals(mOwnerid)) {
                //TODO setdat
                mToyManagerFragment.setData(mResponse, mFormatTime, mBabyimg,mBabyName,mOwnername,mLst,mOwnerid);
                showFragment(mToyManagerFragment);
//                } else {
//                    ToastUtil.showToast(getActivity(), "您当前不是管理员,不能管理玩具");
//                }
                break;

            case R.id.tv_fragment_toy_details_unbind:
                //判断当前的userid是不是ownerid
                if (!mUserId.equals(mOwnerid)) {
                    ToastUtil.showToast(getContext(), "您不是该玩具的持有人,不能解绑玩具");
                    return;
                } else {

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("toycode", mCode);
                    bundle.putString("toyid", mToyId);
                    intent.putExtras(bundle);
                    intent.setClass(getContext(), UnBindBabyActivity.class);
                    startActivityForResult(intent, UNBIND_BABY);
//                    startActivity(intent);
                }
                //可以先不跳转,直接解除绑定
//                    unBindBabyToToy();


                //TODO showDialog(); 需不需要给用户一个友好的提示,dialog?
                ToastUtil.showToast(getActivity(), "details  unbind");
                break;

            case R.id.tv_fragment_toy_details_bind:
                if (!mUserId.equals(mOwnerid)) {
                    ToastUtil.showToast(getContext(), "您不是该玩具的持有人,不能解绑玩具");
                    return;
                }
                //TODO showDialog(); 需不需要给用户一个友好的提示,dialog?
                Intent intent = new Intent();
                intent.setClass(getActivity(), BindBabyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("toycode", mCode);
                bundle.putString("toyid", mToyId);
                intent.putExtras(bundle);
                startActivityForResult(intent, BIND_BABYTO_TOY);
//                startActivity(intent);
                ToastUtil.showToast(getActivity(), "details  bind");
                break;
            case R.id.back_btn:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.backToTop();
                ToastUtil.showToast(getActivity(), "back");
                break;
        }

    }

    //3.4.19 删除玩具和宝宝关心
//    private void unBindBabyToToy() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constant.baseurl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AllInterface allInterface = retrofit.create(AllInterface.class);
//        UnbindBabyToToyReqBean.BODYBean.LSTBean lstBean = new UnbindBabyToToyReqBean.BODYBean.LSTBean(mToyId, mToycode, );
//        List<UnbindBabyToToyReqBean.BODYBean.LSTBean> lstBeanList = new ArrayList<>();
//        lstBeanList.add(lstBean);
//
//        UnbindBabyToToyReqBean.BODYBean bodyBean = new UnbindBabyToToyReqBean.BODYBean(lstBeanList);
//        UnbindBabyToToyReqBean unbindBabyToToyReqBean = new UnbindBabyToToyReqBean("REQ", "DTOYB", mPhoneNum, new SimpleDateFormat
// ("yyyyMMddmmssSSS")
//                .format(new Date()), bodyBean, "", mToken, "1");
//
//
//    }

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
                bundle.putString("babyimg",mBabyimg);
                bundle.putString("babyname",mBabyName);

                startActivity(new Intent(getActivity(), VideoActivity.class).putExtras(bundle));
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
        //TODO 查询当前玩具是否正在播放音乐 3.4.43
//        queryPlayingMusic();
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

    }

    @Override
    public void onResume() {
        super.onResume();
//        checkStateInfo();
//        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        queryToyHasBindBaby();
//        checkStateInfo();
        initView();
    }

    //拉去玩具的状态信息
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
                    int wifiValue = -100;
                    int volValue = 0;
                    int elecValue = 0;
                    if (!wifi.equals("")) {
                        wifiValue = Integer.parseInt(wifi);

                    }
//                    if (!vol.equals("")) {
//                        volValue = Integer.parseInt(vol);
//
//                    }
                    if (!elec.equals("")) {
                        elecValue = Integer.parseInt(elec);

                    }

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
                        mCallMIc.setImageResource(R.drawable.toy_recording_prohibit);
                        mCallCamera.setImageResource(R.drawable.toy_webcam_prohibit);
                    } else if (online.equals("1")) {
                        mCallMIc.setImageResource(R.drawable.toy_recording);
                        mCallCamera.setImageResource(R.drawable.toy_webcam);
                    }

                    if (wifiValue == -200) {
                        ToastUtil.showToast(getActivity(), "当前玩具wifi异常");
                    }
                    if (wifiValue >= -100 && wifiValue <= -80) {//最弱信号
                        mCallSignal.setImageResource(R.drawable.toy_signal_1);
                    } else if (wifiValue > -80 && wifiValue <= -60) {
                        mCallSignal.setImageResource(R.drawable.toy_signal_2);
                    } else if (wifiValue > -60 && wifiValue <= -40) {
                        mCallSignal.setImageResource(R.drawable.toy_signal_3);
                    } else if (wifiValue > -40 && wifiValue <= -20) {
                        mCallSignal.setImageResource(R.drawable.toy_signal_4);
                    } else if (wifiValue > -20 && wifiValue <= 0) {
                        mCallSignal.setImageResource(R.drawable.toy_signal_5);
                    }

                    if (elecValue >= 0 && elecValue <= 10) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_1);
                    } else if (elecValue > 10 && elecValue <= 20) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_2);
                    } else if (elecValue > 20 && elecValue <= 30) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_3);
                    } else if (elecValue > 30 && elecValue <= 40) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_4);
                    } else if (elecValue > 40 && elecValue <= 50) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_5);
                    } else if (elecValue > 50 && elecValue <= 70) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_6);
                    } else if (elecValue > 70 && elecValue <= 80) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_7);
                    } else if (elecValue > 80 && elecValue <= 90) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_8);
                    } else if (elecValue > 90 && elecValue <= 100) {
                        mCallButtery.setImageResource(R.drawable.toy_battery_9);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BIND_BABYTO_TOY && resultCode == 00) {
            queryToyHasBindBaby();
            initView();
        } else if (requestCode == UNBIND_BABY && resultCode == 00) {
            queryToyHasBindBaby();
            initView();
        }
    }
}
