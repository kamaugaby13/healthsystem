package com.health.system;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepo extends JpaRepository<Doctor,Integer>{
	
public Doctor findByDoctorIdAndPassword(String doctorId,String passwword);

public Doctor findByDoctorId(String doctorId);

@Modifying
@Transactional
@Query(value="delete from doctors where doctor_id=?1",nativeQuery=true)
public int deleteByDid(String doctorId);

@Query(value="select * from doctors where doctor_id=?1",nativeQuery=true)
public List<Doctor> findByDoctorIds(String doctorId);
}
