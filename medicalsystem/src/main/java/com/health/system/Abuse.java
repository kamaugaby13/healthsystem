package com.health.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="abuses")
public class Abuse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="description")
	private String description;
	
	@Column(name="doctor_id")
	private String doctorId;
	
	@Column(name="doctor_name")
	private String dfirstName;

	

   public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDfirstName() {
		return dfirstName;
	}

	public void setDfirstName(String dfirstName) {
		this.dfirstName = dfirstName;
	}
	
	

}
