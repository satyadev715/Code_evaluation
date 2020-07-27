package com.adex.assessment.codeevaluation.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="\"User Agent is in blacklist")
public class BlackListedUserAgentException extends RuntimeException {
    public BlackListedUserAgentException(String message) {
            super(message);
        }
}

