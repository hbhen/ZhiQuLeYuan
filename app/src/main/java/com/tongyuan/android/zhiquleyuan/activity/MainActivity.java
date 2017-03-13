package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyResultBean;
import com.tongyuan.android.zhiquleyuan.fragment.DiscoveryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.HistoryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.MineFragment;
import com.tongyuan.android.zhiquleyuan.fragment.RecodingFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyAddFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyDetailsFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.QueryToyInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    public static final String TAG = "222";
    private LinearLayout rb_discovery;
    private LinearLayout rb_recoding;
    private ImageView rb_toy;
    private LinearLayout rb_history;
    private LinearLayout rb_mine;
    private FragmentTransaction beginTransaction;
    private DiscoveryFragment discoveryFragment;
    private RecodingFragment recodingFragment;
    private ToySelectorFragment mToySelectorFragment;
    private HistoryFragment historyFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;
    private ToyAddFragment toyAddFragment;
    private ToyDetailsFragment toyDetailsFragment;
    private String mCurrentTime;
    private String mMainToken;
    private String mMainphoneNum;


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
        initData();
        initFragment();
//        XRequest.initXRequest(MainActivity.this);
        rb_discovery.setOnClickListener(this);
        rb_recoding.setOnClickListener(this);
        rb_toy.setOnClickListener(this);
        rb_history.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
        //请求网络,获得我的录音的文件数据,拿到里面,请求网络数据,需要准备
    }

    private void initData() {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mCurrentTime = simpleDateFormat.format(date);
        mMainToken = SPUtils.getString(this, "TOKEN", "");
        mMainphoneNum = SPUtils.getString(this, "phoneNum", "");
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        mToySelectorFragment = new ToySelectorFragment();
        toyAddFragment = new ToyAddFragment();
        historyFragment = new HistoryFragment();
        mineFragment = new MineFragment();
        toyDetailsFragment = new ToyDetailsFragment();

        fragmentManager = getSupportFragmentManager();
        beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.fl_fragmentcontainer, discoveryFragment);

        //beginTransaction.replace(R.id.fl_fragmentcontainer,discoveryFragment);

//        beginTransaction.add(R.id.fl_fragmentcontainer, discoveryFragment);
//        beginTransaction.add(R.id.fl_fragmentcontainer, recodingFragment);
//        beginTransaction.add(R.id.fl_fragmentcontainer, toyFragment);
//        beginTransaction.add(R.id.fl_fragmentcontainer, historyFragment);
//        beginTransaction.add(R.id.fl_fragmentcontainer, mineFragment);
//        beginTransaction.add(R.id.fl_fragmentcontainer,toyAddFragment);
//        beginTransaction.show(discoveryFragment);
//        beginTransaction.hide(recodingFragment);
//        beginTransaction.hide(toyFragment);
//        beginTransaction.hide(historyFragment);
//        beginTransaction.hide(mineFragment);
//        beginTransaction.hide(toyAddFragment);
//
        beginTransaction.commit();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rb_discovery:
                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
//                ft1.show(discoveryFragment);
//                ft1.hide(recodingFragment);
//                ft1.hide(toyFragment);
//                ft1.hide(toyAddFragment);
//                ft1.hide(historyFragment);
//                ft1.hide(mineFragment);
                ft1.replace(R.id.fl_fragmentcontainer, discoveryFragment);
                ft1.commit();
                break;
            case R.id.rb_recoding:
                showDifferentFragment();
                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                ft2.replace(R.id.fl_fragmentcontainer, recodingFragment);
//                ft2.show(recodingFragment);
//                ft2.hide(discoveryFragment);
//                ft2.hide(toyFragment);
//                ft2.hide(toyAddFragment);
//                ft2.hide(historyFragment);
//                ft2.hide(mineFragment);
                ft2.commit();
                break;
            case R.id.rb_toy:
                boolean hasToy = chargeHasToy();
                Log.i("1111", "onClick: "+hasToy);
                FragmentTransaction ft3;
                if (!mMainToken.equals("")) {
                    //如果当前用户无玩具,则进入添加玩具页面
                    if (hasToy==true) {
                        ft3 = getSupportFragmentManager().beginTransaction();
                        ft3.replace(R.id.fl_fragmentcontainer, toyAddFragment);
                        ft3.commit();
                    } else {
                        //否则走玩具选择页面
                        FragmentTransaction ft32 = getSupportFragmentManager().beginTransaction();
                        ft32.replace(R.id.fl_fragmentcontainer,mToySelectorFragment);
                        ft32.commit();
//                        ft3.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);玩具选择页
                        //正常的逻辑是走扫描二维码的界面,为了调节视频,先跳转到玩具通话页面
//                    ft3.replace(R.id.fl_fragmentcontainer, toyAddFragment);


                    }
//                ft3.show(toyAddFragment);
//                ft3.hide(recodingFragment);
//                ft3.hide(discoveryFragment);
//                ft3.hide(historyFragment);
//                ft3.hide(mineFragment);
//                ft3.hide(toyFragment);



                } else {
                    Intent intent = new Intent();
                    intent.setClass(this, ActivityLogin.class);
                    startActivity(intent);
                }
                break;
            case R.id.rb_history:
                FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                ft4.replace(R.id.fl_fragmentcontainer, historyFragment);
//                ft4.show(historyFragment);
//                ft4.hide(recodingFragment);
//                ft4.hide(toyFragment);
//                ft4.hide(discoveryFragment);
//                ft4.hide(mineFragment);
//                ft4.hide(toyAddFragment);
                ft4.commit();
                break;
            case R.id.rb_mine:
                FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                ft5.replace(R.id.fl_fragmentcontainer, mineFragment);
//                ft5.show(mineFragment);
//                ft5.hide(recodingFragment);
//                ft5.hide(toyFragment);
//                ft5.hide(toyAddFragment);
//                ft5.hide(historyFragment);
//                ft5.hide(discoveryFragment);
                ft5.commit();
                break;

        }
    }

    private boolean chargeHasToy() {
        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QueryToyInterface queryToyInterface=retrofit1.create(QueryToyInterface.class);

        QueryToyRequestBean.BODYBean queryToyRequestBody=new QueryToyRequestBean.BODYBean("0","","","-1","1");
        QueryToyRequestBean queryToyRequestBean = new QueryToyRequestBean("REQ", "QRYTOYS", mMainphoneNum, mCurrentTime, queryToyRequestBody, "", mMainToken, "1");

        Gson mainGson=new Gson();
        String queryToyjson = mainGson.toJson(queryToyRequestBean);
        Log.i(TAG, "chargeHasToy: "+queryToyjson);
        Call<QueryToyResultBean> toyResult = queryToyInterface.getToyResult(queryToyjson);
       toyResult.enqueue(new Callback<QueryToyResultBean>() {
           @Override
           public void onResponse(Call<QueryToyResultBean> call, Response<QueryToyResultBean> response) {
               response.body().getBODY().getLST();
               Log.i(TAG, "onResponse: "+response.body().toString());
               Log.i(TAG, "onResponse: "+ response.body().getBODY().getLST());

           }

           @Override
           public void onFailure(Call<QueryToyResultBean> call, Throwable t) {

           }
       });
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void showDifferentFragment() {
        /**
         * 思路:从服务器查询我的录音文件,如果没有,就显示空白的list页面,
         * 那么现在就是要拿到服务器返回的数据即可判断,
         * 因此需要在点击之前就获取服务器的数据,在点击的时候,把这个值传过来,作为判断就好!
         * */
//        if (){
//
//        }else{
//            //如果有录音文件就进入有list的页面.
//        }
    }
}


