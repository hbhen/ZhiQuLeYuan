package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryListViewAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.Items;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.RecommandBean;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mToyDetails = inflater.inflate(R.layout.fragment_toy_details, null);

//        FrameLayout framelayoutControl =  (FrameLayout) mToyDetails.findViewById(R.id.fl_fragment_toy_details_playcontrol);
        mListviewRecommand = (ListView) mToyDetails.findViewById(R.id.lv_fragment_toy_details_recommand);

        mCall = (ImageView) mToyDetails.findViewById(R.id.iv_toy_details_call);
        mToyManager = (TextView) mToyDetails.findViewById(R.id.tv_toy_details_manager);

        mIv_fragment_toy_details_babyImg = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_babyImg);
        mIv_fragment_toy_details_toyimg = (ImageView) mToyDetails.findViewById(R.id.iv_fragment_toy_details_toyimg);
        mTv_fragment_toy_details_acttime = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_acttime);
        mTv_fragment_toy_details_babyName = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_babyName);
        mTv_fragment_toy_details_toytype = (TextView) mToyDetails.findViewById(R.id.tv_fragment_toy_details_toytype);
        mToyManagerFragment = new ToyManagerFragment();
        return mToyDetails;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        intiData();

    }

    private void intiData() {
        Bundle arguments = getArguments();
        SingleToyInfoRESBean.BODYBean response = arguments.getParcelable("response");
        mToyId = response.getID();
        mToken = SPUtils.getString(getContext(), "TOKEN", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
    }

    public void setArgments(SingleToyInfoRESBean.BODYBean response) {
        mToyId = response.getID();
        mToken = SPUtils.getString(getContext(), "TOKEN", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
    }

    private void initView() {
        final List<Items> list = new ArrayList<Items>();
        list.add(new Items("第一种布局", null));
        list.add(new Items(null, "第二种布局"));
        DiscoveryListViewAdapter discoveryListViewAdapter = new DiscoveryListViewAdapter(getActivity(), list);
        mListviewRecommand.setAdapter(discoveryListViewAdapter);
        mListviewRecommand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击不同的item之后都进入音乐播放器.

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                RecommandBean recommandBean = new RecommandBean();

                bundle.putParcelable("key", recommandBean);
                intent.putExtras(bundle);
                intent.setClass(getActivity(), MyPlayActivity.class);
                startActivity(intent);
                ToastUtil.showToast(getContext(), "当前的position是" + position);
            }
        });
        Bundle arguments = getArguments();
        mResponse = arguments.getParcelable("response");
        mBabyimg = arguments.getString("babyimg");
        String acttime = mResponse.getACTTIME();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        mFormatTime = acttime;
        try {
            mFormatTime = simpleDateFormat1.format(simpleDateFormat.parse(acttime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保不齐以后要用这些参数,先取出来
        mImg = mResponse.getIMG();
        mCode = mResponse.getCODE();
        mId = mResponse.getID();
        mName = mResponse.getNAME();
        mOwnerid = mResponse.getOWNERID();
        mOwnername = mResponse.getOWNERNAME();
        mTv_fragment_toy_details_acttime.setText(mFormatTime);
        mTv_fragment_toy_details_toytype.setText(mCode);
        Glide.with(getActivity()).load(mImg).asBitmap().into(mIv_fragment_toy_details_toyimg);
//        Log.d(TAG, "mToycode: " + mToycode);
        Glide.with(getActivity()).load(mBabyimg).asBitmap().into(new BitmapImageViewTarget(mIv_fragment_toy_details_babyImg) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                mRoundedBitmapDrawable.setCircular(true);
                mIv_fragment_toy_details_babyImg.setImageDrawable(mRoundedBitmapDrawable);
            }
        });

        mCall.setOnClickListener(this);
        mToyManager.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toy_details_call:
                //通话控制
                CallToToy();

                ToastUtil.showToast(getActivity(), "跳转到通话界面");//是fragemnt界面
//
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fl_fragmentcontainer, new VideoFragment()).commit();
                break;
            case R.id.tv_toy_details_manager:

                ToastUtil.showToast(getActivity(), "跳转到玩具管理界面");
                if (SPUtils.getString(getActivity(), "ID", "").equals(mOwnerid)) {
                    Bundle bundle = new Bundle();
                    //传了一个bean过去,想用就用吧.
                    bundle.putParcelable("response", mResponse);
                    //传单个参数,上面的那个bean其实已经包含了这些信息,这样看着更具体一些
                    bundle.putString("acttime", mFormatTime);
                    bundle.putString("babyimg", mBabyimg);
                    bundle.putString("img", mImg);
                    mToyManagerFragment.setArguments(bundle);
                    FragmentManager fragmentManager1 = getFragmentManager();
                    fragmentManager1.beginTransaction().replace(R.id.fl_fragmentcontainer, mToyManagerFragment).commit();
                } else {
                    ToastUtil.showToast(getActivity(), "您当前不是管理员,不能管理玩具");
                }
                break;
            case R.id.tv_fragment_toy_details_unbind:
                ToastUtil.showToast(getActivity(), "details` unbind");
        }

    }
//
    private void CallToToy() {
        String token = SPUtils.getString(getActivity(), "TOKEN", "");
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface=retrofit.create(AllInterface.class);
        CallToToyReq.ParamBean param=new CallToToyReq.ParamBean(mToyId,"1","666666");
        CallToToyReq callToToyReq=new CallToToyReq("contact_toy",param,mToken);
        Gson gson=new Gson();
        String s = gson.toJson(callToToyReq);
        Call<CallToToyRes> callToToyResCall = allInterface.CALL_TO_TOY_RES_CALL(s);
        callToToyResCall.enqueue(new Callback<CallToToyRes>() {
            @Override
            public void onResponse(Call<CallToToyRes> call, Response<CallToToyRes> response) {
                if (response.body()!=null&&response.body().getMsg().equals("")){
                    mRoomid = response.body().getRoomid();
                    Log.i("555555", "onResponse:+mRoomid "+mRoomid);
                }
            }

            @Override
            public void onFailure(Call<CallToToyRes> call, Throwable t) {

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

            }

            @Override
            public void onFailure(Call<QueryPlayingMusicResBean> call, Throwable t) {

            }
        });


    }
}
