package com.health.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Archives {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="archive_id")
	private int archiveId;

	@Column(name="consultation_type")
	private String consultationType;

	@Column(name="first_name")
	private String firstName;

	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="description")
	private String description;
	
	@Column(name="doctor_name")
	private String dfirstname;
	
	@Column(name="doctor_id")
	private String doctorId;

	@Column(name="doctor_phone")
	private String dphoneNumber;
	
	public String getDphoneNumber() {
		return dphoneNumber;
	}

	public void setDphoneNumber(String dphoneNumber) {
		this.dphoneNumber = dphoneNumber;
	}

	public String getDfirstname() {
		return dfirstname;
	}

	public void setDfirstname(String dfirstname) {
		this.dfirstname = dfirstname;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public int getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(int archiveId) {
		this.archiveId = archiveId;
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



	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	


}
