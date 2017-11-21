package com.tongyuan.android.zhiquleyuan.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 2017/10/23.
 */
//activity管理类
public class ActivityManager {
    public static List<Activity> sActivities = new ArrayList<>();

    public static void addAvtivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeAvtivity(Activity activity) {
        sActivities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static List queryActivity() {
//        if (sActivities == null) {
//            List<String> arrayList = new ArrayList<>();
//            arrayList.add("测试的list");
//            return arrayList;
//        }
        return sActivities;
    }
}
