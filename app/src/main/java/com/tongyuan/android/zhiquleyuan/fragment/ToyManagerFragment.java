package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.NoDisturbActivity;
import com.tongyuan.android.zhiquleyuan.activity.SetupWlanActivity;
import com.tongyuan.android.zhiquleyuan.adapter.ToyMemberAdapter;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReQBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
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
public class ToyManagerFragment extends Fragment implements View.OnClickListener {
    private final String TAG = "tmf111111";
    private Button mBt_fragment_managetoy_setupwlan;
    private Button mBt_fragment_managetoy_nodisturb;
    private TextView mTv_frament_managetoy_manager;
    private TextView mTv_fragment_toy_details_unbind;
    private ImageView mIv_fragmenft_managetoy_toyimg;
    private TextView mTv_fragment_managetoy_toytype;
    private TextView mTv_fragment_managetoy_desc;
    private TextView mTv_fragment_managetoy_acttime;
    private RecyclerView mRecyclerview_fragment_managetoy;
    private ImageView mBabyImg;
    private TextView mBabyName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View fragment_manageToy = inflater.inflate(R.layout.fragment_managetoy, null);
//        fragment_manageToy.findViewById(R.id.)
        mBt_fragment_managetoy_setupwlan = (Button) fragment_manageToy.findViewById(R.id.bt_fragment_managetoy_setupwlan);
        mBt_fragment_managetoy_nodisturb = (Button) fragment_manageToy.findViewById(R.id.bt_fragment_managetoy_nodisturb);
        mTv_frament_managetoy_manager = (TextView) fragment_manageToy.findViewById(R.id.tv_frament_managetoy_manager);
        mTv_fragment_toy_details_unbind = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_unbindtoy);
//        向id中添加数据内容
        mIv_fragmenft_managetoy_toyimg = (ImageView) fragment_manageToy.findViewById(R.id.iv_fragment_managetoy_toyimg);
        mTv_fragment_managetoy_toytype = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_toytype);
        mTv_fragment_managetoy_desc = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_desc);
        mTv_fragment_managetoy_acttime = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_acttime);
        mRecyclerview_fragment_managetoy = (RecyclerView) fragment_manageToy.findViewById(R.id.recyclerview_fragment_managetoy);
        mBabyImg = (ImageView) fragment_manageToy.findViewById(R.id.iv_fragment_managetoy_babyhead);
        mBabyName = (TextView) fragment_manageToy.findViewById(R.id.tv_fragment_managetoy_babyname);
        initData();
        initListener();
        return fragment_manageToy;
    }

    private void initData() {
        String token = SPUtils.getString(getActivity(), "TOKEN", "");
        String phoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(new Date());


        Bundle arguments = getArguments();
        SingleToyInfoRESBean.BODYBean response = arguments.getParcelable("response");
        String toyId = response.getID();
        String toyCode = response.getCODE();
        String acttime = arguments.getString("acttime");
        String img = response.getIMG();
        String babyimg = arguments.getString("babyimg");
        mTv_fragment_managetoy_toytype.setText(response.getCODE());
        mTv_fragment_managetoy_acttime.setText(acttime);

        //玩具图片
        Glide.with(getActivity()).load(img).asBitmap().into(mIv_fragmenft_managetoy_toyimg);
        //baby头像
        Glide.with(getActivity()).load(babyimg).asBitmap().centerCrop().into(new BitmapImageViewTarget(mBabyImg) {
            @Override
            protected void setResource(Bitmap resource) {

                RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                mRoundedBitmapDrawable.setCircular(true);
                mBabyImg.setImageDrawable(mRoundedBitmapDrawable);
            }


        });
        //获取成员信息,需要传什么参数,去访问哪个接口  3.4.48接口
        getToyManager(time, token, phoneNum, toyId, toyCode);
        Log.i(TAG, "initData:toyimg ");
    }

    private void getToyManager(String time, String token, String phoneNum, String toyId, final String toyCode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryToyMemberReQBean.BODYBean bodyBean = new QueryToyMemberReQBean.BODYBean(toyId, toyCode, "10", "1");
        QueryToyMemberReQBean queryToyMemberReQBean = new QueryToyMemberReQBean("REQ", "QTOYU", phoneNum, time, bodyBean, "", token, "1");
        Gson gson = new Gson();
        String params = gson.toJson(queryToyMemberReQBean);
        Call<QueryToyMemberReSBean> queryToyMemberResult = allInterface.getQueryToyMemberResult(params);
        queryToyMemberResult.enqueue(new Callback<QueryToyMemberReSBean>() {
            @Override
            public void onResponse(Call<QueryToyMemberReSBean> call, Response<QueryToyMemberReSBean> response) {
                if (response != null) {
                    ToastUtil.showToast(getContext(), "response" + response);
                    Log.i(TAG, "onResponse: ++" + response);
                    final List<QueryToyMemberReSBean.BODYBean.LSTBean> lst = response.body().getBODY().getLST();
                    ToyMemberAdapter toyMemberAdapter = new ToyMemberAdapter(getActivity(), R.layout.manager_gridview_item, lst);
                    mRecyclerview_fragment_managetoy.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                    mRecyclerview_fragment_managetoy.setAdapter(toyMemberAdapter);
                    toyMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            ToastUtil.showToast(getActivity(),"当前点击的是:"+position);
                        }
                    });
                    toyMemberAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                            //不管长按的是哪个item,都让他显示所有的小减号,完成相关的操作以后,再全部消失;
                            //长按一个item的时候,设置监听,让所有的item都显示或不显示减号
                            ImageView remove = (ImageView) view.findViewById(R.id.iv_manager_gridview_item_remove);
                                remove.setVisibility(View.VISIBLE);

                            ToastUtil.showToast(getActivity(),"当前长摁的是:"+position);
                            return true;

                        }
                    });
//                    toyMemberAdapter.

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
            default:
                break;
        }
    }
}
