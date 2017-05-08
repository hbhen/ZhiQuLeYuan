package com.tongyuan.android.zhiquleyuan.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by android on 2016/12/5.
 */

public class NoScrollViewPager extends ViewPager{
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //禁用滑动
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
