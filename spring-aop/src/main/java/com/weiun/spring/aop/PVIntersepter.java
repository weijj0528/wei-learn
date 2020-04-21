package com.weiun.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
public class PVIntersepter {

    @Around(value = "execution(* com.weiun.spring.aop.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println("你好");
        return new HashMap<>();
    }

}
