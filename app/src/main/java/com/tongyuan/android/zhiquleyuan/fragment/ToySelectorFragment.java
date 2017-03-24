package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2016/12/3.
 */

public class ToySelectorFragment extends Fragment {
    public static final String TOYDETAILS = "toydetails";
    private View toyRoot;
    private ImageView mSelectImageview;
    private ImageView mHeadeImageview;
    private ViewPager mViewpagetToy;
    private PagerAdapter mPagerAdapter;
    private ImageView mImageview_fragment_toy_addtoy;
    private static final String TAG = "222222";

    ArrayList<String> toyImg = new ArrayList<String>();
    ArrayList<String> babyImg = new ArrayList<String>();
    ArrayList<String> toyId = new ArrayList<String>();
    ArrayList<String> toyCode = new ArrayList<String>();

    String mToycode = new String();//唯一标识
    String mActtime = new String();
    String mToyimg = new String();
    String mOwnername = new String();

    public Bundle bundle = new Bundle();

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
        final ToyDetailsFragment toyDetailsFragment = new ToyDetailsFragment();
        mImageview_fragment_toy_addtoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_fragmentcontainer, new ToyAddFragment());
                transaction.commit();
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
        mViewpagetToy.setOffscreenPageLimit(3);
        mViewpagetToy.setAdapter(mPagerAdapter = new PagerAdapter() {


            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                for (int i = 0; i < toyImg.size(); i++) {

                    Glide.with(getActivity()).load(toyImg.get(i).toString()).asBitmap().into(imageView);

                }

                container.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        * 这里点击不同的图片都要进入toydetailsfragment里面,但是传递的数据是不一样的,intent bundle
                        * 需要传递的参数:1,玩具的图片; 2,玩具的型号; 3,玩具激活的时间; 4,玩具的状态
                        * */
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        String time = simpleDateFormat.format(new Date());
                        //TODO 从这里开始要处理数据!
                        switch (position) {
                            case 0:
                                //点击0 item的时候,查询该玩具的信息 ,把有用的数据传递到MyToyFrgment去,查询的方法,每个图片点击的时候都要用,所以,写成一个公共方法
                                ToastUtil.showToast(getActivity(), "点击的是" + position);

                                String toyid0 = toyId.get(0);
                                String toycode0 = toyCode.get(0);
                                SPUtils.putString(getActivity(), "toycode0", toycode0);

                                QuerySingleToyInfo(toyid0, toycode0, time);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bundle.putString("toyimg", mToyimg);
                                        Log.d(TAG, "mToyimg149: " + mToyimg);
                                        bundle.putString("toycode", mToycode);
                                        Log.d(TAG, "mmToycode149: " + mToycode);

                                        bundle.putString("acttime", mActtime);
                                        bundle.putString("ownername", mOwnername);

                                        toyDetailsFragment.setArguments(bundle);

                                        FragmentManager fragmentManager0 = getFragmentManager();
                                        FragmentTransaction transaction0 = fragmentManager0.beginTransaction();
                                        transaction0.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);
                                        transaction0.commit();
                                    }
                                }, 300);

