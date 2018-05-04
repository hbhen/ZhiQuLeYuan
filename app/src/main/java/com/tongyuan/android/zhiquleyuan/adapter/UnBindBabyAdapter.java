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
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListFromToyIdRes;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.view.GlideCircleTransform;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Response;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * Created by Android on 2017/6/30.
 */
public class UnBindBabyAdapter extends BaseAdapter {
    private Response<QueryBabyListFromToyIdRes> mResponse;
    private Context mContext;

    public UnBindBabyAdapter(Context context, Response<QueryBabyListFromToyIdRes> response) {
        this.mResponse = response;
        this.mContext = context;
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

    UnBindBabyHolder mUnBindBabyHolder = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_unbindbaby, null);
            mUnBindBabyHolder = new UnBindBabyHolder();
            mUnBindBabyHolder.mImageViewBabyImg = (ImageView) convertView.findViewById(R.id.iv_item_unbindbaby_babyimg);
            mUnBindBabyHolder.mTextViewBabyName = (TextView) convertView.findViewById(R.id.tv_item_unbindbaby_babyname);
            mUnBindBabyHolder.mImageViewSex = (ImageView) convertView.findViewById(R.id.iv_item_unbindbaby_sex);
            mUnBindBabyHolder.mTextViewSex = (TextView) convertView.findViewById(R.id.tv_item_unbindbaby_sex);
            mUnBindBabyHolder.mImageViewBirthday = (ImageView) convertView.findViewById(R.id.iv_item_unbindbaby_birthday);
            mUnBindBabyHolder.mTextViewBirthday = (TextView) convertView.findViewById(R.id.tv_item_unbindbaby_birthday);
            mUnBindBabyHolder.mCheckImageButton = (CheckBox) convertView.findViewById(R.id.iv_item_unbindbaby_checked);

            convertView.setTag(mUnBindBabyHolder);

        } else {
            mUnBindBabyHolder = (UnBindBabyHolder) convertView.getTag();
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
            mUnBindBabyHolder.mTextViewBirthday.setText(formatTime);
            LogUtil.i(TAG, "babyinfolistadapter:" + formatTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Glide.with(mContext).load(babyImgUrl).asBitmap().centerCrop().transform(new GlideCircleTransform(mContext)).into
                (mUnBindBabyHolder.mImageViewBabyImg);
        mUnBindBabyHolder.mTextViewBabyName.setText(babyName);
        mUnBindBabyHolder.mTextViewSex.setText(sex);
        if (sex.equals("m")) {
            mUnBindBabyHolder.mImageViewSex.setImageResource(R.drawable.signs_man);
        } else {
            mUnBindBabyHolder.mImageViewSex.setImageResource(R.drawable.signs_woman);
        }

//        mUnBindBabyHolder.mCheckImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mUnBindBabyHolder.mCheckImageButton.setSelected(true);
//                ToastUtil.showToast(mContext, "碰到了111");
//            }
//        });
        return convertView;
    }


    class UnBindBabyHolder {
        private ImageView mImageViewSex;
        private TextView mTextViewSex;
        private ImageView mImageViewBirthday;
        private TextView mTextViewBirthday;
        private ImageView mImageViewBabyImg;
        private TextView mTextViewBabyName;
        private CheckBox mCheckImageButton;
    }
}
