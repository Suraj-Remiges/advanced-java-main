package com.remiges.adv_java_assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remiges.adv_java_assignment.entity.EmployeeShadow;

@Repository
public interface EmployeeShadowRepository extends JpaRepository<EmployeeShadow, Integer> {

}
