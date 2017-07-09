package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tongyuan.android.zhiquleyuan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android on 2016/12/23.
 */

public class MySuggestionActivity extends AppCompatActivity {

    @BindView(R.id.iv_suggestion_back)
    ImageView mIvSuggestionBack;
    @BindView(R.id.tb_suggestion)
    RelativeLayout mTbSuggestion;
    @BindView(R.id.et_suggestion)
    EditText mEtSuggestion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.iv_suggestion_back, R.id.tb_suggestion, R.id.et_suggestion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_suggestion_back:
                finish();
                break;
            case R.id.tb_suggestion:
                break;
            case R.id.et_suggestion:
                break;
        }
    }
}
