package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.PlayAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseActivity;
import com.tongyuan.android.zhiquleyuan.bean.MusicPlayerBean;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by DTC on 2018/4/24.
 */

public class PlayingListActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    ArrayList<String> mArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playinglist);
        ListView playinglist = (ListView) findViewById(R.id.lv_playinglist);
        Intent intent = getIntent();
        Bundle playlist1 = intent.getBundleExtra("playlistbundle");
        final ArrayList<MusicPlayerBean> playlist = playlist1.getParcelableArrayList("playlist");
        LogUtil.i(TAG, "playlist:" + playlist.size());
        final PlayAdapter playAdapter = new PlayAdapter(playlist, this);
        playinglist.setAdapter(playAdapter);
        playinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyPlayActivity.launch(mContext, playlist, position);
//                ToastUtil.showToast(mContext, "点 : " + position);
            }
        });
    }

    @Override
    protected void onPrepared() {

    }

    @Override
    protected void onError() {

    }

    @Override
    protected void onCompleted() {

    }

    @Override
    protected void bindSuccess() {

    }

    @Override
    protected void isSimplePlayUrl() {

    }
}
