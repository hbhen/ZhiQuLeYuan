package com.tongyuan.android.zhiquleyuan.request.base;

import android.content.Context;

import com.tongyuan.android.zhiquleyuan.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Created by zgg on 2017/6/4.
 */

public class BaseRequest<T> {

    public String TYPE ;
    public String CMD  ;
    public String ACCT;
    public String TIME;
    public T BODY;
    public String VERI;
    public String TOKEN;
    public String SEQ;

    public BaseRequest(Context context, T body) {
        TYPE  = "RES";
        CMD   = "QRYCOL";
        ACCT  = SPUtils.getString(context, "phoneNum", "");
        TIME  = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        BODY  = body;
        VERI  = "";
        TOKEN = SPUtils.getString(context, "TOKEN", "");
        SEQ   = "1";
    }

    public BaseRequest(Context context, T body, String cmd) {
        this(context, body);
        this.CMD = cmd;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "TYPE='" + TYPE + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", VERI='" + VERI + '\'' +
                ", TOKEN='" + TOKEN + '\'' +
                ", SEQ='" + SEQ + '\'' +
                '}';
    }

}
