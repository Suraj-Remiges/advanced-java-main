package com.remiges.adv_java_assignment.utility;

import java.beans.JavaBean;
import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
}
