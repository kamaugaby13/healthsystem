package com.health.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="consultations")
public class Consultation {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="consultation_id")
private int consultationId;

@Column(name="consultation_type")
private String consultationType;

@Column(name="first_name")
private String firstName;

@Column(name="last_name")
private String lastName;

@Column(name="phone_number")
private String phoneNumber;

@Column(name="patient_age")
private int age;

@Column(name="description")
private String description;

public int getConsultationId() {
	return consultationId;
}

public void setConsultationId(int consultationId) {
	this.consultationId = consultationId;
}

public String getConsultationType() {
	return consultationType;
}

public void setConsultationType(String consultationType) {
	this.consultationType = consultationType;
}

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

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}


}
