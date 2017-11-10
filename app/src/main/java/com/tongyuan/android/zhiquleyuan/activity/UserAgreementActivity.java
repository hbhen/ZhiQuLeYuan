package com.tongyuan.android.zhiquleyuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.interf.Constant;

/**
 * Created by DTC on 2017/11/716:09.
 */
public class UserAgreementActivity extends AppCompatActivity {

    private TextView mAgreementInfo;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useragreement_activity);
//        mAgreementInfo = (TextView) findViewById(R.id.agreementinfo);
        mWebView = (WebView) findViewById(R.id.webView);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(false);
        mWebView.loadUrl(Constant.webViewUrl);
        mWebView.setWebViewClient(new WebViewClient());

    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
