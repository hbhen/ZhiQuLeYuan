package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.RecordingItem;
import com.tongyuan.android.zhiquleyuan.db.DBHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by DTC on 2017/4/21.
 */
public class RecordAdapter extends BaseSwipeAdapter {
    private Context mContext;
    private List<File> recordList;
    private DBHelper mDbHelper;
    LinearLayoutManager mLinearLayoutManager;
    private LinearLayoutManager mLinearLayoutManager1;
    RecordingItem item;



    public RecordAdapter(FragmentActivity activity, List<File> recordList) {
        this.mContext = activity;
        this.recordList = recordList;
        mDbHelper = new DBHelper(mContext);
//        DBHelper.setOnDatabaseChangedListener(this);
        mLinearLayoutManager1 = new LinearLayoutManager(mContext);

    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.recordSwipe;
//        return position;

    }


    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_recoding, parent, false);
    }

    @Override
    public void fillValues(int position, View convertView) {
        final int pos = position;

        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.recordSwipe);
        TextView title = (TextView) convertView.findViewById(R.id.tv_item_recording_title);
        TextView lastModifyTime = (TextView) convertView.findViewById(R.id.tv_recoding_desc_detailstime);
        TextView duration = (TextView) convertView.findViewById(R.id.tv_recoding_desc_detailslongtime);
        TextView playTimes = (TextView) convertView.findViewById(R.id.tv_recoding_desc_detailsplaytime);
        Button delete = (Button) convertView.findViewById(R.id.swipe_record_delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordList.remove(pos);
                notifyDataSetChanged();
                swipeLayout.close();
            }
        });

        title.setText(recordList.get(pos).getName());
        long modifiedTime = recordList.get(pos).lastModified();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String time = simpleDateFormat.format(modifiedTime);
        lastModifyTime.setText(time);
        long durationLen = recordList.get(pos).length();
        String len = simpleDateFormat.format(durationLen);
        duration.setText(len);

        swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click", "click");
            }
        });


        //TODO Times不知道怎么写...
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int position) {
        return recordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
