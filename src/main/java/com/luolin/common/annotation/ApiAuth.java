package com.luolin.common.annotation;

import com.luolin.common.enums.EnumApi;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiAuth {

    String apiName();

    String apiPath();

    EnumApi.ApiLevel apiLevel();

    EnumApi.ApiType[] apiType() default {EnumApi.ApiType.公有};

    EnumApi.ApiScope scope();

    EnumApi.ApiMode apiMode();

    /**
     * 登录校验
     * @return
     */
    EnumApi.CtxLevel ctxLevel() default EnumApi.CtxLevel.无限制;

    /**
     * 开发者名称
     * @return
     */
    String developer();

    /**
     * 版本号
     * @return
     */
    int ver() default 1;

    /**
     * 是否覆盖参数内SiteCode
     * @return
     */
    boolean coverSiteCode() default true;
}
