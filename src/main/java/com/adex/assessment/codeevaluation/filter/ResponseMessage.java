package com.adex.assessment.codeevaluation.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ResponseMessage {
    private static Logger log = LoggerFactory.getLogger(ResponseMessage.class);
    private Date timestamp;
    private String message;
    private String details;

    public ResponseMessage() {
    }

    public ResponseMessage(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
