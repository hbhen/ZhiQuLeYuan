package com.tongyuan.android.zhiquleyuan.http;

import com.tongyuan.android.zhiquleyuan.interf.MyRequestManager;

/**
 * Created by android on 2016/12/27.
 */

public class RequestFactory {
    public static MyRequestManager myRequestManager() {
      return OkHttpRequestManager.getInstance();
       // return XRequest.getInstance();
    }
}
