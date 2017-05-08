package com.tongyuan.android.zhiquleyuan.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ScrollView;

import com.tongyuan.android.zhiquleyuan.view.FlowLayout;

/**
 * Created by android on 2016/12/7.
 */

public class SearchFragment extends Fragment {


    ScrollView scrollView;
    FlowLayout flowLayout;
    int hPading, vPadding;

    protected View getSuccessView() {
        scrollView = new ScrollView(getActivity());
//            hPading = CommonUtil.getDimens(R.dimen.dp12);
//            vPadding = CommonUtil.getDimens(R.dimen.dp9);

        flowLayout = new FlowLayout(getActivity());
        //1.设置padding
//            int padding = CommonUtil.getDimens(R.dimen.dp15);
//            flowLayout.setPadding(padding, padding, padding, padding);

        scrollView.addView(flowLayout);
        return scrollView;
    }
}

    //  @Override
//    protected void loadData() {
//            HttpHelper.getInstance().get(Url.Hot, new HttpCallback() {
//                @Override
//               public void onSuccess(String result) {
//                    stateLayout.showSuccessView();
//        ArrayList<String> list = (ArrayList<String>) GsonUtil.parseJsonToList(result, new TypeToken<List<String>>() {
//        }.getType());
//        if (list != null) {
//            //更新UI
//
//            //1.遍历集合。给flowLayout添加子View
//            for (int i = 0; i < list.size(); i++) {
//                final TextView textView = new TextView(getActivity());
//                textView.setText(list.get(i));
//                textView.setTextColor(Color.WHITE);
//                textView.setPadding(hPading, vPadding, hPading, vPadding);
//                //设置背景色
//                GradientDrawable pressed = DrawableUtil.generateDrawable();
//                GradientDrawable normal = DrawableUtil.generateDrawable();
//                textView.setBackgroundDrawable(DrawableUtil.generateSelector(pressed, normal));
//
//                //设置点击事件
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtil.showToast(textView.getText().toString());
//                    }
//                });
//
//                //将TextView添加进来
//                flowLayout.addView(textView);
//            }
//        }
//    }
//
//    //@Override
//    public void onFail(Exception e) {
//        //  stateLayout.showErrorView();
//    }
//},false);
//
