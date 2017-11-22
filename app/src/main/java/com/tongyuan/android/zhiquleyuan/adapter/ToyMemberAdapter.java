package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
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
import com.tongyuan.android.zhiquleyuan.bean.TransferPermissionsReqBean;
import com.tongyuan.android.zhiquleyuan.bean.TransferPermissionsResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.holder.MemberHolder;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
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

    public static final int TOYMEMBER_ADAPTER_TO_ADDMEMEBER_GROUP = 1;
    private Context mContext;
    private List<QueryToyMemberReSBean.BODYBean.LSTBean> mLSTBeanlist;
    private String ownerUserId;
    private SingleToyInfoRESBean.BODYBean mResponse;
    private String toyId;
    private String code;
    private String token;
    private String phoneNum;
    private String time;
    private MemberHolder memberHolder;
    private int mKingPosition = -1;

    public enum SetManagerMode {
        SET, UNSET
    }

    public SetManagerMode mSetManagerMode = SetManagerMode.UNSET;

    private Constant.Mode mMode = Constant.Mode.NORMAL;//默认给的初始状态是 normal;

    public ToyMemberAdapter(FragmentActivity activity, List<QueryToyMemberReSBean.BODYBean.LSTBean> lst, SingleToyInfoRESBean.BODYBean response) {
        this.mContext = activity;
        this.mResponse = response;
        this.mLSTBeanlist = lst;
        toyId = mResponse.getID();
        ownerUserId = mResponse.getOWNERID();
        code = mResponse.getCODE();
        token = SPUtils.getString(mContext, "token", "");
        phoneNum = SPUtils.getString(mContext, "phoneNum", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        time = simpleDateFormat.format(new Date());
    }

    public void refreshData() {
        if (mLSTBeanlist != null) {
            for (int i = 0; i < mLSTBeanlist.size(); i++) {
                if (mLSTBeanlist.get(i).getID().equals(ownerUserId)) {
                    mKingPosition = i;
                    break;
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.i("adapter", "getCount=" + (mLSTBeanlist.size() + 2));
        if (mLSTBeanlist.size() == 0)
            return 0;
        return mLSTBeanlist.size() + 2;
    }

    @Override
    public Object getItem(int position) {
        Log.i("adapter", "getItem " + position);
        if (position >= mLSTBeanlist.size()) {
            return null;
        }
        return mLSTBeanlist.get(position);
    }


    @Override
    public long getItemId(int position) {
        Log.i("adapter", "getItemId=" + position);
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            memberHolder = new MemberHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.manager_gridview_item, null);
            memberHolder.itemView = convertView.findViewById(R.id.item_frame);
            memberHolder.icon = (ImageView) convertView.findViewById(R.id.iv_manager_gridview_item);
            memberHolder.itemView.setOnClickListener(clickIcon);
            memberHolder.itemView.setOnLongClickListener(mOnLongClickListener);
            memberHolder.del = (ImageView) convertView.findViewById(R.id.iv_manager_gridview_item_remove);
            memberHolder.king = (ImageView) convertView.findViewById(R.id.iv_manager_gridview_item_managerking);
            convertView.setTag(memberHolder);
        } else {
            memberHolder = (MemberHolder) convertView.getTag();
        }
        memberHolder.itemView.setTag(position);

        if (position < mLSTBeanlist.size()) {
            /*if (mLSTBeanlist.get(position).getID().equals(ownerUserId)) {
                mKingPosition = position;
                memberHolder.king.setImageResource(R.drawable.managerking_3x);
            } else {
                memberHolder.king.setVisibility(View.INVISIBLE);
            }*/
            if (mKingPosition == position) {
                memberHolder.king.setImageResource(R.drawable.managerking_3x);
            } else {
                memberHolder.king.setVisibility(View.INVISIBLE);
            }
        } else {
            memberHolder.king.setVisibility(View.INVISIBLE);
        }

        //加号部分 包括点击事件
        if (position == getCount() - 2) {
            memberHolder.icon.setImageResource(R.drawable.toymanage_addmember_3x);
            memberHolder.del.setVisibility(View.GONE);
            Log.i("adapter", position + "=+=" + (getCount() - 2));
        } else if (position == getCount() - 1) {
            //减号部分 包括点击事件
            memberHolder.icon.setImageResource(R.drawable.toymanage_removemember_3x);
            memberHolder.del.setVisibility(View.GONE);
            Log.i("adapter", position + "=-=" + (getCount() - 1));
        } else {

            //正常的头像展示部分
            //管理员头像的显示

            Log.i("adapter", position + "==else ");
            loadImage(memberHolder.icon, position);
            if (mMode == Constant.Mode.NORMAL) {
                memberHolder.del.setVisibility(View.GONE);
            } else {
                memberHolder.del.setVisibility(View.VISIBLE);
            }
        }

        return convertView;

    }

    private void loadImage(final ImageView imgView, final int position) {

        Log.i("adapter", "loadImage " + position);
        String userImg = mLSTBeanlist.get(position).getIMG();
//        Glide.with(mContext).load(userImg).asBitmap().placeholder(R.drawable.default_cover).into(imgView);

        Glide.with(mContext).load(userImg).asBitmap().centerCrop().placeholder(R.drawable.player_cover_default).into(new BitmapImageViewTarget
                (imgView) {

            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                imgView.setImageDrawable(roundedBitmapDrawable);
                Log.i("adapter", "setResource " + position);
            }
        });
    }

    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(final View v) {
            final int longPosition = (int) v.getTag();
//            ToastUtil.showToast(mContext, "长按了..." + longPosition);
            if (longPosition == getCount() - 2 || longPosition == getCount() - 1) {
//                ToastUtil.showToast(mContext, "再见");
                return true;

            }
            if (mSetManagerMode == SetManagerMode.UNSET) {
                return true;
            }
            if (mKingPosition == longPosition) {
                return true;
            }

//            StartFlick(imageView);
//            memberHolder.king.setVisibility(View.VISIBLE);

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("转移管理员权限");
            builder.setMessage("是否确认转移管理员权限");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String userId = mLSTBeanlist.get(longPosition).getID();
                    changeManager(longPosition, v, userId);
                    ToastUtil.showToast(mContext, "sure");
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    ToastUtil.showToast(mContext, "false");

                }
            });

            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
