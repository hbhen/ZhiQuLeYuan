package com.tongyuan.android.zhiquleyuan.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.activity.VideoActivity;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android on 2017/6/3.
 */

public class CallManager {

    public static void CallToToy(final String toyId, final Context context) {

        final String token = SPUtils.getString(context, "TOKEN", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        CallToToyReq.ParamBean param = new CallToToyReq.ParamBean(toyId, "1", "");
        CallToToyReq callToToyReq = new CallToToyReq("contact_toy", param, token);
        Gson gson = new Gson();
        String s = gson.toJson(callToToyReq);
        Call<CallToToyRes> callToToyResCall = allInterface.CALL_TO_TOY_RES_CALL(s);
        callToToyResCall.enqueue(new Callback<CallToToyRes>() {
            @Override
            public void onResponse(Call<CallToToyRes> call, Response<CallToToyRes> response) {

                Log.i("555555", "onResponse:+response " + response.body().toString());
                String roomId = response.body().getRoomid();
                if (roomId == null) {
                    ToastUtil.showToast(context, "房间号不存在");

                    if (response.body().getCode().equals("-10008")) {
                        ToastUtil.showToast(context, "推送失败");
                    } else if (response.body().getCode().equals("-10009")) {
                        ToastUtil.showToast(context, "玩具未登录");
                    } else if (response.body().getCode().equals("-10012")) {
                        ToastUtil.showToast(context, "玩具通话中");
                    }
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("roomid", roomId);
                bundle.putString("token", token);
                bundle.putString("toyid", toyId);

                context.startActivity(new Intent(context, VideoActivity.class).putExtras(bundle));
            }

            @Override
            public void onFailure(Call<CallToToyRes> call, Throwable t) {
                Log.i("111111", t.toString());
                ToastUtil.showToast(context,"当前网络异常");
            }
        });
    }
}
