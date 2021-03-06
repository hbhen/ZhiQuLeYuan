package com.tongyuan.android.zhiquleyuan.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtil {
    private static boolean DEBUG = true;
    private static Toast toast;

    /**
     * 强大的可以连续弹的吐司
     *
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (toast == null) {
            //创建吐司对象
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            //说明吐司已经存在了，那么则只需要更改当前吐司的文字内容
            toast.setText(text);
        }
        //最后你再show
        toast.show();
    }

    public static void showToast(Context context, int resId) {
        if (toast == null) {
            //创建吐司对象
            toast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
        } else {
            //说明吐司已经存在了，那么则只需要更改当前吐司的文字内容
            toast.setText(resId);
        }
        //最后你再show
        toast.show();
    }
}
