package com.remiges.adv_java_assignment.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RunTimeException extends RuntimeException {

    public static final long serialVersionUID = 1L;
    String requestId;
    HttpStatus status;

    public RunTimeException(String message, HttpStatus status, String RequestId) {
        super(message);
        this.requestId = requestId;
        this.status = status;
    }

}
