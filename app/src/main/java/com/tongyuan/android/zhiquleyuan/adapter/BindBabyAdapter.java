package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.holder.BindBabyHolder;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.view.GlideCircleTransform;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;


/**
 * Created by Android on 2017/5/15.
 */
public class BindBabyAdapter extends BaseAdapter {
    private final String TAG = BindBabyAdapter.class.getSimpleName();
    private Response<QueryBabyListResult> mResponse;
    private Context mContext;
    HashMap<Integer, Boolean> isSelected;
    private final LayoutInflater mInflater;
    private Map<Integer, Boolean> map = new HashMap<>();


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
            mBindBabyHolder.mCheckImageButton = (CheckBox) convertView.findViewById(R.id.iv_item_bindbaby_checked);
            mBindBabyHolder.mCheckImageButton.setTag(position);
            convertView.setTag(mBindBabyHolder);

        } else {
            mBindBabyHolder = (BindBabyHolder) convertView.getTag();
        }

        String babyImgUrl = mResponse.body().getBODY().getLST().get(position).getIMG().toString();
        String babyName = mResponse.body().getBODY().getLST().get(position).getNAME();
        String sex = mResponse.body().getBODY().getLST().get(position).getSEX();
        String birthday = mResponse.body().getBODY().getLST().get(position).getBIRTHDAY();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSS");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parse = simpleDateFormat.parse(birthday);
            String formatTime = simpleDateFormat1.format(parse);
            mBindBabyHolder.mTextViewBirthday.setText(formatTime);
            LogUtil.i(TAG, "babyinfolistadapter:" + formatTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Glide.with(mContext).load(babyImgUrl).asBitmap().centerCrop().transform(new GlideCircleTransform(mContext)).into(mBindBabyHolder
                .mImageViewBabyImg);
        mBindBabyHolder.mTextViewBabyName.setText(babyName);
        mBindBabyHolder.mTextViewSex.setText(sex);
        if (sex.equals("m")) {
            mBindBabyHolder.mImageViewSex.setImageResource(R.drawable.signs_man);
        }
        mBindBabyHolder.mImageViewSex.setImageResource(R.drawable.signs_woman);
        return convertView;
    }


}

