package com.remiges.adv_java_assignment.wrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.remiges.adv_java_assignment.entity.Employee;

import lombok.Data;

@Data
@JsonRootName("employee")
public class EmployeeXMLWrapper {

    private List<Employee> employeeList;

}
