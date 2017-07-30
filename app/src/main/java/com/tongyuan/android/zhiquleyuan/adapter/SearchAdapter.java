package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tongyuan.android.zhiquleyuan.bean.SearchResBean;

import java.util.List;

/**
 * Created by Android on 2017/7/28.
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    private List<SearchResBean.BODYBean.LSTBean> lst;

    public SearchAdapter(Context applicationContext, List<SearchResBean.BODYBean.LSTBean> lst) {
        this.context = applicationContext;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchHolder holder;
        if (convertView==null){

        }
        return convertView;
    }

    private class SearchHolder {

    }
}
