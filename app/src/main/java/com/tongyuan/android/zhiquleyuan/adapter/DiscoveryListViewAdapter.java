package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/12/9.
 */

public class DiscoveryListViewAdapter extends BaseAdapter {
    // ArrayList<ResourceID> list = new ArrayList<ResourceID>();

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    private Context context;
    List list = new ArrayList();

    //有数据的时候,传数据集合过来
//    public DiscoveryListViewAdapter(Context context,ArrayList<ResourceID> list) {
//        this.context = context;
//        this.list = list;
//
//    }
    public DiscoveryListViewAdapter(Context context, List list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertview, ViewGroup parent) {
        DiscoveryListViewHolder disHolder = null;
        if (convertview == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertview = View.inflate(context, R.layout.item_discovery_listview, null);
            disHolder = new DiscoveryListViewHolder();
            disHolder.iv_title = (ImageView) convertview.findViewById(R.id.iv_title);
            disHolder.tv_title = (TextView) convertview.findViewById(R.id.tv_title);
            disHolder.iv_desc = (ImageView) convertview.findViewById(R.id.iv_desc);
            disHolder.tv_desc_title = (TextView) convertview.findViewById(R.id.tv_desc_title);
            disHolder.tv_desc_times = (TextView) convertview.findViewById(R.id.tv_desc_times);
            disHolder.tv_desc_category = (TextView) convertview.findViewById(R.id.tv_desc_category);
            convertview.setTag(disHolder);
        } else {
            disHolder = (DiscoveryListViewHolder) convertview.getTag();
        }

        disHolder.iv_title.setImageResource(R.mipmap.ic_launcher);
        disHolder.tv_title.setText("推荐内容");
        disHolder.iv_desc.setImageResource(R.mipmap.ic_launcher);
        disHolder.tv_desc_title.setText("TED-Ed原创课程");
        disHolder.tv_desc_times.setText("播放次数");
        disHolder.tv_desc_category.setText("所属品类");
        return convertview;
    }
}
