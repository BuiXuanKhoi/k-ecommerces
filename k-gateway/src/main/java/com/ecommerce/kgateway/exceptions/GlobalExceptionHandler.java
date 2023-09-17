package com.ecommerce.kgateway.exceptions;


import com.ecommerce.kgateway.exceptions.models.ErrorRespond;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorRespond handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ErrorRespond(HttpStatus.NOT_FOUND.value(),exception.getMessage());
    }



}
