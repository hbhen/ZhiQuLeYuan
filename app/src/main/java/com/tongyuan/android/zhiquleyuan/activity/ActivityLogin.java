package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.GetSmsCodeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.GetSmsCodeResBean;
import com.tongyuan.android.zhiquleyuan.bean.GetSmsCodeValueReqBean;
import com.tongyuan.android.zhiquleyuan.bean.GetSmsCodeValueResBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReQBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.RegistAndLoginReqBean;
import com.tongyuan.android.zhiquleyuan.bean.RegistAndLoginResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.CountDownTimersUtils;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SDCardUtils;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.utils.VersionCodeAndVersionNameUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.zhy.http.okhttp.callback.StringCallback;

//import com.zhy.http.okhttp.OkHttpUtils;


/**
 * Created by android on 2016/12/25.
 */

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "444444";
    public static final int LOGINOK = 5011;
    public static final int SAVETOKEN = 5012;
    //界面控件
    private EditText et_login_smscode;
    private EditText et_login_phone;
    private Button bt_login;
    private TextView tv_text_login;
    private ImageView iv_login;
    private String mPhoneNum = null;
    private String mPhoneNumSave;
    public static String mTokenSave;
    private LinearLayout mIv_back;
    CountDownTimersUtils mCountDownTimerUtils;
    private RadioButton mBt_userAgreement;
    private TextView mTv_userAgreement;
    private boolean isRbChecked = false;
    private String mIdx;
    private String mSmsCode;
    private RelativeLayout mRl_tv_text;
    private boolean mIsfirstlogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mIsfirstlogin = SPUtils.getBoolean(this, "isfirstlogin", true);
        initView();
    }

    private void initView() {

        mIv_back = (LinearLayout) findViewById(R.id.iv_back_login);
        iv_login = (ImageView) findViewById(R.id.iv_login);
        et_login_phone = (EditText) findViewById(R.id.et_login_phone);
        et_login_smscode = (EditText) findViewById(R.id.et_login_smscode);
        bt_login = (Button) findViewById(R.id.bt_login);
        mBt_userAgreement = (RadioButton) findViewById(R.id.rb_activity_login);
        mTv_userAgreement = (TextView) findViewById(R.id.tv_activity_login_textviewclick);
        tv_text_login = (TextView) findViewById(R.id.tv_text_login);
        mRl_tv_text = (RelativeLayout) findViewById(R.id.rl_textview_readandconfirm);
        if (mIsfirstlogin) {
            mRl_tv_text.setVisibility(View.VISIBLE);
            SPUtils.putBoolean(this, "isfirstlogin", false);
        } else {
            isRbChecked = true;
            mRl_tv_text.setVisibility(View.GONE);
        }
        mCountDownTimerUtils = new CountDownTimersUtils(tv_text_login, 60000, 1000);
        iv_login.setOnClickListener(this);
        et_login_smscode.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        tv_text_login.setOnClickListener(this);
        et_login_phone.setOnClickListener(this);
        mIv_back.setOnClickListener(this);
        mTv_userAgreement.setOnClickListener(this);
        //控制确认登录键是否可点击
        mBt_userAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRbChecked) {

                    mBt_userAgreement.setChecked(false);
                    isRbChecked = false;
                    LogUtil.i(TAG, "onCheckedChanged: isChecked1" + isRbChecked);
                    bt_login.setClickable(false);
                    LogUtil.i(TAG, "onClick1: " + bt_login.isClickable());
                } else {

                    mBt_userAgreement.setChecked(true);
                    isRbChecked = true;
                    LogUtil.i(TAG, "onCheckedChanged: isChecked2" + isRbChecked);
                    bt_login.setClickable(true);
                    LogUtil.i(TAG, "onClick2: " + bt_login.isClickable());


                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_login:
                finish();
                break;
            case R.id.iv_login:
                break;
            case R.id.et_login_phone://输入手机号
                break;
            case R.id.tv_text_login://获取验证码按钮
                //1,获取phone号码
                mPhoneNum = et_login_phone.getText().toString().trim();
                //2,获取短信验证码
                //在这里只获取验证码,不能让他去获取token,在确认登录的时候再获取token,
                //1,对手机号进行判断
                //1.1 手机号是否为空
                if (TextUtils.isEmpty(mPhoneNum)) {
                    ToastUtil.showToast(this, "空,请输入手机号,");

                } else {
                    LogUtil.d(TAG, "getSmsCode: " + isPhoneNum(mPhoneNum));
                    if (!isPhoneNum(mPhoneNum)) {
                        ToastUtil.showToast(this, "当前输入的手机号有误,请重新输入..");
                    } else {
                        //2获取短信验证申请码
                        mCountDownTimerUtils.start();
                        getSmsCode(mPhoneNum);
                    }
                }


                break;
            case R.id.et_login_smscode://输入验证码

                break;
            case R.id.bt_login://确认登录
                String smsCode = et_login_smscode.getText().toString();
                String phone = et_login_phone.getText().toString();
                LogUtil.i(TAG, "onClick: 验证码.........." + smsCode);
                String token = SPUtils.getString(this, "token", "");
                LogUtil.i("110011", "onClick: --!!" + token);
                if (!isRbChecked) {
                    ToastUtil.showToast(this, "请阅读并同意<<童缘科技服务协议>>");
                    return;
                }
                if (!isPhoneNum(phone)) {
                    ToastUtil.showToast(this, "请检查手机号");
                    return;
                }
                if (smsCode.equals("")) {
                    ToastUtil.showToast(this, "验证码不能为空");
                    return;
                }
                getToken();
                break;
            case R.id.tv_activity_login_textviewclick://用户协议
                Intent intent = new Intent();
                intent.setClass(this, UserAgreementActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void getSmsCode(final String phoneNum) {
        //用retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        GetSmsCodeReqBean.BODYBean bodyBean = new GetSmsCodeReqBean.BODYBean(phoneNum);
        GetSmsCodeReqBean getSmsCodeReqBean = new GetSmsCodeReqBean("REQ", "SMSA", "", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                , bodyBean, "", "", "1");
        Gson gson = new Gson();
        String s = gson.toJson(getSmsCodeReqBean);
        retrofit2.Call<GetSmsCodeResBean> getSmsCodeResBeanCall = allInterface.GET_SMSCODE_RES_BEAN_CALL(s);
        getSmsCodeResBeanCall.enqueue(new Callback<GetSmsCodeResBean>() {
            @Override
            public void onResponse(retrofit2.Call<GetSmsCodeResBean> call, Response<GetSmsCodeResBean> response) {
                String tmp = response.body().getBODY().getTmp();
                getSmsCodeValue(phoneNum, tmp);
                LogUtil.i(TAG, "response-------" + response);
            }

            @Override
            public void onFailure(retrofit2.Call<GetSmsCodeResBean> call, Throwable t) {

            }
        });


    }

    private void getSmsCodeValue(final String phoneNum, String tmp) {
        LogUtil.i(TAG, "tmp------" + tmp);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        //test 为1时,是不发送信息给手机 正式环境不用1,用""代替
//        GetSmsCodeValueReqBean.BODYBean bodyBean = new GetSmsCodeValueReqBean.BODYBean("REG", phoneNum, tmp, "");
        GetSmsCodeValueReqBean.BODYBean bodyBean = new GetSmsCodeValueReqBean.BODYBean("REG", phoneNum, tmp, "1");
        GetSmsCodeValueReqBean getSmsCodeValueReqBean = new GetSmsCodeValueReqBean("REQ", "SMS", "", new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()), bodyBean, "", "", "1");
        Gson gson = new Gson();
        String s = gson.toJson(getSmsCodeValueReqBean);
        retrofit2.Call<GetSmsCodeValueResBean> getSmsCodeValueResBeanCall = allInterface.GET_SMSCODEVALUE_RES_BEAN_CALL(s);
        getSmsCodeValueResBeanCall.enqueue(new Callback<GetSmsCodeValueResBean>() {
            @Override
            public void onResponse(retrofit2.Call<GetSmsCodeValueResBean> call, Response<GetSmsCodeValueResBean> response) {
                LogUtil.i(TAG, "onResponse: _________" + response);
                LogUtil.i(TAG, "onResponse: ----" + response.body());
                mIdx = response.body().getBODY().getIDX();
                mSmsCode = response.body().getBODY().getSmscode();
                /*
                * 现在这里还是用直接填的方式,正式的时候,两种方式
                * 1,让用户看短息,并填写
                * 2,直接获取用户的短信,自动填充到smscode的位置
                * 选第一个
                * */
                //TODO 别忘了删,正式上线的时候
                et_login_smscode.setText(mSmsCode);
                //点击了获取验证码以后,再删除验证码,点击"确认登录"仍然能登录的原因就错在这了 ,不应该在这里
//                getToken(smsCode, idx, phoneNum);

            }

            @Override
            public void onFailure(retrofit2.Call<GetSmsCodeValueResBean> call, Throwable t) {
                LogUtil.i(TAG, "onFailure: " + t);
            }
        });

    }

    private void getToken() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        RegistAndLoginReqBean.BODYBean bodyBean = new RegistAndLoginReqBean.BODYBean(mPhoneNum, mSmsCode, mIdx, "", "A",
                VersionCodeAndVersionNameUtils.getLocalVersionName(this));
        RegistAndLoginReqBean registAndLoginReqBean = new RegistAndLoginReqBean("REQ", "REG", "", new SimpleDateFormat("yyyyMMddHHmmssSSS").format
                (new Date()), bodyBean, "", "", "1");
        Gson gson = new Gson();
        String s = gson.toJson(registAndLoginReqBean);
        retrofit2.Call<RegistAndLoginResBean> registAndLoginResBeanCall = allInterface.REGIST_AND_LOGIN_RES_BEAN_CALL(s);
        registAndLoginResBeanCall.enqueue(new Callback<RegistAndLoginResBean>() {
            @Override
            public void onResponse(retrofit2.Call<RegistAndLoginResBean> call, Response<RegistAndLoginResBean> response) {
                LogUtil.i(TAG, "onResponse: ------" + response);
                LogUtil.i("444444", "response" + response);
                String token = response.body().getTOKEN();
                String id = response.body().getBODY().getID();
                String phone = response.body().getBODY().getPHONE();
                mPhoneNum = phone;
                LogUtil.i(TAG, "userID" + id);
                LogUtil.i(TAG, "token: " + token);
                LogUtil.i(TAG, "phone: " + phone);
                LogUtil.i(TAG, "phoneNum: " + mPhoneNum);

                saveToken(token, phone, id);

            }

            @Override
            public void onFailure(Call<RegistAndLoginResBean> call, Throwable t) {
                LogUtil.d(TAG,"onFailure"+t);
//                LogUtil.i(TAG, "onFailure: " + t);
            }
        });
    }

    //    SPUtils.putString(getApplicationContext(), "username", name);
