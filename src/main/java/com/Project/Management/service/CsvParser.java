package com.Project.Management.service;

import com.Project.Management.models.Projects;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CsvParser {




    public List<Projects> ParserToProjects(){

        try {
            Resource resource = new ClassPathResource("SeededData/test.csv");
            File file = resource.getFile();
            Scanner in = new Scanner(file);
            List<Projects> projects = new ArrayList<>();
            while(in.hasNext()) {
                // TODO add validation
                String[] dataFromLine=in.next().split(",");
                Projects project = new Projects();
                // remove all empty or invisible  character
                project.setEmpID(Integer.parseInt(dataFromLine[0].replaceAll("\\uFEFF", "").trim()));
                project.setProjectID(Integer.parseInt(dataFromLine[1].replaceAll("\\uFEFF", "").trim()));
                project.setDateFrom(dataFromLine[2].replaceAll("\\uFEFF", "").trim());
                project.setDateTo(dataFromLine[3].replaceAll("\\uFEFF", "").trim());
                projects.add(project);
            }
            in.close();

            return projects;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
