package com.health.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietService {

	@Autowired
	DietRepo drepo;
	public String saveDiet(Diet diet) {
		String message="";
		drepo.save(diet);
		if(drepo.save(diet)!=null) {
			message="saved successfully!";
		}else {
			message="failed to save!";
		}
		return message;
	}
	public Diet getDiet(String condition) {
		return drepo.findByCondition(condition);
	}
	
}
