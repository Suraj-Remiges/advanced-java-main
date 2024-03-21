package com.remiges.adv_java_assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.remiges.adv_java_assignment.dto.ResponseHandllerDao;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(RunTimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleEntityNotFoundException(RunTimeException ex) {
		return ResponseHandllerDao.ResponceHandler("", HttpStatus.NOT_FOUND, ex.getRequestId(), ex.getMessage());
	}

}
