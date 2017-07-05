package com.tongyuan.android.zhiquleyuan.bean;

/**
 *
 * Created by DTC on 2017/3/24.
 */

public class DiscoverySubReqBean {

    /**
     * 构造
     * @param COLID 栏目ID
     * @param PS    page size   一页有多少
     * @param PN    page number 第几页
     */
    public DiscoverySubReqBean(String COLID, String PS, String PN) {
        this.COLID = COLID;
        this.PS = PS;
        this.PN = PN;
    }

    public String COLID;
    public String PS;
    public String PN;
}
