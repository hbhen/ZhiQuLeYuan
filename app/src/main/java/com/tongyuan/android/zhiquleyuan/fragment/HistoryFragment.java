package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.HistoryAdapter;
import com.tongyuan.android.zhiquleyuan.adapter.HistorySwipeAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryResultBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.swipe.refreshlib.AbListView;
import com.tongyuan.android.zhiquleyuan.swipe.refreshlib.library.PullToRefreshBase;
import com.tongyuan.android.zhiquleyuan.swipe.refreshlib.library.PullToRefreshScrollView;
import com.tongyuan.android.zhiquleyuan.utils.CallManager;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

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
 * Created by android on 2016/12/3.
 */

public class HistoryFragment extends BaseFragment implements View.OnClickListener {

    private AbListView lv_fragment_history;
    private ImageView iv_item_history_pic;
    private Response<CallHistoryResultBean> mResponseCallHist;
    private final String TAG = "444444";
    private String mPhoneNum;
    private String mTime;
    private int start = 0; // 当前页数
    private int limit = 8; // 为每页显示数据数目
    private int totalCount = 50;
    private RelativeLayout mItem_history_header;
    private PullToRefreshScrollView mPulltorefreshscrollview;
    private Handler mHandler;
    private HistorySwipeAdapter mHistorySwipeAdapter;
    private ArrayList<CallHistoryResultBean.BODYBean.LSTBean> mLst;
    private PullToRefreshBase.Mode mCurrentMode;
    private List<String> listData = new ArrayList<>();
    private List<String> listDataMore = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefresh;
    private String mToken;
    private ImageView mHistory_call;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View historyRoot = inflater.inflate(R.layout.fragment_history, null);
        lv_fragment_history = (AbListView) historyRoot.findViewById(R.id.lv_fragment_history);
        iv_item_history_pic = (ImageView) lv_fragment_history.findViewById(R.id.iv_item_history_pic);
        mItem_history_header = (RelativeLayout) historyRoot.findViewById(R.id.rl_history_title);
        mHistory_call = (ImageView) mItem_history_header.findViewById(R.id.iv_history_call);
//        mItem_history_header = inflater.inflate(R.layout.item_history_header, null);

        mSwipeRefresh = (SwipeRefreshLayout) historyRoot.findViewById(R.id.swiperefresh_history);
//        mPulltorefreshscrollview = (PullToRefreshScrollView) historyRoot.findViewById(R.id.pulltorefreshscrollview);

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(mPhoneNum, mTime, mToken);
                mSwipeRefresh.setRefreshing(false);
            }
        });
        return historyRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!SPUtils.getString(getActivity(), "token", "").equals("")) {
            initData();
            initListener();
        } else {
            ToastUtil.showToast(getContext(), "未登录,请登录");
            return;
        }
    }

    private void initListener() {
        mItem_history_header.setOnClickListener(this);
    }


    private void initData() {
        mToken = SPUtils.getString(getActivity(), "token", "");
        mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        mTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        getData(mPhoneNum, mTime, mToken);
    }


    private void getData(String phoneNum, String time, final String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);

        CallHistoryRequestBean.BODYBean callHistoryBody = new CallHistoryRequestBean.BODYBean("", "4", "1");

        CallHistoryRequestBean callHistoryRequestBean = new CallHistoryRequestBean("REQ", "MTALK", phoneNum, time,
                callHistoryBody, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(callHistoryRequestBean);
        Call<CallHistoryResultBean> callHistoryResult = allInterface.getCallHistoryResult(s);
        callHistoryResult.enqueue(new Callback<CallHistoryResultBean>() {
            @Override
            public void onResponse(final Call<CallHistoryResultBean> call, Response<CallHistoryResultBean> response) {
                if (response.body().getCODE().equals("-10006")) {
                    Log.i(TAG, "onResponse: message" + response.body().getCODE().equals("-10006"));
                } else {

                    //给recyclerview设置adapter数据,展示list
                    mResponseCallHist = response;
                    Log.i(TAG, "HistoryFragment:onResponse+response: " + response.body().toString() + ":");
                    mLst = (ArrayList<CallHistoryResultBean.BODYBean.LSTBean>) response.body()
                            .getBODY().getLST();

                    //展示数据
                    /*
                    * 这个adapter不用,换成不带删除功能的adapter

                    HistorySwipeAdapter historySwipeAdapter = new HistorySwipeAdapter(getContext(), mLst);
                    lv_fragment_history.setAdapter(historySwipeAdapter);
                    lv_fragment_history.addHeaderView(mItem_history_header);
                    lv_fragment_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ToastUtil.showToast(getContext(), "position" + position);
                    });
                    * */

                    HistoryAdapter historyAdapter = new HistoryAdapter(getContext(), mLst);
                    lv_fragment_history.setAdapter(historyAdapter);
//                    lv_fragment_history.addHeaderView(mItem_history_header);
                    lv_fragment_history.setDivider(null);
                    lv_fragment_history.setFooterDividersEnabled(false);
                    lv_fragment_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String toyid = mResponseCallHist.body().getBODY().getLST().get(position).getTOYID();
                            CallManager.CallToToy(toyid, getActivity());

                        }
                    });

                    //删除 不带侧滑的listview 模块

                }
//                else{
//                    Log.d("ddd", "response: 为空");
//                    return;
//                }


                Log.i("hhhhhh", "onResponse:+response " + response.body().toString());

            }

            @Override
            public void onFailure(Call<CallHistoryResultBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "失败了不能联网");
            }
        });
    }

    private void showData1() {
        List<CallHistoryResultBean.BODYBean.LSTBean> lst = mResponseCallHist.body().getBODY().getLST();
        for (int i = 0; i < lst.size() - 1; i++) {
            CallHistoryResultBean.BODYBean.LSTBean lstBean = lst.get(i);
            String toyid = lstBean.getTOYID();
            Log.i("hhhhhh", "showData: ++++" + toyid);
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_history_title:
                if (ToySelectorFragment.mToyId != null) {
                    CallManager.CallToToy(ToySelectorFragment.mToyId, getActivity());
//                                    ToastUtil.showToast(getContext(), "点击的是" + position);
                } else {
                    ToastUtil.showToast(getActivity(), "toyid为空,请选择玩具");
                }
                break;
            default:
                break;
        }
    }
}
