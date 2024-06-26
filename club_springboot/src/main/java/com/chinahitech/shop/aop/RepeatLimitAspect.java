package com.chinahitech.shop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 限流注解的切面类
 */
@Slf4j
@Aspect
@Component
public class RepeatLimitAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


//    @Pointcut("@annotation(repeatLimit)")
//    public void pointCut(RepeatLimit repeatLimit) {
//    }

//    @Around("pointCut(repeatLimit)")

    @Around(value = "@annotation(repeatLimit)")
    public Object around(ProceedingJoinPoint pjp, RepeatLimit repeatLimit) throws Throwable {

        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();
        Assert.notNull(request, "request can not null");
        int lockSeconds = repeatLimit.lockTime();
        int lockNum = repeatLimit.lockNum();
        String serviceName = repeatLimit.serviceName();
        Boolean isLimit = limitByRedis(request.getMethod(), lockNum, lockSeconds, serviceName);
        if (isLimit) {
            throw new RuntimeException("当前服务繁忙，请稍后再试");
        }

        // 没有超过数量限制，则放行
        return pjp.proceed();

    }

    private Boolean limitByRedis(String method, int lockNum, int lockSeconds, String serviceName) {
        //通过lua脚本进行incr & 过期时间限制
        String script = "local cur = redis.call('incr',KEYS[1]); " +
                "local t = redis.call('ttl', KEYS[1]); " +
                "if t == -1 then redis.call('expire', KEYS[1], ARGV[1]) end; return tostring(cur)";
        DefaultRedisScript<Integer> redisScript = new DefaultRedisScript<Integer>(script, Integer.class);
        List<String> keyList = new ArrayList<>();
        String key = serviceName + ":" + method;
        keyList.add(key);
        Integer result = redisTemplate.execute(redisScript, keyList, lockSeconds);
        int execute = result == null ? 0 : result;
        if (execute < lockNum) {
            return false;
        }
        return true;
    }

}
