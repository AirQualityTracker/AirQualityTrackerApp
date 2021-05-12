package com.androidapp.airqualitytracker;

import java.util.HashSet;

/**
 * This class represents a user
 */
public class User {
    //User info
    private int id;
    //private String username, email, password;
    private HashSet<String> favouritesCities;

    //Empty Constructor
    public User(){
        //
    }

    //Constructor with parameters
    public User(String username, String email, String password){
        /*
        this.username = username;
        this.email = email;
        this.password = password;
         */
        favouritesCities = new HashSet<>();
        favouritesCities.add("New York");
        favouritesCities.add("Athens");
    }

    //Setters and Getters for the user info

    public void setId(int id) {
        this.id = id;
    }

    /*
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCities(HashSet cities) {this.cities = cities;}

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

     */

    public void setFavouritesCities(HashSet cities) {this.favouritesCities = cities;}

    public HashSet getFavouritesCities() {return favouritesCities;}


}
