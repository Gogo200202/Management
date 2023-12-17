package com.Project.Management;


import com.Project.Management.service.csvParserToProjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


@SpringBootApplication
public class ManagementApplication {



	public static void main(String[] args) {


		SpringApplication.run(ManagementApplication.class, args);

	}

}
