package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryResultBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Android on 2017/5/25.
 */
public class HistoryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<CallHistoryResultBean.BODYBean.LSTBean> mList;
    private String mFormattime;


    public HistoryAdapter(Context context, ArrayList<CallHistoryResultBean.BODYBean.LSTBean> lst) {
        this.mContext = context;
        this.mList = lst;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryHolder historyHolder = null;
        if (convertView == null) {
            historyHolder = new HistoryHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_history, null);
            historyHolder.callDate = (TextView) convertView.findViewById(R.id.tv_item_history_time);
            historyHolder.toyPic = (ImageView) convertView.findViewById(R.id.iv_item_history_pic);
            historyHolder.toyId = (TextView) convertView.findViewById(R.id.tv_item_history_title);
            historyHolder.callDuration = (TextView) convertView.findViewById(R.id.tv_itemt_history_desc);
            convertView.setTag(historyHolder);

        }
        historyHolder = (HistoryHolder) convertView.getTag();


        Glide.with(mContext).load(mList.get(position).getTOYIMG().toString()).asBitmap().into(historyHolder.toyPic);

        historyHolder.toyId.setText(mList.get(position).getTOYNICK().toString());
        String begintime = mList.get(position).getBEGINTIME();
        mFormattime = begintime;

        //先处理传过来的日期内容,把格式转换成日(大),月(小); 这个方法可以抽取成一个工具类
        String substringDate = mFormattime.substring(4, 8);
        String month = substringDate.substring(0, 2);
        String day = substringDate.substring(2, substringDate.length());
        String newDate = day + month;
        SpannableString spannableString = new SpannableString(newDate + "月");
        spannableString.setSpan(new AbsoluteSizeSpan(48), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(24), 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
        historyHolder.callDate.setText(spannableString);

        Log.i("444444", "getView:substringDate " + substringDate + ";");
        Log.i("444444", "getView:month " + month + ";");
        Log.i("444444", "getView:day " + day + ";");
        Log.i("444444", "getView:newDate " + newDate + ";");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        try {
            mFormattime = simpleDateFormat.format(simpleDateFormat1.parse(begintime));
            historyHolder.callDuration.setText(mFormattime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    class HistoryHolder {
        private TextView callDate;
        private ImageView toyPic;
        private TextView toyId;
        private TextView callDuration;
    }
}
