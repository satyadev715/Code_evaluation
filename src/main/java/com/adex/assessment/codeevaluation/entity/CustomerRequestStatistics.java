package com.adex.assessment.codeevaluation.entity;

import java.time.LocalDate;
import java.util.Date;

public class CustomerRequestStatistics {
    private Integer customer_id;
    private LocalDate request_date;
    private Long requestCount;

    public CustomerRequestStatistics() {
    }

    public CustomerRequestStatistics(Integer customerId, LocalDate requestDate, Long requestCount) {
        this.customer_id = customerId;
        this.request_date = requestDate;
        this.requestCount = requestCount;
    }

    public Integer getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Integer customerId) {
        this.customer_id = customerId;
    }

    public LocalDate getRequestDate() {
        return request_date;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.request_date = requestDate;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }
}
