package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;

public class ThirdVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_video);
        TextView viewById = (TextView) findViewById(R.id.ttt);
        Intent intent = getIntent();
        String scan_result = intent.getStringExtra("SCAN_RESULT");

        viewById.setText(scan_result);

    }
}
