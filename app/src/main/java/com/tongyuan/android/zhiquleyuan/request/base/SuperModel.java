package com.tongyuan.android.zhiquleyuan.request.base;


/**
 * 返回数据的基类
 * Created by zgg on 2017/6/4.
 */

public class SuperModel<T> {

    public String TYPE;
    public String CMD;
    public String ACCT;
    public String TIME;
    public T BODY;
    public String VERI;
    public String TOKEN;
    public String SEQ;
    public String CODE;
    public String MSG;
}
