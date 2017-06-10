package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.BabyInfoListAdapter;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListRequest;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.fragment.MineFragment;
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
 * Created by android on 2017/3/3.
 */
public class BabyInfoListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ADD_BABYINFO = 502;
    private ListView mListview;
    private String mToken;
    private ImageView mIv_babayinfolist_addbabyinfo;
    private ImageView mIv_babyinfolist_backa;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.babyinfolist);
        initView();

        initData();
        initListener();
    }

    private void initListener() {
        mIv_babayinfolist_addbabyinfo.setOnClickListener(this);
    }

    private void initView() {
        mListview = (ListView) findViewById(R.id.lv_babyinfolist);
        mIv_babayinfolist_addbabyinfo = (ImageView) findViewById(R.id.iv_babayinfolist_addbabyinfo);
        mIv_babyinfolist_backa = (ImageView) findViewById(R.id.iv_babyinfolist_backa);
    }

    private void initData() {

        mToken = SPUtils.getString(this, "TOKEN", "");
        final String phone = SPUtils.getString(this, "phoneNum", "");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        final String time = simpleDateFormat.format(date);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);

        QueryBabyListRequest.BODYBean queryBabyListBody = new QueryBabyListRequest.BODYBean("10", "1");
        QueryBabyListRequest queryBabyListRequest = new QueryBabyListRequest("REQ", "QMYB", phone, time, queryBabyListBody, "", mToken, "1");

        Gson gson = new Gson();
        String babyListJson = gson.toJson(queryBabyListRequest);
        Call<QueryBabyListResult> babyListResult = allInterface.getBabyListResult(babyListJson);
        babyListResult.enqueue(new Callback<QueryBabyListResult>() {
            @Override
            public void onResponse(Call<QueryBabyListResult> call, Response<QueryBabyListResult> response) {
                String listResponse = response.body().toString();

                Log.i("111", "listResponse: " + listResponse);
                List<QueryBabyListResult.BODYBean.LSTBean> lst = response.body().getBODY().getLST();

                QueryBabyListResult.BODYBean.LSTBean lstBean = null;
                if (mToken.equals("")) {
                    ToastUtil.showToast(getApplicationContext(), "当前未登录,请登录后操作");
                } else {
//                    ToastUtil.showToast(getApplicationContext(), "chenggong" + response.body().getBODY().toString());

//                    showBabyList(lst);
                    showBabyList(response,time,phone,mToken,lst);
                }

            }

            @Override
            public void onFailure(Call<QueryBabyListResult> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "网络异常");
                t.printStackTrace();
            }
        });
    }

    private void showBabyList(Response<QueryBabyListResult> response, String time, String phone, String token, List<QueryBabyListResult.BODYBean
            .LSTBean> lst) {
        mListview.setAdapter((new BabyInfoListAdapter(this,response,time,phone,token,lst)));
    }

//    private void showBabyList(List<QueryBabyListResult.BODYBean.LSTBean> lst) {
//        mListview.setAdapter(new BabyInfoListAdapter(this, lst));
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_babayinfolist_addbabyinfo:
                Intent mIntent = new Intent();
                mIntent.setClass(this,MyBabyActivity.class);
                startActivityForResult(mIntent,ADD_BABYINFO);
                break;
            case R.id.iv_babyinfolist_backa:
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                transaction.replace(R.id.fl_fragmentcontainer,new MineFragment());
                transaction.commit();
                break;
        }
    }

    public static final int SuccessCode = 66;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == SuccessCode) {
            initData();
        }
    }
}
