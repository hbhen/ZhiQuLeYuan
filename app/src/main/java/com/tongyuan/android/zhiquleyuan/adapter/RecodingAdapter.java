//package com.tongyuan.android.zhiquleyuan.adapter;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.tongyuan.android.zhiquleyuan.R;
//
//import java.io.File;
//import java.util.List;
//
///**
// * Created by android on 2017/3/5.
// */
//
//public class RecodingAdapter extends BaseAdapter {
//    private final List<File> rec;
//
//    public RecodingAdapter(List<File> rec) {
//        this.rec = rec;
//    }
//
//    @Override
//    public int getCount() {
//        return rec.size();
//    }
//
//    @Override
//    public File getItem(int position) {
//        return rec.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = View.inflate(parent.getContext(), R.layout.recoding_item, null);
//        }
//
//        TextView tv_file = (TextView) convertView.findViewById(R.id.tv_file);
//        tv_file.setText(rec.get(position).getName());
//        return convertView;
//    }
//
//
//}
