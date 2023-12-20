package com.Project.Management.controller;

import com.Project.Management.models.Projects;
import com.Project.Management.service.ProjectsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class HomeController {

    ProjectsService service;
    public HomeController(ProjectsService service){
        this.service=service;
    }
    @GetMapping("/All")
    public List<Projects> All(){
        return service.ReadAll();
    }

    @PostMapping("/Add")
    public void Add( @RequestBody Projects projects){
        service.AddEmp(projects);
    }

    @DeleteMapping("/Delete")
    public void Delete( @RequestParam int id){
        boolean result =service.DeleteById(id);
        if(result){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PutMapping("/Update")
    public void Update( @RequestParam int id ,@RequestBody Projects projects){
        boolean result= service.UpdateEmp(id,projects);
        if(result){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @GetMapping("/pairOfEmployeesWorkedTogetherForTheLongestPeriodOfTime")
    public String pairOfEmployeesWorkedTogetherForTheLongestPeriodOfTime( ){
       return service.pairOfEmployeesWorkedTogetherForTheLongestPeriodOfTime();
    }




}
