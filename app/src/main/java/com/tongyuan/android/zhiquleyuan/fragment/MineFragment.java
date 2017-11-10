package com.tongyuan.android.zhiquleyuan.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.AboutActivity;
import com.tongyuan.android.zhiquleyuan.activity.ActivityLogin;
import com.tongyuan.android.zhiquleyuan.activity.BabyInfoListActivity;
import com.tongyuan.android.zhiquleyuan.activity.MyBabyActivity;
import com.tongyuan.android.zhiquleyuan.activity.MyCollectionActivity;
import com.tongyuan.android.zhiquleyuan.activity.MyPushActivity;
import com.tongyuan.android.zhiquleyuan.activity.MySuggestionActivity;
import com.tongyuan.android.zhiquleyuan.activity.MyUpdateActivity;
import com.tongyuan.android.zhiquleyuan.activity.PlayListActivity;
import com.tongyuan.android.zhiquleyuan.activity.SetUserInfoActivity;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListRequest;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReQBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReSBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tongyuan.android.zhiquleyuan.activity.ActivityLogin.LOGINOK;

/**
 * Created by android on 2016/12/3.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private static final int SETINFO = 8;
    public static final int MINELOGIN = 501;
    public final int LOGINSUCCESS = 7;
    private LinearLayout myBaby;
    private LinearLayout myCollection;
    private LinearLayout myPlay;
    private LinearLayout mySuggestion;
    private LinearLayout myPush;
    private LinearLayout myUpdate;
    private LinearLayout myAbout;
    private RelativeLayout mLogin;
    private ImageView mPic;
    private TextView mMineTitle;
    private String mToken;
    private LinearLayout mMyLogout;
    private String mPhoneNum;
    private List<QueryBabyListResult.BODYBean.LSTBean> mLst;
    private Intent mIntent;
    private TextView mTv_fragment_mine_desc;
    private QueryBabyListResult mBody;
    private final String TAG = "minefragment";
    private String mUsername;
    private String mUserimg;
    private Toolbar mToolbar;
    private ImageView mBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {

        mIntent = new Intent();

        View mineRoot = inflater.inflate(R.layout.fragment_mine, null);

        return mineRoot;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("tag", "onAttach: went");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("tag", "onCreate: went");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTv_fragment_mine_desc = (TextView) getActivity().findViewById(R.id.tv_fragment_mine_desc);//登录后的描述信息
        mMineTitle = (TextView) getActivity().findViewById(R.id.tv_fragment_mine_title);//点击登录

        myBaby = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_baby);
        myCollection = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_collection);
        myPlay = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_play);
        myPush = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_push);
        mySuggestion = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_suggestion);
        myUpdate = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_update);
        myAbout = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_about);
        mLogin = (RelativeLayout) getActivity().findViewById(R.id.rl_fragment_mine_clicklogin);

        mPic = (ImageView) getActivity().findViewById(R.id.iv_fragment_mine);//登录后显示的头像
        mMyLogout = (LinearLayout) getActivity().findViewById(R.id.ll_fragment_mine_logout);
        initData();
        Log.i("tag", "onCreateView: went");
//        showDifferentLoginInfo();

        mPic.setOnClickListener(this);
        myBaby.setOnClickListener(this);
        myCollection.setOnClickListener(this);
        myPlay.setOnClickListener(this);
        myPush.setOnClickListener(this);
        mySuggestion.setOnClickListener(this);
        myUpdate.setOnClickListener(this);
        myAbout.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mMyLogout.setOnClickListener(this);

        Log.i("tag", "onActivityCreated: went");
    }

    private void initData() {
        //还是应该有一个网络请求,获取用户的信息
        mToken = SPUtils.getString(getContext(), "token", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");
        getUserInfo();
        mUsername = SPUtils.getString(getContext(), "username", "");
        mUserimg = SPUtils.getString(getContext(), "userimg", "");
        Log.i(TAG, "initData: mUsername " + mUsername);
        Log.i(TAG, "initData: mUserimg " + mUserimg);
    }

    private void getUserInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allinterface = retrofit.create(AllInterface.class);
        QuerySingleUserInfoReQBean.BODYBean bodybean = new QuerySingleUserInfoReQBean.BODYBean(mPhoneNum);
        QuerySingleUserInfoReQBean querysinglebean = new QuerySingleUserInfoReQBean("RES", "QRYUSER", mPhoneNum, new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodybean, "", mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(querysinglebean);
        Call<QuerySingleUserInfoReSBean> querySingleUserInfoResult = allinterface.getQuerySingleUserInfoResult(s);
        querySingleUserInfoResult.enqueue(new Callback<QuerySingleUserInfoReSBean>() {
            @Override
            public void onResponse(Call<QuerySingleUserInfoReSBean> call, Response<QuerySingleUserInfoReSBean>
                    response) {
                if (response == null) {
                    ToastUtil.showToast(mContext, "数据请求为空");
                    return;
                } else {
                    String birthday = response.body().getBODY().getBIRTHDAY();
                    String img = response.body().getBODY().getIMG();
                    String name = response.body().getBODY().getNAME();
                    String nick = response.body().getBODY().getNICK();
                    String sex = response.body().getBODY().getSEX();
                    showDifferentLoginInfo();
                }
            }

            @Override
            public void onFailure(Call<QuerySingleUserInfoReSBean> call, Throwable t) {
                ToastUtil.showToast(mContext, t.toString());
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        Log.i(TAG, "minefragment:onResume: token" + mToken);
        mToken = SPUtils.getString(getActivity(), "token", "");//重新赋值mToken
        showDifferentLoginInfo();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("tag", "onStart: went");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_fragment_mine_clicklogin:
                Log.i(TAG, "onClick: mToken undelelte1" + mToken);
                mToken = SPUtils.getString(getActivity(), "token", "");
                Log.i(TAG, "onClick: mToken undelelte2" + mToken);
                if (mToken.equals("")) {
                    //进入登录页面
                    mIntent.setClass(getActivity(), ActivityLogin.class);
                    getActivity().startActivityForResult(mIntent, MINELOGIN);
                    ToastUtil.showToast(getActivity(), "进入登录信息页面");
                } else {
                    /*
                    *  mToken = SPUtils.getString(getContext(), "token", "");
        mPhoneNum = SPUtils.getString(getContext(), "phoneNum", "");
        mUsername = SPUtils.getString(getContext(), "username", "");
        mUserimg = SPUtils.getString(getContext(), "userimg", "");
                    * */
                    //进入用户设置页面
                    mIntent.setClass(getContext(), SetUserInfoActivity.class);
                    startActivity(mIntent);
                    ToastUtil.showToast(getActivity(), "进入设置用户信息页面");
                }
                break;
            case R.id.ll_fragment_mine_baby:
                Log.i(TAG, "onClick: minefragment" + mToken + "------" + SPUtils.getString(getContext(), "token", ""));
                if (!mToken.equals("")) {
                    getListInfo();
                } else {
                    Log.i(TAG, "onClick: minefragment走没有??");
                    ToastUtil.showToast(getActivity(), "您当前没有登录");
                }

                break;
            case R.id.ll_fragment_mine_collection:
                if (!mToken.equals("")) {
                    mIntent.setClass(getActivity(), MyCollectionActivity.class);
                    startActivity(mIntent);
                } else {
                    ToastUtil.showToast(getActivity(), "您当前没有登录");
                }
                break;
            case R.id.ll_fragment_mine_play:
                if (!mToken.equals("")) {
                    mIntent.setClass(getActivity(), PlayListActivity.class);
                    startActivity(mIntent);
                } else {
                    ToastUtil.showToast(getContext(), "您当前没有登录");

                }
                break;
            case R.id.ll_fragment_mine_push:
                if (!mToken.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("babyinfo", mBody);
                    mIntent.putExtras(bundle);
                    mIntent.setClass(getActivity(), MyPushActivity.class);
                    startActivity(mIntent);
                } else {
                    ToastUtil.showToast(getContext(), "您当前没有登录");
                }

                break;
            case R.id.ll_fragment_mine_suggestion:
                mIntent.setClass(getActivity(), MySuggestionActivity.class);
                startActivity(mIntent);
                break;
            case R.id.ll_fragment_mine_update:
                mIntent.setClass(getActivity(), MyUpdateActivity.class);
                startActivity(mIntent);
                break;
            case R.id.ll_fragment_mine_about:
                mIntent.setClass(getActivity(), AboutActivity.class);
