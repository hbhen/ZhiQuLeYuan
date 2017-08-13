package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.adapter.NewAdapter;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeReqBean;
import com.tongyuan.android.zhiquleyuan.bean.NodisturbTimeResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2017/3/12.
 */
public class NoDisturbActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_add_nodisturbtime)
    ImageView mAddNodisturb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodisturb);
        ButterKnife.bind(this);
        getNoDisturbTime();
    }

    private void getNoDisturbTime() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        NodisturbTimeReqBean.BODYBean bodyBean = new NodisturbTimeReqBean.BODYBean(ToySelectorFragment.mToyId, "10", "1");
        NodisturbTimeReqBean nodisturbTimeReqBean = new NodisturbTimeReqBean("REQ", "QTOYSP", SPUtils.getString(this, "phoneNum", ""), new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(this, "TOKEN", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(nodisturbTimeReqBean);
        Call<NodisturbTimeResBean> nodisturbTimeResBeanCall = allInterface.NODISTURB_TIME_RES_BEAN_CALL(s);
        nodisturbTimeResBeanCall.enqueue(new Callback<NodisturbTimeResBean>() {
            @Override
            public void onResponse(Call<NodisturbTimeResBean> call, Response<NodisturbTimeResBean> response) {
                List<NodisturbTimeResBean.BODYBean.LSTBean> lst = response.body().getBODY().getLST();
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                NoDisturbAdapter adapter = new NoDisturbAdapter(getApplicationContext(), lst);
                NewAdapter adapter = new NewAdapter(getApplicationContext(), R.layout.disturb_recycler_troggle, lst);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        showPopUpWindow();
                        ToastUtil.showToast(getApplication(), "点击了" + position);

                    }
                });
                adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                        ToastUtil.showToast(getApplicationContext(), "长按了" + position);
                        return true;
                    }
                });


            }

            @Override
            public void onFailure(Call<NodisturbTimeResBean> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), t.toString());
            }
        });
    }

    private void showPopUpWindow() {
        //展示wheelview 弹出wheelview
        //

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_button:
                ToastUtil.showToast(this, "34");
                break;
            case R.id.iv_add_nodisturbtime:
                ToastUtil.showToast(this, "56");
                break;
            default:
                break;
        }
    }
}
