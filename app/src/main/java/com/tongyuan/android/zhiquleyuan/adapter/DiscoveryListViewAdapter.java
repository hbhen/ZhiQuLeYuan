package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.bean.Items;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryListViewHolder1;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryListViewHolder2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static com.tongyuan.android.zhiquleyuan.R.id.tv_desc_detailstimes;
import static com.tongyuan.android.zhiquleyuan.R.id.tv_desc_detailstimes_notitle;

/**
 * Created by android on 2016/12/9.
 */

public class DiscoveryListViewAdapter extends BaseAdapter {
    //    ArrayList<ResourceID> list = new ArrayList<ResourceID>();
//    List<Items> itemList = new ArrayList<>();


    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    private Context mContext;
//    private Response<DiscoveryListResultBean> mResponse;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> list = new ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>();

    public DiscoveryListViewAdapter(Context context, List<Items> list, Response<DiscoveryListResultBean> response) {
        this.mContext = context;
//        this.mResponse=response;
//        this.itemList = list;
        this.list.addAll(response.body().getBODY().getLST());
    }


    //有数据的时候,传数据集合过来
//    public DiscoveryListViewAdapter(Context context,ArrayList<ResourceID> list) {
//        this.context = context;
//        this.list = list;
//
//    }

    public ArrayList<DiscoveryListResultBean.BODYBean.LSTBean> getList() {
        return (ArrayList<DiscoveryListResultBean.BODYBean.LSTBean>) list;
    }

