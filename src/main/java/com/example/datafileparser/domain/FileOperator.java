package com.example.datafileparser.domain;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperator {


    //file resource object
    private Resource fileResource;

    public FileOperator() {
    }

    //get json file
    public FileInputStream getJSONFile() throws IOException {
        fileResource = new ClassPathResource("assets/user.json");
        return new FileInputStream(fileResource.getFile());
    }

    // get xml file
    public FileInputStream getXMLFile() throws IOException {
        fileResource = new ClassPathResource("assets/user.xml");
        return new FileInputStream(fileResource.getFile());
    }

    // get sql file
    public FileInputStream getSQLFile() throws IOException {
        fileResource = new ClassPathResource("assets/user.sql");
        return new FileInputStream(fileResource.getFile());
    }

    // get csv file
    public FileInputStream getCSVFile() throws IOException {
        fileResource = new ClassPathResource("assets/user.csv");
        return new FileInputStream(fileResource.getFile());
    }

    //write csv file
    public void writeCSV(String data) throws IOException{
        FileWriter dataWriter = new FileWriter(new ClassPathResource("assets/user.csv").getFile());
        dataWriter.append(data);
        dataWriter.flush();
    }

}