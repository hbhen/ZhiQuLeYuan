package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
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
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.MyPushAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPushReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPushResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.CallManager;
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

/*import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;*/


/**
 * Created by android on 2016/12/23.
 */

public class MyPushActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener {

    private final String TAG = MyPushActivity.class.getSimpleName();
    private SwipeMenuListView mLv_myPush;
    private LinearLayout mPush_back;
    private String mToyid;
    private SwipeRefreshLayout mSpRefresh;
    private String mToken;
    private String mPhoneNum;
    private RelativeLayout mMyPushHeader;
    private View footerView;
    private EditText mEditTextView;
    private String keyWord = "";
    private int currentPage = 1;
    private String NC = "-1";

    ArrayList<QueryMyPushResBean.BODYBean.LSTBean> myPushList = new ArrayList<>();
    ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> mList = new ArrayList<>();
    private MyPushAdapter mMyPushAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypush);
        initView();
        initDate();
        initListener();


    }


    private void initView() {
        mMyPushHeader = (RelativeLayout) findViewById(R.id.rl_mypush_header);
        mLv_myPush = (SwipeMenuListView) findViewById(R.id.rv_mypush);
        mPush_back = (LinearLayout) findViewById(R.id.iv_push_back);
        mSpRefresh = (SwipeRefreshLayout) findViewById(R.id.sprefresh);
        mEditTextView = (EditText) findViewById(R.id.et_mycollection_mypush);
        footerView = LayoutInflater.from(this).inflate(R.layout.discovery_sub_item_foot, null);
        footerView.setVisibility(View.GONE);
        mLv_myPush.addFooterView(footerView);

    }

    private void initDate() {
        mToyid = SPUtils.getString(this, "toyidtopush", "");
        Intent intent = getIntent();
        QueryBabyListResult babyinfo = intent.getParcelableExtra("babyinfo");

        mToken = SPUtils.getString(this, "token", "");
        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        mMyPushAdapter = new MyPushAdapter(getApplicationContext(), myPushList);
        mLv_myPush.setAdapter(mMyPushAdapter);
        getMyPushData("", mToken, mPhoneNum, false);
    }

    private void initListener() {
        mMyPushHeader.setOnClickListener(this);
        mPush_back.setOnClickListener(this);
        mSpRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                NC = "-1";
                getMyPushData("", mToken, mPhoneNum, false);
                mSpRefresh.setRefreshing(false);
            }
        });

        mEditTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MyPushActivity.this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    String text = mEditTextView.getText().toString().trim();

                    getMyPushData(text, mToken, mPhoneNum, false);
                }
                return false;
            }
        });
        mLv_myPush.setOnScrollListener(this);

    }


    private void getMyPushData(String keyWord, final String token, final String phoneNum, final boolean isLoadMore) {
        if (!NC.equals("0")) {

            int page = currentPage;
            if (isLoadMore) {
                page++;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String time = simpleDateFormat.format(new Date());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AllInterface allInterface = retrofit.create(AllInterface.class);
            //TODO 我的页面下的推送是查看当前玩具的推送,还是该用户的所有推送???
            QueryMyPushReqBean.BODYBean bodyBean = new QueryMyPushReqBean.BODYBean(keyWord, "10", String.valueOf(page), keyWord, mToyid);
            final QueryMyPushReqBean queryMyPushReqBean = new QueryMyPushReqBean("REQ", "MYPUSH", phoneNum, time, bodyBean, "", token, "1");
            Gson gson = new Gson();
            String s = gson.toJson(queryMyPushReqBean);
            Call<QueryMyPushResBean> queryMyPushResult = allInterface.getQueryMyPushResult(s);
            queryMyPushResult.enqueue(new Callback<QueryMyPushResBean>() {

                private List<QueryMyPushResBean.BODYBean.LSTBean> mLst;

                @Override
                public void onResponse(Call<QueryMyPushResBean> call, final Response<QueryMyPushResBean> response) {
                    if (response.body().getCODE().equals("0")) {
                        NC = response.body().getBODY().getNC();
                        if (isLoadMore) {
                            currentPage++;
                        } else {
                            myPushList.clear();
                            currentPage = 1;
                        }
                        mLst = response.body().getBODY().getLST();
                        myPushList.addAll(mLst);

                        if ("0".equals(response.body().getBODY().getNC())) {
                            footerView.setVisibility(View.GONE);
                        } else {
                            footerView.setVisibility(View.VISIBLE);
                        }
                        mMyPushAdapter.notifyDataSetChanged();
                        for (QueryMyPushResBean.BODYBean.LSTBean bean : mLst) {
                            DiscoveryListResultBean.BODYBean.LSTBean listBean = new DiscoveryListResultBean.BODYBean.LSTBean();
                            listBean.setID(bean.getID());
                            listBean.setIMG(bean.getIMG());
                            listBean.setNAME(bean.getNAME());
                            mList.add(listBean);
                        }

                        mLv_myPush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            ArrayList<QueryMyPushResBean.BODYBean.LSTBean> arrayList = new ArrayList<QueryMyPushResBean.BODYBean.LSTBean>();
//                            for (int i = 0; i < mLst.size(); i++) {
//
//                                arrayList.add(i, response.body().getBODY().getLST().get(i));
//
//                            }
//                            LogUtil.i("121212", "onItemClick: list" + arrayList.size() + "---" + position);
                                MyPlayActivity.launch(getApplicationContext(), mList, position);

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
                                deleteItem.setTitleColor(Color.WHITE);
                                menu.addMenuItem(deleteItem);

                            }
                        };
                        mLv_myPush.setMenuCreator(mCreator);
                        mLv_myPush.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                                deletePush(phoneNum, new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), position, myPushList, token);
                                myPushList.remove(position);
                                mMyPushAdapter.notifyDataSetChanged();
//                                ToastUtil.showToast(getApplicationContext(), "点击删除");
                                return false;
                            }
                        });

                        mLv_myPush.setSmoothScrollbarEnabled(true);
                        mLv_myPush.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
                        mLv_myPush.setOpenInterpolator(new AccelerateInterpolator());
                        mLv_myPush.setCloseInterpolator(new AccelerateInterpolator());
                    } else if (response.body().getCODE().equals("-10006")) {
                        SPUtils.putString(getApplicationContext(), "token", "");
                        ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                    }
                    isLoading = false;
                }

                @Override
                public void onFailure(Call<QueryMyPushResBean> call, Throwable t) {
                    ToastUtil.showToast(MyPushActivity.this, "网络连接异常,请检查网络");
                    isLoading = false;
                    LogUtil.i(TAG, t.toString());
                }
            });
        }
    }

    private void deletePush(String phoneNum, String time, int position, List<QueryMyPushResBean.BODYBean.LSTBean> list, String
            token) {
//        String resId = list.get(position).getID();
        String resId = list.get(position).getRCDID();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        DeleteMyPushReqBean.BODYBean.LSTBean lstBean = new DeleteMyPushReqBean.BODYBean.LSTBean(resId);
        List<DeleteMyPushReqBean.BODYBean.LSTBean> lst = new ArrayList<>();
        lst.add(lstBean);
        DeleteMyPushReqBean.BODYBean bodyBean = new DeleteMyPushReqBean.BODYBean(lst);
        DeleteMyPushReqBean deleteMyMypushReqBean = new DeleteMyPushReqBean("REQ", "DMYPUSH", phoneNum, time, bodyBean, "", token,
                "1");
        Gson gson = new Gson();
        String babyListJson = gson.toJson(deleteMyMypushReqBean);
        Call<DeleteMyPushResBean> babyListResult = allInterface.DELETE_MY_PUSH_RES_BEAN_CALL(babyListJson);
        babyListResult.enqueue(new Callback<DeleteMyPushResBean>() {
            @Override
            public void onResponse(Call<DeleteMyPushResBean> call, Response<DeleteMyPushResBean> response) {
                footerView.setVisibility(View.GONE);
                mMyPushAdapter.notifyDataSetChanged();
//                ToastUtil.showToast(getApplicationContext(), "走没走");
                LogUtil.i("555555", "recordingfragment+(deleteRecording)onResponse: " + response.body().getBODY().toString());
            }

            @Override
            public void onFailure(Call<DeleteMyPushResBean> call, Throwable t) {
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
            case R.id.iv_push_back:
                finish();
                break;
            case R.id.rl_mypush_header:
                if (ToySelectorFragment.mToyId != null) {
                    CallManager.CallToToy(ToySelectorFragment.mToyId, this);
                } else {
                    ToastUtil.showToast(this, "请去玩具页面选择玩具");
                }
                break;
            default:
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
            getMyPushData("", mToken, mPhoneNum, true);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }
}
