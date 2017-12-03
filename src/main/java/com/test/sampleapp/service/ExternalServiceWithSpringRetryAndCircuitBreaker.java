package com.test.sampleapp.service;

import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Created by MRomeh
 */
@Service
public class ExternalServiceWithSpringRetryAndCircuitBreaker {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceWithSpringRetryAndCircuitBreaker.class);

    /* example of circuit breaker with spring retry which will retry to call the server 2 times in case of error
      and for example exclude Timeout exception from retry conditions and go to recover directly
     */
    @CircuitBreaker(maxAttempts = 2, openTimeout = 5000l, resetTimeout = 10000l, exclude = TimeoutException.class)
    public void sendEmail() {
        // add your external service call here so it can be protected by Spring rety and CircuitBreaker logic

    }

    /**
     * The recover method needs to have same return type and parameters which will be called in case the circuit is closed or retrials are over
     * so this the fallback logic
     *
     * @return
     */
    @Recover
    private void fallbackForCall() {
        log.error("Fallback for external service call invoked, the external service is NOT reachable");
    }


}
