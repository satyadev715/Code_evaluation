package com.adex.assessment.codeevaluation.config;

import com.adex.assessment.codeevaluation.service.CustomerService;
import com.adex.assessment.codeevaluation.service.RequestDataService;
import com.adex.assessment.codeevaluation.service.ValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

        @Bean
        public CustomerService getCustomerService() {
            return new CustomerService();
        }

        @Bean
        public RequestDataService getRequestDataService(){
            return new RequestDataService();
        }

        @Bean
       public ValidationService getValidationService(){
            return new ValidationService();
        }

}
