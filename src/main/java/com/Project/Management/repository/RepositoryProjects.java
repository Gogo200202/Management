package com.Project.Management.repository;

import com.Project.Management.models.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface RepositoryProjects extends JpaRepository<Projects, Long> {


    @Query(value = "SELECT DISTINCT project_id FROM projects", nativeQuery = true)
    List<Integer> findDistinctProjectID();

    List<Projects>findByprojectID(int id);
    Projects findByempID(int id);

    List<Projects> findBydatefromBetweenAndProjectID(Date start, Date end,int id);


}
