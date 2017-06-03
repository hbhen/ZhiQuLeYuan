package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by Android on 2017/5/15.
 */
public class BindBabyAdapter extends BaseAdapter {

    private Response<QueryBabyListResult> mResponse;
    private Context mContext;
    HashMap<Integer, Boolean> isSelected;
    private final LayoutInflater mInflater;


    public BindBabyAdapter(Context context, Response<QueryBabyListResult> response) {
        this.mResponse = response;
        this.mContext = context;
        isSelected = new HashMap<Integer, Boolean>();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mResponse.body().getBODY().getLST().size();
    }

    @Override
    public Object getItem(int position) {
        return mResponse.body().getBODY().getLST().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    BindBabyHolder mBindBabyHolder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bindbaby, null);
            mBindBabyHolder = new BindBabyHolder();
            mBindBabyHolder.mImageViewBabyImg = (ImageView) convertView.findViewById(R.id.iv_item_bindbaby_babyimg);
            mBindBabyHolder.mTextViewBabyName = (TextView) convertView.findViewById(R.id.tv_item_bindbaby_babyname);
            mBindBabyHolder.mImageViewSex = (ImageView) convertView.findViewById(R.id.iv_item_bindbaby_sex);
            mBindBabyHolder.mTextViewSex = (TextView) convertView.findViewById(R.id.tv_item_bindbaby_sex);
            mBindBabyHolder.mImageViewBirthday = (ImageView) convertView.findViewById(R.id.iv_item_bindbaby_birthday);
            mBindBabyHolder.mTextViewBirthday = (TextView) convertView.findViewById(R.id.tv_item_bindbaby_birthday);
            mBindBabyHolder.mCheckImageButton = (ImageButton) convertView.findViewById(R.id.iv_item_bindbaby_checked);

            convertView.setTag(mBindBabyHolder);

        } else {
            mBindBabyHolder = (BindBabyHolder) convertView.getTag();
        }
        String babyImgUrl = mResponse.body().getBODY().getLST().get(position).getIMG().toString();
        String babyName = mResponse.body().getBODY().getLST().get(position).getNAME();
        String sex = mResponse.body().getBODY().getLST().get(position).getSEX();
        String birthday = mResponse.body().getBODY().getLST().get(position).getBIRTHDAY();


        Glide.with(mContext).load(babyImgUrl).asBitmap().into(new BitmapImageViewTarget(mBindBabyHolder.mImageViewBabyImg) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                mBindBabyHolder.mImageViewBabyImg.setImageDrawable(roundedBitmapDrawable);
            }
        });
        mBindBabyHolder.mTextViewBabyName.setText(babyName);
        mBindBabyHolder.mTextViewSex.setText(sex);
        mBindBabyHolder.mTextViewBirthday.setText(birthday);
        if (sex.equals("m")) {
            mBindBabyHolder.mImageViewSex.setImageResource(R.drawable.signs_man);
        }
        mBindBabyHolder.mImageViewSex.setImageResource(R.drawable.signs_woman);

        mBindBabyHolder.mCheckImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBindBabyHolder.mCheckImageButton.setSelected(true);
                ToastUtil.showToast(mContext, "碰到了111");
            }
        });
        return convertView;
    }

    public static void setIsSeleted() {

    }

    class BindBabyHolder {
        private ImageView mImageViewSex;
        private TextView mTextViewSex;
        private ImageView mImageViewBirthday;
        private TextView mTextViewBirthday;
        private ImageView mImageViewBabyImg;
        private TextView mTextViewBabyName;
        private ImageButton mCheckImageButton;
    }
}

