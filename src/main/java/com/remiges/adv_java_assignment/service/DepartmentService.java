package com.remiges.adv_java_assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.remiges.adv_java_assignment.dto.DepartmentDto;
import com.remiges.adv_java_assignment.entity.Departments;
import com.remiges.adv_java_assignment.repo.DeptRepo;

@Service
public class DepartmentService {

    @Autowired
    private DeptRepo deptRepo;

    public ResponseEntity<?> addDepartment(DepartmentDto deptDto) {

        if (deptDto.getId() == null || deptDto.getDeptName().isEmpty()) {
            return new ResponseEntity<>("Invalid id or Dept. Name", HttpStatus.BAD_REQUEST);
        } else {
            Departments dept = new Departments();
            dept.setId(deptDto.getId());
            dept.setDeptName(deptDto.getDeptName());
            deptRepo.save(dept);
            return new ResponseEntity<Departments>(dept, HttpStatus.OK);
        }

    }

    public ResponseEntity<?> getAllDept() {
        List<Departments> deptList = deptRepo.findAll();
        return new ResponseEntity<>(deptList, HttpStatus.OK);
    }

    public ResponseEntity<?> getDeptById(Integer deptid) {
        if (deptid == null) {
            return new ResponseEntity<>("Enter Dept ID" + deptid, HttpStatus.BAD_REQUEST);
        } else {

            Optional<Departments> dept = deptRepo.findById(deptid);

            if (dept.isPresent()) {
                return new ResponseEntity<Departments>(dept.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Department Not found for id " + deptid, HttpStatus.NOT_FOUND);
            }

        }
    }

}
