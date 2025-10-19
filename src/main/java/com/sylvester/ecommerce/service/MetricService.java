package com.sylvester.ecommerce.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MetricService {

    private final MeterRegistry meterRegistry;

    public void requestCountIncrement(String endpoint) {
        Counter counter = meterRegistry.counter("Api_request_Count_total","endpoint", endpoint);
        counter.increment();
    }

    public void trackApiLatency(String endpoint, long duration) {
        Timer.builder("Api_latency")
                .tag("endpoint", endpoint)
                .publishPercentileHistogram()
                .register(meterRegistry)
                .record(duration, TimeUnit.MILLISECONDS);
    }

    public void statusCountIncrement(String endpoint, int status) {
        meterRegistry.counter("Api_status_total","endpoint", endpoint, "status", String.valueOf(status)).increment();
    }
}
