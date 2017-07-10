package com.tongyuan.android.zhiquleyuan.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.zhy.magicviewpager.transformer.BasePageTransformer;
import com.zhy.magicviewpager.transformer.NonPageTransformer;

/**
 * Created by Android on 2017/7/9.
 */

public class SelectorPagerTransformer extends BasePageTransformer {

    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale = DEFAULT_MIN_SCALE;

    public SelectorPagerTransformer() {

    }

    public SelectorPagerTransformer(float minScale) {
        this(minScale, NonPageTransformer.INSTANCE);
    }

    public SelectorPagerTransformer(ViewPager.PageTransformer pageTransformer) {
        this(DEFAULT_MIN_SCALE, pageTransformer);
    }


    public SelectorPagerTransformer(float minScale, ViewPager.PageTransformer pageTransformer) {
        mMinScale = minScale;
        mPageTransformer = pageTransformer;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void pageTransform(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        Log.i("gengen", "pageTransform position="+position);

        view.setPivotY(pageHeight / 2);
        view.setPivotX(pageWidth / 2);
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
            view.setPivotX(pageWidth);
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            if (position < 0) //1-2:1[0,-1] ;2-1:1[-1,0]
            {

                float scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale;
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                view.setPivotX(pageWidth * (DEFAULT_CENTER + (DEFAULT_CENTER * -position)));

            } else //1-2:2[1,0] ;2-1:2[0,1]
            {
                float scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale;
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
            }


        } else { // (1,+Infinity]
            view.setPivotX(0);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        }
    }
}