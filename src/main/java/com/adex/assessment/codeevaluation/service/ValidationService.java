package com.adex.assessment.codeevaluation.service;

import com.adex.assessment.codeevaluation.dao.IPRepository;
import com.adex.assessment.codeevaluation.dao.UserAgentRepository;
import com.adex.assessment.codeevaluation.entity.IPAddressBlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService {

    private UserAgentRepository userAgentRepository;
    private IPRepository ipRepository;


    public ValidationService() {
    }

    @Autowired
    public ValidationService(UserAgentRepository userAgentRepository, IPRepository ipRepository) {
        this.userAgentRepository = userAgentRepository;
        this.ipRepository = ipRepository;
    }

    public Boolean validateUserAgent(String userAgent){
        return userAgentRepository.existsUserAgentByUa(userAgent);
    }

    public Boolean validateRemoteIp(String ip){
        Optional<IPAddressBlackList> ipOptional = ipRepository.existsByIp(ip);
        if(ipOptional.isPresent()){
            return true;
        }else
            return false;
    }

}
