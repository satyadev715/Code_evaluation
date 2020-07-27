package com.adex.assessment.codeevaluation.service;

import com.adex.assessment.codeevaluation.controller.CustomerRequestController;
import com.adex.assessment.codeevaluation.dao.CustomerRepository;
import com.adex.assessment.codeevaluation.entity.Customer;
import com.adex.assessment.codeevaluation.filter.CustomerNotActiveException;
import com.adex.assessment.codeevaluation.filter.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static Logger log = LoggerFactory.getLogger(CustomerRequestController.class);
    private CustomerRepository customerRepository;

    public CustomerService() {
        log.info("CustomerService() called");
    }

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        log.info("CustomerService(CustomerRepository customerRepository) called");
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer getCustomer(Integer id) {
        Optional<Customer>  optionalCustomer = this.customerRepository.findById(id);
        Customer customer =null;
        if(optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
        }else{
            throw new CustomerNotFoundException("Customer Id not found :"+id);
        }
       return customer;
    }

}
