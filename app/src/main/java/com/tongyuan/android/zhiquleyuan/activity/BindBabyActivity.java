package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.BindBabyAdapter;
import com.tongyuan.android.zhiquleyuan.bean.BindBabyToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.BindBabyToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListRequest;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
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

/**
 * Created by Android on 2017/5/15.
 */

public class BindBabyActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = BindBabyActivity.class.getSimpleName();
    public static final int RESULT_OK = 100;
    private String mBabyId;
    private ListView mListview_bindbaby;
    private Button mButton_confirm;
    private LinearLayout mBack_bindbaby;
    private String mPhoneNum;
    private String mToken;
    private String mTime;
    private String mToyCode;
    private String mToyId;
    //private ImageButton mCheck;
    private int checkPosition = -1;
    private int savePositioin = -1;
    Response<QueryBabyListResult> myResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindbaby);
        initView();
        initData();
        initListener();
        RadioButton radioButton = new RadioButton(this);


    }

    private void initView() {

        mListview_bindbaby = (ListView) findViewById(R.id.lv_bindbaby);
        mButton_confirm = (Button) findViewById(R.id.bt_bindbaby);
        mBack_bindbaby = (LinearLayout) findViewById(R.id.iv_bindbaby_backa);
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_bindbaby, null);
        //mCheck = (ImageButton) inflate.findViewById(R.id.iv_item_bindbaby_checked);

    }

    private void initListener() {

        mBack_bindbaby.setOnClickListener(this);
        mButton_confirm.setOnClickListener(this);
        //mCheck.setOnClickListener(this);
//        mListview_bindbaby.setOnClickListener(this);


    }


    private void initData() {

        //从ToyAddFragment穿过来的数据
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        LogUtil.i("bindbabyactivity", "initData: +bundle" + bundle);
//        LogUtil.i("bindbabyactivity", "initData: +bundle" + bundle.getParcelable("addtoyresultbean"));

//        AddToyResultBean addtoyresultbean = bundle.getParcelable("addtoyresultbean");//3.4.11接口
//        AddToyResultBean.BODYBean body = addtoyresultbean.getBODY();
//        mToyCode = body.getCODE();
//        mToyId = body.getID();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mToyCode = bundle.getString("toycode");
        mToyId = bundle.getString("toyid");

        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        mToken = SPUtils.getString(this, "token", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
        //3.4.44 通过用户id拿到当前用户的宝宝列表
        getBabyList();

    }

    private void getBabyList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);

        QueryBabyListRequest.BODYBean bodyBean = new QueryBabyListRequest.BODYBean("", "1");
        QueryBabyListRequest queryBabyListRequest = new QueryBabyListRequest("REQ", "QMYB", mPhoneNum, mTime, bodyBean, "", mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryBabyListRequest);
        Call<QueryBabyListResult> babyListResult = allInterface.getBabyListResult(s);
        babyListResult.enqueue(new Callback<QueryBabyListResult>() {

            @Override
            public void onResponse(Call<QueryBabyListResult> call, final Response<QueryBabyListResult> response) {
                if (response != null && response.body().getCODE().equals("0")) {
                    myResponse = response;
                    LogUtil.i("55555", "(3.4.11)onResponse: " + response.body().toString());
                    final BindBabyAdapter bindBabyAdapter = new BindBabyAdapter(getApplicationContext(), response);
                    mListview_bindbaby.setAdapter(bindBabyAdapter);
                    mListview_bindbaby.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

                    mListview_bindbaby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            checkPosition = position;
                            if (checkPosition != -1) {
                                if (savePositioin == -1) {//表示的是第一次点击,那么第一次点击就直接设置点击的item的button
//                        ImageButton checked = (ImageButton) parent.getRootView().findViewById(R.id.iv_item_bindbaby_checked);
                                    CheckBox checked = (CheckBox) view.findViewById(R.id.iv_item_bindbaby_checked);
//                        LogUtil.i(TAG, "onItemClick: " + (checked == checked1 ? "1" : "2") + ">>>>>>" + checked + "<<<<<<+" + checked1);
                                    checked.setChecked(true);
                                    LogUtil.i(TAG, "savePosition(前)  :  >>" + savePositioin);
                                    //mListview_bindbaby.setItemChecked(checkPosition, true);
                                    savePositioin = checkPosition;
                                    bindBabyAdapter.notifyDataSetChanged();
                                    LogUtil.i(TAG, "savePosition(后)  :  >>" + savePositioin);
                                } else {//表示非第一次点击
                                    if (savePositioin != checkPosition) {//非第一次点击的情况下,判断点击的item是不是同一个,不是同一个就把上次点击的图片取消,这次的图片设置.并且把这次的点击的位置记录给saveposition
                                        View saveView = mListview_bindbaby.findViewWithTag(savePositioin);
                                        CheckBox uncheckedView = (CheckBox) saveView.findViewById(R.id.iv_item_bindbaby_checked);
                                        //mListview_bindbaby.setItemChecked(savePositioin, false);
                                        uncheckedView.setChecked(false);

                                        CheckBox checked = (CheckBox) view.findViewById(R.id.iv_item_bindbaby_checked);

                                        checked.setChecked(true);
                                        //mListview_bindbaby.setItemChecked(checkPosition, true);
                                        savePositioin = checkPosition;
                                    }

                                }
                            }
                            mBabyId = myResponse.body().getBODY().getLST().get(checkPosition).getID().toString();
                            LogUtil.i("555555", "onItemClick: +mBabyId" + mBabyId);
//                            ToastUtil.showToast(getApplicationContext(), "item" + position);
                        }
                    });

//                mListview_bindbaby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mListview_bindbaby.setItemChecked(position, true);
////                        if (mListview_bindbaby.isItemChecked(position)){
////                            mCheck.isSelected(true);
////                        }
////                        mCheck.setSelected(false);
//                        checkPosition = position;
//                        mBabyId = response.body().getBODY().getLST().get(checkPosition).getID().toString();
//                        LogUtil.i("555555", "onItemClick: +mBabyId" + mBabyId);
//                        ToastUtil.showToast(getApplicationContext(), "item" + position);
//                    }
//                });

                } else if (response.body().getCODE().equals("-10006")) {
                    SPUtils.putString(getApplicationContext(), "token", "");
                    ToastUtil.showToast(BindBabyActivity.this, response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<QueryBabyListResult> call, Throwable t) {
                ToastUtil.showToast(getBaseContext(), "网络异常,请检查网络");
                LogUtil.i(TAG, t.toString());
            }
        });

    }

    private void bindBabyToToy(String babyID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        BindBabyToToyReq.BODYBean.LSTBean lstBean = new BindBabyToToyReq.BODYBean.LSTBean(mToyId, mToyCode, babyID);
        List<BindBabyToToyReq.BODYBean.LSTBean> lst = new ArrayList<>();
        lst.add(lstBean);

        BindBabyToToyReq.BODYBean bodyBean = new BindBabyToToyReq.BODYBean(lst);
        BindBabyToToyReq bindBabyToToyReq = new BindBabyToToyReq("REQ", "TOYB", mPhoneNum, mTime, bodyBean, "", mToken, "1");

        Gson gson = new Gson();
        String s = gson.toJson(bindBabyToToyReq);
        Call<BindBabyToToyRes> bindBabyToToyResCall = allInterface.BIND_BABY_TO_TOY_RES_CALL(s);
        bindBabyToToyResCall.enqueue(new Callback<BindBabyToToyRes>() {
            @Override
            public void onResponse(Call<BindBabyToToyRes> call, Response<BindBabyToToyRes> response) {

                LogUtil.i("555555", "(3.4.18) onResponse: " + response.body().toString());
//                ToastUtil.showToast(getApplicationContext(), "绑定成功,跳转到toyselector页面");
                String img = myResponse.body().getBODY().getLST().get(checkPosition).getIMG();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("babyid", mBabyId);
                bundle.putString("babyimage", img);
                intent.putExtra("bindbabybundle", bundle);
                setResult(100, intent);
                finish();
            }

            @Override
            public void onFailure(Call<BindBabyToToyRes> call, Throwable t) {
                ToastUtil.showToast(getBaseContext(), "网络异常,请检查网络");
                LogUtil.i(TAG, t.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bindbaby_backa:
                finish();
                break;
            case R.id.bt_bindbaby:
                if (checkPosition == -1) {
                    ToastUtil.showToast(this, "请选择宝宝");
                    return;
                } else {

                    bindBabyToToy(mBabyId);
                }
                break;
            default:
                break;
        }
    }

}
