package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;

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
import com.tongyuan.android.zhiquleyuan.bean.QueryMyCollectionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPlayResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
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

public class PlayListActivity extends AppCompatActivity implements View.OnClickListener {

    private SwipeMenuListView mSwipelistview;
    private SwipeRefreshLayout sprefresh;
    private ImageView mBack;
    ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> mList = new ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        sprefresh = (SwipeRefreshLayout) findViewById(R.id.sprefresh);
        mSwipelistview = (SwipeMenuListView) findViewById(R.id.sp_activity_play_list);
        mBack = (ImageView) findViewById(R.id.baby_back);
    }

    private void initData() {
        getMyPlayList();
    }

    private void getMyPlayList() {
        final String token = SPUtils.getString(this, "TOKEN", "");
        final String phoneNum = SPUtils.getString(this, "phoneNum", "");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        final String time = simpleDateFormat.format(date);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryMyCollectionReqBean.BODYBean bodyBean = new QueryMyCollectionReqBean.BODYBean("","", "10", "1");
        QueryMyCollectionReqBean queryMyCollectionReqBean = new QueryMyCollectionReqBean("REQ", "MYPLAY", phoneNum, time, bodyBean, "", token, "1");

        Gson gson = new Gson();
        String babyListJson = gson.toJson(queryMyCollectionReqBean);
        Call<QueryMyPlayResBean> babyListResult = allInterface.QUERY_MYPLAY_RES_BEAN_CALL(babyListJson);

        babyListResult.enqueue(new Callback<QueryMyPlayResBean>() {

            private List<QueryMyPlayResBean.BODYBean.LSTBean> mLst;
            private PlayListAdapter mPlayListAdapter;

            @Override
            public void onResponse(Call<QueryMyPlayResBean> call, Response<QueryMyPlayResBean> response) {
                if (response.body() != null && response.body().getCODE().equals("0")) {
                    Log.i("555555", "queryRecordingList:response" + response.body().getBODY().toString());
                    //TODO 考虑一下,这时候,

                    mLst = response.body().getBODY().getLST();
                    for (QueryMyPlayResBean.BODYBean.LSTBean bean : mLst) {
                        DiscoveryListResultBean.BODYBean.LSTBean listBean = new DiscoveryListResultBean.BODYBean.LSTBean();
                        listBean.setID(bean.getID());
                        listBean.setIMG(bean.getIMG());
                        listBean.setNAME(bean.getNAME());
                        mList.add(listBean);
                    }
                    // 如果list为空怎么办??2017.6.14   傻呀!!做判断就行了!!!
                    mPlayListAdapter = new PlayListAdapter(getApplicationContext(), mLst);
                    mSwipelistview.setAdapter(mPlayListAdapter);
                    mSwipelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            MyPlayActivity.launch(getApplicationContext(), mList,position);
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
                            deleteItem.setTitleSize(16);

                            deleteItem.setTitleColor(R.color.white);
                            menu.addMenuItem(deleteItem);

                        }
                    };
                    mSwipelistview.setMenuCreator(mCreator);
                    mSwipelistview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                            deleteMyPlay(phoneNum, time, position, mLst, token);
                            mLst.remove(position);
                            mPlayListAdapter.notifyDataSetChanged();
                            ToastUtil.showToast(getApplicationContext(), "点击删除");
                            return false;
                        }
                    });
                    mSwipelistview.setSmoothScrollbarEnabled(true);
                    mSwipelistview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
                    mSwipelistview.setOpenInterpolator(new AccelerateInterpolator());
                    mSwipelistview.setCloseInterpolator(new AccelerateInterpolator());

                }
            }

            @Override
            public void onFailure(Call<QueryMyPlayResBean> call, Throwable t) {

                Log.i("555555", "querymycollection+List:failure" + t.toString());
            }
        });

    }

    private void deleteMyPlay(String phoneNum, String time, int position, List<QueryMyPlayResBean.BODYBean.LSTBean> qlst, String token) {
        String resId = qlst.get(position).getID();
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
        String babyListJson = gson.toJson(deleteMyPlayReqBean);
        Call<DeleteMyPlayResBean> babyListResult = allInterface.DELETE_MYPLAY_RES_BEAN_CALL(babyListJson);
        babyListResult.enqueue(new Callback<DeleteMyPlayResBean>() {
            @Override
            public void onResponse(Call<DeleteMyPlayResBean> call, Response<DeleteMyPlayResBean> response) {
                Log.i("555555", "recordingfragment+(deleteRecording)onResponse: " + response.body().getBODY().toString());

            }

            @Override
            public void onFailure(Call<DeleteMyPlayResBean> call, Throwable t) {
                Log.i("555555", "recordingfragment+(deleteRecording)onFailure: " + t.toString());

            }
        });
    }

    private void initListener() {
        mBack.setOnClickListener(this);
        sprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getMyPlayList();
                sprefresh.setRefreshing(false);
            }

        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baby_back:
                finish();
                break;
        }
    }
}
