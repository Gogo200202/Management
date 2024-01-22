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
    private Integer empID;

    @Column(name = "Project_ID")
    private Integer projectID;



    @Column(name = "Date_From")
    private Date datefrom;

    @Column(name = "Date_To")
    private Date dateto;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public Date getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(Date datefrom) {
        this.datefrom = datefrom;
    }

    public Date getDateto() {
        return dateto;
    }

    public void setDateto(Date dateto) {
        this.dateto = dateto;
    }
}
