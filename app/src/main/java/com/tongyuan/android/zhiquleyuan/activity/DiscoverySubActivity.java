package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoverySecondCategoryAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyReqBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 专辑详情页
 * Created by android on 2017/3/11.
 */

public class DiscoverySubActivity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemClickListener{

    private DiscoverySecondCategoryAdapter adapter;
    private ImageView mIv_discoverysecondcategory;
    private Button mSubscribeButton;
    private List<DiscoveryGridSecondaryResultBean.LSTBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_secondcategory);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));
        initView();
        initListener();
        initData();

    }

    private void initView() {
        mIv_discoverysecondcategory = (ImageView) findViewById(R.id.iv_album_details_one);
        mSubscribeButton = (Button) findViewById(R.id.bt_item_album_details_one_subscribe);

        ListView listview = (ListView) findViewById(R.id.lv_activity_discovery_secondcategory);
        adapter = new DiscoverySecondCategoryAdapter(getApplication(), list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    private void initListener() {
        mSubscribeButton.setOnClickListener(this);
        findViewById(R.id.sub_discovery_back).setOnClickListener(this);
    }


    private void initData() {
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String colid = intent.getStringExtra("colid");
        Uri parse = Uri.parse(img);
        Glide.with(this).load(parse).asBitmap().into(mIv_discoverysecondcategory);
        getIdColSecondaryInfo(colid);
    }


    private void getIdColSecondaryInfo(String colid) {
        BaseRequest request = new BaseRequest<>(getApplicationContext(), new DiscoveryGridSecondaryRequestBean(colid, "10", "1"), "QRYRES");
        Call<SuperModel<DiscoveryGridSecondaryResultBean>> discoveryGridSecondaryResult = RequestManager.getInstance().getDiscoveryGridSecondaryResult(request);
        discoveryGridSecondaryResult.enqueue(new Callback<SuperModel<DiscoveryGridSecondaryResultBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DiscoveryGridSecondaryResultBean>> call, final Response<SuperModel<DiscoveryGridSecondaryResultBean>> response) {
                if (response.body() != null && response.body().CODE.equals("0")) {
                    list.clear();
                    list.addAll(response.body().BODY.LST);
                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showToast(getApplicationContext(), response.body().MSG);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<DiscoveryGridSecondaryResultBean>> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "联网失败");
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sub_discovery_back) {
            finish();
        } else {

            ToastUtil.showToast(this, "点击的是专辑订阅");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        ToastUtil.showToast(getApplicationContext(), "点击的是" + position);
        requestDetail(position);
    }

    private void requestDetail(final int position) {
        LocalPlayApplyReqBean.BODYBean bodyBean1 = new LocalPlayApplyReqBean.BODYBean(list.get
                (position).ID);
        BaseRequest request = new BaseRequest<>(getApplicationContext(), bodyBean1, "PLAY");
        Call<SuperModel<LocalPlayApplyResBean>> localPlayApplyResBeanCall = RequestManager.getInstance().requestMusicDetail(request);
        localPlayApplyResBeanCall.enqueue(new Callback<SuperModel<LocalPlayApplyResBean>>() {
            @Override
            public void onResponse(Call<SuperModel<LocalPlayApplyResBean>> call, Response<SuperModel<LocalPlayApplyResBean>> response) {
                if (response.body().CODE.equals("0")) {
                    Intent intent = new Intent();
                    intent.putExtra("musicimg", list.get(position).IMG);
                    intent.putExtra("musicname", list.get(position).NAME);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("musicinfo", response.body().BODY);
                    intent.putExtras(bundle);
                    intent.setClass(getApplicationContext(), MyPlayActivity.class);
                    startActivity(intent);
//                    ToastUtil.showToast(getApplicationContext(), "didi");
                } else {
                    ToastUtil.showToast(getApplicationContext(), response.body().MSG);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<LocalPlayApplyResBean>> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "error");
            }
        });
    }
}


