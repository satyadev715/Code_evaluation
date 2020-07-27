package com.adex.assessment.codeevaluation.entity;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="request_data")

public class RequestData {
    @Column(name = "request_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(name="request_payload", nullable=false, length = 1000)
    private String requestPayload;

    @Column(name="customer_id", nullable=false)
    @NotEmpty(message = "Please provide a Customer Id")
    private Integer customerId;

    @Column(name="request_time")
    private Timestamp requestTime;

    @Column(name="status")
    private Character status;

    public RequestData() {
    }

    public RequestData(Integer requestId, String requestPayload, @NotEmpty(message = "Please provide a Customer Id") Integer customerId,
                       Timestamp requestTime, Character status) {

        this.requestId = requestId;
        this.requestPayload = requestPayload;
        this.customerId = customerId;
        this.requestTime = requestTime;
        this.status = status;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
}
