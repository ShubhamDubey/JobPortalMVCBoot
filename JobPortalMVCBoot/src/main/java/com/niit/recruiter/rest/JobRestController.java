package com.niit.recruiter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.Recruiter;
import com.niit.recruiter.repository.ApplicationRepository;
import com.niit.recruiter.repository.JobRepository;
import com.niit.recruiter.repository.JobSeekerRepository;
import com.niit.recruiter.repository.RecruiterRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

	@Autowired
	private JobRepository jobRepo;
	@Autowired
	private RecruiterRepository recruiterRepo;
	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	@Autowired
	private ApplicationRepository applicationRepo;
	
	@PostMapping("/showPostedJobs")
	public List<Job> jobListPostedByRecruiter(@RequestBody Recruiter recruiter) {
		System.out.println(recruiter.getFirstName());
		List<Job> jobList=jobRepo.findByRecruiter(recruiter);
		return jobList;
	}
	@PostMapping("/postJob")
	public List<Job> postJob(@RequestBody Recruiter recruiter)
	{
		return jobRepo.findByRecruiter(recruiterRepo.save(recruiter));
	}

}