//                                EventBus.getDefault().postSticky(new SelectorToMyToyMessageEvent(a));

                                break;
                            case 1:
                                ToastUtil.showToast(getContext(), "点击的是" + position);
                                String toyid1 = toyId.get(1);
                                String toycode1 = toyCode.get(1);
                                SPUtils.putString(getActivity(), "toycode1", toycode1);
                                QuerySingleToyInfo(toyid1, toycode1, time);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bundle.putString("toycode", mToycode);
                                        bundle.putString("toyimg", mToyimg);
                                        bundle.putString("acttime", mActtime);
                                        bundle.putString("ownername", mOwnername);
                                        toyDetailsFragment.setArguments(bundle);
                                        FragmentManager fragmentManager1 = getFragmentManager();
                                        FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                                        transaction1.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);
                                        transaction1.commit();
                                    }
                                }, 300);

                                break;
                            case 2:
                                ToastUtil.showToast(getContext(), "点击的是" + position);
                                String toyid2 = toyId.get(2);
                                String toycode2 = toyCode.get(2);
                                SPUtils.putString(getActivity(), "toycode2", toycode2);
                                QuerySingleToyInfo(toyid2, toycode2, time);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bundle.putString("toycode", mToycode);
                                        bundle.putString("toyimg", mToyimg);
                                        bundle.putString("acttime", mActtime);
                                        bundle.putString("ownername", mOwnername);
                                        toyDetailsFragment.setArguments(bundle);
                                        FragmentManager fragmentManager1 = getFragmentManager();
                                        FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                                        transaction1.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);
                                        transaction1.commit();
                                    }
                                }, 300);
                                break;

                        }
                    }

                });
                return imageView;
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
        mViewpagetToy.setPageTransformer(true, new ScaleInTransformer());
        mViewpagetToy.setCurrentItem(1);
        mViewpagetToy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);
                transaction.commit();
                ToastUtil.showToast(getActivity(), "点击的是玩具");
            }
        });
    }

    private void QuerySingleToyInfo(String toyid, String toycode, String time) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        SingleToyInfoREQBean.BODYBean bodyBean = new SingleToyInfoREQBean.BODYBean(toyid, toycode);
        SingleToyInfoREQBean singleToyInfoREQBean = new SingleToyInfoREQBean("REQ", "QTOY", SPUtils.getString(getActivity(), "phoneNum", ""), time, bodyBean, "", SPUtils.getString(getActivity(), "TOKEN", ""), "1");

        Gson gson = new Gson();
        String s = gson.toJson(singleToyInfoREQBean);
        Call<SingleToyInfoRESBean> singleToyInfoResult = allInterface.getSingleToyInfoResult(s);
        singleToyInfoResult.enqueue(new Callback<SingleToyInfoRESBean>() {
            @Override
            public void onResponse(Call<SingleToyInfoRESBean> call, Response<SingleToyInfoRESBean> response) {
                if (response != null && !response.body().getCODE().equals(0)) {
                    String toycode = response.body().getBODY().getCODE();
                    Log.i(TAG, "toycode:235 " + toycode);
                    String acttime = response.body().getBODY().getACTTIME();
                    String toyimg = response.body().getBODY().getIMG();
                    String ownername = response.body().getBODY().getOWNERNAME();

                    mToycode = toycode;
                    Log.i(TAG, "mToycode241: " + mToycode);
                    mActtime = acttime;
                    mToyimg = toyimg;
                    mOwnername = ownername;

                } else {
                    ToastUtil.showToast(getActivity(), "Response为空,请检查网络");
                    Log.d(TAG, "onResponse: 为空,请检查网络");
                }
            }

            @Override
            public void onFailure(Call<SingleToyInfoRESBean> call, Throwable t) {

            }
        });
    }

    /**
     * 思路:在这个页面,要去两个地方的数据,
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
        Log.i(TAG, "messageEventToy.mQueryBabyListResults: " + messageEventToy.mQueryBabyListResults.get(1).toString());
        Log.i(TAG, "messageEventToy.mQueryBabyListResults: " + messageEventToy.mQueryBabyListResults.size());
        ToastUtil.showToast(getActivity(), messageEventToy.mQueryBabyListResults.get(0).toString());
        //1.储存传递过来的数据用list,map,数组?看要求
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

        Log.i(TAG, "babyImg=====" + toyImg);

    }

    //获取从ToyAddFragment传过来的response数据.
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onGetToyAddMessage(AddToyMessageEvent addToyMessageEvent) {
        String img = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getIMG();
        String id = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getID();
        String code = addToyMessageEvent.mAddToyResultBeanResponse.body().getCODE();

        //这里应该做个判断,判断当前的list里面有没有这个玩具,有就不添加
        for (int i = 0; i < toyId.size(); i++) {
            if (!toyId.get(i).equals(id)) {
                toyImg.add(img);
            }
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