//                mIntent.setClass(getActivity(), ToyUpdateActivity.class);
                startActivity(mIntent);
                break;
            case R.id.ll_fragment_mine_logout:
                /**
                 * 退出登录的详细逻辑是:1,点击退出登录,执行清除token的功能.
                 * 2,先执行弹窗的提示,确认就清除,并且返回"点击登录的那个"mine"的界面;取消就返回之前的界面.
                 * */
                //有退出登录的接口 3.4.54
                LogOut();
                //弹窗提示
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("确认退出");
                builder.setMessage("是否确认退出");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        SPUtils.remove(getActivity(), "token");
                        SPUtils.putString(getActivity(), "token", "");
                        mToken = SPUtils.getString(getActivity(), "token", "");
                        Log.i(TAG, "onClick: mToken delelte" + mToken);
//                        Log.i(TAG, "onClick: token" + SPUtils.getString(getActivity(), "token", ""));
                        mMineTitle.setText("点击登录");
                        Glide.with(getActivity()).load(R.drawable.player_cover_default).asBitmap().into(mPic);
                        mTv_fragment_mine_desc.setText("登录后设置用户的信息");
                        mMyLogout.setVisibility(View.GONE);
                        ToastUtil.showToast(getContext(), "sure");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDifferentLoginInfo();
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        showDifferentLoginInfo();
                    }
                });
                builder.show();
                break;
