package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.MyPushAdapter;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushReqBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.swipe.refreshlib.AbListView;
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
 * Created by android on 2016/12/23.
 */

public class MyPushActivity extends AppCompatActivity implements View.OnClickListener {

    private AbListView mLv_myPush;
    private ImageView mPush_back;
    private View mMyPushHeader;
    private List<QueryBabyListResult.BODYBean.LSTBean> mLst;
    private String mToyid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypush);
        initView();
        initDate();
        initListener();


    }
    private void initView() {

        mLv_myPush = (AbListView) findViewById(R.id.rv_mypush);
        mPush_back = (ImageView) findViewById(R.id.iv_push_back);
        mMyPushHeader = LayoutInflater.from(this).inflate(R.layout.item_push_header, null);
    }
    private void initDate() {
        mToyid = SPUtils.getString(this, "toyidtopush", "");
        Intent intent = getIntent();
        QueryBabyListResult babyinfo = intent.getParcelableExtra("babyinfo");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(new Date());
        String token = SPUtils.getString(this, "TOKEN", "");
        String phoneNum = SPUtils.getString(this, "phoneNum", "");


        getMyPushData(time, token, phoneNum);
    }
    private void initListener() {
        mPush_back.setOnClickListener(this);
    }



    private void getMyPushData(String time, String token, String phoneNum) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllInterface allInterface = retrofit.create(AllInterface.class);
        //TODO 我的页面下的推送是查看当前玩具的推送,还是该用户的所有推送???
        QueryMyPushReqBean.BODYBean bodyBean = new QueryMyPushReqBean.BODYBean("",mToyid, "", "10", "1");
        QueryMyPushReqBean queryMyPushReqBean = new QueryMyPushReqBean("REQ", "MYPUSH", phoneNum, time, bodyBean, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryMyPushReqBean);
        Call<QueryMyPushResBean> queryMyPushResult = allInterface.getQueryMyPushResult(s);
        queryMyPushResult.enqueue(new Callback<QueryMyPushResBean>() {
            @Override
            public void onResponse(Call<QueryMyPushResBean> call, Response<QueryMyPushResBean> response) {
                if (response.body().getCODE().equals("0")){
                    MyPushAdapter myPushAdapter = new MyPushAdapter(getApplicationContext(), response);

                    mLv_myPush.addHeaderView(mMyPushHeader);
                    mLv_myPush.setAdapter(myPushAdapter);
                }else {
                    ToastUtil.showToast(getApplicationContext(),response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<QueryMyPushResBean> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_push_back:
                finish();
                break;
        }
    }
}
