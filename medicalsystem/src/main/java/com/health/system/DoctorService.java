package com.health.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

	@Autowired
	ReplyRepo repo;

	@Autowired
	DoctorRepo drepo;
	
	@Autowired
	ConsultationRepo crepo;
	
	@Autowired
	DieticianRepo dietRepo;
	
	@Autowired
	ChiefHealthRepo chrepo;
	
	@Autowired
	ArchivesRepo arepo;
	
    @Autowired
	AbuseRepo abuseRepo;
    
	public String saveReply(Reply reply) {
		String message="";
		repo.save(reply);
		if(repo.save(reply)!=null) {
			message="reply sent! thanks alot";
					}
		else {
			message="failed to send! try again";
		}
		return message;
	   
	}

	public String saveDoctor(Doctor doctor) {
		String message="";
		drepo.save(doctor);
		if(drepo.save(doctor)!=null) {
			message="doctor added!thanks alot";
					}
		else {
			message="failed to add! try again";
		}
		return message;
	}

	public Doctor loginDoctor(String doctorId, String password) {
		
		return drepo.findByDoctorIdAndPassword(doctorId, password);
	}

	public List<Consultation> getConsultations(String speciality) {
	
		return crepo.findByconsultationType(speciality);
	}

	public Dietician loginDietician(String dietcianId, String password) {
	
		return dietRepo.findByDieticianIdAndPassword(dietcianId,password);
	}

	public String saveDietician(Dietician diet) {
	
	String message="";
	dietRepo.save(diet);
	if(dietRepo.save(diet)!=null) {
		message="dietician added!thanks alot";
				}
	else {
		message="failed to add dietician! try again";
	}
	return message;
		
	}

	public ChiefHealth loginDoctorChief(String chiefId, String password) {
		
		return chrepo.findByChiefIdAndPassword(chiefId, password);
		
	}

	public void saveDoctorChief(ChiefHealth health) {
		chrepo.save(health);
		
	}

	public List<Reply> getReviews() {
		
		return repo.findAll();
	}

	public void addArchives(Archives arch) {
		arepo.save(arch);	
	}

	public void deleteConsultation(int id) {
		crepo.deleteById(id);
		
	}

	public Doctor reloginDoctor(String doctorId) {
	
		return drepo.findByDoctorId(doctorId);
	}

	public List<Archives> findAll() {
		return arepo.findAll();	
	}

	public List<Doctor> findAllDoctors() {
		return drepo.findAll();
	}

	public void deleteByDid(String doctorId) {
	drepo.deleteByDid(doctorId);
	}

	public String check(String doctorId) {
		String valid="";
		drepo.findByDoctorId(doctorId);
		if(drepo.findByDoctorId(doctorId)!=null) {
			valid="yes";
		}else {
			valid="no";
		}
		return valid;
	}

	public String checkDiet(String dieticianId) {
		String valid="";
		dietRepo.findByDieticianId(dieticianId);
		if(dietRepo.findByDieticianId(dieticianId)!=null) {
			valid="yes";
		}else {
			valid="no";
		}
		return valid;
	}

	public Doctor findDoctor(String doctorId) {
		return drepo.findByDoctorId(doctorId);
	}

	public ChiefHealth findChief(String chiefId) {
		
		return chrepo.findByChiefId(chiefId);
	}

	public Dietician findDietician(String dieticianId) {
		
		return dietRepo.findByDieticianId(dieticianId);
	}

	public List<Doctor> findAllDoctors(String doctorId) {
		
		return drepo.findByDoctorIds(doctorId);
	}

	public String saveAbuse(Abuse abuse) {
		String message="";
		abuseRepo.save(abuse);
		if(abuseRepo.save(abuse)!=null) {
		message="Abuse Reported successfully!";
		}
		else {
			message="Reporting Failed!";	
		}
		return message;
	}

	public List<Abuse> getAbuses() {
		// TODO Auto-generated method stub
		return abuseRepo.findAll();
	}

	public void deleteAbuse(int id) {
	abuseRepo.deleteByPid(id);
		
	}
	
	
}
