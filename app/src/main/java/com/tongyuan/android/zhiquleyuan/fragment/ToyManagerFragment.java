package com.tongyuan.android.zhiquleyuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.NoDisturbActivity;
import com.tongyuan.android.zhiquleyuan.activity.SetupWlanActivity;

/**
 * Created by android on 2017/3/12.
 */

public class ToyManagerFragment extends Fragment implements View.OnClickListener {

    private Button mBt_fragment_managetoy_setupwlan;
    private Button mBt_fragment_managetoy_nodisturb;
    private TextView mTv_frament_managetoy_manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_manageToy = inflater.inflate(R.layout.fragment_managetoy, null);
//        fragment_manageToy.findViewById(R.id.)
        mBt_fragment_managetoy_setupwlan = (Button) fragment_manageToy.findViewById(R.id.bt_fragment_managetoy_setupwlan);
        mBt_fragment_managetoy_nodisturb = (Button) fragment_manageToy.findViewById(R.id.bt_fragment_managetoy_nodisturb);
        mTv_frament_managetoy_manager = (TextView) fragment_manageToy.findViewById(R.id.tv_frament_managetoy_manager);
        initListener();
        return fragment_manageToy;
    }

    private void initListener() {
        mBt_fragment_managetoy_nodisturb.setOnClickListener(this);
        mBt_fragment_managetoy_setupwlan.setOnClickListener(this);
        mTv_frament_managetoy_manager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fragment_managetoy_nodisturb:
                Intent intent = new Intent(getActivity(), NoDisturbActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_fragment_managetoy_setupwlan:
                Intent intent1 = new Intent(getActivity(), SetupWlanActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_frament_managetoy_manager:
                break;
        }
    }
}
