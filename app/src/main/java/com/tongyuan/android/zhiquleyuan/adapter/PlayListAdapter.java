package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryMyPlayResBean;

import java.util.List;

/**
 * Created by Android on 2017/6/13.
 */
public class PlayListAdapter extends BaseAdapter {
    private Context mContext;
    private List<QueryMyPlayResBean.BODYBean.LSTBean> mLSTBeen;


    public PlayListAdapter(Context context, List<QueryMyPlayResBean.BODYBean.LSTBean> lst) {
        this.mContext = context;
        this.mLSTBeen = lst;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.item_album_details_two, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();

        Glide.with(mContext).load(mLSTBeen.get(position).getIMG()).asBitmap().into(holder.iv_icon);
        holder.tv_name.setText(mLSTBeen.get(position).getNAME());
        return convertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;

        public ViewHolder(View view) {
            iv_icon = (ImageView) view.findViewById(R.id.iv_album_details_two);
            tv_name = (TextView) view.findViewById(R.id.tv_album_details_two_title);
            view.setTag(this);
        }
    }
}
