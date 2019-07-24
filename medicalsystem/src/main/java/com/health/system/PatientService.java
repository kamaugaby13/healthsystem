package com.health.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

	@Autowired
	PatientRepo prepo;

	@Autowired 
	ConsultationRepo crepo;
	
	@Autowired
	ReplyRepo repo;
	
	public void savePatient(Patient patient) {
		prepo.save(patient);
		}

	public Patient loginPatient(String phoneNumber, String password) {
		return prepo.findByPhoneNumberAndPassword(phoneNumber, password);
		}

	public String saveConsult(Consultation consult) {
		crepo.save(consult);
		String message=""; 
		if(crepo.save(consult)!=null) {
		message="Sent! please wait for the doctor's reply"	;
		
		}else {
		message="failed! please resend your request again";	
		}
		return message;
	}

	public List<Reply> getReply(String phoneNumber) {
		
		return repo.findByPatientphoneNumber(phoneNumber);
		
	}

	public Patient findPatient(String phoneNumber) {
		
		return prepo.findByPhoneNumber(phoneNumber);
	}
    
	public List<Patient> findAllPatients() {
		
		return prepo.findAll();
	}

	public void deletePatients(String phoneNumber) {
		prepo.deleteByPid(phoneNumber);
		
	}


	public String check(String phoneNumber) {
		String valid="";
		prepo.findByPhoneNumber(phoneNumber);
		if(prepo.findByPhoneNumber(phoneNumber)!=null)
		{
		valid="yes";
		}else {
			valid="no";
		}
		return valid;
	}

	public List<Patient> findAllPatients(String phoneNumber) {
		
		return prepo.findByPhoneNumbers(phoneNumber);
	}

	public Patient patient(String phoneNumber) {
		
		return prepo.findPat(phoneNumber);
	}
	}
