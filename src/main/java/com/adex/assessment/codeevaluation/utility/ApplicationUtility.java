package com.adex.assessment.codeevaluation.utility;
import com.adex.assessment.codeevaluation.entity.RequestDTO;
import com.adex.assessment.codeevaluation.entity.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;
import java.time.Instant;


public class ApplicationUtility {
    private static Logger log = LoggerFactory.getLogger(ApplicationUtility.class);

    public static RequestData convertRequestDTO(RequestDTO requestDTO, Character status){
        RequestData requestData =new RequestData();
        requestData.setCustomerId(requestDTO.getCustomerID());
        Timestamp ts= Timestamp.from(Instant.ofEpochSecond(requestDTO.getTimestamp()));
        log.debug("request time : "+ts.toString());
        requestData.setRequestTime(ts);
        requestData.setRequestPayload(requestDTO.toString());
        requestData.setStatus(status);
        return requestData;
    }

}
