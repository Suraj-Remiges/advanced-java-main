package com.remiges.adv_java_assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.remiges.adv_java_assignment.service.EmployeeService;
import com.remiges.adv_java_assignment.service.RedisService;
import com.remiges.adv_java_assignment.utility.Request;
import com.remiges.adv_java_assignment.utility.Response;

import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController1 {

    private final com.remiges.adv_java_assignment.service.RedisService redisService;

    @Autowired
    private EmployeeService employeeService;

    // @Autowired
    public RedisController1(RedisService redisService) {
        this.redisService = redisService;
    }

    // Problem 20. (i)
    @PostMapping("/addEmployee")
    public ResponseEntity<Object> addEmployee(@RequestBody Request request) {
        Map<String, Object> data = request.getData();
        String empName = (String) data.get("empName");
        redisService.addEmployee(empName);
        return Response.generateResponse("success", "", "", empName, Response.generateRandomNumberString(12));
    }

    // Problem 20. (ii)
    @GetMapping("/getEmployeeValue")
    public ResponseEntity<Object> getEmployeeValue(@RequestParam String empName, @RequestBody Request request) {
        Integer value = redisService.getEmployeeValue(empName);
        return Response.generateResponse("success", "", "", value, request.getReqId());
    }

    // Problem 20. (iii)
    @PostMapping("/incrementEmployeeValue")
    public ResponseEntity<Object> incrementEmployeeValue(@RequestParam String empName,
            @RequestBody Request request) {
        redisService.incrementEmployeeValue(empName);
        return Response.generateResponse("success", "", "", request.getData(),
                Response.generateRandomNumberString(12));
    }

    // Problem 20. (iv)
    @PostMapping("/decrementEmployeeValue")
    public ResponseEntity<Object> decrementEmployeeValue(@RequestParam String empName,
            @RequestBody Request request) {
        redisService.decrementEmployeeValue(empName);
        return Response.generateResponse("success", "", "", request.getData(),
                Response.generateRandomNumberString(12));
    }

    // Problem 20. (v)
    @PostMapping("/setEmployeeTTL")
    public ResponseEntity<Object> setEmployeeTTL(@RequestParam String empName, @RequestParam long ttlInSeconds,
            @RequestBody Request request) {
        redisService.setEmployeeTTL(empName, ttlInSeconds);
        return Response.generateResponse("success", "", "", request.getData(),
                Response.generateRandomNumberString(12));
    }

    // Problem 21.

    @PostMapping("/updateEmployeeContribution")
    public ResponseEntity<Object> updateEmployeeContribution(@RequestBody Request request) {
        try {
            String department = (String) request.getData().get("department");
            String employeeId = (String) request.getData().get("employeeId");
            int count = request.getData().containsKey("count") ? (int) request.getData().get("count") : 1;

            Map<String, Integer> contribution = redisService.updateEmployeeContribution(department, employeeId,
                    count);

            return Response.generateResponse("success", "", "", contribution, request.getReqId());
        } catch (Exception e) {
            return Response.generateResponse("error", "500", e.getMessage(), null,
                    Response.generateRandomNumberString(12));
        }
    }

    // Problem 22.

    @GetMapping("/myhr/employee/getContribution")
    public ResponseEntity<Object> getEmployeeContribution(@RequestParam String department) {

        try {

            // Call service method to get the latest count for the employee
            int latestCount = redisService.getEmployeeContribution(department);
            // Return the response
            // return ResponseEntity.ok().body("Latest count for department " + department +
            // ": " + latestCount);
            return Response.generateResponse("success", "", "", latestCount,
                    Response.generateRandomNumberString(12));
        } catch (Exception e) {
            return Response.generateResponse("error", "500", e.getMessage(), null,
                    Response.generateRandomNumberString(12));
        }

    }

    // Problem 23.

    @GetMapping("/{department}/{employeeId}/contribution")
    public ResponseEntity<Object> getEmployeeContribution(@PathVariable String department,
            @PathVariable String employeeId) {

        try {

            int contribution = employeeService.getEmployeeContribution(department, employeeId);

            return Response.generateResponse("success", "", "", contribution,
                    Response.generateRandomNumberString(12));
        } catch (Exception e) {
            return Response.generateResponse("error", "500", e.getMessage(), null,
                    Response.generateRandomNumberString(12));
        }
    }

}
