package com.remiges.adv_java_assignment.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.remiges.adv_java_assignment.utility.Request;
import com.remiges.adv_java_assignment.utility.Response;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ArrayCal {

    @PostMapping("/mycalc")
    public ResponseEntity<Response> performOperation(@RequestBody Request request, HttpServletRequest httpRequest) {
        try {
            // Get User-Agent from the request headers
            String userAgent = httpRequest.getHeader("User-Agent");

            // Method to receive type of operation to perform as per client request
            String operation = (String) request.getData().get("operation");
            @SuppressWarnings("unchecked")
            List<Number> numbers = (List<Number>) request.getData().get("numbers");

            double result = performOperation(operation, numbers);

            Response response = new Response("success", "200", "",
                    Map.of("operation performed", operation, "result", result, "User-Agent", userAgent),
                    request.getReqId(), null);
            response.setServerTs(Instant.now());
            response.setReqId(UUID.randomUUID().toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return handleException(e, request.getReqId());
        }
    }

    private ResponseEntity<Response> handleException(Exception e, String reqId) {
        Response eResponse = new Response(reqId, reqId, reqId, null, reqId, null);
        eResponse.setStatus("errro");
        eResponse.setStatusCode("Internal_Error");
        eResponse.setStatusMsg(e.getMessage());
        eResponse.setReqId(reqId);
        eResponse.setServerTs(Instant.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(eResponse);
    }

    private double performOperation(String operation, List<Number> numbers) {
        // Exception handling for Illegal Argument
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("At least two numbers are required for the operation.");
        }

        // Switch case to perform operations
        return switch (operation) {
            case "add" -> numbers.stream().mapToDouble(Number::doubleValue).sum();
            case "subtract" -> numbers.get(0).doubleValue() - numbers.get(1).doubleValue();
            case "multiply" -> numbers.stream().mapToDouble(Number::doubleValue).reduce(1, (a, b) -> a * b);
            case "divide" -> {
                // Convertion to double
                double num1 = numbers.get(0).doubleValue();
                double num2 = numbers.get(1).doubleValue();
                if (num2 == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed.");
                }
                yield num1 / num2;
            }

            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }
}
