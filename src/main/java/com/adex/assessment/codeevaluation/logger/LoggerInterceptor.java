package com.adex.assessment.codeevaluation.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);
}
