package com.remiges.adv_java_assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remiges.adv_java_assignment.dto.EmployeeDto;
import com.remiges.adv_java_assignment.entity.Employee;
import com.remiges.adv_java_assignment.service.EmployeeService;

@RestController
@RequestMapping("myhr/employee")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmp")
    public ResponseEntity<?> employeeadd(@RequestBody EmployeeDto emp) {
        logger.debug("Debug lod: Adding Employee to database");
        return employeeService.addEmp(emp);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.ok().body(savedEmployee);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: error, message: " + e.getMessage());

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("status: error, message: " + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("status: error, message: An unexpected error occurred");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<java.util.List<Object[]>> employeeList(@RequestParam(required = false) String type) {
        logger.debug("Debug lod: Extracting Employee list");
        java.util.List<Object[]> employees = employeeService.employeeList();
        return ResponseEntity.ok().body(employees);

    }

    @GetMapping("/filter")
    public ResponseEntity<?> getEmpList(@RequestParam(name = "filter", required = true) String filter) {
        return employeeService.getEmpList(filter);
    }

    // @GetMapping("/alist")
    // public ResponseEntity<Employee> getEmpByFilter(@RequestParam Integer id){
    // return;
    // }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        logger.debug("Debug log: Retrieving employee details for employeeId: {}", id);

        return employeeService.getEmployeeById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,
            @RequestBody Employee updateRequest) {
        Employee updatedEmployee = employeeService.updateEmployeeById(id,
                updateRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
