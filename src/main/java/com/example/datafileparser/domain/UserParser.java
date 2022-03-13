package com.example.datafileparser.domain;


import com.example.datafileparser.data.model.User;

public class UserParser {

    //user data
    private final String[] users;

    public UserParser(String[] users) {
        this.users = users;
    }

    public User parseUser(){
        User currUser = new User();
        currUser.setId(Integer.parseInt(users[0]));
        currUser.setFirst_name(users[1]);
        currUser.setLast_name(users[2]);
        currUser.setEmail(users[3]);
        currUser.setGender(users[4]);
        currUser.setPhone(users[5]);
        return currUser;
    }

}
