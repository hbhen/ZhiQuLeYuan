package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.BindBabyAdapter;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.BindBabyToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.BindBabyToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListRequest;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
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
 * Created by Android on 2017/5/15.
 */

public class BindBabyActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListview_bindbaby;
    private Button mButton_confirm;
    private ImageView mBack_bindbaby;
    private String mPhoneNum;
    private String mToken;
    private String mTime;
    private String mToyCode;
    private String mToyId;
    private Boolean isChecked = false;
    private ImageButton mCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindbaby);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mListview_bindbaby = (ListView) findViewById(R.id.lv_bindbaby);
        mButton_confirm = (Button) findViewById(R.id.bt_bindbaby);
        mBack_bindbaby = (ImageView) findViewById(R.id.iv_bindbaby_backa);
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_bindbaby, null);
        mCheck = (ImageButton) inflate.findViewById(R.id.iv_item_bindbaby_checked);
    }

    private void initListener() {
        mBack_bindbaby.setOnClickListener(this);
        mButton_confirm.setOnClickListener(this);
        mCheck.setOnClickListener(this);
//        mListview_bindbaby.setOnClickListener(this);
    }

    private void initData() {
        //从ToyAddFragment穿过来的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Log.i("bindbabyactivity", "initData: +bundle" + bundle);
        Log.i("bindbabyactivity", "initData: +bundle" + bundle.getParcelable("addtoyresultbean"));

        AddToyResultBean addtoyresultbean = bundle.getParcelable("addtoyresultbean");//3.4.11接口
        AddToyResultBean.BODYBean body = addtoyresultbean.getBODY();
        mToyCode = body.getCODE();
        mToyId = body.getID();
        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        mToken = SPUtils.getString(this, "TOKEN", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
        //3.4.44 通过用户id拿到当前用户的宝宝列表
        getBabyList();

    }

    private void getBabyList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);

        QueryBabyListRequest.BODYBean bodyBean = new QueryBabyListRequest.BODYBean("10", "1");
        QueryBabyListRequest queryBabyListRequest = new QueryBabyListRequest("REQ", "QMYB", mPhoneNum, mTime, bodyBean, "", mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryBabyListRequest);
        Call<QueryBabyListResult> babyListResult = allInterface.getBabyListResult(s);
        babyListResult.enqueue(new Callback<QueryBabyListResult>() {
            @Override
            public void onResponse(Call<QueryBabyListResult> call, final Response<QueryBabyListResult> response) {
                Log.i("55555", "(3.4.11)onResponse: " + response.body().toString());
                BindBabyAdapter bindBabyAdapter = new BindBabyAdapter(getApplicationContext(), response);
                mListview_bindbaby.setAdapter(bindBabyAdapter);
//                mListview_bindbaby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        ImageView check = (ImageView) view.findViewById(R.id.iv_item_bindbaby_checked);
//
//
//
//                    }
//                });
//                mListview_bindbaby.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
////                        Log.i("55555555", "onItemLongClick: "+mCheckbox.getId());
////                        CheckBox check = (CheckBox) parent.findViewById(R.id.cb_item_bindbaby_checkbox);
//                        ImageView check = (ImageView) view.findViewById(R.id.iv_item_bindbaby_checked);
//                        check.setVisibility(View.VISIBLE);
//
//                        mButton_confirm.setVisibility(View.VISIBLE);
//                        mButton_confirm.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                //点击了确定,就应该访问网络接口,完成绑定的逻辑,页面也要挑战到玩具的选择页面
//                                String babyID = response.body().getBODY().getLST().get(position).getID();
//
//                                //请求网络 3.4.18 绑定玩具
//                                bindBabyToToy(babyID);
//                            }
//                        });
//                        return true;
//                    }
//                });
            }

            @Override
            public void onFailure(Call<QueryBabyListResult> call, Throwable t) {

            }
        });

    }

    private void bindBabyToToy(String babyID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        BindBabyToToyReq.BODYBean.LSTBean lstBean = new BindBabyToToyReq.BODYBean.LSTBean(babyID, mToyCode, mToyId);
        List<BindBabyToToyReq.BODYBean.LSTBean> lst = new ArrayList<BindBabyToToyReq.BODYBean.LSTBean>();
        BindBabyToToyReq.BODYBean bodyBean = new BindBabyToToyReq.BODYBean(lst);
        BindBabyToToyReq bindBabyToToyReq = new BindBabyToToyReq("REQ", "TOYB", mPhoneNum, mTime, bodyBean, "", mToken, "1");

        Gson gson = new Gson();
        String s = gson.toJson(bindBabyToToyReq);
        Call<BindBabyToToyRes> bindBabyToToyResCall = allInterface.BIND_BABY_TO_TOY_RES_CALL(s);
        bindBabyToToyResCall.enqueue(new Callback<BindBabyToToyRes>() {
            @Override
            public void onResponse(Call<BindBabyToToyRes> call, Response<BindBabyToToyRes> response) {
                Log.i("555555", "(3.4.18) onResponse: " + response.body().toString());
                ToastUtil.showToast(getApplicationContext(), "绑定成功,跳转到toyselector页面");
            }

            @Override
            public void onFailure(Call<BindBabyToToyRes> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bindbaby_backa:
                break;
            case R.id.bt_bindbaby:
                break;
            case R.id.lv_bindbaby:
                break;
//            case R.id.iv_item_bindbaby_checked:
//                ToastUtil.showToast(this,"碰到了");
            default:
                break;
        }
    }
}
