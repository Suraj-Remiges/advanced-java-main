package com.remiges.adv_java_assignment.utility;

import java.beans.JavaBean;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import lombok.Data;

@JavaBean
@Data
public class Request {

    private String token;
    private Map<String, Object> data;
    private String reqId;
    private Instant clientTs;
    private String clientType;

    public Request(String token, Map<String, Object> data, String reqId, Instant clientTs, String clientType) {
        this.token = token;
        this.data = data;
        this.reqId = reqId;
        this.clientTs = clientTs;
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "ApiRequest [token=" + token + ", data=" + data + ", reqId=" + reqId + ", client_ts=" + clientTs
                + ", client_type=" + clientType + "]";
    }

    // Method to generate random String
    public static String generateRanId() {
        return UUID.randomUUID().toString();
    }

}
