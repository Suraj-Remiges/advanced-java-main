package com.remiges.adv_java_assignment.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lowagie.text.List;
import com.remiges.adv_java_assignment.dto.EmployeeDto;
import com.remiges.adv_java_assignment.dto.EmployeeNameIdDto;
import com.remiges.adv_java_assignment.dto.ResponseHandllerDao;
import com.remiges.adv_java_assignment.entity.Departments;
import com.remiges.adv_java_assignment.entity.Employee;
import com.remiges.adv_java_assignment.entity.EmployeeShadow;
import com.remiges.adv_java_assignment.entity.Ranks;
import com.remiges.adv_java_assignment.exception.RunTimeException;
import com.remiges.adv_java_assignment.repo.DeptRepo;
import com.remiges.adv_java_assignment.repo.EmployeeRepository;
import com.remiges.adv_java_assignment.repo.EmployeeShadowRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RankService rankService;

    @Autowired
    private EmployeeShadowRepository employeeShadowRepository;

    @SuppressWarnings("null")
    // method to save employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // //add employee
    public ResponseEntity<?> addEmp(EmployeeDto empDto) {

        if (empDto.getEmpId().isEmpty()) {
            throw new RunTimeException("EmpId not entered", null, null);

        } else {

            Departments deptData;

            ResponseEntity<?> deptResponEntity = departmentService.getDeptById(empDto.getDeptid());
            if (deptResponEntity.getStatusCode() == HttpStatus.OK) {
                deptData = (Departments) deptResponEntity.getBody();
            } else {
                System.out.println(deptResponEntity.getBody());
                return deptResponEntity;
            }

            Ranks ranks;
            ResponseEntity<?> rankResponceEntity = rankService.getRankById(empDto.getRankid());
            if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
                ranks = (Ranks) rankResponceEntity.getBody();
            } else {
                return rankResponceEntity;
            }

            Employee emp = new Employee();

            emp.setId(empDto.getId());

            emp.setEmpId(empDto.getEmpId());

            emp.setFName(empDto.getfName());

            emp.setFullName(empDto.getFullName());

            emp.setDob(empDto.getDob());

            emp.setDoj(empDto.getDoj());

            emp.setSalary(empDto.getSalary());

            emp.setReportsTo(empDto.getReportsTo());

            emp.setClientReqId(empDto.getClientReqId());

            emp.setDeptid(deptData);

            emp.setRankid(ranks);

            employeeRepository.save(emp);

            return ResponseHandllerDao.ResponceHandler(emp, HttpStatus.OK, "");
        }

    }

    // Method to get all employee's id and full name
    public java.util.List<Object[]> employeeList() {
        return employeeRepository.findAll().stream()
                .map(employee -> new Object[] { employee.getId(), employee.getFullName() })
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getEmpList(String filter) {
        java.util.List<EmployeeNameIdDto> empList;

        if (filter == null)
            empList = employeeRepository.getEmpList();
        else
            empList = employeeRepository.getEmpList("%"+filter+"%");

        if (empList.size() <= 0)
            throw new RunTimeException("Employee not found", HttpStatus.NOT_FOUND, filter);

        return ResponseHandllerDao.ResponceHandler(empList, HttpStatus.OK, "", "");

    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee updateEmployeeById(Integer id, Employee updateRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee with given id does not exist"));

        EmployeeShadow empShadow = new EmployeeShadow(
                employee.getId(),
                employee.getEmpId(),
                employee.getFName(),
                employee.getFullName(), employee.getDob(), employee.getDoj(),
                employee.getSalary(),
                employee.getReportsTo(), employee.getDeptid(), employee.getRankid(),
                employee.getCreateDate(),
                employee.getUpdateDate(), employee.getClientReqId());

        employeeShadowRepository.save(empShadow);

        employee.setEmpId(updateRequest.getEmpId());
        employee.setFName(updateRequest.getFName());
        employee.setFullName(updateRequest.getFullName());
        employee.setDob(updateRequest.getDob());
        employee.setDoj(updateRequest.getDoj());
        employee.setSalary(updateRequest.getSalary());
        employee.setReportsTo(updateRequest.getReportsTo());
        employee.setDeptid(updateRequest.getDeptid());
        employee.setRankid(updateRequest.getRankid());

        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        // Copy existing employee to employee_shadow table
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee with given id does not exist"));

        EmployeeShadow empShadow = new EmployeeShadow(
                employee.getId(),
                employee.getEmpId(),
                employee.getFName(),
                employee.getFullName(), employee.getDob(), employee.getDoj(),
                employee.getSalary(),
                employee.getReportsTo(), employee.getDeptid(), employee.getRankid(),
                employee.getCreateDate(),
                employee.getUpdateDate(), employee.getClientReqId());

        employeeShadowRepository.save(empShadow);

        // Delete the employee
        employeeRepository.deleteById(id);
    }

}
