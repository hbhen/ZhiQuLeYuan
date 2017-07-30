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
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;
import com.tongyuan.android.zhiquleyuan.holder.MyPushHolder;

import retrofit2.Response;

/**
 * Created by android on 2017/3/12.
 */
public class MyPushAdapter extends BaseAdapter {
    private Context mContext;
    private Response<QueryMyPushResBean> response;
    private MyPushHolder mMPHolder;

    public MyPushAdapter(Context myPushActivity, Response<QueryMyPushResBean> response) {
        this.mContext = myPushActivity;
        this.response = response;
    }


    @Override
    public int getCount() {
        return response.body().getBODY().getLST().size();
    }

    @Override
    public Object getItem(int position) {
        return response.body().getBODY().getLST().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String img = response.body().getBODY().getLST().get(position).getIMG();
        String name = response.body().getBODY().getLST().get(position).getNAME();
        String times = response.body().getBODY().getLST().get(position).getTIMES();
        String colname = response.body().getBODY().getLST().get(position).getCOLNAME();

        if (convertView == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_mycollection, null);
            convertView = view;
            mMPHolder = new MyPushHolder();
            mMPHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_mycollection);
            mMPHolder.textViewTitle = (TextView) convertView.findViewById(R.id.tv_item_mycollection);
            mMPHolder.textViewTimes = (TextView) convertView.findViewById(R.id.tv_item_mycollection_detailstimes);
            mMPHolder.textViewCategory = (TextView) convertView.findViewById(R.id.tv_item_mycollection_category);
            mMPHolder.textViewCategoryDesc = (TextView) convertView.findViewById(R.id.tv_item_mycollection_detailscategory);
            convertView.setTag(mMPHolder);
        } else {
            mMPHolder = (MyPushHolder) convertView.getTag();
        }
        //这里先写假数据,bean类里面没有定义textview的get也没有imageview的get,set方法;

//        mMCHolder.imageView.setImageResource(R.mipmap.test);
        Glide.with(mContext).load(img).asBitmap().into(mMPHolder.imageView);
//        mMCHolder.textViewTitle.setText("铃儿响叮当");
        mMPHolder.textViewTitle.setText(name);
//        mMCHolder.textViewTimes.setText("212");
        mMPHolder.textViewTimes.setText(times);
        mMPHolder.textViewCategory.setText("所属品类");
//        mMCHolder.textViewCategory.setText("儿童歌谣");
        mMPHolder.textViewCategoryDesc.setText(colname);


        return convertView;
    }


}
