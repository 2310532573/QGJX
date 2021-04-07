package com.luolin.framework.role;

import com.luolin.framework.exception.AuthException;
import com.luolin.framework.redis.RedisUtil;
import com.luolin.utils.Status;
import com.luolin.utils.UserThreadLocal;
import com.luolin.vo.UserData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class RoleAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut
    public void pointCut(){

    }

    @Before("execution(* com.luolin.controller.*Controller.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //通过方法获取注解
        RequiresRoles annotation = method.getAnnotation(RequiresRoles.class);
        if(annotation != null){
            String token = UserThreadLocal.get();
            UserData userData = (UserData) redisUtil.get(token);
            if(userData != null){
                if(!String.valueOf(userData.getType()).equals(String.valueOf(annotation.type().getCode()))){
                    throw new AuthException(Status.NO_AUTH.getMsg());
                }
            }else{
                throw new AuthException(Status.NO_AUTH.getMsg());
            }
        }
    }
}
