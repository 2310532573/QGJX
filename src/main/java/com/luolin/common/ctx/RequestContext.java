package com.luolin.common.ctx;

import com.alibaba.fastjson.JSONObject;

public class RequestContext {
    private static ThreadLocal<SiteContext> siteContextThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<JSONObject> userContextThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<JSONObject> memberContextThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<RequestContent> requestContentThreadLocal = new InheritableThreadLocal<>();

    public static RequestContent getCurrentRequestContent() {
        return requestContentThreadLocal.get();
    }

    public static RequestContent sendRequest(JSONObject jsonObject) {
        return sendRequest(jsonObject, false);
    }

    public static RequestContent sendRequest(JSONObject jsonObject, Boolean clearToken) {
        final RequestContent requestContent = requestContentThreadLocal.get();

        RequestContent copy = new RequestContent();
        if(!clearToken) {
            copy.setToken(requestContent.getToken());
        }
        copy.setSiteCode(requestContent.getSiteCode());
        copy.setLastUpdateUser(requestContent.getLastUpdateUser());
        copy.setApiBase(requestContent.getApiBase());
        copy.setPageNumber(requestContent.getPageNumber());
        copy.setPageSize(requestContent.getPageSize());
//        List<String> excludeFields = new ArrayList<>();
//        excludeFields.add("paramData");
//        if(clearToken) {
//            excludeFields.add("token");
//        }
//        RequestContent copy = BeanConvertUtil.map(RequestContent.class, requestContent, null, excludeFields);
        copy.setParamData(jsonObject);
        return copy;
    }

    public static void setRequestContent(RequestContent requestContent) {
        RequestContext.requestContentThreadLocal.set(requestContent);
    }

    public static void clearThreadLocal() {
        RequestContext.memberContextThreadLocal.remove();
        RequestContext.userContextThreadLocal.remove();
        RequestContext.siteContextThreadLocal.remove();
        RequestContext.requestContentThreadLocal.remove();
    }

    public static void setSiteContext(SiteContext siteContext) {
        RequestContext.siteContextThreadLocal.set(siteContext);
    }

    public static SiteContext getSiteContext() {
        return siteContextThreadLocal.get();
    }

    public static String getSiteCode() {
        SiteContext siteContext = getSiteContext();
        if(siteContext != null) {
            return siteContext.getSiteCode();
        }
        return null;
    }

    public static JSONObject getUserContext() {
        return userContextThreadLocal.get();
    }

    public static void setUserContext(JSONObject userContext) {
        RequestContext.userContextThreadLocal.set(userContext);
    }

    public static JSONObject getMemberContext() {
        return memberContextThreadLocal.get();
    }

    public static void setMemberContext(JSONObject memberContext) {
        RequestContext.memberContextThreadLocal.set(memberContext);
    }
}
