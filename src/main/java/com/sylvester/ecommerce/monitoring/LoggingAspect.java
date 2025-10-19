package com.sylvester.ecommerce.monitoring;

import com.sylvester.ecommerce.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final MetricService metricService;

    @Around("execution(* com.sylvester.ecommerce.controller..*(..))")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        String endpoint = joinPoint.getSignature().getName();
        metricService.requestCountIncrement(endpoint);
        metricService.trackApiLatency(endpoint, duration);
        return result;

    }


}
