package com.Project.Management.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Projects")
public class Projects {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "Emp_ID")
    private Integer EmpID;

    @Column(name = "Project_ID")
    private Integer ProjectID;



    @Column(name = "Date_From")
    private Date DateFrom;

    @Column(name = "Date_To")
    private Date DateTo;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        ProjectID = projectID;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }
}
