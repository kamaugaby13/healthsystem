package com.health.system;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiefHealthRepo extends JpaRepository<ChiefHealth,Integer>{
 
public ChiefHealth findByChiefIdAndPassword(String chiefId,String password);

public ChiefHealth findByChiefId(String chiefId);

}
