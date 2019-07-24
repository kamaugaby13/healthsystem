package com.health.system;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepo extends JpaRepository<Diet,Integer>{

public Diet findByCondition(String condition);

}
