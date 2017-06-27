package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.tongyuan.android.zhiquleyuan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android on 2017/6/22.
 */

public class NoDisturbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int type_troggle = 1;
    private final static int type_date = 2;
    private LayoutInflater mInflater;

    public NoDisturbAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type_troggle == viewType) {
            View view = mInflater.inflate(R.layout.disturb_recycler_troggle, null);
            return new ToggleHolder(view);
        } else {
            View view = mInflater.inflate(R.layout.disturb_recycler_date, null);
            return new DateHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DateHolder) {

        } else if (holder instanceof ToggleHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return type_troggle;
        } else {
            return type_date;
        }
    }

    class DateHolder extends RecyclerView.ViewHolder {

        //TextView tv;
        @BindView(R.id.wv_birth_year)
        TextView yearView;
        @BindView(R.id.wv_birth_month)
        TextView monthView;
        @BindView(R.id.wv_birth_day)
        TextView dayView;

        public DateHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    class ToggleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nodisturb_first)
        TextView tv_nodisturb_first;
        @BindView(R.id.switch_button)
        SwitchButton btn;

        public ToggleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
