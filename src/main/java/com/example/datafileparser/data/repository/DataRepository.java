package com.example.datafileparser.data.repository;

import com.example.datafileparser.data.model.User;
import com.example.datafileparser.domain.FileRetriever;
import com.example.datafileparser.domain.UserParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataRepository {

    private final FileRetriever retriever;
    private BufferedReader dataBuffer;
    public ArrayList<User> userList;

    public DataRepository() {
        retriever = new FileRetriever();
    }

    public ArrayList<User> fetchCSV() throws IOException {
        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(retriever.getCSVFile()));
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

    public ArrayList<User> fetchXML() throws Exception{

        //refresh list
        userList = new ArrayList<>();

        // instantiate document factory
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        //process xml securely (optional setting)
        builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse the file
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        //create document from file
        Document dataDoc  = documentBuilder.parse(retriever.getXMLFile());

        //normalize format (optional setting)
        dataDoc.getDocumentElement().normalize();

        //create list of nodes
        NodeList dataNodes = dataDoc.getElementsByTagName("record");

        // loop through every item
        for (int i = 0; i < dataNodes.getLength(); i++) {

            // individual node
            Node node = dataNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE){

                Element dataElement = (Element) node;

                String[] userData = new String[6];
                userData[0] = dataElement.getElementsByTagName("id").item(0).getTextContent();
                userData[1] = dataElement.getElementsByTagName("first_name").item(0).getTextContent();
                userData[2] = dataElement.getElementsByTagName("last_name").item(0).getTextContent();
                userData[3] = dataElement.getElementsByTagName("email").item(0).getTextContent();
                userData[4] = dataElement.getElementsByTagName("gender").item(0).getTextContent();
                userData[5] = dataElement.getElementsByTagName("phone").item(0).getTextContent();

                userList.add( new UserParser(userData).parseUser());

            }

        }


        return userList;
    }

    public ArrayList<User> fetchJSON() throws IOException{
        //refresh list
        userList = new ArrayList<>();

        // build gson
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        //create gson object
        Gson gson = builder.create();

        // read input stream using a buffered reader
        dataBuffer = new BufferedReader(new InputStreamReader(retriever.getJSONFile()));

        StringBuilder dataBuilder = new StringBuilder();

        // loop through every line
        String line;
        while((line =  dataBuffer.readLine()) != null){
            dataBuilder.append(line);
        }

        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
        userList = gson.fromJson(dataBuilder.toString().trim(), userListType);


        return userList;
    }

}
