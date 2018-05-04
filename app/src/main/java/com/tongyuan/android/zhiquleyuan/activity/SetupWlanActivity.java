package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android on 2017/3/12.
 */

public class SetupWlanActivity extends AppCompatActivity {
    private static final String TAG = "setwlan";
    @BindView(R.id.tb_netconfig_inputinfo_name)
    RelativeLayout mTbNetconfigInputinfoName;
    @BindView(R.id.tv_netconfig_inputinfo_name)
    TextView mTvNetconfigInputinfoName;
    @BindView(R.id.et_setwlanname)
    EditText mEtSetwlanname;
    @BindView(R.id.rl_netconfig_inputinfo_name)
    RelativeLayout mRlNetconfigInputinfoName;
    @BindView(R.id.tv_netconfig_inputinfo_psd)
    TextView mTvNetconfigInputinfoPsd;
    @BindView(R.id.et_setwlan)
    EditText mEtSetwlan;
    @BindView(R.id.rl_secret)
    RelativeLayout mRlSecret;
    @BindView(R.id.bt_confirm)
    Button mBtConfirm;
    @BindView(R.id.iv_back)
    LinearLayout mIvBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netconfig_inputinfo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.et_setwlanname, R.id.et_setwlan, R.id.bt_confirm, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_setwlanname:
                break;
            case R.id.et_setwlan:
                break;
            case R.id.bt_confirm:
                String wlanName = mEtSetwlanname.getText().toString().trim();
                String wlanSecret = mEtSetwlan.getText().toString().trim();
                LogUtil.d(TAG, "onViewClicked: +wlanName:" + wlanName + "+wlanSecret:" + wlanSecret);
                if (wlanName.equals("") || wlanSecret.equals("")) {
                    ToastUtil.showToast(this, "请填写无线名称和无线密码");
                    return;
                }
                SPUtils.putString(this, "wlanname", "" + wlanName);
                SPUtils.putString(this, "wlansecret", "" + wlanSecret);
                Intent intent = new Intent();
                intent.setClass(this, SetUpWlanActivityStepOne.class);
                startActivity(intent);
                break;
        }
    }
}
