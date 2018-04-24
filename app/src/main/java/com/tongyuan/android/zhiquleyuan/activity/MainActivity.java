package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.base.ActivityManager;
import com.tongyuan.android.zhiquleyuan.base.BaseActivity;
import com.tongyuan.android.zhiquleyuan.bean.PhoneHeartReqBean;
import com.tongyuan.android.zhiquleyuan.bean.PhoneHeartResBean;
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
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.interf.QueryToyInterface;
import com.tongyuan.android.zhiquleyuan.service.CheckTokenService;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.common.QueuedWork;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends BaseActivity implements View.OnClickListener {

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
    private Boolean isQuit = false;
    private Timer mTimer = new Timer();
    private long exitTime = 0;
    UMShareListener mUMShareListener;
//    private boolean isFirstInstall = true;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ActivityManager.addAvtivity(this);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));
        CrashReport.initCrashReport(getApplicationContext(), "4d4412e3f1", true);
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
//        SPUtils.putString(this, "installation", "1");//第一次安装
//        if (SPUtils.getString(this, "installation", "").equals("1")) {
//            SPUtils.putString(this, "installation", "2");
//            startActivityForResult(new Intent(this, ActivityLogin.class), 79);
//        }
        checkToken();
        PlatformConfig.setWeixin("wx0d4463dd363141ea", "c6d8ce330df02acdbdce34226ecff193");

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
        LogUtil.i(TAG1, "mainactivity : onCreate went");

    }

    @Override
    protected void onPrepared() {
        if (recodingFragment != null)
            recodingFragment.onPrepared();
    }

    @Override
    protected void onError() {
        if (recodingFragment != null)
            recodingFragment.onError();
    }

    @Override
    protected void onCompleted() {
        if (recodingFragment != null)
            recodingFragment.onCompleted();
    }

    @Override
    protected void bindSuccess() {
        if (recodingFragment != null)
            recodingFragment.bindSuccess();
    }

    @Override
    protected void isSimplePlayUrl() {
        if (recodingFragment != null)
            recodingFragment.isSimplePlayUrl();
    }

    private void checkToken() {
        Intent intent = new Intent();
        intent.setClass(this, CheckTokenService.class);
        startService(intent);
        LogUtil.i(TAG, "checkToken: startservice +走了");
//        String token = SPUtils.getString(this, "token", "");
//        getServiceToken(token);
//        if (token == null) {
//            //发送广播,通知,各个控件
//            return;
//        } else {
//            //token不为空,从服务器查询token,两次的token是否一致,一致就显示一种界面,不一致就显示未登录的界面
//        }
    }

    private void getServiceToken(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        PhoneHeartReqBean.BODYBean bodyBean = new PhoneHeartReqBean.BODYBean("A", "APP", "1.0", "", "", "", "", "", "");
        PhoneHeartReqBean phoneHeartReqBean = new PhoneHeartReqBean("REQ", "HEART", "", new SimpleDateFormat
                ("yyyyMMddHHmmssSSS").format(new Date()),
                bodyBean, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(phoneHeartReqBean);
        Call<PhoneHeartResBean> phoneHeartResBeanCall = allInterface.PHONEHEART_RES_BEAN_CALL(s);
        phoneHeartResBeanCall.enqueue(new Callback<PhoneHeartResBean>() {

            @Override
            public void onResponse(Call<PhoneHeartResBean> call, Response<PhoneHeartResBean> response) {
                if (response.body().getCODE().equals("-10006")) {
                    ToastUtil.showToast(getApplicationContext(), response.body().getMSG());
                    SPUtils.putString(getApplicationContext(), "token", "");
                }
            }

            @Override
            public void onFailure(Call<PhoneHeartResBean> call, Throwable t) {
                LogUtil.i(TAG, "onFailure: " + t.toString());
            }
        });

    }

    private void initData() {

        mCurrentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        mMainToken = SPUtils.getString(this, "token", "");
        mMainphoneNum = SPUtils.getString(this, "phoneNum", "");
        mList = new ArrayList<QueryToyResultBean.BODYBean.LSTBean>();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            //TAG_EXIT="exit"
            boolean isExit = intent.getBooleanExtra("exit", false);
            if (isExit) {
                this.finish();
            }
        }

    }

    private void initView() {

        rb_discovery = (LinearLayout) findViewById(R.id.rb_discovery);
        rb_recoding = (LinearLayout) findViewById(R.id.rb_recoding);
        rb_toy = (ImageView) findViewById(R.id.rb_toy);
        rb_history = (LinearLayout) findViewById(R.id.rb_history);
        rb_mine = (LinearLayout) findViewById(R.id.rb_mine);

    }

    private void showFragment(Fragment f, String name) {

        FragmentManager fragmentManager = getSupportFragmentManager();
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

        LogUtil.d("false", "transaction!" + transaction + "----fragment" + f);

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
            } else if (name.equals(MineFragment.class.getSimpleName())) {
                fragmentStack.push(f);
            }
        }

        //transaction.commit();
        transaction.commitAllowingStateLoss();

    }

    private Fragment currentFragment;

    public void showFragment(String name) {
        if (name.equals(DiscoveryFragment.class.getSimpleName())) {
            if (discoveryFragment == null) {
                discoveryFragment = new DiscoveryFragment();
            }
            showFragment(discoveryFragment, name);
            currentFragment = discoveryFragment;
        } else if (name.equals(RecodingFragment.class.getSimpleName())) {
            if (recodingFragment == null) {
                recodingFragment = new RecodingFragment();
            }
            showFragment(recodingFragment, name);
            currentFragment = recodingFragment;
        } else if (name.equals(ToyAddFragment.class.getSimpleName())) {
            if (toyAddFragment == null) {
                toyAddFragment = new ToyAddFragment();
            }
            showFragment(toyAddFragment, name);
            currentFragment = toyAddFragment;
        } else if (name.equals(ToySelectorFragment.class.getSimpleName())) {
            fragmentStack.clear();
            if (mToySelectorFragment == null) {
                mToySelectorFragment = new ToySelectorFragment();
            }
            showFragment(mToySelectorFragment, name);
            currentFragment = mToySelectorFragment;
        } else if (name.equals(HistoryFragment.class.getSimpleName())) {
            if (historyFragment == null) {
                historyFragment = new HistoryFragment();
            }
            showFragment(historyFragment, name);
            currentFragment = historyFragment;
        } else if (name.equals(MineFragment.class.getSimpleName())) {
            if (mineFragment == null) {
                mineFragment = new MineFragment();
            }
            showFragment(mineFragment, name);
            currentFragment = mineFragment;
        } else if (name.equals(ToyDetailsFragment.class.getSimpleName())) {
            if (toyDetailsFragment == null) {
                toyDetailsFragment = new ToyDetailsFragment();
            }
            showFragment(toyDetailsFragment, name);
            currentFragment = toyDetailsFragment;
        } else if (name.equals(CallWaitingConnectFragment.class.getSimpleName())) {
            if (mCallWaitingConnectFragment == null) {
                mCallWaitingConnectFragment = new CallWaitingConnectFragment();
            }
            showFragment(mCallWaitingConnectFragment, name);
            currentFragment = mCallWaitingConnectFragment;
        }

    }

    public void backToTop() {
        if (fragmentStack.isEmpty()) {
            moveTaskToBack(false);
        } else {
            if (currentFragment instanceof ToyAddFragment) {
                fragmentStack.pop();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_fragmentcontainer, mToySelectorFragment).
                        commit();
                currentFragment = mToySelectorFragment;
            } else if (currentFragment instanceof ToyDetailsFragment) {
                fragmentStack.pop();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_fragmentcontainer, mToySelectorFragment).
                        commit();
                currentFragment = mToySelectorFragment;
            } else if (currentFragment instanceof ToyManagerFragment) {
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
            if (fragment == null) {
                return;
            }
            showFragment(fragment, ToyManagerFragment.class.getSimpleName());
            currentFragment = fragment;
        }
    }

    private void initFragment() {
        //添加五个Fragment对象添加进来
//        discoveryFragment = new DiscoveryFragment();
//        recodingFragment = new RecodingFragment();
//        mToySelectorFragment = new ToySelectorFragment();
//        toyAddFragment = new ToyAddFragment();
//        historyFragment = new HistoryFragment();
//        mineFragment = new MineFragment();
//        toyDetailsFragment = new ToyDetailsFragment();
//        mCallWaitingConnectFragment = new CallWaitingConnectFragment();
        showFragment(DiscoveryFragment.class.getSimpleName());
    }

    public ToyDetailsFragment getToyDetailsFragment() {
        if (toyDetailsFragment == null) {
            toyDetailsFragment = new ToyDetailsFragment();
        }
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
                rb_toy.setSelected(false);
                showFragment(DiscoveryFragment.class.getSimpleName());

                break;
            case R.id.rb_recoding:

                mMainToken = SPUtils.getString(this, "token", "");
                if ("".equals(mMainToken)) {
                    startActivityForResult(new Intent(this, ActivityLogin.class), 78);
                    return;

                }
                rb_discovery.setSelected(false);
                rb_recoding.setSelected(true);
                rb_history.setSelected(false);
                rb_mine.setSelected(false);
                rb_toy.setSelected(false);
                if (recodingFragment == null) {
                    recodingFragment = new RecodingFragment();
                }

                showFragment(recodingFragment, RecodingFragment.class.getSimpleName());

                break;

            case R.id.rb_history:
                mMainToken = SPUtils.getString(this, "token", "");
                if ("".equals(mMainToken)) {
                    startActivityForResult(new Intent(this, ActivityLogin.class), 78);
                    return;
                }
                showHistoryFragment();
                break;
            case R.id.rb_mine:
                mMainToken = SPUtils.getString(this, "token", "");
                if ("".equals(mMainToken)) {
                    startActivityForResult(new Intent(this, ActivityLogin.class), 78);
                    return;
                }
                rb_mine.setSelected(true);
                rb_discovery.setSelected(false);
                rb_recoding.setSelected(false);
                rb_history.setSelected(false);
                rb_toy.setSelected(false);
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                showFragment(mineFragment, null);

                break;
            case R.id.rb_toy:
                rb_toy.setSelected(true);
                rb_discovery.setSelected(false);
                rb_recoding.setSelected(false);
                rb_history.setSelected(false);
                rb_mine.setSelected(false);
                mMainToken = SPUtils.getString(this, "token", "");
                if ("".equals(mMainToken)) {
                    chargeHasLogin();
                    return;
                }
                showToyFragment();
                break;
            default:
                break;

        }
    }

    private void showHistoryFragment() {
        rb_discovery.setSelected(false);
        rb_recoding.setSelected(false);
        rb_history.setSelected(true);
        rb_mine.setSelected(false);
        rb_toy.setSelected(false);
        if (historyFragment == null) {
            historyFragment = new HistoryFragment();
        }

        showFragment(historyFragment, null);
    }

    private void showToyFragment() {

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

        mMainToken = SPUtils.getString(this, "token", "");

        //看是否有玩具,有玩具就去selector页面,无玩具,就去玩具添加页面

        chargeHasToy(mMainToken);

    }


    private void chargeHasLogin() {
        if (mMainToken.equals("")) {
            //未登录,去登录页面
            startActivityForResult(new Intent(this, ActivityLogin.class), 77);
        } else {
            //看是否有玩具,有玩具就去selector页面,无玩具,就去玩具添加页面
            chargeHasToy(mMainToken);
        }
    }

    private void chargeHasToy(String token) {

//        if (mList == null || mList.size() == 0) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QueryToyInterface queryToyInterface = retrofit1.create(QueryToyInterface.class);
        QueryToyRequestBean.BODYBean queryToyRequestBody = new QueryToyRequestBean.BODYBean("0", "", "", "-1", "1");
        QueryToyRequestBean queryToyRequestBean = new QueryToyRequestBean("REQ", "QRYTOYS", mMainphoneNum,
                mCurrentTime, queryToyRequestBody, "",
                token, "1");
        Gson mainGson = new Gson();
        String queryToyjson = mainGson.toJson(queryToyRequestBean);
        LogUtil.i(TAG, "chargeHasToy: " + queryToyjson);
        Call<QueryToyResultBean> toyResult = queryToyInterface.getToyResult(queryToyjson);
        toyResult.enqueue(new Callback<QueryToyResultBean>() {
            @Override
            public void onResponse(Call<QueryToyResultBean> call, Response<QueryToyResultBean> response) {
                //只有list有想要的数据,所以只传list就行
                if ("0".equals(response.body().getCODE())) {
                    mList = response.body().getBODY().getLST();
                    LogUtil.i(TAG, "MainActivity+onResponse:list1" + mList.toString());
                    if (mList != null) {
                        showDifferentToyFragment(mList);
                    }
                } else {
                    ToastUtil.showToast(mContext, response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<QueryToyResultBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "失败,网络异常");
            }
        });

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

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

//    private void exit() {

    //    }


    @Override
    public void onBackPressed() {
        //点两次返回键,退出程序
//        ArrayList<String> arrayList = new ArrayList<>();
//        if (System.currentTimeMillis() - exitTime > 2000) {
//            ToastUtil.showToast(this, "再按一次退出程序");
//            exitTime = System.currentTimeMillis();
//        } else {
//            for (Object s : ActivityManager.queryActivity()) {
//                Activity activity = (Activity) s;
//                LogUtil.d("uiu", "onBackPressed: activity" + activity.toString());
//            }
//
//            ActivityManager.finishAll();
//            finish();
//            System.exit(0);
//            android.os.Process.killProcess(Process.myPid());
//        }
//        backToTop();
        if (isQuit == false) {
            isQuit = true;
            ToastUtil.showToast(this, "再按一次返回键退出程序");
            TimerTask timerTask = null;
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            mTimer.schedule(timerTask, 2000);
        } else {

            LogUtil.d(TAG, "onBackPressed: " + ActivityManager.queryActivity().size());
            ActivityManager.finishAll();
            finish();
            System.exit(0);
            long l = SystemClock.elapsedRealtime();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    //接收来自fragment的result 用getactivity();来接收的;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 77 && data != null && resultCode == 5011) {// toyFragment
            showToyFragment();
            return;
        } else if (requestCode == 78 && data != null) {
            showHistoryFragment();
            return;
        } else if (resultCode == 90) {
            if (toyAddFragment != null)
                toyAddFragment.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 79) {
            showFragment(DiscoveryFragment.class.getSimpleName());
        } else if (requestCode == 80) {
            showFragment(MineFragment.class.getSimpleName());
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}