//    SPUtils.putString(getApplicationContext(), "userimg", img);
//    SPUtils.putString(this, "phoneNum", phoneNum);
//    SPUtils.putString(this, "token", token);
//    SPUtils.putString(this, "userID", id);
    private void saveToken(String token, String phoneNum, String id) {
        if (SDCardUtils.isSDCardEnable()) {
            SPUtils.putString(this, "phoneNum", phoneNum);
            SPUtils.putString(this, "token", token);
            SPUtils.putString(this, "userID", id);
        } else {
            ToastUtil.showToast(this, "sd卡不可用,请检查后重试");
            LogUtil.i(TAG, "sdcard状态:" + SDCardUtils.isSDCardEnable());
        }
        mPhoneNumSave = SPUtils.getString(this, "phoneNum", "");
        mTokenSave = SPUtils.getString(this, "token", "");

        LogUtil.i(TAG, "phoneNumSave: " + mPhoneNumSave);
        LogUtil.i(TAG, "tokenSave: " + mTokenSave);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("logintoken", mTokenSave);
        intent.putExtras(bundle);

        setResult(LOGINOK, intent);

        confirmLogin();
    }

    private boolean isPhoneNum(String phoneNum) {
        String telRegex = "[1][3578]\\d{9}";
        return phoneNum.matches(telRegex);
    }

    private void confirmLogin() {
        String phoneNum = SPUtils.getString(this, "phoneNum", "");
        if (!mTokenSave.equals("")) {
            LogUtil.i(TAG, "confirmLogin: 登录成功");
            ToastUtil.showToast(this, "登录成功");
            QueryUserInfo(phoneNum, mTokenSave);
        } else {
            ToastUtil.showToast(this, "登录失败");
        }
    }

    //查询当前登录的用户信息
    private void QueryUserInfo(String phoneNum, String mTokenSave) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = simpleDateFormat.format(new Date());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QuerySingleUserInfoReQBean.BODYBean bodyBean = new QuerySingleUserInfoReQBean.BODYBean(phoneNum);
        QuerySingleUserInfoReQBean querySingleUserInfoReQBean = new QuerySingleUserInfoReQBean("REQ", "QRYUSER", phoneNum, format, bodyBean, "",
                mTokenSave, "1");
        Gson gson = new Gson();
        String params = gson.toJson(querySingleUserInfoReQBean);
        retrofit2.Call<QuerySingleUserInfoReSBean> querySingleUserInfoResult = allInterface.getQuerySingleUserInfoResult(params);

        querySingleUserInfoResult.enqueue(new Callback<QuerySingleUserInfoReSBean>() {

            @Override
            public void onResponse(retrofit2.Call<QuerySingleUserInfoReSBean> call, Response<QuerySingleUserInfoReSBean> response) {
                if (response.body().getCODE().equals("0")) {
                    String name = response.body().getBODY().getNAME();
                    String img = response.body().getBODY().getIMG();
                    String ID = response.body().getBODY().getID();
                    String birthday = response.body().getBODY().getBIRTHDAY();
                    String sex = response.body().getBODY().getSEX();

                    SPUtils.putString(getApplicationContext(), "username", name);
                    SPUtils.putString(getApplicationContext(), "userimg", img);
                    SPUtils.putString(getApplicationContext(), "userID", ID);
                    SPUtils.putString(getApplicationContext(), "userbirthday", birthday);
                    SPUtils.putString(getApplicationContext(), "usersex", sex);

                    String username = SPUtils.getString(getApplicationContext(), "username", "");
                    String userimg = SPUtils.getString(getApplicationContext(), "userimg", "");
                    String userID = SPUtils.getString(getApplicationContext(), "userID", "");
                    String userBirthday = SPUtils.getString(getApplicationContext(), "userbirthday", birthday);
                    String userSex = SPUtils.getString(getApplicationContext(), "usersex", sex);

                    LogUtil.i(TAG, "onResponse: " + "username:-" + username + "userimg:-" + userimg + "userID:-" + userID + "userbirthday:-" +
                            userBirthday +
                            "usersex:-" + userSex);

                    Intent data = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", name);
                    bundle.putString("userimg", img);
                    data.putExtras(bundle);
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<QuerySingleUserInfoReSBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), R.string.network_error);
            }
        });

    }
}

