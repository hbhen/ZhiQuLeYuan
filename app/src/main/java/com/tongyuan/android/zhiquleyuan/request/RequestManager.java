package com.tongyuan.android.zhiquleyuan.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by zgg on 2017/6/4.
 */

public class RequestManager {

    private static RequestManager instance;
    private static AllInterface serves;
    private static Gson mGson;

    private RequestManager() {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        serves = retrofit.create(AllInterface.class);
        mGson = new Gson();
    }

    public static RequestManager getInstance() {
        if(instance == null)
            instance = new RequestManager();
        return instance;
    }

    public Call<SuperModel<DiscoveryGridItemBean>> getDiscoveryGridResult(BaseRequest params) {
        String json = mGson.toJson(params);
        return serves.getDiscoveryGridResult(json);
    }

    public Call<SuperModel<DiscoveryGridSecondaryResultBean>>  getDiscoveryGridSecondaryResult(BaseRequest params) {
        String json = mGson.toJson(params);
        return serves.getDiscoveryGridSecondaryResult(json);
    }

    public  Call<SuperModel<LocalPlayApplyResBean>> requestMusicDetail(BaseRequest params) {
        String json = mGson.toJson(params);
        return serves.LOCAL_PLAY_APPLY_RES_BEAN_CALL(json);
    }
//    public  Call<SuperModel<QueryRecordingResBean>> queryRecordingResBean (BaseRequest params) {
//        String json = mGson.toJson(params);
//        return serves.QUERY_RECORDING_RES_BEAN_CALL(json);
//    }
}
