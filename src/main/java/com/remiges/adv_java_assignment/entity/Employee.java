package com.remiges.adv_java_assignment.entity;

import java.sql.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "employee")
@Entity
// Emplyee entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "emp_id")
    private String empId;

    @Column(nullable = false, name = "f_name")
    private String fName;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    private Date doj;

    @Column(nullable = false)
    private Integer salary;

    @Column(name = "reports_to")
    private Integer reportsTo;

    @CreationTimestamp
    @Column(name = "create_date")
    private java.util.Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private java.util.Date updateDate;

    @UuidGenerator
    @Column(name = "client_reqid")
    private String clientReqId;

    @ManyToOne
    @JsonManagedReference
    // @JsonIgnoreProperties("employee")
    //@JoinColumn(name = "dept_id")
    private Departments deptid;

    @ManyToOne
    @JsonManagedReference
    // @JsonIgnoreProperties("employee")
    //@JoinColumn(name = "rank_id")
    private Ranks rankid;

    // Constructor

    public String getFullName() {
        return fullName;
    }

    public Employee(Integer id, String empId, String fName, String fullName, Date dob, Date doj, Integer salary,
            Integer reportsTo, java.util.Date createDate, java.util.Date updateDate, String clientReqId,
            Departments deptid, Ranks rankid) {
        this.id = id;
        this.empId = empId;
        this.fName = fName;
        this.fullName = fullName;
        this.dob = dob;
        this.doj = doj;
        this.salary = salary;
        this.reportsTo = reportsTo;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.clientReqId = clientReqId;
        this.deptid = deptid;
        this.rankid = rankid;
    }

    public Employee() {
    }

    // Default constructor

}
