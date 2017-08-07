package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.SearchResBean;
import com.tongyuan.android.zhiquleyuan.holder.SearchHolder;

import java.util.List;

/**
 * Created by Android on 2017/7/28.
 */
public class SearchAdapter extends BaseAdapter {

    private Context context;
    private List<SearchResBean.BODYBean.LSTBean> list;
//    private ArrayList list;
    private SearchHolder searchHolder;
//    private ImageView mImageView;
//    private TextView title;
//    private TextView times;
//    private TextView category;

    public SearchAdapter(Context applicationContext, List<SearchResBean.BODYBean.LSTBean> lst) {
        this.context = applicationContext;
        this.list = lst;
    }
//public SearchAdapter(Context context,ArrayList lst){
//    this.context=context;
//    this.list=lst;

//}
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
        if (convertView == null) {
            searchHolder = new SearchHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search, null);
            searchHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_item_search);
            searchHolder.title = (TextView) convertView.findViewById(R.id.tv_item_search);
            searchHolder.times = (TextView) convertView.findViewById(R.id.tv_item_search_detailstimes);
            searchHolder.category = (TextView) convertView.findViewById(R.id.tv_item_search_detailscategory);
            convertView.setTag(searchHolder);
        }

        else {
            searchHolder = (SearchHolder) convertView.getTag();

        }

        Glide.with(context).load(list.get(position).getIMG()).placeholder(R.drawable.player_cover_default).into(searchHolder.mImageView);
        searchHolder.title.setText(list.get(position).getNAME());
        searchHolder.times.setText(list.get(position).getTIMES());
        searchHolder.category.setText(list.get(position).getTYPE());

        return convertView;
    }


}
