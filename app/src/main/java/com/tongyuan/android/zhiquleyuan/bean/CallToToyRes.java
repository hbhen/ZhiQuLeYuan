package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/5/8.
 */
public class CallToToyRes {
    /**
     * roomid : 149762913
     * code : 0
     * msg :
     */

    private String roomid;
    private String code;
    private String msg;

    @Override
    public String toString() {
        return "CallToToyRes{" +
                "roomid='" + roomid + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public CallToToyRes(String roomid, String code, String msg) {
        this.roomid = roomid;
        this.code = code;
        this.msg = msg;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
//{"code":"-10009","msg":"玩具未登录，未取到玩具DEVCODE"}toyid错误的时候返回的数据
//{"roomid":"666666","code":"0","msg":""}method为2的时候
//{"code":"-10006","msg":"用户未登录"}token不对返回的错误