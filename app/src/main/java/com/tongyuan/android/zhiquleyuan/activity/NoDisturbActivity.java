package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.NoDisturbAdapter;
import com.tongyuan.android.zhiquleyuan.bean.DeleteNodisturbTimeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteNodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.bean.SetNodisturbTimeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.SetNodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.GetTimeUtil;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.MySwipeRefreshLayout;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenu;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenuCreator;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenuItem;
import com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView.SwipeMenuListView;
import com.tongyuan.android.zhiquleyuan.view.wheelview.OnWheelChangedListener;
import com.tongyuan.android.zhiquleyuan.view.wheelview.WheelView;
import com.tongyuan.android.zhiquleyuan.view.wheelview.adapter.NumericWheelAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment.mToyId;


/**
 * Created by android on 2017/3/12.
 */
public class NoDisturbActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = NoDisturbActivity.class.getSimpleName();
    @BindView(R.id.sp_nodisturb)
    MySwipeRefreshLayout mMySwipeRefreshLayout;
    @BindView(R.id.swipelistview_nodisturb)
    SwipeMenuListView mSwipeMenuListView;
    @BindView(R.id.iv_add_nodisturbtime)
    ImageView mAddNodisturb;
    @BindView(R.id.ll_parent)
    LinearLayout mLayout_parent;
    @BindView(R.id.iv_activity_nodisturb_back)
    LinearLayout nodisturb_back;

    private String ymdData[] = new String[720];
    private PopupWindow popupWindow;

    private WheelView wl_ymd;
    private WheelView wl_startMin;
    private WheelView wl_hour;
    private WheelView wl_min;
    private String mEnd_min;
    private String mEnd_hour;
    private String mStart_min;
    private String mStart_hour;
    public static String pstate = "0";
    private static int CLICK_ACTION = 1;
    private int selectPosition = -2;

    String[] week_str =
            {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    String[] xiaoshi_start =
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                    "19", "20", "21", "22", "23"};

    String[] fenzhong_start =
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                    "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
                    "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54",
                    "55", "56", "57", "58", "59"};
    String lastweek = "周一";
    private NoDisturbAdapter mAdapter;
    private TextView mOk;
    private TextView mCancle;
    private String mToken;
    private String NC = "-1";
    private int currentPage = 1;
    private View footerView;
    private List<NodisturbTimeResBean.BODYBean.LSTBean> nodisturblist = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodisturb);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();

    }

    private void initView() {
//        footerView = LayoutInflater.from(this).inflate(R.layout.discovery_sub_item_foot, null);
//        footerView.setVisibility(View.GONE);
//        mSwipeMenuListView.addFooterView(footerView);

        inSwipeMenuListViewContent();
    }

    private void inSwipeMenuListViewContent() {
        SwipeMenuCreator mCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(NoDisturbActivity.this);
                deleteItem.setTitle("删除");
                deleteItem.setBackground(R.color.redFont);
                deleteItem.setWidth(dp2px(70));
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        mSwipeMenuListView.setMenuCreator(mCreator);
        mSwipeMenuListView.setSmoothScrollbarEnabled(true);
        mSwipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        mSwipeMenuListView.setOpenInterpolator(new AccelerateInterpolator());
        mSwipeMenuListView.setCloseInterpolator(new AccelerateInterpolator());
    }

    private void initListener() {

        mMySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
//                NC = "-1";
                getNoDisturbTime(false);
                mMySwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CLICK_ACTION = 2;
                selectPosition = position;
                showPop();
            }
        });
        mSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                deleteNodisturbTime(nodisturblist, position);
                nodisturblist.remove(position);
                mAdapter.notifyDataSetChanged();
//                ToastUtil.showToast(getApplicationContext(), "点击删除");
                return false;
            }
        });
