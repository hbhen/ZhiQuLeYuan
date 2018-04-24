package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.suke.widget.SwitchButton;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.NoDisturbActivity;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.bean.SetNodisturbTimeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.SetNodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

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
 * Created by Android on 2017/8/10.
 */
public class NewAdapter extends BaseQuickAdapter<NodisturbTimeResBean.BODYBean.LSTBean, BaseViewHolder> {
    private Context mContext;
    private List<NodisturbTimeResBean.BODYBean.LSTBean> mList;
    private String mBeginFormat;
    private String mEndFormat;
    private BaseViewHolder mHelper;
    private SwitchButton mSwitchButton;

    public NewAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<NodisturbTimeResBean.BODYBean.LSTBean> data) {
        super(layoutResId, data);
        this.mContext = context;
        this.mList = data;

    }

//    @Override
//
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case 0:
//                return onCreateDefViewHolder(parent, viewType);
//            case 1:
//                return onCreateDefViewHolder(parent, viewType);
//            default:
//                return onCreateDefViewHolder(parent, viewType);
//        }
//    }
    @Override
    protected void convert(final BaseViewHolder helper, NodisturbTimeResBean.BODYBean.LSTBean item) {
        this.mHelper = helper;
        mSwitchButton = (SwitchButton) helper.itemView.findViewById(R.id.switch_button);
        setTime(helper.getLayoutPosition());
        String pstate = item.getPSTATE();

        if (pstate.equals("0")) {
            mSwitchButton.setChecked(false);
            NoDisturbActivity.pstate = pstate;

        } else {
            mSwitchButton.setChecked(true);
            NoDisturbActivity.pstate = pstate;
        }
        helper.setText(R.id.tv_nodisturb_start, mBeginFormat);
        helper.setText(R.id.tv_nodisturb_end, "-" + mEndFormat);
        mSwitchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    NoDisturbActivity.pstate = "1";
                    String id = mList.get(helper.getLayoutPosition()).getID();
                    sendTimeSwitchButtonToServer(id, mList.get(helper.getLayoutPosition()).getBTIME(), mList.get(helper.getLayoutPosition())
                            .getETIME());
                } else {
                    NoDisturbActivity.pstate = "0";
                    String id = mList.get(helper.getLayoutPosition()).getID();
                    sendTimeSwitchButtonToServer(id, mList.get(helper.getLayoutPosition()).getBTIME(), mList.get(helper.getLayoutPosition())
                            .getETIME());
                }
            }
        });

//        helper.setText(R.id.tv_activity_mode, "I hope everyday");

    }

    private void sendTimeSwitchButtonToServer(String id, String btime, String etime) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        final SetNodisturbTimeReqBean.BODYBean.LSTBean lstBean = new SetNodisturbTimeReqBean.BODYBean.LSTBean(id, ToySelectorFragment.mToyId,
                ToySelectorFragment.mToyId, btime, etime, NoDisturbActivity.pstate);
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
//                if (response.body().getCODE().equals("0")) {
////                    BaseViewHolder baseViewHolder = mAdapter.returnView();
////                    if (baseViewHolder == null) {
////                        View inflate = LayoutInflater.from(NoDisturbActivity.this).inflate(R.layout.disturb_recycler_troggle, null);
////                        TextView start = (TextView) inflate.findViewById(R.id.tv_nodisturb_start);
////                        TextView end = (TextView) inflate.findViewById(R.id.tv_nodisturb_end);
////                        start.setText(mStart_hour + ":" + mStart_min + "-");
////                        end.setText(mEnd_hour + ":" + mEnd_min);
////                        getNoDisturbTime();
////                        mAdapter.notifyDataSetChanged();
////                        popupWindow.dismiss();
////                    } else {
////                        LogUtil.i("1212321", "onResponse: " + mStart_hour + "_" + mStart_min + "_" + mEnd_hour + "_" + mEnd_min + ":");
//                        baseViewHolder.setText(R.id.tv_nodisturb_start, mStart_hour + ":" + mStart_min + "-");
//                        baseViewHolder.setText(R.id.tv_nodisturb_end, mEnd_hour + ":" + mEnd_min);
////                        ToastUtil.showToast(getApplicationContext(), "ok");
//                        getNoDisturbTime();
//                        mAdapter.notifyDataSetChanged();
//                        popupWindow.dismiss();
////                    }
//
//                }
            }

            @Override
            public void onFailure(Call<SetNodisturbTimeResBean> call, Throwable t) {

            }
        });
    }

    private void setTime(int position) {
        String btime = mList.get(position).getBTIME();
        String beginSubString = btime.substring(0, 4);
        String etime = mList.get(position).getETIME();
        String endSubString = etime.substring(0, 4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        try {
            Date beginParse = simpleDateFormat.parse(beginSubString);
            mBeginFormat = simpleDateFormat1.format(beginParse);

            Date endParse = simpleDateFormat.parse(endSubString);
            mEndFormat = simpleDateFormat1.format(endParse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseViewHolder returnView() {
        return mHelper;
    }


}
