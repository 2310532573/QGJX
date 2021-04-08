package com.luolin.common.enums;

public class EnumApi {
    public enum ApiMode {
        R, W, RW
    }

    public enum ApiScope {
        PC, IOS, APP, WEB, OTHERS;

        public static ApiScope getScope(String device) {
            if (device == null) {
                return null;
            }
            if (device.equalsIgnoreCase(EnumApi.ApiScope.PC.name())) {
                return EnumApi.ApiScope.PC;
            } else if (device.equalsIgnoreCase(EnumApi.ApiScope.WEB.name())) {
                return EnumApi.ApiScope.WEB;
            } else if (device.equalsIgnoreCase(EnumApi.ApiScope.APP.name())) {
                return EnumApi.ApiScope.APP;
            } else {
                throw new IllegalArgumentException("不支持此设备");
            }
        }
    }

    public enum ApiType {
        公有,权限,白名单,黑名单
    }

    public enum ApiLevel {
        公共,敏感
    }

    public enum CtxLevel {
        无限制,学生登录,后台登录,企业登陆
    }
}
