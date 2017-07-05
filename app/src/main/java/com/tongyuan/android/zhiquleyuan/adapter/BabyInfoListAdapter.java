package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.BabyInfoListActivity;
import com.tongyuan.android.zhiquleyuan.activity.SetInitVolumeActivity;
import com.tongyuan.android.zhiquleyuan.bean.DeleteBabyInfoReQBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteBabyInfoReSBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryBabyListResult;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2017/3/5.
 */
public class BabyInfoListAdapter extends BaseSwipeAdapter {

    private List<QueryBabyListResult.BODYBean.LSTBean> mLSTBeen;
    private Context mContext;
    private ImageView mIv_item_babyinfolist;
    private TextView mTv_title_item_babyinfolist;
    private TextView mTv_item_babyinfolist_invite;
    private TextView mTv_item_babyinfolist_sexy;
    private TextView mTv_item_babyinfolist_birthday;
    private TextView mTv_item_babyinfolist_primarysound;
    private Button mSwipe_babyinfolist_delete;
    private Response<QueryBabyListResult> response;
    private String time;
    private String phone;
    private String token;


    public BabyInfoListAdapter(Context context, List<QueryBabyListResult.BODYBean.LSTBean> lst) {
        mContext = context;
        mLSTBeen = lst;

    }

    public BabyInfoListAdapter(BabyInfoListActivity context, Response<QueryBabyListResult> response, String time, String phone, String token, List
            <QueryBabyListResult.BODYBean.LSTBean> lst) {
        mContext = context;
        this.response = response;
        this.time = time;
        this.phone = phone;
        this.token = token;
        mLSTBeen = lst;


    }

    @Override
    public int getSwipeLayoutResourceId(int position) {

        return R.id.Swipe_babyinfolist;

    }

    @Override
    public View generateView(int position, ViewGroup parent) {

        return LayoutInflater.from(mContext).inflate(R.layout.item_babyinfolist, parent, false);

    }

    @Override
    public void fillValues(int position, View convertView) {
        final int pos = position;
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.Swipe_babyinfolist);
        mIv_item_babyinfolist = (ImageView) convertView.findViewById(R.id.iv_item_babyinfolist);
        mTv_title_item_babyinfolist = (TextView) convertView.findViewById(R.id.tv_title_item_babyinfolist);
        mTv_item_babyinfolist_invite = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_invite);
        mTv_item_babyinfolist_sexy = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_sexy);
        mTv_item_babyinfolist_birthday = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_birthday);
        mTv_item_babyinfolist_primarysound = (TextView) convertView.findViewById(R.id.tv_item_babyinfolist_primarysound);
        mSwipe_babyinfolist_delete = (Button) convertView.findViewById(R.id.swipe_babyinfolist_delete);
        mSwipe_babyinfolist_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先写在刷新列表之前
                deleteBabyInfo(pos);
                mLSTBeen.remove(pos);
                notifyDataSetChanged();
                swipeLayout.close();// 删除成功后需要关闭侧滑
            }
        });
        Glide.with(mContext).load(mLSTBeen.get(pos).getIMG()).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIv_item_babyinfolist) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                mIv_item_babyinfolist.setImageDrawable(roundedBitmapDrawable);
            }
        });
        mTv_title_item_babyinfolist.setText(mLSTBeen.get(pos).getNAME());
        mTv_item_babyinfolist_sexy.setText(mLSTBeen.get(pos).getSEX());
        mTv_item_babyinfolist_birthday.setText(mLSTBeen.get(pos).getBIRTHDAY());
        mTv_item_babyinfolist_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, "点击的是邀请");
            }
        });
        mTv_item_babyinfolist_primarysound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 拿错数据了,应该要toyid,给setinitvolume传过了已经,要改正 2017 6月9 0:10
                Intent intent = new Intent();
                intent.setClass(mContext, SetInitVolumeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("babaid", mLSTBeen.get(pos).getID());
                intent.putExtras(bundle);
                mContext.startActivity(intent);

                ToastUtil.showToast(mContext, "点击的是设置音量");
            }
        });
    }

    private void deleteBabyInfo(int pos) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllInterface allInterface = retrofit.create(AllInterface.class);

        DeleteBabyInfoReQBean.BODYBean bodyBean = new DeleteBabyInfoReQBean.BODYBean("BABY", mLSTBeen.get(pos).getID());

        DeleteBabyInfoReQBean babyInfoRequestBean = new DeleteBabyInfoReQBean("REQ", "DINFO", phone, time, bodyBean, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(babyInfoRequestBean);
        Call<DeleteBabyInfoReSBean> deleteBabyInfoReSBeanCall = allInterface.delteBabyInfoReult(s);

        deleteBabyInfoReSBeanCall.enqueue(new Callback<DeleteBabyInfoReSBean>() {
            @Override
            public void onResponse(Call<DeleteBabyInfoReSBean> call, Response<DeleteBabyInfoReSBean> response) {
                ToastUtil.showToast(mContext, "删除成功");
            }

            @Override
            public void onFailure(Call<DeleteBabyInfoReSBean> call, Throwable t) {
                ToastUtil.showToast(mContext, "网络异常");
            }
        });


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
}
