package com.health.system;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepo extends JpaRepository<Patient,String>{
	
public Patient findByPhoneNumberAndPassword(String phoneNumber,String password);

public Patient findByPhoneNumber(String phoneNumber);

@Modifying
@Transactional
@Query(value="delete from patients where phone_number=?1",nativeQuery=true)
public int deleteByPid(String phoneNumber);

@Query(value="select * from patients where phone_number=?1",nativeQuery=true)
public List<Patient> findByPhoneNumbers(String phoneNumber);

@Query(value="select * from patients where phone_number=?1",nativeQuery=true)
public Patient findPat(String phoneNumber);

}
