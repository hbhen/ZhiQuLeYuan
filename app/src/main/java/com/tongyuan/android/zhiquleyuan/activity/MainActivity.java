package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.tongyuan.android.zhiquleyuan.event.MessageEventToy;
import com.tongyuan.android.zhiquleyuan.fragment.DiscoveryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.HistoryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.MineFragment;
import com.tongyuan.android.zhiquleyuan.fragment.RecodingFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyAddFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyDetailsFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.QueryToyInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    public static final String TAG = "333333";
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
    public List<QueryToyResultBean.BODYBean.LSTBean> mList;

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
        rb_discovery.setOnClickListener(this);
        rb_recoding.setOnClickListener(this);
        rb_toy.setOnClickListener(this);
        rb_history.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
        //请求网络,获得我的录音的文件数据,拿到里面,请求网络数据,需要准备
    }

    private void initData() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mCurrentTime = simpleDateFormat.format(date);
        mMainToken = SPUtils.getString(this, "TOKEN", "");
        Log.i(TAG, "initData: token" + mMainToken);
        mMainphoneNum = SPUtils.getString(this, "phoneNum", "");
        mList = new ArrayList<QueryToyResultBean.BODYBean.LSTBean>();

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


        beginTransaction.commit();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rb_discovery:
                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.fl_fragmentcontainer, discoveryFragment);
                ft1.commit();
                break;
            case R.id.rb_recoding:
                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                ft2.replace(R.id.fl_fragmentcontainer, recodingFragment);
                ft2.commit();
                break;
            case R.id.rb_toy:
                /*
                * 点击button,查询当前的用户是否有玩具,有玩具就展示出来,展示到selectToy页面
                否则进入无玩具页面,要求添加页面  待添加完玩具以后,再把玩具的图片和id,绑定的宝宝信息,传到selectToy页面,并展示
                * */
                //1,查询玩具
                chargeHasToy();
                if (!mMainToken.equals("")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            boolean hasToy = mList.size() == 0 ? true : false;
                            Log.i(TAG, "ssssssss: " + mList.size());
//                    Log.i(TAG, "hasToy: " + hasToy);
//                    String acttime = mList.get(0).getACTTIME();
//                    Log.i(TAG, "run: imggggg  " + acttime);
//                    String img = mList.get(0).getIMG();
//                    Log.i(TAG, "run: imggggg  " + img);

                            //如果当前用户玩具,则进入添加玩具页面
                            if (hasToy == true) {
                                FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                                ft3.replace(R.id.fl_fragmentcontainer, toyAddFragment);
                                ft3.commit();
                            } else {
                                //否则走玩具选择页面
                                EventBus.getDefault().postSticky(new MessageEventToy(mList));
                                FragmentManager supportFragmentManager = getSupportFragmentManager();
                                FragmentTransaction ft32 = supportFragmentManager.beginTransaction();
                                ft32.replace(R.id.fl_fragmentcontainer, mToySelectorFragment);
                                ft32.commit();
                                //ft3.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);玩具选择页
                                // 正常的逻辑是走扫描二维码的界面,为了调节视频,先跳转到玩具通话页面
                                // ft3.replace(R.id.fl_fragmentcontainer, toyAddFragment);
                            }
                        }
                    }, 300);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), ActivityLogin.class);
                    //改成startactivityforresult();拿到数据以后返回后更改首页ui
                    startActivity(intent);
                }
                break;
            case R.id.rb_history:
                FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                ft4.replace(R.id.fl_fragmentcontainer, historyFragment);
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

    private void chargeHasToy() {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QueryToyInterface queryToyInterface = retrofit1.create(QueryToyInterface.class);

        QueryToyRequestBean.BODYBean queryToyRequestBody = new QueryToyRequestBean.BODYBean("0", "", "", "-1", "1");
        QueryToyRequestBean queryToyRequestBean = new QueryToyRequestBean("REQ", "QRYTOYS", mMainphoneNum, mCurrentTime, queryToyRequestBody, "",
                mMainToken, "1");

        Gson mainGson = new Gson();
        String queryToyjson = mainGson.toJson(queryToyRequestBean);
        Log.i(TAG, "chargeHasToy: " + queryToyjson);
        Call<QueryToyResultBean> toyResult = queryToyInterface.getToyResult(queryToyjson);
        toyResult.enqueue(new Callback<QueryToyResultBean>() {


            @Override
            public void onResponse(Call<QueryToyResultBean> call, Response<QueryToyResultBean> response) {
                //只有list有想要的数据,所以只传list就行
                List<QueryToyResultBean.BODYBean.LSTBean> lst = response.body().getBODY().getLST();
                mList = lst;
                if (!mList.isEmpty()) {
                    Log.i(TAG, "mLst: " + mList.size());
                    Log.i(TAG, "onResponse:  lst " + response.body().getBODY().getLST());

                } else {
                    ToastUtil.showToast(getApplicationContext(), "mList---为空,请检查Lst");
                }
            }

            @Override
            public void onFailure(Call<QueryToyResultBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "失败,网络异常");
            }
        });
    }
}


