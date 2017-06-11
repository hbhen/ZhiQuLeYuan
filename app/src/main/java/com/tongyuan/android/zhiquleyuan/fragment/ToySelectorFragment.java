package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.MainActivity;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoREQBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.event.AddToyMessageEvent;
import com.tongyuan.android.zhiquleyuan.event.MessageEventToy;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2016/12/3.
 */

public class ToySelectorFragment extends BaseFragment {
    public static final String TOYDETAILS = "toydetails";
    private View toyRoot;
    private ImageView mSelectImageview;
    private ImageView mHeadeImageview;
    private ViewPager mViewpagetToy;
    private PagerAdapter mPagerAdapter;
    private ImageView mImageview_fragment_toy_addtoy;
    private static final String TAG = "222222";
//    private ToyAddFragment toyAddFragment = new ToyAddFragment();

    ArrayList<String> toyImg = new ArrayList<String>();
    ArrayList<String> babyImg = new ArrayList<String>();
    ArrayList<String> toyId = new ArrayList<String>();
    ArrayList<String> toyCode = new ArrayList<String>();
    private SingleToyInfoRESBean body;
//    List<Response<SingleToyInfoRESBean>> responseList;
//    String mToycode = new String();//唯一标识
//    String mActtime = new String();
//    String mToyimg = new String();
//    String mOwnername = new String();

    private ImageView mImageView;
    private Bundle mBundle;
    public static String mToyid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        toyRoot = inflater.inflate(R.layout.fragment_toy, null);
//       mSelectImageview = (ImageView) toyRoot.findViewById(R.id.iv_fragment_toy);
        mViewpagetToy = (ViewPager) toyRoot.findViewById(R.id.id_viewpager);

        mHeadeImageview = (ImageView) toyRoot.findViewById(R.id.iv_fragment_toy_headimage);

        mImageview_fragment_toy_addtoy = (ImageView) toyRoot.findViewById(R.id.imageview_fragment_toy_addtoy);
//        mSelectImageview.setFocusable(true);
        mHeadeImageview.setFocusable(false);

        return toyRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        final ToyDetailsFragment toyDetailsFragment = new ToyDetailsFragment();
        mImageview_fragment_toy_addtoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.fl_fragmentcontainer, new ToyAddFragment());
