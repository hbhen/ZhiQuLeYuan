package com.tongyuan.android.zhiquleyuan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListRequsetBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoReq;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdReq;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.bean.UpdateToyVersionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.UpdateToyVersionResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.view.GlideCircleTransform;
import com.tongyuan.android.zhiquleyuan.zxing.app.CaptureActivity;

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
import static com.tongyuan.android.zhiquleyuan.R.id.tv_fragment_toy_details_babyName;
import static com.tongyuan.android.zhiquleyuan.R.id.tv_toy_details_playing;

/**
 * Created by android on 2017/1/9.
 */
public class ToyDetailsFragment extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AbsListView.OnScrollListener {

    private static final int BIND_BABYTO_TOY = 3001;
    private static final int UNBIND_BABY = 3002;
    public static final int THREE_CALL_PLAY = 3003;
    private View mToyDetails;
    private ImageView mCall;
    private TextView mToyManager;
    private ImageView mIv_fragment_toy_details_babyImg;
    private ImageView mIv_fragment_toy_details_toyimg;
    private TextView mTv_fragment_toy_details_acttime;
    private TextView mTv_fragment_toy_details_babyName;
    private TextView mTv_fragment_toy_details_toytype;
    private Bundle mArguments;
    public final String TAG = this.getClass().getSimpleName();
    private ListView mListviewRecommand;
    private DiscoveryListViewAdapter mLAdapter;
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
    private View mListviewtitle;
    private String mUserId;
    private String mBabyid;
    private List<QueryBabyListFromToyIdRes.BODYBean.LSTBean> mLst = new ArrayList<>();
    List<DiscoveryListResultBean.BODYBean.LSTBean> list = new ArrayList<>();
    private String mBabyName;
    String[] mStrings = new String[]{"与玩具通话", "与电视,玩具通话"};
    private boolean isShow = false;
    private RelativeLayout mToyPlayControl;
    private TextView mUpdate;
    private ImageView mIv_toyPlayControl_pre;
    private ImageView mIv_toyPlayControl_next;
    private ImageView mIv_toyPlayControl_play;
    private SeekBar mIv_toyPlayControl_seekbar;
    private SwipeRefreshLayout mRefresh;
    private View footerView;
    int currentPage = 1;
    public String NC = "-1";
    private boolean isToyPlaying = false;//从网络获取正在播放的
    private ImageView mSetToyVolume;
    private ImageView mIv_toyPlayControl_pause;

    public void setBabyImage(String babyImage) {
        this.mBabyimg = babyImage;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, "onViewCreated went");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        LogUtil.i(TAG, "onCreateView went");
        mToyDetails = inflater.inflate(R.layout.fragment_toy_details, null);
        mRefresh = (SwipeRefreshLayout) mToyDetails.findViewById(R.id.swipe_toydetails);
//        玩具正在播放的控制面板
        mToyPlayControl = (RelativeLayout) mToyDetails.findViewById(R.id.rl_fragment_toy_playingcontrol);
        mSetToyVolume = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_controltoy_setvolume);
        mIv_toyPlayControl_pre = (ImageView) mToyPlayControl.findViewById(R.id.iv_controltoy_prev);
        mIv_toyPlayControl_next = (ImageView) mToyPlayControl.findViewById(R.id.iv_controltoy_next);
        mIv_toyPlayControl_play = (ImageView) mToyPlayControl.findViewById(R.id.iv_controltoy_play);
        mIv_toyPlayControl_pause = (ImageView) mToyPlayControl.findViewById(R.id.iv_controltoy_pause);
        mIv_toyPlayControl_seekbar = (SeekBar) mToyPlayControl.findViewById(R.id.seekbar);
        mListviewtitle = inflater.inflate(R.layout.discovery_recyclerview_listview_title, null);
        LogUtil.i("timedate", "onCreateView");
        //TODO 动态设置布局的位置:mIsPlaying 显示,mListviewRecommand在它下面; 不显示,mListviewRecommand在Relativlayout的下面.
