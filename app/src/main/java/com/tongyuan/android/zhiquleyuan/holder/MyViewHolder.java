package com.tongyuan.android.zhiquleyuan.holder;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.tongyuan.android.zhiquleyuan.R;

/**
 * Created by DTC on 2017/3/31.
 */
public class MyViewHolder extends BaseViewHolder {

    public ImageView mImageView;

    public MyViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.iv_manager_gridview_item);

    }
}
