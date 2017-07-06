package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android on 2017/7/6.
 */

public class SetUpWlanActivityStepOne extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tb_netconfig_prepare)
    RelativeLayout mTbNetconfigPrepare;
    @BindView(R.id.iv_netconfig_prepare)
    ImageView mIvNetconfigPrepare;
    @BindView(R.id.bt_sendpre)
    Button mBtSendpre;
    @BindView(R.id.tv_netconfig_retry1_prepare)
    TextView mTvNetconfigRetry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netconfig_prepare);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.bt_sendpre})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_sendpre:
                Intent intent = new Intent();
                intent.setClass(this, SetUpWlanActivityStepTwo.class);
                startActivity(intent);

                break;
        }
    }
}
