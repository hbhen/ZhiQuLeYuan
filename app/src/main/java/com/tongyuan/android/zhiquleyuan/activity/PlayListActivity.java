package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.PlayListAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPlayReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPlayResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPlayReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPlayResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
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

public class PlayListActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener {
    private static final String TAG = "responsetag";
    private SwipeMenuListView mSwipelistview;
    private SwipeRefreshLayout sprefresh;
    private LinearLayout mBack;
    ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> mList = new ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>();
    ArrayList<QueryMyPlayResBean.BODYBean.LSTBean> queryMusicList = new ArrayList<QueryMyPlayResBean.BODYBean.LSTBean>();
    private int currentPage = 1;
    private String NC = "-1";
    private View footerView;
    private ArrayList<QueryMyPlayResBean.BODYBean.LSTBean> mLst;
    private PlayListAdapter mPlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initView() {
        sprefresh = (SwipeRefreshLayout) findViewById(R.id.sprefresh);
        mSwipelistview = (SwipeMenuListView) findViewById(R.id.sp_activity_play_list);
        mBack = (LinearLayout) findViewById(R.id.iv_playlist_back);
        footerView = LayoutInflater.from(this).inflate(R.layout.discovery_sub_item_foot, null);
        footerView.setVisibility(View.GONE);
        mSwipelistview.addFooterView(footerView);
    }

    private void initData() {
        mPlayListAdapter = new PlayListAdapter(getApplicationContext(), queryMusicList);
        mSwipelistview.setAdapter(mPlayListAdapter);
        getMyPlayList(false);
    }

