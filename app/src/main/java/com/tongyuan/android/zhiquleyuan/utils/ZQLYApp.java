package com.tongyuan.android.zhiquleyuan.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

/**
 * Created by android on 2016/12/20.
 */

public class ZQLYApp extends MultiDexApplication {

    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

//        开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = false;
//        开启Log日志的开关
        LogUtil.DEBUG = true;

        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);


    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {

        PlatformConfig.setWeixin("wx0d4463dd363141ea", "7d90d5ff4e4ecdc4f64f73af6b9bcc52");

        //豆瓣RENREN平台目前只能在服务器端配置
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");
//        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
//        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
//        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
//        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
//        PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");
    }


}
