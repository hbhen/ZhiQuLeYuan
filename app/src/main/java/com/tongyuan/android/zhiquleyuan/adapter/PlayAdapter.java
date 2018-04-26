package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.MyPlayActivity;
import com.tongyuan.android.zhiquleyuan.bean.MusicPlayerBean;

import java.util.ArrayList;

/**
 * Created by DTC on 2018/4/24.
 */

public class PlayAdapter extends BaseAdapter {
    private final String TAG = getClass().getSimpleName();
    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<? extends MusicPlayerBean> mStringArrayList;


    public PlayAdapter(ArrayList<? extends MusicPlayerBean> listw, Context context) {
        mContext = context;
        mStringArrayList = listw;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mStringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStringArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_playinglist, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Glide.with(mContext).load(mStringArrayList.get(position).getIMG()).asBitmap().placeholder(R.drawable.player_cover_default).into(holder
                .mAlumbImg);
        for (int i = 0; i < mStringArrayList.size(); i++) {
            String id = mStringArrayList.get(position).getID();
            if (id.equals(MyPlayActivity.getCurrentId())) {
                holder.mAlumbName.setTextColor(Color.RED);
            }
        }
        holder.mAlumbName.setText(mStringArrayList.get(position).getNAME());
        return convertView;
    }



    class ViewHolder {
        ImageView mAlumbImg;
        TextView mAlumbName;

        public ViewHolder(View view) {
            mAlumbImg = (ImageView) view.findViewById(R.id.iv_playinglist_img);
            mAlumbName = (TextView) view.findViewById(R.id.tv_playinglist_name);
            view.setTag(this);
        }
    }

}
