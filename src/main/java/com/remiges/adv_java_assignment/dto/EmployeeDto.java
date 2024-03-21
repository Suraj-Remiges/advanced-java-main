package com.remiges.adv_java_assignment.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeDto {

    private Integer id;

    private String empId;

    private String fName;

    private String fullName;

    private Date dob;

    private Date doj;

    private Integer salary;

    private Integer reportsTo;

    private java.util.Date createDate;

    private java.util.Date updateDate;

    private String clientReqId;

    private Integer deptid;

    private Integer rankid;

    public EmployeeDto(Integer id, String empId, String fName, String fullName, Date dob, Date doj, Integer salary,
            Integer reportsTo, java.util.Date createDate, java.util.Date updateDate, String clientReqId, Integer deptid,
            Integer rankid) {
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

    public EmployeeDto() {
    }

	public String getfName() {
		return fName;
	}

    

}
