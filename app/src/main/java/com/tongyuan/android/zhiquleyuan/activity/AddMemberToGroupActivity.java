package com.tongyuan.android.zhiquleyuan.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.AddMemberToGroupReQBean;
import com.tongyuan.android.zhiquleyuan.bean.AddMemberToGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReQBean;
import com.tongyuan.android.zhiquleyuan.bean.QuerySingleUserInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToyManagerFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.CommonUtil;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DTC on 2017/4/18.
 */
public class AddMemberToGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "addmember";
    private EditText mEt_addmembertogroup;
    private Button mBt_addmembertogroup;
    private Intent mIntent;
    private String mPhone;
    private String mTime;
    private String mPhoneNum;
    private String mToken;
    private ToyManagerFragment mToyManagerFragment;
    private String mToyinfoID;
    private String mCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmembertogroup);
        initView();
        initListener();
        initDate();

    }

    private void initView() {
        mEt_addmembertogroup = (EditText) findViewById(R.id.et_addmembertogroup);
        mBt_addmembertogroup = (Button) findViewById(R.id.bt_addmembertogroup);
    }

    private void initListener() {
        mEt_addmembertogroup.setOnClickListener(this);
        mBt_addmembertogroup.setOnClickListener(this);
    }

    private void initDate() {
        mIntent = new Intent();
        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mTime = simpleDateFormat.format(new Date());
        mToken = SPUtils.getString(this, "token", "");

        Intent intent = getIntent();
        LogUtil.d(TAG, "initDate: +" + intent.toString());
        int flag = intent.getIntExtra("flag", 0);
        //传过来的参数是1,就是从玩具群的邀请成员传过来的.
        if (flag == 1) {
            Bundle bundle = intent.getExtras();
            LogUtil.d(TAG, "initDate: " + bundle.toString());
            SingleToyInfoRESBean.BODYBean toyinfo = bundle.getParcelable("toyinfo");

            mToyinfoID = toyinfo.getID();
            mCode = toyinfo.getCODE();
            //传过来的参数是2,就是BabyInfoListActivity的BabyInfoListAdapter传过来的.
        } else if (flag == 2) {
//            ToyManagerFragment toymanagerfragment = (ToyManagerFragment) ToyManagerFragment.instantiate(this, ToyManagerFragment.class
//                    .getSimpleName());
//            SingleToyInfoRESBean.BODYBean response = toymanagerfragment.mResponse;
//            String string = response.toString();
            LogUtil.d(TAG, "initDate: string(addmembertogroupactivity)" + "");
            LogUtil.d(TAG, "initDate: BabyInfoListActivity的BabyInfoListAdapter传过来的");
        }


    }

    //TODO // FIXME: 2017/10/25
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_addmembertogroup:
                break;
            case R.id.bt_addmembertogroup:
                mPhone = mEt_addmembertogroup.getText().toString().trim();
                if (mPhone.equals("")) {
                    ToastUtil.showToast(this, "请输入手机号");
                    return;
                }
                if (CommonUtil.isPhoneNum(mPhone)) {
                    LogUtil.d(TAG, "onClick: phone+" + mPhone);
//                    ToastUtil.showToast(this, "phone" + mPhone);
                    QuerySingleUserInfo(mPhone);
                } else {
                    ToastUtil.showToast(this, "请检查输入的手机号");
                    return;
                }

            default:
                break;
        }
    }

    private void QuerySingleUserInfo(final String phone) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);

        //根据acct(手机号)查询当前用户是否注册了信息,只有注册过的用户才能添加到group
        QuerySingleUserInfoReQBean.BODYBean bodyBean = new QuerySingleUserInfoReQBean.BODYBean(phone);
        QuerySingleUserInfoReQBean querySingleUserInfoReQBean = new QuerySingleUserInfoReQBean("REQ", "QRYUSER", mPhoneNum, mTime, bodyBean, "",
                mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(querySingleUserInfoReQBean);
        Call<QuerySingleUserInfoReSBean> querySingleUserInfoResult = allInterface.getQuerySingleUserInfoResult(s);
        LogUtil.d(TAG, "QuerySingleUserInfo: ssss+" + s);
        querySingleUserInfoResult.enqueue(new Callback<QuerySingleUserInfoReSBean>() {
            @Override
            public void onResponse(Call<QuerySingleUserInfoReSBean> call, Response<QuerySingleUserInfoReSBean> response) {
                if (!response.body().toString().isEmpty() && response.body().getCODE().equals("-201")) {
                    ToastUtil.showToast(getApplicationContext(), "当前用户不存在,发送注册链接给用户");
                    mIntent.setClass(getApplicationContext(), UnRegisterUserInvite.class);
                    mIntent.putExtra("phone", phone);
                    startActivity(mIntent);
                } else if (!response.body().toString().isEmpty() && response.body().getCODE().equals("0")) {

                    //返回的是正确的值,用户存在,把用户的id获取并返回
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("singleuserinfo", response.body());
//                    mIntent.putExtras(bundle);
//                    FragmentManager supportFragmentManager = getSupportFragmentManager();
//                    FragmentTransaction transaction = supportFragmentManager.beginTransaction();
//                    mToyManagerFragment.setArguments(bundle);
//                    transaction.replace(R.id.fl_fragmentcontainer, mToyManagerFragment);
//                    transaction.commit();

                    String userId = response.body().getBODY().getID();

                    //查询成员,添加完以后,跳转到toymanagerfragment

                    addMemberToGroup(userId);

                } else if (response.body().getCODE().equals("-10006")) {
                    SPUtils.putString(getApplicationContext(), "token", "");
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                } else {
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<QuerySingleUserInfoReSBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "网络异常,请检查网络");
                LogUtil.i(TAG, t.toString());
            }
        });

    }

    private void addMemberToGroup(String userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        AddMemberToGroupReQBean.BODYBean bodyBean = new AddMemberToGroupReQBean.BODYBean(mToyinfoID, mCode, userId);
        AddMemberToGroupReQBean addMemberToGroupReQBean = new AddMemberToGroupReQBean("REQ", "AU2T", mPhoneNum, mTime, bodyBean, "", mToken, "1");
        Gson gson = new Gson();
        String params = gson.toJson(addMemberToGroupReQBean);
        Call<AddMemberToGroupReSBean> addMemberToGroupReSBeanCall = allInterface.ADD_MEMBER_TO_GROUP_RES_BEAN_CALL(params);
        addMemberToGroupReSBeanCall.enqueue(new Callback<AddMemberToGroupReSBean>() {
            @Override
            public void onResponse(Call<AddMemberToGroupReSBean> call, Response<AddMemberToGroupReSBean> response) {
//                FragmentManager supportFragmentManager = getSupportFragmentManager();
//                FragmentTransaction transaction = supportFragmentManager.beginTransaction();
//                transaction.add(R.id.fl_fragmentcontainer, new ToyManagerFragment());
//                transaction.commit();
                LogUtil.d(TAG, "onResponse: (完成逻辑)+ " + response.body().toString());
                finish();
            }

            @Override
            public void onFailure(Call<AddMemberToGroupReSBean> call, Throwable t) {
                LogUtil.d(TAG, "onFailure: " + t);

            }
        });
    }
}
