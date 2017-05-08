package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.MyCollectionAdapter;
import com.tongyuan.android.zhiquleyuan.bean.MyCollectionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/12/23.
 */

public class MyCollectionActivity extends AppCompatActivity {

    private ListView lv_mycollection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        List<MyCollectionBean> list = new ArrayList<MyCollectionBean>();
        MyCollectionBean mMCBean = new MyCollectionBean();
//list也先不用
        lv_mycollection = (ListView) findViewById(R.id.lv_mycollection);
        MyCollectionAdapter mMCAdapter = new MyCollectionAdapter(this, list);
        lv_mycollection.setAdapter(mMCAdapter);
    }
}
