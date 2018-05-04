package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.AddInCollectionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.AddInCollectionResBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyCollectionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.DeleteMyCollectionResBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.holder.SecondaryCategoryHolder;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2016/12/21.
 */
public class DiscoverySecondCategoryAdapter extends BaseAdapter {
    private Context mContext;
    private final LayoutInflater mInflater;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> list;
    private Drawable playingDrawable;
    private Drawable grayPlayDrawable;
    private Drawable isFavoriteDrawable;
    private Drawable grayFavoriteDrawable;

    private boolean isFav = false;
    private HashMap<Integer, String> hm = new HashMap();


    public DiscoverySecondCategoryAdapter(Context context, List<DiscoveryListResultBean.BODYBean.LSTBean> list) {

        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;

        playingDrawable = context.getResources().getDrawable(R.drawable.dis_sub_item_played);
        playingDrawable.setBounds(0, 0, playingDrawable.getMinimumWidth(), playingDrawable.getMinimumHeight());//必须设置图片大小，否则不显示
        grayPlayDrawable = context.getResources().getDrawable(R.drawable.audition_list_gray_ico);
        grayPlayDrawable.setBounds(0, 0, grayPlayDrawable.getMinimumWidth(), grayPlayDrawable.getMinimumHeight());//必须设置图片大小，否则不显示

        isFavoriteDrawable = context.getResources().getDrawable(R.drawable.secondary_favorite_3x);
        isFavoriteDrawable.setBounds(0, 0, isFavoriteDrawable.getMinimumWidth(), isFavoriteDrawable.getMinimumHeight());//必须设置图片大小，否则不显示
        grayFavoriteDrawable = context.getResources().getDrawable(R.drawable.secondary_favorite_gray_3x);
        grayFavoriteDrawable.setBounds(0, 0, grayFavoriteDrawable.getMinimumWidth(), grayFavoriteDrawable.getMinimumHeight());//必须设置图片大小，否则不显示

    }

    public void onPause() {
        for (DiscoveryListResultBean.BODYBean.LSTBean bean : list) {
            bean.isPlaying = false;
        }
        notifyDataSetChanged();
    }

    public ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> getList() {
        return (ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>) list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SecondaryCategoryHolder holder;
        if (convertView == null) {
            holder = new SecondaryCategoryHolder();
            convertView = mInflater.inflate(R.layout.item_album_details_two, null);
            holder.textviewTitle = (TextView) convertView.findViewById(R.id.tv_album_details_two_title);
            holder.itemalumbimg = (ImageView) convertView.findViewById(R.id.iv_album_details_two);
            holder.textviewAge = (TextView) convertView.findViewById(R.id.tv_item_album_details_two_age);
            holder.textviewLike = (TextView) convertView.findViewById(R.id.tv_itemt_album_details_two_like);
            holder.playView = (TextView) convertView.findViewById(R.id.dis_sub_item_play);
            holder.favoriteView = (TextView) convertView.findViewById(R.id.dis_sub_item_favorite);
            holder.playView.setOnClickListener(clickView);
            holder.favoriteView.setOnClickListener(clickView);
            convertView.setTag(holder);
        } else {
            holder = (SecondaryCategoryHolder) convertView.getTag();

        }

        Uri parse = Uri.parse(list.get(position).getIMG());
        Glide.with(mContext).load(parse).placeholder(R.drawable.player_cover_default).into(holder.itemalumbimg);
        holder.textviewTitle.setText(list.get(position).getNAME());
        holder.playView.setTag(position);
        holder.favoriteView.setTag(position);
        holder.textviewLike.setText(list.get(position).getTIMES());
        hm.put(position, list.get(position).getISFAV());


        if (list.get(position).getISFAV().equals("1")) {
            holder.favoriteView.setCompoundDrawables(isFavoriteDrawable, null, null, null);
            holder.favoriteView.setTextColor(mContext.getResources().getColor(R.color.redFont));
        } else {
            holder.favoriteView.setCompoundDrawables(grayFavoriteDrawable, null, null, null);
            holder.favoriteView.setTextColor(mContext.getResources().getColor(R.color.grayFont));
        }

        if (list.get(position).isPlaying /*&& MusicPlayerService.isPlayUrl(list.get(position).getID())*/) {
            holder.playView.setCompoundDrawables(playingDrawable, null, null, null);
            holder.playView.setTextColor(mContext.getResources().getColor(R.color.redFont));
        } else {
            holder.playView.setCompoundDrawables(grayPlayDrawable, null, null, null);
            holder.playView.setTextColor(mContext.getResources().getColor(R.color.grayFont));
        }
        return convertView;
    }

