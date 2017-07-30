package com.tongyuan.android.zhiquleyuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.tongyuan.android.zhiquleyuan.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DTC on 2017/4/19.
 */
public class UnRegisterUserInvite extends AppCompatActivity {
    @BindView(R.id.tv_activity_unregisterinvite_phonenum)
    TextView mTvActivityUnregisterinvitePhonenum;
    @BindView(R.id.switch_button)
    SwitchButton mSwitchButton;
    @BindView(R.id.ll_activity_unregisteruserinvite_wechat)
    LinearLayout mLlActivityUnregisteruserinviteWechat;
    @BindView(R.id.ll_activity_unregisteruserinvite_sms)
    LinearLayout mLlActivityUnregisteruserinviteSms;
    private ShareAction mShareAction;
    private CustomUShareListener mCustomUShareListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unregisteruserinvite);
        ButterKnife.bind(this);
        String phone = getIntent().getStringExtra("phone");
        mTvActivityUnregisterinvitePhonenum.setText(phone);
        mShareAction = new ShareAction(this);
        mCustomUShareListener = new CustomUShareListener(this);
    }

    @OnClick({R.id.switch_button, R.id.ll_activity_unregisteruserinvite_wechat, R.id.ll_activity_unregisteruserinvite_sms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switch_button:
                break;
            case R.id.ll_activity_unregisteruserinvite_wechat:
                mShareAction.withText("www.baidu.com")
                        .setDisplayList(SHARE_MEDIA.WEIXIN)
                        .setCallback(mCustomUShareListener)
                        .open();
                break;
            case R.id.ll_activity_unregisteruserinvite_sms:
                mShareAction.setDisplayList(SHARE_MEDIA.SMS)
                        .setCallback(mCustomUShareListener)
                        .open();
                break;
        }
    }

    private class CustomUShareListener implements UMShareListener {
        private WeakReference<MainActivity> mWeakReference;

        public CustomUShareListener(Activity activity) {
            mWeakReference = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);


    }
}
