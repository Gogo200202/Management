package com.Project.Management.models;

import jakarta.persistence.*;

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

    // TODO add Data type

    @Column(name = "Date_From")
    private String DateFrom;

    @Column(name = "Date_To")
    private String DateTo;

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

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }
}
