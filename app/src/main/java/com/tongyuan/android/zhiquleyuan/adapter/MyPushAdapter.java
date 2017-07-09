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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Response;

/**
 * Created by android on 2017/3/12.
 */
public class MyPushAdapter extends BaseSwipeAdapter {
    private Context mContext;
    private Response<QueryMyPushResBean> response;
    private String mFormattime;

    public MyPushAdapter(Context myPushActivity, Response<QueryMyPushResBean> response) {
        this.mContext = myPushActivity;
        this.response = response;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_push;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_mypush, parent, false);
    }

    @Override
    public void fillValues(int position, View convertView) {
        final int pos = position;
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_push);
        ImageView iv_item_mypush = (ImageView) convertView.findViewById(R.id.iv_item_mypush);
        ImageView iv_item_mypush_play = (ImageView) convertView.findViewById(R.id.iv_item_mypush_play);
        TextView tv_item_mypush_time = (TextView) convertView.findViewById(R.id.tv_item_mypush_time);
        TextView tv_item_mypush_title = (TextView) convertView.findViewById(R.id.tv_item_mypush_title);
        TextView tv_item_mypush_subject = (TextView) convertView.findViewById(R.id.tv_item_mypush_subject);
        Button pushDelete = (Button) convertView.findViewById(R.id.swipe_pust_delete_btn);
        pushDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                response.body().getBODY().getLST().remove(pos);
                notifyDataSetChanged();
                swipeLayout.close();
            }
        });

        String begintime = response.body().getBODY().getLST().get(position).getBEGINTIME();
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
        tv_item_mypush_time.setText(spannableString);

        Log.i("444444", "getView:substringDate " + substringDate + ";");
        Log.i("444444", "getView:month " + month + ";");
        Log.i("444444", "getView:day " + day + ";");
        Log.i("444444", "getView:newDate " + newDate + ";");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");


        try {
            mFormattime = simpleDateFormat.format(simpleDateFormat1.parse(begintime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_item_mypush_subject.setText(mFormattime);
        Glide.with(mContext).load(response.body().getBODY().getLST().get(position).getIMG().toString()).asBitmap().into(iv_item_mypush);


    }

    @Override
    public int getCount() {
        return response.body().getBODY().getLST().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    public MyPushAdapter(Context context, int layoutResId, List data) {
//        super(R.layout.item_mypush, data);
//        this.mContext = context;
//    }

}
