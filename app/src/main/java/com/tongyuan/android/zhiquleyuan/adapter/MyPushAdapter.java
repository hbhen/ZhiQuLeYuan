package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by android on 2017/3/12.
 */
public class MyPushAdapter extends BaseAdapter {
    private Context mContext;
    private Response<QueryMyPushResBean> response;
    private MyPushHolder mMPHolder;
    private String mFormattime;
    private ArrayList<QueryMyPushResBean.BODYBean.LSTBean> myPushList;

    public MyPushAdapter(Context myPushActivity, Response<QueryMyPushResBean> response) {
        this.mContext = myPushActivity;
        this.response = response;
    }

    public MyPushAdapter(Context myPushActivity, ArrayList<QueryMyPushResBean.BODYBean.LSTBean> myPushList) {
        this.mContext = myPushActivity;
        this.myPushList = myPushList;

    }


    @Override
    public int getCount() {
//        return response.body().getBODY().getLST().size();
        return myPushList.size();
    }


    @Override
    public Object getItem(int position) {
//        return response.body().getBODY().getLST().get(position);
        return myPushList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String img = myPushList.get(position).getIMG();

//        String img = response.body().getBODY().getLST().get(position).getIMG();
        String name = myPushList.get(position).getNAME();
//        String name = response.body().getBODY().getLST().get(position).getNAME();
//        String times = response.body().getBODY().getLST().get(position).getTIMES();
        String begintime = myPushList.get(position).getBEGINTIME();
//        String begintime = response.body().getBODY().getLST().get(position).getBEGINTIME();
//        String colname = response.body().getBODY().getLST().get(position).getCOLNAME();

        if (convertView == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_history, null);
            convertView = view;
            mMPHolder = new MyPushHolder();
            mMPHolder.textViewMonth = (TextView) convertView.findViewById(R.id.tv_item_history_time);
            mMPHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_history_pic);
            mMPHolder.imageViewPlay = (ImageView) convertView.findViewById(R.id.iv_item_history_play);
            mMPHolder.textViewTitle = (TextView) convertView.findViewById(R.id.tv_item_history_title);
            mMPHolder.textViewTime = (TextView) convertView.findViewById(R.id.tv_itemt_history_desc);
            mMPHolder.textViewCategory = (TextView) convertView.findViewById(R.id.tv_itemt_history_duration);
//            mMPHolder.textViewCategoryDesc = (TextView) convertView.findViewById(R.id.tv_item_mycollection_detailscategory);
            convertView.setTag(mMPHolder);
        } else {
            mMPHolder = (MyPushHolder) convertView.getTag();
        }
        //这里先写假数据,bean类里面没有定义textview的get也没有imageview的get,set方法;

//        mMCHolder.imageView.setImageResource(R.mipmap.test);
        mFormattime = begintime;
        String substringDate = mFormattime.substring(4, 8);
        String month = substringDate.substring(0, 2);
        String day = substringDate.substring(2, substringDate.length());
        String newDate = day + month;
        SpannableString spannableString = new SpannableString(newDate + "月");
        spannableString.setSpan(new AbsoluteSizeSpan(48), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(24), 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
        mMPHolder.textViewMonth.setText(spannableString);

        String substringTime = mFormattime.substring(8, 12);
        Glide.with(mContext).load(img).placeholder(R.drawable.player_cover_default).into(mMPHolder.imageView);
//        mMCHolder.textViewTitle.setText("铃儿响叮当");
        mMPHolder.textViewTitle.setText(name);
//        mMCHolder.textViewTimes.setText("212");
        mMPHolder.textViewCategory.setText("推送时间");
//        mMPHolder.textViewTime.setText(times);
//        mMCHolder.textViewCategory.setText("儿童歌谣");
//        mMPHolder.textViewCategoryDesc.setText(colname);


        return convertView;
    }


}
