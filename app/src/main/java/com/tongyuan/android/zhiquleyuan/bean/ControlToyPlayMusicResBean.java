package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/6/10.
 */

public class ControlToyPlayMusicResBean {
    /**
     * code : 0
     * msg :
     */

    private String code;
    private String msg;

    @Override
    public String toString() {
        return "ControlToyPlayMusicResBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public ControlToyPlayMusicResBean(String code, String msg) {
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
