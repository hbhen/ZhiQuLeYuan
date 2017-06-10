package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/6/8.
 */

public class ControlToyVolumeReq {

    /**
     * cmd : volume
     * param : {"toyid":"1","value":"10"}
     * token : xxx
     */

    private String cmd;
    private ParamBean param;
    private String token;

    @Override
    public String toString() {
        return "ControlToyVolumeReq{" +
                "cmd='" + cmd + '\'' +
                ", param=" + param +
                ", token='" + token + '\'' +
                '}';
    }

    public ControlToyVolumeReq(String cmd, ParamBean param, String token) {
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
         * value : 10
         */

        private String toyid;
        private String value;

        @Override
        public String toString() {
            return "ParamBean{" +
                    "toyid='" + toyid + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

        public ParamBean(String toyid, String value) {
            this.toyid = toyid;
            this.value = value;
        }

        public String getToyid() {
            return toyid;
        }

        public void setToyid(String toyid) {
            this.toyid = toyid;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
