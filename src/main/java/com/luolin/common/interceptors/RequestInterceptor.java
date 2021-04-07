package com.luolin.common.interceptors;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class RequestInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> contextHolder = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        contextHolder.set(System.currentTimeMillis());
        log.info(new StringBuilder().append("请求信息### - IP:").append(request.getRemoteAddr())
                .append(" <<<<<请求地址>>>>>:").append(request.getRequestURI()).append(" - 请求参数：")
                .append(JSON.toJSONString(request.getParameterMap())));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        long startTime = contextHolder.get();
        if (System.currentTimeMillis() - startTime >= 3000) {
            log.warn("afterCompletion请求超时，请求地址:"+request.getRequestURI()+"，处理时间:"+(System.currentTimeMillis()-startTime));
        }
    }
}
