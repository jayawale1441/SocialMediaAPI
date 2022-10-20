package com.controller;

import java.util.NoSuchElementException;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



import com.exception.*;

@ControllerAdvice 
public class ControllerAdvisor extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(NoUserFoundException i,WebRequest req)
    {
        
        return  new ResponseEntity<>(i.toString(),HttpStatus.NOT_FOUND);
    }

}
