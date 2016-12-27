package com.tongyuan.android.zhiquleyuan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.holder.DiscoveryGridHolder;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.util.ToastUtil;

/**
 * Created by android on 2016/12/21.
 */
public class DiscoveryGridAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private LayoutInflater inflater;
    private Context context;
    private View convertView;
    int[] pic=new int[]{};
    String[] text=new String[]{};

    public DiscoveryGridAdapter(Context context,int[] pic,String[] text) {
        this.context = context;
        this.pic=pic;
        this.text=text;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position) {
            case 0:
                Log.w("11", "onItemClick: _____________________");
                System.out.print("111111111111111111111111111");
                ToastUtil.showToast(context, "儿童歌谣");
                break;
            case 1:
                ToastUtil.showToast(context, "国学经典");
                break;
            case 2:
                ToastUtil.showToast(context, "生活百科");
                break;
            case 3:
                ToastUtil.showToast(context, "英语启蒙");
                break;
            case 4:
                ToastUtil.showToast(context, "中国民乐");
                break;
            case 5:
                ToastUtil.showToast(context, "西方古典");
                break;
            case 6:
                ToastUtil.showToast(context, "摇篮曲");
                break;
            case 7:
                ToastUtil.showToast(context, "胎教音乐");
                break;
            case 8:
                ToastUtil.showToast(context, "人格培养");
                break;
            default:
                break;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiscoveryGridHolder holder = null;

        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_discovery_gridview, null);
            holder = new DiscoveryGridHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_grid);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_grid);
            convertView.setTag(holder);
        } else {
            holder = (DiscoveryGridHolder) convertView.getTag();
        }
        //设置数据,这是写死的数据,以后还要获取网络上来的数据
        holder.iv.setImageResource(pic[position]);
        holder.tv.setText(text[position]);
        return convertView;
    }


}
