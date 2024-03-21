package com.remiges.adv_java_assignment.entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_shadow")
public class EmployeeShadow {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "emp_id")
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

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Departments deptid;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Ranks rankid;

    @CreationTimestamp
    @Column(name = "create_date")
    private java.util.Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private java.util.Date updateDate;

    @UuidGenerator
    @Column(name = "client_reqid")
    private String clientReqId;

    // Constructor

    public String getFullName() {
        return fullName;
    }

    public EmployeeShadow(Integer integer, String string, String string2, String string3, Date date, Date date2,
            Integer integer2, Integer integer3, List<Departments> list, List<Ranks> list2, java.util.Date date3,
            java.util.Date date4, String string4) {
    }

    public EmployeeShadow(Integer id, String empId, String fName, String fullName, Date dob, Date doj, Integer salary,
            Integer reportsTo, Departments deptid, Ranks rankid, java.util.Date createDate, java.util.Date updateDate,
            String clientReqId) {
        this.id = id;
        this.empId = empId;
        this.fName = fName;
        this.fullName = fullName;
        this.dob = dob;
        this.doj = doj;
        this.salary = salary;
        this.reportsTo = reportsTo;
        this.deptid = deptid;
        this.rankid = rankid;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.clientReqId = clientReqId;
    }

    public EmployeeShadow() {
    }

}
