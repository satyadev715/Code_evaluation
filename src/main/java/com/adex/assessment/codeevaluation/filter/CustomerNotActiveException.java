package com.adex.assessment.codeevaluation.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="CustomerId is Inactive")
public class CustomerNotActiveException  extends RuntimeException{
    public CustomerNotActiveException(String message) {
        super(message);
    }
}
