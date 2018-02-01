package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android on 2017/7/6.
 */

public class SetUpWlanActivityStepFour extends AppCompatActivity {
    @BindView(R.id.iv_back4)
    LinearLayout mIvBack4;
    @BindView(R.id.tb_netconfig_prepare)
    RelativeLayout mTbNetconfigPrepare;
    @BindView(R.id.iv_netconfig_waitconnect)
    ImageView mIvNetconfigWaitconnect;
    @BindView(R.id.tv_tips)
    TextView mTvTips;
    @BindView(R.id.tv_netconfig_again)
    TextView mTvNetconfigAgain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netconfig_waitconnect);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back4, R.id.tv_netconfig_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back4:
                finish();
                break;
            case R.id.tv_netconfig_again:
                ToastUtil.showToast(this,"返回inputinfo页面");
                Intent it = new Intent(this, SetupWlanActivity.class);
                startActivity(it);
                break;
        }
    }
}
