package com.example.datafileparser.data.repository;

import com.example.datafileparser.data.model.User;
import com.example.datafileparser.domain.FileOperator;
import com.example.datafileparser.domain.UserParser;
import java.io.*;
import java.util.ArrayList;

public class DataRepository {

    private final FileOperator operator;
    private BufferedReader dataBuffer;
    public ArrayList<User> userList;

    public DataRepository() {
        operator = new FileOperator();
    }

    //CSV operations
    public ArrayList<User> fetchCSV() throws IOException {
        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(operator.getCSVFile()));
        //refresh user list
        userList = new ArrayList<>();

        // loop through every line
        int i = 0;
        String line;
        while((line =  dataBuffer.readLine()) != null){
            //remember first line has header
            if(i != 0) userList.add(new UserParser(line.split(",")).parseUser());

            i++;
        }

        return userList;
    }

    public ArrayList<User> updateCSV(User user) throws IOException{

        int id = user.getId();
        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(operator.getCSVFile()));
        // loop through every line
        int i = 0;
        String line;
        StringBuilder buffer = new StringBuilder();
        while((line =  dataBuffer.readLine()) != null){

            char first = line.charAt(0);
            try{
                int myId = Integer.parseInt(String.valueOf(first));

                if(myId == id){
                    buffer.append(user).append(System.lineSeparator());
                }else{
                    buffer.append(line).append(System.lineSeparator());
                }

            } catch (Exception e){
                System.out.println("NaN");
            }


            i++;
        }
        operator.writeCSV(buffer.toString().trim());

        return fetchCSV();
    }

    public ArrayList<User> deleteFromCSV(int id) throws IOException{
        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(operator.getCSVFile()));
        // loop through every line
        String line;
        StringBuilder buffer = new StringBuilder();
        while((line =  dataBuffer.readLine()) != null){

            char first = line.charAt(0);
            try{
                int myId = Integer.parseInt(String.valueOf(first));

                if(myId != id){
                    buffer.append(line).append(System.lineSeparator());
                }

            } catch (Exception e){
                System.out.println("NaN");
            }


        }
        operator.writeCSV(buffer.toString().trim());
        return fetchCSV();
    }



}
