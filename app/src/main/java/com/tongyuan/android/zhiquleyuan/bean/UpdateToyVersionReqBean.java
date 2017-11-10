package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/1011:40.
 */
public class UpdateToyVersionReqBean {
    @Override
    public String toString() {
        return "UpdateToyVersionReqBean{" +
                "cmd='" + cmd + '\'' +
                ", param=" + param +
                ", token='" + token + '\'' +
                '}';
    }

    public UpdateToyVersionReqBean(String cmd, ParamBean param, String token) {
        this.cmd = cmd;
        this.param = param;
        this.token = token;
    }

    /**
     * cmd : update
     * param : {"toyid":"1"}
     * token : 2
     */

    private String cmd;
    private ParamBean param;
    private String token;

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
         */

        private String toyid;

        @Override
        public String toString() {
            return "ParamBean{" +
                    "toyid='" + toyid + '\'' +
                    '}';
        }

        public ParamBean(String toyid) {
            this.toyid = toyid;
        }

        public String getToyid() {
            return toyid;
        }

        public void setToyid(String toyid) {
            this.toyid = toyid;
        }
    }
}
