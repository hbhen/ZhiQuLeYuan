package com.tongyuan.android.zhiquleyuan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.MainActivity;
import com.tongyuan.android.zhiquleyuan.adapter.ToySelectPagerAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromNormalUserReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromNormalUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromPowerUserReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromPowerUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoREQBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.event.AddToyMessageEvent;
import com.tongyuan.android.zhiquleyuan.event.MessageEventToy;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by android on 2016/12/3.
 */

public class ToySelectorFragment extends BaseFragment {

    @BindView(R.id.iv_fragment_toy_headimage)
    ImageView mHeadImageView;
    @BindView(R.id.id_viewpager)
    ViewPager mViewPageToy;
//    @BindView(R.id.iv_add)
//    ImageView mIv_add;
//    @BindView(R.id.iv_delete)
//    ImageView mIv_delete;
//    private ImageView mImageView;


    private static final String TAG = "222222";
    public static String mToyId;

//    ArrayList<String> toyImg = new ArrayList<String>();
//    ArrayList<String> babyImg = new ArrayList<String>();
//    ArrayList<String> toyId = new ArrayList<String>();
//    ArrayList<String> toyCode = new ArrayList<String>();
//    ArrayList<String> ownerId = new ArrayList<String>();
    private List<QueryToyResultBean.BODYBean.LSTBean> toyList = new ArrayList<>();
    private SingleToyInfoRESBean body;
    private ToySelectPagerAdapter mPagerAdapter;
    private int mCurrentPosition;
//    private ViewGroup mContainer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        View toyRoot = inflater.inflate(R.layout.fragment_toy, container, false);
        ButterKnife.bind(this, toyRoot);
        //mHeadImageView.setFocusable(false);
        return toyRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        mViewPageToy.setPageMargin(20);
        mViewPageToy.setOffscreenPageLimit(10);

