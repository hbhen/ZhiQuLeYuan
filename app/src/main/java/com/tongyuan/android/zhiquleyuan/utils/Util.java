package com.tongyuan.android.zhiquleyuan.utils;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by dzl on 2016/9/10.
 */
public class Util {

    private static final String TAG = Util.class.getSimpleName();

    /**
     * 遍历viewGroup中所有的Button和ImageButton，并且注册点击监听器
     * @param viewGroup
     * @param listener
     */
    public static void setButtonOnClickListener(ViewGroup viewGroup, View.OnClickListener listener) {

        // 遍历所有的子View
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof Button || child instanceof ImageButton) {
                child.setOnClickListener(listener);
            } else if (child instanceof ViewGroup) {
                setButtonOnClickListener((ViewGroup) child, listener);  // 递归遍历
            }
        }
    }

    /** 获取屏幕宽 */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        return screenWidth;
    }

    /** 获取屏幕高 */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenHeiht = windowManager.getDefaultDisplay().getHeight();
        return screenHeiht;
    }

    /** 打印Cursor中所有的数据 */
    public static void printCursor(Cursor cursor) {
        if (cursor == null) {
            return;
        }

        Log.i(TAG, "总共有" + cursor.getCount() + "条数据");

        // 遍历所有的行
        while (cursor.moveToNext()) {

            Log.i(TAG, " ------------------ ");
            // 遍历一行中所有的列
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                String columnName = cursor.getColumnName(i);    // 获取列名
                String columnValue = cursor.getString(i);       // 获取列的值
                Log.i(TAG, columnName + " = " + columnValue);
            }
        }
    }

    /**
     * 把一个毫秒值格式化为一个String类型的时间，如果这个时间包含小时，则格式化为：01:30:59，如果不包含小时，则格式化为：30:59
     * @param duration
     * @return
     */
    public static CharSequence formatMillis(long duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();   // 清除时间，会保留：1970年1月1日 00:00:00
        calendar.add(Calendar.MILLISECOND, (int) duration); // 把一个时长变成了一个标题的日期
        long oneHourMillis = 1 * 60 * 60 * 1000;        // 1小时对应的毫秒值
        boolean hasHour = duration / oneHourMillis > 0; // 判断duration是否达到（超过）了1个小时
        CharSequence inFormat = hasHour ? "kk:mm:ss" : "mm:ss";
        return DateFormat.format(inFormat, calendar);
    }

    /**
     * 在屏幕的中央显示一个Toast
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
