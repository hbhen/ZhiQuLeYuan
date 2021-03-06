package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.MainActivity;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.AddToyRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.event.AddToyMessageEvent;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.zxing.app.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by android on 2016/12/3.
 */

public class ToyAddFragment extends BaseFragment implements View.OnClickListener {

    private View tv_toy_add_blodtext;
    private View toyRoot;
    private RelativeLayout rl_toy_add_goshopping;
    private String mToycode;
    private String mPhoneNum;
    private String mTime;
    private String mToken;
    private final static int REQUEST_CODE = 300;

    private static final String TAG = "taf";
    String code;
    private LinearLayout mIv_back;
    private TextView babyName;
    private ImageView mBabyImg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        toyRoot = inflater.inflate(R.layout.fragment_toy_add, null);
        return toyRoot;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_toy_add_blodtext = toyRoot.findViewById(R.id.tv_toy_add_boldtext);
        mIv_back = (LinearLayout) toyRoot.findViewById(R.id.iv_fragment_toy_add_back);
        mBabyImg = (ImageView) toyRoot.findViewById(R.id.iv_fragment_toy_add_babyimg);
        babyName = (TextView) toyRoot.findViewById(R.id.tv_fragment_toy_add_babyname);
        rl_toy_add_goshopping = (RelativeLayout) toyRoot.findViewById(R.id.rl_toy_add_goshopping);

//        mToySelectorFragment = new ToySelectorFragment();
        initData();
        tv_toy_add_blodtext.setOnClickListener(this);
        rl_toy_add_goshopping.setOnClickListener(this);
        mIv_back.setOnClickListener(this);
    }


    private void initData() {
        mToycode = SPUtils.getString(getActivity(), "toycode", "");
        mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mToken = SPUtils.getString(getActivity(), "token", "");
        LogUtil.i(TAG, "mToken: " + mToken);
        mTime = simpleDateFormat.format(new Date());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_toy_add_boldtext:

                //跳转到扫描二维码的界面,进行二维码扫描
                Intent intent = new Intent();
                intent.putExtra("flag", 1);
                intent.setClass(getActivity(), CaptureActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 300);//拿到玩具的唯一id code
//                ToastUtil.showToast(getActivity(), "跳转到zxing界面,进行二维码扫描");
                break;
            case R.id.rl_toy_add_goshopping:
                ToastUtil.showToast(getActivity(), "商城暂未开通");
                break;
            case R.id.iv_fragment_toy_add_back:
                //添加返回栈
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.backToTop();
//                ToastUtil.showToast(mContext, "back");
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            //返回的二维码解析数据
//            case 300:
        if (resultCode == 90) {
            code = data.getStringExtra("SCAN_RESULT");
            //对二维码数据的解析
//                    SPUtils.putString(getActivity(), "toycode", code);
            LogUtil.i(TAG, "code: " + code);
//                    ToastUtil.showToast(getActivity(), "code---" + code);
            //3.4.11 添加修改玩具属性
//                    ToastUtil.showToast(getContext(),"code++++");
            showFragment(ToySelectorFragment.class.getSimpleName());
            addToy(code);
        }
//                break;
//        }
    }


    private void addToy(String code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        //我也不知道后台要什么数据 ,就都传的是唯一的id号
        AddToyRequestBean.BODYBean addToyRequestBody = new AddToyRequestBean.BODYBean(code, code, code, code);
        AddToyRequestBean addToyRequestBean = new AddToyRequestBean("REQ", "ATOY", mPhoneNum, mTime, addToyRequestBody, "", mToken, "1");

        Gson gson = new Gson();
        String s = gson.toJson(addToyRequestBean);
        Call<AddToyResultBean> addToyResult = allInterface.getAddToyResult(s);

        addToyResult.enqueue(new Callback<AddToyResultBean>() {
            @Override
            public void onResponse(Call<AddToyResultBean> call, Response<AddToyResultBean> response) {
                if (response != null && response.body().getCODE().equals("0")) {
//                    String toyCodeGet = response.body().getBODY().getCODE();//要
//                    String toyOwneridGet = response.body().getBODY().getOWNERID();//要
//                    String acttimeGet = response.body().getBODY().getACTTIME();//要
//                    String toyOwnernameGet = response.body().getBODY().getOWNERNAME();//要
//                    String toyIdGet = response.body().getBODY().getID();//要
//                    String toyImgGet = response.body().getBODY().getIMG();
//
//                    toyCode = toyCodeGet;
//                    toyOwnerid = toyOwneridGet;
//                    acttime = acttimeGet;
//                    toyOwnername = toyOwnernameGet;
//                    toyId = toyIdGet;
//                    toyImg = toyImgGet;
//
//                    LogUtil.i(TAG, "onResponse:toyaddfragment 222----++" + response.body().toString());
//                    LogUtil.i(TAG, "toyImg +toyaddfragment" + toyImg);
//                    LogUtil.i(TAG, "toyImgGet+toyaddfragment" + toyImgGet);//拿到数据了,怎么传到ToySeleFragment?
//                    List<AddToyResultBean> list=new ArrayList<AddToyResultBean>();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.replace(R.id.fl_fragmentcontainer, mToySelectorFragment);
//                    transaction.commit();
                    LogUtil.i("55555", "(3.4.11) addtoyresultbean response:" + response.body().toString());

                    //这里添加玩具成功,需要去绑定宝宝的信息,所以跳转到宝宝绑定的页面
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), BindBabyActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("addtoyresultbean", response.body());
//                    intent.putExtras(bundle);
//                    startActivity(intent);

                    showFragment(ToySelectorFragment.class.getSimpleName());
//                    //用eventbus发送给toyselectfragment,让它获取数据
                    EventBus.getDefault().post(new AddToyMessageEvent(response));

                } else if (response.body().getCODE().equals("-10006")) {
                    SPUtils.putString(getContext(), "token", "");
                    ToastUtil.showToast(getActivity(), response.body().getMSG());
                } else {
                    ToastUtil.showToast(getActivity(), response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<AddToyResultBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), R.string.network_error);
                LogUtil.i(TAG, t.toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
