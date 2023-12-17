package com.Project.Management.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Service
public class csvParserToProjects {




    public void method(){

        try {
            Resource resource = new ClassPathResource("SeededData/test.csv");
            File file = resource.getFile();
            Scanner in = new Scanner(file);

            StringBuilder sb = new StringBuilder();
            while(in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            String outString = sb.toString();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
