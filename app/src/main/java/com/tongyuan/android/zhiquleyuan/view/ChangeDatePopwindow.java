package com.tongyuan.android.zhiquleyuan.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.view.wheelview.OnWheelChangedListener;
import com.tongyuan.android.zhiquleyuan.view.wheelview.OnWheelScrollListener;
import com.tongyuan.android.zhiquleyuan.view.wheelview.WheelView;
import com.tongyuan.android.zhiquleyuan.view.wheelview.adapter.AbstractWheelTextAdapter1;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by android on 2017/1/6.
 */
public class ChangeDatePopwindow extends PopupWindow implements View.OnClickListener {


    private Context mContext;
    private boolean issetdata = false;
    private ArrayList<String> arry_years = new ArrayList<String>();
    private ArrayList<String> arry_months = new ArrayList<String>();
    private ArrayList<String> arry_days = new ArrayList<String>();

    private CalendarTextAdapter mYearAdapter;
    private CalendarTextAdapter mMonthAdapter;
    private CalendarTextAdapter mDaydapter;

    private String month;
    private String day;

    private String currentYear = getYear();
    private String currentMonth = getMonth();
    private String currentDay = getDay();

    private int maxTextSize = 24;
    private int minTextSize = 14;

    private String selectYear;
    private String selectMonth;
    private String selectDay;

    private OnBirthListener onBirthListener;
    private final TextView mBtnMyinfoCancel;
    private final TextView mBtnMyinfoSure;
    private final WheelView mMWvBirthYear;
    private final WheelView mMWvBirthMonth;
    private final WheelView mMWvBirthDay;

    /*
    *  @Bind(R.id.btn_myinfo_cancel)
        TextView mBtnMyinfoCancel;

        @Bind(R.id.btn_myinfo_sure)
        TextView mBtnMyinfoSure;

        @Bind(R.id.wv_birth_year)
        com.tongyuan.android.zhiquleyuan.view.wheelview.WheelView mWvBirthYear;

        @Bind(R.id.wv_birth_month)
        com.tongyuan.android.zhiquleyuan.view.wheelview.WheelView mWvBirthMonth;

        @Bind(R.id.wv_birth_day)
        com.tongyuan.android.zhiquleyuan.view.wheelview.WheelView mWvBirthDay;

        @Bind(R.id.ly_myinfo_changeaddress_child)
        LinearLayout mLyMyinfoChangeaddressChild;

        @Bind(R.id.ly_myinfo_changeaddress)
        LinearLayout mLyMyinfoChangeaddress;
    *
    * */
    public ChangeDatePopwindow(Context context) {
        super(context);
        this.mContext = context;
        View view = View.inflate(context, R.layout.dialog_myinfo_changebirth, null);
        mBtnMyinfoCancel = (TextView) view.findViewById(R.id.btn_myinfo_cancel);
        mBtnMyinfoSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);

