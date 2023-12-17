package com.Project.Management.controller;

import com.Project.Management.service.csvParserToProjects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    private csvParserToProjects parser;
    public Home(csvParserToProjects parser){
        this.parser=parser;
    }
    @GetMapping("/")
    public String test(){
      this.parser.method();
        return "5";
    }
}
