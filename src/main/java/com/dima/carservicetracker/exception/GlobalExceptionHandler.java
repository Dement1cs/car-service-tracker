package com.dima.carservicetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationErrors( MethodArgumentNotValidException ex){
        Map<String, String> errors = new LinkedHashMap<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for(FieldError error : fieldErrors){
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", 400);
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return response;
    }

    @ExceptionHandler({
            CarNotFoundException.class,
            ServiceRecordNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound (RuntimeException ex) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("status", 404);
        response.put("message", ex.getMessage());

        return response;
    }
}
