package com.tongyuan.android.zhiquleyuan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.MyCollectionAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyCollectionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyCollectionResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyCollectionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyCollectionResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
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
 * Created by android on 2016/12/23.
 */

public class MyCollectionActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener {

    private ListView lv_mycollection;
    private SwipeRefreshLayout mSpRefresh;
    private SwipeMenuListView mSwipeListview;
    private LinearLayout mCollection_back;
    private EditText mEditText;
    private View footerView;
    private int currentPage = 1;
    private String NC = "-1";
    private String keyWord = "";
    ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> mList = new ArrayList<>();
    List<QueryMyCollectionResBean.BODYBean.LSTBean> collectionList = new ArrayList<>();
    private MyCollectionAdapter mMyCollectionAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);

        initView();
        initData();
        initListener();

    }

    private void initView() {
        mSpRefresh = (SwipeRefreshLayout) findViewById(R.id.sprefresh);
        mSwipeListview = (SwipeMenuListView) findViewById(R.id.lv_mycollection);
        lv_mycollection = (ListView) findViewById(R.id.lv_mycollection);
        mCollection_back = (LinearLayout) findViewById(R.id.iv_collection_back);
        mEditText = (EditText) findViewById(R.id.et_activity_mycollection);
        footerView = LayoutInflater.from(this).inflate(R.layout.discovery_sub_item_foot, null);
        footerView.setVisibility(View.GONE);
        mSwipeListview.addFooterView(footerView);
        initSwipeMenuListViewContent();
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
        mSwipeListview.setMenuCreator(mCreator);
        mSwipeListview.setSmoothScrollbarEnabled(true);
        mSwipeListview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        mSwipeListview.setOpenInterpolator(new AccelerateInterpolator());
        mSwipeListview.setCloseInterpolator(new AccelerateInterpolator());
    }

    private void initData() {
//        getMyCollection("");
        mMyCollectionAdapter = new MyCollectionAdapter(getApplicationContext(), collectionList);
        mSwipeListview.setAdapter(mMyCollectionAdapter);
        getMyCollection("", false);
    }

    private void initListener() {
        mSwipeListview.setOnScrollListener(this);
        mCollection_back.setOnClickListener(this);
        mSpRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                NC = "-1";
//                getMyCollection("");
                keyWord = "";
                getMyCollection("", false);
                mSpRefresh.setRefreshing(false);
            }
        });
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MyCollectionActivity.this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    String text = mEditText.getText().toString().trim();
                    getMyCollection(keyWord, false);
                }
                return false;
            }
        });
    }

    private void getMyCollection(String keyWord, final boolean isLoadMore) {
        int page = currentPage;
        if (isLoadMore) {
            page++;
        }
        if (!NC.equals("0")) {
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
            QueryMyCollectionReqBean.BODYBean bodyBean = new QueryMyCollectionReqBean.BODYBean(keyWord, "", "10", String.valueOf(page));
            QueryMyCollectionReqBean queryMyCollectionReqBean = new QueryMyCollectionReqBean("REQ", "MYFAV", phoneNum, time, bodyBean, "", token,
                    "1");
            Gson gson = new Gson();
            String babyListJson = gson.toJson(queryMyCollectionReqBean);
            Call<QueryMyCollectionResBean> babyListResult = allInterface.QUERY_MYCOLLECTION_RES_BEAN_CALL(babyListJson);
            babyListResult.enqueue(new Callback<QueryMyCollectionResBean>() {
                @Override
                public void onResponse(Call<QueryMyCollectionResBean> call, final Response<QueryMyCollectionResBean> response) {
                    if (response.body() != null && response.body().getCODE().equals("0")) {
                        NC = response.body().getBODY().getNC();
                        LogUtil.i("555555", "queryRecordingList:response" + response.body().getBODY().toString());
                        List<QueryMyCollectionResBean.BODYBean.LSTBean> lst = response.body().getBODY().getLST();
                        if (isLoadMore) {
                            currentPage++;
                        } else {
                            collectionList.clear();
                            currentPage = 1;
                        }
                        collectionList.addAll(lst);
                        if ("0".equals(response.body().getBODY().getNC())) {
                            footerView.setVisibility(View.GONE);
                        } else {
                            footerView.setVisibility(View.VISIBLE);
                        }
                        mMyCollectionAdapter.notifyDataSetChanged();
//                    mSwipeListview.setAdapter(mMyCollectionAdapter);
                        //把list封装,传到MyPlay去播放
                        for (QueryMyCollectionResBean.BODYBean.LSTBean lstBean : collectionList) {
                            DiscoveryListResultBean.BODYBean.LSTBean listBean = new DiscoveryListResultBean.BODYBean.LSTBean();
                            listBean.setID(lstBean.getID());
                            listBean.setIMG(lstBean.getIMG());
                            listBean.setNAME(lstBean.getNAME());
                            mList.add(listBean);
                        }
                        mSwipeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                MyPlayActivity.launch(getApplicationContext(), mList, position);
//                                ToastUtil.showToast(getApplicationContext(), "点击的是:" + position);
                            }
                        });

                        mSwipeListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                                deleteCollection(phoneNum, time, position, collectionList, token);
                                collectionList.remove(position);
                                mMyCollectionAdapter.notifyDataSetChanged();
//                                ToastUtil.showToast(getApplicationContext(), "点击删除");
                                return false;
                            }
                        });

                    } else if (response.body().getCODE().equals("-10006")) {
                        SPUtils.putString(getApplicationContext(), "token", "");
                        ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                    } else {
                        ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                        footerView.setVisibility(View.GONE);
                    }
                    isLoading = false;
                }

                @Override
                public void onFailure(Call<QueryMyCollectionResBean> call, Throwable t) {
                    ToastUtil.showToast(getApplicationContext(), "网络异常,请检查网络");
                    isLoading = false;
                    LogUtil.i("555555", "querymycollection+List:failure" + t.toString());

                }

            });
        }
    }

    private void deleteCollection(String phoneNum, String time, int position, List<QueryMyCollectionResBean.BODYBean.LSTBean> qlst, String token) {

        String resId = qlst.get(position).getID();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        DeleteMyCollectionReqBean.BODYBean.LSTBean lstBean = new DeleteMyCollectionReqBean.BODYBean.LSTBean(resId);
        List<DeleteMyCollectionReqBean.BODYBean.LSTBean> lst = new ArrayList<>();
        lst.add(lstBean);
        DeleteMyCollectionReqBean.BODYBean bodyBean = new DeleteMyCollectionReqBean.BODYBean(lst);
        DeleteMyCollectionReqBean deleteMyCollectionReqBean = new DeleteMyCollectionReqBean("REQ", "DFAVRES", phoneNum, time, bodyBean, "", token,
                "1");
        Gson gson = new Gson();
        String babyListJson = gson.toJson(deleteMyCollectionReqBean);
        Call<DeleteMyCollectionResBean> babyListResult = allInterface.DELETE_MYCOLLECTION_RES_BEAN_CALL(babyListJson);
        babyListResult.enqueue(new Callback<DeleteMyCollectionResBean>() {
            @Override
            public void onResponse(Call<DeleteMyCollectionResBean> call, Response<DeleteMyCollectionResBean> response) {
                footerView.setVisibility(View.GONE);
                mMyCollectionAdapter.notifyDataSetChanged();
                LogUtil.i("555555", "recordingfragment+(deleteRecording)onResponse: " + response.body().getBODY().toString());

            }

            @Override
            public void onFailure(Call<DeleteMyCollectionResBean> call, Throwable t) {
                LogUtil.i("555555", "recordingfragment+(deleteRecording)onFailure: " + t.toString());
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
            case R.id.iv_collection_back:
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
            getMyCollection("", true);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;//可见的item的数量,
        this.totalItemCount = totalItemCount;
    }
}
