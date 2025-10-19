package com.sylvester.ecommerce.interceptor;

import com.sylvester.ecommerce.service.MetricService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@Slf4j
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private final MetricService metricService;
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        startTime.set(System.currentTimeMillis());
        log.info("Incoming request: {} {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        Long start = startTime.get();
        if (start == null) {
            return;
        }
        long duration = System.currentTimeMillis() - start;
        String path = request.getRequestURI();
        int status = response.getStatus();

        metricService.requestCountIncrement(path);
        metricService.trackApiLatency(path, duration);
        System.out.printf("⬅️ Completed %s %s with status %d in %d ms%n", request.getMethod(), path, status, duration);
        startTime.remove();
    }



}
