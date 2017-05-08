package com.tongyuan.android.zhiquleyuan.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by android on 2017/3/20.
 */

public class SwipeLayout extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mContentView;
    private View mDeleteView;
    private int mContentWidth;
    private int mContentHeight;
    private int mDeleteWidth;

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iniT();
    }


    private enum SwipeState {
        Open, Close
    }

    private SwipeState mState = SwipeState.Close;//默认是关闭

    private void iniT() {
        mViewDragHelper = ViewDragHelper.create(this, callback);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mDeleteView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mContentWidth = mContentView.getMeasuredWidth();
        mContentHeight = mContentView.getMeasuredHeight();
        mDeleteWidth = mDeleteView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        mContentView.layout(0, 0, mContentWidth, mContentHeight);
        mDeleteView.layout(mContentWidth, 0, mContentWidth + mDeleteWidth, mContentHeight);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = mViewDragHelper.shouldInterceptTouchEvent(ev);
        if (!SwipeLayoutManager.create().isCanSwipe(this)) {
            SwipeLayoutManager.create().closeLayout();
            return false;
        }
        return result;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SwipeLayoutManager.create().clearSwipeLayout();
    }

    float x, y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!SwipeLayoutManager.create().isCanSwipe(this)) {
            requestDisallowInterceptTouchEvent(true);
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                float deltaX = moveX - x;
                float delataY = moveY - y;
                if (Math.abs(deltaX) > Math.abs(delataY)) {
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 1;//只要大于0就行
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == mContentView) {
                if (left > 0)
                    left = 0;
                if (left < -mDeleteWidth)
                    left = -mDeleteWidth;
            } else if (child == mDeleteView) {
                if (left < (mContentWidth - mDeleteWidth)) {
                    left = (mContentWidth - mDeleteWidth);

                } else if (left > mContentWidth) {
                    left = mContentWidth;
                }
            }
            return left;
        }

        //        用来做伴随移动的,就是让deleteView跟着contentView移动
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView == mContentView) {
                int newLeft = mDeleteView.getLeft() + dx;
                mDeleteView.layout(newLeft, 0, newLeft + mDeleteWidth, mDeleteView.getBottom());

            } else if (changedView == mDeleteView) {
                int newLeft = mContentView.getLeft() + dx;
                mContentView.layout(newLeft, mContentView.getTop(), mContentWidth + newLeft, mContentView.getBottom());
            }
            //判断打开与关闭
            if (mContentView.getLeft() == 0 && mState != SwipeState.Close) {
                mState = SwipeState.Close;
                SwipeLayoutManager.create().clearSwipeLayout();
            } else if (mContentView.getLeft() == -mDeleteWidth && mState != SwipeState.Open) {
                mState = SwipeState.Open;
                SwipeLayoutManager.create().setSwipeLayout(SwipeLayout.this);
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mContentView.getLeft() < -mDeleteWidth / 2) {
                open();
            } else {
                close();
            }
        }
    };

    public void close() {
        mViewDragHelper.smoothSlideViewTo(mContentView, 0, mContentView.getTop());
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
    }

    public void open() {
        mViewDragHelper.smoothSlideViewTo(mContentView, -mDeleteWidth, mContentView.getTop());
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }
    }
}
