package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.AddMemberToGroup;
import com.tongyuan.android.zhiquleyuan.bean.DelMembFromGroupReQBean;
import com.tongyuan.android.zhiquleyuan.bean.DelMembFromGroupReSBean;
import com.tongyuan.android.zhiquleyuan.bean.QueryToyMemberReSBean;
import com.tongyuan.android.zhiquleyuan.bean.SingleToyInfoRESBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by DTC on 2017/4/13.
 */
public class ToyMemberAdapter extends BaseAdapter {

    private Context mContext;
    private List<QueryToyMemberReSBean.BODYBean.LSTBean> mLSTBeanlist;
    private String ownerUserId;
    private SingleToyInfoRESBean.BODYBean mResponse;
    private String toyId;
    private String code;
    private String token;
    private String phoneNum;
    private String time;


    private enum Mode {

        DEL, NORMAL, KING

    }

    private Mode mMode = Mode.NORMAL;//默认给的初始状态是 normal;


    public ToyMemberAdapter(FragmentActivity activity, List<QueryToyMemberReSBean.BODYBean.LSTBean> lst, SingleToyInfoRESBean.BODYBean response) {
        this.mContext = activity;
        this.mResponse = response;
        this.mLSTBeanlist = lst;
        Log.i("111111", "ToyAdapter size="+mLSTBeanlist.size());
    }

    @Override
    public int getCount() {
        return mLSTBeanlist.size() + 2;
    }

    @Override
    public Object getItem(int position) {
        if (position >= mLSTBeanlist.size()) {
            position = mLSTBeanlist.size() - 1;
        }
        return mLSTBeanlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        toyId = mResponse.getID();
        ownerUserId = mResponse.getOWNERID();
        code = mResponse.getCODE();
        token = SPUtils.getString(mContext, "TOKEN", "");
        phoneNum = SPUtils.getString(mContext, "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        time = simpleDateFormat.format(new Date());

        final MemberHolder memberHolder;

        if (convertView == null) {
            memberHolder = new MemberHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.manager_gridview_item, null);
            memberHolder.icon = (ImageView) convertView.findViewById(R.id.iv_manager_gridview_item);
            memberHolder.del = (ImageView) convertView.findViewById(R.id.iv_manager_gridview_item_remove);
            memberHolder.king = (ImageView) convertView.findViewById(R.id.iv_manager_gridview_item_managerking);
            convertView.setTag(memberHolder);
        } else {
            memberHolder = (MemberHolder) convertView.getTag();
        }

        memberHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, "点击的是:" + position);
            }
        });

        if(position <mLSTBeanlist.size()) {
            if (mLSTBeanlist.get(position).getID().equals(ownerUserId)) {
                memberHolder.king.setVisibility(View.VISIBLE);

            } else {
                memberHolder.king.setVisibility(View.INVISIBLE);
            }
        } else {
            memberHolder.king.setVisibility(View.INVISIBLE);
        }

        //加号部分 包括点击事件
        if (position == mLSTBeanlist.size()) {
            memberHolder.icon.setImageResource(R.drawable.toymanage_addmember_3x);
            memberHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d("will jump to add member");
                    ToastUtil.showToast(mContext, "点击了加号,开始执行加号的逻辑");
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("toyinfo", mResponse);
                    intent.putExtras(bundle);

                    intent.setClass(mContext, AddMemberToGroup.class);
                    mContext.startActivity(intent);
                }
            });

            memberHolder.del.setVisibility(View.GONE);

        } else if (position == mLSTBeanlist.size() + 1) {
            //减号部分 包括点击事件
            memberHolder.icon.setImageResource(R.drawable.toymanage_removemember_3x);

            memberHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMode == Mode.NORMAL) {
                        mMode = Mode.DEL;
                    } else {
                        mMode = Mode.NORMAL;
                    }
                    LogUtil.d("switch mode , mMode=>" + mMode);
                    refreshUI();
                }
            });
//            memberHolder.online.setVisibility(View.GONE);
            memberHolder.del.setVisibility(View.GONE);
        } else {
            //正常的头像展示部分
            //管理员头像的显示

            String userImg = mLSTBeanlist.get(position).getIMG();
            Glide.with(mContext).load(userImg).asBitmap().into(new BitmapImageViewTarget(memberHolder.icon) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    roundedBitmapDrawable.setCircular(true);
                    memberHolder.icon.setImageDrawable(roundedBitmapDrawable);
                }
            });

            if (mMode == Mode.NORMAL) {
                memberHolder.del.setVisibility(View.GONE);
            } else {
                memberHolder.del.setVisibility(View.VISIBLE);
                memberHolder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mLSTBeanlist.get(position).getID().equals(ownerUserId)) {
                            String id = mLSTBeanlist.get(position).getID();

                            mLSTBeanlist.remove(position);

                            //提交网络 管理员踢人出玩具群
                            deleteMember(toyId, id);
                            refreshUI();
                        } else {
                            ToastUtil.showToast(mContext, "你点击的是管理员,管理员不能删除");
                        }
                    }
                });
            }
        }

        return convertView;

    }

    private void deleteMember(String toyId, String id) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final AllInterface allInterface = retrofit.create(AllInterface.class);

        DelMembFromGroupReQBean.BODYBean bodyBean = new DelMembFromGroupReQBean.BODYBean(toyId, code, id);
        DelMembFromGroupReQBean delMembFromGroupReQBean = new DelMembFromGroupReQBean("REQ", "DU2T", phoneNum, time, bodyBean, "", token, "1");
        Gson gson = new Gson();
        String params = gson.toJson(delMembFromGroupReQBean);
        Call<DelMembFromGroupReSBean> delMembFromGroupReSBeanCall = allInterface.DEL_MEMB_FROM_GROUP_RES_BEAN_CALL(params);
        delMembFromGroupReSBeanCall.enqueue(new Callback<DelMembFromGroupReSBean>() {
            @Override
            public void onResponse(Call<DelMembFromGroupReSBean> call, Response<DelMembFromGroupReSBean> response) {
                Log.i("111", "onResponse++deletemember:" + response.body().toString());
                if (!response.body().toString().isEmpty() && response.body().getCODE().equals("0")) {
                    ToastUtil.showToast(mContext, "删除成功");

                }
            }

            @Override
            public void onFailure(Call<DelMembFromGroupReSBean> call, Throwable t) {

                ToastUtil.showToast(mContext, "shibai");

            }
        });

    }

    private void refreshUI() {
        notifyDataSetChanged();
    }

    public int getSize() {
        return mLSTBeanlist.size() - 1;
    }

    private class MemberHolder {
        ImageView icon;
        ImageView del;
        ImageView king;
    }
}
