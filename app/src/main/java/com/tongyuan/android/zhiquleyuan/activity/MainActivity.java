package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyResultBean;
import com.tongyuan.android.zhiquleyuan.event.MessageEventToy;
import com.tongyuan.android.zhiquleyuan.fragment.CallWaitingConnectFragment;
import com.tongyuan.android.zhiquleyuan.fragment.DiscoveryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.HistoryFragment;
import com.tongyuan.android.zhiquleyuan.fragment.MineFragment;
import com.tongyuan.android.zhiquleyuan.fragment.RecodingFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyAddFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyDetailsFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToyManagerFragment;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.QueryToyInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

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
    private DiscoveryFragment discoveryFragment;
    private RecodingFragment recodingFragment;
    private ToySelectorFragment mToySelectorFragment;
    private HistoryFragment historyFragment;
    private MineFragment mineFragment;
    private ToyAddFragment toyAddFragment;
    private ToyDetailsFragment toyDetailsFragment;
    private String mCurrentTime;
    private String mMainToken;
    private String mMainphoneNum;
    public List<QueryToyResultBean.BODYBean.LSTBean> mList;
    private CallWaitingConnectFragment mCallWaitingConnectFragment;
    private Stack<Fragment> fragmentStack = new Stack<>();
    private static final String TAG1 = "88888";


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));

        CrashReport.initCrashReport(getApplicationContext(), "4d4412e3f1", true);
