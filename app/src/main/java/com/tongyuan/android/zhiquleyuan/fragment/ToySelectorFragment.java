package com.tongyuan.android.zhiquleyuan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.MainActivity;
import com.tongyuan.android.zhiquleyuan.adapter.ToySelectPagerAdapter;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromNormalUserReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromNormalUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromPowerUserReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteToyFromPowerUserResBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyResultBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoREQBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.event.AddToyMessageEvent;
import com.tongyuan.android.zhiquleyuan.event.MessageEventToy;
import com.tongyuan.android.zhiquleyuan.request.RequestManager;
import com.tongyuan.android.zhiquleyuan.request.base.SuperModel;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.view.GlideCircleTransform;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by android on 2016/12/3.
 */

public class ToySelectorFragment extends BaseFragment {

    @BindView(R.id.iv_fragment_toy_headimage)
    ImageView mHeadImageView;
    @BindView(R.id.id_viewpager)
    ViewPager mViewPageToy;
    private static final String TAG = ToySelectorFragment.class.getSimpleName();
    public static String mToyId;
    //    ArrayList<String> toyImg = new ArrayList<String>();
//    ArrayList<String> babyImg = new ArrayList<String>();
//    ArrayList<String> toyId = new ArrayList<String>();
//    ArrayList<String> toyCode = new ArrayList<String>();
//    ArrayList<String> ownerId = new ArrayList<String>();
    private List<QueryToyResultBean.BODYBean.LSTBean> toyList = new ArrayList<>();
    //private SingleToyInfoRESBean body;
    private ToySelectPagerAdapter mPagerAdapter;
    private int mCurrentPosition;
//    private ViewGroup mContainer;

    public void setBabyImage(String babyImage) {
        if (toyList == null || toyList.size() - 1 < mCurrentPosition)
            return;
        toyList.get(mCurrentPosition).setBABYIMG(babyImage);
        displayBabyHead(mCurrentPosition);
    }

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int margin = (int) getResources().getDimension(R.dimen.dp_20);
        mViewPageToy.setPageMargin(margin);
        mViewPageToy.setOffscreenPageLimit(10);

