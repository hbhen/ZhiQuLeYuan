package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.holder.SecondaryCategoryHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/12/21.
 */
public class DiscoverySecondCategoryAdapter extends BaseAdapter {
    private Context mContext;
    private final LayoutInflater mInflater;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> list ;

    public DiscoverySecondCategoryAdapter(Context context, List<DiscoveryListResultBean.BODYBean.LSTBean> list) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    public ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> getList() {
        return (ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>) list;
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
        SecondaryCategoryHolder holder;
        if (convertView == null) {
            holder = new SecondaryCategoryHolder();
            convertView = mInflater.inflate(R.layout.item_album_details_two, null);
            holder.textviewTitle = (TextView) convertView.findViewById(R.id.tv_album_details_two_title);
            holder.itemalumbimg = (ImageView) convertView.findViewById(R.id.iv_album_details_two);
            holder.textviewAge = (TextView) convertView.findViewById(R.id.tv_item_album_details_two_age);
            holder.textviewLike = (TextView) convertView.findViewById(R.id.tv_itemt_album_details_two_like);
            convertView.setTag(holder);
        }else{
            holder= (SecondaryCategoryHolder) convertView.getTag();
        }
        /**
         * {
         * "TYPE": "RES",
         * "CMD": "QRYRES",
         * "ACCT": "XXXX",
         * "TIME": "20170324113712831",
         * "BODY": {
         * "PN": "1",
         * "CNT": "1",
         * "PS": "10",
         * "NC": "0",
         * "LST": [{
         * "TYPE": "音频文件",
         * "NAME": "蚂蚁",
         * "IMG": "http://120.27.41.179:8081/zqpland/resource/thumbnail/5/png/20170210/201702101530091016563538.png",
         * "ID": "201701121950061016547106",
         * "COLNAME": "国学经典 ",
         * "SIZE": "11.03MB",
         * "DUR": "",
         * "COLID": "201611050827051016432864 ",
         * "REMARK": "",
         * "TIMES": "5"
         * }]
         * },
         * "VERI": "",
         * "TOKEN": "51ff422f-fdec-4c1e-a277-90419c20b827",
         * "SEQ": "1",
         * "CODE": "0",
         * "MSG": ""
         * }
         */
        Uri parse = Uri.parse(list.get(position).getIMG());
        Glide.with(mContext).load(parse).into(holder.itemalumbimg);
        holder.textviewTitle.setText(list.get(position).getNAME());

        return convertView;
    }
}
