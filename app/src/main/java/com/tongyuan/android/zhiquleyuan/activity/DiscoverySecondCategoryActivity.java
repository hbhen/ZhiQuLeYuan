package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoverySecondCategoryAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoverySubReqBean;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 发现页二级目录
 * Created by android on 2017/3/11.
 */

public class DiscoverySecondCategoryActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener {

    @BindView(R.id.lv_activity_discovery_secondcategory)
    ListView listView;
    //    @BindView(R.id.iv_album_details_one)
    //    ImageView coverImageView;
    @BindView(R.id.swipe_seconde_category)
    SwipeRefreshLayout swipeRefresh;
    private View footerView;
    private DiscoverySecondCategoryAdapter adapter;
    private String token;
    private String colid;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> list = new ArrayList<>();
    private int currPage = 1;
    String NC = "-1";
    private static final String TAG
            = "tag";

    public static void launch(Context context, String imgPath, String colId) {
        Intent intent = new Intent(context, DiscoverySecondCategoryActivity.class);
        intent.putExtra("img", imgPath);
        intent.putExtra("colid", colId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_secondcategory);
        ButterKnife.bind(this);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));
        adapter = new DiscoverySecondCategoryAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if ("".equals(token)) {
                    ToastUtil.showToast(getApplicationContext(), R.string.user_no_login);
                    return;
                }
                MyPlayActivity.launch(DiscoverySecondCategoryActivity.this, adapter.getList(), position);
            }
        });

        listView.setOnScrollListener(this);
        footerView = LayoutInflater.from(this).inflate(R.layout.discovery_sub_item_foot, null);
        footerView.setVisibility(View.GONE);
        listView.addFooterView(footerView);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NC = "-1";
                currPage = 1;
                getIdColSecondaryInfo(colid, false);
                swipeRefresh.setRefreshing(false);
            }
        });
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.onPause();
    }

    private void initData() {
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        colid = intent.getStringExtra("colid");
        Uri parse = Uri.parse(img);
//        Glide.with(this).load(parse).asBitmap().into(coverImageView);
        token = SPUtils.getString(this, "token", "");
        getIdColSecondaryInfo(colid, false);
    }


    private void getIdColSecondaryInfo(String colid, final boolean isLoadMore) {
        int page = currPage;
        if (isLoadMore) {
            page++;
        }
        if (!NC.equals("0")) {
            DiscoverySubReqBean bodyBean = new DiscoverySubReqBean(colid, "10", String.valueOf(page));
            Call<SuperModel<DiscoveryGridSecondaryResultBean>> result = RequestManager.getInstance().
                    getDiscoverySubList(this, bodyBean);
            result.enqueue(new Callback<SuperModel<DiscoveryGridSecondaryResultBean>>() {
                @Override
                public void onResponse(Call<SuperModel<DiscoveryGridSecondaryResultBean>> call, Response<SuperModel<DiscoveryGridSecondaryResultBean>>
                        response) {
                    LogUtil.d(TAG, "onResponse:(discoverysubreqbean) " + response.body().toString() + "__________" + response.body().BODY.toString());
                    if ("0".equals(response.body().CODE)) {
                        if (isLoadMore) {
                            currPage++;
                        } else {
                            list.clear();
                            currPage = 1;
                        }
                        NC = response.body().BODY.NC;
                        list.addAll(response.body().BODY.LST);
//                    LogUtil.i(TAG, "all list() : " + response.body().BODY.LST.toString());
//                    LogUtil.i(TAG, "LST.size():" + response.body().BODY.LST.size());
//                    LogUtil.i(TAG, "list.addall():" + list.toString());
//                    LogUtil.i(TAG, "list.size():" + list.size());
                        LogUtil.i(TAG, "response():" + response.body().toString());
                        LogUtil.i(TAG, "response():" + response.body().BODY.toString());
                        LogUtil.i(TAG, "response.body.nc():" + response.body().BODY.NC);
                        LogUtil.i(TAG, "response.body.PN():" + response.body().BODY.PN);
                        LogUtil.i(TAG, "response.body.PS():" + response.body().BODY.PS);
                        if ("0".equals(response.body().BODY.NC)) {
                            footerView.setVisibility(View.GONE);
                        } else {
                            footerView.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
//                        ToastUtil.showToast(ZQLYApp.context, "成功");
                    } else if (response.body().CODE.equals("-10006")) {
                        SPUtils.putString(getApplicationContext(), "token", "");
                        ToastUtil.showToast(getApplicationContext(), response.body().MSG);
                    } else {
//                        ToastUtil.showToast(ZQLYApp.context, "没成功");
//                    ToastUtil.showToast(getApplicationContext(), response.body().MSG);
                        footerView.setVisibility(View.GONE);
                    }
                    isLoading = false;

                }

                @Override
                public void onFailure(Call<SuperModel<DiscoveryGridSecondaryResultBean>> call, Throwable t) {
                    ToastUtil.showToast(getApplicationContext(), "网络异常,请检查网络");
                    isLoading = false;
                }
            });
        }
    }

    @OnClick({R.id.sub_discovery_back})
//    R.id.bt_item_album_details_one_subscribe
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.bt_item_album_details_one_subscribe:
//                ToastUtil.showToast(this, "点击的是专辑订阅");
//                break;
            case R.id.sub_discovery_back:
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
            getIdColSecondaryInfo(colid, true);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }
}