    @Override
    public int getCount() {
//        return itemList.size();
        Log.i("adapter", "list.size="+list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
//        return itemList.get(position);
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public int getViewTypeCount() {
////        return 3;
//        return 2;
//    }

    //    每个convertView都会调用此方法,获得当前应该加载的布局样式
    @Override
    public int getItemViewType(int position) {
//        获取当前布局的数据
//        Items items = itemList.get(position);
        /*
        * 哪个字段不为空就说明是哪个布局
        * 比如,第一个布局只有一个字段,那么就判断这个字段是不是为空,如果不为空,就表用是第一布局的数据
        * 根据字段为不为空,判断当前应该加载的布局
        * */
        if (position==0) {
            return TYPE_ONE;
        } else
            return TYPE_TWO;

    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        //初始化两个holder,因为有两个布局
        DiscoveryListViewHolder1 disHolder1 = null;
        DiscoveryListViewHolder2 disHolder2 = null;

        int type = getItemViewType(position);

        if (convertview == null) {
//            LayoutInflater inflater = LayoutInflater.from(mContext);
            switch (type) {
                case TYPE_ONE:
                    convertview = View.inflate(mContext, R.layout.item_discovery_listview, null);
                    disHolder1 = new DiscoveryListViewHolder1();
                    disHolder1.iv_title = (ImageView) convertview.findViewById(R.id.iv_title);
                    disHolder1.tv_title = (TextView) convertview.findViewById(R.id.tv_title);
                    disHolder1.iv_desc = (ImageView) convertview.findViewById(R.id.iv_desc);
                    disHolder1.tv_desc_title = (TextView) convertview.findViewById(R.id.tv_desc_title);
                    disHolder1.tv_desc_times = (TextView) convertview.findViewById(tv_desc_detailstimes);
                    disHolder1.tv_desc_category = (TextView) convertview.findViewById(R.id.tv_desc_detailscategory);
                    convertview.setTag(disHolder1);
                    break;
                case TYPE_TWO:
                    convertview = View.inflate(mContext, R.layout.item_discovery_listview_notitle, null);
                    disHolder2 = new DiscoveryListViewHolder2();
                    disHolder2.iv_desc_notitle = (ImageView) convertview.findViewById(R.id.iv_desc_notitle);
                    disHolder2.tv_desc_title_notitle = (TextView) convertview.findViewById(R.id.tv_desc_title_notitle);
                    disHolder2.tv_desc_times_notitle = (TextView) convertview.findViewById(tv_desc_detailstimes_notitle);
                    disHolder2.tv_desc_category_notitle = (TextView) convertview.findViewById(R.id.tv_desc_detailscategory_notitle);
                    convertview.setTag(disHolder2);
                    break;
                default:
                    break;

            }

        } else {
            switch (type) {
                case TYPE_ONE:
                    if(convertview.getTag() instanceof DiscoveryListViewHolder1) {
                        disHolder1 = (DiscoveryListViewHolder1) convertview.getTag();
                    } else {
                        disHolder1 = fillHolder(convertview);
                    }
                    break;
                case TYPE_TWO:
                    if(convertview.getTag() instanceof DiscoveryListViewHolder2) {
                        disHolder2 = (DiscoveryListViewHolder2) convertview.getTag();
                    } else {
                        disHolder2 = fillHolderTitle(convertview);
                    }

                    break;
                default:
                    break;

            }


        }
        switch (type) {
            case TYPE_ONE:
//                disHolder1.iv_title.setImageResource(R.mipmap.ic_launcher);
                disHolder1.tv_title.setText("推荐内容");
//                disHolder1.iv_desc.setImageResource(R.mipmap.ic_launcher);
//                disHolder1.tv_desc_title.setText("TED-Ed原创课程");
//                disHolder1.tv_desc_times.setText("播放次数");
//                disHolder1.tv_desc_category.setText("所属品类");
                Glide.with(mContext).load(list.get(position).getIMG()).asBitmap().into(disHolder1.iv_desc);
                disHolder1.tv_desc_title.setText(list.get(position).getNAME());
                disHolder1.tv_desc_times.setText(list.get(position).getTIMES());
                disHolder1.tv_desc_category.setText(list.get(position).getCOLNAME());
                break;
            case TYPE_TWO:
//                disHolder2.iv_desc_notitle.setImageResource(R.mipmap.ic_launcher);
//                disHolder2.tv_desc_title_notitle.setText("TED-Ed原创课程");
//                disHolder2.tv_desc_times_notitle.setText("播放次数");
//                disHolder2.tv_desc_category_notitle.setText("所属品类");
                Glide.with(mContext).load(list.get(position).getIMG()).asBitmap().into(disHolder2.iv_desc_notitle);
                disHolder2.tv_desc_title_notitle.setText(list.get(position).getNAME());
                disHolder2.tv_desc_times_notitle.setText(list.get(position).getTIMES());
                disHolder2.tv_desc_category_notitle.setText(list.get(position).getCOLNAME());
                break;

        }

        return convertview;
    }

    private DiscoveryListViewHolder1 fillHolder(View convertview) {
        convertview = View.inflate(mContext, R.layout.item_discovery_listview, null);
        DiscoveryListViewHolder1 disHolder1 = new DiscoveryListViewHolder1();
        disHolder1.iv_title = (ImageView) convertview.findViewById(R.id.iv_title);
        disHolder1.tv_title = (TextView) convertview.findViewById(R.id.tv_title);
        disHolder1.iv_desc = (ImageView) convertview.findViewById(R.id.iv_desc);
        disHolder1.tv_desc_title = (TextView) convertview.findViewById(R.id.tv_desc_title);
        disHolder1.tv_desc_times = (TextView) convertview.findViewById(tv_desc_detailstimes);
        disHolder1.tv_desc_category = (TextView) convertview.findViewById(R.id.tv_desc_detailscategory);
        convertview.setTag(disHolder1);
        return disHolder1;
    }

    private DiscoveryListViewHolder2 fillHolderTitle(View convertview) {
        convertview = View.inflate(mContext, R.layout.item_discovery_listview_notitle, null);
        DiscoveryListViewHolder2 disHolder2 = new DiscoveryListViewHolder2();
        disHolder2.iv_desc_notitle = (ImageView) convertview.findViewById(R.id.iv_desc_notitle);
        disHolder2.tv_desc_title_notitle = (TextView) convertview.findViewById(R.id.tv_desc_title_notitle);
        disHolder2.tv_desc_times_notitle = (TextView) convertview.findViewById(tv_desc_detailstimes_notitle);
        disHolder2.tv_desc_category_notitle = (TextView) convertview.findViewById(R.id.tv_desc_detailscategory_notitle);
        convertview.setTag(disHolder2);
        return disHolder2;
    }
}
