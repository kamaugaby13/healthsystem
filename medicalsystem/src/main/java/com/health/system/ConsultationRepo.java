package com.health.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultationRepo extends JpaRepository<Consultation,Integer>{

	@Query(value="select * from consultations where consultation_type=?1",nativeQuery=true)
	List<Consultation> findByconsultationType(String speciality);

}
