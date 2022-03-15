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
        File file = new File("user.csv");
        return  new FileInputStream(file);
    }

    //write csv file
    public void writeCSV(String data) throws IOException{
        FileWriter dataWriter = new FileWriter("user.csv");
        dataWriter.append(data);
        dataWriter.flush();
    }

}
