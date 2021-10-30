package com.learning.users.model;

import java.lang.module.FindException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private String emailAddress;
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
