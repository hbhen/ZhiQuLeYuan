package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.suke.widget.SwitchButton;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

/**
 * Created by android on 2017/3/12.
 */
public class NoDisturbActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodisturb);
        SwitchButton switchButton = (SwitchButton) findViewById(R.id.switch_button);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_nodisturb_first);
//        switchButton.setChecked(true);
//        switchButton.toggle();
        switchButton.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
//        switchButton.setTag("12");
//        switchButton.toggle(true);
        switchButton.setShadowEffect(true);//disable shadow effect
        switchButton.setEnabled(true);//disable button
        switchButton.setEnableEffect(false);//disable the switch animation

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                ToastUtil.showToast(getApplicationContext(), "ischecked" + isChecked);

                //TODO do your job
                if (isChecked == true) {
                    //打开开关的逻辑:开启设置免打扰,
                    ToastUtil.showToast(getApplicationContext(), "开");

                }else{
                    //关闭开关的逻辑
                    ToastUtil.showToast(getApplicationContext(), "关");

                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_nodisturb_first:
                ToastUtil.showToast(this, "12");
                break;
            case R.id.switch_button:

                ToastUtil.showToast(this, "34");
                break;
            default:
                break;
        }
    }
}