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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.BindBabyActivity;
import com.tongyuan.android.zhiquleyuan.activity.NoDisturbActivity;
import com.tongyuan.android.zhiquleyuan.activity.SetupWlanActivity;
import com.tongyuan.android.zhiquleyuan.adapter.ToyMemberAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReQBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.view.MyGridView;

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
 * Created by android on 2017/3/12.
 */
//TODO 从上一个页面传过来的数据,设置到页面的各个控件上.  玩具解除绑定,点击以后应该弹出的是宝宝的列表,然后选择,确定,提交网络请求,解除成功.
public class ToyManagerFragment extends BaseFragment implements View.OnClickListener {
    private final String TAG = "111";
    private Button mBt_fragment_managetoy_setupwlan;
    private Button mBt_fragment_managetoy_nodisturb;
    private TextView mTv_frament_managetoy_manager;
    private TextView mTv_fragment_toy_details_unbind;
    private TextView mTv_fragment_toy_details_bind;
    private ImageView mIv_fragmenft_managetoy_toyimg;
    private TextView mTv_fragment_managetoy_toytype;
    private TextView mTv_fragment_managetoy_desc;
    private TextView mTv_fragment_managetoy_acttime;
    //    private RecyclerView mRecyclerview_fragment_managetoy;
    private ImageView mBabyImg;
    private TextView mBabyName;
    private MyGridView mMygrid;
    private List<QueryToyMemberReSBean.BODYBean.LSTBean> lst = new ArrayList<>();
    ToyMemberAdapter toyMemberAdapter;
    boolean removeState = false;//定义删除状态
    private SingleToyInfoRESBean.BODYBean mResponse;
    private String mToken;
    private String mPhoneNum;
    private String formatTime;
    private String mTime;
    private String mToyId;
    private String mToyCode;
    private String mBabyimg;
    private String mImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View fragment_manageToy = inflater.inflate(R.layout.fragment_managetoy, null);
        mBt_fragment_managetoy_setupwlan = (Button) fragment_manageToy.findViewById(R.id.bt_fragment_managetoy_setupwlan);
        mBt_fragment_managetoy_nodisturb = (Button) fragment_manageToy.findViewById(R.id.bt_fragment_managetoy_nodisturb);
        mTv_frament_managetoy_manager = (TextView) fragment_manageToy.findViewById(R.id.tv_frament_managetoy_manager);
        mTv_fragment_toy_details_unbind = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_unbindtoy);
        mTv_fragment_toy_details_bind = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_bindtoy);
//        向id中添加数据内容
        mIv_fragmenft_managetoy_toyimg = (ImageView) fragment_manageToy.findViewById(R.id.iv_fragment_managetoy_toyimg);
        mTv_fragment_managetoy_toytype = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_toytype);
        mTv_fragment_managetoy_desc = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_desc);
        mTv_fragment_managetoy_acttime = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_acttime);
