package com.health.system;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DieticianRepo extends JpaRepository<Dietician,String>{

public Dietician findByDieticianIdAndPassword(String dietcianId, String password);


public Dietician findByDieticianId(String dieticianId);

}