//        FrameLayout framelayoutControl =  (FrameLayout) mToyDetails.findViewById(R.id
// .fl_fragment_toy_details_playcontrol);
        mUpdate = (TextView) mToyDetails.findViewById(R.id.tv_updatetoy);
        mListviewRecommand = (ListView) mToyDetails.findViewById(R.id.lv_fragment_toy_details_recommand);
        LogUtil.i(TAG, "mLIstviewRecommand():" + mListviewRecommand.toString());
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
        mToyIsPlaying = (TextView) mToyDetails.findViewById(tv_toy_details_playing);
        if (isToyPlaying) {
            mToyIsPlaying.setOnClickListener(this);
        } else {
            mToyIsPlaying.setText("当前玩具未播放");
        }
        mLAdapter = new DiscoveryListViewAdapter(getContext(), list);
        mListviewRecommand.setAdapter(mLAdapter);
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.discovery_sub_item_foot, null);
        footerView.setVisibility(View.GONE);
        mListviewRecommand.addFooterView(footerView);
        mToyDetails.findViewById(R.id.back_btn).setOnClickListener(this);
//        mToyIsPlaying.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mSetToyVolume.setOnClickListener(this);
        mIv_toyPlayControl_pre.setOnClickListener(this);
        mIv_toyPlayControl_next.setOnClickListener(this);
        mIv_toyPlayControl_play.setOnClickListener(this);
        mIv_toyPlayControl_pause.setOnClickListener(this);
        mIv_toyPlayControl_seekbar.setOnSeekBarChangeListener(this);
        mListviewRecommand.setOnScrollListener(this);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                NC = "-1";
                getListRaw(false);
                Log.i(TAG, "(onRefresh) getlistraw: went");
                mRefresh.setRefreshing(false);
            }
        });
        mToyManagerFragment = new ToyManagerFragment();
        mListviewRecommand.addHeaderView(mListviewtitle);
        mListviewRecommand.setHeaderDividersEnabled(false);
        mListviewtitle.setClickable(false);
        LogUtil.i(TAG, "onCreateView return 之前");

        initView();

        return mToyDetails;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG, "onActivityCreated went");
        getListRaw(false);
