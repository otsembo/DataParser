package com.example.datafileparser.data.repository;

import com.example.datafileparser.common.Constants;
import com.example.datafileparser.data.model.User;
import com.example.datafileparser.domain.FileOperator;
import com.example.datafileparser.domain.UserParser;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataRepository {

    public final FileOperator operator;
    public BufferedReader dataBuffer;
    public ArrayList<User> userList;
    public int newest_id = 0;

    public DataRepository() {
        operator = new FileOperator();
        try{
            fetchCSV();
        }catch (IOException ignored){}
    }


    //CSV operations
    public ArrayList<User> fetchCSV() throws IOException {
        int uids = 0;
        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(operator.getCSVFile()));
        //refresh user list
        userList = new ArrayList<>();

        // loop through every line
        int i = 0;
        String line;
        while((line =  dataBuffer.readLine()) != null){
            //remember first line has header
            if(i != 0) {
                User mUser = new UserParser(line.split(",")).parseUser();
                userList.add(mUser);
                uids = mUser.id + 1;
            }
            i++;
        }

        newest_id = uids;

        return userList;
    }

    public ArrayList<User> updateCSV(User user) throws IOException{

        int uids = 0;
        int id = user.getId();
        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(operator.getCSVFile()));
        // loop through every line
        int i = 0;
        String line;
        StringBuilder buffer = new StringBuilder();
        while((line =  dataBuffer.readLine()) != null){

            if(i!=0){
                String[] dataLine = line.split(",");
                try{
                    int myId = Integer.parseInt(String.valueOf(dataLine[0]));

                    if(myId == id){
                        buffer.append(user).append(System.lineSeparator());
                    }else{
                        buffer.append(line).append(System.lineSeparator());
                    }

                    uids = myId + 1;

                } catch (Exception e){
                    System.out.println("NaN");
                }
            }else{

                buffer.append(Constants.CSV_HEADER).append(System.lineSeparator());

            }

            i++;
        }


        if(user.id == newest_id) buffer.append(user).append(System.lineSeparator());

        newest_id = uids;

        operator.writeCSV(buffer.toString().trim());

        return fetchCSV();
    }

    public ArrayList<User> deleteFromCSV(int id) throws IOException{
        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(operator.getCSVFile()));
        // loop through every line
        int i = 0;
        String line;
        StringBuilder buffer = new StringBuilder();
        while((line =  dataBuffer.readLine()) != null){

            String[] dataLine = line.split(",");
            if(i!=0){
                try{
                    int myId = Integer.parseInt(String.valueOf(dataLine[0]));

                    if(myId != id){
                        buffer.append(line).append(System.lineSeparator());
                    }

                } catch (Exception e){
                    System.out.println("NaN");
                }
            }else{
                buffer.append(Constants.CSV_HEADER).append(System.lineSeparator());
            }

            i++;


        }
        operator.writeCSV(buffer.toString().trim());
        return fetchCSV();
    }



}
