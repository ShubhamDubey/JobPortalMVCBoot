package com.niit.recruiter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.Recruiter;
import com.niit.recruiter.repository.JobRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/jobs")
public class JobController {

	@Autowired
	private JobRepository jobRepo;

	@GetMapping("")
	public List<Job> jobListPostedByRecruiter(@RequestBody Recruiter recruiter) {
		System.out.println(recruiter.getFirstName());
		List<Job> jobList=jobRepo.findByRecruiter(recruiter);
		return jobList;
	}

}
