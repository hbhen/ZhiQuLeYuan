package com.tongyuan.android.zhiquleyuan.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tongyuan.android.zhiquleyuan.R;

/**
 *
 * Created by zgg on 2017/6/27.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        switch (view.getId()) {
            case R.id.discovery_recommend:
                outRect.left = space;
                outRect.bottom = space;
                if ((parent.getChildAdapterPosition(view)) % 3 == 0) {
                    outRect.right = space;
                }
                break;
            case R.id.discovery_list:
                outRect.left = space;
                outRect.top = space;
                break;
        }
    }

}
