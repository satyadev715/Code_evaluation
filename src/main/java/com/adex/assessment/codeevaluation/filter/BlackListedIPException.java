package com.adex.assessment.codeevaluation.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="\"RemoteIP is in blacklist")
public class BlackListedIPException extends RuntimeException {
    public BlackListedIPException(String message) {
        super(message);
    }
}
