package com.tongyuan.android.zhiquleyuan.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.tongyuan.android.zhiquleyuan.R;

/**
 *
 * Created by zgg on 2017/6/27.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean login = false;

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
                if(login) {
                    if ((parent.getChildAdapterPosition(view) + 1) % 3 == 0) {
                        outRect.right = space;
                    }
                } else {
                    if ((parent.getChildAdapterPosition(view)) % 3 == 0) {
                        outRect.right = space;
                    }
                }

                break;
            case R.id.discovery_list:
                outRect.left = space;
                outRect.top = space;
                break;
        }
    }

    public void isLogin(boolean isLogin) {
        this.login = isLogin;
    }

}
