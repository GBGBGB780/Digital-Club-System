package com.chinahitech.shop.aop;

import java.lang.annotation.*;

/**
 * 限流注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatLimit {

    /**
     * 设置请求锁定时间,默认为 10s
     *
     * @return 请求锁定时间
     */
    int lockTime() default 10;

    /**
     * 时间内请求数，默认为 100
     *
     * @return 请求数
     */
    int lockNum() default 100;

    /**
     * 当前服务名
     *
     * @return 服务名
     */
    String serviceName() default "admin";

}

