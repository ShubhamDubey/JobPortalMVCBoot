package com.niit.recruiter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.repository.ApplicationRepository;
import com.niit.recruiter.repository.JobRepository;
import com.niit.recruiter.repository.JobSeekerRepository;


@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/application")
public class ApplicationController {

	@Autowired
	private JobRepository jobRepo;
	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	@Autowired
	private ApplicationRepository applicationRepo;
	
	@PostMapping("/applyJob")
	public List<Application> applyJob(@RequestBody JobSeeker jobSeeker) {
		System.out.println(jobSeeker.getFirstName());
			
		return applicationRepo.findByJobSeeker(jobSeekerRepo.save(jobSeeker));
	}

}
