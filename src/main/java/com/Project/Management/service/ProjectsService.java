package com.Project.Management.service;

import com.Project.Management.models.Projects;
import com.Project.Management.repository.RepositoryProjects;

import com.Project.Management.service.Inretfaces.ProjectsServiceCRUDInterface;
import com.Project.Management.util.CsvParser;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProjectsService implements ProjectsServiceCRUDInterface {

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

    public String pairOfEmployeesWorkedTogetherForTheLongestPeriodOfTime(){

        // projects id
        List<Integer> AllProjectsIds = repositoryProjects.findDistinctProjectID();
        // project -> employ -> worked with
        Map<Integer, HashMap<Integer,List<Projects>>> projectToEmploys = new HashMap<>();
        //project-> employ -> time worked
        Map<Integer,HashMap<Integer,Integer>> employTime=new HashMap<>();

        for (int i = 0; i < AllProjectsIds.size(); i++) {
            // get project by id to get all employs who have worked on this project
            List<Projects> currentProjects=repositoryProjects.findByprojectID(AllProjectsIds.get(i));
            projectToEmploys.put(AllProjectsIds.get(i),new HashMap<>());
            employTime.put(AllProjectsIds.get(i),new HashMap<>());

            for (int j = 0; j < currentProjects.size(); j++) {

                var currentEmploy=currentProjects.get(j);
                long diff = Math.abs(currentEmploy.getDatefrom().getTime() - currentEmploy.getDateto().getTime());
                long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                employTime.get(AllProjectsIds.get(i)).put(currentEmploy.getEmpID(),(int)daysBetween);
                // get all employ that worked on this project at this
                // specific time to get whit what other employs he has worked
                var result=repositoryProjects.findBydatefromBetweenAndProjectID(currentEmploy.getDatefrom(),currentEmploy.getDateto(),currentEmploy.getProjectID());
                // remove current employ from resul
                if (!result.isEmpty()){

                    if(projectToEmploys.get(AllProjectsIds.get(i)).containsKey(currentEmploy.getEmpID())){

                        for (int k = 0; k < result.size(); k++) {

                            projectToEmploys.get(AllProjectsIds.get(i)).get(currentEmploy.getEmpID()).add(result.get(k));

                        }
                    }else {
                        projectToEmploys.get(AllProjectsIds.get(i)).put(currentEmploy.getEmpID(),result);
                        for (int k = 0; k < result.size(); k++) {

                            if(currentEmploy.getDatefrom().before(result.get(k).getDatefrom())){

                                if(projectToEmploys.get(AllProjectsIds.get(i)).containsKey(result.get(k).getEmpID())){
                                    final int labelText = result.get(k).getEmpID() ;
                                    Projects ifExist= projectToEmploys.get(AllProjectsIds.get(i)).get(currentEmploy.getEmpID()).stream().filter(x->x.getEmpID()==labelText).findFirst().orElse(null);

                                    if(Objects.isNull(ifExist)){
                                        projectToEmploys.get(AllProjectsIds.get(i)).get(currentEmploy.getEmpID()).add(result.get(k));
                                    }

                                }else {

                                    List<Projects> p = new ArrayList<>();
                                    p.add(currentEmploy);
                                    projectToEmploys.get(AllProjectsIds.get(i)).put(result.get(k).getEmpID(),p);

                                }

                            }

                        }

                    }

                }

            }

        }

        Map<String,Integer> employTotalTimeThogether=new HashMap<>();
        for (var projects :projectToEmploys.entrySet()){
            for (var emp :projects.getValue().entrySet()){
                for (int i = 0; i < emp.getValue().size(); i++) {
                    if(emp.getKey()==emp.getValue().get(i).getEmpID()){
                        continue;
                    }
                    var totalTimeForBoat=employTime.get(projects.getKey()).get(emp.getKey())+employTime.get(projects.getKey()).get(emp.getValue().get(i).getEmpID());
                    employTotalTimeThogether.put(emp.getKey().toString()+" "+emp.getValue().get(i).getEmpID(),totalTimeForBoat);
                }

            }

        }


        Map<String, Integer> sortedMap =
                employTotalTimeThogether.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e2, LinkedHashMap::new));



        Object firstKey = sortedMap.keySet().toArray()[sortedMap.keySet().toArray().length-1];
        Object valueForFirstKey = sortedMap.get(firstKey);



        String[] employs=firstKey.toString().split(" ");
        Integer emp1=Integer.parseInt(employs[0]);
        Integer emp2=Integer.parseInt(employs[1]);
        Map<Integer,Integer> Emp1WorkedOn= getEmployeesProjectsTimeWorked(projectToEmploys, emp1, employTime);
        Map<Integer,Integer> Emp2WorkedOn= getEmployeesProjectsTimeWorked(projectToEmploys, emp2, employTime);

        StringBuilder sb= new StringBuilder();
        sb.append(firstKey).append(" Total hours worked ").append(valueForFirstKey).append("\n");
        for (var projects : Emp1WorkedOn.entrySet()){
            sb.append("Employee id ").append(emp1).append("Project id ").append(projects.getKey()).append(" Time ").append(projects.getValue()).append("\n");
        }

        for (var projects : Emp2WorkedOn.entrySet()){
            sb.append("Employee id ").append(emp2).append("Project id ").append(projects.getKey()).append(" Time ").append(projects.getValue()).append("\n");
        }


        return sb.toString() ;

    }

    private Map<Integer,Integer> getEmployeesProjectsTimeWorked(Map<Integer, HashMap<Integer, List<Projects>>> projectToEmploys, Integer emp1, Map<Integer, HashMap<Integer, Integer>> employTime) {
        Map<Integer,Integer> projectHoursForEmp=new HashMap<>();
        for (var projects : projectToEmploys.entrySet()){

            if(projects.getValue().containsKey(emp1)){
                Integer p = employTime.get(projects.getKey()).get(emp1);
                projectHoursForEmp.put(projects.getKey(),p);
            }

        }

        return projectHoursForEmp;
    }

    
    //Emp ->Employee

    public boolean DeleteById(int id){
       if(repositoryProjects.existsById((long)id)){
           repositoryProjects.deleteById((long)id);
           return  false;
       }
          return  true;
    }

    public List<Projects> ReadAll(){
     return repositoryProjects.findAll();
    }

    public void AddEmp(Projects projects){
        repositoryProjects.save(projects);
    }

    public boolean UpdateEmp(int id , Projects projects){


        Projects projectsToUpdate=repositoryProjects.findById((long)id).orElse(null);
        if(Objects.isNull(projectsToUpdate)){
            return true;
        }
        projectsToUpdate.setId(projects.getId());
        projectsToUpdate.setEmpID(projects.getEmpID());
        projectsToUpdate.setProjectID(projects.getProjectID());
        projectsToUpdate.setDatefrom(projects.getDatefrom());
        projectsToUpdate.setDateto(projects.getDateto());
        repositoryProjects.save(projectsToUpdate);

        return false;
    }



}
