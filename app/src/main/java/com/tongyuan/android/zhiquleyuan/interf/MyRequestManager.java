package com.tongyuan.android.zhiquleyuan.interf;

/**
 * Created by android on 2017/1/16.
 */

public interface MyRequestManager {
    void get(String url, MyRequestCallback myRequestCallBack);

    void post(String url, String requestBodyJson, MyRequestCallback myRequestCallBack);

    void put(String url, String requestBodyJson, MyRequestCallback myRequestCallBack);

    void delete(String url, String requestBodyJson, MyRequestCallback myRequestCallBack);
}
