package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;

/**
 * Created by android on 2017/2/6.
 */
public class PersonalActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEt_personalinfo;
    private TextView mTv_save;
    private String mId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        mEt_personalinfo = (EditText) findViewById(R.id.et_personalinfo);
        mTv_save = (TextView) findViewById(R.id.tv_save);
        mId = mEt_personalinfo.getText().toString();
        mTv_save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.putExtra("ID",mId);


    }
}