//        mSwipeMenuListView.setOnScrollListener(this);
    }

    private void initData() {
        mToken = SPUtils.getString(this, "token", "");
        long currentTime = System.currentTimeMillis();
        long day = 24 * 60 * 60 * 1000;
        long lastYear = currentTime - day * 360;
        for (int i = 0; i < 720; i++) {
            long time = lastYear + day * i;
            ymdData[i] = GetTimeUtil.getYMDTime(time);
        }

        getNoDisturbTime(false);
    }

    private void getNoDisturbTime(final boolean isLoadMore) {
//        int page = currentPage;
//        if (isLoadMore) {
//            page++;
//        }
//        if (!NC.equals("0")) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllInterface allInterface = retrofit.create(AllInterface.class);
//            NodisturbTimeReqBean.BODYBean bodyBean = new NodisturbTimeReqBean.BODYBean(mToyId, "10", String.valueOf(page));
        NodisturbTimeReqBean.BODYBean bodyBean = new NodisturbTimeReqBean.BODYBean(mToyId, "", "");
        NodisturbTimeReqBean nodisturbTimeReqBean = new NodisturbTimeReqBean("REQ", "QTOYSP", SPUtils.getString(this, "phoneNum", ""), new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(this, "token", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(nodisturbTimeReqBean);
        Call<NodisturbTimeResBean> nodisturbTimeResBeanCall = allInterface.NODISTURB_TIME_RES_BEAN_CALL(s);
        nodisturbTimeResBeanCall.enqueue(new Callback<NodisturbTimeResBean>() {
            @Override
            public void onResponse(Call<NodisturbTimeResBean> call, Response<NodisturbTimeResBean> response) {
                if (response.body() != null && response.body().getCODE().equals("0")) {
                    mAdapter = new NoDisturbAdapter(NoDisturbActivity.this, nodisturblist);
                    mSwipeMenuListView.setAdapter(mAdapter);
                    NC = response.body().getBODY().getNC();
                    List<NodisturbTimeResBean.BODYBean.LSTBean> lst = response.body().getBODY().getLST();
                    LogUtil.i("1212321", "onResponse: " + lst.toString());
                    LogUtil.i("1212321", "onResponse: " + response.body().getTOKEN().toString());
                    if (isLoadMore) {
                        currentPage++;
                    } else {
                        nodisturblist.clear();
                        currentPage = 1;
                    }
                    nodisturblist.addAll(lst);
//                        if (NC.equals("0")) {
//                            footerView.setVisibility(View.GONE);
//                        } else {
//                            footerView.setVisibility(View.VISIBLE);
//                        }
                    //mSwipeMenuListView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    //mSwipeMenuListView.scrollBy(1,1);
                    LogUtil.d("isLoading", "notifyData...");
                } else if (response.body().getCODE().equals("-10006")) {
                    SPUtils.putString(getApplicationContext(), "token", "");
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                } else {
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
//                        footerView.setVisibility(View.GONE);
                }
//                    isLoading = false;
//                    LogUtil.d("isLoading", "isLoading = false...");

            }

            @Override
            public void onFailure(Call<NodisturbTimeResBean> call, Throwable t) {
//                    isLoading = false;
                LogUtil.i(TAG, "querynodisturbList:failure" + t.toString());
                ToastUtil.showToast(getApplicationContext(), R.string.network_error);
            }
        });

//        }

    }

    private void deleteNodisturbTime(List<NodisturbTimeResBean.BODYBean.LSTBean> lst, final int position) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        DeleteNodisturbTimeReqBean.BODYBean.LSTBean lstBean = new DeleteNodisturbTimeReqBean.BODYBean.LSTBean(lst.get(position).getID());
        List list = new ArrayList();
        list.add(lstBean);
        DeleteNodisturbTimeReqBean.BODYBean bodyBean = new DeleteNodisturbTimeReqBean.BODYBean(list);
        DeleteNodisturbTimeReqBean deleteNodisturbTimeReqBean = new DeleteNodisturbTimeReqBean("REQ", "DTOYSP", "", new SimpleDateFormat
                ("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(deleteNodisturbTimeReqBean);
        Call<DeleteNodisturbTimeResBean> deleteNodisturbTimeResBeanCall = allInterface.DELETE_NODISTURB_TIME_RES_BEAN_CALL(s);
        deleteNodisturbTimeResBeanCall.enqueue(new Callback<DeleteNodisturbTimeResBean>() {
            @Override
            public void onResponse(Call<DeleteNodisturbTimeResBean> call, Response<DeleteNodisturbTimeResBean> response) {
                if (response.body().getCODE().equals("0")) {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DeleteNodisturbTimeResBean> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.iv_add_nodisturbtime, R.id.iv_activity_nodisturb_back, R.id.tv_activity_nodisturb_complete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_nodisturbtime:
                CLICK_ACTION = 1;
                showPop();
                break;
            case R.id.iv_activity_nodisturb_back:
                finish();
                break;
            case R.id.tv_activity_nodisturb_complete:
                finish();
                break;
            default:
                break;
        }
    }

    private void showPop() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupWindowView = inflater.inflate(R.layout.pop_item, null);
        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        initWheelView(popupWindowView);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                makeWindowLight();
            }
        });
        popupWindow.showAtLocation(mLayout_parent, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
    }

    private NumericWheelAdapter startHourAdapter;
    private NumericWheelAdapter startMinuteAdapter;
    private NumericWheelAdapter endHourAdapter;
    private NumericWheelAdapter endMinuteAdapter;

    private void initWheelView(final View popupWindowView) {
        Calendar c = Calendar.getInstance();
        mOk = (TextView) popupWindowView.findViewById(R.id.tv_ok);
        mCancle = (TextView) popupWindowView.findViewById(R.id.tv_cancle);

        wl_ymd = (WheelView) popupWindowView.findViewById(R.id.wl_ymd);
        wl_startMin = (WheelView) popupWindowView.findViewById(R.id.wl_week);
        wl_hour = (WheelView) popupWindowView.findViewById(R.id.wl_hour);
        wl_min = (WheelView) popupWindowView.findViewById(R.id.wl_min);

        startHourAdapter = new NumericWheelAdapter(this, 0, 23);
        wl_ymd.setViewAdapter(startHourAdapter);
        startHourAdapter.setTextSize(18);
        startHourAdapter.setLabel("");
        wl_ymd.setCyclic(true);
        Calendar calendar = Calendar.getInstance();
        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        wl_ymd.setCurrentItem(currHour);

        wl_ymd.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int selectStartMinute = wl_startMin.getCurrentItem();
                if (selectStartMinute == 59) {
                    if (newValue == 23) {
                        newValue = 0;
                    } else {
                        newValue++;
                    }
                }

                endHourAdapter = new NumericWheelAdapter(NoDisturbActivity.this, newValue, 23);
                endHourAdapter.setLabel("");
                wl_hour.setViewAdapter(endHourAdapter);
                wl_hour.setCurrentItem(0);
                if (endHourAdapter.getItemsCount() == 1) {
                    wl_hour.setCyclic(false);// 可循环滚动
                } else {
                    wl_hour.setCyclic(true);// 可循环滚动
                }
            }
        });

        startMinuteAdapter = new NumericWheelAdapter(this, 0, 59);
        wl_startMin.setViewAdapter(startMinuteAdapter);
        startMinuteAdapter.setTextSize(18);
        startMinuteAdapter.setLabel("");
        int currMinute = calendar.get(Calendar.MINUTE);
        wl_startMin.setCurrentItem(currMinute);
        wl_startMin.setCyclic(true);

        wl_startMin.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int selectStartHour = wl_ymd.getCurrentItem();
                int endHour = selectStartHour;
                if (newValue == 59) {
                    if (endHour == 23) {
                        endHour = 0;
                    } else {
                        endHour++;
                    }
                }

                endHourAdapter = new NumericWheelAdapter(NoDisturbActivity.this, endHour, 23);
                wl_hour.setViewAdapter(endHourAdapter);
                endHourAdapter.setLabel("");

                int selectStarMin = wl_startMin.getCurrentItem();
                int endMin = wl_min.getCurrentItem();
                if (selectStarMin > endMin) {
                    endMin = selectStarMin;
                    wl_min.setCurrentItem(endMin);

                }

            }

        });

        wl_min.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int startMin = wl_startMin.getCurrentItem();
                int selelctEndMin = wl_min.getCurrentItem();
                if (selelctEndMin < startMin) {
                    selelctEndMin = startMin;
                    wl_min.setCurrentItem(selelctEndMin);

                }

                if (endHourAdapter.getItemsCount() == 1) {
                    wl_hour.setCyclic(false);// 可循环滚动
                } else {
                    wl_hour.setCyclic(true);// 可循环滚动
                }
            }
        });

        int selectStartHour = wl_ymd.getCurrentItem();
        int selectStartMinute = wl_startMin.getCurrentItem();
        int minEndHour = selectStartHour;
        if (selectStartMinute == 59) {
            if (selectStartHour == 23) {
                minEndHour = 0;
            } else {
                minEndHour++;
            }
        }

        endHourAdapter = new NumericWheelAdapter(this, minEndHour, 23);
        endHourAdapter.setLabel("");
        endHourAdapter.setTextSize(18);
        wl_hour.setViewAdapter(endHourAdapter);
        wl_hour.setCurrentItem(0);

        if (endHourAdapter.getItemsCount() == 1) {
            wl_hour.setCyclic(false);// 可循环滚动
        } else {
            wl_hour.setCyclic(true);// 可循环滚动
        }

        endMinuteAdapter = new NumericWheelAdapter(this, 0, 59);
        endMinuteAdapter.setLabel("");
        endMinuteAdapter.setTextSize(18);
        wl_min.setViewAdapter(endMinuteAdapter);
        wl_min.setCurrentItem(currMinute);
        wl_min.setCyclic(true);// 可循环滚动
        mOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int startHour = wl_ymd.getCurrentItem();
                int startMin = wl_startMin.getCurrentItem();
                int endHour = wl_hour.getCurrentItem();
                int endMin = wl_min.getCurrentItem();

                mStart_hour = startHourAdapter.getItemText(startHour).toString();
                mStart_min = startMinuteAdapter.getItemText(startMin).toString();
                mEnd_hour = endHourAdapter.getItemText(endHour).toString();
                mEnd_min = endMinuteAdapter.getItemText(endMin).toString();

                if (Integer.parseInt(mStart_hour) < 10) {
                    mStart_hour = "0" + mStart_hour;
                }
                if (Integer.parseInt(mStart_min) < 10) {
                    mStart_min = "0" + mStart_min;
                }
                if (Integer.parseInt(mEnd_hour) < 10) {
                    mEnd_hour = "0" + mEnd_hour;
                }
                if (Integer.parseInt(mEnd_min) < 10) {
                    mEnd_min = "0" + mEnd_min;
                }

                String beginTime = mStart_hour + mStart_min;
                String endTime = mEnd_hour + mEnd_min;

                sendTimeToServer(beginTime, endTime);

            }
        });

        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
