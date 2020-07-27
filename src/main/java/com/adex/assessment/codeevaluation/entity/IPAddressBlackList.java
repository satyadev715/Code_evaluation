package com.adex.assessment.codeevaluation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ip_blacklist")
public class IPAddressBlackList {
    @Id
    @Column(name="ip")
    private Long ip;

    public IPAddressBlackList() {
    }

    public IPAddressBlackList(Long ip) {
        this.ip = ip;
    }

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }
}