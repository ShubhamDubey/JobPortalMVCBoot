package com.niit.recruiter.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.Recruiter;

@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/recruiter")
public class RecruiterRestController {

	private Recruiter removeMapping( Recruiter recruiter)
	{
		recruiter.setUsers(null);
		recruiter.setJobList(null);
		return recruiter;
	}
	
}
