package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suke.widget.SwitchButton;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Android on 2017/8/10.
 */
public class NewAdapter extends BaseQuickAdapter<NodisturbTimeResBean.BODYBean.LSTBean, BaseViewHolder> {
    private Context mContext;
    private List<NodisturbTimeResBean.BODYBean.LSTBean> mList;
    private String mBeginFormat;
    private String mEndFormat;
    private BaseViewHolder mHelper;

    public NewAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<NodisturbTimeResBean.BODYBean.LSTBean> data) {
        super(layoutResId, data);
        this.mContext = context;
        this.mList = data;

    }

    @Override
    protected void convert(BaseViewHolder helper, NodisturbTimeResBean.BODYBean.LSTBean item) {
        this.mHelper = helper;
        com.suke.widget.SwitchButton switchButton = (SwitchButton) helper.itemView.findViewById(R.id.switch_button);
        setTime(helper.getLayoutPosition());
        String pstate = item.getPSTATE();

        if (pstate.equals("0")) {
            switchButton.setChecked(false);
        } else {
            switchButton.setChecked(true);
        }
        helper.setText(R.id.tv_nodisturb_start, mBeginFormat);
        helper.setText(R.id.tv_nodisturb_end, "-" + mEndFormat);
//        helper.setText(R.id.tv_activity_mode, "I hope everyday");

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
