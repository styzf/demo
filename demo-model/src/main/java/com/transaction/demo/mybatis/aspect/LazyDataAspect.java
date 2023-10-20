package com.transaction.demo.mybatis.aspect;

import com.transaction.annotation.LazyData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author styzf
 * @date 2023/2/8 22:48
 */
@Aspect
@Component
@Order(0)
public class LazyDataAspect {
    
    @Around("@annotation(com.transaction.annotation.LazyData)")
    public Object before(JoinPoint joinPoint, Object object, RuntimeException runtimeException) {
        return object;
    }
}
