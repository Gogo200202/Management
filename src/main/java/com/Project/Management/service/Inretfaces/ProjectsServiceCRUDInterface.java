package com.Project.Management.service.Inretfaces;

import com.Project.Management.models.Projects;

import java.util.List;

public interface ProjectsServiceCRUDInterface {

    public  boolean DeleteById(int id);
    public List<Projects> ReadAll();

    public void AddEmp(Projects projects);
    public boolean UpdateEmp(int id , Projects projects);
}
