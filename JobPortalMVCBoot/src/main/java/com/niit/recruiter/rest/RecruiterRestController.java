package com.niit.recruiter.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/recruiter")
public class RecruiterRestController {

	public void RecruiterRestController() {
		System.out.println("Chal gaya");
	}

}
