package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android on 2017/6/22.
 */

public class NoDisturbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int type_troggle = 1;
    private final static int type_date = 2;
    private LayoutInflater mInflater;
    private List<NodisturbTimeResBean.BODYBean.LSTBean> mLSTBeen;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    private void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public NoDisturbAdapter(Context context, List<NodisturbTimeResBean.BODYBean.LSTBean> lst) {
        mInflater = LayoutInflater.from(context);
        mLSTBeen = lst;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.disturb_recycler_troggle, null);
        return new ToggleHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        String pstate = mLSTBeen.get(position).getPSTATE();
        if (pstate.equals("0")) {
            ((ToggleHolder) holder).btn.setChecked(false);
        } else {

            ((ToggleHolder) holder).btn.setChecked(true);
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                    ToastUtil.showToast(mContext, "短按");
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    mOnItemClickListener.onItemLongClick(holder.itemView, position);
                    ToastUtil.showToast(mContext, "长按");
                    return false;
                }
            });
        }
        ((ToggleHolder) holder).btn.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked == true) {
//                    //打开开关的逻辑:开启设置免打扰,
                    setNoDisturbTimeOn();
                    ToastUtil.showToast(mContext, "开");

                } else {
                    //关闭开关的逻辑
//                    setNoDisturbTimeOff();
                    ToastUtil.showToast(mContext, "关");
                }
            }
        });

        String btime = mLSTBeen.get(position).getBTIME();
        String beginSubString = btime.substring(0, 4);
        String etime = mLSTBeen.get(position).getETIME();
        String endSubString = etime.substring(0, 4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        try {
            Date beginParse = simpleDateFormat.parse(beginSubString);
            String beginFormat = simpleDateFormat1.format(beginParse);
            Date endParse = simpleDateFormat.parse(endSubString);
            String endFormat = simpleDateFormat1.format(endParse);
            ((ToggleHolder) holder).tv_nodisturb_start.setText(beginFormat );
            ((ToggleHolder) holder).tv_nodisturb_end.setText("-" + endFormat);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int position) {
        if (mLSTBeen == null || mLSTBeen.isEmpty()) {
            return;
        }
        mLSTBeen.remove(position);
        notifyItemRemoved(position);

    }

    private void setNoDisturbTimeOn() {
        //讲设置好的数据发送到服务器 pstate ="1";

    }


    @Override
    public int getItemCount() {
        return mLSTBeen.size();

    }

    public class ToggleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nodisturb_start)
        TextView tv_nodisturb_start;
        @BindView(R.id.tv_nodisturb_end)
        TextView tv_nodisturb_end;
        @BindView(R.id.switch_button)
        SwitchButton btn;

        public ToggleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


}
