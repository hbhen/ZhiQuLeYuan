package com.tongyuan.android.zhiquleyuan.interf;

/**
 * Created by Android on 2017/6/8.
 */

public interface Constant {

    String baseurl = "http://120.27.41.179:8082/zqpland/m/iface/";
    //    String apkDownload = "http://192.168.1.105:8080/update/zqly5.0.apk";
//    String apkDownload = "http://imtt.dd.qq.com/16891/4AA0756BB7F1DEBD6F23D6CA6E688768.apk?fsname=com.tongyuan.android" +
//            ".zhiquleyuan_1.3_3.apk&csr=1bbd";
    String apkDownload = "http://sj.qq.com/myapp/detail.htm?apkName=com.tongyuan.android.zhiquleyuan";
    //    String baseurl="http://120.27.41.179:8081/zqpland/m/iface/";
//    String baseurl= "https://www.kinder-mate.com:8443/zqpland/m/iface/";
    String toyPlay = "1";
    String toyPause = "2";
    String toyStop = "3";
    String toyNext = "4";
    String webViewUrl = "http://www.kinder-mate.cn/agreetment.html";

    enum Mode {
        DEL, NORMAL, KING
    }
}
