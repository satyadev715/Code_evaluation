package com.adex.assessment.codeevaluation.service;

import com.adex.assessment.codeevaluation.dao.RequestDataRepository;
import com.adex.assessment.codeevaluation.entity.RequestDTO;
import com.adex.assessment.codeevaluation.entity.RequestData;
import com.adex.assessment.codeevaluation.utility.ApplicationUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;

@Service
public class RequestDataService {
    private static Logger log = LoggerFactory.getLogger(RequestDataService.class);

    private RequestDataRepository requestDataRepository;

    public RequestDataService() {
    }

    @Autowired
    public RequestDataService(RequestDataRepository requestDataRepository) {
        this.requestDataRepository = requestDataRepository;
    }

    public Integer saveRequest(RequestDTO requestDTO, Character status){
        RequestData savedRequestData = requestDataRepository.
                save(ApplicationUtility.convertRequestDTO(requestDTO,status));
        return savedRequestData.getRequestId();
    }

    public long getCountOfRequests(Integer customerId, LocalDate request_date) {
        return requestDataRepository.findAllWithCustomerIdAndRequestTime(customerId,request_date);
    }
}
