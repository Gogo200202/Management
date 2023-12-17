package com.Project.Management;


import com.Project.Management.service.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ManagementApplication {

	@Autowired
	CsvParser csvParser;

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(ManagementApplication.class, args);
		CsvParser service = applicationContext.getBean(CsvParser.class);
		 var a= service.ParserToProjects();

	}

}
