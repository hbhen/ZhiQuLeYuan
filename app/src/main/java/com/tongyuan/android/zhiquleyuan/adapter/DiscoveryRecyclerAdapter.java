package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.ActivityLogin;
import com.tongyuan.android.zhiquleyuan.activity.DiscoverySecondCategoryActivity;
import com.tongyuan.android.zhiquleyuan.activity.MyPlayActivity;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.tongyuan.android.zhiquleyuan.R.id.tv_desc_detailstimes;

/**
 * Created by android on 2016/12/27.
 */
public class DiscoveryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    //private MyItemClickListener mMyItemClickListener;
    public static final String TAG = "sshhsshh";
    private List<DiscoveryGridItemBean.LSTBean> mDiscoveryGridViewList;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> mDiscoveryListViewList;
    private int height;
    //TYPE
    private static final int TYPE_TYPE1_HEAD = 0xff01;
    private static final int TYPE_TYPE1 = 0xff02;
    private static final int TYPE_TYPE2_HEAD = 0xff03;
    private static final int TYPE_TYPE2 = 0xff04;
    private static final int TYPE_FOOTER = 0xff05;
    private boolean hasFootView = false;

    private LayoutInflater inflate;
    private String mToken = "";

    public DiscoveryRecyclerAdapter(Context context, List<DiscoveryGridItemBean.LSTBean> discoveryGridViewList, List<DiscoveryListResultBean
            .BODYBean.LSTBean> discoveryListViewList) {
        this.mContext = context;
        this.mDiscoveryGridViewList = discoveryGridViewList;
        this.mDiscoveryListViewList = discoveryListViewList;
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        inflate = LayoutInflater.from(mContext);
        int width = (screenWidth - 4 * (int) context.getResources().getDimension(R.dimen.discovery_grid_space)) / 3;
        height = 86 * width / 112;
        mToken = SPUtils.getString(mContext, "token", "");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TYPE1_HEAD:
                return new HolderNineGridTitle(inflate.inflate(R.layout.discovery_recyclerview_nine_title, null));
            case TYPE_TYPE1:
                return new HolderNineGrid(inflate.inflate(R.layout.item_discovery_gridview, null));
            case TYPE_TYPE2_HEAD:
                return new HolderListViewTitle(inflate.inflate(R.layout.discovery_recyclerview_listview_title,
                        null));
            case TYPE_TYPE2:
                return new HolderListView(inflate.inflate(R.layout.item_discovery_listview, null));
            case TYPE_FOOTER:
                View view = inflate.inflate(R.layout.discovery_sub_item_foot, parent, false);
                return new FootViewHolde(view);
            default:
                //LogUtil.d("error", "viewholder is null");
                return null;
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_TYPE1_HEAD:
                        case TYPE_TYPE2_HEAD:
                        case TYPE_TYPE2:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE1:
                            return 2;
                        case TYPE_FOOTER:
                            return 6;
                        default:
                            return 3;
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderNineGrid) {
            bindGridHold((HolderNineGrid) holder, position);
        } else if (holder instanceof HolderListView) {
            bindListHold((HolderListView) holder, position);
        }
    }

    private void bindGridHold(HolderNineGrid holder, int position) {
        int realPosition;
        if ("".equals(mToken)) {
            realPosition = position - 1;
        } else {
            realPosition = position;
        }
        Glide.with(mContext).load(mDiscoveryGridViewList.get(realPosition).IMG).placeholder(R.drawable.player_cover_default).into(holder.gridImg);
        holder.gridText.setText(mDiscoveryGridViewList.get(realPosition).NAME);
    }

    private void bindListHold(HolderListView holder, int position) {
        int realPosition = position - mDiscoveryGridViewList.size();
        if ("".equals(mToken)) {
            realPosition -= 2;
        } else {
            realPosition -= 1;
        }
        if (realPosition == mDiscoveryListViewList.size()) {
            return;
        }
        Glide.with(mContext).load(mDiscoveryListViewList.get(realPosition).
                getIMG()).asBitmap().placeholder(R.drawable.player_cover_default).into(holder.iv_desc);
        holder.tv_desc_title.setText(mDiscoveryListViewList.get(realPosition).getNAME());
        holder.tv_desc_times.setText(mDiscoveryListViewList.get(realPosition).getTIMES());
        holder.tv_desc_category.setText(mDiscoveryListViewList.get(realPosition).getCOLNAME());
    }

    @Override
    public int getItemViewType(int position) {
        if ("".equals(mToken)) {
            if (position == 0) {
                return TYPE_TYPE1_HEAD;
            } else if (0 < position && position <= mDiscoveryGridViewList.size()) {
                return TYPE_TYPE1;
            } else if (position == (1 + mDiscoveryGridViewList.size())) {
                return TYPE_TYPE2_HEAD;
            } else if ((position == getItemCount() - 1) && hasFootView) {
                return TYPE_FOOTER;
            } else {
                return TYPE_TYPE2;
            }
        } else {
            if (position < mDiscoveryGridViewList.size()) {
                return TYPE_TYPE1;
            } else if (position == mDiscoveryGridViewList.size()) {
                return TYPE_TYPE2_HEAD;
            } else if ((position == getItemCount() - 1) && hasFootView) {
                return TYPE_FOOTER;
            } else {
                return TYPE_TYPE2;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mDiscoveryGridViewList.size() == 0 && mDiscoveryListViewList.size() == 0) {
            return 0;
        }
        int count;
        if ("".equals(mToken)) {
            count = mDiscoveryGridViewList.size() + mDiscoveryListViewList.size() + 2;
        } else {
            count = mDiscoveryGridViewList.size() + mDiscoveryListViewList.size() + 1;
        }

        if (hasFootView) {
            count += 1;
        }
        return count;
    }


    private class HolderNineGridTitle extends RecyclerView.ViewHolder implements View.OnClickListener {
        HolderNineGridTitle(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //登录
            Intent intent = new Intent(mContext, ActivityLogin.class);
            mContext.startActivity(intent);
        }
    }

    private class HolderListViewTitle extends RecyclerView.ViewHolder {
        HolderListViewTitle(View itemView) {
            super(itemView);
        }
    }

    private class HolderNineGrid extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView gridImg;
        TextView gridText;

        HolderNineGrid(View itemView) {
            super(itemView);
            gridImg = (ImageView) itemView.findViewById(R.id.iv_grid);
            gridImg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
            gridText = (TextView) itemView.findViewById(R.id.tv_grid);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            if ("".equals(mToken)) {
                position -= 1;
            }
            DiscoveryGridItemBean.LSTBean bean = mDiscoveryGridViewList.get(position);
            DiscoverySecondCategoryActivity.launch(mContext, bean.IMG, bean.ID);
        }
    }

    private class HolderListView extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_title;
        ImageView iv_desc;
        TextView tv_title;
        TextView tv_desc_title;
        TextView tv_desc_times;
        TextView tv_desc_category;

        HolderListView(View itemView) {
            super(itemView);
            //mMyItemClickListener=listener;
            iv_title = (ImageView) itemView.findViewById(R.id.iv_title);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_desc = (ImageView) itemView.findViewById(R.id.iv_desc);
            tv_desc_title = (TextView) itemView.findViewById(R.id.tv_desc_title);
            tv_desc_times = (TextView) itemView.findViewById(tv_desc_detailstimes);
            tv_desc_category = (TextView) itemView.findViewById(R.id.tv_desc_detailscategory);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if ("".equals(mToken)) {
                ToastUtil.showToast(mContext, R.string.user_no_login);
                return;
            }
            int position;
            if ("".equals(mToken)) {
                position = getLayoutPosition() - 2 - mDiscoveryGridViewList.size();
            } else {
                position = getLayoutPosition() - mDiscoveryGridViewList.size() - 1;
            }
            MyPlayActivity.launch(mContext, (ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>) mDiscoveryListViewList, position);
        }
    }

    private class FootViewHolde extends RecyclerView.ViewHolder {

        public FootViewHolde(View itemView) {
            super(itemView);
        }
    }

    public boolean isLogin() {
        mToken = SPUtils.getString(mContext, "token", "");
        return (!"".equals(mToken));
    }

    /**
     * 是否加载更多
     *
     * @param isLoad true就显示FootView
     */
    public void isLoadMore(boolean isLoad) {
        this.hasFootView = isLoad;
        notifyDataSetChanged();
    }

    public boolean isHasFootView() {
        return hasFootView;
    }
}
