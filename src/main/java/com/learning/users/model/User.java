package com.learning.users.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

public class User {

    private int id;

    @Size(min = 3, max = 50, message = "Name has to have between 3 and 50 characters.")
    private String name;

    @Size(min = 3, max = 50, message = "Last name has to have between 3 and 50 characters.")
    private String lastName;

    @Email(message = "Email was not identified as a valid one." )
    private String email;

    @Past(message = "Past date should be informed in this field.")
    private LocalDate dateOfBirth;

    @Size(min = 13, max = 16, message = "Phone number should be in the format: +DDI DDD Phone Number")
    private String phone;

    @URL(message = "The GitHub profile informed could not be verified as a valid one")
    private String gitHubProfile;

    public int getId() { return id; }

    public void setId(int id) {this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGitHubProfile() {
        return gitHubProfile;
    }

    public void setGitHubProfile(String gitHubProfile) {
        this.gitHubProfile = gitHubProfile;
    }

}
