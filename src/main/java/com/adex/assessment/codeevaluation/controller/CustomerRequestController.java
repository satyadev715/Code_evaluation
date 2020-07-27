package com.adex.assessment.codeevaluation.controller;

import com.adex.assessment.codeevaluation.entity.Customer;
import com.adex.assessment.codeevaluation.entity.CustomerRequestStatistics;
import com.adex.assessment.codeevaluation.entity.RequestDTO;
import com.adex.assessment.codeevaluation.filter.BlackListedIPException;
import com.adex.assessment.codeevaluation.filter.BlackListedUserAgentException;
import com.adex.assessment.codeevaluation.filter.CustomerNotActiveException;
import com.adex.assessment.codeevaluation.service.CustomerService;
import com.adex.assessment.codeevaluation.service.RequestDataService;
import com.adex.assessment.codeevaluation.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.net.UnknownHostException;
import java.time.LocalDate;

@RestController
@RequestMapping("/customer")
public class CustomerRequestController {
    private static Logger log = LoggerFactory.getLogger(CustomerRequestController.class);

    private CustomerService customerService;
    private RequestDataService requestDataService;
    private ValidationService validationService;

    @Autowired
    public CustomerRequestController(CustomerService customerService, RequestDataService requestDataService, ValidationService validationService) {
        this.customerService = customerService;
        this.requestDataService = requestDataService;
        this.validationService = validationService;
    }

    @PostMapping(value="/request", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<Object> processRequest(@Valid @RequestBody RequestDTO requestDTO, WebRequest request) throws UnknownHostException {

        //get existing customer
        Customer customer = customerService.getCustomer(requestDTO.getCustomerID());

        //check customerId is active or not
         if(customer.getActive() != 1) {
            log.debug("CustomerId is Inactive : "+ requestDTO.getCustomerID());
            requestDataService.saveRequest(requestDTO,'N');
           throw new CustomerNotActiveException("CustomerId is Inactive :"+ requestDTO.getCustomerID());
        }
         //get the User Agent from the request
        String userAgent = request.getHeader("User-Agent");

         //validate the request's User-Agent
        if(validationService.validateUserAgent(userAgent)){
            log.debug("UserAgent is in blacklist : "+userAgent);
            requestDataService.saveRequest(requestDTO,'N');
            throw new BlackListedUserAgentException( "UserAgent is in blacklist :"+userAgent);
        }
         String remoteIp = requestDTO.getRemoteIP().getHostName();

        //validate the remoteIp
        if(validationService.validateRemoteIp(remoteIp)){
            log.debug("RemoteIp is in blacklist : "+remoteIp);
            requestDataService.saveRequest(requestDTO,'N');
            throw new BlackListedIPException("RemoteIp is in blacklist :"+remoteIp);
        }

        //save the request
        Integer requestId = requestDataService.saveRequest(requestDTO,'Y');
        log.info("request data saved successfully : "+requestId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Request processed successfully : "+requestId);
    }

    @GetMapping(value="/statistics",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerRequestStatistics getCustomerRequestStats(@RequestParam(name = "id") Integer customerId,
                                                                             @RequestParam(name ="requestDate") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate requestDate)
    {
        //get existing customer
        Customer customer = customerService.getCustomer(customerId);

        //get customer's request count for a specific date
        long requestsCount = requestDataService.getCountOfRequests(customerId,requestDate);

        log.info("requestsCount :"+requestsCount);

        return new CustomerRequestStatistics(customerId,requestDate,requestsCount);
    }
}

