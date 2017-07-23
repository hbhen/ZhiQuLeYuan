package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.CommitSuggestionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.CommitSuggestionResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2016/12/23.
 */

public class MySuggestionActivity extends AppCompatActivity {

    @BindView(R.id.iv_suggestion_back)
    ImageView mIvSuggestionBack;
    @BindView(R.id.tb_suggestion)
    RelativeLayout mTbSuggestion;
    @BindView(R.id.et_suggestion)
    EditText mEtSuggestion;
    @BindView(R.id.bt_activity_suggestion)
    Button mBtActivitySuggestion;
    private String mSuggestion;
    private static final String TAG = "suggestionactivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.iv_suggestion_back, R.id.tb_suggestion, R.id.et_suggestion, R.id.bt_activity_suggestion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_suggestion_back:
                finish();
                break;
            case R.id.et_suggestion:
                break;
            case R.id.bt_activity_suggestion:
                mSuggestion = mEtSuggestion.getText().toString().trim();
                if (!TextUtils.isEmpty(mSuggestion)) {
                    commitSuggestion();

                } else {
                    ToastUtil.showToast(this, "无意见");
                }
                break;
            default:
                break;
        }
    }

    private void commitSuggestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommitSuggestionReqBean.BODYBean bodyBean = new CommitSuggestionReqBean.BODYBean(mSuggestion);
        CommitSuggestionReqBean commitSuggestionReqBean = new CommitSuggestionReqBean("REQ", "FEEDBACK", SPUtils.getString(this, "phoneNum", ""), new
                SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(this, "TOKEN", ""), "1");
        AllInterface allInterface = retrofit.create(AllInterface.class);
        Gson gson = new Gson();
        String s = gson.toJson(commitSuggestionReqBean);
        Call<CommitSuggestionResBean> commitSuggestionResBeanCall = allInterface.COMMIT_SUGGESTION_RES_BEAN_CALL(s);
        commitSuggestionResBeanCall.enqueue(new Callback<CommitSuggestionResBean>() {
            @Override
            public void onResponse(Call<CommitSuggestionResBean> call, Response<CommitSuggestionResBean> response) {
                if (response != null) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<CommitSuggestionResBean> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

    }

}
