package com.health.system;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AbuseRepo extends JpaRepository<Abuse,Integer>{
	@Modifying
	@Transactional
	@Query(value="delete from abuses where id=?1",nativeQuery=true)
	public int deleteByPid(int id);
}
