package com.tongyuan.android.zhiquleyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.FancyCoverAdapter;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;

/**
 * Created by android on 2016/12/3.
 */

public class ToySelectorFragment extends Fragment{
    private at.technikum.mti.fancycoverflow.FancyCoverFlow fancyCoverFlow;
    private View toyRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        toyRoot = inflater.inflate(R.layout.fragment_toy, null);
        return toyRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fancyCoverFlow = (FancyCoverFlow) toyRoot.findViewById(R.id.fancyCoverFlow);

        this.fancyCoverFlow.setAdapter(new FancyCoverAdapter());
        this.fancyCoverFlow.setUnselectedAlpha(1.0f);
        this.fancyCoverFlow.setUnselectedSaturation(0.0f);
        this.fancyCoverFlow.setUnselectedScale(0.5f);
        this.fancyCoverFlow.setSpacing(0);
        this.fancyCoverFlow.setMaxRotation(0);
        this.fancyCoverFlow.setScaleDownGravity(0.2f);
        this.fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);

    }
}
