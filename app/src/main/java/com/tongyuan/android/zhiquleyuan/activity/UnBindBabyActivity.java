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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.UnBindBabyAdapter;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdReq;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdRes;
import com.tongyuan.android.zhiquleyuan.bean.UnbindBabyToToyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.UnbindBabyToToyResBean;
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
 * Created by Android on 2017/6/30.
 */
public class UnBindBabyActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListview_unbindbaby;
    private Button mButton_confirm;
    private LinearLayout mBack_unbindbaby;
    private ImageButton mCheck;
    private String mToyCode;
    private String mToyId;
    private String mPhoneNum;
    private String mToken;
    private String mTime;
    private int checkPosition = -1;
    private String mBabyId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbindbaby);

        initView();
        initListener();
        initData();

    }

    private void initView() {

        mListview_unbindbaby = (ListView) findViewById(R.id.lv_unbindbaby);
        mButton_confirm = (Button) findViewById(R.id.bt_unbindbaby);
        mBack_unbindbaby = (LinearLayout) findViewById(R.id.iv_unbindbaby_backa);
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_unbindbaby, null);
        mCheck = (ImageButton) inflate.findViewById(R.id.iv_item_unbindbaby_checked);

    }

    private void initListener() {

        mBack_unbindbaby.setOnClickListener(this);
        mButton_confirm.setOnClickListener(this);
        mCheck.setOnClickListener(this);
//        mListview_bindbaby.setOnClickListener(this);

    }


    private void initData() {

        //从ToyAddFragment穿过来的数据

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mToyCode = bundle.getString("toycode");
        mToyId = bundle.getString("toyid");

        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        mToken = SPUtils.getString(this, "token", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
        //3.4.24 通过玩具id拿到当前用户的宝宝列表
        getBabyListFromToy();

    }

    private void getBabyListFromToy() {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSS");
        String currentTime = simpleDateFormat.format(new Date());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryBabyListFromToyIdReq.BODYBean bodyBean = new QueryBabyListFromToyIdReq.BODYBean(mToyId, "10", "1");
        QueryBabyListFromToyIdReq queryBabyListFromToyIdReq = new QueryBabyListFromToyIdReq("REQ", "QRYTOYB", mPhoneNum, currentTime, bodyBean, "",
                mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(queryBabyListFromToyIdReq);

        Call<QueryBabyListFromToyIdRes> queryBabyListFromToyIdResCall = allInterface.QUERY_BABY_LIST_FROM_TOY_ID_CALL(s);
        queryBabyListFromToyIdResCall.enqueue(new Callback<QueryBabyListFromToyIdRes>() {
            @Override
            public void onResponse(Call<QueryBabyListFromToyIdRes> call, final Response<QueryBabyListFromToyIdRes> response) {
                UnBindBabyAdapter unBindBabyAdapter = new UnBindBabyAdapter(getApplicationContext(), response);
                mListview_unbindbaby.setAdapter(unBindBabyAdapter);
                mListview_unbindbaby.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                mListview_unbindbaby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mListview_unbindbaby.setItemChecked(position, true);
                        checkPosition = position;
                        mBabyId = response.body().getBODY().getLST().get(checkPosition).getID().toString();
                        LogUtil.i("555555", "onItemClick: +mBabyId" + mBabyId);
                        ToastUtil.showToast(getApplicationContext(), "item" + position);
                    }
                });
            }

            @Override
            public void onFailure(Call<QueryBabyListFromToyIdRes> call, Throwable t) {

            }
        });

    }

    private void unBindBabyToToy(String babyID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        UnbindBabyToToyReqBean.BODYBean.LSTBean lstBean = new UnbindBabyToToyReqBean.BODYBean.LSTBean(mToyId, mToyCode, babyID);
        List<UnbindBabyToToyReqBean.BODYBean.LSTBean> lst = new ArrayList<>();
        lst.add(lstBean);
        UnbindBabyToToyReqBean.BODYBean bodyBean = new UnbindBabyToToyReqBean.BODYBean(lst);
        UnbindBabyToToyReqBean bindBabyToToyReq = new UnbindBabyToToyReqBean("REQ", "DTOYB", mPhoneNum, mTime, bodyBean, "", mToken, "1");

        Gson gson = new Gson();
        String s = gson.toJson(bindBabyToToyReq);
        Call<UnbindBabyToToyResBean> unBindBabyToToyResCall = allInterface.UNBIND_BABY_TO_TOY_RES_BEAN_CALL(s);
        unBindBabyToToyResCall.enqueue(new Callback<UnbindBabyToToyResBean>() {
            @Override
            public void onResponse(Call<UnbindBabyToToyResBean> call, Response<UnbindBabyToToyResBean> response) {
                LogUtil.i("555555", "(3.4.19) onResponse: " + response.body().toString());
                ToastUtil.showToast(getApplicationContext(), "解绑成功,跳转到toyselector页面");

                finish();
            }

            @Override
            public void onFailure(Call<UnbindBabyToToyResBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_unbindbaby_backa:
                finish();
                break;
            case R.id.bt_unbindbaby:
                if (checkPosition == -1) {
                    ToastUtil.showToast(this, "请选择宝宝");
                    return;
                } else {
                    unBindBabyToToy(mBabyId);
                }

                break;
            case R.id.iv_item_unbindbaby_checked:
                ToastUtil.showToast(this, "碰到了");
            default:
                break;
        }
    }
}
