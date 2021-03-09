package me.saukin.app.controller;

import javax.servlet.http.HttpServletRequest;
import me.saukin.app.exceptions.CustomError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author saukin
 */
@ControllerAdvice
public class ExceptionControlle {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(HttpServletRequest request, Exception exception)  {
        
        CustomError error = new CustomError(HttpStatus.BAD_REQUEST, exception.getMessage());
        
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }
    
}
