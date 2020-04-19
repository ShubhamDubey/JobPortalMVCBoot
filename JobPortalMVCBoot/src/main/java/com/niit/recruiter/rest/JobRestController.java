package com.niit.recruiter.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("")
	public List<Job> jobListing()
	{
		return jobRepo.findAll();
	}
	@PostMapping("/showPostedJobs")
	public List<Job> jobListPostedByRecruiter(@RequestBody Recruiter recruiter) {
		System.out.println(recruiter.getFirstName());
		List<Job> jobList=jobRepo.findByRecruiterOrderByAdvertiseDateAsc(recruiter);
		return jobList;
	}
	
	
	
	@PostMapping("/postjob")
	public List<Job> postJob(@RequestBody Recruiter recruiter)
	{System.out.println("postJob from JobRestController");
		Recruiter recruiterData=recruiterRepo.findById(recruiter.getId()).get();
		System.out.println(recruiter.getId());
		List<Job> postedJobList=new ArrayList<Job>();
		for(Job job:recruiter.getJobList())
		{System.out.println("Hello");
			System.out.println(job.getName());
//			job.g
//			job.setAdvertiseDate(new Date());
			job.setRecruiter(recruiterData);
			postedJobList.add(job);
			
		}
		
//		recruiter.getJobList().get(0).setRecruiter(recruiterData);
		recruiterData.getJobList().addAll(jobRepo.saveAll(postedJobList));

		return jobRepo.findByRecruiter(recruiterRepo.save(recruiterData));
	}

}
