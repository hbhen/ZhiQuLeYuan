package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryGridHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/12/21.
 */
public class DiscoveryGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<DiscoveryGridItemBean.LSTBean> list = new ArrayList<>();
    private int width;
    private int height;

    public DiscoveryGridAdapter(Context context, List<DiscoveryGridItemBean.LSTBean> data) {//看看以后能不能把这些数据封装到一个list里面!
        this.context = context;
        this.list = data;
        inflater = LayoutInflater.from(context);
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        width = (screenWidth - 3*(int)context.getResources().getDimension(R.dimen.discovery_grid_space))/3;
        height = 86 * width / 112;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiscoveryGridHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_discovery_gridview, null);
            holder = new DiscoveryGridHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_grid);
            holder.iv.setLayoutParams(new LinearLayout.LayoutParams(width, height));
            holder.tv = (TextView) convertView.findViewById(R.id.tv_grid);
            convertView.setTag(holder);
        } else {
            holder = (DiscoveryGridHolder) convertView.getTag();
        }
        Uri parse = Uri.parse(list.get(position).IMG);
        Glide.with(context).load(parse).into(holder.iv);
        holder.tv.setText(list.get(position).NAME);
        return convertView;
    }
}
