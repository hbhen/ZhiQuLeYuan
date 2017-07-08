package com.tongyuan.android.zhiquleyuan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.tongyuan.android.zhiquleyuan.activity.MainActivity;

/**
 * Created by Android on 2017/7/8.
 */

public class BaseFragment extends Fragment {
    public FragmentActivity mActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拿到acitivity对象,获得context
        mActivity = getActivity();


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        //初始化布局view
//        View view = initview();
//        return view;
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        initData();
//    }
    //初始化数据,也是交给子类实现,,可以重写
//    protected void initData() {
//    }
    //初始化布局 交给子类实现
//    public abstract View initview();

    public void showFragment(String name) {

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showFragment(name);

        }

//        transaction.replace(R.id.fl_fragmentcontainer, f);
//        transaction.commit();

    }

    public void showFragment(BaseFragment fragment) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showFragment(fragment);

        }
    }
}
