package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryGridItemBean;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;

import java.util.List;

import static com.tongyuan.android.zhiquleyuan.R.id.tv_desc_detailstimes;

/**
 * Created by android on 2016/12/27.
 */
public class DiscoveryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    public MyItemClickListener mMyItemClickListener;
    public static final String TAG = "sshhsshh";
    private List<DiscoveryGridItemBean.LSTBean> mDiscoveryGridViewList;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> mDiscoveryListViewList;
    private int width;
    private int height;
    //TYPE
    public static final int TYPE_TYPE1_HEAD = 0xff01;
    public static final int TYPE_TYPE1 = 0xff02;
    public static final int TYPE_TYPE2_HEAD = 0xff03;
    public static final int TYPE_TYPE2 = 0xff04;
    public ImageView iv_title;
    public ImageView iv_desc;
    public TextView tv_title;
    public TextView tv_desc_title;
    public TextView tv_desc_times;
    public TextView tv_desc_category;

    public ImageView gridImg;
    public TextView gridText;

    public DiscoveryRecyclerAdapter(Context context, List<DiscoveryGridItemBean.LSTBean> discoveryGridViewList, List<DiscoveryListResultBean
            .BODYBean.LSTBean> discoveryListViewList) {
        this.mContext = context;
        this.mDiscoveryGridViewList = discoveryGridViewList;
        this.mDiscoveryListViewList = discoveryListViewList;
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        width = (screenWidth - 3 * (int) context.getResources().getDimension(R.dimen.discovery_grid_space)) / 3;
        height = 86 * width / 112;
        Log.i("discovery ssshhh", "DiscoveryRecyclerAdapter: " + mDiscoveryGridViewList.size() + mDiscoveryGridViewList.toString());
        Log.i("discovery ssshhh", "DiscoveryRecyclerAdapter: " + mDiscoveryListViewList.size() + mDiscoveryListViewList.toString());

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TYPE1_HEAD:
                return new HolderNineGridTitle(LayoutInflater.from(mContext).inflate(R.layout.discovery_recyclerview_nine_title, null),mMyItemClickListener);
            case TYPE_TYPE1:
                return new HolderNineGrid(LayoutInflater.from(mContext).inflate(R.layout.item_discovery_gridview, null),mMyItemClickListener);
            case TYPE_TYPE2_HEAD:
                return new HolderListViewTitle(LayoutInflater.from(mContext).inflate(R.layout.discovery_recyclerview_listview_title,
                        null));
            case TYPE_TYPE2:
                return new HolderListView(LayoutInflater.from(mContext).inflate(R.layout.item_discovery_listview, null),mMyItemClickListener);

            default:
                Log.d("error", "viewholder is null");
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
                        default:
                            return 3;
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderNineGridTitle) {
            bindType1Head((HolderNineGridTitle) holder, position);
            //设置title的显示和隐藏


        } else if (holder instanceof HolderNineGrid) {
            bindType1((HolderNineGrid) holder, position);

        } else if (holder instanceof HolderListViewTitle) {
            bindType2Head((HolderListViewTitle) holder, position);

        } else if (holder instanceof HolderListView) {
            bindType2((HolderListView) holder, position);
        }
    }

    private void bindType1Head(HolderNineGridTitle holder, int position) {
        Log.i(TAG, "bindType1Head: " + position);
        Log.i(TAG, "bindType1Head: " + position);

    }

    private void bindType1(HolderNineGrid holder, int position) {
        Log.i(TAG, "bindType1: " + position);
        Glide.with(mContext).load(mDiscoveryGridViewList.get(position - 1).IMG).into(gridImg);
        gridText.setText(mDiscoveryGridViewList.get(position - 1).NAME);
    }

    private void bindType2Head(HolderListViewTitle holder, int position) {
        Log.i(TAG, "bindType2Head: " + position);
    }

    private void bindType2(HolderListView holder, int position) {
        Log.i(TAG, "bindType2: " + position);
        Glide.with(mContext).load(mDiscoveryListViewList.get(position - mDiscoveryGridViewList.size() - 1).getIMG()).asBitmap().into(iv_desc);
        tv_desc_title.setText(mDiscoveryListViewList.get(position - mDiscoveryGridViewList.size() - 1).getNAME());
        tv_desc_times.setText(mDiscoveryListViewList.get(position - mDiscoveryGridViewList.size() - 1).getTIMES());
        tv_desc_category.setText(mDiscoveryListViewList.get(position - mDiscoveryGridViewList.size() - 1).getCOLNAME());

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TYPE1_HEAD;
        } else if (1 <= position && position <= 9) {
            return TYPE_TYPE1;
        } else if (position == 10) {
            return TYPE_TYPE2_HEAD;
        } else {
            return TYPE_TYPE2;
        }

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + mDiscoveryGridViewList.size() + mDiscoveryListViewList.size());
        Log.i(TAG, "getItemCount: " + mDiscoveryGridViewList.size() + mDiscoveryListViewList.size() + 2);
        return mDiscoveryGridViewList.size() + mDiscoveryListViewList.size();
    }


    public class HolderNineGridTitle extends RecyclerView.ViewHolder implements View.OnClickListener {
        public HolderNineGridTitle(View itemView,MyItemClickListener listener) {
            super(itemView);
            mMyItemClickListener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mMyItemClickListener!=null){
                mMyItemClickListener.onItemClick(v,getLayoutPosition());
            }
        }
    }

    public class HolderListViewTitle extends RecyclerView.ViewHolder {
        public HolderListViewTitle(View itemView) {
            super(itemView);
        }


    }

    public class HolderNineGrid extends RecyclerView.ViewHolder implements View.OnClickListener {


        public HolderNineGrid(View itemView, MyItemClickListener listener) {
            super(itemView);
            mMyItemClickListener=listener;

            gridImg = (ImageView) itemView.findViewById(R.id.iv_grid);
            gridImg.setLayoutParams(new LinearLayout.LayoutParams(width, height));
            gridText = (TextView) itemView.findViewById(R.id.tv_grid);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mMyItemClickListener!=null){
                mMyItemClickListener.onItemClick(v,getLayoutPosition());
            }
        }
    }

    public class HolderListView extends RecyclerView.ViewHolder implements View.OnClickListener {


        public HolderListView(View itemView,MyItemClickListener listener) {
            super(itemView);
            mMyItemClickListener=listener;
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
            if (mMyItemClickListener!=null){
                mMyItemClickListener.onItemClick(v,getLayoutPosition());
            }
        }
    }

    public interface MyItemClickListener {
         void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mMyItemClickListener = listener;
    }

}
