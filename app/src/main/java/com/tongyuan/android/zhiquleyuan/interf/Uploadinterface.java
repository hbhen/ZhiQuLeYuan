package com.tongyuan.android.zhiquleyuan.interf;

import com.tongyuan.android.zhiquleyuan.bean.UpLoadRecordBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by android on 2017/2/21.
 */

public interface Uploadinterface {
    @GET("busi")
    Call<UpLoadRecordBean> getResult(@Query("params") String params);
}
