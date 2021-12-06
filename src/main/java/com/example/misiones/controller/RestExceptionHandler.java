package com.example.misiones.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.misiones.exception.MissionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ErrorDetails> generalException(Exception ex, WebRequest request) {
		log.error("Something was wrong with ser server app");
		ErrorDetails detail = new ErrorDetails(LocalDateTime.now(), "Application server error", ex.getMessage());
		return new ResponseEntity<>(detail,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { MissionException.class })
	protected ResponseEntity<ErrorDetails> dataException(Exception ex, WebRequest request) {
		log.error("Error in a rest call !!!");
		ErrorDetails detail = new ErrorDetails(LocalDateTime.now(), "Data not found", ex.getMessage());
		return new ResponseEntity<>(detail,HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
//	protected ResponseEntity<Object> dataNotValid(MethodArgumentNotValidException ex, WebRequest request) {
//		log.error("Error with the data provided by the client");
//		ErrorDetails detail = new ErrorDetails(LocalDateTime.now(), "Provided data error", ex.getMessage());
//		return new ResponseEntity<>(detail,HttpStatus.BAD_REQUEST);
//	}
	
	protected org.springframework.http.ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Error with the data provided by the client HTTP_CODE({})", status.value());
		ErrorDetails detail = new ErrorDetails(LocalDateTime.now(), "Provided data error", ex.getMessage());
		return new ResponseEntity<>(detail,HttpStatus.BAD_REQUEST);
	};
}
