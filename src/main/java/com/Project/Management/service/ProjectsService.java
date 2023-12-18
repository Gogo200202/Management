package com.Project.Management.service;

import com.Project.Management.models.Projects;
import com.Project.Management.repository.RepositoryProjects;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    CsvParser parser;
    RepositoryProjects repositoryProjects;

    public ProjectsService( CsvParser parser,RepositoryProjects repositoryProjects){
        this.parser=parser;
        this.repositoryProjects=repositoryProjects;

    }

    public void SeedDataFromCsvFileToDataBase(){
        List<Projects> projects= parser.ParserToProjectsFromCsvFile();
        repositoryProjects.saveAll(projects);

    }

    public List<Projects> All(){
        return repositoryProjects.findAll();
    }


    public void pairOfEmployeesWorkedTogether(){

    }


}
