package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;

/**
 * Created by android on 2017/3/3.
 */
public class MyQueryBabyListResult extends QueryBabyListResult {

    public MyQueryBabyListResult(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
        super(TYPE, CMD, ACCT, TIME, BODY, VERI, TOKEN, SEQ, CODE, MSG);
    }

    protected MyQueryBabyListResult(Parcel in) {
        super(in);
    }

    public String checkState;

    public String getCheckState() {
        return this.checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }
}
