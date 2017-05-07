package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;

import retrofit2.Response;

/**
 * Created by android on 2017/3/12.
 */
public class MyPushAdapter extends BaseSwipeAdapter {
    private Context mContext;
    private Response<QueryMyPushResBean> response;

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

    }

    @Override
    public int getCount() {
        return 3;
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
