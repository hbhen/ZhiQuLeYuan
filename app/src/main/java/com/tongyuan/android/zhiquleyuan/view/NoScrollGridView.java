package com.tongyuan.android.zhiquleyuan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by android on 2016/12/21.
 */

public class NoScrollGridView extends GridView {
    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