//                ToastUtil.showToast(getApplicationContext(), "cancle");
            }
        });


    }

    private void sendTimeToServer(String beginTime, String endTime) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        List list = new ArrayList();
        if (CLICK_ACTION == 1) {
            SetNodisturbTimeReqBean.BODYBean.LSTBean lstBean = new SetNodisturbTimeReqBean.BODYBean.LSTBean("", ToySelectorFragment.mToyId,
                    ToySelectorFragment.mToyId, beginTime + "00", endTime + "00", "1");
            list.add(lstBean);
        } else {
            SetNodisturbTimeReqBean.BODYBean.LSTBean lstBean = new SetNodisturbTimeReqBean.BODYBean.LSTBean(nodisturblist.get(selectPosition).getID
                    (), mToyId, mToyId, beginTime + "00", endTime + "00", "1");
            list.add(lstBean);
        }
        SetNodisturbTimeReqBean.BODYBean bodyBean = new SetNodisturbTimeReqBean.BODYBean(list);
        SetNodisturbTimeReqBean setNodisturbTimeReqBean = new SetNodisturbTimeReqBean("REQ", "TOYSP", "", new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()), bodyBean, "", mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(setNodisturbTimeReqBean);
        Call<SetNodisturbTimeResBean> setNodisturbTimeResBeanCall = allInterface.SET_NODISTURB_TIME_RES_BEAN_CALL(s);
        setNodisturbTimeResBeanCall.enqueue(new Callback<SetNodisturbTimeResBean>() {
            @Override
            public void onResponse(Call<SetNodisturbTimeResBean> call, Response<SetNodisturbTimeResBean> response) {
                if (response.body().getCODE().equals("0")) {
                    getNoDisturbTime(false);
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SetNodisturbTimeResBean> call, Throwable t) {

            }
        });
    }

    /*private void changeWheelWeek(int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        wl_startMin.setCurrentItem(i - 1);
        lastweek = week_str[i - 1];

    }*/

    private void makeWindowLight() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

//    private int totalItemCount;
//    private int lastItem;
//    private boolean isLoading = false;
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        LogUtil.d("isLoading", "onScrollStateChanged isLoading= "+isLoading + " lastItem == totalItemCount " + (lastItem == totalItemCount) + " " +scrollState);
//        if (!isLoading && lastItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
//            //显示加载更多
//            isLoading = true;
//            getNoDisturbTime(true);
//        }
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        lastItem = firstVisibleItem + visibleItemCount;//可见的item的数量,
//        this.totalItemCount = totalItemCount;
//    }
}
