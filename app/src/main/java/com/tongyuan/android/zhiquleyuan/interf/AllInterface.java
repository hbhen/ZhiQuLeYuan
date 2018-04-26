package com.tongyuan.android.zhiquleyuan.interf;

import com.tongyuan.android.zhiquleyuan.bean.AddInCollectionResBean;
import com.tongyuan.android.zhiquleyuan.bean.AddMemberToGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.AddRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.BabyInfoResultBean;
import com.tongyuan.android.zhiquleyuan.bean.BindBabyToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.CallHistoryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.bean.ChangeRecordingNameResBean;
import com.tongyuan.android.zhiquleyuan.bean.CheckTokenResBean;
import com.tongyuan.android.zhiquleyuan.bean.CommitSuggestionResBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyPlayRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyVolumeRes;
import com.tongyuan.android.zhiquleyuan.bean.DelMembFromGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteBabyInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyCollectionResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPlayResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyPushResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteNodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromNormalUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromPowerUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridSecondaryResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.GetInstantStateInfoRes;
import com.tongyuan.android.zhiquleyuan.bean.GetNewestVersionResBean;
import com.tongyuan.android.zhiquleyuan.bean.GetSmsCodeResBean;
import com.tongyuan.android.zhiquleyuan.bean.GetSmsCodeValueResBean;
import com.tongyuan.android.zhiquleyuan.bean.LocalPlayApplyResBean;
import com.tongyuan.android.zhiquleyuan.bean.ModifyRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.bean.PhoneHeartResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdRes;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyCollectionResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPlayResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPushResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryPlayingMusicResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryRecordingResBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.bean.RegistAndLoginResBean;
import com.tongyuan.android.zhiquleyuan.bean.SearchResBean;
import com.tongyuan.android.zhiquleyuan.bean.SetNodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.bean.TransferPermissionsResBean;
import com.tongyuan.android.zhiquleyuan.bean.UnbindBabyToToyResBean;
import com.tongyuan.android.zhiquleyuan.bean.UpdateToyVersionResBean;
import com.tongyuan.android.zhiquleyuan.bean.UserInfoResBean;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;

import java.util.List;

import okhttp3.MultipartBody;
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
    //添加,修改宝宝(或者个人)的信息  3.4.9  此接口是设置用户的
    @POST("busi")
    Call<UserInfoResBean> sendUserInfoResult(@Query("params") String params);

    @Multipart
    @POST("busi")
    Call<UserInfoResBean> USER_INFO_RES_BEAN_CALL(@Part List<MultipartBody.Part> partList);

    //    添加,修改宝宝(或者个人)的信息  3.4.9   此接口是设置宝宝的
    @POST("busi")
    Call<BabyInfoResultBean> sendBabyInfoResult(@Query("params") String params);

    //    @Multipart
//    @POST("busi")
//    Call<BabyInfoResultBean> BABY_INFO_RESULT_BEAN_CALL(@Body RequestBody description, @Part("file") MultipartBody
// .Part file);
//    @Multipart
//    @POST("busi")
//    Call<BabyInfoResultBean> BABY_INFO_RESULT_BEAN_CALL(@Part("params") String params, @Part("file") MultipartBody
// .Part file);
    @Multipart
    @POST("busi")
    Call<BabyInfoResultBean> BABY_INFO_RESULT_BEAN_CALL(@Part List<MultipartBody.Part> partList);
