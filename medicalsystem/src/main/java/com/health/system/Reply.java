package com.health.system;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="replies")
public class Reply {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int replyId;
private String doctorId;
private String solution;
private String patientphoneNumber;
private String doctorfirstName;
private String doctorphoneNumber;
private String firstName;
public String getDoctorfirstName() {
	return doctorfirstName;
}
public void setDoctorfirstName(String doctorfirstName) {
	this.doctorfirstName = doctorfirstName;
}

public String getDoctorId() {
	return doctorId;
}
public void setDoctorId(String doctorId) {
	this.doctorId = doctorId;
}
public String getSolution() {
	return solution;
}
public void setSolution(String solution) {
	this.solution = solution;
}
public String getPatientphoneNumber() {
	return patientphoneNumber;
}
public void setPatientphoneNumber(String patientphoneNumber) {
	this.patientphoneNumber = patientphoneNumber;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getDoctorphoneNumber() {
	return doctorphoneNumber;
}
public void setDoctorphoneNumber(String doctorphoneNumber) {
	this.doctorphoneNumber = doctorphoneNumber;
}

	
}
