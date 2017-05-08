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

import java.util.ArrayList;

/**
 * Created by android on 2016/12/26.
 */
public class RAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> listImg;
    private ArrayList<String> listId;
    private ArrayList<String> listDura;

    public RAdapter(Context context, int layoutResId, ArrayList<String> listImg, ArrayList<String> listId) {
        this.listDura = listDura;
        this.listId = listId;
        this.listImg = listImg;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listId.size();
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
        CallHolder callHolder =null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = View.inflate(context, R.layout.item_content, null);
            callHolder = new CallHolder();
            callHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_item_history_pic);
            callHolder.mTextTitle = (TextView) convertView.findViewById(R.id.tv_item_history_title);
            callHolder.mTextDesc = (TextView) convertView.findViewById(R.id.tv_itemt_history_desc);//通话时长无数据响应
//            callHolder.mTextTime = (TextView) convertView.findViewById(R.id.tv_item_history_time);
            convertView.setTag(callHolder);
        } else {
            callHolder = (CallHolder) convertView.getTag();
        }
        for (int i = 0; i < position+1; i++) {
            Glide.with(context).load(listImg.get(position)).into(callHolder.mImageView);
        }
        for (int i = 0; i < position+1; i++) {
            callHolder.mTextTitle.setText(listId.get(position));

        }
//        for (int i = 0; i < position; i++) {
//            callHolder.mTextTime.setText(listDura.get(position));
//        }

        return convertView;
    }

    class CallHolder {
        public ImageView mImageView;
        public TextView mTextTitle;
        public TextView mTextDesc;
        public TextView mTextTime;
    }
}
