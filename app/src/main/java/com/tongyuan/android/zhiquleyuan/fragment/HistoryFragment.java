package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.HistorySwipeAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryResultBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.swipe.refreshlib.AbListView;
import com.tongyuan.android.zhiquleyuan.swipe.refreshlib.library.PullToRefreshBase;
import com.tongyuan.android.zhiquleyuan.swipe.refreshlib.library.PullToRefreshScrollView;
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
 * Created by android on 2016/12/3.
 */

public class HistoryFragment extends BaseFragment {

    private AbListView lv_fragment_history;
    private ImageView iv_item_history_pic;
    private Response<CallHistoryResultBean> mResponseCallHist;
    private final String TAG = "444444";
    private String mToyId;
    private String mPhoneNum;
    private String mTime;
    private int start = 0; // 当前页数
    private int limit = 8; // 为每页显示数据数目
    private int totalCount = 50;
    private View mItem_history_header;
    private PullToRefreshScrollView mPulltorefreshscrollview;
    private Handler mHandler;
    private HistorySwipeAdapter mHistorySwipeAdapter;
    private ArrayList<CallHistoryResultBean.BODYBean.LSTBean> mLst;
    private PullToRefreshBase.Mode mCurrentMode;
    private List<String> listData = new ArrayList<>();
    private List<String> listDataMore = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View historyRoot = inflater.inflate(R.layout.fragment_history, null);
        lv_fragment_history = (AbListView) historyRoot.findViewById(R.id.lv_fragment_history);
        iv_item_history_pic = (ImageView) lv_fragment_history.findViewById(R.id.iv_item_history_pic);
        mItem_history_header = inflater.inflate(R.layout.item_history_header, null);
//        mPulltorefreshscrollview = (PullToRefreshScrollView) historyRoot.findViewById(R.id.pulltorefreshscrollview);


        return historyRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();

//        showData();
    }


    private void initData() {
        String token = SPUtils.getString(getActivity(), "TOKEN", "");
        mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        mTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        getData(mPhoneNum, mTime, token);
    }


    private void getData(String phoneNum, String time, String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);

        CallHistoryRequestBean.BODYBean callHistoryBody = new CallHistoryRequestBean.BODYBean("", "10", "1");

        CallHistoryRequestBean callHistoryRequestBean = new CallHistoryRequestBean("REQ", "MTALK", phoneNum, time, callHistoryBody, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(callHistoryRequestBean);
        Call<CallHistoryResultBean> callHistoryResult = allInterface.getCallHistoryResult(s);
        callHistoryResult.enqueue(new Callback<CallHistoryResultBean>() {
            @Override
            public void onResponse(Call<CallHistoryResultBean> call, Response<CallHistoryResultBean> response) {
                if (response != null) {
                    //给recyclerview设置adapter数据,展示list
                    mResponseCallHist = response;
                    mLst = (ArrayList<CallHistoryResultBean.BODYBean.LSTBean>) response.body()
                            .getBODY().getLST();
                    //展示数据
                    HistorySwipeAdapter historySwipeAdapter = new HistorySwipeAdapter(getContext(), mLst);
                    lv_fragment_history.setAdapter(historySwipeAdapter);
                    lv_fragment_history.addHeaderView(mItem_history_header);
                    lv_fragment_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ToastUtil.showToast(getContext(), "position" + position);
                        }
                    });


                    /**
                     *从这往下是不带侧滑的listview
                     */
//                    //放玩具头像
//                    final ArrayList<String> listImg = new ArrayList<String>();
//                    for (int i = 0; i < lst.size() - 1; i++) {
//                        String toyimg = lst.get(i).getTOYIMG();
//                        listImg.add(toyimg);
//                    }
//                    Log.d("ddd", "onResponse: " + listImg);
//                    //放玩具Id
//                    ArrayList<String> listId = new ArrayList<String>();
//                    for (int i = 0; i < lst.size() - 1; i++) {
//                        String toyid = lst.get(i).getTOYID();
//                        listId.add(toyid);
//                    }
//                    //放通话日期
////                    ArrayList<String> listDura = new ArrayList<String>();
////                    for (int i = 0; i < lst.size() - 1; i++) {
////                        String begintime = lst.get(i).getBEGINTIME();
////                        listDura.add(begintime);
////                    }
////                    Log.d("ddd", "onResponse: " + listDura);
//
//                    //放通话时长


                    //展示数据
//                    final RAdapter mRAdapter = new RAdapter(getContext(), R.layout.item_content, listImg, listId);
//                    lv_fragment_history.setAdapter(mRAdapter);
//                    lv_fragment_history.addHeaderView(mItem_history_header);
//                    mItem_history_header.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            FragmentManager fragmentManager = getFragmentManager();
//                            FragmentTransaction transaction = fragmentManager.beginTransaction();
//                            transaction.replace(R.id.fl_fragmentcontainer, new VideoFragment());
//                            transaction.commit();
//                        }
//                    });
//                    lv_fragment_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                        }
//                    });
//                    lv_fragment_history.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                        @Override
//                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                            builder.setMessage("确认删除吗");
//                            builder.setTitle("提示");
//                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    listImg.remove(position);
//
//                                    mRAdapter.notifyDataSetChanged();
//                                    ToastUtil.showToast(getActivity(), "删除成功");
//                                    dialog.dismiss();
//
//
//                                }
//                            });
//                            builder.create().show();
//                            return true;
//                        }
//                    });


                } else {
                    Log.d("ddd", "response: 为空");
                }


                Log.i("hhhhhh", "onResponse:+response " + response.body().toString());

            }

            @Override
            public void onFailure(Call<CallHistoryResultBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "失败了不能联网");
            }
        });
    }

    private void showData1() {
        List<CallHistoryResultBean.BODYBean.LSTBean> lst = mResponseCallHist.body().getBODY().getLST();
        for (int i = 0; i < lst.size() - 1; i++) {
            CallHistoryResultBean.BODYBean.LSTBean lstBean = lst.get(i);
            String toyid = lstBean.getTOYID();
            Log.i("hhhhhh", "showData: ++++" + toyid);
        }


    }


}
