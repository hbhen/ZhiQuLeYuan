package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.BabyInfoListAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DeleteBabyInfoReQBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteBabyInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListRequest;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.MySwipeRefreshLayout;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenu;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenuCreator;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenuItem;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenuListView;

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
 * Created by android on 2017/3/3.
 */
public class BabyInfoListActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener {

    private static final int ADD_BABYINFO = 502;
    public static final int SuccessCode = 66;
    private SwipeMenuListView mSwipeMenuListView;
    private String mToken;
    private RelativeLayout mIv_babayinfolist_addbabyinfo;
    private MySwipeRefreshLayout sp;
    private static final String TAG = "88888";
    private LinearLayout iv_back;
    private int currentPage = 1;
    private String NC = "-1";
    private View footerView;
    List<QueryBabyListResult.BODYBean.LSTBean> babyInfoList = new ArrayList<>();
    private String mPhone;
    private BabyInfoListAdapter mBabyInfoListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.babyinfolist);
        initView();
        initData();
        initListener();
        LogUtil.i(TAG, "babyinfolistactivity : onCreatea went");
    }

    private void initView() {
        mSwipeMenuListView = (SwipeMenuListView) findViewById(R.id.lv_babyinfolist);
        iv_back = (LinearLayout) findViewById(R.id.iv_babyinfolist_backa);
        sp = (MySwipeRefreshLayout) findViewById(R.id.sp);
        mIv_babayinfolist_addbabyinfo = (RelativeLayout) findViewById(R.id.iv_babayinfolist_addbabyinfo);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initSwipeMenuListViewContent();
        footerView = LayoutInflater.from(this).inflate(R.layout.discovery_sub_item_foot, null);
        footerView.setVisibility(View.GONE);
        mSwipeMenuListView.addFooterView(footerView);
    }

    private void initSwipeMenuListViewContent() {
        SwipeMenuCreator mCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setTitle("删除");
                deleteItem.setBackground(R.color.redFont);
                deleteItem.setWidth(dp2px(70));
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        mSwipeMenuListView.setMenuCreator(mCreator);
        mSwipeMenuListView.setSmoothScrollbarEnabled(true);
        mSwipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        mSwipeMenuListView.setOpenInterpolator(new AccelerateInterpolator());
        mSwipeMenuListView.setCloseInterpolator(new AccelerateInterpolator());

    }

    private void initListener() {
        mIv_babayinfolist_addbabyinfo.setOnClickListener(this);
        sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                NC = "-1";
                initData();
                sp.setRefreshing(false);
            }
        });
        mSwipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("fromtype", "contains");
                String birthday = babyInfoList.get(position).getBIRTHDAY();
                String img = babyInfoList.get(position).getIMG();
                String name = babyInfoList.get(position).getNAME();
                String sex = babyInfoList.get(position).getSEX();
                String nick = babyInfoList.get(position).getNICK();
                intent.putExtra("birthday", birthday);
                intent.putExtra("img", img);
                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("nick", nick);
                intent.setClass(BabyInfoListActivity.this, MyBabyActivity.class);
                BabyInfoListActivity.this.startActivity(intent);
//                ToastUtil.showToast(BabyInfoListActivity.this, "点击的条目是:" + position);
            }
        });
        mSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                deleteCollection(mPhone, position, babyInfoList, mToken);
                babyInfoList.remove(position);
                mBabyInfoListAdapter.notifyDataSetChanged();