//        LogUtil.i(TAG, "(onActivityCreated) getlistraw went");

    }

    private void getListRaw(final boolean isLoadMore) {
        LogUtil.i(TAG, Thread.currentThread().toString());
        if (!NC.equals("0")) {
            int page = currentPage;
            if (isLoadMore) {
                page++;
            }
            final DiscoveryListRequsetBean.BODYBean request = new DiscoveryListRequsetBean.BODYBean("10", String.valueOf(page));
            Call<SuperModel<DiscoveryListResultBean.BODYBean>> discoveryListResult = RequestManager.getInstance()
                    .getDiscoveryListResult(getContext(),
                            request);
            discoveryListResult.enqueue(new Callback<SuperModel<DiscoveryListResultBean.BODYBean>>() {
                @Override
                public void onResponse(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Response<SuperModel<DiscoveryListResultBean.BODYBean>>
                        response) {
                    if ("0".equals(response.body().CODE)) {
                        NC = response.body().BODY.getNC();
                        if (isLoadMore) {
                            currentPage++;
                        } else {
                            list.clear();
                            currentPage = 1;
                        }
                        list.addAll(response.body().BODY.getLST());
//                        if ("0".equals(response.body().BODY.getNC())) {
//                            list = response.body().BODY.getLST();
//                            footerView.setVisibility(View.GONE);
//                        } else {
//                            footerView.setVisibility(View.VISIBLE);
//                        }

//                    if ("0".equals(response.body().BODY.getNC())) {
//
//                    } else {
//
//                    }
//                    mLAdapter.notifyDataSetChanged();

                        //返回的list是一个空list
                        mListviewRecommand.setAdapter(mLAdapter);
                        mLAdapter.notifyDataSetChanged();
                        LogUtil.i(TAG, "onResponse: " + response.body().BODY);
                        LogUtil.d(TAG, "onResponse: " + SPUtils.getString(getActivity(), "token", ""));


//                    mLAdapter = new DiscoveryListViewAdapter(getContext(), response.body().BODY.getLST());
//                    mListviewRecommand.addHeaderView(mListviewtitle);
//                    mListviewtitle.setClickable(false);
//                    mListviewRecommand.setAdapter(mLAdapter);
//                    mListviewRecommand.setHeaderDividersEnabled(false);

                    } else if (response.body().CODE.equals("-10006")) {
                        SPUtils.putString(getContext(), "token", "");
                        ToastUtil.showToast(getActivity(), response.body().MSG);
                    } else {
                        ToastUtil.showToast(getActivity(), response.body().MSG);
                        footerView.setVisibility(View.GONE);
                    }
                    isLoading = false;
                }

                @Override
                public void onFailure(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Throwable t) {
                    ToastUtil.showToast(getActivity(), R.string.network_error);
                    LogUtil.d(TAG, "onFailure: (toydetailsfragment)" + t.toString());
                    isLoading = false;
                }
            });
        }

    }

    public void setData(SingleToyInfoRESBean.BODYBean response, String image) {
        LogUtil.i("timedate", "setData: went");
        mUserId = SPUtils.getString(getContext(), "userID", "");
        LogUtil.i(TAG, "setData: went");
        this.mResponse = response;
        this.mBabyimg = image;
        //要传给videoactivity的宝宝头像
        SPUtils.putString(getContext(), "babyimg", mBabyimg);
        mToyId = response.getID();
        mToken = SPUtils.getString(getContext(), "token", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");

        String acttime = response.getACTTIME();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSS");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parse = simpleDateFormat.parse(acttime);
            String formatTime = simpleDateFormat1.format(parse);
            mFormatTime = formatTime;
            LogUtil.i(TAG, "toydetails toydetailsfragment1:" + formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtil.i(TAG, "toydetails toydetailsfragment2:" + mFormatTime);
        LogUtil.i(TAG, "toydetails toydetailsfragment3:" + acttime);
        //保不齐以后要用这些参数,先取出来
        mImg = response.getIMG();
        mCode = response.getCODE();
        mId = response.getID();
        mName = response.getNAME();
        mOwnerid = response.getOWNERID();
        mOwnername = response.getOWNERNAME();
        LogUtil.i(TAG, "mbabyimg: " + mBabyimg.toString());
        //请求一次网络,查询当前玩具的宝宝信息: 3.4.24
        LogUtil.i(TAG, "setData() went");
        queryToyHasBindBaby();

        getListRaw(false);
        LogUtil.i(TAG, "(setData) getlistraw : went");
    }

    private void queryToyHasBindBaby() {
        LogUtil.i(TAG, Thread.currentThread().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSS");
        String currentTime = simpleDateFormat.format(new Date());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryBabyListFromToyIdReq.BODYBean bodyBean = new QueryBabyListFromToyIdReq.BODYBean(mToyId, "10", "1");
        QueryBabyListFromToyIdReq queryBabyListFromToyIdReq = new QueryBabyListFromToyIdReq("REQ", "QRYTOYB",
                mPhoneNum, currentTime, bodyBean, "",
                mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryBabyListFromToyIdReq);

        Call<QueryBabyListFromToyIdRes> queryBabyListFromToyIdResCall = allInterface.QUERY_BABY_LIST_FROM_TOY_ID_CALL
                (s);
        queryBabyListFromToyIdResCall.enqueue(new Callback<QueryBabyListFromToyIdRes>() {
            @Override
            public void onResponse(Call<QueryBabyListFromToyIdRes> call, Response<QueryBabyListFromToyIdRes> response) {
                //如果当前的玩具绑定了宝宝,则显示"解绑宝宝",如果没有绑定宝宝,则显示"绑定宝宝"
                if (response == null) {
                    return;
                }
                if (response != null && response.body().getCODE().equals("0")) {
                    mLst = response.body().getBODY().getLST();

                    if (mLst.size() == 0) {
                        mBindToy.setVisibility(View.VISIBLE);
                        mUnbindToy.setVisibility(View.GONE);
                        mTv_fragment_toy_details_babyName.setText(mBabyName);
                    } else {
                        for (int i = 0; i < mLst.size(); i++) {
                            mBabyName = mLst.get(i).getNAME();
                        }
                        //要传给videoactivity的宝宝的名字
                        SPUtils.putString(getContext(), "babyname", mBabyName);
                        mBindToy.setVisibility(View.GONE);
                        mUnbindToy.setVisibility(View.VISIBLE);
                    }
                    initView();
                }
            }

            @Override
            public void onFailure(Call<QueryBabyListFromToyIdRes> call, Throwable t) {

            }
        });
    }

    private void initView() {
        mLAdapter = new DiscoveryListViewAdapter(getContext(), list);
        mListviewRecommand.setAdapter(mLAdapter);
        checkStateInfo();
        LogUtil.i("timedate", "initView: went");
        if (mListviewRecommand == null)
            return;
        mTv_fragment_toy_details_acttime.setText(mFormatTime);
        mTv_fragment_toy_details_toytype.setText(mName);
        Glide.with(mContext).load(mImg).asBitmap().into(mIv_fragment_toy_details_toyimg);

        displayBabyInfo();
        if (mLst.size() == 0) {
            mTv_fragment_toy_details_babyName.setText(mOwnername);
        } else {
            mTv_fragment_toy_details_babyName.setText(mBabyName);
        }


        mCall.setOnClickListener(this);
        mToyManager.setOnClickListener(this);
        mUnbindToy.setOnClickListener(this);
        mBindToy.setOnClickListener(this);

        mListviewRecommand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int positionWithoutTitle = position - 1;
                if (position == 0) {
                    return;
                }
                MyPlayActivity.launch(getActivity(), mLAdapter.getList(), positionWithoutTitle);
            }
        });
    }

    private void displayBabyInfo() {
        Glide.with(mContext).load(mBabyimg).asBitmap().placeholder(R.mipmap.default_babyimage).centerCrop().transform(new GlideCircleTransform
                (mContext)).into(mIv_fragment_toy_details_babyImg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_updatetoy:
                updateToyVersion();
                break;
//            case tv_toy_details_playing:
//
//                isShow = !isShow;
//                if (isShow) {
//                    mToyPlayControl.setVisibility(View.VISIBLE);
//                    mRefresh.setVisibility(View.GONE);
////                    mListviewRecommand.setVisibility(View.GONE);
//                } else {
//                    mToyPlayControl.setVisibility(View.GONE);
//                    mRefresh.setVisibility(View.VISIBLE);
////                    mListviewRecommand.setVisibility(View.VISIBLE);
//                }
//                break;
            case iv_toy_details_call:
                //添加新的需求: 从只能双方通话,到多方通话
//                FrameLayout frameLayout = new FrameLayout(mContext);
//                ListView listView = new ListView(mContext);
//                frameLayout.addView();
                //通话控制
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("请选择通话对象")
                        .setItems(mStrings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    CallToToy();
//                                    ToastUtil.showToast(getActivity(), "跳转到通话界面");//是fragemnt界面
                                } else {
                                    //去扫描二维码,获得电视端的唯一识别码,然后就直接从二维码那个页面去获取三方会议号,并申请三方会议通话
                                    Intent it = new Intent();
                                    it.setClass(getActivity(), CaptureActivity.class);
                                    Bundle bundle = new Bundle();

                                    it.putExtra("flag", 2);
                                    bundle.putString("babyimgString", mBabyimg);
                                    bundle.putString("babynameString", mBabyName);
                                    bundle.putString("roomid", mRoomid);
                                    bundle.putString("token", mToken);
                                    bundle.putString("toyId", mToyId);
//
//                                    bundle.getString("babyimgString");
//                                    bundle.getString("babynameString");
//                                    bundle.getString("roomid");
//                                    bundle.getString("token");
//                                    bundle.getString("toyId");
//                                    LogUtil.i("captureactivity", "onClick00: --" + bundle.getString("babyimgString") +
//                                            "--");
//                                    LogUtil.i("captureactivity", "onClick00: --" + bundle.getString("babynameString") +
//                                            "--");
//                                    LogUtil.i("captureactivity", "onClick00: --" + bundle.getString("roomid") + "--");
//                                    LogUtil.i("captureactivity", "onClick00: --" + bundle.getString("token") + "--");
//                                    LogUtil.i("captureactivity", "onClick00: --" + bundle.getString("toyId") + "--");
                                    it.putExtras(bundle);
                                    startActivity(it);

//                                    startActivityForResult(it, THREE_CALL_PLAY);

                                }
                            }
                        }).setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.tv_toy_details_manager:
