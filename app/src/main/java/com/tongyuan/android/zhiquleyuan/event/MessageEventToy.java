package com.tongyuan.android.zhiquleyuan.event;

import com.tongyuan.android.zhiquleyuan.bean.QueryToyResultBean;

import java.util.List;

/**
 * Created by android on 2017/3/17.
 */

public class MessageEventToy {
    public List<QueryToyResultBean.BODYBean.LSTBean> mQueryBabyListResults;

    public MessageEventToy(List<QueryToyResultBean.BODYBean.LSTBean> queryBabyListResults) {
        this.mQueryBabyListResults = queryBabyListResults;
    }

}
