package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.libra.sinvoice.LogHelper;
import com.libra.sinvoice.SinVoicePlayer;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android on 2017/7/6.
 */

public class SetUpWlanActivityStepTwo extends AppCompatActivity implements SinVoicePlayer.Listener {

    private final static String TAG = "MainActivity";
    private final static int MAX_NUMBER = 5;
    private final static int MSG_SET_RECG_TEXT = 1;
    private final static int MSG_RECG_START = 2;
    private final static int MSG_RECG_END = 3;

    private final static String CODEBOOK = "0123456789￥$";
    @BindView(R.id.iv_back)
    LinearLayout mIvBack;
    @BindView(R.id.tb_netconfig_prepare)
    RelativeLayout mTbNetconfigPrepare;
    @BindView(R.id.iv_netconfig_prepare)
    ImageView mIvNetconfigPrepare;
    @BindView(R.id.bt_sendvoice)
    Button mBtSendvoice;
    @BindView(R.id.tv_netconfig_retry2_sendvoice)
    TextView mTvNetconfigRetry;


    private List<String> arrayList = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "_", "!", "b", "c", "d", "e", "f", "g", "h", ".",
            "i", "。", "j", "k", "l", "m", "n", "o", "p", "~", "q", "r", ";", "s", "t", "u", "v", "w", "x", "`", "y", "z", "A", ":", "B", "C", "D",
            "E", "F", "<", "G", "H", "I", "J", ">", "K", "L", "M", "N", "?", "O", "P", "Q", "R", "S", ",", "T", "U", "V", "'", "W", "X", "Y", "Z",
            "0", "|", "77", "a", "@", "#", "%", "^", "&", "*", "(", ")", "-", "+", "9", "{", "1", "2", "3", "4", "5", "6", "7", "8", "}", "", "",
            "", "", "", "", "");

    private SinVoicePlayer mSinVoicePlayer;
    private String mWlanname;
    private String mWlansecret;
    private String mReturnStr;
    private String mString2;
    private StringBuilder mAppend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netconfig_send);
        ButterKnife.bind(this);

        mWlanname = SPUtils.getString(this, "wlanname", "");
        mWlansecret = SPUtils.getString(this, "wlansecret", "");
        mSinVoicePlayer = new SinVoicePlayer(CODEBOOK);
        mSinVoicePlayer.setListener(this);
    }

    @OnClick({R.id.iv_back, R.id.bt_sendvoice, R.id.tv_netconfig_retry2_sendvoice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_sendvoice:
                try {

                    String wlanName = URLEncoder.encode(mWlanname, "UTF-8");
                    String wlanSecret = URLEncoder.encode(mWlansecret, "UTF-8");
                    String encode = wlanName + "|" + wlanSecret;
                    String returnStr = returnStr(encode);
                    mSinVoicePlayer.play(returnStr);
                    LogUtil.i(TAG, "onViewClicked: returnStr" + returnStr);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_netconfig_retry2_sendvoice:

                break;

        }
    }

    @Override
    public void onPlayStart() {
        LogUtil.d(TAG, "start play");
    }

    @Override
    public void onPlayEnd() {
        Intent intent = new Intent();
        intent.setClass(this, SetUpWlanActivityStepThree.class);
        startActivity(intent);
        LogHelper.d(TAG, "stop play");
    }


    private String returnStr(String str) {
        String tempY = "-2";
        String tempLength = "";
        for (int i = 0; i < str.length(); i++) {
            String s = str.substring(i, i + 1);
//	 char  item =  str.charAt(i);
            int m = arrayList.indexOf(s);
//	 if (m<10){
//	 tempLength = tempLength + "0";
//	 }
            String tm = m + "";
            if (tm.equals("-1")) {
                Toast.makeText(this, s + "不符合规范", Toast.LENGTH_SHORT).show();
                break;
            } else if (tm.length() < 2) {
                tm = "0" + tm;
            }
//            tempLength = tempLength+tm;

            //获取第一位和第二位

            String index1 = tm.substring(0, 1);
            String index2 = tm.substring(1, 2);
            if (index1.equals(tempY)) {
                tempLength = tempLength + "￥";
            } else {
                tempLength = tempLength + index1;
            }
            if (index2.equals(index1)) {
                tempLength = tempLength + "$";
            } else {
                tempLength = tempLength + index2;
            }
            tempY = index2;

        }
        return tempLength;

    }
}