//            case iv_fragment_mine:
//                mIntent.setClass(getActivity(), AboutActivity.class);
//                startActivity(mIntent);
//                break;
            default:
                break;
        }
    }

    private void LogOut() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //TODO 用户退出登录的接口  未完成2017/07/09 15:57

    }

    private void getListInfo() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(date);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QueryBabyListRequest.BODYBean queryBabyListBody = new QueryBabyListRequest.BODYBean("10", "1");
        QueryBabyListRequest queryBabyListRequest = new QueryBabyListRequest("REQ", "QMYB", mPhoneNum, time,
                queryBabyListBody, "", mToken, "1");
        Gson gson = new Gson();
        String babyListJson = gson.toJson(queryBabyListRequest);
        Call<QueryBabyListResult> babyListResult = allInterface.getBabyListResult(babyListJson);
        babyListResult.enqueue(new Callback<QueryBabyListResult>() {
            @Override
            public void onResponse(Call<QueryBabyListResult> call, Response<QueryBabyListResult> response) {
                String listResponse = response.body().toString();
                mBody = response.body();
                Log.i("111", "listResponse: " + listResponse);
                mLst = response.body().getBODY().getLST();
                if (response.body().getCODE().equals("0")) {
                    if (mLst.size() != 0) {
                        ToastUtil.showToast(getActivity(), "进入宝宝信息里列表");
                        mIntent.setClass(getActivity(), BabyInfoListActivity.class);
                        startActivity(mIntent);
                    } else {
                        mIntent.setClass(getActivity(), MyBabyActivity.class);
                        startActivity(mIntent);
                    }
                } else {
                    ToastUtil.showToast(getActivity(), response.body().getMSG());
                    Log.i(TAG, "onResponse: minefragment" + response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<QueryBabyListResult> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "联网失败,网络异常");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MINELOGIN && resultCode == LOGINOK) {
            Bundle bundle = data.getExtras();
            mUsername = bundle.getString("username");
            mUserimg = bundle.getString("userimg");
//            Log.i("444444", "minefragment:onActivityResult: " + mUsername);
//            Log.i("444444", "minefragment:onActivityResult: " + mUserimg);
            mMineTitle.setText(mUsername);
            Glide.with(getContext()).load(mUserimg).asBitmap().into(mPic);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        SPUtils.getString(getActivity(),"username","");
//        SPUtils.getString(getActivity(),"userimg","");
        mToken = SPUtils.getString(getActivity(), "token", "");//重新赋值mToken

        Log.i(TAG, "minefragment:onResume: token" + mToken);

        showDifferentLoginInfo();
        Log.i("tag", "onResume: went");

    }

    public void showDifferentLoginInfo() {
        mToken = SPUtils.getString(getActivity(), "token", "");
        Log.i(TAG, "onClick: mToken delelte3" + mToken);
        if (!mToken.equals("")) {
            mUsername = SPUtils.getString(getActivity(), "username", "");//这个拿到的值是用户的id
            mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
            Log.i(TAG, "showDifferentLoginInfo: " + mUsername);
            mUserimg = SPUtils.getString(getActivity(), "userimg", "");
            mMyLogout.setVisibility(View.VISIBLE);
            if (mUsername.equals("")) {
                mMineTitle.setText("设置用户名");
            } else {
                mMineTitle.setText(mUsername);
            }
            mTv_fragment_mine_desc.setText(mPhoneNum);
            Glide.with(getActivity()).load(mUserimg).placeholder(R.drawable.player_cover_default).into(mPic);
        } else {
            mMyLogout.setVisibility(View.GONE);
            mMineTitle.setText("点击登录");
            mTv_fragment_mine_desc.setText("登录后设置机主的基本信息");
            Glide.with(getActivity()).load(R.drawable.player_cover_default).asBitmap().into(mPic);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("tag", "onPause: went");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("tag", "onStop: went");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("tag", "onDetach: went");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag", "onDestroy: went");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("tag", "onDestroyView: went");
    }
}