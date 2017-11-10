package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/1011:41.
 */
public class UpdateToyVersionResBean {

    @Override
    public String toString() {
        return "UpdateToyVersionResBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public UpdateToyVersionResBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * code : -10009
     * msg : 玩具未登录，未取到玩具DEVCODE
     */

    private String code;
    private String msg;

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
