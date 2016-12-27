package com.tongyuan.android.zhiquleyuan.http;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by android on 2016/12/27.
 */

public class OkHttpRequestManager implements MyRequestManager {

    private final Handler handler;
    private final OkHttpClient okHttpClient;
    public static final MediaType TYPE_JSON=MediaType.parse("application/json; charset=utf-8");

    //单例模式
    public static OkHttpRequestManager getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final OkHttpRequestManager INSTANCE=new OkHttpRequestManager();
    }
    //构造方法 ,初始化参数
    public OkHttpRequestManager(){
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        //在哪个线程创建该对象，则最后的请求结果将在该线程回调
        handler = new Handler();
    }

    @Override
    public void get(String url, MyRequestCallback myRequestCallBack) {
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        addCallBack(myRequestCallBack, request);
    }



    @Override
    public void post(String url, String requestBodyJson, MyRequestCallback myRequestCallBack) {
        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        addCallBack(myRequestCallBack, request);
    }

    @Override
    public void put(String url, String requestBodyJson, MyRequestCallback myRequestCallBack) {
        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        addCallBack(myRequestCallBack, request);
    }

    @Override
    public void delete(String url, String requestBodyJson, MyRequestCallback myRequestCallBack) {
        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();
        addCallBack(myRequestCallBack, request);
    }

    private void addCallBack(final MyRequestCallback myRequestCallBack, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myRequestCallBack.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                if (response.isSuccessful()){
                    final String json = response.body().toString();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myRequestCallBack.onSuccess(json);
                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myRequestCallBack.onFailure(new IOException(response.message() + ",url=" + call.request().url().toString()));
                        }
                    });
                }
            }
        });
    }


}
