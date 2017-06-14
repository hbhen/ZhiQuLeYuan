package com.tongyuan.android.zhiquleyuan.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;

/**
 * 创建一个text和Icon组合的view子类
 * 继承与LinerLayout表示两个IconTextView中的TextView和ImageView之间是线性布局关系。
 * Created by zgg on 2017/5/6.
 */

public class IconTextView extends LinearLayout {

    //类中的两个私有成员，Text和Icon
    //IconifiedTextView中的两个子View。由TextView和ImageView组合成一个新的View。
    private TextView mText;
    private ImageView mIcon;
    private Drawable icon;
    private String title;
    private float titleSize;
    private ColorStateList textColor = null;

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        title = typedArray.getString(R.styleable.CustomTitleView_titleText);
        textColor = typedArray.getColorStateList(R.styleable.CustomTitleView_titleTextColor);
        titleSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleView_titleTextSize, 15);
        icon = typedArray.getDrawable(R.styleable.CustomTitleView_titleIcon);
        init(context);
        typedArray.recycle();
    }

    private void init(Context context) {
        this.setGravity(Gravity.CENTER);
        mIcon = new ImageView(context);
        mIcon.setImageDrawable(icon);
        addView(mIcon, new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        mText = new TextView(context);
        mText.setText(title);
        titleSize = titleSize / getResources().getDisplayMetrics().density;
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        mText.setTextSize(titleSize);
        mText.setGravity(Gravity.CENTER);
        mText.setTextColor(textColor != null ? textColor : ColorStateList.valueOf(0xFF000000));
        addView(mText, lp);
    }

    //设置TextView的Text内容
    public void setText(String words) {
        mText.setText(words);
    }

    //设置ImageView的Icon图标
    public void setIcon(Drawable bullet) {
        mIcon.setImageDrawable(bullet);
    }

    public void setTextSize(float size) {
        mText.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    // 未完成的功能
    // 1: touch效果

}
