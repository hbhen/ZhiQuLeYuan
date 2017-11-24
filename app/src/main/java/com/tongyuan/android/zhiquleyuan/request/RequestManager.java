package com.tongyuan.android.zhiquleyuan.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicReqBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromNormalUserReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromNormalUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromPowerUserReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromPowerUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListRequsetBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoverySubReqBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoREQBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.request.base.BaseRequest;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
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
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serves = retrofit.create(AllInterface.class);

        mGson = new Gson();

    }

    public static RequestManager getInstance() {

        if (instance == null)
            instance = new RequestManager();
        return instance;

    }

    public Call<SuperModel<DiscoveryGridItemBean>> getDiscoveryGridResult(BaseRequest params) {

        String json = mGson.toJson(params);
        return serves.getDiscoveryGridResult(json);

    }

    public Call<SuperModel<DiscoveryGridSecondaryResultBean>> getDiscoverySubList(Context cxt, DiscoverySubReqBean bodyBean) {
        BaseRequest baseRequest = new BaseRequest<>(cxt, bodyBean, "QRYRES");
        String json = mGson.toJson(baseRequest);
        return serves.getDiscoveryGridSecondaryResult(json);
    }

    /**
     * 请求音乐详情页
     */
    public Call<SuperModel<LocalPlayApplyResBean>> requestMusicDetail(BaseRequest params) {
        String json = mGson.toJson(params);
        return serves.LOCAL_PLAY_APPLY_RES_BEAN_CALL(json);
    }

    /**
     * 请求发现列表页数据
     */
    public Call<SuperModel<DiscoveryListResultBean.BODYBean>> getDiscoveryListResult(Context context, DiscoveryListRequsetBean.BODYBean params) {
        BaseRequest request = new BaseRequest<>(context, params, "QRYREC");
        String json = mGson.toJson(request);
        return serves.getDiscoveryListResult2(json);

    }

    /**
     * 请求玩具详情页数据
     */
    public Call<SuperModel<SingleToyInfoRESBean.BODYBean>> getToyDetail(Context context, SingleToyInfoREQBean.BODYBean params) {
        BaseRequest request = new BaseRequest<>(context, params, "QTOY");
        String json = mGson.toJson(request);
        return serves.getSingleToyInfoResult(json);
    }

    /**
     * 删除玩具
     */
    public Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> deleteSeletedToy(Context context, DeleteToyFromPowerUserReqBean params) {
        BaseRequest request = new BaseRequest<>(context, params, "RESET");
        String json = mGson.toJson(request);
        return serves.DELETE_TOY_FROM_POWER_USER_RES_BEAN_CALL(json);
    }

    /**
     * 删除普通玩具
     */
    public Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> deleteSeletedNormalToy(Context context, DeleteToyFromNormalUserReqBean
            .BODYBean params) {
        BaseRequest request = new BaseRequest<>(context, params, "DATOY");
        String json = mGson.toJson(request);
        return serves.DELETE_TOY_FROM_NORMAL_USER_RES_BEAN_CALL(json);
    }

    /**
     * 向玩具端发送指令，播放
     */
    public Call<ControlToyPlayMusicResBean> ToyPlayCommand(Context context, ControlToyPlayMusicReqBean.ParamBean paramBean) {

        String mToken = SPUtils.getString(context, "token", "");
        ControlToyPlayMusicReqBean control_play = new ControlToyPlayMusicReqBean("control_play", paramBean, mToken);
        Gson gson = new Gson();
        String json = gson.toJson(control_play);
        return serves.CONTROL_TOY_PLAY_MUSIC_RES_BEAN_CALL(json);

    }


}
