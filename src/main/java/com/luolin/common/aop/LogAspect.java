package com.luolin.common.aop;


import com.luolin.common.annotation.LogAnnotation;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(-1)
@Log4j2
public class LogAspect {


    //定义切点
    @Pointcut("@annotation(com.luolin.common.annotation.LogAnnotation)")
    public void logAspect(){
    }

    @AfterReturning("logAspect()")
    public void getControllerLog(JoinPoint joinPoint){
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        String remark = "";
        LogAnnotation controllerLogAnnotation = method.getAnnotation(LogAnnotation.class);
        if (controllerLogAnnotation != null) {
            remark = controllerLogAnnotation.remark();
        }
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        log.info("###ControllerLogAnnotation - 请求类：" + className + " - 请求方法:" + methodName + " - 请求参数：" + args + " - 功能：" + remark +" ###");
    }

}
