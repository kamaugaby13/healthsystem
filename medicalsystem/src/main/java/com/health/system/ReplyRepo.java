package com.health.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepo extends JpaRepository<Reply,Integer>{

public List<Reply> findByPatientphoneNumber(String phoneNumber);

}
