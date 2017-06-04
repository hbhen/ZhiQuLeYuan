package com.tongyuan.android.zhiquleyuan.interf;

import com.tongyuan.android.zhiquleyuan.bean.AddMemberToGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.BabyInfoResultBean;
import com.tongyuan.android.zhiquleyuan.bean.BindBabyToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.DelMembFromGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteBabyInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoRes;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by android on 2017/3/2.
 */

public interface AllInterface {
    //    添加,修改宝宝(或者个人)的信息  3.4.9
    @POST("busi")
    Call<BabyInfoResultBean> sendBabyInfoResult(@Query("params") String params);

    @Multipart
    @POST("busi")
    Call<BabyInfoResultBean> BABY_INFO_RESULT_BEAN_CALL(@Part("description") RequestBody description, @Part("file") MultipartBody.Part file);
    //删除宝宝的信息(或者个人信息) 3.4.10
    @GET("busi")
    Call<DeleteBabyInfoReSBean> delteBabyInfoReult(@Query("params") String params);
//    //如果是get请求呢
//    @GET("busi")
//    Call<BabyInfoResultBean> sendBabyInfoResult1(@Query("params") String params);
//    上传宝宝的或者User的头像   3.4.9
//    @POST("busi")
//    Call<String> sendImg(@Part("babyImg"));

    //查询根据登录用户创建的宝宝的列表 3.4.44
    @GET("busi")
    Call<QueryBabyListResult> getBabyListResult(@Query("params") String params);

    //查询宝宝列表 (根据玩具id) 3.4.24
    @GET("busi")
    Call<QueryBabyListFromToyIdRes> QUERY_BABY_LIST_FROM_TOY_ID_CALL(@Query("params") String params);

    //请求首页gridview的目录信息(九宫格)3.4.25
    @GET("busi")
    Call<SuperModel<DiscoveryGridItemBean>> getDiscoveryGridResult(@Query("params") String params);

    //请求首页gridview的二级目录信息,点击之后的信息3.4.29
    @GET("busi")
    Call<DiscoveryGridSecondaryResultBean> getDiscoveryGridSecondaryResult(@Query("params") String params);

    //请求推荐内容网络请求
    @GET("busi")
    Call<DiscoveryListResultBean> getDiscoveryListResult(@Query("params") String params);

    //添加/修改玩具信息 3.4.11
    @GET("busi")
    Call<AddToyResultBean> getAddToyResult(@Query("params") String params);

    //查询玩具通话历史 3.4.42
    @GET("busi")
    Call<CallHistoryResultBean> getCallHistoryResult(@Query("params") String params);

    //查询单个玩具信息 3.4.21
    @GET("busi")
    Call<SingleToyInfoRESBean> getSingleToyInfoResult(@Query("params") String params);

    //查询群成员(根据玩具ID) 3.4.48
    @GET("busi")
    Call<QueryToyMemberReSBean> QUERY_TOY_MEMBER_RES_BEAN_CALL(@Query("params") String params);

    //查询单个用户(按账号)3.4.22
    @GET("busi")
    Call<QuerySingleUserInfoReSBean> getQuerySingleUserInfoResult(@Query("params") String params);

    //查询我的推送 3.4.36
    @GET("busi")
    Call<QueryMyPushResBean> getQueryMyPushResult(@Query("params") String params);

    //管理员踢用户出玩具群 3.4.46
    @GET("busi")
    Call<DelMembFromGroupReSBean> DEL_MEMB_FROM_GROUP_RES_BEAN_CALL(@Query("params") String params);

    //邀请 3.4.45
    @GET("busi")
    Call<AddMemberToGroupReSBean> ADD_MEMBER_TO_GROUP_RES_BEAN_CALL(@Query("params") String params);

    //查询玩具正在播放文件 3.4.43
    @GET("busi")
    Call<QueryPlayingMusicResBean> QUERY_PLAYING_MUSIC_RES_BEAN_CALL(@Query("params") String params);

    //本地播放申请 3.4.38
    @GET("busi")
    Call<LocalPlayApplyResBean> LOCAL_PLAY_APPLY_RES_BEAN_CALL(@Query("params") String params);

    //通话控制  3.4.5
    @GET("jpush")
    Call<CallToToyRes> CALL_TO_TOY_RES_CALL(@Query("params") String params);

    //拉去玩具及时状态 3.4.55
    @GET("busi")
    Call<GetInstantStateInfoRes> GET_INSTANT_STATE_INFO_RES_CALL(@Query("params") String params);

    //绑定玩具和宝宝 3.4.18
    @GET("busi")
    Call<BindBabyToToyRes> BIND_BABY_TO_TOY_RES_CALL(@Query("params") String params);
}
