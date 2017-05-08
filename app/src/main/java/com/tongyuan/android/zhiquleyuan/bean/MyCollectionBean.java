package com.tongyuan.android.zhiquleyuan.bean;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by android on 2016/12/23.
 */
//这个等后台的数据,以后调通,用jsonformat一键生成.
public class MyCollectionBean {
    public ImageView imageView;
    public TextView textViewTitle;
    public TextView textViewTimes;
    public TextView textViewCategory;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public void setTextViewTitle(TextView textViewTitle) {
        this.textViewTitle = textViewTitle;
    }

    public TextView getTextViewTimes() {
        return textViewTimes;
    }

    public void setTextViewTimes(TextView textViewTimes) {
        this.textViewTimes = textViewTimes;
    }

    public TextView getTextViewCategory() {
        return textViewCategory;
    }

    public void setTextViewCategory(TextView textViewCategory) {
        this.textViewCategory = textViewCategory;
    }
}
