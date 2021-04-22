package com.androidapp.airqualitytracker;

/**
 * This class represents a user
 */
public class User {
    //User info
    private int id;
    private String username, email, password;

    //Empty Constructor
    public User(){
        //
    }

    //Constructor with parameters
    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //Setters and Getters for the user info

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
