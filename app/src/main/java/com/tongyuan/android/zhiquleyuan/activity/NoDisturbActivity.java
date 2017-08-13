package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.NewAdapter;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.GetTimeUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.TimeUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.view.wheelview.OnWheelChangedListener;
import com.tongyuan.android.zhiquleyuan.view.wheelview.WheelView;
import com.tongyuan.android.zhiquleyuan.view.wheelview.adapter.ArrayWheelAdapter;
import com.tongyuan.android.zhiquleyuan.view.wheelview.adapter.NumericWheelAdapter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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

/**
 * Created by android on 2017/3/12.
 */
public class NoDisturbActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_add_nodisturbtime)
    ImageView mAddNodisturb;
    @BindView(R.id.ll_parent)
    LinearLayout mLayout_parent;

    private String ymdData[] = new String[720];
    private PopupWindow popupWindow;

    private WheelView wl_ymd;
    private WheelView wl_startMin;
    private WheelView wl_hour;
    private WheelView wl_min;

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
    private NewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodisturb);
        initData();
        ButterKnife.bind(this);
        getNoDisturbTime();
    }


    private void initData() {
        long currentTime = System.currentTimeMillis();
        long day = 24 * 60 * 60 * 1000;
        long lastYear = currentTime - day * 360;
        for (int i = 0; i < 720; i++) {
            long time = lastYear + day * i;
            ymdData[i] = GetTimeUtil.getYMDTime(time);
        }
        for (int i = 0; i < xiaoshi_start.length; i++) {

        }
        for (int i = 0; i < fenzhong_start.length; i++) {

        }
    }

    private void getNoDisturbTime() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        NodisturbTimeReqBean.BODYBean bodyBean = new NodisturbTimeReqBean.BODYBean(ToySelectorFragment.mToyId, "10", "1");
        NodisturbTimeReqBean nodisturbTimeReqBean = new NodisturbTimeReqBean("REQ", "QTOYSP", SPUtils.getString(this, "phoneNum", ""), new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(this, "TOKEN", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(nodisturbTimeReqBean);
        Call<NodisturbTimeResBean> nodisturbTimeResBeanCall = allInterface.NODISTURB_TIME_RES_BEAN_CALL(s);
        nodisturbTimeResBeanCall.enqueue(new Callback<NodisturbTimeResBean>() {
            @Override
            public void onResponse(Call<NodisturbTimeResBean> call, Response<NodisturbTimeResBean> response) {
                List<NodisturbTimeResBean.BODYBean.LSTBean> lst = response.body().getBODY().getLST();
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                NoDisturbAdapter adapter = new NoDisturbAdapter(getApplicationContext(), lst);
                mAdapter = new NewAdapter(getApplicationContext(), R.layout.disturb_recycler_troggle, lst);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        showPop();
                        ToastUtil.showToast(getApplication(), "点击了" + position);

                    }
                });
                mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                        ToastUtil.showToast(getApplicationContext(), "长按了" + position);
                        return true;
                    }
                });


            }

            @Override
            public void onFailure(Call<NodisturbTimeResBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), t.toString());
            }
        });
    }

    @OnClick({R.id.iv_add_nodisturbtime})
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.switch_button:
//                ToastUtil.showToast(this, "34");
//                break;
            case R.id.iv_add_nodisturbtime:
                showPop();
                ToastUtil.showToast(this, "56");
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

    private void initWheelView(View popupWindowView) {
        Calendar c = Calendar.getInstance();
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
                if(selectStartMinute == 59) {
                    if(newValue == 23) {
                        newValue = 0;
                    } else {
                        newValue++;
                    }
                }
                endHourAdapter = new NumericWheelAdapter(NoDisturbActivity.this, newValue, 23);
                endHourAdapter.setLabel("");
                wl_hour.setViewAdapter(endHourAdapter);

                wl_hour.setCurrentItem(0);
                if(endHourAdapter.getItemsCount() == 1) {
                    wl_hour.setCyclic(false);// 可循环滚动
                } else {
                    wl_hour.setCyclic(true);// 可循环滚动
                }
            }
        });

        BaseViewHolder baseViewHolder = mAdapter.returnView();

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
                if(endHourAdapter.getItemsCount() == 1) {
                    wl_hour.setCyclic(false);// 可循环滚动
                } else {
                    wl_hour.setCyclic(true);// 可循环滚动
                }
            }
        });

        int selectStartHour = wl_ymd.getCurrentItem();
        int selectStartMinute = wl_startMin.getCurrentItem();
        int minEndHour = selectStartHour;
        if(selectStartMinute == 59) {
            if(selectStartHour == 23) {
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
        if(endHourAdapter.getItemsCount() == 1) {
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


        String currenthh = new SimpleDateFormat("HH").format(c.getTime());
        List<String> asList = Arrays.asList(xiaoshi_start);
        int hour_index = asList.indexOf(currenthh);
        wl_hour.setCurrentItem(hour_index);
        baseViewHolder.setText(R.id.tv_nodisturb_start, currenthh);


        String currentmm = new SimpleDateFormat("mm").format(c.getTime());
        List<String> asList2 = Arrays.asList(fenzhong_start);
        int min_index = asList2.indexOf(currentmm);
        wl_min.setCurrentItem(min_index);
        baseViewHolder.setText(R.id.tv_nodisturb_end, currentmm);

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
}
