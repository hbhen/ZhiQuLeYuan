package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.AddMemberToGroupActivity;
import com.tongyuan.android.zhiquleyuan.activity.SetInitVolumeActivity;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.holder.BabyInfoListHolder;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.view.GlideCircleTransform;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * Created by android on 2017/3/5.
 */
public class BabyInfoListAdapter extends BaseAdapter {

    private List<QueryBabyListResult.BODYBean.LSTBean> mLSTBeen;
    private Context mContext;

    private BabyInfoListHolder mBabyInfoListHolder;


    public BabyInfoListAdapter(Context context, List<QueryBabyListResult.BODYBean.LSTBean> lst) {
        mContext = context;
        mLSTBeen = lst;

    }


    @Override
    public int getCount() {
        return mLSTBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mLSTBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String birthday = mLSTBeen.get(position).getBIRTHDAY();
        String sex = mLSTBeen.get(position).getSEX();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmssSSS");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_babyinfolist, null);
            mBabyInfoListHolder = new BabyInfoListHolder();
            mBabyInfoListHolder.mIv_item_babyinfolist = (ImageView) convertView.findViewById(R.id.iv_item_babyinfolist);
            mBabyInfoListHolder.mTv_title_item_babyinfolist = (TextView) convertView.findViewById(R.id.tv_title_item_babyinfolist);
            mBabyInfoListHolder.mTv_item_babyinfolist_invite = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_invite);
            mBabyInfoListHolder.mTv_item_babyinfolist_sexy = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_sexy);
            mBabyInfoListHolder.mTv_item_babyinfolist_birthday = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_birthday);
            mBabyInfoListHolder.mTv_item_babyinfolist_primarysound = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_primarysound);
            mBabyInfoListHolder.mLl_babyinfolist_setvolume = (LinearLayout) convertView.findViewById(R.id.ll_babyinfolist_setvolume);
            mBabyInfoListHolder.mLl_babyinfolist_invite = (LinearLayout) convertView.findViewById(R.id.ll_babyinfolist_invite);
            mBabyInfoListHolder.iv_item_babyinfolist_sign_sex = (ImageView) convertView.findViewById(R.id.iv_item_babyinfolist_sign_sex);


            convertView.setTag(mBabyInfoListHolder);
        } else {
            mBabyInfoListHolder = (BabyInfoListHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mLSTBeen.get(position).getIMG()).asBitmap().placeholder(R.drawable.player_cover_default).transform(new
                GlideCircleTransform(mContext)
        ).into(mBabyInfoListHolder.mIv_item_babyinfolist);
        try {
            Date parse = simpleDateFormat.parse(birthday);
            String formatTime = simpleDateFormat1.format(parse);
            mBabyInfoListHolder.mTv_item_babyinfolist_birthday.setText(formatTime);
            LogUtil.i(TAG, "babyinfolistadapter:" + formatTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

//                .into(new BitmapImageViewTarget(mBabyInfoListHolder
//                .mIv_item_babyinfolist) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                roundedBitmapDrawable.setCircular(true);
//                mBabyInfoListHolder.mIv_item_babyinfolist.setImageDrawable(roundedBitmapDrawable);
//            }
//        });
        mBabyInfoListHolder.mTv_title_item_babyinfolist.setText(mLSTBeen.get(position).getNAME());
        if (sex.equals("m")) {
            mBabyInfoListHolder.iv_item_babyinfolist_sign_sex.setImageResource(R.drawable.signs_man);
        } else {
            mBabyInfoListHolder.iv_item_babyinfolist_sign_sex.setImageResource(R.drawable.signs_woman);
        }
        mBabyInfoListHolder.mTv_item_babyinfolist_sexy.setText(mLSTBeen.get(position).getSEX());
        mBabyInfoListHolder.mLl_babyinfolist_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddMemberToGroupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("flag", 2);
                mContext.startActivity(intent);
//                ToastUtil.showToast(mContext, "点击的是邀请");
            }
        });
        mBabyInfoListHolder.mLl_babyinfolist_setvolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetInitVolumeActivity.launch(mContext, mLSTBeen.get(position).getID());
//                ToastUtil.showToast(mContext, "点击的是设置音量");
            }
        });

        return convertView;
    }
}
