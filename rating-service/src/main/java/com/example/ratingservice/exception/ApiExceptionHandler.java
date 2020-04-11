package com.example.ratingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ratingservice.exception.*;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value={ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestExceptionNotFound(ApiRequestException e){
	
		ApiException apiException=new ApiException(e.getMessage(),e,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
	}
	
	
}
