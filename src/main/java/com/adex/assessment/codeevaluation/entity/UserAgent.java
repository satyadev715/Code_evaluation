package com.adex.assessment.codeevaluation.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ua_blacklist")
public class UserAgent {
    @Column(name="ua")
    @Id
    private String ua;

    public UserAgent() {
    }

    public UserAgent(String ua) {
        this.ua = ua;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }
}