    private void getMyPlayList(final boolean isLoadMore) {
        if (!NC.equals("0")) {
            int page = currentPage;
            if (isLoadMore) {
                page++;
            }
            final String token = SPUtils.getString(this, "token", "");
            final String phoneNum = SPUtils.getString(this, "phoneNum", "");
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            final String time = simpleDateFormat.format(date);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AllInterface allInterface = retrofit.create(AllInterface.class);
            QueryMyPlayReqBean.BODYBean bodyBean = new QueryMyPlayReqBean.BODYBean("", "10", String.valueOf(page));
            QueryMyPlayReqBean queryMyCollectionReqBean = new QueryMyPlayReqBean("REQ", "MYPLAY", phoneNum, time, bodyBean, "", token, "1");
            Gson gson = new Gson();
            String babyListJson = gson.toJson(queryMyCollectionReqBean);
            Call<QueryMyPlayResBean> babyListResult = allInterface.QUERY_MYPLAY_RES_BEAN_CALL(babyListJson);

            babyListResult.enqueue(new Callback<QueryMyPlayResBean>() {

                @Override
                public void onResponse(Call<QueryMyPlayResBean> call, Response<QueryMyPlayResBean> response) {
                    int size = response.body().getBODY().getLST().size();
                    LogUtil.i(TAG, "onResponse: size" + size);
                    if (response.body() != null && response.body().getCODE().equals("0")) {
                        NC = response.body().getBODY().getNC();
                        LogUtil.d(TAG, "onResponse: <QueryMyPlayResBean>" + response.body().getBODY().toString());
                        LogUtil.i("555555", "queryRecordingList:response" + response.body().getBODY().toString());
                        if (isLoadMore) {
                            currentPage++;
                        } else {
                            queryMusicList.clear();
                            currentPage = 1;
                        }
                        mLst = (ArrayList<QueryMyPlayResBean.BODYBean.LSTBean>) response.body().getBODY().getLST();
                        LogUtil.d(TAG, "onResponse: mlst" + mLst.size());
                        queryMusicList.addAll(mLst);

                        LogUtil.d(TAG, "onResponse: querymusic:" + queryMusicList.size());
                        if ("0".equals(response.body().getBODY().getNC())) {
                            footerView.setVisibility(View.GONE);
                        } else {
                            footerView.setVisibility(View.VISIBLE);
                        }
                        mPlayListAdapter.notifyDataSetChanged();
                        for (QueryMyPlayResBean.BODYBean.LSTBean bean : queryMusicList) {
                            DiscoveryListResultBean.BODYBean.LSTBean listBean = new DiscoveryListResultBean.BODYBean.LSTBean();
                            listBean.setID(bean.getID());
                            listBean.setIMG(bean.getIMG());
                            listBean.setNAME(bean.getNAME());
                            mList.add(listBean);
                        }

                        mSwipelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                MyPlayActivity.launch(getApplicationContext(), mList, position);
                                ToastUtil.showToast(getApplicationContext(), "点击的是:" + position);
                            }
                        });
                        SwipeMenuCreator mCreator = new SwipeMenuCreator() {
                            @Override
                            public void create(SwipeMenu menu) {
                                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                                deleteItem.setTitle("删除");
                                deleteItem.setBackground(R.color.redFont);
                                deleteItem.setWidth(dp2px(70));
                                deleteItem.setTitleSize(18);
                                deleteItem.setTitleColor(R.color.white);
                                menu.addMenuItem(deleteItem);
                            }
                        };
                        mSwipelistview.setMenuCreator(mCreator);
                        mSwipelistview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                                queryMusicList.remove(position);
                                deleteMyPlay(phoneNum, time, position, queryMusicList, token);
//                            mPlayListAdapter.notifyDataSetChanged();
                                ToastUtil.showToast(getApplicationContext(), "点击删除");
                                return false;
                            }
                        });
                        mSwipelistview.setSmoothScrollbarEnabled(true);
                        mSwipelistview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
                        mSwipelistview.setOpenInterpolator(new AccelerateInterpolator());
                        mSwipelistview.setCloseInterpolator(new AccelerateInterpolator());
                    } else {
                        ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                        footerView.setVisibility(View.GONE);
                    }
                    isLoading = false;

                }

                @Override
                public void onFailure(Call<QueryMyPlayResBean> call, Throwable t) {
                    isLoading = false;
                    LogUtil.i("555555", "querymycollection+List:failure" + t.toString());
                }
            });
        }
    }

    private void deleteMyPlay(String phoneNum, String time, int position, List<QueryMyPlayResBean.BODYBean.LSTBean> qlst, String token) {
//        String resId = qlst.get(position).getID();
        String resId = qlst.get(position).getRCDID();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        List<DeleteMyPlayReqBean.BODYBean.LSTBean> lst = new ArrayList<>();
        DeleteMyPlayReqBean.BODYBean.LSTBean lstBean = new DeleteMyPlayReqBean.BODYBean.LSTBean(resId);
        lst.add(lstBean);
        DeleteMyPlayReqBean.BODYBean bodyBean = new DeleteMyPlayReqBean.BODYBean(lst);
        DeleteMyPlayReqBean deleteMyPlayReqBean = new DeleteMyPlayReqBean("REQ", "DMYPLAY", phoneNum, time, bodyBean, "", token,
                "1");
        Gson gson = new Gson();
        String deletePlay = gson.toJson(deleteMyPlayReqBean);
        Call<DeleteMyPlayResBean> playListResult = allInterface.DELETE_MYPLAY_RES_BEAN_CALL(deletePlay);
        playListResult.enqueue(new Callback<DeleteMyPlayResBean>() {
            @Override
            public void onResponse(Call<DeleteMyPlayResBean> call, Response<DeleteMyPlayResBean> response) {
                footerView.setVisibility(View.GONE);
                mPlayListAdapter.notifyDataSetChanged();
                ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                LogUtil.i("555555", "recordingfragment+(deleteRecording)onResponse: " + response.body().getBODY().toString());

            }

            @Override
            public void onFailure(Call<DeleteMyPlayResBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), t.toString());

                LogUtil.i("555555", "recordingfragment+(deleteRecording)onFailure: " + t.toString());

            }
        });
    }

    private void initListener() {
        mBack.setOnClickListener(this);
        sprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                currentPage = 1;
                NC = "-1";
                getMyPlayList(false);
                sprefresh.setRefreshing(false);
            }

        });
        mSwipelistview.setOnScrollListener(this);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_playlist_back:
                finish();
                break;
        }
    }

    private int totalItemCount;
    private int lastItem;
    private boolean isLoading = false;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!isLoading && lastItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
            //显示加载更多
            isLoading = true;
            getMyPlayList(true);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

}
