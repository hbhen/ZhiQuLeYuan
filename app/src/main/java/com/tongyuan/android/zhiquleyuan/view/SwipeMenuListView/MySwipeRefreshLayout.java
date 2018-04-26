package com.tongyuan.android.zhiquleyuan.view.SwipeMenuListView;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by DTC on 2018/4/26.
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private int startX;
    private int startY;

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dX = (int) (ev.getX() - startX);
                int dY = (int) (ev.getY() - startY);
                if (Math.abs(dX) > Math.abs(dY)) {//左右滑动
                    return false;
                }
//                else {//上下滑动
//                    return false;
//                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
