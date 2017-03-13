package com.tongyuan.android.zhiquleyuan.interf;

import com.tongyuan.android.zhiquleyuan.bean.QueryToyResultBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by android on 2017/3/9.
 */

public interface QueryToyInterface {

    @GET("busi")
    Call<QueryToyResultBean> getToyResult (@Query("params") String params);
}
