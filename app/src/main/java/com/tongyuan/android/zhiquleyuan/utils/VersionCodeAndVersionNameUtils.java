package com.tongyuan.android.zhiquleyuan.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by DTC on 2017/11/615:09.
 */

public class VersionCodeAndVersionNameUtils {
    public static int getLocalVersionCode(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            LogUtil.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
            LogUtil.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
