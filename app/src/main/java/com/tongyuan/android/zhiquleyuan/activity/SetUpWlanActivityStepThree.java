package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android on 2017/7/6.
 */

public class SetUpWlanActivityStepThree extends AppCompatActivity {
    @BindView(R.id.iv_back3)
    LinearLayout mIvBack3;
    @BindView(R.id.tb_netconfig_prepare)
    RelativeLayout mTbNetconfigPrepare;
    @BindView(R.id.iv_netconfig_prepare)
    ImageView mIvNetconfigPrepare;
    @BindView(R.id.bt_sendsuccess)
    Button mBtSendsuccess;
    @BindView(R.id.tv_netconfig_retry3_sendvoiceagain)
    TextView mTvNetconfigRetry3Sendvoiceagain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netconfig_success);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back3, R.id.bt_sendsuccess, R.id.tv_netconfig_retry3_sendvoiceagain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back3:
                finish();
                break;
            case R.id.bt_sendsuccess:
                Intent intent=new Intent();
                intent.setClass(this,SetUpWlanActivityStepFour.class);
                startActivity(intent);
                break;
            case R.id.tv_netconfig_retry3_sendvoiceagain:
                finish();
                break;
        }
    }
}
