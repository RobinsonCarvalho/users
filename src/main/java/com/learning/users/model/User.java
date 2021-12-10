package com.learning.users.model;
import com.sun.jdi.IntegerValue;

import java.util.Date;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String gender;
    private String email;
    private String maritalStatus;
    private Integer idPartner;

    public Integer getId() { return id; }

    public void setId(Integer id) {	this.id = id;}

    public String getFirstName() { return firstName;  }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;	}

    public Date getBirthDay() {return birthDay;	}

    public void setBirthDay(Date birthDay) {this.birthDay = birthDay;}

    public String getGender() {return gender;}

    public void setGender(Gender gender) { this.gender = gender.description; }

    public String getEmail() { return email; }

    public void setEmail(String email) {this.email = email;	}

    public String getMaritalStatus() {	return maritalStatus;	}

    public void setMaritalStatus(MaritalStatus maritalStatus) { this.maritalStatus = maritalStatus.description; }

    public Integer getIdPartner() {	return idPartner;}

    public void setIdPartner(Integer idPartner) {this.idPartner = idPartner;}

    public enum MaritalStatus {

        SINGLE("single"),
        MARRIED("married"),
        DIVORCED("divorced"),
        WIDOW("widow");

        private String description;

        MaritalStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum Gender {

        MALE("male"),
        FEMALE("female"),
        UNDEFINED("undefined");

        private String description;

        Gender(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

}

