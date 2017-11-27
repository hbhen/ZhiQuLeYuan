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
import com.tongyuan.android.zhiquleyuan.bean.QueryMyCollectionResBean;
import com.tongyuan.android.zhiquleyuan.holder.MyCollectionHolder;

import java.util.List;

/**
 * Created by android on 2016/12/23.
 */
public class MyCollectionAdapter extends BaseAdapter {
    private Context mContext;

    private MyCollectionHolder mMCHolder;
    private List<QueryMyCollectionResBean.BODYBean.LSTBean> mLSTBeen;

    public MyCollectionAdapter(Context applicationContext, List<QueryMyCollectionResBean.BODYBean.LSTBean> lst) {
        this.mContext = applicationContext;
        this.mLSTBeen = lst;
    }

    //直接放回9条数据现在,以后封装到bean里面的数据拿到以后,再用list集合的长度.
    @Override
    public int getCount() {
//        return list.size();
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

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        String img = mLSTBeen.get(position).getIMG();
        String name = mLSTBeen.get(position).getNAME();
        String times = mLSTBeen.get(position).getTIMES();
        String colname = mLSTBeen.get(position).getCOLNAME();

        if (convertView == null) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.item_mycollection, null);
//            convertView = view;
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mycollection, null);
            mMCHolder = new MyCollectionHolder();
            mMCHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_mycollection);
            mMCHolder.textViewTitle = (TextView) convertView.findViewById(R.id.tv_item_mycollection);
            mMCHolder.textViewTimes = (TextView) convertView.findViewById(R.id.tv_item_mycollection_detailstimes);
            mMCHolder.textViewCategory = (TextView) convertView.findViewById(R.id.tv_item_mycollection_category);
            mMCHolder.textViewCategoryDesc = (TextView) convertView.findViewById(R.id.tv_item_mycollection_detailscategory);
            convertView.setTag(mMCHolder);
        } else {
            mMCHolder = (MyCollectionHolder) convertView.getTag();
        }
        //这里先写假数据,bean类里面没有定义textview的get也没有imageview的get,set方法;

//        mMCHolder.imageView.setImageResource(R.mipmap.test);
        Glide.with(mContext).load(img).placeholder(R.drawable.player_cover_default).into(mMCHolder.imageView);
//        mMCHolder.textViewTitle.setText("铃儿响叮当");
        mMCHolder.textViewTitle.setText(name);
//        mMCHolder.textViewTimes.setText("212");
        mMCHolder.textViewTimes.setText(times);
        mMCHolder.textViewCategory.setText("所属品类");
//        mMCHolder.textViewCategory.setText("儿童歌谣");
        mMCHolder.textViewCategoryDesc.setText(colname);


        return convertView;
    }
}