        mViewPageToy.setPageTransformer(true, new ScaleInTransformer());
        mViewPageToy.setCurrentItem(0);
        displayBabyHead(mCurrentPosition);
        refreshPagerAdapter();
        mViewPageToy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                //获得宝宝的头像 , 滑到当前的position展示当前的玩具的宝宝,如果当前的玩具没有绑定宝宝,那么就让他显示默认的baby头像
                //宝宝的头像从哪来?怎么确定宝宝的头像和玩具的关系
                displayBabyHead(mCurrentPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 显示baby的头像
     *
     * @param position pager的位置
     */
    private void displayBabyHead(int position) {
        if (toyList == null || toyList.size() == 0) {
//            mHeadImageView.setVisibility(View.GONE);
            showFragment(ToyAddFragment.class.getSimpleName());
            return;
        }

        if (toyList != null && toyList.size() == 0) {
            showFragment(ToyAddFragment.class.getSimpleName());
        }

        if (position < 0) {
//            showFragment(ToyAddFragment.class.getSimpleName());
            return;
        }
        //        LogUtil.i(TAG, "displayBabyHead: toylist"+toyList.size()+"&&"+toyList.get(position).toString());
        String s = toyList.get(position).getBABYIMG();//获得宝宝的头像  -1
        LogUtil.i(TAG, "displayBabyHead:s=" + s);

        if (s.equals("")) {
            LogUtil.i(TAG, "displayBabyHead 1 : s为空");
            Glide.with(mContext).load(R.drawable.player_cover_default).asBitmap().transform(new GlideCircleTransform(mContext)).into(mHeadImageView);

        } else {
            LogUtil.i(TAG, "displayBabyHead: 不为空 " + s);
            Glide.with(mContext).load(s).asBitmap().transform(new GlideCircleTransform(mContext)).into(mHeadImageView);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        displayBabyHead(mCurrentPosition);
        refreshPagerAdapter();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            refreshPagerAdapter();
        }
    }

    @OnClick({R.id.iv_add, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
//                ToastUtil.showToast(mContext,"+");
                showFragment(ToyAddFragment.class.getSimpleName());
                break;
            case R.id.iv_delete:
                removeToy();
                break;
        }
    }

    private void removeToy() {
        if (toyList == null || toyList.size() == 0) {
            return;
        }

        final String deleteId = toyList.get(mCurrentPosition).getID();
        final String deleteCode = toyList.get(mCurrentPosition).getCODE();
        String toyOwnerId = toyList.get(mCurrentPosition).getOWNERID();
        if (toyOwnerId.equals(SPUtils.getString(getContext(), "userID", ""))) {
            //是玩具的所有者,即管理员
            //弹窗:提示用户当前是管理员,是否删除玩具,并解散玩具群 3.4.47
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("您是当前玩具的管理员,确认删除玩具并解散群?");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //mContainer.removeViewAt(mCurrentPosition);
                    //mViewPageToy.removeViewAt(position);
                    deleteToyFromPowerUser(mCurrentPosition, deleteId, deleteCode);
                }
            });
            builder.show();
        } else {
            //非管理员
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
                    //mViewPageToy.removeViewAt(position);
                    deleteToyFromNormalUser(mCurrentPosition, deleteId, deleteCode);
                }
            });
            builder.show();

        }
        //ToastUtil.showToast(getContext(), "shanchu 下");
    }

    private void deleteToyFromPowerUser(final int currentPosition, String deleteId, String deleteCode) {
        DeleteToyFromPowerUserReqBean bodyBean = new DeleteToyFromPowerUserReqBean(deleteId, deleteCode);

        Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> deleteToyFromPowerUserResBeanCall = RequestManager.getInstance().deleteSeletedToy
                (getActivity(), bodyBean);
        deleteToyFromPowerUserResBeanCall.enqueue(new Callback<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> call, Response<SuperModel<DeleteToyFromPowerUserResBean
                    .BODYBean>> response) {
                if ("0".equals(response.body().CODE)) {
                    mCurrentPosition--;
                    toyList.remove(currentPosition);
                    refreshPagerAdapter();
                    //LogUtil.i(TAG, "onResponse: respnose12" + response.body().toString());
                    //ToastUtil.showToast(getContext(), "确实已经删除了222");
                } else if (response.body().CODE.equals("-10006")) {
                    SPUtils.putString(getContext(), "token", "");
                    ToastUtil.showToast(getContext(), response.body().MSG);
                } else {
                    ToastUtil.showToast(getContext(), response.body().MSG);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<DeleteToyFromPowerUserResBean.BODYBean>> call, Throwable t) {
                LogUtil.i(TAG, t.toString());
                ToastUtil.showToast(getContext(), R.string.network_error);
            }
        });
    }

    private void deleteToyFromNormalUser(final int position, String deleteId, String deleteCode) {
        DeleteToyFromNormalUserReqBean.BODYBean bodyBean = new DeleteToyFromNormalUserReqBean.BODYBean(deleteId, deleteCode);
        Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> result = RequestManager.getInstance().deleteSeletedNormalToy(getActivity(),
                bodyBean);
        result.enqueue(new Callback<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>>() {
            @Override
            public void onResponse(Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> call,
                                   Response<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> response) {

                if ("0".equals(response.body().CODE)) {
                    mCurrentPosition--;
                    toyList.remove(position);
                    refreshPagerAdapter();
                    if (toyList.size() == 0) {
                        showFragment(ToyAddFragment.class.getSimpleName());
                        mPagerAdapter.notifyDataSetChanged();
                    }
                    //LogUtil.i(TAG, "onResponse: respnose12" + response.body().toString());
                    //ToastUtil.showToast(getContext(), "确实已经删除了111");
                } else {
                    ToastUtil.showToast(getContext(), response.body().MSG);
                }

            }

            @Override
            public void onFailure(Call<SuperModel<DeleteToyFromNormalUserResBean.BODYBean>> call, Throwable t) {
                ToastUtil.showToast(getContext(), R.string.network_error);
            }
        });
    }

    //查询单个玩具的信息
    private void QuerySingleToyInfo(String toyid, final String toycode, final int position) {
        mCurrentPosition = position;
        SingleToyInfoREQBean.BODYBean bodyBean = new SingleToyInfoREQBean.BODYBean(toyid, toycode);
        Call<SuperModel<SingleToyInfoRESBean.BODYBean>> singleToyInfoResult = RequestManager.getInstance().getToyDetail(getContext(), bodyBean);
        singleToyInfoResult.enqueue(new Callback<SuperModel<SingleToyInfoRESBean.BODYBean>>() {


            @Override
            public void onResponse(Call<SuperModel<SingleToyInfoRESBean.BODYBean>> call, Response<SuperModel<SingleToyInfoRESBean.BODYBean>>
                    response) {
//                LogUtil.i(TAG,"空空空"+response.body().toString());
                if (response!=null&&response.body().CODE.equals("0")) {
                    LogUtil.i(TAG, "onResponse: +toyinfo" + response.body().BODY.toString());
                    String toyCode = response.body().BODY.getCODE();
                    MainActivity mainActivity = (MainActivity) getActivity();

                    mainActivity.getToyDetailsFragment().setData(response.body().BODY, toyList.get(mCurrentPosition).getBABYIMG());
                    ArrayList<String> codeList=new ArrayList();
                    ArrayList<String> idList=new ArrayList();

                    for (int i = 0; i < toyList.size(); i++) {
                        String code = toyList.get(i).getCODE();
                        String id = toyList.get(i).getID();
                        idList.add(id);
                        codeList.add(code);
                    }

                    mainActivity.getToyDetailsFragment().setToyCodeListAndIdList(codeList,idList);
                    showFragment(ToyDetailsFragment.class.getSimpleName());
                } else {
                    ToastUtil.showToast(getActivity(), response.body().MSG);
                }
            }

            @Override
            public void onFailure(Call<SuperModel<SingleToyInfoRESBean.BODYBean>> call, Throwable t) {
                ToastUtil.showToast(getActivity(), R.string.network_error);
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
        toyList.clear();
        toyList.addAll(messageEventToy.mQueryToyListResults);
        //LogUtil.i("gengen", "onToyMessage "+toyList.size());
        if (mPagerAdapter != null) {
            refreshPagerAdapter();
            //mPagerAdapter.notifyDataSetChanged();
        }
        //LogUtil.i(TAG, "onToyMessage: adapter" + mPagerAdapter);
    }

    //获取从ToyAddFragment传过来的response数据.
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onGetToyAddMessage(AddToyMessageEvent addToyMessageEvent) {
        String img = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getIMG();
        String id = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getID();
        String code = addToyMessageEvent.mAddToyResultBeanResponse.body().getCODE();
        String ownerId = addToyMessageEvent.mAddToyResultBeanResponse.body().getBODY().getOWNERID();

        //这里应该做个判断,判断当前的list里面有没有这个玩具,有就不添加
        boolean isAdd = true;
        for (int i = 0; i < toyList.size(); i++) {
            if (toyList.get(i).getID().equals(id)) {
                isAdd = false;
            }
        }

        if (isAdd) {
            QueryToyResultBean.BODYBean.LSTBean bean = new QueryToyResultBean.BODYBean.LSTBean();
            bean.setIMG(img);
            bean.setID(id);
            bean.setCODE(code);
            bean.setOWNERID(ownerId);
            bean.setBABYIMG("");
            toyList.add(bean);
            //LogUtil.i("gengen", "onGetToyAddMessage notifyDataSetChangeed...");
            if (mPagerAdapter != null) {
                refreshPagerAdapter();
                //mPagerAdapter.notifyDataSetChanged();

            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(getActivity());
    }

    private void refreshPagerAdapter() {
        if (toyList.size() == 0) {
            showFragment(ToyAddFragment.class.getSimpleName());
        } else {
            mPagerAdapter = new ToySelectPagerAdapter(getActivity(), toyList);
            mPagerAdapter.setOnItemClick(new ToySelectPagerAdapter.OnItemClick() {
                @Override
                public void itemClick(int position) {
                    mToyId = toyList.get(position).getID();
                    mViewPageToy.setCurrentItem(position);

                    LogUtil.i(TAG, "itemClick:toyselectorfragment mToyId" + mToyId);
                    //LogUtil.i(TAG, "onClick:toyid " + mToyId);
                    SPUtils.putString(getActivity(), "toyidtopush", mToyId);
                    String toycode = toyList.get(position).getCODE();
                    //LogUtil.i(TAG, "onClick:toycode " + toycode);
                    QuerySingleToyInfo(mToyId, toycode, position);
                }
            });

            mViewPageToy.setAdapter(mPagerAdapter);
            mViewPageToy.setCurrentItem(mCurrentPosition);
        }

    }


}