//    Call<BabyInfoResultBean> BABY_INFO_RESULT_BEAN_CALL(@Part("params") BabyInfoRequestBean params, @Part("file")
// MultipartBody.Part file);

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
    Call<SuperModel<DiscoveryGridSecondaryResultBean>> getDiscoveryGridSecondaryResult(@Query("params") String params);

    //请求推荐内容网络请求 3.4.30
    @GET("busi")
    Call<SuperModel<DiscoveryListResultBean.BODYBean>> getDiscoveryListResult2(@Query("params") String params);

    //添加/修改玩具信息 3.4.11
    @GET("busi")
    Call<AddToyResultBean> getAddToyResult(@Query("params") String params);

    //查询玩具通话历史 3.4.42
    @GET("busi")
    Call<CallHistoryResultBean> getCallHistoryResult(@Query("params") String params);

    //查询单个玩具信息 3.4.21
    @GET("busi")
    Call<SuperModel<SingleToyInfoRESBean.BODYBean>> getSingleToyInfoResult(@Query("params") String params);

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
    Call<SuperModel<LocalPlayApplyResBean>> LOCAL_PLAY_APPLY_RES_BEAN_CALL(@Query("params") String params);

    //通话控制  3.4.51
    @GET("jpush")
    Call<CallToToyRes> CALL_TO_TOY_RES_CALL(@Query("params") String params);

    //拉去玩具及时状态 3.4.55
    @GET("busi")
    Call<GetInstantStateInfoRes> GET_INSTANT_STATE_INFO_RES_CALL(@Query("params") String params);

    //绑定玩具和宝宝 3.4.18
    @GET("busi")
    Call<BindBabyToToyRes> BIND_BABY_TO_TOY_RES_CALL(@Query("params") String params);

    //设定玩具的音量 3.4.49
    @GET("jpush")
    Call<ControlToyVolumeRes> CONTROL_TOY_VOLUME_RES_CALL(@Query("params") String params);

    //控制玩具播放 音频 3.4.50
    @GET("jpush")
    Call<ControlToyPlayMusicResBean> CONTROL_TOY_PLAY_MUSIC_RES_BEAN_CALL(@Query("params") String params);

    //控制玩具播放 录音 3.4.50
    @GET("jpush")
    Call<ControlToyPlayRecordingResBean> CONTROL_TOY_PLAY_RECORDING_RES_BEAN_CALL(@Query("params") String params);

    //查询我的录音 3.4.37
    @GET("busi")
    Call<SuperModel<QueryRecordingResBean>> SUPER_MODEL_CALL_QUERY_RECORDING_RES_BEAN_CALL(@Query("params") String
                                                                                                   params);

    //没有封装的  3.4.37
    @GET("busi")
    Call<QueryRecordingResBean> QUERY_RECORDING_RES_BEAN_CALL(@Query("params") String params);

    //删除资源(录音文件) 3.4.28
    @GET("busi")
    Call<DeleteRecordingResBean> DELETE_RECORDING_RES_BEAN_CALL(@Query("params") String params);

    //添加资源(录音文件) 3.4.26
    @Multipart
    @POST("busi")
    Call<AddRecordingResBean> ADD_RECORDING_RES_BEAN_CALL(@Part List<MultipartBody.Part> partList);

    //修改资源(录音文件) 3.4.26
    @GET("busi")
    Call<ChangeRecordingNameResBean> CHANGE_RECORDING_NAME_RES_BEAN_CALL(@Query("params") String params);

    //修改资源(录音文件) 3.4.26
    @GET("busi")
    Call<ModifyRecordingResBean> MODIFY_RECORDING_RES_BEAN_CALL(@Query("params") String params);

    //查询我的收藏3.4.34
    @GET("busi")
    Call<QueryMyCollectionResBean> QUERY_MYCOLLECTION_RES_BEAN_CALL(@Query("params") String params);

    //删除我的收藏 3.4.33
    @GET("busi")
    Call<DeleteMyCollectionResBean> DELETE_MYCOLLECTION_RES_BEAN_CALL(@Query("params") String params);

    //查询我的播放3.4.35
    @GET("busi")
    Call<QueryMyPlayResBean> QUERY_MYPLAY_RES_BEAN_CALL(@Query("params") String params);

    //删除我的播放 3.4.56
    @GET("busi")
    Call<DeleteMyPlayResBean> DELETE_MYPLAY_RES_BEAN_CALL(@Query("params") String params);

    //删除玩具和宝宝关系 3.4.19
    @GET("busi")
    Call<UnbindBabyToToyResBean> UNBIND_BABY_TO_TOY_RES_BEAN_CALL(@Query("params") String params);

    //心跳接口(手机端)3.4.6
    @GET("busi")
    Call<PhoneHeartResBean> PHONEHEART_RES_BEAN_CALL(@Query("params") String params);

    //3.4.12 删除玩具信息(用户退出玩具群) 非管理员删除
    @GET("busi")
    Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> DELETE_TOY_FROM_NORMAL_USER_RES_BEAN_CALL(@Query("params") String params);

    //3.4.47 玩具重置(恢复未激活状态,解散群等) 管理员删除
    @GET("busi")
    Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> DELETE_TOY_FROM_POWER_USER_RES_BEAN_CALL(@Query("params") String params);

    //3.4.32 加入收藏
    @GET("busi")
    Call<AddInCollectionResBean> ADDINCOLLECTION_RES_BEAN_CALL(@Query("params") String params);

    //3.4.31 检索资源
    @GET("busi")
    Call<SearchResBean> SEARCH_RES_BEAN_CALL(@Query("params") String params);

    //3.4.56 意见反馈
    @GET("busi")
    Call<CommitSuggestionResBean> COMMIT_SUGGESTION_RES_BEAN_CALL(@Query("params") String params);

    //3.4.17 查询玩具免打扰时间段
    @GET("busi")
    Call<NodisturbTimeResBean> NODISTURB_TIME_RES_BEAN_CALL(@Query("params") String params);

    //3.4.57 删除我的推送
    @GET("busi")
    Call<DeleteMyPushResBean> DELETE_MY_PUSH_RES_BEAN_CALL(@Query("params") String params);

    //3.4.13添加/修改玩具属性(玩具持有人:1玩具初始音量3)
    @GET("busi")
    Call<TransferPermissionsResBean> TRANSFER_PERMISSIONS_RES_BEAN_CALL(@Query("params") String params);

    //3.4.15 设定免打扰时间
    @GET("busi")
    Call<SetNodisturbTimeResBean> SET_NODISTURB_TIME_RES_BEAN_CALL(@Query("params") String params);

    //3.4.16 删除免打扰时间
    @GET("busi")
    Call<DeleteNodisturbTimeResBean> DELETE_NODISTURB_TIME_RES_BEAN_CALL(@Query("params") String params);

    //3.4.6 心跳接口
    @GET("heart")
    Call<CheckTokenResBean> CHECK_TOKEN_RES_BEAN_CALL(@Query("params") String params);

    //3.4.2 获取最新版本
    @GET("lastverion")
    Call<GetNewestVersionResBean> GET_NEWEST_VERSION_CALL(@Query("params") String params);

    //3.4.7 获取短信接口授权号
    @GET("smscode")
    Call<GetSmsCodeResBean> GET_SMSCODE_RES_BEAN_CALL(@Query("params") String params);

    //3.4.8 获取短信验证码
    @GET("smscode")
    Call<GetSmsCodeValueResBean> GET_SMSCODEVALUE_RES_BEAN_CALL(@Query("params") String params);

    //3.4.4 用户注册登录
    @GET("register")
    Call<RegistAndLoginResBean> REGIST_AND_LOGIN_RES_BEAN_CALL(@Query("params") String params);

    //3.4.53  控制玩具更新程序
    @GET("jpush")
    Call<UpdateToyVersionResBean> UPDATE_TOY_VERSION_RES_BEAN_CALL(@Query("params") String params);

}
