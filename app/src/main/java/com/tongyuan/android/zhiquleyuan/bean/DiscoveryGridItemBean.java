package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 *
 * Created by zgg on 2017/6/4.
 */

public class DiscoveryGridItemBean {
    public String PN;
    public String NC;
    public String CNT;
    public String PS;
    public List<LSTBean> LST;

    public static class LSTBean {
        public String IMG;
        public String ID;
        public String PID;
        public String NAME;
    }
}
