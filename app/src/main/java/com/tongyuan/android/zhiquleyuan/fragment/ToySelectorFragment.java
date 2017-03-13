package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

/**
 * Created by android on 2016/12/3.
 */

public class ToySelectorFragment extends Fragment {
    private at.technikum.mti.fancycoverflow.FancyCoverFlow fancyCoverFlow;
    private View toyRoot;
    private ImageView mSelectImageview;
    private ImageView mHeadeImageview;
    private ViewPager mViewpagetToy;
    private PagerAdapter mPagerAdapter;
    private ImageView mImageview_fragment_toy_addtoy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        super.onViewCreated(view, savedInstanceState);
        /**
         * fancycoverflow原来的代码本来可以用的,但是不知道为什么现在用不了了
         * */
//        this.fancyCoverFlow = (FancyCoverFlow) toyRoot.findViewById(R.id.fancyCoverFlow);
//        this.fancyCoverFlow.setAdapter(new FancyCoverAdapter());
//        this.fancyCoverFlow.setUnselectedAlpha(1.0f);
//        this.fancyCoverFlow.setUnselectedSaturation(0.0f);
//        this.fancyCoverFlow.setUnselectedScale(0.5f);
//        this.fancyCoverFlow.setSpacing(0);
//        this.fancyCoverFlow.setMaxRotation(0);
//        this.fancyCoverFlow.setScaleDownGravity(0.2f);
//        this.fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        final ToyDetailsFragment toyDetailsFragment = new ToyDetailsFragment();
//        mSelectImageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.fl_fragmentcontainer,toyDetailsFragment);
//                transaction.commit();
//                ToastUtil.showToast(getActivity(),"点击的是玩具");
//            }
//
//        });
        mImageview_fragment_toy_addtoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_fragmentcontainer,new ToyAddFragment());
                transaction.commit();
            }
        });
        mHeadeImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.fl_fragmentcontainer,toyDetailsFragment);
//                transaction.commit();
                ToastUtil.showToast(getActivity(), "点击的是头像");
            }

        });
        mViewpagetToy.setPageMargin(20);
        mViewpagetToy.setOffscreenPageLimit(3);
        final int[] img = new int[]{R.mipmap.test, R.mipmap.test, R.mipmap.test};
        mViewpagetToy.setAdapter(mPagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(img[position]);
                container.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        * 这里点击不同的图片都要进入toydetailsfragment里面,但是传递的数据是不一样的,intent bundle
                        * 需要传递的参数:1,玩具的图片; 2,玩具的型号; 3,玩具激活的时间; 4,玩具的状态
                        *
                        * */
                        //TODO 从这里开始要处理数据!
                        switch (position) {
                            case 0:
                                ToastUtil.showToast(getActivity(), "点击的是" + position);
                                FragmentManager fragmentManager0 = getFragmentManager();
                                FragmentTransaction transaction0 = fragmentManager0.beginTransaction();
                                transaction0.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);
                                transaction0.commit();
                                break;
                            case 1:
                                ToastUtil.showToast(getContext(), "点击的是" + position);
                                FragmentManager fragmentManager1 = getFragmentManager();
                                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                                transaction1.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);
                                transaction1.commit();
                                break;
                            case 2:
                                ToastUtil.showToast(getContext(), "点击的是" + position);
                                FragmentManager fragmentManager2 = getFragmentManager();
                                FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
                                transaction2.replace(R.id.fl_fragmentcontainer, toyDetailsFragment);
                                transaction2.commit();
                                break;

                        }
                    }

                });
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return img.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==(View) object;
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
//        mSelectImageview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.fl_fragmentcontainer,toyDetailsFragment);
//                transaction.commit();
//
//                ToastUtil.showToast(getActivity(),"点击的是玩具");
//                return true;
//            }
//        });


    }


}
