package com.tongyuan.android.zhiquleyuan.event;

import com.tongyuan.android.zhiquleyuan.bean.AddToyResultBean;

import retrofit2.Response;

/**
 * Created by android on 2017/3/21.
 */

public class AddToyMessageEvent {
    public Response<AddToyResultBean> mAddToyResultBeanResponse;
    public String img;
    public String ownerId;

    public AddToyMessageEvent(Response<AddToyResultBean> addToyResultBeanResponse) {
        this.mAddToyResultBeanResponse = addToyResultBeanResponse;

    }
//    public AddToyMessageEvent(Response<AddToyResultBean> addToyResultBeanResponse,String img) {
//        this.mAddToyResultBeanResponse=addToyResultBeanResponse;
//        this.img=img;
//    }
//    public AddToyMessageEvent(Response<AddToyResultBean> addToyResultBeanResponse,String img,String ownerId){
//        this.mAddToyResultBeanResponse=addToyResultBeanResponse;
//        this.img=img;
//        this.ownerId=ownerId;
//    }
}
