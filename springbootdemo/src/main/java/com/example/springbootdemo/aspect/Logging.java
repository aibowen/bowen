/**
 *  文件名：Logging
 *  版权：Copyright 2017-2022 CMCC All Rights Reserved.
 *  描述：日志记录切面
 */
package com.example.springbootdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-16 11:08
 */
@Aspect
@Component
@Slf4j
public class Logging {
    @Pointcut("execution(* com.example.springbootdemo.web.TestController.*(..))")
    public void testPoint() {

    }

    @Before("testPoint()")
    public void before() {
        log.info("前置通知。。。");
    }
}
