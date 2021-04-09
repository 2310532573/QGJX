package com.luolin.common.enums;

public enum ErrorCodeEnum {


    DATA_NULL_ERROR("101","查询数据失败"),

    DATA_ADD_ERROR("102","添加数据失败"),

    DATA_UPDATE_ERROR("103","更新数据失败"),

    DATA_UPDATE_STATUS_ERROR("104","修改状态失败"),

    DATA_DELETE_ERROR("105","删除数据失败"),

    NETWORK_ERROR("999","网络异常，请待会重试"),

    PARAM_TOKEN_NULL_ERROR("888","未找到token信息"),

    USER_AUTH_ERROR("9999","权限校验失败，请联系管理员"),

    GOOGLE_CLOUD_STORAGE_UPLOAD_ERROR("1099","google cloud storage上传失败"),

    GOOGLE_CLOUD_STORAGE_ERROR("1404","google cloud storage异常"),

    GOOGLE_CLOUD_STORAGE_DOWNLOAD_ERROR("1504","google cloud storage下载错误"),

    GOOGLE_CLOUD_STORAGE_DELETE_ERROR("1999","google cloud storage删除失败"),

    GOOGLE_CLOUD_STORAGE_NULL("1404","google cloud storage对象资源为空"),
    ;


    private String code;
    private String msg;

    ErrorCodeEnum(String code, String msg) {
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
