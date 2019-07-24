package com.health.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class MedicalsystemApplication {
	@Bean
	public ModelAndView getModel() {
		return new ModelAndView();
	}

	public static void main(String[] args) {
		SpringApplication.run(MedicalsystemApplication.class, args);
	}

}
