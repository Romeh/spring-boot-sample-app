package com.test.sampleapp.web;

import com.test.sampleapp.web.dto.CustomErrorResponse;
import org.slf4j.Logger;
import com.test.sampleapp.exceptions.*;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generic error handling mechanism.
 *
 * @author romih
 */
@ControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public CustomErrorResponse handleNotFound(ResourceNotFoundException ex) {
        log.warn("Entity was not found", ex);
        return new CustomErrorResponse(ERROR_CODE.E0001.name(), ex.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(NotAllowedOperationException.class)
    @ResponseBody
    public CustomErrorResponse handleNotFound(NotAllowedOperationException ex) {
        log.warn("Not Allowed operation", ex);
        return new CustomErrorResponse(ERROR_CODE.E0002.name(), ex.getMessage());
    }

    private enum ERROR_CODE {
        E0001, E0002
    }

}
