package com.tongyuan.android.zhiquleyuan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.tongyuan.android.zhiquleyuan.activity.MainActivity;

/**
 * Created by Android on 2017/7/8.
 */

public class BaseFragment extends Fragment {
    public FragmentActivity mActivity;
    protected Context mContext;
    protected boolean isDestory = false;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                fragmentTransaction.hide(this);
            } else {
                fragmentTransaction.show(this);

            }
            fragmentTransaction.commit();

        }
        //拿到acitivity对象,获得context
        mActivity = getActivity();
        mContext = getActivity().getApplicationContext();


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


    @Override
    public void onDetach() {
        super.onDetach();
        isDestory = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }
}
