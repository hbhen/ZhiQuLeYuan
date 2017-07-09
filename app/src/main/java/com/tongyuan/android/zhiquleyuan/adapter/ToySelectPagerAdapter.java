package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyResultBean;

import java.util.List;

/**
 *
 * Created by zgg on 2017/7/5.
 */

public class ToySelectPagerAdapter extends AbstractViewPagerAdapter<QueryToyResultBean.BODYBean.LSTBean> implements View.OnClickListener{

    private Context mContext;

    public ToySelectPagerAdapter(Context context, List<QueryToyResultBean.BODYBean.LSTBean> data) {
        super(data);
        this.mContext = context;
    }

    @Override
    public View newView(int position) {
        View view = View.inflate(mContext, R.layout.layout_toy_image, null);
        ImageView mImageView = (ImageView) view.findViewById(R.id.image);
        view.setOnClickListener(this);
        view.setTag(position);
        Glide.with(mContext).load(mData.get(position).getIMG()).asBitmap().into(mImageView);
        return view;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if(clickItem!=null)
            clickItem.itemClick(position);
    }

    private OnItemClick clickItem;

    public void setOnItemClick(OnItemClick click) {
        this.clickItem = click;
    }

    public interface OnItemClick {
        void itemClick(int position);
    }

}
