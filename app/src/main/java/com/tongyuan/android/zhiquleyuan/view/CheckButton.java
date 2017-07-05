package com.tongyuan.android.zhiquleyuan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by Android on 2017/6/29.
 */

public class CheckButton extends CheckBox {
    public CheckButton(Context context) {
        super(context);
        initView(context);
    }

    public CheckButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CheckButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        // 填充布局

    }
}
