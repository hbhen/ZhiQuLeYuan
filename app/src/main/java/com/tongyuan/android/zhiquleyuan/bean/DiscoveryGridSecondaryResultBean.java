package com.tongyuan.android.zhiquleyuan.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by DTC on 2017/3/24.
 */
public class DiscoveryGridSecondaryResultBean implements Serializable{

    public String PN;
    public String CNT;
    public String PS;
    public String NC;
    public List<LSTBean> LST;

    public static class LSTBean implements Serializable{

        public String TYPE;
        public String NAME;
        public String IMG;
        public String ID;
        public String COLNAME;
        public String SIZE;
        public String DUR;
        public String COLID;
        public String REMARK;
        public String TIMES;

    }
}
