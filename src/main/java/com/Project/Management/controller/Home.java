package com.Project.Management.controller;

import com.Project.Management.models.Projects;
import com.Project.Management.service.ProjectsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {

    ProjectsService service;
    public Home(ProjectsService service){
        this.service=service;
    }
    @GetMapping("/All")
    public List<Projects> All(){

          return service.All();
    }



}
