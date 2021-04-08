package com.luolin.common.enums;

public enum ErrorCodeAndMsg {


    DATA_NULL_ERROR("data.data.101","查询数据失败"),

    DATA_ADD_ERROR("data.data.102","添加数据失败"),

    DATA_UPDATE_ERROR("data.data.103","更新数据失败"),

    DATA_UPDATE_STATUS_ERROR("data.data.104","修改状态失败"),

    DATA_DELETE_ERROR("data.data.105","删除数据失败"),

    NETWORK_ERROR("data.999","网络异常，请待会重试"),

    PARAM_TOKEN_NULL_ERROR("data.param.116","未找到token信息"),

    USER_AUTH_ERROR("font.auth.101","权限校验失败，请联系管理员")
    ;


    private String code;
    private String msg;

    ErrorCodeAndMsg(String code, String msg) {
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

    public String getCode(Object... param) {
        StringBuilder sb = new StringBuilder(this.code);
        for(Object o : param) {
            sb.append("&");
            if(o instanceof String) {
                sb.append(o);
            } else {
                sb.append(o);
            }
        }
        return sb.toString();
    }
}
