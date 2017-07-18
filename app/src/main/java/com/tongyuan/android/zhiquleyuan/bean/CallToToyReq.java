package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/5/8.
 */

public class CallToToyReq {

    /**
     * cmd : contact_toy
     * param : {"toyid":"1","method":"1","room":"666666"}
     * token : xxx
     */

    private String cmd;
    private ParamBean param;
    private String token;

    @Override
    public String toString() {
        return "CallToToyReq{" +
                "cmd='" + cmd + '\'' +
                ", param=" + param +
                ", token='" + token + '\'' +
                '}';
    }

    public CallToToyReq(String cmd, ParamBean param, String token) {
        this.cmd = cmd;
        this.param = param;
        this.token = token;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class ParamBean {
        /**
         * toyid : 1
         * method : 1
         * room : 666666
         */

        private String toyid;
        private String method;
        private String room;
        private String thirdid;


        @Override
        public String toString() {
            return "ParamBean{" +
                    "toyid='" + toyid + '\'' +
                    ", method='" + method + '\'' +
                    ", room='" + room + '\'' +
                    ", thirdid='" + thirdid + '\'' +
                    '}';
        }

        public ParamBean(String toyid, String method, String room, String thirdid) {
            this.toyid = toyid;
            this.method = method;
            this.room = room;
            this.thirdid = thirdid;
        }
    }
}