//        mRecyclerview_fragment_managetoy = (RecyclerView) fragment_manageToy.findViewById(R.id.recyclerview_fragment_managetoy);

        mMygrid = (MyGridView) fragment_manageToy.findViewById(R.id.mygrid);
        toyMemberAdapter = new ToyMemberAdapter(getActivity(), lst, mResponse);
        mMygrid.setNumColumns(5);
        mMygrid.setAdapter(toyMemberAdapter);
        mBabyImg = (ImageView) fragment_manageToy.findViewById(R.id.iv_fragment_managetoy_babyhead);
        mBabyName = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_babyname);
        initData();
        initListener();
        Log.i("manager", "onCreateView");
        refreshView();
        return fragment_manageToy;
    }

    private void initData() {


        mToken = SPUtils.getString(getActivity(), "TOKEN", "");
        mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
        //获取成员信息,需要传什么参数,去访问哪个接口  3.4.48接口
        //getToyMember(mTime, mToken, mPhoneNum, mToyId, mToyCode);
        Log.i(TAG, "initData:toyimg ");
    }

    private void getToyMember(String time, String token, String phoneNum, String toyId, final String toyCode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryToyMemberReQBean.BODYBean bodyBean = new QueryToyMemberReQBean.BODYBean(toyId, toyCode, "10", "1");
        QueryToyMemberReQBean queryToyMemberReQBean = new QueryToyMemberReQBean("REQ", "QTOYU", phoneNum, time, bodyBean, "", token, "1");
        Gson gson = new Gson();
        String params = gson.toJson(queryToyMemberReQBean);
        Call<QueryToyMemberReSBean> queryToyMemberResult = allInterface.QUERY_TOY_MEMBER_RES_BEAN_CALL(params);
        queryToyMemberResult.enqueue(new Callback<QueryToyMemberReSBean>() {
            @Override
            public void onResponse(Call<QueryToyMemberReSBean> call, Response<QueryToyMemberReSBean> response) {
                if (response != null) {
//                    ToastUtil.showToast(getActivity(), "response" + response);
                    //玩具群成员
                    Log.i(TAG, "onResponse:toymem ++" + response.body().toString());


                    lst.clear();
                    lst.addAll(response.body().getBODY().getLST());
                    Log.i("111111", "onResponse: " + lst.size());
                    if (toyMemberAdapter != null) {
                        toyMemberAdapter.notifyDataSetChanged();
                    }
//                    mMygrid.setNumColumns(5);
//                    toyMemberAdapter = new ToyMemberAdapter(getActivity(), lst, mResponse);
//                    mMygrid.setAdapter(toyMemberAdapter);

                } else {

                    Log.i(TAG, "onResponse: ++response为空");

                }
            }

            @Override
            public void onFailure(Call<QueryToyMemberReSBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "toymanagerfragment的网络链接异常");
            }
        });
    }

    private void initListener() {
        mBt_fragment_managetoy_nodisturb.setOnClickListener(this);
        mBt_fragment_managetoy_setupwlan.setOnClickListener(this);
        mTv_frament_managetoy_manager.setOnClickListener(this);
        mTv_fragment_toy_details_unbind.setOnClickListener(this);
        mTv_fragment_toy_details_bind.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fragment_managetoy_nodisturb:
                Intent intent = new Intent(getActivity(), NoDisturbActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_fragment_managetoy_setupwlan:
                Intent intent1 = new Intent(getActivity(), SetupWlanActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_frament_managetoy_manager:
                ToastUtil.showToast(getActivity(), "set manager");
                break;
            case R.id.tv_fragment_managetoy_unbindtoy:
                ToastUtil.showToast(getActivity(), "Unbind");
                break;
            case R.id.tv_fragment_managetoy_bindtoy:
                //这里添加玩具成功,需要去绑定宝宝的信息,所以跳转到宝宝绑定的页面
                    Intent intent2 = new Intent();
                    intent2.setClass(getContext(), BindBabyActivity.class);
                    startActivity(intent2);
                ToastUtil.showToast(getActivity(), "bind");
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //刷新

        getToyMember(mTime, mToken, mPhoneNum, mToyId, mToyCode);
    }

    public void setData(SingleToyInfoRESBean.BODYBean response, String formatTime, String babyimg) {
        this.mResponse = response;
        this.mBabyimg = babyimg;
        Log.i("manager", "setData");
        mToken = SPUtils.getString(getActivity(), "TOKEN", "");
        this.formatTime = formatTime;
        mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
//        Bundle arguments = getArguments();
//        //singletoyinfo
//        mResponse = arguments.getParcelable("response");

        mToyId = mResponse.getID();
        mToyCode = mResponse.getCODE();
//        String acttime = arguments.getString("acttime");

        //获取成员信息,需要传什么参数,去访问哪个接口  3.4.48接口
        getToyMember(mTime, mToken, mPhoneNum, mToyId, mToyCode);

        if (mTv_fragment_managetoy_toytype == null)
            return;
//        String babyimg = arguments.getString("babyimg");
        refreshView();
    }

    private void refreshView() {
        if(mResponse == null)
            return;
        mTv_fragment_managetoy_toytype.setText(mResponse.getCODE());
        mTv_fragment_managetoy_acttime.setText(formatTime);
        String img = mResponse.getIMG();
        //玩具图片
        Glide.with(getActivity()).load(img).asBitmap().into(mIv_fragmenft_managetoy_toyimg);
        //baby头像
        Glide.with(getActivity()).load(mBabyimg).asBitmap().centerCrop().into(new BitmapImageViewTarget(mBabyImg) {
            @Override
            protected void setResource(Bitmap resource) {

                RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                mRoundedBitmapDrawable.setCircular(true);
               mBabyImg.setImageDrawable(mRoundedBitmapDrawable);
            }
        });
        if (toyMemberAdapter != null) {
            toyMemberAdapter.notifyDataSetChanged();
        }
    }



}