    View.OnClickListener clickView = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            if (v.getId() == R.id.dis_sub_item_play) {
                String mToken = SPUtils.getString(mContext, "token", "");
                if ("".equals(mToken)) {
                    ToastUtil.showToast(mContext, R.string.user_no_login);
                    return;
                }
                String musicId = list.get(position).getID();
                if (list.get(position).isPlaying && MusicPlayerService.isPlayUrl(list.get(position).getID())) {
                    MusicPlayerService.stopService(mContext, true);
                    for (DiscoveryListResultBean.BODYBean.LSTBean bean : list) {
                        bean.isPlaying = false;
                    }
                } else {
                    MusicPlayerService.launchService(mContext, musicId);
                    for (DiscoveryListResultBean.BODYBean.LSTBean bean : list) {
                        bean.isPlaying = false;
                    }
                    list.get(position).isPlaying = true;
                }
                notifyDataSetChanged();

            } else if (v.getId() == R.id.dis_sub_item_favorite) {
                TextView textview = (TextView) v;
                if (hm.get(position).equals("1")) {
                    list.get(position).setISFAV("0");
                    textview.setTextColor(mContext.getResources().getColor(R.color.redFont));
                    deleteCollectionRes(position);
                } else {
                    list.get(position).setISFAV("1");
                    hm.put(position, "1");
                    textview.setTextColor(mContext.getResources().getColor(R.color.grayFont));
                    collectionRes(position);
                }

            }
        }
    };

    private void deleteCollectionRes(final int position) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        DeleteMyCollectionReqBean.BODYBean.LSTBean lstBean = new DeleteMyCollectionReqBean.BODYBean.LSTBean(list.get(position).getID());
        ArrayList arraylist = new ArrayList();
        arraylist.add(lstBean);
        final DeleteMyCollectionReqBean.BODYBean bodyBean = new DeleteMyCollectionReqBean.BODYBean(arraylist);
        DeleteMyCollectionReqBean deleteMyCollectionReqBean = new DeleteMyCollectionReqBean("REQ", "DFAVRES", SPUtils.getString(mContext,
                "phoneNum", ""), new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(mContext, "token", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(deleteMyCollectionReqBean);
        Call<DeleteMyCollectionResBean> deleteMyCollectionResBeanCall = allInterface.DELETE_MYCOLLECTION_RES_BEAN_CALL(s);
        deleteMyCollectionResBeanCall.enqueue(new Callback<DeleteMyCollectionResBean>() {
            @Override
            public void onResponse(Call<DeleteMyCollectionResBean> call, Response<DeleteMyCollectionResBean> response) {
                if (response != null && response.body().getCODE().equals("0")) {
                    notifyDataSetChanged();
//                    ToastUtil.showToast(mContext, "删除收藏成功");
                }
            }

            @Override
            public void onFailure(Call<DeleteMyCollectionResBean> call, Throwable t) {
            }
        });
    }


    private void collectionRes(final int position) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        AddInCollectionReqBean.BODYBean.LSTBean lstBean = new AddInCollectionReqBean.BODYBean.LSTBean(list.get(position).getID());
        ArrayList arraylist = new ArrayList();
        arraylist.add(lstBean);
        AddInCollectionReqBean.BODYBean bodyBean = new AddInCollectionReqBean.BODYBean(arraylist);
        AddInCollectionReqBean addInCollectionReqBean = new AddInCollectionReqBean("REQ", "FAVRES", SPUtils.getString(mContext, "phoneNum", ""), new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(mContext, "token", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(addInCollectionReqBean);
        Call<AddInCollectionResBean> addInCollectionResBeanCall = allInterface.ADDINCOLLECTION_RES_BEAN_CALL(s);
        addInCollectionResBeanCall.enqueue(new Callback<AddInCollectionResBean>() {
            @Override
            public void onResponse(Call<AddInCollectionResBean> call, Response<AddInCollectionResBean> response) {
                if (response != null && response.body().getCODE().equals("0")) {
                    notifyDataSetChanged();
                    ToastUtil.showToast(mContext, "收藏成功");
                }
            }

            @Override
            public void onFailure(Call<AddInCollectionResBean> call, Throwable t) {
            }
        });
    }
}
