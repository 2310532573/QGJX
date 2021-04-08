package com.luolin.common.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 初始化权限，反射获取所有接口
 */
@Component
public class InitAuth implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
