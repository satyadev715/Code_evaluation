package com.adex.assessment.codeevaluation.dao;

import com.adex.assessment.codeevaluation.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
