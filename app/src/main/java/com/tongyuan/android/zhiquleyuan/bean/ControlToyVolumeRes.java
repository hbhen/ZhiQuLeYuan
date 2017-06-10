package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/6/8.
 */
public class ControlToyVolumeRes {

    /**
     * code : 0
     * msg :
     */

    private String code;
    private String msg;

    @Override
    public String toString() {
        return "ControlToyVolumeRes{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public ControlToyVolumeRes(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
