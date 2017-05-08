package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryResultBean;

import java.util.ArrayList;

/**
 * Created by DTC on 2017/4/6.
 */

public class HistorySwipeAdapter extends BaseSwipeAdapter {
    private Context mContext;
    private ArrayList<CallHistoryResultBean.BODYBean.LSTBean> mLSTBeen;

    public HistorySwipeAdapter(Context context, ArrayList<CallHistoryResultBean.BODYBean.LSTBean> lst) {
        this.mContext = context;
        this.mLSTBeen = lst;

    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.sample1;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_content, parent, false);
    }

    @Override
    public void fillValues(int position, View convertView) {

        final int pos = position;

        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample1);
        ImageView viewById = (ImageView) convertView.findViewById(R.id.iv_item_history_pic);
        TextView viewById1 = (TextView) convertView.findViewById(R.id.tv_item_history_title);
        TextView viewById2 = (TextView) convertView.findViewById(R.id.tv_itemt_history_desc);
        Button viewById3 = (Button) convertView.findViewById(R.id.swipe_delete_btn);
        viewById3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLSTBeen.remove(pos);
                notifyDataSetChanged();
                swipeLayout.close();// 删除成功后需要关闭侧滑
            }
        });

        Glide.with(mContext).load(mLSTBeen.get(position).getTOYIMG()).into(viewById);
        viewById1.setText(mLSTBeen.get(position).getTOYID());

    }

    @Override
    public int getCount() {
        return mLSTBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mLSTBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
