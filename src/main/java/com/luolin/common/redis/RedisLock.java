package com.luolin.common.redis;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
@Log4j2
public class RedisLock {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ValueOperations<String, String> valueOps;

    /**
     * 重试时间
     */
    private final static int RETRY_MILLIS = 100;

    /**
     * 获取锁
     *
     * @param cid
     * @param expireStr
     * @return 获取锁成功返回ture，超时返回false
     * @throws Exception
     */
    public boolean lock(String cid, String userId, String expireStr) {
        String lockKey = cid + RedisKey.KEY_SPLIT + RedisKey.LOCK_KEY + RedisKey.KEY_SPLIT + userId;
        while (true) {
            try {
                if (valueOps.setIfAbsent(lockKey, expireStr)) {
                    return true;
                }
                //redis里key的时间
                String currentValue = valueOps.get(lockKey);
                //判断锁是否已经过期，过期则重新设置并获取
                if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
                    //设置锁并返回旧值
                    String oldValue = valueOps.getAndSet(lockKey, expireStr);
                    //比较锁的时间，如果不一致则可能是其他锁已经修改了值并获取
                    if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                        return true;
                    }
                }
                //延时
                try {
                    Thread.sleep(RETRY_MILLIS);
                } catch (InterruptedException e) {
                    log.error("【redis分布式锁】sleep异常,lockKey:" + lockKey + ",error:" + e.getMessage(), e);
                    //再次更新中断标志位为true
                    Thread.currentThread().interrupt();
                }
            } catch (Exception e) {
                log.error("【redis分布式锁】加锁异常,lockKey:" + lockKey + ",error:" + e.getMessage());
            }
        }
    }

    /**
     * 释放获取到的锁
     *
     * @param cid
     * @param userId
     * @param value
     */
    public void unlock(String cid, String userId, String value) {
        String lockKey = cid + RedisKey.KEY_SPLIT + RedisKey.LOCK_KEY + RedisKey.KEY_SPLIT + userId;
        try {
            String currentValue = valueOps.get(lockKey);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.delete(lockKey);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常:" + e.getMessage(), e);
        }
    }
}