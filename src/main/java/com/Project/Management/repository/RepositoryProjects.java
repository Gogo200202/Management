package com.Project.Management.repository;

import com.Project.Management.models.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProjects extends JpaRepository<Projects, Long> {
}