        mMWvBirthYear = (WheelView) view.findViewById(R.id.wv_birth_year);
        mMWvBirthMonth = (WheelView) view.findViewById(R.id.wv_birth_month);
        mMWvBirthDay = (WheelView) view.findViewById(R.id.wv_birth_day);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);

        mBtnMyinfoSure.setOnClickListener(this);
        mBtnMyinfoCancel.setOnClickListener(this);
        if (!issetdata) {
            initData();
        }
        initYears();
        mYearAdapter = new CalendarTextAdapter(context, arry_years, setYear(currentYear), maxTextSize, minTextSize);
        mMWvBirthYear.setVisibleItems(5);
        mMWvBirthYear.setViewAdapter(mYearAdapter);
        mMWvBirthYear.setCurrentItem(setYear(currentYear));


        initMonths(Integer.parseInt(month));
        mMonthAdapter = new CalendarTextAdapter(context, arry_months, setMonth(currentMonth), maxTextSize, minTextSize);
        mMWvBirthMonth.setVisibleItems(5);
        mMWvBirthMonth.setViewAdapter(mMonthAdapter);
        mMWvBirthMonth.setCurrentItem(setMonth(currentMonth));

        initDays(Integer.parseInt(day));
        mDaydapter = new CalendarTextAdapter(context, arry_days, Integer.parseInt(currentDay) - 1, maxTextSize, minTextSize);
        mMWvBirthDay.setVisibleItems(5);
        mMWvBirthDay.setViewAdapter(mDaydapter);
        mMWvBirthDay.setCurrentItem(Integer.parseInt(currentDay) - 1);

        //年滑动的监听
        mMWvBirthYear.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                selectYear = currentText;
                LogUtil.i("111", "currentText:----------------- " + currentText);

                setTextviewSize(currentText, mYearAdapter);
                currentYear = currentText.substring(0, currentText.length() - 1).toString();
                LogUtil.i("111", "currentYear========" + currentYear);
                setYear(currentYear);
                initMonths(Integer.parseInt(month));
                mMonthAdapter = new CalendarTextAdapter(mContext, arry_months, 0, maxTextSize, minTextSize);
                mMWvBirthMonth.setVisibleItems(5);
                mMWvBirthMonth.setViewAdapter(mMonthAdapter);
                mMWvBirthMonth.setCurrentItem(0);

                calDays(currentYear, month);
            }
        });
        mMWvBirthYear.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mYearAdapter);
            }
        });

        //月滑动的监听
        mMWvBirthMonth.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                selectMonth = currentText;
                LogUtil.i("111", "selectMonth: " + selectMonth);
                setTextviewSize(currentText, mMonthAdapter);
                setMonth(currentText.substring(0, 1));
                initDays(Integer.parseInt(day));
                mDaydapter = new CalendarTextAdapter(mContext, arry_days, 0, maxTextSize, minTextSize);
                mMWvBirthDay.setVisibleItems(5);
                mMWvBirthDay.setViewAdapter(mDaydapter);
                mMWvBirthDay.setCurrentItem(0);

                calDays(currentYear, month);
            }
        });
        mMWvBirthMonth.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        //日滑动的监听
        mMWvBirthDay.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
                selectDay = currentText;
                LogUtil.i("111", "selectDay: " + selectDay);
            }
        });
        mMWvBirthDay.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
            }
        });
    }

    //设置字体的大小
    private void setTextviewSize(String curriteItemText, CalendarTextAdapter daydapter) {
        ArrayList<View> arrayList = daydapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

    //初始化年
    private void initYears() {
        for (int i = Integer.parseInt(getYear()); i > 1950; i--) {
            arry_years.add(i + "年");
        }

    }

    //初始化月
    public void initMonths(int months) {
        arry_months.clear();
        for (int i = 1; i <= months; i++) {
            if (i < 10) {
                arry_months.add("0" + i + "月");
            } else {
                arry_months.add(i + "月");
            }

        }
    }

    //初始化日
    public void initDays(int days) {
        arry_days.clear();
        for (int i = 1; i <= days; i++) {
            if (i < 10) {
                arry_days.add("0" + i + "日");
            } else {
                arry_days.add(i + "日");
            }

        }
    }


    private class CalendarTextAdapter extends AbstractWheelTextAdapter1 {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }
    }

    public void setBirthdayListener(OnBirthListener onBirthListener) {
        this.onBirthListener = onBirthListener;
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnMyinfoSure) {
            if (onBirthListener != null) {
                onBirthListener.onClick(selectYear, selectMonth, selectDay);
                LogUtil.i("111", "" + selectYear + "" + selectMonth + "" + selectDay);
            }
        } else if (view == mBtnMyinfoSure) {

        } else {
            dismiss();
        }
        dismiss();
    }

    private String getYear() {
        Calendar c = Calendar.getInstance();
        c.get(Calendar.YEAR);
        LogUtil.i("year", "getYear: " + c);
        return c.get(Calendar.YEAR) + "";

    }

    private String getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1 + "";
    }

    private String getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE) + "";
    }

    private void initData() {
        setDate(getYear(), getMonth(), getDay());
        this.currentDay = 1 + "";
        this.currentMonth = 1 + "";
    }


    /**
     * 设置年月日
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(String year, String month, String day) {
        selectYear = year + "年";
        selectMonth = month + "月";
        selectDay = day + "日";
        issetdata = true;
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
        if (year == getYear()) {
            this.month = getMonth();
        } else {
            this.month = 12 + "";
        }
        calDays(year, month);
    }


    /**
     * 设置年份
     *
     * @param year
     */
    public int setYear(String year) {
        int yearIndex = 0;
        if (!year.equals(getYear())) {
            this.month = 12 + "";
        } else {
            this.month = getMonth();
        }
        for (int i = Integer.parseInt(getYear()); i > 1950; i--) {
            if (i == Integer.parseInt(year)) {
                return yearIndex;
            }
            yearIndex++;
        }
        return yearIndex;
    }

    /**
     * 设置月份
     *
     * @param month
     * @param month
     * @return
     */
    public int setMonth(String month) {
        int monthIndex = 0;
        calDays(currentYear, month);
        for (int i = 1; i < Integer.parseInt(this.month); i++) {
            if (Integer.parseInt(month) == i) {
                return monthIndex;
            } else {
                monthIndex++;
            }
        }
        return monthIndex;
    }

    /**
     * 计算每月多少天
     *
     * @param month
     * @param year
     */
    public void calDays(String year, String month) {
        boolean leayyear = false;
        if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        for (int i = 1; i <= 12; i++) {
            switch (Integer.parseInt(month)) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    this.day = 31 + "";
                    break;
                case 2:
                    if (leayyear) {
                        this.day = 29 + "";
                    } else {
                        this.day = 28 + "";
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    this.day = 30 + "";
                    break;
            }
        }
        if (year.equals(getYear()) && month.equals(getMonth())) {
            this.day = getDay();
        }
    }

    public interface OnBirthListener {
        public void onClick(String year, String month, String day);
    }
    public Boolean isBirthdatChanged(){

        return false;
    }
}
