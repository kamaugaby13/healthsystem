package com.health.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diet")
public class DietController {
	@Autowired
    DietService service;
	@RequestMapping("/dietentry")
	public String enterDiet() {
		
	return "DietEntryPage";
	}
	@RequestMapping("/savediet")
	public String saveDiet(Diet diet,Model model) {
	String message=service.saveDiet(diet);
		model.addAttribute("message", message);
		return "DietEntryPage";
		
	} 
	
	@GetMapping("/dietdisplay")
	public String display(String condition,Model model) {
		String message="Diet for the condition doesn't exist";
	Diet diet=service.getDiet(condition);
	if(diet!=null) {
	model.addAttribute("diet", diet);
	}else {
		model.addAttribute("message",message);
	}
	return "diet";
}
}