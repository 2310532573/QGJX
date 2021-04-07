package com.luolin.service;

import com.luolin.framework.exception.TokenException;
import com.luolin.framework.redis.RedisUtil;
import com.luolin.utils.Status;
import com.luolin.utils.UserThreadLocal;
import com.luolin.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

    @Autowired
    private RedisUtil redisUtil;

    public UserData getUserData(){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        if(userData != null){
            return userData;
        }else{
            throw new TokenException(Status.TOKEN_ERROR.getMsg());
        }
    }
}
