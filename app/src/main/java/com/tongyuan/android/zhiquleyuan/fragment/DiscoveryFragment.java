package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.SearchActivity;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryRecyclerAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListRequsetBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.SpacesItemDecoration;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 先加载布局,再在布局里面添加数据.布局从哪个生命周期开始加载?数据从哪个生命周期开始加载?
 * Created by android on 2016/12/3.
 */
public class DiscoveryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    //public static final int REQUEST_CODE_LOGIN = 1001;
    //public static final String TAG = "discovery";
    private View mDiscoveryRoot;
    private SwipeRefreshLayout mSwiperefresh;
    private RecyclerView mRecyclerView;
    private DiscoveryRecyclerAdapter mAdapter;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> discoveryListViewList = new ArrayList<>();
    private List<DiscoveryGridItemBean.LSTBean> discoveryGridViewList = new ArrayList<>();
    private SpacesItemDecoration spacesItemDecoration;
    private boolean isLoading = true;
    private int currPage = 1;
    private String NC = "0"; // 0代表没有更多推荐数据
    private TextView mSearchTitle;
    private ImageView mListeningTitle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDiscoveryRoot = inflater.inflate(R.layout.fragment_discovery_recycleview, null);
        initView();
        initListener();
        return mDiscoveryRoot;
    }

    private void initListener() {
        mSearchTitle.setOnClickListener(this);
        mListeningTitle.setOnClickListener(this);
    }

    private void initView() {
        mSwiperefresh = (SwipeRefreshLayout) mDiscoveryRoot.findViewById(R.id.swiperefresh_discovery);

        mSearchTitle = (TextView) mDiscoveryRoot.findViewById(R.id.et_home_title);
        mListeningTitle = (ImageView) mDiscoveryRoot.findViewById(R.id.iv_home_title);

        mSwiperefresh.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) mDiscoveryRoot.findViewById(R.id.recyclerView_discovery);

        mAdapter = new DiscoveryRecyclerAdapter(getContext(), discoveryGridViewList, discoveryListViewList);
        final GridLayoutManager layoutManager = new GridLayoutManager(mRecyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        int spacingInPixels = (int) getResources().getDimension(R.dimen.discovery_grid_space);
        spacesItemDecoration = new SpacesItemDecoration(spacingInPixels);
        mRecyclerView.addItemDecoration(spacesItemDecoration);
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //Log.i("gengen", "lastVisibleItemPosition + 1==" +(lastVisibleItemPosition+1) + " count="+mAdapter.getItemCount());
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    /*boolean isRefreshing = mSwiperefresh.isRefreshing();
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }*/

                    /*if(NC.equals("0")) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }*/

                    if (!isLoading && (!"0".endsWith(NC))) {
                        isLoading = true;
                        mAdapter.isLoadMore(true);
                        getListRaw(true);
                    }
                }
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //拿grid的数据
        getIdCol();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLoginState();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden)
            return;
        checkLoginState();
    }

    private void checkLoginState() {
        if (mAdapter != null) {
            boolean isLogin = mAdapter.isLogin();
            spacesItemDecoration.isLogin(isLogin);
            mAdapter.notifyDataSetChanged();
        }
    }

    //拿到list的数据
    private void getListRaw(final boolean isLoadMore) {
        int page = currPage;
        if (isLoadMore) {
            ++page;
        }
        DiscoveryListRequsetBean.BODYBean request = new DiscoveryListRequsetBean.BODYBean("10", String.valueOf(page));
        Call<SuperModel<DiscoveryListResultBean.BODYBean>> discoveryListResult = RequestManager.getInstance().getDiscoveryListResult(getContext(),
                request);
        discoveryListResult.enqueue(new Callback<SuperModel<DiscoveryListResultBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Response<SuperModel<DiscoveryListResultBean.BODYBean>>
                    response) {
                NC = response.body().BODY.getNC();
                if ("0".equals(response.body().CODE)) {
                    if (isLoadMore) {
                        ++currPage;
                    } else {
                        discoveryListViewList.clear();
                        currPage = 1;
                    }
                    //Log.i(TAG, "onResponse: "+response.body().toString());
                    discoveryListViewList.addAll(response.body().BODY.getLST());
                    mAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showToast(getActivity(), response.body().MSG);
                }
                mSwiperefresh.setRefreshing(false);
                isLoading = false;
                mAdapter.isLoadMore(false);
            }

            @Override
            public void onFailure(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "网络请求失败");
                mSwiperefresh.setRefreshing(false);
                isLoading = false;
            }
        });
    }

    //gridview的数据
    private void getIdCol() {
        final BaseRequest request = new BaseRequest<>(getContext(), new DiscoveryGridRequestBean("0", "-1", "1"));
        Call<SuperModel<DiscoveryGridItemBean>> discoveryGridResult = RequestManager.getInstance().getDiscoveryGridResult(request);
        discoveryGridResult.enqueue(new Callback<SuperModel<DiscoveryGridItemBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DiscoveryGridItemBean>> call, Response<SuperModel<DiscoveryGridItemBean>> response) {
                if ("0".equals(response.body().CODE)) {
                    if (response.body().BODY != null && response.body().BODY.LST != null) {
                        discoveryGridViewList.clear();
                        discoveryGridViewList.addAll(response.body().BODY.LST);
                        mAdapter.notifyDataSetChanged();
                        //Log.i(TAG, "onResponse: grid" + discoveryGridViewList);
                        getListRaw(false);
                    }
                } else {
                    ToastUtil.showToast(getActivity(), response.body().MSG);
                    mSwiperefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<DiscoveryGridItemBean>> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "网络请求失败");
                mSwiperefresh.setRefreshing(false);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_home_title:
                Intent intent = new Intent();
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_home_title:
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        getIdCol();
    }

}

