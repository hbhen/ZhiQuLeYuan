package com.tongyuan.android.zhiquleyuan.interf;

import com.tongyuan.android.zhiquleyuan.bean.AddMemberToGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.BabyInfoResultBean;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.DelMembFromGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteBabyInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

    /**
 * Created by android on 2017/3/2.
 */

public interface AllInterface {
    //    添加,修改宝宝(或者个人)的信息  3.4.9
    @POST("busi")
    Call<BabyInfoResultBean> sendBabyInfoResult(@Query("params") String params);

    //删除宝宝的信息(或者个人信息) 3.4.10
    @GET("busi")
    Call<DeleteBabyInfoReSBean> delteBabyInfoReult(@Query("params") String params);
//    //如果是get请求呢
//    @GET("busi")
//    Call<BabyInfoResultBean> sendBabyInfoResult1(@Query("params") String params);
    //上传宝宝的或者User的头像   3.4.9
//    @POST("busi")
//    Call<String> sendImg(@Part("babyImg"))

    //查询根据登录用户创建的宝宝的列表 3.4.44
    @GET("busi")
    Call<QueryBabyListResult> getBabyListResult(@Query("params") String params);

    //请求首页gridview的目录信息(九宫格)3.4.25
    @GET("busi")
    Call<DiscoveryGridResultBean> getDiscoveryGridResult(@Query("params") String params);

    //请求首页gridview的二级目录信息,点击之后的信息3.4.29
    @GET("busi")
    Call<DiscoveryGridSecondaryResultBean> getDiscoveryGridSecondaryResult(@Query("params") String params);

    //请求推荐内容网络请求
    @GET("busi")
    Call<DiscoveryListResultBean> getDiscoveryListResult(@Query("params") String params);

    //添加/修改玩具信息
    @GET("busi")
    Call<AddToyResultBean> getAddToyResult(@Query("params") String params);

    //查询玩具通话历史
    @GET("busi")
    Call<CallHistoryResultBean> getCallHistoryResult(@Query("params") String params);

    //查询单个玩具信息
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
}
