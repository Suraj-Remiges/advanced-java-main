package com.remiges.adv_java_assignment.controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.remiges.adv_java_assignment.utility.Request;
import com.remiges.adv_java_assignment.utility.Response;

@RestController
public class PropertiesController {

    @Value("${my.property1:NULL}")
    private String property1;

    @Value("${my.property2:NULL}")
    private String property2;

    @Value("${my.property3:NULL}")
    private String property3;

    @PostMapping("/myproperties")
    public ResponseEntity<Response> getProperties(@RequestBody Request request) {
        try {

            @SuppressWarnings("unchecked")
            List<String> propertyIdentifiers = (List<String>) request.getData().get("propertyIdentifiers");
            Map<String, Object> result = new HashMap<>();

            propertyIdentifiers.forEach(identifier -> {
                switch (identifier) {
                    case "property1":
                        result.put("property1", property1);
                        break;
                    case "property2":
                        result.put("property2", property2);
                        break;
                    case "property3":
                        result.put("property3", property3);
                        break;
                    default:
                        result.put(identifier, "NULL");
                }
            });
            Response response = new Response("Success", "200", "", result, request.getReqId(), null);
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
}