//                transaction.commit();
                showFragment(ToyAddFragment.class.getSimpleName());
            }
        });
        //不知道点击头像要跳到哪里,先添加一个点击事件吧
        mHeadeImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(getActivity(), "点击的是头像");
            }

        });

        mViewpagetToy.setPageMargin(20);
        mViewpagetToy.setOffscreenPageLimit(10);


        Log.i(TAG, "new MyPagerAdapter....");
        mViewpagetToy.setAdapter(mPagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                mImageView = new ImageView(getActivity());
                mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Log.i("111111", "instaniateItem " + toyImg.get(position));
                Glide.with(getActivity()).load(toyImg.get(position)).asBitmap().into(mImageView);
                container.addView(mImageView);

                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        * 这里点击不同的图片都要进入toydetailsfragment里面,但是传递的数据是不一样的,intent bundle
                        * 需要传递的参数:1,玩具的图片; 2,玩具的型号; 3,玩具激活的时间; 4,玩具的状态
                        * 根据position来判断进入的是哪个玩具
                        * */

                        //TODO 从这里开始要处理数据!
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        String time = simpleDateFormat.format(new Date());

                        mToyid = toyId.get(position);
                        Log.i(TAG, "onClick:toyid " + mToyid);
                        SPUtils.putString(getActivity(), "toyidtopush", mToyid);
                        String toycode = toyCode.get(position);
                        Log.i(TAG, "onClick:toycode " + toycode);

                        QuerySingleToyInfo(mToyid, toycode, time,position);

                        Toast.makeText(getActivity(), "当前position" + position, Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onClick: toyid1" + mToyid);

//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {

//
//                            }
//                        }, 300);
                    }
                });


                return mImageView;
            }

            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return toyImg.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == (View) object;
            }

        });
        Log.i("111111", "mPagerAdapter=" + mPagerAdapter);
        mViewpagetToy.setPageTransformer(true, new ScaleInTransformer());
        mViewpagetToy.setCurrentItem(1);
        mViewpagetToy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //TODO
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
////获取数据
//    public Bundle getBundle() {
//        return mBundle;
//    }
////设置数据
//    public void setBundle(Bundle bundle) {
//        this.mBundle = bundle;
//    }

    //查询单个玩具的信息
    private void QuerySingleToyInfo(String toyid, String toycode, String time, final int position) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        SingleToyInfoREQBean.BODYBean bodyBean = new SingleToyInfoREQBean.BODYBean(toyid, toycode);
        SingleToyInfoREQBean singleToyInfoREQBean = new SingleToyInfoREQBean("REQ", "QTOY", SPUtils.getString(getActivity(), "phoneNum", ""), time,
                bodyBean, "", SPUtils.getString(getActivity(), "TOKEN", ""), "1");

        Gson gson = new Gson();
        String s = gson.toJson(singleToyInfoREQBean);
        Call<SingleToyInfoRESBean> singleToyInfoResult = allInterface.getSingleToyInfoResult(s);
        singleToyInfoResult.enqueue(new Callback<SingleToyInfoRESBean>() {
            @Override
            public void onResponse(Call<SingleToyInfoRESBean> call, Response<SingleToyInfoRESBean> response) {
                if (response != null && response.body().getCODE().equals("0")) {
//                    String toycode = response.body().getBODY().getCODE();
//                    Log.i(TAG, "toycode:235 " + toycode);
//                    String acttime = response.body().getBODY().getACTTIME();
//                    String toyimg = response.body().getBODY().getIMG();
//                    String ownername = response.body().getBODY().getOWNERNAME();
//
//                    mToycode = toycode;
//                    Log.i(TAG, "mToycode241: " + mToycode);
//                    mActtime = acttime;
//                    mToyimg = toyimg;
//                    mOwnername = ownername;
                    body = response.body();

                    MainActivity mainActivity = (MainActivity) getActivity();

                    mainActivity.getToyDetailsFragment().setData(body.getBODY(), babyImg.get(position));

                    showFragment(ToyDetailsFragment.class.getSimpleName());
                    Log.i(TAG, "onResponse: +toyselelctorfragment+body" + body.getBODY().toString());

                } else {
                    ToastUtil.showToast(getActivity(), "Response为空,请检查网络");
                    Log.d(TAG, "onResponse: 为空,请检查网络");
                }
            }

            @Override
            public void onFailure(Call<SingleToyInfoRESBean> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.toString());
            }
        });
    }

    /**
     * 思路:在这个页面,要取两个地方的数据,
     * 一/是从mainactivity传过来的mList数据,从list拿到所有的1,玩具的图片2,宝宝的头像3,其他需要的信息.
     * 二/从addtoyfragment传过来的,二维码扫描后的结果,这个结果就是一个code,qrcode在addtoyfragment里面
     * 处理过后,拿到里面的信息,比如toyid,类别,激活状态等等.addtoyfragment处理完数据以后,再通过eventbus传到
     * 本页面,与mainactivity传过来的数据合并,把图片传递到viewpager上,显示出来,点击不同的图片,进入不同的fragment页面(fragment是相同
     * 的,只是传递的数据不同,要在这里做判断.
     */

    //接收并处理从activity传过来的数据
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onToyMessage(MessageEventToy messageEventToy) {
        //从mainactivity传过来的lst数据,
//        Log.i(TAG, "messageEventToy.mQueryBabyListResults: " + messageEventToy.mQueryBabyListResults.get(1).1toString());
//        Log.i(TAG, "messageEventToy.mQueryBabyListResults: " + messageEventToy.mQueryBabyListResults.size());
//        ToastUtil.showToast(getActivity(), messageEventToy.mQueryBabyListResults.get(0).toString());
        //1.储存传递过来的数据用list,map,数组?看要求
//        List<QueryToyResultBean.BODYBean.LSTBean> listResults = messageEventToy.mQueryBabyListResults;

        babyImg.clear();
        toyId.clear();
        toyImg.clear();
        toyId.clear();
        toyCode.clear();
        for (int i = 0; i < messageEventToy.mQueryBabyListResults.size(); i++) {

            String imgBaby = messageEventToy.mQueryBabyListResults.get(i).getBABYIMG();
            String imgToy = messageEventToy.mQueryBabyListResults.get(i).getIMG();
            String ownername = messageEventToy.mQueryBabyListResults.get(i).getOWNERNAME();
            String ownerId = messageEventToy.mQueryBabyListResults.get(i).getOWNERID();
            String code = messageEventToy.mQueryBabyListResults.get(i).getCODE();
            String idToy = messageEventToy.mQueryBabyListResults.get(i).getID();
            String timeAct = messageEventToy.mQueryBabyListResults.get(i).getACTTIME();
            //TODO 需不需要在这个界面就把玩具的状态信息拿到?
            babyImg.add(imgBaby);
            toyImg.add(imgToy);
            toyId.add(idToy);
            toyCode.add(code);
        }

        Log.i(TAG, "babyImg=====" + babyImg);
        Log.i(TAG, "toyImg size=====" + toyImg.size());
        Log.i(TAG, "onToyMessage: adapter1"+mPagerAdapter);
        if (mPagerAdapter!=null)
        mPagerAdapter.notifyDataSetChanged();
        Log.i(TAG, "onToyMessage: adapter"+mPagerAdapter);

    }

    //获取从ToyAddFragment传过来的response数据.
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void onGetToyAddMessage(AddToyMessageEvent addToyMessageEvent) {
        Response<AddToyResultBean> addToyResultBeanResponse = addToyMessageEvent.mAddToyResultBeanResponse;

        //拿到从ToyAddFragment传过来的response数据
        List<Response<AddToyResultBean>> responseList = new ArrayList<>();

        String img = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getIMG();
        String id = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getID();
        String code = addToyMessageEvent.mAddToyResultBeanResponse.body().getCODE();
//        String code = addToyMessageEvent.mAddToyResultBeanResponse.body().getBABYIMG();
//        String imgBaby = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().get;
        Log.i("111111", "onGetToyAddMessage: " + toyId.size());
        //这里应该做个判断,判断当前的list里面有没有这个玩具,有就不添加
        boolean isAdd = true;
        for (int i = 0; i < toyId.size(); i++) {
            if (toyId.get(i).equals(id)) {
                isAdd = false;
            }
        }

        if (isAdd) {
            toyImg.add(img);
            toyId.add(id);
            toyCode.add(code);
//            babyImg.add(imgBaby);
        }
//        Log.i("111111", Thread.currentThread().getName() + " mPagerAdapre" + mPagerAdapter);
        Log.i("111111", " toyId.size2" + toyId.size());
        if (mPagerAdapter != null)
            mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
//        MainActivity mainactivity = (MainActivity) getActivity();
//        mainactivity.getToyDetailsFragment().setArguments();

    }
}
