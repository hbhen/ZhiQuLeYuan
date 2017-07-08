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
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.DiscoveryListResultBean;
import com.tongyuan.android.zhiquleyuan.holder.SecondaryCategoryHolder;
import com.tongyuan.android.zhiquleyuan.player.MusicPlayer;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/12/21.
 */
public class DiscoverySecondCategoryAdapter extends BaseAdapter {
    private Context mContext;
    private final LayoutInflater mInflater;
    private List<DiscoveryListResultBean.BODYBean.LSTBean> list ;
    private Drawable playingDrawable;
    private Drawable grayPlayDrawable;


    public DiscoverySecondCategoryAdapter(Context context, List<DiscoveryListResultBean.BODYBean.LSTBean> list) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        playingDrawable = context.getResources().getDrawable(R.drawable.dis_sub_item_played);
        playingDrawable.setBounds(0, 0, playingDrawable.getMinimumWidth(), playingDrawable.getMinimumHeight());//必须设置图片大小，否则不显示
        grayPlayDrawable = context.getResources().getDrawable(R.drawable.audition_list_gray_ico);
        grayPlayDrawable.setBounds(0, 0, grayPlayDrawable.getMinimumWidth(), grayPlayDrawable.getMinimumHeight());//必须设置图片大小，否则不显示
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
        }else{
            holder= (SecondaryCategoryHolder) convertView.getTag();
        }
        /**
         * {
         * "TYPE": "RES",
         * "CMD": "QRYRES",
         * "ACCT": "XXXX",
         * "TIME": "20170324113712831",
         * "BODY": {
         * "PN": "1",
         * "CNT": "1",
         * "PS": "10",
         * "NC": "0",
         * "LST": [{
         * "TYPE": "音频文件",
         * "NAME": "蚂蚁",
         * "IMG": "http://120.27.41.179:8081/zqpland/resource/thumbnail/5/png/20170210/201702101530091016563538.png",
         * "ID": "201701121950061016547106",
         * "COLNAME": "国学经典 ",
         * "SIZE": "11.03MB",
         * "DUR": "",
         * "COLID": "201611050827051016432864 ",
         * "REMARK": "",
         * "TIMES": "5"
         * }]
         * },
         * "VERI": "",
         * "TOKEN": "51ff422f-fdec-4c1e-a277-90419c20b827",
         * "SEQ": "1",
         * "CODE": "0",
         * "MSG": ""
         * }
         */
        Uri parse = Uri.parse(list.get(position).getIMG());
        Glide.with(mContext).load(parse).placeholder(R.drawable.player_cover_default).into(holder.itemalumbimg);
        holder.textviewTitle.setText(list.get(position).getNAME());
        holder.playView.setTag(position);
        holder.favoriteView.setTag(position);
        if(list.get(position).isPlaying /*&& MusicPlayerService.isPlayUrl(list.get(position).getID())*/) {
            holder.playView.setCompoundDrawables(playingDrawable, null, null, null);
            holder.playView.setTextColor(mContext.getResources().getColor(R.color.redFont));
        } else {
            holder.playView.setCompoundDrawables(grayPlayDrawable, null, null, null);
            holder.playView.setTextColor(mContext.getResources().getColor(R.color.grayFont));
        }
        return convertView;
    }

    private View.OnClickListener clickView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            if (v.getId() == R.id.dis_sub_item_play) {
                String mToken = SPUtils.getString(mContext, "TOKEN", "");
                if("".equals(mToken)) {
                    ToastUtil.showToast(mContext, R.string.user_no_login);
                    return;
                }
                String musicId = list.get(position).getID();
                if(list.get(position).isPlaying && MusicPlayerService.isPlayUrl(list.get(position).getID())) {
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

            } else if(v.getId() == R.id.dis_sub_item_favorite) {
                ToastUtil.showToast(mContext, "favorite");
            }
        }
    };
}
