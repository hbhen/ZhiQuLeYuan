package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongyuan.android.zhiquleyuan.R;
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
    private SwipeRefreshLayout mSwiperefresh_discovery;
    private RecyclerView mRecyclerView_discovery;
    private DiscoveryRecyclerAdapter mDiscoveryRecyclerAdapter;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> discoveryListViewList = new ArrayList<>();
    private List<DiscoveryGridItemBean.LSTBean> discoveryGridViewList = new ArrayList<>();
    private SpacesItemDecoration spacesItemDecoration;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDiscoveryRoot = inflater.inflate(R.layout.fragment_discovery_recycleview, null);
        initView();
        return mDiscoveryRoot;
    }

    private void initView() {
        mSwiperefresh_discovery = (SwipeRefreshLayout) mDiscoveryRoot.findViewById(R.id.swiperefresh_discovery);
        mSwiperefresh_discovery.setOnRefreshListener(this);
        mRecyclerView_discovery = (RecyclerView) mDiscoveryRoot.findViewById(R.id.recyclerView_discovery);

        mDiscoveryRecyclerAdapter = new DiscoveryRecyclerAdapter(getContext(), discoveryGridViewList, discoveryListViewList);
        mRecyclerView_discovery.setLayoutManager(new GridLayoutManager(mRecyclerView_discovery.getContext(), 6, GridLayoutManager.VERTICAL,
                false));
        mRecyclerView_discovery.setAdapter(mDiscoveryRecyclerAdapter);
        int spacingInPixels = (int) getResources().getDimension(R.dimen.discovery_grid_space);
        spacesItemDecoration = new SpacesItemDecoration(spacingInPixels);
        mRecyclerView_discovery.addItemDecoration(spacesItemDecoration);
        /*mDiscoveryRecyclerAdapter.setOnItemClickListener(new DiscoveryRecyclerAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                *//*if (position == 0) {
                    ToastUtil.showToast(getContext(), "" + position);
                } else if (position >= 1 && position <= 9) {
                    ToastUtil.showToast(getContext(), "ninegrid" + position);
                } else if (position >= 10) {
                    ToastUtil.showToast(getContext(), "listview" + position);
                }*//*
            }
        });*/
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
        if (mDiscoveryRecyclerAdapter != null) {
            boolean isLogin = mDiscoveryRecyclerAdapter.isLogin();
            spacesItemDecoration.isLogin(isLogin);
            mDiscoveryRecyclerAdapter.notifyDataSetChanged();
        }
    }

    //拿到list的数据
    private void getListRaw() {
        DiscoveryListRequsetBean.BODYBean request = new DiscoveryListRequsetBean.BODYBean("10", "1");
        Call<SuperModel<DiscoveryListResultBean.BODYBean>> discoveryListResult = RequestManager.getInstance().getDiscoveryListResult(getContext(), request);
        discoveryListResult.enqueue(new Callback<SuperModel<DiscoveryListResultBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Response<SuperModel<DiscoveryListResultBean.BODYBean>> response) {
                if ("0".equals(response.body().CODE)) {
                    discoveryListViewList.clear();

                    discoveryListViewList.addAll(response.body().BODY.getLST());
                    mDiscoveryRecyclerAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showToast(getActivity(), response.body().MSG);
                }
                mSwiperefresh_discovery.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<SuperModel<DiscoveryListResultBean.BODYBean>> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "网络请求失败");
                mSwiperefresh_discovery.setRefreshing(false);
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
                        mDiscoveryRecyclerAdapter.notifyDataSetChanged();
                        //Log.i(TAG, "onResponse: grid" + discoveryGridViewList);
                        getListRaw();
                    }
                } else {
                    ToastUtil.showToast(getActivity(), response.body().MSG);
                    mSwiperefresh_discovery.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<DiscoveryGridItemBean>> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "网络请求失败");
                mSwiperefresh_discovery.setRefreshing(false);
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.et_home_title) {

//        } else if (v.getId() == R.id.tv_gridview_title) {
//            Intent intent = new Intent(getActivity(), ActivityLogin.class);
//            //需不需要intent传参数出去再说吧
//            startActivityForResult(intent, REQUEST_CODE_LOGIN);
        }
        //Log.i("tagd", "onResume: went");
    }

    @Override
    public void onRefresh() {
        getIdCol();
    }

}