//                ToastUtil.showToast(getApplicationContext(), "点击了删除");
                return false;
            }
        });
    }

    private void deleteCollection(String phone, int position, List<QueryBabyListResult.BODYBean.LSTBean> babyInfoList, String token) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        final String time = simpleDateFormat.format(date);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllInterface allInterface = retrofit.create(AllInterface.class);

        DeleteBabyInfoReQBean.BODYBean bodyBean = new DeleteBabyInfoReQBean.BODYBean("BABY", babyInfoList.get(position).getID());

        DeleteBabyInfoReQBean babyInfoRequestBean = new DeleteBabyInfoReQBean("REQ", "DINFO", phone, time, bodyBean, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(babyInfoRequestBean);
        Call<DeleteBabyInfoReSBean> deleteBabyInfoReSBeanCall = allInterface.delteBabyInfoReult(s);

        deleteBabyInfoReSBeanCall.enqueue(new Callback<DeleteBabyInfoReSBean>() {
            @Override
            public void onResponse(Call<DeleteBabyInfoReSBean> call, Response<DeleteBabyInfoReSBean> response) {
                if (response.body().getCODE().equals("0")) {
                    footerView.setVisibility(View.GONE);
                    mBabyInfoListAdapter.notifyDataSetChanged();
                    ToastUtil.showToast(BabyInfoListActivity.this, "删除成功");
                    LogUtil.i("555555", "recordingfragment+(deleteRecording)onResponse: " + response.body().getBODY().toString());
                } else {
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                }

            }

            @Override
            public void onFailure(Call<DeleteBabyInfoReSBean> call, Throwable t) {
                ToastUtil.showToast(BabyInfoListActivity.this, "网络异常");
                LogUtil.i("555555", "recordingfragment+(deleteRecording)onFailure: " + t.toString());
            }
        });


    }

    private void initData() {


        getBabyList(false);
    }

    private void getBabyList(final boolean isLoadMore) {
        int page = currentPage;
        if (isLoadMore) {
            page++;
        }
        if (!NC.equals("0")) {
            mToken = SPUtils.getString(this, "token", "");
            mPhone = SPUtils.getString(this, "phoneNum", "");
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            final String time = simpleDateFormat.format(date);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AllInterface allInterface = retrofit.create(AllInterface.class);
            //3.4.44
            QueryBabyListRequest.BODYBean queryBabyListBody = new QueryBabyListRequest.BODYBean("10", String.valueOf(page));
            QueryBabyListRequest queryBabyListRequest = new QueryBabyListRequest("REQ", "QMYB", mPhone, time, queryBabyListBody, "", mToken, "1");
            Gson gson = new Gson();
            String babyListJson = gson.toJson(queryBabyListRequest);
            Call<QueryBabyListResult> babyListResult = allInterface.getBabyListResult(babyListJson);
            babyListResult.enqueue(new Callback<QueryBabyListResult>() {
                @Override
                public void onResponse(Call<QueryBabyListResult> call, Response<QueryBabyListResult> response) {

                    if (mToken.equals("")) {
                        ToastUtil.showToast(getApplicationContext(), "当前未登录,请登录后操作");
                    } else {
                        if (response.body().getBODY() != null && response.body().getCODE().equals("0")) {
                            List<QueryBabyListResult.BODYBean.LSTBean> lst = response.body().getBODY().getLST();
                            String listResponse = response.body().toString();
                            NC = response.body().getBODY().getNC();
                            LogUtil.i("111", "listResponse: " + listResponse);
                            if (isLoadMore) {
                                currentPage++;
                            } else {
                                babyInfoList.clear();
                                currentPage = 1;
                            }
                            babyInfoList.addAll(lst);
                            mBabyInfoListAdapter = new BabyInfoListAdapter(getApplicationContext(), babyInfoList);
                            mSwipeMenuListView.setAdapter(mBabyInfoListAdapter);
//                            mBabyInfoListAdapter.notifyDataSetChanged();
                            if ("0".equals(NC)) {
                                footerView.setVisibility(View.GONE);
                            } else {
                                footerView.setVisibility(View.VISIBLE);
                            }
                        } else if (response.body().getCODE().equals("-10006")) {
                            ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                            SPUtils.putString(getApplicationContext(), "token", "");
                        } else {
                            ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                            footerView.setVisibility(View.GONE);
                        }
                        isLoading = false;
                    }
                }

                @Override
                public void onFailure(Call<QueryBabyListResult> call, Throwable t) {
                    ToastUtil.showToast(getApplicationContext(), "网络异常,请检查网络");
                    isLoading = false;
                    LogUtil.i(TAG, "querybabyinfolist:failure" + t.toString());
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_babayinfolist_addbabyinfo:
                Intent mIntent = new Intent();
                mIntent.putExtra("fromtype", "notcontains");
                mIntent.setClass(this, MyBabyActivity.class);
                startActivityForResult(mIntent, ADD_BABYINFO);
                break;
            case R.id.iv_babyinfolist_backa:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SuccessCode) {
            initData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        LogUtil.i(TAG, "babyinfolistactivity : onResume went");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG, "babyinfolistactivity : onStop went");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG, "babyinfolistactivity : onStart went");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG, "babyinfolistactivity : onPause went");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "babyinfolistactivity : onDestroy went");
    }

    private int totalItemCount;
    private int lastItem;
    private boolean isLoading = false;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!isLoading && lastItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
            //显示加载更多
            isLoading = true;
            getBabyList(true);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;//可见的item的数量,
        this.totalItemCount = totalItemCount;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
