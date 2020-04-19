package com.niit.recruiter.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public List<Job> postJob(@RequestBody Map<String,String> job)
	{
		Integer recruiterId=Integer.parseInt(job.get("recruiter"));
		Date advertiseDate=new Date(job.get("advertiseDate"));
		Date expireDate =new Date(job.get("expireDate"));
		String description=job.get("description");
		String companyName=job.get("employerEmail");
		String logoPath=job.get("log");
		String title=job.get("name");
		String salary=job.get("salary");
		
		Recruiter recruiter=recruiterRepo.findById(recruiterId).get();
		System.out.println(recruiter);
		Job postJob=new Job();
		postJob.setDescription(description);
		postJob.setAdvertiseDate(advertiseDate);
		postJob.setEmployerEmail(companyName);
		postJob.setExpireDate(expireDate);
		postJob.setLogo(logoPath);
		postJob.setName(title);
		postJob.setSalary(salary);
		postJob.setRecruiter(recruiter);
		System.out.println(postJob);
		recruiter.getJobList().add(jobRepo.save(postJob));
		return jobRepo.findByRecruiter(recruiterRepo.save(recruiter));
	}

}
