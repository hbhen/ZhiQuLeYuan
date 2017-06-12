package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.ActivityLogin;
import com.tongyuan.android.zhiquleyuan.activity.DiscoverySecondCategoryActivity;
import com.tongyuan.android.zhiquleyuan.activity.MyPlayActivity;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryGridAdapter;
import com.tongyuan.android.zhiquleyuan.adapter.DiscoveryListViewAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListRequsetBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.Items;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
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
 *
 * Created by android on 2016/12/3.
 */
/*
* 先加载布局,再在布局里面添加数据.布局从哪个生命周期开始加载?数据从哪个生命周期开始加载?
*
* */
public class DiscoveryFragment extends BaseFragment implements View.OnClickListener{
    public static final int REQUEST_CODE_LOGIN = 1001;
    private ListView lv_fragment_discovery;
    private GridView gv_fragment_discovery;
    public static final String TAG = "discovery";
    private List<DiscoveryGridItemBean.LSTBean> lst = new ArrayList<>();
    private DiscoveryGridAdapter mGAdapter;
    private DiscoveryListViewAdapter mLAdapter;
    private TextView mGridviewTitle;
//    private String mLogintokenToMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View discoveryRoot = inflater.inflate(R.layout.fragment_discovery, null);
        lv_fragment_discovery = (ListView) discoveryRoot.findViewById(R.id.lv_fragment_discovery);
        gv_fragment_discovery = (GridView) discoveryRoot.findViewById(R.id.gv_fragment_discovery);
        mGridviewTitle = (TextView) discoveryRoot.findViewById(R.id.tv_gridview_title);
        mGridviewTitle.setOnClickListener(this);
        discoveryRoot.findViewById(R.id.et_home_title).setOnClickListener(this);

        mGAdapter = new DiscoveryGridAdapter(getActivity(), lst);
        gv_fragment_discovery.setAdapter(mGAdapter);
        gv_fragment_discovery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mColid = lst.get(position).ID;
                String img = lst.get(position).IMG;
                Intent intent = new Intent();
                intent.setClass(getActivity(), DiscoverySecondCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("colid", mColid);
                bundle.putString("img", img);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return discoveryRoot;
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
        checkLoginState();
    }

    private void checkLoginState() {
        if (SPUtils.getString(getActivity(), "TOKEN", "").isEmpty()) {
            mGridviewTitle.setVisibility(View.VISIBLE);
        } else {
            mGridviewTitle.setVisibility(View.GONE);
        }
    }

    private void initData() {
//        拿grid的数据
        getIdCol();

//        getIdColSecondaryInfo();

//        拿到list的数据
        getListRaw();

        //拿不到推荐资源的数据，先用colid 0来代替
//        getListRawFromId();

    }

    //拿到colid 去申请的数据
//    private void getListRawFromId() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        AllInterface allInterface = retrofit.create(AllInterface.class);
//
//        DiscoveryGridSecondaryRequestBean.BODYBean bodyBean = new DiscoveryGridSecondaryRequestBean.BODYBean(mColid, "10", "1");
//        Log.d("aaaaaa", "colid " + mColid);
//        DiscoveryGridSecondaryRequestBean discoveryGridSecondaryRequestBean = new DiscoveryGridSecondaryRequestBean("REQ", "QRYRES", SPUtils
//                .getString(getActivity(), "phoneNum", ""), new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils
//                .getString(getActivity(), "TOKEN", ""), "1");
//        Gson gson = new Gson();
//        String s = gson.toJson(discoveryGridSecondaryRequestBean);
//        Call<DiscoveryGridSecondaryResultBean> discoveryGridSecondaryResult = allInterface.getDiscoveryGridSecondaryResult(s);
//        discoveryGridSecondaryResult.enqueue(new Callback<DiscoveryGridSecondaryResultBean>() {
//            @Override
//            public void onResponse(Call<DiscoveryGridSecondaryResultBean> call, final Response<DiscoveryGridSecondaryResultBean> response) {
//                if (response != null && !response.body().getCODE().equals(0)) {
//                    //返回的list是一个空list
//                    Log.d(TAG, "onResponse: " + SPUtils.getString(getActivity(), "TOKEN", ""));

//                    List<Items> list = new ArrayList<Items>();
//                    list.add(new Items("第一种布局", null));
//                    list.add(new Items(null, "第二种布局"));
//                    mLAdapter = new DiscoveryListViewAdapter(getActivity(), list);
//                    lv_fragment_discovery.setAdapter(mLAdapter);
//                    lv_fragment_discovery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Intent intent = new Intent();
//                            intent.setClass(getActivity(), MyPlayActivity.class);
//                            Bundle bundle = new Bundle();
//                            //responselist为空，lstbean不能用
//                            bundle.putParcelable("recommandmusicinfo", response.body());
//                            startActivity(intent);
//                            ToastUtil.showToast(getActivity(), "当前点击的是:" + position);
//                        }
//                    });
//                } else {
//                    ToastUtil.showToast(getActivity(), "shibai1");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DiscoveryGridSecondaryResultBean> call, Throwable t) {
//
//            }
//        });
//    }


    //拿到list的数据
    //TODO 后台没有数据!而且还不能添加,所以先空着,这里取不到数据;
    private void getListRaw() {
        Retrofit retrofit = new Retrofit.Builder()
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
                if (response != null && !response.body().getCODE().equals(0)) {
                    //返回的list是一个空list
                    Log.d(TAG, "onResponse: " + SPUtils.getString(getActivity(), "TOKEN", ""));
                    List<Items> list = new ArrayList<Items>();
                    list.add(new Items("第一种布局", null));
                    list.add(new Items(null, "第二种布局"));

                    mLAdapter = new DiscoveryListViewAdapter(getContext(), list, response);
                    lv_fragment_discovery.setAdapter(mLAdapter);
                    lv_fragment_discovery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            DiscoveryListResultBean.BODYBean.LSTBean lstBean = (DiscoveryListResultBean.BODYBean.LSTBean) parent.getAdapter().getItem(position);
//                             = response.body().getBODY().getLST().get(position);

//                            getLocalPlayApplication(lstBean, position, SPUtils.getString(getActivity(), "phoneNum", ""), new SimpleDateFormat
//                                    ("yyyyMMddHHmmssSSS").format(new Date()), SPUtils.getString(getActivity(), "TOKEN", ""));

                            Intent intent = new Intent();
                            intent.setClass(getActivity(), MyPlayActivity.class);
                            Bundle bundle = new Bundle();
//                            responselist为空，lstbean不能用
                            bundle.putParcelable("recommandlistbean",lstBean);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            ToastUtil.showToast(getActivity(), "当前点击的是:" + position);
                        }
                    });


                } else {
                    ToastUtil.showToast(getActivity(), "shibai1");
                }
            }

            @Override
            public void onFailure(Call<DiscoveryListResultBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "shibai2");
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
                if(response.body().BODY != null && response.body().BODY.LST != null) {
                    lst.clear();
                    lst.addAll(response.body().BODY.LST);
                    mGAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SuperModel<DiscoveryGridItemBean>> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "错误");
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case REQUEST_CODE_LOGIN:
//                Bundle b = data.getExtras();
////                mLogintokenToMain = b.getString("logintoken");
//                break;
//            default:
//                break;
//        }
//
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.et_home_title) {

        } else if(v.getId() == R.id.tv_gridview_title) {
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
}

