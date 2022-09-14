package com.alkemy.disney.controller;

import com.alkemy.disney.dto.ApiErrorDTO;
import com.alkemy.disney.excepciones.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<Object> handleThrowable (Throwable ex, WebRequest request){
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                Arrays.asList("")
        );
        return handleExceptionInternal((Exception) ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParaNotFound(RuntimeException ex, WebRequest request){
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("Param Not Found")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST,request);
    }

    /*@ExceptionHandler(value = {UserAlreadyExistAuthenticationException.class})
    protected ResponseEntity<Object> handleUserAlreadyExist(RuntimeException ex, WebRequest request){
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList()
        )
    }*/

}
