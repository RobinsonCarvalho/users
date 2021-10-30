package com.learning.users.model;
import java.util.Date;

public class Person {

	private Integer id;
	private String firstName;
	private String LastName;
	private String birthDay;
	private String gender;
	private String email;
	private String maritalStatus;
	private Integer idPartner;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {	this.id = id;}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {return LastName;}

	public void setLastName(String lastName) {lastName = lastName;	}

	public String getBirthDay() {return birthDay;	}

	public void setBirthDay(String birthDay) {this.birthDay = birthDay;}

	public String getGender() {return gender;}

	public void setGender(String gender) {	this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {this.email = email;	}

	public String getMaritalStatus() {	return maritalStatus;	}

	public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;	}

	public Integer getIdPartner() {	return idPartner;}

	public void setIdPartner(Integer idPartner) {this.idPartner = idPartner;}

	public enum Permission {

		ADMINISTRATOR("administrator"),
		MANAGER("manager"),
		DEVELOPER("developer");

		private String description;

		Permission(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}

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

