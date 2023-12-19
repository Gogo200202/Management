package com.Project.Management.service;

import com.Project.Management.models.Projects;
import com.Project.Management.repository.RepositoryProjects;
import com.Project.Management.utils.EmployIdAndTime;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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






    public void pairOfEmployeesWorkedTogether(){

        // projects id
       List<Integer> AllProjectsIds = repositoryProjects.findDistinctProjectID();

        // project -> employ -> worked with
        Map<Integer, HashMap<EmployIdAndTime,Integer>> projectToEmploys = new HashMap<>();
        for (int i = 0; i <  AllProjectsIds.size(); i++) {
         // get project by id
            List<Projects> currentProject=repositoryProjects.findByprojectID(AllProjectsIds.get(i));
            // add project to the map
            projectToEmploys.put(currentProject.get(i).getProjectID(),new HashMap<>());

            for (int j = 0; j < currentProject.size(); j++) {

                var currentEmploy=currentProject.get(j);
             // get all employ that worked on this project at this
                // specific time to get whit what other employs he has worked
                var result=repositoryProjects.findBydatefromBetweenAndProjectID(currentEmploy.getDatefrom(),currentEmploy.getDateto(),currentEmploy.getProjectID());

                // remove current employ from resul
                result.remove(result.stream().filter(x->x.getEmpID()==currentEmploy.getEmpID()).findFirst().orElse(null));

                if (!result.isEmpty()){
                    for (int k = 0; k < result.size(); k++) {
                            // if one have been more time then the other add them back
                            EmployIdAndTime employIdAndTime = new EmployIdAndTime();
                            employIdAndTime.setEmployId(currentEmploy.getEmpID());
                            long diff = Math.abs(currentEmploy.getDatefrom().getTime() - currentEmploy.getDateto().getTime());
                            long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                            employIdAndTime.setTimeSpentOnProject((int) daysBetween);

                            projectToEmploys.get(currentProject.get(i).getProjectID()).put(employIdAndTime, result.get(k).getEmpID());

                            EmployIdAndTime employIdAndTimeSecond = new EmployIdAndTime();
                            long diffSecond = Math.abs(currentEmploy.getDatefrom().getTime() - currentEmploy.getDateto().getTime());
                            long daysBetweenSecond = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                            employIdAndTimeSecond.setTimeSpentOnProject((int) daysBetweenSecond);
                            employIdAndTimeSecond.setEmployId(result.get(k).getEmpID());
                            projectToEmploys.get(currentProject.get(i).getProjectID()).put(employIdAndTimeSecond, currentEmploy.getEmpID());
                    }

                }

            }

        }


    }


}
