package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.ActivityLogin;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryGridAdapter;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryListViewAdapter;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryRecyclerAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListRequsetBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.SpacesItemDecoration;
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
 * Created by android on 2016/12/3.
 */
/*
* 先加载布局,再在布局里面添加数据.布局从哪个生命周期开始加载?数据从哪个生命周期开始加载?
*
* */
public class DiscoveryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    public static final int REQUEST_CODE_LOGIN = 1001;
    private ListView lv_fragment_discovery;
    private GridView gv_fragment_discovery;
    public static final String TAG = "discovery";
    private List<DiscoveryGridItemBean.LSTBean> lst = new ArrayList<>();
    private DiscoveryGridAdapter mGAdapter;
    private DiscoveryListViewAdapter mLAdapter;
    private TextView mGridviewTitle;
    private View mDiscoveryRoot;
    private SwipeRefreshLayout mSwiperefresh_discovery;
    private RecyclerView mRecyclerView_discovery;
    private DiscoveryRecyclerAdapter mDiscoveryRecyclerAdapter;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> discoveryListViewList = new ArrayList<>();
    private List<DiscoveryGridItemBean.LSTBean> discoveryGridViewList = new ArrayList<>();
    private String mToken;
    private String mPhoneNum;
    //    private String mLogintokenToMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDiscoveryRoot = inflater.inflate(R.layout.fragment_discovery_recycleview, null);
        initView();


//        lv_fragment_discovery = (ListView) mDiscoveryRoot.findViewById(R.id.lv_fragment_discovery);
//        gv_fragment_discovery = (GridView) mDiscoveryRoot.findViewById(R.id.gv_fragment_discovery);
//        mGridviewTitle = (TextView) mDiscoveryRoot.findViewById(R.id.tv_gridview_title);
//        mGridviewTitle.setOnClickListener(this);
//        mDiscoveryRoot.findViewById(R.id.et_home_title).setOnClickListener(this);
//        mGAdapter = new DiscoveryGridAdapter(getActivity(), lst);
//        gv_fragment_discovery.setAdapter(mGAdapter);
//        gv_fragment_discovery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String mColid = lst.get(position).ID;
//                String img = lst.get(position).IMG;
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), DiscoverySecondCategoryActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("colid", mColid);
//                bundle.putString("img", img);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
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
        mRecyclerView_discovery.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        mDiscoveryRecyclerAdapter.setOnItemClickListener(new DiscoveryRecyclerAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    ToastUtil.showToast(getContext(), "" + position);
                } else if (position >= 1 && position <= 9) {
                    ToastUtil.showToast(getContext(), "ninegrid" + position);
                } else if (position >= 10) {
                    ToastUtil.showToast(getContext(), "listview" + position);
                }
            }
        });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        checkLoginState();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        checkLoginState();
    }

    private void checkLoginState() {
        if (SPUtils.getString(getActivity(), "TOKEN", "").isEmpty()) {

//            mGridviewTitle.setVisibility(View.VISIBLE);
        } else {
//            mGridviewTitle.setVisibility(View.GONE);
        }
    }

    private void initData() {
        mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        mToken = SPUtils.getString(getActivity(), "TOKEN", "");
        //拿grid的数据
        getIdCol();
    }

    //拿到list的数据
    private void getListRaw() {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        DiscoveryListRequsetBean.BODYBean bodyBean = new DiscoveryListRequsetBean.BODYBean("10", "1");

        DiscoveryListRequsetBean discoveryListRequsetBean = new DiscoveryListRequsetBean("REQ", "QRYREC", SPUtils.getString(getActivity(),
                "phoneNum", ""),
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(getActivity(), "TOKEN", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(discoveryListRequsetBean);
        Call<DiscoveryListResultBean> discoveryListResult = allInterface.getDiscoveryListResult(s);

        discoveryListResult.enqueue(new Callback<DiscoveryListResultBean>() {
            @Override
            public void onResponse(Call<DiscoveryListResultBean> call, final Response<DiscoveryListResultBean> response) {
                Log.i(TAG, "code "+response.body().getCODE());
                if (response != null && !response.body().getCODE().equals(0)) {
                    discoveryListViewList.clear();
                    discoveryListViewList.addAll(response.body().getBODY().getLST());
                    Log.i(TAG, "onResponse: list" + discoveryListViewList.toString());
//                    sendListViewList();

                    //返回的list是一个空list
                    Log.d(TAG, "onResponse: " + SPUtils.getString(getActivity(), "TOKEN", ""));
                    mDiscoveryRecyclerAdapter.notifyDataSetChanged();
//
                } else {
                    ToastUtil.showToast(getActivity(), "shibai1");
                }
            }

            //
            @Override
            public void onFailure(Call<DiscoveryListResultBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "shibai2");
            }
        });*/

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

        } else if (v.getId() == R.id.tv_gridview_title) {
            Intent intent = new Intent(getActivity(), ActivityLogin.class);
            //需不需要intent传参数出去再说吧
            startActivityForResult(intent, REQUEST_CODE_LOGIN);
        }
        Log.i("tagd", "onResume: went");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("tagd", "onStart: went");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("tagd", "onAttach: went");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("tagd", "onCreate: went");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("tagd", "onPause: went");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("tagd", "onStop: went");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("tagd", "onDetach: went");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("tagd", "onDestroyView: went");

    }

    @Override
    public void onRefresh() {
        initData();
    }

}

