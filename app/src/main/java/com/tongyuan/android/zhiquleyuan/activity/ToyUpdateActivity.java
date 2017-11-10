package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.UpdateToyVersionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.UpdateToyVersionResBean;
import com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DTC on 2017/11/1011:26.
 */
public class ToyUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "updatetoy";
    private String mToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toyupdate_activity);
        mToken = SPUtils.getString(this, "token", "");
        Button updateVersion = (Button) findViewById(R.id.tv_updatetoy);
        Button checkVersion = (Button) findViewById(R.id.tv_checktoyversion);
        updateVersion.setOnClickListener(this);
        checkVersion.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_checktoyversion:
                getToyVersion();
                break;
            case R.id.tv_updatetoy:

                break;
            default:
                break;
        }
    }

    private void getToyVersion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        UpdateToyVersionReqBean.ParamBean paramBean = new UpdateToyVersionReqBean.ParamBean(ToySelectorFragment.mToyId);
        UpdateToyVersionReqBean updateToyVersionReqBean = new UpdateToyVersionReqBean("update", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(updateToyVersionReqBean);
        Call<UpdateToyVersionResBean> updateToyVersionResBeanCall = allInterface.UPDATE_TOY_VERSION_RES_BEAN_CALL(s);
        updateToyVersionResBeanCall.enqueue(new Callback<UpdateToyVersionResBean>() {
            @Override
            public void onResponse(Call<UpdateToyVersionResBean> call, Response<UpdateToyVersionResBean> response) {
                Log.i(TAG, "(updatetoy)onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<UpdateToyVersionResBean> call, Throwable t) {
                Log.i(TAG, "(updatetoy)onFailure: " + t);
            }
        });
    }
}
