package com.remiges.adv_java_assignment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyHREmployeeController {

     private Map<String, Integer> employeeContributionMap;

    public MyHREmployeeController() {
        employeeContributionMap = new HashMap<>();
    }

    @PostMapping("/myhr/employee/updateEmployeeContribution")
    public ResponseEntity<?> updateEmployeeContribution(
            @RequestParam String departmentName,
            @RequestParam String employeeID,
            @RequestParam(required = false, defaultValue = "1") int count) {

        String key = generateKey(departmentName, employeeID);

        int updatedCount = employeeContributionMap.getOrDefault(key, 0) + count;
        employeeContributionMap.put(key, updatedCount);

        return ResponseEntity.ok("Name: " + key + ", Updated Count: " + updatedCount);
    }

    private String generateKey(String departmentName, String employeeID) {
        return "user." + departmentName + "." + employeeID;
    }

}


