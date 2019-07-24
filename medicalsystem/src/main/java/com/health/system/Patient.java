package com.health.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patients")
public class Patient {


@Column(name="first_name")
private String firstName;

@Column(name="last_name")
private String lastName;

@Id
@Column(name="phone_number")
private String phoneNumber;

@Column(name="patient_age")
private int age;

@Column(name="password")
private String password;


public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


}
