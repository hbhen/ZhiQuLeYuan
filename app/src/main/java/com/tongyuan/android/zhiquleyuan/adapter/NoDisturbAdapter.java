package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.NoDisturbActivity;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.bean.SetNodisturbTimeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.SetNodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.holder.NodisturbHolder;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android on 2017/6/22.
 */

public class NoDisturbAdapter extends BaseAdapter {
    private final String TAG = "ADASTA";

    private final static int type_troggle = 1;
    private final static int type_date = 2;
    private LayoutInflater mInflater;
    private List<NodisturbTimeResBean.BODYBean.LSTBean> mLSTBeen;
    private Context mContext;
    private Map<Integer, Boolean> mMap = new HashMap<>();

    public NoDisturbAdapter(Context context, List<NodisturbTimeResBean.BODYBean.LSTBean> lst) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLSTBeen = lst;

    }

    @Override
    public int getCount() {
        return mLSTBeen.size();
    }

    @Override
    public Object getItem(int position) {
        String pstate = mLSTBeen.get(position).getPSTATE();
        Log.i("myaxxx", "position=  " + position + " : patae = " + pstate);
        Log.i("myaxxx", " 1的状态 : " + position + " : " + mLSTBeen.get(1).getPSTATE());
        Log.i("myaxxx", " 9的状态 : " + position + " : " + mLSTBeen.get(9).getPSTATE());

        return mLSTBeen.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int index = position;
        LogUtil.i(TAG, "pstate 的值是(before) : " + NoDisturbActivity.pstate);
        NodisturbHolder mNodisturbHolder = null;
        if (convertView == null) {
            mNodisturbHolder = new NodisturbHolder();
            convertView = mInflater.inflate(R.layout.disturb_recycler_troggle, parent, false);
            mNodisturbHolder.mTv_nodisturb_start = (TextView) convertView.findViewById(R.id.tv_nodisturb_start);
            mNodisturbHolder.mTv_nodisturb_end = (TextView) convertView.findViewById(R.id.tv_nodisturb_end);
//            mNodisturbHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.switch_button);
            mNodisturbHolder.mSwitch_button = (CheckBox) convertView.findViewById(R.id.switch_button_nodisturb);
            convertView.setTag(mNodisturbHolder);
        } else {
            mNodisturbHolder = (NodisturbHolder) convertView.getTag();
        }
        final String btime = mLSTBeen.get(position).getBTIME();
        String beginSubString = btime.substring(0, 4);
        final String etime = mLSTBeen.get(position).getETIME();
        String endSubString = etime.substring(0, 4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        mNodisturbHolder.mSwitch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mLSTBeen.get(position).setPSTATE(isChecked ? "1" : "0");
                sendTimeSwitchButtonToServer(mLSTBeen.get(position).getPSTATE(), mLSTBeen.get(position).getID(), btime, etime);
            }
        });
//        这是初始化checkbox的选中状态!
        final String pstate = mLSTBeen.get(position).getPSTATE();
        mNodisturbHolder.mSwitch_button.setChecked(pstate.equals("1") ? true : false);


        try {
            Date beginParse = simpleDateFormat.parse(beginSubString);
            String beginFormat = simpleDateFormat1.format(beginParse);
            Date endParse = simpleDateFormat.parse(endSubString);
            String endFormat = simpleDateFormat1.format(endParse);
            mNodisturbHolder.mTv_nodisturb_start.setText("(" + position + ")" + beginFormat);
            mNodisturbHolder.mTv_nodisturb_end.setText("-" + endFormat);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertView;

    }

    private void sendTimeSwitchButtonToServer(String isChecked, String id, String btime, String etime) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        LogUtil.i(TAG, "pstate 的值是(before) : " + NoDisturbActivity.pstate);
        LogUtil.i(TAG, "pstate 的值是(before) : " + isChecked);
        final SetNodisturbTimeReqBean.BODYBean.LSTBean lstBean = new SetNodisturbTimeReqBean.BODYBean.LSTBean(id, ToySelectorFragment.mToyId, ToySelectorFragment.mToyId, btime, etime, isChecked);
        LogUtil.i(TAG, "pstate 的值是(after) : " + NoDisturbActivity.pstate);
        LogUtil.i(TAG, "pstate 的值是(after) : " + isChecked);
        final List list = new ArrayList();
        list.add(lstBean);
        SetNodisturbTimeReqBean.BODYBean bodyBean = new SetNodisturbTimeReqBean.BODYBean(list);
        SetNodisturbTimeReqBean setNodisturbTimeReqBean = new SetNodisturbTimeReqBean("REQ", "TOYSP", "", new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()), bodyBean, "", SPUtils.getString(mContext, "token", ""), NoDisturbActivity.pstate);
        Gson gson = new Gson();
        String s = gson.toJson(setNodisturbTimeReqBean);
        Call<SetNodisturbTimeResBean> setNodisturbTimeResBeanCall = allInterface.SET_NODISTURB_TIME_RES_BEAN_CALL(s);
        setNodisturbTimeResBeanCall.enqueue(new Callback<SetNodisturbTimeResBean>() {
            @Override
            public void onResponse(Call<SetNodisturbTimeResBean> call, Response<SetNodisturbTimeResBean> response) {
                String s1 = response.body().toString();
                String s2 = response.body().getBODY().getLST().toString();
            }

            @Override
            public void onFailure(Call<SetNodisturbTimeResBean> call, Throwable t) {

            }
        });
    }
}
