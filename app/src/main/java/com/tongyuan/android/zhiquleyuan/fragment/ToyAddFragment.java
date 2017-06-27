package com.tongyuan.android.zhiquleyuan.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.BindBabyActivity;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.AddToyRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.zxing.app.CaptureActivity;

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


    private static final String TAG = "taf";
    String code;


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
        rl_toy_add_goshopping = (RelativeLayout) toyRoot.findViewById(R.id.rl_toy_add_goshopping);
//        mToySelectorFragment = new ToySelectorFragment();
        initData();
        tv_toy_add_blodtext.setOnClickListener(this);
        rl_toy_add_goshopping.setOnClickListener(this);
    }

    private void initData() {

        mToycode = SPUtils.getString(getActivity(), "toycode", "");
        mPhoneNum = SPUtils.getString(getActivity(), "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        mToken = SPUtils.getString(getActivity(), "TOKEN", "");
        Log.i(TAG, "mToken: " + mToken);
        mTime = simpleDateFormat.format(new Date());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_toy_add_boldtext:
                //跳转到扫描二维码的界面,进行二维码扫描
                Intent intent = new Intent();
                intent.setClass(getActivity(), CaptureActivity.class);
//                startActivity(intent);

                startActivityForResult(intent, 300);//拿到玩具的唯一id code
//                ToastUtil.showToast(getActivity(), "跳转到zxing界面,进行二维码扫描");


                break;
            case R.id.rl_toy_add_goshopping:
                ToastUtil.showToast(getActivity(), "商城暂未开通");

                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回的二维码解析数据
            case 300:
                if (resultCode == Activity.RESULT_OK) {
                    code = data.getStringExtra("SCAN_RESULT");
                    //对二维码数据的解析
//                    SPUtils.putString(getActivity(), "toycode", code);
                    Log.i(TAG, "code: " + code);
                    ToastUtil.showToast(getActivity(), "code---" + code);
                    //3.4.11 添加修改玩具属性
                    addToy(code);
                }
                break;
        }
    }


    private void addToy(String code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
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
//                    Log.i(TAG, "onResponse:toyaddfragment 222----++" + response.body().toString());
//                    Log.i(TAG, "toyImg +toyaddfragment" + toyImg);
//                    Log.i(TAG, "toyImgGet+toyaddfragment" + toyImgGet);//拿到数据了,怎么传到ToySeleFragment?
//                    List<AddToyResultBean> list=new ArrayList<AddToyResultBean>();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.replace(R.id.fl_fragmentcontainer, mToySelectorFragment);
//                    transaction.commit();
                    Log.i("55555", "(3.4.11) addtoyresultbean response:" + response.body().toString());

                    //这里添加玩具成功,需要去绑定宝宝的信息,所以跳转到宝宝绑定的页面
                    Intent intent = new Intent();
                    intent.setClass(getContext(), BindBabyActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("addtoyresultbean", response.body());
                    intent.putExtras(bundle);
                    startActivity(intent);

//                    showFragment(ToySelectorFragment.class.getSimpleName());
//                    //用eventbus发送给toyselectfragment,让它获取数据
//                    EventBus.getDefault().post(new AddToyMessageEvent(response));

                } else {
                    ToastUtil.showToast(getActivity(), "response为空,获取不到数据,网络异常");
                }
            }

            @Override
            public void onFailure(Call<AddToyResultBean> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "失败联网");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
