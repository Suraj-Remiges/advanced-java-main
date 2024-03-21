package com.remiges.adv_java_assignment.utility;

import java.beans.JavaBean;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@JavaBean
@Data
public class Response {

    private String status;
    private String statusCode;
    private String statusMsg;
    private Map<String, Object> data;
    private String reqId;
    private Instant serverTs;

    // Parameterised constructor
    public Response(String status, String statusCode, String statusMsg, Map<String, Object> data, String reqId,
            Instant serverTs) {
        this.status = status;
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
        this.reqId = reqId;
        this.serverTs = serverTs;
    }

    // Methid to generate random String
    public static String generateRandomNumberString(int length) {
        Random random = new Random();
        return random.ints(length, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    public static ResponseEntity<Object> generateResponse(String string, String string2, String string3,
            Map<String, Object> data2, String string4) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateResponse'");
    }

    public static ResponseEntity<Object> generateResponse(String status, String statusCode, String statusMsg,
            Object responseData, String reqId) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("status_code", statusCode);
        map.put("status_msg", statusMsg);
        map.put("data", responseData);
        map.put("_reqid", reqId);
        map.put("_server_ts", LocalDateTime.now().toString());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
