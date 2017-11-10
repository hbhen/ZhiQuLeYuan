package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.MyPushAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPushReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPushResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
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
 * Created by android on 2016/12/23.
 */

public class MyPushActivity extends AppCompatActivity implements View.OnClickListener {

    private SwipeMenuListView mLv_myPush;
    private ImageView mPush_back;
    private List<QueryBabyListResult.BODYBean.LSTBean> mLst;
    private String mToyid;
    private SwipeRefreshLayout mSpRefresh;
    private String mToken;
    private String mPhoneNum;
    private RelativeLayout mMyPushHeader;


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
        mPush_back = (ImageView) findViewById(R.id.iv_push_back);
        mSpRefresh = (SwipeRefreshLayout) findViewById(R.id.sprefresh);

    }

    private void initDate() {
        mToyid = SPUtils.getString(this, "toyidtopush", "");
        Intent intent = getIntent();
        QueryBabyListResult babyinfo = intent.getParcelableExtra("babyinfo");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(new Date());
        mToken = SPUtils.getString(this, "token", "");
        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        getMyPushData(time, mToken, mPhoneNum);
    }

    private void initListener() {
        mMyPushHeader.setOnClickListener(this);
        mPush_back.setOnClickListener(this);
        mSpRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyPushData(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), mToken, mPhoneNum);
                mSpRefresh.setRefreshing(false);
            }
        });
    }


    private void getMyPushData(String time, final String token, final String phoneNum) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllInterface allInterface = retrofit.create(AllInterface.class);
        //TODO 我的页面下的推送是查看当前玩具的推送,还是该用户的所有推送???
        QueryMyPushReqBean.BODYBean bodyBean = new QueryMyPushReqBean.BODYBean("", "10", "1", "", mToyid);
        QueryMyPushReqBean queryMyPushReqBean = new QueryMyPushReqBean("REQ", "MYPUSH", phoneNum, time, bodyBean, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryMyPushReqBean);
        Call<QueryMyPushResBean> queryMyPushResult = allInterface.getQueryMyPushResult(s);
        queryMyPushResult.enqueue(new Callback<QueryMyPushResBean>() {

            private List<QueryMyPushResBean.BODYBean.LSTBean> mLst;

            @Override
            public void onResponse(Call<QueryMyPushResBean> call, final Response<QueryMyPushResBean> response) {
                if (response.body().getCODE().equals("0")) {
                    final MyPushAdapter myPushAdapter = new MyPushAdapter(getApplicationContext(), response);
                    mLst = response.body().getBODY().getLST();

                    mLv_myPush.setAdapter(myPushAdapter);

                    mLv_myPush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ArrayList<QueryMyPushResBean.BODYBean.LSTBean> arrayList = new ArrayList<QueryMyPushResBean.BODYBean.LSTBean>();
                            for (int i = 0; i < mLst.size(); i++) {

                                arrayList.add(i, response.body().getBODY().getLST().get(i));

                            }
                            Log.i("121212", "onItemClick: list" + arrayList.size() + "---" + position);
                            MyPlayActivity.launch(getApplicationContext(), arrayList, position);

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
                    mLv_myPush.setMenuCreator(mCreator);
                    mLv_myPush.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                            deletePush(phoneNum, new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), position, mLst, token);
                            mLst.remove(position);
                            myPushAdapter.notifyDataSetChanged();

                            ToastUtil.showToast(getApplicationContext(), "点击删除");

                            return false;

                        }
                    });

                    mLv_myPush.setSmoothScrollbarEnabled(true);
                    mLv_myPush.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
                    mLv_myPush.setOpenInterpolator(new AccelerateInterpolator());
                    mLv_myPush.setCloseInterpolator(new AccelerateInterpolator());
                } else {
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<QueryMyPushResBean> call, Throwable t) {

            }
        });
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
                ToastUtil.showToast(getApplicationContext(),"走没走");
                Log.i("555555", "recordingfragment+(deleteRecording)onResponse: " + response.body().getBODY().toString());
            }

            @Override
            public void onFailure(Call<DeleteMyPushResBean> call, Throwable t) {
                Log.i("555555", "recordingfragment+(deleteRecording)onFailure: " + t.toString());
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
                    ToastUtil.showToast(this, "toyid为空,请选择玩具");
                }
                break;
            default:
                break;
        }
    }
}
