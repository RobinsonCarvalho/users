package com.learning.users.model;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (int id){
        super("The user id " + id + " could not be localized.");
    }

}
