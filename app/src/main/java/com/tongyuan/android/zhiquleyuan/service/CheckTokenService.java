package com.tongyuan.android.zhiquleyuan.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.bean.CheckTokenReqBean;
import com.tongyuan.android.zhiquleyuan.bean.CheckTokenResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android on 2017/9/11.
 */
public class CheckTokenService extends IntentService {

    private static boolean control = false;
    private static final String TAG = "service";
    private AlarmManager mSystemService;
    private PendingIntent mPendingIntent;

    public CheckTokenService() {
        super("checktoken");
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        String token = SPUtils.getString(this, "TOKEN", "");
//        mSystemService = (AlarmManager) getSystemService(ALARM_SERVICE);
//        long lo = (long) SystemClock.elapsedRealtime() + 600000;
//        Intent intent1 = new Intent(this, CheckTokenReceiver.class);
//        mPendingIntent = PendingIntent.getBroadcast(this, 0, intent1, 0);
//        mSystemService.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, lo, mPendingIntent);
//        //判断是否第一次进来,一般来说用户登录就直接开启服务 判断是第一次登录就不用请求
//        if (!control) {
//            control = true;
//            Log.e(TAG, "第一次进来");
//        } else {
//            Log.e(TAG, "第二次进来");
//            Log.e(TAG, "onStartCommand: 开始请求网络获取Token" + token);
//            Log.e(TAG, "隔多久请求" + lo);
//            if (!TextUtils.isEmpty(token)) {
//                //这里放请求网络的逻辑 可以先打个log看看
//                checkToken();
//            } else {
//                Toast.makeText(getApplicationContext(), "你好请重新登录", Toast.LENGTH_SHORT).show();
//            }
//        }
//        return super.onStartCommand(intent, flags, startId);
//    }

    @Override
    protected void onHandleIntent(Intent intent) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                checkToken();

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 10000);
    }

    private void checkToken() {
        Log.i(TAG, "checkToken: 走没有走");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        CheckTokenReqBean.BODYBean bodyBean = new CheckTokenReqBean.BODYBean("A", "APP", "", "", "", "", "", "", "");
        CheckTokenReqBean checkTokenReqBean = new CheckTokenReqBean("REQ", "HEART", "", new SimpleDateFormat
                ("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(CheckTokenService.this,
                "token", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(checkTokenReqBean);
        Call<CheckTokenResBean> checkTokenResBeanCall = allInterface.CHECK_TOKEN_RES_BEAN_CALL(s);
        Log.i(TAG, "checkToken: 走到enqueu前面了");
        checkTokenResBeanCall.enqueue(new Callback<CheckTokenResBean>() {
            @Override
            public void onResponse(Call<CheckTokenResBean> call, Response<CheckTokenResBean> response) {
                Log.i(TAG, "checkToken000: 走到enqueu后面了");
                if (response != null && response.body().getBODY().getOK() != null) {
                    if (response.body().getTOKEN().equals(SPUtils.getString(CheckTokenService.this, "token", ""))) {
                        Log.i(TAG, "onResponse000: " + response.body().toString());
                        Log.i(TAG, response.message());
                    } else {
                        SPUtils.putString(CheckTokenService.this, "token", response.body().getTOKEN());
                        Log.i(TAG, "onResponse: checktokenservice" + "-----" + response.body().getTOKEN() + "!!");
                    }
                } else {
                    SPUtils.putString(CheckTokenService.this, "token", "");
                    Log.i(TAG, "onResponse111: " + response.message());

                    //给其他的activity发送信息,让他们都进入登录界面 eventbus  rxjava?现在就用通知吧.
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("checktoken");
                    Intent intent = new Intent();
                    intent.putExtra("token", response.body().getTOKEN());
                    intent.setAction("checktoken");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sendOrderedBroadcast(intent, null);
                }
            }

            @Override
            public void onFailure(Call<CheckTokenResBean> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
