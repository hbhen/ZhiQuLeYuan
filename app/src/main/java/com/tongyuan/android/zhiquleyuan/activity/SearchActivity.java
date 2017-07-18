package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.SearchReqBean;
import com.tongyuan.android.zhiquleyuan.bean.SearchResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.StatusBarUtils;
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
 * Created by Android on 2017/7/9.
 */
public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.et_activity_search)
    EditText mEtActivitySearch;
    @BindView(R.id.tv_activity_search)
    TextView mTvActivitySearch;
    @BindView(R.id.rl_activity_search)
    RecyclerView mRlActivitySearch;
    private static final String TAG = "searchactivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        StatusBarUtils.setStatusBarLightMode(this, getResources().getColor(R.color.main_top_bg));
        mEtActivitySearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    String text = mEtActivitySearch.getText().toString().trim();
                    getSearchContent(text);
                }
                return false;
            }
        });
    }

    private void getSearchContent(String text) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        SearchReqBean.BODYBean bodyBean = new SearchReqBean.BODYBean(text, "10", "1");
        SearchReqBean searchReqBean = new SearchReqBean("REQ", "SEARES", SPUtils.getString(this, "phoneNum", ""), new SimpleDateFormat
                ("yyyyMMddHHmmssSSS").format(new Date()), bodyBean, "", SPUtils.getString(this, "TOKEN", ""), "1");
        Gson gson = new Gson();
        String s = gson.toJson(searchReqBean);
        Call<SearchResBean> searchResBeanCall = allInterface.SEARCH_RES_BEAN_CALL(s);
        searchResBeanCall.enqueue(new Callback<SearchResBean>() {
            @Override
            public void onResponse(Call<SearchResBean> call, Response<SearchResBean> response) {
                if (response != null && response.body().getCODE().equals("0")) {
                    ToastUtil.showToast(getApplicationContext(), "chenggong sou suo" + response.body().getBODY().toString());
                }
            }

            @Override
            public void onFailure(Call<SearchResBean> call, Throwable t) {
                Log.e(TAG, "onFailure: searchactivity" + t.toString());
            }
        });


    }

    @OnClick({R.id.et_activity_search, R.id.tv_activity_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_activity_search:
                finish();
                break;
        }
    }


}
