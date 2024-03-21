package com.remiges.adv_java_assignment.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "departments")
@Data
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dept_name")
    private String deptName;

    @OneToMany(mappedBy = "deptid")
    @JsonBackReference
    //@JsonIgnoreProperties("deptid")
    private List<Employee> employee;

    public Departments() {
    }

    public Departments(Integer id, String deptName, List<Employee> employee) {
        this.id = id;
        this.deptName = deptName;
        this.employee = employee;
    }

}