//                ToastUtil.showToast(getActivity(), "跳转到玩具管理界面");
//                if (SPUtils.getString(getActivity(), "ID", "").equals(mOwnerid)) {
                //TODO setdat
                mToyManagerFragment.setData(mResponse, mFormatTime, mBabyimg, mBabyName, mOwnername, mLst, mOwnerid);
                showFragment(mToyManagerFragment);
//                } else {
//                    ToastUtil.showToast(getActivity(), "您当前不是管理员,不能管理玩具");
//                }
                break;
            case R.id.tv_fragment_toy_details_unbind:
                //判断当前的userid是不是ownerid
                if (!mUserId.equals(mOwnerid)) {
                    ToastUtil.showToast(mContext, "您不是该玩具的持有人,不能解绑宝宝");
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
//                ToastUtil.showToast(getActivity(), "details  unbind");
                break;

            case R.id.tv_fragment_toy_details_bind:
                if (!mUserId.equals(mOwnerid)) {
                    ToastUtil.showToast(mContext, "您不是该玩具的持有人,不能绑定宝宝");
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
//                ToastUtil.showToast(mContext, "details  bind");
                break;
            case R.id.back_btn:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.backToTop();
//                ToastUtil.showToast(mContext, "back");
                break;
            case R.id.iv_fragment_controltoy_setvolume:
                ToastUtil.showToast(mContext, "setvolume");
                break;
            case R.id.iv_controltoy_prev:
                controlToyPlay("4");//上一首 ,下一首 ,都是用的4
                ToastUtil.showToast(mContext, "controlpre");
                break;
            case R.id.iv_controltoy_next:
                controlToyPlay("4");
                ToastUtil.showToast(mContext, "controlnext");
                break;
            case R.id.iv_controltoy_play:
                controlToyPlay("1");
                ToastUtil.showToast(mContext, "controlplay");
                break;
            case R.id.iv_controltoy_pause:
                controlToyPlay("2");
                ToastUtil.showToast(mContext, "controlpause");
                break;
            default:
                break;
            

        }

    }

    private void updateToyVersion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        UpdateToyVersionReqBean.ParamBean paramBean = new UpdateToyVersionReqBean.ParamBean(ToySelectorFragment.mToyId);
        UpdateToyVersionReqBean updateToyVersionReqBean = new UpdateToyVersionReqBean("update", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(updateToyVersionReqBean);
        Call<UpdateToyVersionResBean> updateToyVersionResBeanCall = allInterface.UPDATE_TOY_VERSION_RES_BEAN_CALL(s);
        updateToyVersionResBeanCall.enqueue(new Callback<UpdateToyVersionResBean>() {
            @Override
            public void onResponse(Call<UpdateToyVersionResBean> call, Response<UpdateToyVersionResBean> response) {
                ToastUtil.showToast(getContext(), "发送玩具更新请求成功");
                LogUtil.i(TAG, "(updatetoy)onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<UpdateToyVersionResBean> call, Throwable t) {
                LogUtil.i(TAG, "(updatetoy)onFailure: " + t);
            }
        });
    }

    //3.4.19 删除玩具和宝宝关心
//    private void unBindBabyToToy() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constant.baseurl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AllInterface allInterface = retrofit.create(AllInterface.class);
//        UnbindBabyToToyReqBean.BODYBean.LSTBean lstBean = new UnbindBabyToToyReqBean.BODYBean.LSTBean(mToyId,
// mToycode, );
//        List<UnbindBabyToToyReqBean.BODYBean.LSTBean> lstBeanList = new ArrayList<>();
//        lstBeanList.add(lstBean);
//
//        UnbindBabyToToyReqBean.BODYBean bodyBean = new UnbindBabyToToyReqBean.BODYBean(lstBeanList);
//        UnbindBabyToToyReqBean unbindBabyToToyReqBean = new UnbindBabyToToyReqBean("REQ", "DTOYB", mPhoneNum, new
// SimpleDateFormat
// ("yyyyMMddmmssSSS")
//                .format(new Date()), bodyBean, "", mToken, "1");
//
//
//    }

    private void CallToToy() {
        final String token = SPUtils.getString(getActivity(), "token", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        CallToToyReq.ParamBean param = new CallToToyReq.ParamBean(mToyId, "1", "", "");
        CallToToyReq callToToyReq = new CallToToyReq("contact_toy", param, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(callToToyReq);
        Call<CallToToyRes> callToToyResCall = allInterface.CALL_TO_TOY_RES_CALL(s);
        callToToyResCall.enqueue(new Callback<CallToToyRes>() {
            @Override
            public void onResponse(Call<CallToToyRes> call, Response<CallToToyRes> response) {
                LogUtil.i(TAG, "onResponse: RESPONSE" + response.message());
                LogUtil.i(TAG, "onResponse:+response " + response.body().toString());
                mRoomid = response.body().getRoomid();
                if (response.body().getCode().equals("-10009")) {
                    ToastUtil.showToast(getActivity(), "玩具未登录");
                    return;
                }
                if (mRoomid == null) {
                    ToastUtil.showToast(getActivity(), "玩具正在休眠");
                    return;
                }
                if (response.body().getCode().equals("-10012")) {
                    ToastUtil.showToast(getActivity(), "玩具通话中");
                    return;
                }
                if (response.body().getCode().equals("-10008")) {
                    ToastUtil.showToast(getActivity(), "推送失败");
                    return;
                }
                VideoActivity.launch(mContext, mBabyimg, mBabyName, mRoomid, mToken, mToyId, null);
//                Bundle bundle = new Bundle();
//                bundle.putString("roomid", mRoomid);
//                bundle.putString("token", token);
//                bundle.putString("toyid", mId);
//                bundle.putString("babyimg",mBabyimg);
//                bundle.putString("babyname",mBabyName);
//
//                startActivity(new Intent(getActivity(), VideoActivity.class).putExtras(bundle));
//                ToastUtil.showToast(getActivity(), "roomid" + mRoomid);
                LogUtil.i("555555", "onResponse:+mRoomid " + mRoomid);

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
                LogUtil.i("111111", t.toString());
                ToastUtil.showToast(mContext, t.toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(TAG, "onStart went ");

        //TODO 查询当前玩具是否正在播放音乐 3.4.43  暂时先不查询了,因为玩具端不知道怎么上传当前播放的状态给服务器
        queryPlayingMusic();
    }

    private void queryPlayingMusic() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryPlayingMusicReqBean.BODYBean bodyBean = new QueryPlayingMusicReqBean.BODYBean(ToySelectorFragment.mToyId);
        QueryPlayingMusicReqBean queryPlayingMusicReqBean = new QueryPlayingMusicReqBean("REQ", "QPRES", mPhoneNum,
                mTime, bodyBean, "", "token",
                "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryPlayingMusicReqBean);
        Call<QueryPlayingMusicResBean> queryPlayingMusicResBeanCall = allInterface.QUERY_PLAYING_MUSIC_RES_BEAN_CALL(s);
        queryPlayingMusicResBeanCall.enqueue(new Callback<QueryPlayingMusicResBean>() {
            @Override
            public void onResponse(Call<QueryPlayingMusicResBean> call, final Response<QueryPlayingMusicResBean> response) {
                LogUtil.i("55555", response.body().toString());

//                if (response.body().getBODY().getID().equals("")) {
                if (!response.body().getCODE().equals("0")) {
                    ToastUtil.showToast(getContext(), "当前玩具没有正在播放的歌曲");
                    mToyIsPlaying.setVisibility(View.GONE);
                } else {
                    mToyIsPlaying.setVisibility(View.VISIBLE);
                    mToyIsPlaying.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isShow = !isShow;
                            if (isShow) {
                                mToyIsPlaying.setText("玩具正在播放 : " + response.body().getBODY().getNAME() + "歌曲");
                                mToyPlayControl.setVisibility(View.VISIBLE);
                                mIv_toyPlayControl_play.setVisibility(View.GONE);
                                mIv_toyPlayControl_pause.setVisibility(View.VISIBLE);
                                mRefresh.setVisibility(View.GONE);
                            } else {
                                mToyPlayControl.setVisibility(View.GONE);
                                mRefresh.setVisibility(View.VISIBLE);
                            }

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<QueryPlayingMusicResBean> call, Throwable t) {
                ToastUtil.showToast(mContext, "请检查网络");
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG, "onPause went ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(mContext).load(mBabyimg).asBitmap().centerCrop().transform(new GlideCircleTransform(mContext)).into
                (mIv_fragment_toy_details_babyImg);
        LogUtil.i(TAG, "onResume went ");
        queryToyHasBindBaby();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy went ");

    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        LogUtil.i(TAG, "onHiddenChanged went");
//        queryToyHasBindBaby();
////        checkStateInfo();
//        initView();
//
//    }

    //拉去玩具的状态信息
    private void checkStateInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl).addConverterFactory(GsonConverterFactory
                .create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        GetInstantStateInfoReq.BODYBean bodyBean = new GetInstantStateInfoReq.BODYBean(mToyId);
        GetInstantStateInfoReq getInstantStateInfoReq = new GetInstantStateInfoReq("REQ", "TOYSTATE", mPhoneNum,
                mTime, bodyBean, "", mToken, "1");

        Gson gson = new Gson();
        String s = gson.toJson(getInstantStateInfoReq);
        Call<GetInstantStateInfoRes> getInstantStateInfoResCall = allInterface.GET_INSTANT_STATE_INFO_RES_CALL(s);
        getInstantStateInfoResCall.enqueue(new Callback<GetInstantStateInfoRes>() {
            @Override
            public void onResponse(Call<GetInstantStateInfoRes> call, Response<GetInstantStateInfoRes> response) {
                if (response != null && response.body().getCODE().equals("0")) {
////                    ToastUtil.showToast(getActivity(), "拉取玩具即时状态返回的response为空");
//                } else {
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
                    if (!vol.equals("")) {
                        volValue = Integer.parseInt(vol);
                        SPUtil.getInstance().put("toyvolume", volValue);

                    }
                    if (!elec.equals("")) {
                        elecValue = Integer.parseInt(elec);

                    }

//                    LogUtil.i(TAG, "onResponse:camera" + camera);
//                    LogUtil.i(TAG, "onResponse:elec" + elec);
//                    LogUtil.i(TAG, "onResponse:mic" + mic);
//                    LogUtil.i(TAG, "onResponse:online" + online);
//                    LogUtil.i(TAG, "onResponse:vol" + vol);
//                    LogUtil.i(TAG, "onResponse:wifi" + wifi);

//                    LogUtil.i(TAG, "onResponse: wifiValue" + wifiValue);
//                    LogUtil.i(TAG, "onResponse: elecValue" + elecValue);
//                    LogUtil.i(TAG, "onResponse: volValue" + volValue);
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
                ToastUtil.showToast(getActivity(), t.toString());
                LogUtil.i(TAG, "onFailure: " + t.toString());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100 && data != null) {
            Bundle bundle = data.getBundleExtra("bindbabybundle");
            mImg = bundle.getString("babyimage");
            queryToyHasBindBaby();
        }
//        if (requestCode == BIND_BABYTO_TOY && resultCode == 00) {
//            queryToyHasBindBaby();
//        } else if (requestCode == UNBIND_BABY && resultCode == 00) {
//            queryToyHasBindBaby();
//        } else if (requestCode == THREE_CALL_PLAY && resultCode == RESULT_OK) {
//            String scan_result = data.getStringExtra("SCAN_RESULT");
//            System.out.println("scan_result------" + scan_result);
//            LogUtil.i("scan_result", "onActivityResult: +scan_result" + scan_result);
//        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private int totalItemCount;
    private int lastItem;
    private boolean isLoading = false;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!isLoading && lastItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
            //显示加载更多
            isLoading = true;
            getListRaw(true);
            Log.i(TAG, "(onScrollStateChanged) getlistraw: went");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public void controlToyPlay(String method) {
        String resourseId = "";
        String toyId = mToyId;

        ControlToyPlayMusicReqBean.ParamBean paramBean = null;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        if (method.equals("1")) {
            paramBean = new ControlToyPlayMusicReqBean.ParamBean(toyId, method, resourseId, "0");
        } else if (method.equals("0")) {
            paramBean = new ControlToyPlayMusicReqBean.ParamBean(toyId, method, resourseId, "0");
        } else if (method.equals("2")) {
            paramBean = new ControlToyPlayMusicReqBean.ParamBean(toyId, method, resourseId, "0");

        }
        ControlToyPlayMusicReqBean controlToyPlayMusicReqBean = new ControlToyPlayMusicReqBean("control_play", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(controlToyPlayMusicReqBean);
        Call<ControlToyPlayMusicResBean> controlToyPlayMusicResBeanCall = allInterface.CONTROL_TOY_PLAY_MUSIC_RES_BEAN_CALL(s);
        controlToyPlayMusicResBeanCall.enqueue(new Callback<ControlToyPlayMusicResBean>() {
            @Override
            public void onResponse(Call<ControlToyPlayMusicResBean> call, Response<ControlToyPlayMusicResBean> response) {
                if (response.body().getCode().equals("0")) {
                    ToastUtil.showToast(mContext, "推送成功");
                }
            }

            @Override
            public void onFailure(Call<ControlToyPlayMusicResBean> call, Throwable t) {
                ToastUtil.showToast(mContext, "请检查网络");
            }
        });
    }
}
