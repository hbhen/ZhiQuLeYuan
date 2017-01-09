package com.tongyuan.android.zhiquleyuan.utils;

import android.content.Context;

/**
 * Created by android on 2016/12/2.
 */

public class ConvertUtils {

    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        System.out.print("displaymetrics="+scale);
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * px转sp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        System.out.print("displaymetrics="+fontScale);
        return (int) (pxValue / fontScale + 0.5f);
    }

}
