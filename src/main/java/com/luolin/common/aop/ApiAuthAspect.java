package com.luolin.common.aop;

import com.luolin.common.annotation.ApiAuth;
import com.luolin.common.enums.EnumApi;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
public class ApiAuthAspect {

    @Around("@annotation(com.luolin.common.annotation.ApiAuth)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        Class<?> className = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        Method method = className.getMethod(methodName, argClass);

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = null;
        if (sra != null) {
            request = sra.getRequest();
        }
        if (method.isAnnotationPresent(ApiAuth.class)) {
            ApiAuth auth = method.getAnnotation(ApiAuth.class);

            if(auth.ctxLevel() == EnumApi.CtxLevel.后台登录){

            }
        }
        return point.proceed();
    }


}
