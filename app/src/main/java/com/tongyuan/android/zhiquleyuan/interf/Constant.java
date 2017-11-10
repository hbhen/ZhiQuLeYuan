package com.tongyuan.android.zhiquleyuan.interf;

/**
 * Created by Android on 2017/6/8.
 */

public interface Constant {

      String baseurl = "http://120.27.41.179:8082/zqpland/m/iface/";
//    String baseurl="http://120.27.41.179:8081/zqpland/m/iface/";
//    String baseurl= "https://www.kinder-mate.com:8443/zqpland/m/iface/";
    String toyPlay = "1";
    String toyPause = "2";
    String toyStop = "3";
    String toyNext = "4";
    String webViewUrl="http://www.kinder-mate.cn/agreetment.html";

    enum Mode {
        DEL, NORMAL, KING
    }
}
