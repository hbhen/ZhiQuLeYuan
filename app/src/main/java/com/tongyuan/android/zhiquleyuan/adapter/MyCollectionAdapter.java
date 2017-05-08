package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.holder.MyCollectionHolder;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.MyCollectionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/12/23.
 */
public class MyCollectionAdapter extends BaseAdapter{
    private Context context;
    private List<MyCollectionBean> list=new ArrayList<MyCollectionBean>();
    private MyCollectionHolder mMCHolder;

    public MyCollectionAdapter(Context context,List<MyCollectionBean> list){
        this.context=context;

    }
//直接放回9条数据现在,以后封装到bean里面的数据拿到以后,再用list集合的长度.
    @Override
    public int getCount() {
//        return list.size();
        return 9;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView==null){
            View view = LayoutInflater.from(context).inflate(R.layout.item_mycollection, null);
            convertView=view;
            mMCHolder = new MyCollectionHolder();
            mMCHolder.imageView= (ImageView) convertView.findViewById(R.id.iv_item_mycollection);
            mMCHolder.textViewTitle= (TextView) convertView.findViewById(R.id.tv_item_mycollection);
            mMCHolder.textViewTimes= (TextView) convertView.findViewById(R.id.tv_item_mycollection_detailstimes);
            mMCHolder.textViewCategory= (TextView) convertView.findViewById(R.id.tv_item_mycollection_detailscategory);
            convertView.setTag(mMCHolder);
        }
        else{
            mMCHolder = (MyCollectionHolder) convertView.getTag();
        }
        //这里先写假数据,bean类里面没有定义textview的get也没有imageview的get,set方法;

            mMCHolder.imageView.setImageResource(R.mipmap.test);
            mMCHolder.textViewTitle.setText("铃儿响叮当");
            mMCHolder.textViewTimes.setText("212");
            mMCHolder.textViewCategory.setText("儿童歌谣");


        return convertView;
    }
}
