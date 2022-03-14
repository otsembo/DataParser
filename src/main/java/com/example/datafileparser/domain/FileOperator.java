package com.example.datafileparser.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOperator {


    //file resource object
    private Resource fileResource;

    public FileOperator() {
    }


    // get csv file
    public InputStream getCSVFile() throws IOException {
        fileResource = new ClassPathResource("/assets/user.csv");
        return  fileResource.getInputStream();
    }

    //write csv file
    public void writeCSV(String data) throws IOException{
        fileResource = new ClassPathResource("/assets/user.csv");
        FileWriter dataWriter = new FileWriter(new ClassPathResource("assets/user.csv").getFile());
        dataWriter.append(data);
        dataWriter.flush();
    }

}
