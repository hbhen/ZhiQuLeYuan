package com.tongyuan.android.zhiquleyuan.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zgg on 2017/7/5.
 */

public abstract class AbstractViewPagerAdapter<T> extends PagerAdapter {

    public List<T> mData;
    public SparseArray<View> mViews;
    //private int mChildCount = 0;

    public AbstractViewPagerAdapter(List<T> data) {
        mData = data;
        mViews = new SparseArray<View>(data.size());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            view = newView(position);
            mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    public abstract View newView(int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        /*if ( mChildCount > 0) {
            mChildCount --;
            return POSITION_NONE;
        }*/
        return POSITION_NONE;
    }

    /*@Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }*/
}
