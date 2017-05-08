package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoverySecondCategoryAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by android on 2017/3/11.
 */

public class DiscoverySecondCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListviewSecondCategory;
    private ImageView mIv_discoverysecondcategory;
    private Button mSubscribeButton;
    private Response<DiscoveryGridSecondaryResultBean> mResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_secondcategory);

        initView();
        initListener();
        initData();

    }

    private void initView() {

        mIv_discoverysecondcategory = (ImageView) findViewById(R.id.iv_album_details_one);
        mSubscribeButton = (Button) findViewById(R.id.bt_item_album_details_one_subscribe);

        mListviewSecondCategory = (ListView) findViewById(R.id.lv_activity_discovery_secondcategory);


    }

    private void initListener() {
        mSubscribeButton.setOnClickListener(this);
    }


    private void initData() {
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String colid = intent.getStringExtra("colid");
        Uri parse = Uri.parse(img);
        Glide.with(this).load(parse).asBitmap().into(mIv_discoverysecondcategory);
        String phoneNum = SPUtils.getString(this, "phoneNum", "");
        String token = SPUtils.getString(this, "TOKEN", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formatTime = simpleDateFormat.format(new Date());


        getIdColSecondaryInfo(colid, phoneNum, formatTime, token);


    }


    private void getIdColSecondaryInfo(String colid, final String phoneNum, final String formatTime, final String token) {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final AllInterface allInterface = retrofit.create(AllInterface.class);

        DiscoveryGridSecondaryRequestBean.BODYBean bodyBean = new DiscoveryGridSecondaryRequestBean.BODYBean(colid, "10", "1");
        Log.d("aaaaaa", "colid " + colid);
        DiscoveryGridSecondaryRequestBean discoveryGridSecondaryRequestBean = new DiscoveryGridSecondaryRequestBean("REQ", "QRYRES", phoneNum,
                formatTime, bodyBean, "", token, "1");
        final Gson gson = new Gson();
        String s = gson.toJson(discoveryGridSecondaryRequestBean);
        Call<DiscoveryGridSecondaryResultBean> discoveryGridSecondaryResult = allInterface.getDiscoveryGridSecondaryResult(s);
        discoveryGridSecondaryResult.enqueue(new Callback<DiscoveryGridSecondaryResultBean>() {
            @Override
            public void onResponse(Call<DiscoveryGridSecondaryResultBean> call, final Response<DiscoveryGridSecondaryResultBean> response) {

                if (response.body() != null && response.body().getCODE().equals("0")) {
                    Log.d("aaaaaa", "onResponse: " + response.body().toString());
                    mResponse = response;

                    DiscoverySecondCategoryAdapter discoverySecondCategoryAdapter = new DiscoverySecondCategoryAdapter(getApplication(), mResponse);
                    mListviewSecondCategory.setAdapter(discoverySecondCategoryAdapter);
                    mListviewSecondCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            ToastUtil.showToast(getApplicationContext(), "点击的是" + position);
                            Retrofit retrofit2=new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            AllInterface allInterface1 = retrofit2.create(AllInterface.class);
                            Gson gson1=new Gson();

                            //本机播放需要播放申请,3.4.48   网络请求
                            LocalPlayApplyReqBean.BODYBean bodyBean1 = new LocalPlayApplyReqBean.BODYBean(mResponse.body().getBODY().getLST().get
                                    (position).getID());
                            LocalPlayApplyReqBean localPlayApplyReqBean = new LocalPlayApplyReqBean("REQ", "PLAY", phoneNum, formatTime, bodyBean1,
                                    "", token, "1");
                            String s1 = gson1.toJson(localPlayApplyReqBean);
                            Call<LocalPlayApplyResBean> localPlayApplyResBeanCall = allInterface1.LOCAL_PLAY_APPLY_RES_BEAN_CALL(s1);
                            localPlayApplyResBeanCall.enqueue(new Callback<LocalPlayApplyResBean>() {
                                @Override
                                public void onResponse(Call<LocalPlayApplyResBean> call, Response<LocalPlayApplyResBean> response) {
                                    if (response.body().getCODE().equals("-700")) {
                                        ToastUtil.showToast(getApplicationContext(), "资源不存在");
                                        return;
                                    } else if (response.body().getCODE().equals("0")) {
                                        Intent intent = new Intent();
                                        intent.putExtra("musicimg",mResponse.body().getBODY().getLST().get(position).getIMG() );
                                        intent.putExtra("musicname",mResponse.body().getBODY().getLST().get(position).getNAME());
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("musicinfo", response.body());
                                        intent.putExtras(bundle);
                                        intent.setClass(getApplicationContext(), MyPlayActivity.class);
                                        startActivity(intent);
                                        ToastUtil.showToast(getApplicationContext(),"didi");
                                    }
                                }

                                @Override
                                public void onFailure(Call<LocalPlayApplyResBean> call, Throwable t) {

                                }
                            });


                        }
                    });
                } else {
                    ToastUtil.showToast(getApplicationContext(), "没有获取到response");
                }
            }

            @Override
            public void onFailure(Call<DiscoveryGridSecondaryResultBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "联网失败");
            }
        });

    }

    @Override
    public void onClick(View v) {
        ToastUtil.showToast(this, "点击的是专辑订阅");
    }
}


