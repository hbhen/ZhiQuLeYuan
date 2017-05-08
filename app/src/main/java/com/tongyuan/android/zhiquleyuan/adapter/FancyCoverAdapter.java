package com.tongyuan.android.zhiquleyuan.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tongyuan.android.zhiquleyuan.R;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

/**
 * Created by android on 2016/12/23.
 */

public class FancyCoverAdapter extends FancyCoverFlowAdapter {
    private int[] images = {R.mipmap.test, R.mipmap.test, R.mipmap.test};

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Integer getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {

        return i;

    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        ImageView imageView = null;

        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
        } else {
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(300, 400));

        }

        imageView.setImageResource(this.getItem(i));
        return imageView;
    }
}
