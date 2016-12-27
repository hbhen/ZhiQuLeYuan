package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.fragment.DiscoveryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.HistoryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.MineFragment;
import com.tongyuan.android.zhiquleyuan.fragment.RecodingFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyAddFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyFragment;
import com.tongyuan.android.zhiquleyuan.request.XRequest;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    public static final String TAG = "1";
    private LinearLayout rb_discovery;
    private LinearLayout rb_recoding;
    private ImageView rb_toy;
    private LinearLayout rb_history;
    private LinearLayout rb_mine;
    private FragmentTransaction beginTransaction;
    private DiscoveryFragment discoveryFragment;
    private RecodingFragment recodingFragment;
    private ToyFragment toyFragment;
    private HistoryFragment historyFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;
    private ToyAddFragment toyAddFragment;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        XRequest.initXRequest(MainActivity.this);
        rb_discovery.setOnClickListener(this);
        rb_recoding.setOnClickListener(this);
        rb_toy.setOnClickListener(this);
        rb_history.setOnClickListener(this);
        rb_mine.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        rb_discovery = (LinearLayout) findViewById(R.id.rb_discovery);
        rb_recoding = (LinearLayout) findViewById(R.id.rb_recoding);
        rb_toy = (ImageView) findViewById(R.id.rb_toy);
        rb_history = (LinearLayout) findViewById(R.id.rb_history);
        rb_mine = (LinearLayout) findViewById(R.id.rb_mine);

    }


    private void initFragment() {
        //添加五个Fragment对象添加进来
        discoveryFragment = new DiscoveryFragment();
        recodingFragment = new RecodingFragment();
        toyFragment = new ToyFragment();
        toyAddFragment = new ToyAddFragment();
        historyFragment = new HistoryFragment();
        mineFragment = new MineFragment();

        fragmentManager = getSupportFragmentManager();
        beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(R.id.fl_fragmentcontainer, discoveryFragment);
        beginTransaction.add(R.id.fl_fragmentcontainer, recodingFragment);
        beginTransaction.add(R.id.fl_fragmentcontainer, toyFragment);
        beginTransaction.add(R.id.fl_fragmentcontainer, historyFragment);
        beginTransaction.add(R.id.fl_fragmentcontainer, mineFragment);
        beginTransaction.add(R.id.fl_fragmentcontainer,toyAddFragment);
        beginTransaction.show(discoveryFragment);
        beginTransaction.hide(recodingFragment);
        beginTransaction.hide(toyFragment);
        beginTransaction.hide(historyFragment);
        beginTransaction.hide(mineFragment);
        beginTransaction.hide(toyAddFragment);

        beginTransaction.commit();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rb_discovery:
                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                ft1.show(discoveryFragment);
                ft1.hide(recodingFragment);
                ft1.hide(toyFragment);
                ft1.hide(toyAddFragment);
                ft1.hide(historyFragment);
                ft1.hide(mineFragment);
                ft1.commit();
                break;
            case R.id.rb_recoding:
                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();

                ft2.show(recodingFragment);
                ft2.hide(discoveryFragment);
                ft2.hide(toyFragment);
                ft2.hide(toyAddFragment);
                ft2.hide(historyFragment);
                ft2.hide(mineFragment);
                ft2.commit();
                break;
            case R.id.rb_toy:
                FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();

                ft3.show(toyAddFragment);
                ft3.hide(recodingFragment);
                ft3.hide(discoveryFragment);
                ft3.hide(historyFragment);
                ft3.hide(mineFragment);
                ft3.hide(toyFragment);
                ft3.commit();
                break;
            case R.id.rb_history:
                FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                ft4.show(historyFragment);
                ft4.hide(recodingFragment);
                ft4.hide(toyFragment);
                ft4.hide(discoveryFragment);
                ft4.hide(mineFragment);
                ft4.hide(toyAddFragment);
                ft4.commit();
                break;
            case R.id.rb_mine:
                FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                ft5.show(mineFragment);
                ft5.hide(recodingFragment);
                ft5.hide(toyFragment);
                ft5.hide(toyAddFragment);
                ft5.hide(historyFragment);
                ft5.hide(discoveryFragment);
                ft5.commit();
                break;

        }
    }
}


