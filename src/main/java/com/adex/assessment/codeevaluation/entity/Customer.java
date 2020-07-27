package com.adex.assessment.codeevaluation.entity;


import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "active", nullable = false, length = 1)
    private Integer active;

    public Customer() {
    }

    public Customer(Integer id, String name, Integer active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
