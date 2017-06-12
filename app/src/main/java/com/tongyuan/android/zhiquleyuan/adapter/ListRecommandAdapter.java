package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.Items;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryListViewHolder1;
import com.tongyuan.android.zhiquleyuan.holder.DiscoveryListViewHolder2;

import java.util.ArrayList;
import java.util.List;

import static com.tongyuan.android.zhiquleyuan.R.id.tv_desc_detailstimes;
import static com.tongyuan.android.zhiquleyuan.R.id.tv_desc_times_notitle;
import static com.tongyuan.android.zhiquleyuan.utils.ZhiQuLeYuanApplication.context;

/**
 * Created by DTC on 2017/3/23.
 */
public class ListRecommandAdapter extends BaseAdapter {
    private List<Items> itemList=new ArrayList<Items>();
    private Context mContext;
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public ListRecommandAdapter(Context context, List<Items> list) {
        this.mContext = context;
        this.itemList = list;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化两个holder,因为有两个布局
        DiscoveryListViewHolder1 disHolder1 = null;
        DiscoveryListViewHolder2 disHolder2 = null;

        int type = getItemViewType(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            switch (type) {
                case TYPE_ONE:
                    convertView = View.inflate(context, R.layout.item_discovery_listview, null);
                    disHolder1 = new DiscoveryListViewHolder1();
                    disHolder1.iv_title = (ImageView) convertView.findViewById(R.id.iv_title);
                    disHolder1.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                    disHolder1.iv_desc = (ImageView) convertView.findViewById(R.id.iv_desc);
                    disHolder1.tv_desc_title = (TextView) convertView.findViewById(R.id.tv_desc_title);
                    disHolder1.tv_desc_times = (TextView) convertView.findViewById(tv_desc_detailstimes);
                    disHolder1.tv_desc_category = (TextView) convertView.findViewById(R.id.tv_desc_category);
                    convertView.setTag(disHolder1);
                    break;
                case TYPE_TWO:
                    convertView = View.inflate(context, R.layout.item_discovery_listview_notitle, null);
                    disHolder2 = new DiscoveryListViewHolder2();
                    disHolder2.iv_desc_notitle = (ImageView) convertView.findViewById(R.id.iv_desc_notitle);
                    disHolder2.tv_desc_title_notitle = (TextView) convertView.findViewById(R.id.tv_desc_title_notitle);
                    disHolder2.tv_desc_times_notitle = (TextView) convertView.findViewById(tv_desc_times_notitle);
                    disHolder2.tv_desc_category_notitle = (TextView) convertView.findViewById(R.id.tv_desc_category_notitle);
                    convertView.setTag(disHolder2);
                    break;
                default:
                    break;

            }

        } else {
            switch (type) {
                case TYPE_ONE:
                    disHolder1 = (DiscoveryListViewHolder1) convertView.getTag();
                    break;
                case TYPE_TWO:
                    disHolder2 = (DiscoveryListViewHolder2) convertView.getTag();
                    break;
                default:
                    break;

            }


        }
        switch (type) {
            case TYPE_ONE:
                disHolder1.iv_title.setImageResource(R.mipmap.ic_launcher);
                disHolder1.tv_title.setText("推荐内容");
                disHolder1.iv_desc.setImageResource(R.mipmap.ic_launcher);
                disHolder1.tv_desc_title.setText("TED-Ed原创课程");
                disHolder1.tv_desc_times.setText("播放次数");
                disHolder1.tv_desc_category.setText("所属品类");
                break;
            case TYPE_TWO:
                disHolder2.iv_desc_notitle.setImageResource(R.mipmap.ic_launcher);
                disHolder2.tv_desc_title_notitle.setText("TED-Ed原创课程");
                disHolder2.tv_desc_times_notitle.setText("播放次数");
                disHolder2.tv_desc_category_notitle.setText("所属品类");
                break;

        }

        return convertView;
    }
}
