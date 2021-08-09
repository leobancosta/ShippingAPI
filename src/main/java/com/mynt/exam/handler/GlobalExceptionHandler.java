package com.mynt.exam.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mynt.exam.web.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        
        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> {
        	errorList.add(fieldError.getObjectName()+"."+fieldError.getField()+" : " +fieldError.getDefaultMessage() +" : rejected value [" +fieldError.getRejectedValue() +"]" );
        });
        result.getGlobalErrors().forEach((fieldError) -> {
        	errorList.add(fieldError.getObjectName()+" : " +fieldError.getDefaultMessage() );
        });
        
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), String.valueOf(Instant.now().toEpochMilli()), errorList);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO handleGenericException(Exception ex, WebRequest request) {
		return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), String.valueOf(Instant.now().toEpochMilli()), null);
	}
	
	
}
