package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.QueryRecordingResBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Android on 2017/6/13.
 */
public class RecordingListAdapter extends BaseAdapter {
    private List<QueryRecordingResBean.BODYBean.LSTBean> mLSTBeen;
    private Context mContext;




    public RecordingListAdapter(Context context, List<QueryRecordingResBean.BODYBean.LSTBean> lst) {
        this.mLSTBeen = lst;
        this.mContext = context;
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
        String id = mLSTBeen.get(position).getID();
        String substring = id.substring(4, 12);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMddHHmm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/HH:mm");

        Date parse1 = null;
        String formattime = null;
        try {
            parse1 = simpleDateFormat1.parse(substring);
            formattime = simpleDateFormat.format(parse1);

            Log.i("1111", "onCreate: " + substring);
            Log.i("1111", "onCreate: " + formattime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.item_recoding1, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tv_title.setText(mLSTBeen.get(position).getNAME());
        holder.tv_recoding_desc_detailstime.setText(formattime);
        holder.tv_recoding_desc_detailslongtime.setText(mLSTBeen.get(position).getDUR());
        holder.tv_recoding_desc_detailsplaytime.setText(mLSTBeen.get(position).getTIMES());
        return convertView;
    }

    class ViewHolder {
        TextView tv_title;
        TextView tv_recoding_desc_detailstime;
        TextView tv_recoding_desc_detailslongtime;
        TextView tv_recoding_desc_detailsplaytime;

        public ViewHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.tv_item_recording_title);
            tv_recoding_desc_detailstime = (TextView) view.findViewById(R.id.tv_recoding_desc_detailstime);
            tv_recoding_desc_detailslongtime = (TextView) view.findViewById(R.id.tv_recoding_desc_detailslongtime);
            tv_recoding_desc_detailsplaytime = (TextView) view.findViewById(R.id.tv_recoding_desc_detailsplaytime);
            view.setTag(this);
        }
    }
    public void delete(int p){//设置删除方法

        mLSTBeen.remove(p);

        notifyDataSetChanged();

    }
}