//        CrashReport.testJavaCrash();

        initView();
        initData();
        if (savedInstanceState == null) {

            initFragment();
        }
        rb_discovery.setSelected(true);
        rb_discovery.setOnClickListener(this);
        rb_recoding.setOnClickListener(this);
        rb_toy.setOnClickListener(this);
        rb_history.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
        Log.i(TAG1, "mainactivity : onCreate went");
        //请求网络,获得我的录音的文件数据,拿到里面,请求网络数据,需要准备

    }

    private void initData() {
        mCurrentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        mMainToken = SPUtils.getString(this, "TOKEN", "");
        mMainphoneNum = SPUtils.getString(this, "phoneNum", "");
        mList = new ArrayList<QueryToyResultBean.BODYBean.LSTBean>();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void initView() {
        rb_discovery = (LinearLayout) findViewById(R.id.rb_discovery);
        rb_recoding = (LinearLayout) findViewById(R.id.rb_recoding);
        rb_toy = (ImageView) findViewById(R.id.rb_toy);
        rb_history = (LinearLayout) findViewById(R.id.rb_history);
        rb_mine = (LinearLayout) findViewById(R.id.rb_mine);

    }

    FragmentManager fragmentManager;

    private void showFragment(Fragment f, String name) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /*List<Fragment> fragmentList = fragmentManager.getFragments();
        if (fragmentList != null) {
            for (Fragment fragment : fragmentManager.getFragments()) {
                transaction.hide(fragment);
            }
        }
        if (f.isAdded()) {

            transaction.show(f);
        } else {
            transaction.add(R.id.fl_fragmentcontainer, f).show(f);
        }

        }*/
        transaction.replace(R.id.fl_fragmentcontainer, f);
        if (name != null) {
            if (name.equals(ToyDetailsFragment.class.getSimpleName())) {
                //transaction.addToBackStack(null);
                fragmentStack.push(f);
            } else if (name.equals(ToyManagerFragment.class.getSimpleName())) {
                //transaction.addToBackStack(null);
                fragmentStack.push(f);
            } else if (name.equals(ToyAddFragment.class.getSimpleName())) {
                //transaction.addToBackStack(null);
                fragmentStack.push(f);
            }
        }

        transaction.commit();

    }

    private Fragment currentFragment;

    public void showFragment(String name) {
        if (name.equals(DiscoveryFragment.class.getSimpleName())) {
            showFragment(discoveryFragment, name);
            currentFragment = discoveryFragment;
        } else if (name.equals(RecodingFragment.class.getSimpleName())) {
            showFragment(recodingFragment, name);
            currentFragment = recodingFragment;
        } else if (name.equals(ToyAddFragment.class.getSimpleName())) {
            showFragment(toyAddFragment, name);
            currentFragment = toyAddFragment;
        } else if (name.equals(ToySelectorFragment.class.getSimpleName())) {
            fragmentStack.clear();
            showFragment(mToySelectorFragment, name);
            currentFragment = mToySelectorFragment;
        } else if (name.equals(HistoryFragment.class.getSimpleName())) {
            showFragment(historyFragment, name);
            currentFragment = historyFragment;
        } else if (name.equals(MineFragment.class.getSimpleName())) {
            showFragment(mineFragment, name);
            currentFragment = mineFragment;
        } else if (name.equals(ToyDetailsFragment.class.getSimpleName())) {
            showFragment(toyDetailsFragment, name);
            currentFragment = toyDetailsFragment;
        } else if (name.equals(CallWaitingConnectFragment.class.getSimpleName())) {
            showFragment(mCallWaitingConnectFragment, name);
            currentFragment = mCallWaitingConnectFragment;
        }

    }

    public void backToTop() {
        if (fragmentStack.isEmpty()) {
            moveTaskToBack(false);
        } else {
            if(currentFragment instanceof ToyAddFragment) {
                fragmentStack.pop();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_fragmentcontainer, mToySelectorFragment).
                        commit();
                currentFragment = mToySelectorFragment;
            } else if(currentFragment instanceof ToyDetailsFragment) {
                fragmentStack.pop();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_fragmentcontainer, mToySelectorFragment).
                        commit();
                currentFragment = mToySelectorFragment;
            } else if(currentFragment instanceof ToyManagerFragment) {
                fragmentStack.pop();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_fragmentcontainer, toyDetailsFragment).
                        commit();
                currentFragment = toyDetailsFragment;
            }
        }
    }

    public void showFragment(Fragment fragment) {
        if (fragment instanceof ToyManagerFragment) {
            showFragment(fragment, ToyManagerFragment.class.getSimpleName());
            currentFragment = fragment;
        }
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
//        mVideoFragment = new VideoFragment();
        mCallWaitingConnectFragment = new CallWaitingConnectFragment();
        showFragment(DiscoveryFragment.class.getSimpleName());
    }

    public ToyDetailsFragment getToyDetailsFragment() {
        return toyDetailsFragment;
    }

    public void onClick(View view) {
        fragmentStack.clear();
        switch (view.getId()) {
            case R.id.rb_discovery:
                rb_discovery.setSelected(true);
                rb_recoding.setSelected(false);
                rb_history.setSelected(false);
                rb_mine.setSelected(false);
                showFragment(DiscoveryFragment.class.getSimpleName());

                break;
            case R.id.rb_recoding:
                rb_discovery.setSelected(false);
                rb_recoding.setSelected(true);
                rb_history.setSelected(false);
                rb_mine.setSelected(false);
                if (recodingFragment == null) {
                    recodingFragment = new RecodingFragment();
                }

                showFragment(recodingFragment, RecodingFragment.class.getSimpleName());

                break;

            case R.id.rb_history:
                rb_discovery.setSelected(false);
                rb_recoding.setSelected(false);
                rb_history.setSelected(true);
                rb_mine.setSelected(false);
                if (historyFragment == null) {
                    historyFragment = new HistoryFragment();
                }
                mMainToken = SPUtils.getString(this, "TOKEN", "");
                if (!mMainToken.equals("")) {
                    showFragment(historyFragment, null);
                } else {
                    startActivity(new Intent(getApplicationContext(), ActivityLogin.class));

                }

                break;
            case R.id.rb_mine:
                rb_discovery.setSelected(false);
                rb_recoding.setSelected(false);
                rb_history.setSelected(false);
                rb_mine.setSelected(true);
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                showFragment(mineFragment, null);

                break;
            case R.id.rb_toy:
                 /*
                * 点击button,查询当前的用户是否有玩具,有玩具就展示出来,展示到selectToy页面
                否则进入无玩具页面,要求添加页面  待添加完玩具以后,再把玩具的图片和id,绑定的宝宝信息,传到selectToy页面,并展示
                * */
                rb_discovery.setSelected(false);
                rb_recoding.setSelected(false);
                rb_history.setSelected(false);
                rb_mine.setSelected(false);
                rb_toy.setSelected(true);
                //获取手机的心跳接口,获取最新的token,比较token,如果为空,去登录页,如果不相同 , 也去登录页面.
                //应该先判断是否登录,再判断是否有玩具
                mMainToken = SPUtils.getString(this, "TOKEN", "");

                Log.i(TAG, "onClick: token(mainactivity" + mMainToken);
                chargeHasLogin(mMainToken);
                break;
            default:
                break;

        }
    }


    private void chargeHasLogin(String token) {

        if (token.equals("")) {
            //未登录,去登录页面
            startActivity(new Intent(this, ActivityLogin.class));
        } else {
            //看是否有玩具,有玩具就去selector页面,无玩具,就去玩具添加页面
            chargeHasToy(token);

        }
    }

    private void chargeHasToy(String token) {

//        if (mList == null || mList.size() == 0) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QueryToyInterface queryToyInterface = retrofit1.create(QueryToyInterface.class);
        QueryToyRequestBean.BODYBean queryToyRequestBody = new QueryToyRequestBean.BODYBean("0", "", "", "-1", "1");
        QueryToyRequestBean queryToyRequestBean = new QueryToyRequestBean("REQ", "QRYTOYS", mMainphoneNum, mCurrentTime, queryToyRequestBody, "",
                token, "1");
        Gson mainGson = new Gson();
        String queryToyjson = mainGson.toJson(queryToyRequestBean);
        Log.i(TAG, "chargeHasToy: " + queryToyjson);
        Call<QueryToyResultBean> toyResult = queryToyInterface.getToyResult(queryToyjson);
        toyResult.enqueue(new Callback<QueryToyResultBean>() {
            @Override
            public void onResponse(Call<QueryToyResultBean> call, Response<QueryToyResultBean> response) {
                //只有list有想要的数据,所以只传list就行
                mList = response.body().getBODY().getLST();
                //Log.i(TAG, "MainActivity+onResponse:list1" + mList.toString());
                //Log.i("gengen", "MainActivity+onResponse:list1" + mList.toString());
                if (mList != null) {
                    showDifferentToyFragment(mList);
                }
            }

            @Override
            public void onFailure(Call<QueryToyResultBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "失败,网络异常");
            }
        });
//        }

    }

    private void showDifferentToyFragment(List<QueryToyResultBean.BODYBean.LSTBean> lst) {
        if (lst.size() != 0) {
            EventBus.getDefault().postSticky(new MessageEventToy(lst));
            //不为空,去玩具选择页面
            showFragment(ToySelectorFragment.class.getSimpleName());
        } else {

            //为空,去玩具添加页面

            showFragment(ToyAddFragment.class.getSimpleName());

        }
    }

    @Override
    public void onBackPressed() {
        backToTop();
    }

}


