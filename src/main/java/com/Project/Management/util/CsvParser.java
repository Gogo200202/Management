package com.Project.Management.util;

import com.Project.Management.models.Projects;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class CsvParser {




    public List<Projects> ParserToProjectsFromCsvFile(){

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
                String empId=dataFromLine[0].replaceAll("\\uFEFF", "").trim();
                String projId=dataFromLine[1].replaceAll("\\uFEFF", "").trim();

                try {
                    Integer.parseInt(empId);
                } catch (NumberFormatException e) {
                    throw  new NumberFormatException("You cant pares: "+empId);
                }

                try {
                    Integer.parseInt(projId);
                } catch (NumberFormatException e) {
                    throw  new NumberFormatException("You cant pares: "+projId);
                }

                DateTimeFormatter parser = DateTimeFormatter.ofPattern("[d-MM-yyyy][dd-MM-yyyy][MM-dd-yyyy][yyyy-MM-dd]");
                String dateFromString=dataFromLine[2].replaceAll("\\uFEFF", "").trim();
                LocalDate d1 = LocalDate.parse(dateFromString, parser);
                Date dateFrom = java.sql.Date.valueOf(d1);

                String dateToString=dataFromLine[3].replaceAll("\\uFEFF", "").trim();
                if(dateToString.equals("NULL")){
                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    dateToString=date;
                }
                LocalDate d2 = LocalDate.parse(dateToString, parser);
                Date dateTo = java.sql.Date.valueOf(d2);

                project.setEmpID(Integer.parseInt(empId));
                project.setProjectID(Integer.parseInt(projId));
                project.setDatefrom(dateFrom);
                project.setDateto(dateTo);
                projects.add(project);
            }
            in.close();

            return projects;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
