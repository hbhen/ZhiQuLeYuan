package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReQBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReSBean;
import com.tongyuan.android.zhiquleyuan.http.Config;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SDCardUtils;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by android on 2016/12/25.
 */


public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    public static final int LOGINOK = 5011;
    public static final int SAVETOKEN=5012;
    //界面控件
    private EditText et_login_smscode;
    private EditText et_login_phone;
    private Button bt_login;
    private TextView tv_text_login;
    private ImageView iv_login;
    //private String mPhoneNum;
    private String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    private String mPhoneNum;
    //    public int LOGINSUCCESS = 7;
    private String mPhoneNumSave;
    public static String mTokenSave;
    private static final String TAG = "444444";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();


    }



    private void initView() {
        iv_login = (ImageView) findViewById(R.id.iv_login);

        et_login_phone = (EditText) findViewById(R.id.et_login_phone);

        et_login_smscode = (EditText) findViewById(R.id.et_login_smscode);

        bt_login = (Button) findViewById(R.id.bt_login);

        tv_text_login = (TextView) findViewById(R.id.tv_text_login);

        iv_login.setOnClickListener(this);
        et_login_smscode.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        tv_text_login.setOnClickListener(this);
        et_login_phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login:
                break;
            case R.id.et_login_phone:
                break;

            /*
            * 获取验证码
            * 1,获得用户输入的手机号(判断手机号为空否?1,为空,给提示;2不为空,则判断是否是手机号3正确就保存,获取验证码的时候去取
            * 2,去服务器获得短信申请码(拿手机号去)
            * 3,从服务器拿到返回的短信验证码的申请码(code),再拿着手机号和申请码去申请短信验证码,拿到返回的token,保存token,便于以后登录
            * 4,以后进入app,在需要登录的时候,判断token是否正确,如果正确就返回就进入正常应用,如果不正确就接着弹出登录窗口登录
            *
            * */
            case R.id.tv_text_login:
                //1,获取phone号码
                mPhoneNum = et_login_phone.getText().toString().trim();

                //2,获取短信验证码
                getSmsCode(mPhoneNum);
                //Log.i("M1-smscode", "smsCode:---------- " + smsCodedata);
                // requestResourseID();
                break;

            case R.id.et_login_smscode:

                break;
            case R.id.bt_login:
                String smsCode = et_login_smscode.getText().toString();
                Log.i(TAG, "onClick: 验证码.........." + smsCode);
                String token = SPUtils.getString(this, "TOKEN", "");
                Log.i("110011", "onClick: --!!" + token);
                confirmLogin();
                break;
            default:
                break;
        }
    }




    private void getSmsCode(String phoneNum) {
        //1,对手机号进行判断
        //1.1 手机号是否为空
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtil.showToast(this, "空,请输入手机号,");

        } else {
            //1.1.1 判断是不是手机号phoneNum.length() != 11 &&
            Log.d(TAG, "getSmsCode: " + isPhoneNum(phoneNum));
            if (isPhoneNum(phoneNum) == false) {
                ToastUtil.showToast(this, "当前输入的手机号有误,请重新输入..");
            } else {
                //2获取短信验证申请码
                getSmsCodeApply(phoneNum);
            }
        }
    }

    private void getSmsCodeApply(final String phoneNum) {
        //上传手机号,去服务器获取smscode,上传的内容是json对象
        final String smsCodeApplyUrl = Config.smsCodeUrl;

        JSONObject jsonObject = new JSONObject();
        JSONObject bodyPhoneObject = new JSONObject();
        try {

            jsonObject.put("TYPE", "RES");
            jsonObject.put("CMD", "SMSA");
            jsonObject.put("ACCT", phoneNum);
            jsonObject.put("TIME", time);
            //这里的body后面又有一个大括号,所以还需要一个对象
            jsonObject.put("BODY", bodyPhoneObject);
            bodyPhoneObject.put("PHONE", phoneNum);
            jsonObject.put("VERI", "");
            jsonObject.put("TOKEN", "");
            jsonObject.put("SEQ", "1");
//          String s = String.valueOf(jsonObject);
            String jsonPhoneString = jsonObject.toString();
            //Log.i(TAG, "s是------" + jsonPhoneString);
            OkHttpUtils.get().url(smsCodeApplyUrl)
                    .addParams("params", jsonPhoneString)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ToastUtil.showToast(getApplicationContext(), "大天才输了");

                        }

                        @Override
                        public void onResponse(String response, int id) {

                            try {
                                JSONObject jsonObject1 = new JSONObject(response);
//                                Log.i(TAG, "jsonObject1------: "+jsonObject1);
                                JSONObject jsonObject2 = jsonObject1.getJSONObject("BODY");
                                String tmp = jsonObject2.getString("tmp");
                                getSmsCodeValue(phoneNum, tmp, smsCodeApplyUrl);
//                                Log.i(TAG, "tmp------" + jsonObject2.getString("tmp"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //Log.i(TAG, "s1------" + s1);
                            Log.i(TAG, "response-------" + response);
                            ToastUtil.showToast(getApplicationContext(), "大天才赢了!" + response);
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getSmsCodeValue(final String phoneNum, String tmp, String url) {
        Log.i(TAG, "tmp------" + tmp);
        JSONObject jsonObject = new JSONObject();
        JSONObject bodySmsObject = new JSONObject();
        try {
            jsonObject.put("TYPE", "REQ");
            jsonObject.put("CMD", "SMS");
            jsonObject.put("ACCT", phoneNum);
            jsonObject.put("TIME", time);
            jsonObject.put("BODY", bodySmsObject);
            bodySmsObject.put("CODETYPE", "REG");
            bodySmsObject.put("PHONENO", phoneNum);
            JSONObject agreeno = bodySmsObject.put("AGREENO", tmp);
            String str = agreeno.toString();
            Log.i(TAG, "str------ " + str);
            bodySmsObject.put("test", "1");
            jsonObject.put("VERI", "");
            jsonObject.put("TOKEN", "");
            jsonObject.put("SEQ", "1");
            /*
            * 这一步是为了拿着我的电话号码,上一个借口返回的agreeno的值(测试的时候用的是tmp的值)
            * 我们要带着上面两个参数,以及另外两个参数  CODETYPE,test 去访问下一个借口,然后获得我们需要的数据(具体还是看接口返回的什么数据
            * 以及下个借口需要的什么样的数据我们要往服务器传递的数据!)
            * 没错 !我就是这么啰嗦!!
            * */
            String jsonSmsString = jsonObject.toString();
            OkHttpUtils.get()
                    .url(url)
                    .addParams("params", jsonSmsString)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ToastUtil.showToast(getApplicationContext(), "我又失败了 ,请不要怪我");
                            Log.i("444444", "onError: " + e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            /*
                            * 好吧 ,我又成功的拿到数据了,恩!!!这次我是真的拿到了smscode,现在我要把smscode取出来了
                            * 还是用返回的json数据里面一层一层的拿,直到拿到我的response;
                            * */
                            Log.i(TAG, "onResponse: _________" + response);
                            //ToastUtil.showToast(getApplicationContext(), "我又成功了,请打印response____" + response);
                            try {
                                JSONObject responseThird = new JSONObject(response);
                                JSONObject bodyBody = responseThird.getJSONObject("BODY");
                                Log.i(TAG, "onResponse: ----" + bodyBody);
                                String smsCode = bodyBody.getString("smscode");
//                                Log.i("M1-mS3", "onResponse: _________mS3" + mS3 + "shuju" + bodyBody.getString("smscode"));
                                String idx = bodyBody.getString("IDX");
                                //拿到smscode以后,做相应的逻辑 把他的显示在验证码
                                //把smscode显示出来,有两个做法:1,通过通知,让用户自己输入 2,直接把验证码传到smscode的输入框,暂时先用第2个方法

                                et_login_smscode.setText(smsCode);
                                Log.i("444444", "onResponse: " + bodyBody);
                                getToken(response, phoneNum);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

            ToastUtil.showToast(this, "GameOver!!!!!");
//            Log.i(TAG, "onResponse: _________mS3" + mS3 + "---返回给上面的ms3");

        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    private void getToken(String response, final String phoneNum) {
        String registerUrl = Config.register;
        try {
            JSONObject responseThird = new JSONObject(response);
            JSONObject bodyBody = responseThird.getJSONObject("BODY");
            String smscode = bodyBody.getString("smscode");
            String idx = bodyBody.getString("IDX");
            final JSONObject responseObject = new JSONObject();
            JSONObject bodyObject = new JSONObject();
            responseObject.put("TYPE", "REQ");
            responseObject.put("CMD", "REG");
            responseObject.put("ACCT", phoneNum);
            responseObject.put("TIME", time);
            responseObject.put("BODY", bodyObject);
            bodyObject.put("PHONE", phoneNum);
            bodyObject.put("SMSCODE", smscode);
            Log.i(TAG, "smscode:----- " + smscode);
            bodyObject.put("IDX", idx);
            bodyObject.put("DEVCODE", "");
            bodyObject.put("OS", "A");
            //TODO 现在这个version是写死的,最后记得,这个是要从服务器获取的,获取当前的最新版本
            bodyObject.put("VERSION", "1.0");
            responseObject.put("VERI", "");
            responseObject.put("TOKEN", "");
            responseObject.put("SEQ", "1");
            String responseString = responseObject.toString();
            OkHttpUtils.get()
                    .url(registerUrl)
                    .addParams("params", responseString)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    e.printStackTrace();
                    ToastUtil.showToast(getApplicationContext(), "token获取失败,请检查网络");
                }

                @Override
                public void onResponse(String response, int id) {
                    Log.i(TAG, "onResponse: ------" + response);
                    ToastUtil.showToast(getApplicationContext(), "response" + response);
                    Log.i("11223344", "response" + response);
                    //response里面有token,取出这个token以后保存,以后每次进入app都先判断一下,判断是不是又token,然后判断token对不对
                    //不需要每次都让用户输入手机号,登录,获取验证码,体验不好
                    try {
                        JSONObject responseObject3 = new JSONObject(response);
                        JSONObject body = responseObject3.getJSONObject("BODY");
                        String userId1 = body.getString("ID");
//                        String userId = responseObject3.getString("ID");
                        Log.i("444444", "userId" + userId1);

                        String token = responseObject3.getString("TOKEN");
//                        SPUtils.putString(getApplicationContext(),"TOKEN",token);
                        Log.i("444444", "token: " + token);

                        //保存token到本地
//                        saveToken(token, phoneNum, userId);
                        saveToken(token, phoneNum, userId1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i("444444", "onResponse: +error" + e.toString());
                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("444444", "error:gettoken+" + e.toString());
        }
    }

    private void saveToken(String token, String phoneNum, String id) {
        boolean sdCardEnable = SDCardUtils.isSDCardEnable();
        if (sdCardEnable == true) {
            SPUtils.putString(this, "phoneNum", phoneNum);
            SPUtils.putString(this, "TOKEN", token);
            SPUtils.putString(this, "ID", id);

        } else if (!sdCardEnable) {
            ToastUtil.showToast(this, "sd卡不可用,请检查后重试");
            Log.i(TAG, "sdcard -------" + sdCardEnable);
        }
        mPhoneNumSave = SPUtils.getString(this, "phoneNum", "");
        mTokenSave = SPUtils.getString(this, "TOKEN", "");


        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("logintoken", mTokenSave);
        intent.putExtras(bundle);

        setResult(LOGINOK, intent);
//        setResult(REQUEST_CODE_LOGIN, intent);

        Log.i(TAG, "phoneNum1: " + mPhoneNumSave);
        Log.i(TAG, "token1: " + mTokenSave);
    }


    private boolean isPhoneNum(String phoneNum) {
        String telRegex = "[1][3578]\\d{9}";
        return phoneNum.matches(telRegex);
    }
    private void confirmLogin() {

        String loginToken = SPUtils.getString(this, "TOKEN", "");
        Log.i("444444", "logintoken: " + loginToken);
        String phoneNum = SPUtils.getString(this, "phoneNum", "");
        if (!(loginToken.isEmpty())) {
            ToastUtil.showToast(this, "登录成功");
            this.finish();
//            LOGIN_SUCCESS = 01;//登录成功的值

            //token不为空,在点击确认登录的时候去查询user信息
            QueryUserInfo(phoneNum, loginToken);

        } else {
            ToastUtil.showToast(this, "登录失败");
        }
    }

    private void QueryUserInfo(String phoneNum, String loginToken) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = simpleDateFormat.format(new Date());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        QuerySingleUserInfoReQBean.BODYBean bodyBean = new QuerySingleUserInfoReQBean.BODYBean(phoneNum);
        QuerySingleUserInfoReQBean querySingleUserInfoReQBean = new QuerySingleUserInfoReQBean("REQ", "QRYUSER", phoneNum, format, bodyBean, "",
                loginToken, "1");
        Gson gson = new Gson();
        String params = gson.toJson(querySingleUserInfoReQBean);
        retrofit2.Call<QuerySingleUserInfoReSBean> querySingleUserInfoResult = allInterface.getQuerySingleUserInfoResult(params);
        querySingleUserInfoResult.enqueue(new Callback<QuerySingleUserInfoReSBean>() {
            @Override
            public void onResponse(retrofit2.Call<QuerySingleUserInfoReSBean> call, Response<QuerySingleUserInfoReSBean> response) {
                if (response != null) {
                    String name = response.body().getBODY().getNAME();
                    String img = response.body().getBODY().getIMG();

                    Log.i(TAG, "activitylogin:onResponse: userinfo" + response.body().toString());
                    Log.i(TAG, "activitylogin:onResponse: userinfo" + name);
                    Log.i(TAG, "activitylogin:onResponse: userinfo" + img);

                    SPUtils.putString(getApplicationContext(),"username",name);
                    SPUtils.putString(getApplicationContext(),"userimg",img);

                    Intent data = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", name);
                    bundle.putString("userimg", img);
                    data.putExtras(bundle);


                } else {
                    ToastUtil.showToast(getApplicationContext(), "不知所措");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<QuerySingleUserInfoReSBean> call, Throwable t) {

            }
        });

    }
}

