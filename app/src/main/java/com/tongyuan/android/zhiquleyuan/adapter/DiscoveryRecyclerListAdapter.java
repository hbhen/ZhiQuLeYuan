package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tongyuan.android.zhiquleyuan.R;

import java.util.List;

/**
 * Created by android on 2016/12/27.
 */
public class DiscoveryRecyclerListAdapter extends BaseQuickAdapter {
    public DiscoveryRecyclerListAdapter(Context context,int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {
        baseViewHolder.setText(R.id.tv_title,"1");

    }
}
