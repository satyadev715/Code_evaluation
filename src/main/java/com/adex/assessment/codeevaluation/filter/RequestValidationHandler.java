package com.adex.assessment.codeevaluation.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class RequestValidationHandler {
    private static Logger log = LoggerFactory.getLogger(RequestValidationHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseMessage> handleAllExceptions(Exception ex, WebRequest request) {
        log.debug("handleAllExceptions : "+ex.fillInStackTrace());

        ResponseMessage responseMessage = new ResponseMessage(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        log.debug("handleCustomerNotFoundException : "+ex.fillInStackTrace());
        ResponseMessage errorDetails = new ResponseMessage(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotActiveException.class)
    public ResponseEntity<ResponseMessage> handleCustomerNotActiveException(CustomerNotActiveException ex, WebRequest request) {
        log.debug("handleCustomerNotFoundException : "+ex.fillInStackTrace());
        ResponseMessage errorDetails = new ResponseMessage(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ResponseMessage errorDetails = new ResponseMessage(new Date(), "Validation Failed",
                ex.getBindingResult().toString());

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