//                    ToastUtil.showToast(mContext, "取消");

                }
            });
            builder.show();
            return true;

        }
    };

    private void changeManager(final int longPosition, final View v, String userId) {
        //请求成功,设置成功,刷新列表
        //TODO retrofit网络请求!
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        TransferPermissionsReqBean.BODYBean bodyBean = new TransferPermissionsReqBean.BODYBean(ToySelectorFragment.mToyId, ToySelectorFragment
                .mToyId, "1", userId);
        TransferPermissionsReqBean transferPermissionsReqBean = new TransferPermissionsReqBean("REQ", "TOYA", "", new SimpleDateFormat
                ("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", token, "1");
        Gson gson = new Gson();
        String s = gson.toJson(transferPermissionsReqBean);

        Call<TransferPermissionsResBean> transferPermissionsResBeanCall = allInterface.TRANSFER_PERMISSIONS_RES_BEAN_CALL(s);
        transferPermissionsResBeanCall.enqueue(new Callback<TransferPermissionsResBean>() {
            @Override
            public void onResponse(Call<TransferPermissionsResBean> call, Response<TransferPermissionsResBean> response) {
                if (response.body().getCODE().equals("0")) {
                    //动画停止,setmode变成unset
                    StopFlick(animateView);
                    mSetManagerMode = SetManagerMode.UNSET;
                    mKingPosition = longPosition;
                    notifyDataSetChanged();
                    View root = (View) v.getParent().getParent();
                    ImageView imageView = (ImageView) root.findViewById(R.id.iv_manager_gridview_item_managerking);
                    animateView = imageView;
                    animateView.setVisibility(View.VISIBLE);
                } else {
                    Log.i("toymember", "onResponse: " + response.body().getMSG());
                }
            }

            @Override
            public void onFailure(Call<TransferPermissionsResBean> call, Throwable t) {
                Log.i("toymember", "onFailure: " + t);
            }
        });


    }

    private View.OnClickListener clickIcon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            ToastUtil.showToast(mContext, "点击的是:" + position);

            if (position == getCount() - 2) {
                LogUtil.d("will jump to add member");
                ToastUtil.showToast(mContext, "点击了加号,开始执行加号的逻辑");
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("toyinfo", mResponse);
                intent.putExtras(bundle);
                intent.putExtra("flag",TOYMEMBER_ADAPTER_TO_ADDMEMEBER_GROUP);
                intent.setClass(mContext, AddMemberToGroup.class);
                mContext.startActivity(intent);
            } else if (position == getCount() - 1) {
                if (mMode == Constant.Mode.NORMAL) {
                    mMode = Constant.Mode.DEL;
                } else {
                    mMode = Constant.Mode.NORMAL;
                }
                LogUtil.d("switch mode , mMode=>" + mMode);
                refreshUI();
            } else {

                if (mMode != Constant.Mode.NORMAL) {
                    if (!mLSTBeanlist.get(position).getID().equals(ownerUserId)) {
                        String id = mLSTBeanlist.get(position).getID();
                        //提交网络 管理员踢人出玩具群
                        deleteMember(toyId, id, position);
                    } else {
                        ToastUtil.showToast(mContext, "你点击的是管理员,管理员不能删除");
                    }
                }
            }
        }
    };

    private void deleteMember(String toyId, String id, final int position) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
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
                    mLSTBeanlist.remove(position);
                    refreshUI();
                }
            }

            @Override
            public void onFailure(Call<DelMembFromGroupReSBean> call, Throwable t) {

                ToastUtil.showToast(mContext, "shibai");

            }
        });

    }

    public int getIconPosition() {
        return mKingPosition;
    }

    private void refreshUI() {
        notifyDataSetChanged();
    }

    private ImageView animateView;

    public void StopFlick(ImageView toyMemberAdapterIcon) {
        if (toyMemberAdapterIcon == null) {
            return;
        }
        toyMemberAdapterIcon.clearAnimation();
        animateView = toyMemberAdapterIcon;
    }

    public void StartFlick(ImageView toyMemberAdapterIcon) {
        if (toyMemberAdapterIcon == null) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.4f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        toyMemberAdapterIcon.startAnimation(alphaAnimation);
        animateView = toyMemberAdapterIcon;
    }


}
