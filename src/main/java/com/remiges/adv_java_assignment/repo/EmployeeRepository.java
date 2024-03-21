package com.remiges.adv_java_assignment.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.remiges.adv_java_assignment.dto.EmployeeNameIdDto;
import com.remiges.adv_java_assignment.entity.Employee;

//Employee Repository extending JpaRepository
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(nativeQuery = true, value = "select * from employee where deptid_id=deptid")
    public Optional<List<Employee>> getByDept(Integer deptid);

    @Query(nativeQuery = true, value = "Select empid , fname from Employee")
    public List<EmployeeNameIdDto> getEmpList();

    @Query(nativeQuery = true, value = "select emp_id,f_name from employee where f_name like :filter")
    public List<EmployeeNameIdDto> getEmpList(String filter);

}