        /*mPagerAdapter = new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                mCurrentPosition = position;
                mContainer = container;
                mImageView = new ImageView(getActivity());
                mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //Log.i("111111", "instaniateItem " + toyImg.get(position));
                Glide.with(getActivity()).load(toyImg.get(position)).asBitmap().into(mImageView);
                container.addView(mImageView);


                //点击玩具图片,处理的逻辑
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        *//*
                        * 这里点击不同的图片都要进入toydetailsfragment里面,但是传递的数据是不一样的,intent bundle
                        * 需要传递的参数:1,玩具的图片; 2,玩具的型号; 3,玩具激活的时间; 4,玩具的状态
                        * 根据position来判断进入的是哪个玩具
                        * *//*
                        ToastUtil.showToast(getContext(), "shanchu 上");
                        //TODO 从这里开始要处理数据!
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        String time = simpleDateFormat.format(new Date());

                        mToyId = toyId.get(position);
                        Log.i(TAG, "onClick:toyid " + mToyId);
                        SPUtils.putString(getActivity(), "toyidtopush", mToyId);
                        String toycode = toyCode.get(position);
                        Log.i(TAG, "onClick:toycode " + toycode);

                        QuerySingleToyInfo(mToyId, toycode, time, position);

                        Toast.makeText(getActivity(), "当前position" + position, Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onClick: toyid1" + mToyId);

                    }
                });
                return mImageView;
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

        };*/
        mPagerAdapter = new ToySelectPagerAdapter(getActivity(), toyList);
        mPagerAdapter.setOnItemClick(new ToySelectPagerAdapter.OnItemClick() {
            @Override
            public void itemClick(int position) {
                String mToyId = toyList.get(position).getID();
                //Log.i(TAG, "onClick:toyid " + mToyId);
                SPUtils.putString(getActivity(), "toyidtopush", mToyId);
                String toycode = toyList.get(position).getCODE();
                //Log.i(TAG, "onClick:toycode " + toycode);
                QuerySingleToyInfo(mToyId, toycode, position);
            }
        });
        mViewPageToy.setAdapter(mPagerAdapter);
        mViewPageToy.setPageTransformer(true, new ScaleInTransformer());
        mViewPageToy.setCurrentItem(1);
        displayBabyHead(1);
        mViewPageToy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                //获得宝宝的头像 , 滑到当前的position展示当前的玩具的宝宝,如果当前的玩具没有绑定宝宝,那么就让他显示默认的baby头像
                //宝宝的头像从哪来?怎么确定宝宝的头像和玩具的关系
                displayBabyHead(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 显示baby的头像
     * @param position pager的位置
     */
    private void displayBabyHead(int position) {
        String s = toyList.get(position).getBABYIMG();//获得宝宝的头像
        if (s == null) {
            Glide.with(getContext()).load(R.drawable.ic_launcher).asBitmap().into(new BitmapImageViewTarget(mHeadImageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    roundedBitmapDrawable.setCircular(true);
                    mHeadImageView.setImageDrawable(roundedBitmapDrawable);
                }
            });
            return;
        }
        Glide.with(getContext()).load(s).asBitmap().into(new BitmapImageViewTarget(mHeadImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                mHeadImageView.setImageDrawable(roundedBitmapDrawable);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        mPagerAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_add, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                showFragment(ToyAddFragment.class.getSimpleName());
                break;
            case R.id.iv_delete:
                removeToy();
                break;
        }
    }

    private void removeToy() {
        if(toyList == null || toyList.size()==0)
            return;
        final String deleteId = toyList.get(mCurrentPosition).getID();
        final String deleteCode = toyList.get(mCurrentPosition).getCODE();
        String toyOwnerId = toyList.get(mCurrentPosition).getOWNERID();

        if (toyOwnerId.equals(SPUtils.getString(getContext(), "ID", ""))) {
            //弹窗:提示用户当前是管理员,是否删除玩具,并解散玩具群 3.4.47
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("您是当前玩具的管理员,确认删除玩具并解散群?");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //mContainer.removeViewAt(mCurrentPosition);
//                                    mViewPageToy.removeViewAt(position);
                    deleteToyFromPowerUser(mCurrentPosition, deleteId, deleteCode);
                }
            });
            builder.show();

        } else {
            //弹窗:提示用户,是否删除玩具,并退出玩具群 3.4.12
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("确认退出玩具群?");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //mContainer.removeViewAt(mCurrentPosition);
//                                    mViewPageToy.removeViewAt(position);
                    deleteToyFromNormalUser(mCurrentPosition, deleteId, deleteCode);
                }
            });
            builder.show();

        }
        ToastUtil.showToast(getContext(), "shanchu 下");
    }

    private void deleteToyFromPowerUser(final int currentPosition, String deleteId, String deleteCode) {
        DeleteToyFromPowerUserReqBean bodyBean = new DeleteToyFromPowerUserReqBean(deleteId, deleteCode);

        Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> deleteToyFromPowerUserResBeanCall = RequestManager.getInstance().deleteSeletedToy(getActivity(), bodyBean);
        deleteToyFromPowerUserResBeanCall.enqueue(new Callback<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> call, Response<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> response) {
                if("0".equals(response.body().CODE)) {
                    toyList.remove(currentPosition);
                    mPagerAdapter.notifyDataSetChanged();
                    //Log.i(TAG, "onResponse: respnose12" + response.body().toString());
                    ToastUtil.showToast(getContext(), "确实已经删除了222");
                } else {
                    ToastUtil.showToast(getContext(), response.body().MSG);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> call, Throwable t) {
                ToastUtil.showToast(getContext(), R.string.network_error);
            }
        });
    }

    private void deleteToyFromNormalUser(final int position, String deleteId, String deleteCode) {
        DeleteToyFromNormalUserReqBean.BODYBean bodyBean = new DeleteToyFromNormalUserReqBean.BODYBean(deleteId, deleteCode);
        Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> result = RequestManager.getInstance().deleteSeletedNormalToy(getActivity(), bodyBean);
        result.enqueue(new Callback<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> call, Response<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> response) {

                if("0".equals(response.body().CODE)) {
                    toyList.remove(position);
                    mPagerAdapter.notifyDataSetChanged();
                    //Log.i(TAG, "onResponse: respnose12" + response.body().toString());
                    ToastUtil.showToast(getContext(), "确实已经删除了111");
                } else {
                    ToastUtil.showToast(getContext(), response.body().MSG);
                }

            }

            @Override
            public void onFailure(Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> call, Throwable t) {
                ToastUtil.showToast(getContext(), R.string.network_error);
            }
        });
        //ToastUtil.showToast(getContext(), "确实已经删除了1111");
    }

    //查询单个玩具的信息
    private void QuerySingleToyInfo(String toyid, String toycode, final int position) {
        SingleToyInfoREQBean.BODYBean bodyBean = new SingleToyInfoREQBean.BODYBean(toyid, toycode);
        Call<SuperModel<SingleToyInfoRESBean.BODYBean>> singleToyInfoResult = RequestManager.getInstance().getToyDetail(getActivity(), bodyBean);
        singleToyInfoResult.enqueue(new Callback<SuperModel<SingleToyInfoRESBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<SingleToyInfoRESBean.BODYBean>> call, Response<SuperModel<SingleToyInfoRESBean.BODYBean>> response) {
                if (response.body().CODE.equals("0")) {
                    /*String toycode = response.body().getBODY().getCODE();
                    Log.i(TAG, "toycode:235 " + toycode);
                    String acttime = response.body().getBODY().getACTTIME();
                    String toyimg = response.body().getBODY().getIMG();
                    String ownername = response.body().getBODY().getOWNERNAME();

                    mToycode = toycode;
                    Log.i(TAG, "mToycode241: " + mToycode);
                    mActtime = acttime;
                    mToyimg = toyimg;
                    mOwnername = ownername;
                    body = response.body();*/

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.getToyDetailsFragment().setData(response.body().BODY, toyList.get(position).getBABYIMG());
                    showFragment(ToyDetailsFragment.class.getSimpleName());
                    //Log.i(TAG, "onResponse: +toyselelctorfragment+body" + body.getBODY().toString());

                } else {
                    ToastUtil.showToast(getActivity(), response.body().MSG);
                    //Log.d(TAG, "onResponse: 为空,请检查网络");
                }
            }

            @Override
            public void onFailure(Call<SuperModel<SingleToyInfoRESBean.BODYBean>> call, Throwable t) {
                //Log.i(TAG, "onFailure: " + t.toString());
                ToastUtil.showToast(getActivity(), "Response为空,请检查网络");
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

    //接收并处理从Mainactivity传过来的数据 (所有的玩具信息
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onToyMessage(MessageEventToy messageEventToy) {
        /*babyImg.clear();
        toyId.clear();
        toyImg.clear();
        toyId.clear();
        toyCode.clear();
        ownerId.clear();

        for (int i = 0; i < messageEventToy.mQueryToyListResults.size(); i++) {
            String imgBaby = messageEventToy.mQueryToyListResults.get(i).getBABYIMG();
            String imgToy = messageEventToy.mQueryToyListResults.get(i).getIMG();
            String ownername = messageEventToy.mQueryToyListResults.get(i).getOWNERNAME();
            String toyOwnerId = messageEventToy.mQueryToyListResults.get(i).getOWNERID();
            String code = messageEventToy.mQueryToyListResults.get(i).getCODE();
            String idToy = messageEventToy.mQueryToyListResults.get(i).getID();
            String timeAct = messageEventToy.mQueryToyListResults.get(i).getACTTIME();

            //TODO 需不需要在这个界面就把玩具的状态信息拿到?
            babyImg.add(imgBaby);
            toyImg.add(imgToy);
            toyId.add(idToy);
            toyCode.add(code);
            ownerId.add(toyOwnerId);


        }

        Log.i(TAG, "babyImg=====" + babyImg);
        Log.i(TAG, "toyImg size=====" + toyImg.size());
        Log.i(TAG, "onToyMessage: adapter1" + mPagerAdapter);*/
        toyList.clear();
        toyList.addAll(messageEventToy.mQueryToyListResults);
        if (mPagerAdapter != null)
            mPagerAdapter.notifyDataSetChanged();
        //Log.i(TAG, "onToyMessage: adapter" + mPagerAdapter);
    }

    //获取从ToyAddFragment传过来的response数据.
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void onGetToyAddMessage(AddToyMessageEvent addToyMessageEvent) {
        Response<AddToyResultBean> addToyResultBeanResponse = addToyMessageEvent.mAddToyResultBeanResponse;

        //拿到从ToyAddFragment传过来的response数据
        //List<Response<AddToyResultBean>> responseList = new ArrayList<>();

        String img = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getIMG();
        String id = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getID();
        String code = addToyMessageEvent.mAddToyResultBeanResponse.body().getCODE();
        //Log.i("111111", "onGetToyAddMessage: " + toyId.size());
        //这里应该做个判断,判断当前的list里面有没有这个玩具,有就不添加
        boolean isAdd = true;
        for (int i = 0; i < toyList.size(); i++) {
            if (toyList.get(i).getID().equals(id)) {
                isAdd = false;
            }
        }

        /*if (isAdd) {
            toyImg.add(img);
            toyId.add(id);
            toyCode.add(code);
        }*/
        //Log.i("111111", Thread.currentThread().getName() + " mPagerAdapre" + mPagerAdapter);
        //Log.i("111111", " toyId.size2" + toyId.size());
        if (mPagerAdapter != null)
            mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(getActivity());
    }


}
