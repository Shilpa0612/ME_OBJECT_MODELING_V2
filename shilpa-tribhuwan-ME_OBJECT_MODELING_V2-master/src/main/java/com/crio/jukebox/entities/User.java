package com.crio.jukebox.entities;

//import java.util.ArrayList;
//import java.util.List;

public class User {
    String userId;
    String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return userId;
    }

    @Override
    public String toString() {
        return "entities.User: " + userName;
    }
}
