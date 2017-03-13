package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tongyuan.android.zhiquleyuan.R;

import java.util.List;

/**
 * Created by android on 2017/3/12.
 */
public class MyPushAdapter extends BaseQuickAdapter {
    private Context mContext;

    public MyPushAdapter(Context context, int layoutResId, List data) {
        super(R.layout.item_mypush, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {

    }


}
