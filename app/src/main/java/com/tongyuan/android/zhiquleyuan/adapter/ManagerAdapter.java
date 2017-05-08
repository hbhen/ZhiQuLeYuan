package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.holder.MyViewHolder;

import java.util.List;

/**
 * Created by DTC on 2017/3/31.
 */
public class ManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<QueryToyMemberReSBean.BODYBean.LSTBean> lst;
    private Context mContext;


    public ManagerAdapter(Context context, List<QueryToyMemberReSBean.BODYBean.LSTBean> lst) {
        this.lst = lst;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext, R.layout.manager_gridview_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
//        myViewHolder.mImageView.setImageResource(img[position]);
        Glide.with(mContext).load(lst.get(position).getIMG()).asBitmap().centerCrop().into(new BitmapImageViewTarget(myViewHolder.mImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                myViewHolder.mImageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
    }


    @Override
    public int getItemCount() {
        return lst.size();
    }
}
