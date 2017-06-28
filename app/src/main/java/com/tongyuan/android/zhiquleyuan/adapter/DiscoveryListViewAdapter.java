package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryListViewHolder2;

import java.util.ArrayList;
import java.util.List;

import static com.tongyuan.android.zhiquleyuan.R.id.tv_desc_detailstimes_notitle;

/**
 * Created by android on 2016/12/9.
 */

public class DiscoveryListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> list = new ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>();

    public DiscoveryListViewAdapter(Context context, List<DiscoveryListResultBean.BODYBean.LSTBean> discoveryList) {
        this.mContext = context;
        this.list.addAll(discoveryList);
    }


    public ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> getList() {
        return (ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>) list;
    }

    @Override
    public int getCount() {
        Log.i("adapter", "list.size=" + list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
//        return itemList.get(position);
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        DiscoveryListViewHolder2 disHolder2 = null;

        if (convertview == null) {
//
            convertview = View.inflate(mContext, R.layout.item_discovery_listview_notitle, null);
            disHolder2 = new DiscoveryListViewHolder2();
            disHolder2.iv_desc_notitle = (ImageView) convertview.findViewById(R.id.iv_desc_notitle);
            disHolder2.tv_desc_title_notitle = (TextView) convertview.findViewById(R.id.tv_desc_title_notitle);
            disHolder2.tv_desc_times_notitle = (TextView) convertview.findViewById(tv_desc_detailstimes_notitle);
            disHolder2.tv_desc_category_notitle = (TextView) convertview.findViewById(R.id.tv_desc_detailscategory_notitle);
            convertview.setTag(disHolder2);
        } else {
            disHolder2 = (DiscoveryListViewHolder2) convertview.getTag();
            disHolder2 = fillHolderTitle(convertview);
        }
        Glide.with(mContext).load(list.get(position).getIMG()).asBitmap().into(disHolder2.iv_desc_notitle);
        disHolder2.tv_desc_title_notitle.setText(list.get(position).getNAME());
        disHolder2.tv_desc_times_notitle.setText(list.get(position).getTIMES());
        disHolder2.tv_desc_category_notitle.setText(list.get(position).getCOLNAME());

        return convertview;
    }


    private DiscoveryListViewHolder2 fillHolderTitle(View convertview) {
        convertview = convertview.inflate(mContext, R.layout.item_discovery_listview_notitle, null);
        DiscoveryListViewHolder2 disHolder2 = new DiscoveryListViewHolder2();
        disHolder2.iv_desc_notitle = (ImageView) convertview.findViewById(R.id.iv_desc_notitle);
        disHolder2.tv_desc_title_notitle = (TextView) convertview.findViewById(R.id.tv_desc_title_notitle);
        disHolder2.tv_desc_times_notitle = (TextView) convertview.findViewById(tv_desc_detailstimes_notitle);
        disHolder2.tv_desc_category_notitle = (TextView) convertview.findViewById(R.id.tv_desc_detailscategory_notitle);
        convertview.setTag(disHolder2);
        return disHolder2;
    }
}
