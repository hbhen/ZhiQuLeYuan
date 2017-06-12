package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.base.BaseFragment;

/**
 * Created by Android on 2017/6/1.
 */
public class CallWaitingConnectFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.callwaitingfragment, null);
        View viewById = getActivity().findViewById(R.id.ll_bottom);//底部导航栏
        View viewById1 = getActivity().findViewById(R.id.rb_toy);//底部玩具按钮
        viewById.setVisibility(View.GONE);
        viewById1.setVisibility(View.GONE);

        return view;
    }
}
