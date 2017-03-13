package com.tongyuan.android.zhiquleyuan.interf;

/**
 * Created by android on 2016/12/27.
 */

public interface MyRequestCallback {

    void onSuccess(String response);

    void onFailure(Throwable throwable);
}
