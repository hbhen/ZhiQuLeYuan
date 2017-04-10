package com.tongyuan.android.zhiquleyuan.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.holder.MyViewHolder;

import java.util.List;

/**
 * Created by DTC on 2017/3/31.
 */

public class ToyMemberAdapter extends BaseQuickAdapter<QueryToyMemberReSBean.BODYBean.LSTBean, MyViewHolder> {
    private Context context;

    public ToyMemberAdapter(FragmentActivity activity, int manager_gridview_item, List<QueryToyMemberReSBean.BODYBean.LSTBean> lst) {
        super(R.layout.manager_gridview_item, lst);
        this.context = activity;
    }

    @Override
    protected void convert(final MyViewHolder helper, QueryToyMemberReSBean.BODYBean.LSTBean item) {
        String userImg = item.getIMG();
        Glide.with(context).load(userImg).asBitmap().into(new BitmapImageViewTarget(helper.mImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                helper.mImageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
    }
}

