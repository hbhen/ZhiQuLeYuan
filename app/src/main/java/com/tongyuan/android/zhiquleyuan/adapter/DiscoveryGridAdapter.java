package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryGridHolder;

import java.util.ArrayList;

/**
 * Created by android on 2016/12/21.
 */
public class DiscoveryGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    ArrayList listImg = new ArrayList();
    ArrayList listName = new ArrayList();
    ArrayList listId = new ArrayList();

    public DiscoveryGridAdapter(Context context, ArrayList listImg, ArrayList listId, ArrayList listName) {//看看以后能不能把这些数据封装到一个list里面!
        this.context = context;
        this.listImg = listImg;
        this.listId = listId;
        this.listName = listName;
    }

    @Override
    public int getCount() {
        return listImg.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiscoveryGridHolder holder;
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_discovery_gridview, null);
            holder = new DiscoveryGridHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_grid);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_grid);
            convertView.setTag(holder);
        } else {
            holder = (DiscoveryGridHolder) convertView.getTag();
        }
        Uri parse = Uri.parse(listImg.get(position).toString());
        Log.i("111111", "parse: " + parse);
        Glide.with(context).load(parse).into(holder.iv);
        holder.tv.setText(listName.get(position).toString());
        return convertView;
    }
}
