package com.remiges.adv_java_assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remiges.adv_java_assignment.entity.Departments;

@Repository
public interface DeptRepo extends JpaRepository<Departments, Integer> {

}
