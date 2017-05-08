package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tongyuan.android.zhiquleyuan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/12/27.
 */
public class DiscoveryRecyclerAdapter extends BaseQuickAdapter {
    //    private final static int[] pic = new int[]{R.drawable.find1_3x, R.drawable.find2_3x, R.drawable.find3_3x, R.drawable.find4_3x,
//            R.drawable.find5_3x, R.drawable.find6_3x, R.drawable.find7_3x, R.drawable.find8_3x, R.drawable.find9_3x,};
//    private final static String[] text = new String[]{"儿童歌谣", "国学经典", "生活百科", "英语启蒙",
//            "中国民乐", "西方古典", "摇篮曲", "胎教音乐", "人格培养"};
    public List list = new ArrayList<>();

    public DiscoveryRecyclerAdapter(Context context, int layoutResId, List list) {
        super(layoutResId, list);
        this.list = list;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {

        baseViewHolder.setImageResource(R.id.iv_grid, R.mipmap.test)
                .setText(R.id.tv_grid, "1");
    }

}
