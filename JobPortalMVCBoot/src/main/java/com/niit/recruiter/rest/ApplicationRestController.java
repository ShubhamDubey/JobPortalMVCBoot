package com.niit.recruiter.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.repository.ApplicationRepository;
import com.niit.recruiter.repository.JobRepository;


@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/application")
public class ApplicationRestController {

	@Autowired
	private JobRepository jobRepo;
	@Autowired
	private ApplicationRepository applicationRepo;
	
	private List<Application> removeMapping(List <Application> applicationList)
	{
		for(Application application:applicationList)
		{
			//FirstName,LAstname,Email,Resume
			application.setJob(null);
			application.getJobSeeker().setAppliedJobs(null);
			application.getJobSeeker().getUsers().setJobseeker(null);
			application.getJobSeeker().setCertificationsList(null);
			application.getJobSeeker().setEducationSet(null);
			application.getJobSeeker().setSkillList(null);
		}
		return applicationList;
	}
	@PostMapping("/applicantDetails")
	public List<Application> applicantDetails(@RequestBody Map<String,String> jobId) {
		System.out.println("Application Called");
		Integer id=Integer.parseInt(jobId.get("id"));
		Job job=jobRepo.findById(id).get();
		List<Application> applicationList=job.getApplicaionsList();
		return removeMapping(applicationList);
	
	}

}
