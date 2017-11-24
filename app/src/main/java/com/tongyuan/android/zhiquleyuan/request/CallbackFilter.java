package com.tongyuan.android.zhiquleyuan.request;

import android.content.Intent;

import com.tongyuan.android.zhiquleyuan.activity.ActivityLogin;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.ZQLYApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by zgg on 2017/8/13.
 */

public abstract class CallbackFilter<T> implements Callback<T> {

    public abstract void onResponseFilter(Call<T> call, Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        SuperModel<T> result = (SuperModel<T>) response.body();
        final String notLogin = "1";
        if(notLogin.equals(result.CODE)) {
            //跳转到Login页
            Intent intent = new Intent();
            intent.setClass(ZQLYApp.context.getApplicationContext(), ActivityLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ZQLYApp.context.getApplicationContext().startActivity(intent);
        } else {
            onResponseFilter(call, response);
        }
    }
}
