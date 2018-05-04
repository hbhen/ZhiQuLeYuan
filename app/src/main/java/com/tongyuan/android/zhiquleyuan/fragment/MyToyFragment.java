package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android on 2016/12/26.
 */

public class MyToyFragment extends Fragment {

    @BindView(R.id.tb_toy_details)
    Toolbar mTbToyDetails;

    @BindView(R.id.iv_toy_details_rightimg)
    RelativeLayout mIvToyDetailsRightimg;

    @BindView(R.id.iv_toy_details_call)
    ImageView mIvToyDetailsCall;

    @BindView(R.id.tv_toy_details_manager)
    TextView mTvToyDetailsManager;

    @BindView(R.id.rl_toy_details)
    RelativeLayout mRlToyDetails;

    @BindView(R.id.tv_toy_details_playing)
    TextView mTvToyDetailsPlaying;

    @BindView(R.id.ll_toy_details_control)
    LinearLayout mLlToyDetailsControl;

    @BindView(R.id.rl_toy_details_control)
    RelativeLayout mRlToyDetailsControl;

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        String toyimg = arguments.getString("toyimg");
        String toycode = arguments.getString("toycode");
        String acttime = arguments.getString("acttime");
        String ownername = arguments.getString("ownername");


        mRootView = inflater.inflate(R.layout.fragment_toy_details, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.iv_toy_details_call, R.id.tv_toy_details_manager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toy_details_call:
//                ToastUtil.showToast(getActivity(), "1");
                break;
            case R.id.tv_toy_details_manager:
//                ToastUtil.showToast(getActivity(), "2");
                break;
        }
    }
}
