package com.adex.assessment.codeevaluation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Pattern;
import java.net.InetAddress;

public class RequestDTO {
    private static Logger log = LoggerFactory.getLogger(RequestDTO.class);

    @JsonProperty("customerID")
    private Integer customerID;

    @JsonProperty("tagID")
    private Integer tagID;

    @JsonProperty("userID")
    private String userID;

    @JsonProperty("remoteIP")
    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private InetAddress remoteIP;

    @JsonProperty("timestamp")
    private Long timestamp;

    public RequestDTO() {
    }

    public RequestDTO(Integer customerID, Integer tagID, String userID, InetAddress remoteIP, Long timestamp) {
        this.customerID = customerID;
        this.tagID = tagID;
        this.userID = userID;
        this.remoteIP = remoteIP;
        this.timestamp = timestamp;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getTagId() {
        return tagID;
    }

    public void setTagId(Integer tagId) {
        this.tagID = tagId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public InetAddress getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(InetAddress remoteIP) {
        this.remoteIP = remoteIP;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "RequestBean{" +
                "customerID=" + customerID +
                ", tagID='" + tagID + '\'' +
                ", userID='" + userID + '\'' +
                ", remoteIP='" + remoteIP + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
