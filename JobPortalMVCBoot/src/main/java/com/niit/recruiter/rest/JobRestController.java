package com.niit.recruiter.rest;

import java.util.Date;
import java.util.HashMap;
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
import com.niit.recruiter.model.Users;
import com.niit.recruiter.repository.ApplicationRepository;
import com.niit.recruiter.repository.JobRepository;
import com.niit.recruiter.repository.JobSeekerRepository;
import com.niit.recruiter.repository.RecruiterRepository;
import com.niit.recruiter.repository.UsersRepository;

@CrossOrigin(origins = { "http://localhost:4200", "http://localHost:8080" })
@RestController
@RequestMapping("/api/jobs")
public class JobRestController {
	@Autowired
	private UsersRepository usersRepo;
	@Autowired
	private JobRepository jobRepo;
	@Autowired
	private RecruiterRepository recruiterRepo;
	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	@Autowired
	private ApplicationRepository applicationRepo;

	List<Job> removeMapping(List<Job> jobList) {

		for (Job job : jobList) {
			System.out.println(job);
			job.setRecruiter(null);
			job.setApplicaionsList(null);
		}
		return jobList;

	}

	@GetMapping("")
	public List<Job> jobListing() {
		return removeMapping(jobRepo.findAll());
	}

	@PostMapping("/showPostedJobs")
	public /* Map<String, */List<Job>/* > */ jobListPostedByRecruiter(@RequestBody Users recruiter) {
		Map<String, List<Job>> jobList = new HashMap<String, List<Job>>();
		System.out.println(recruiter.getId());
		recruiter = usersRepo.findById(recruiter.getId()).get();
		List<Job> jobListValues = jobRepo.findByRecruiterOrderByAdvertiseDateAsc(recruiter.getRecruiter());
		if(!jobListValues.isEmpty()) {
		for (Job job : jobListValues) {
			System.out.println(job);
			job.setRecruiter(null);
			job.setApplicaionsList(null);
		}}
		jobList.put("list", jobListValues);
		return jobListValues;
	}

	@PostMapping("/postjob")
	public List<Job> postJob(@RequestBody Map<String, String> job) {
		Integer userId = Integer.parseInt(job.get("recruiter"));
		Date advertiseDate = new Date(job.get("advertiseDate"));
		String[] expireDateWithoutFormat = job.get("expireDate").split("-");
		Date expireDate = new Date(
				expireDateWithoutFormat[1] + "/" + expireDateWithoutFormat[2] + "/" + expireDateWithoutFormat[0]);
		String description = job.get("description");
		String companyName = job.get("employerEmail");
		String logoPath = job.get("log");
		String title = job.get("name");
		String salary = job.get("salary");
		String type = job.get("type");
		String vacancy = job.get("vacancy");
		Users user = usersRepo.findById(userId).get();
		Recruiter recruiter = recruiterRepo.findById(user.getRecruiter().getId()).get();
		System.out.println(recruiter);
		Job postJob = new Job();
		postJob.setDescription(description);
		postJob.setAdvertiseDate(advertiseDate);
		postJob.setEmployerEmail(companyName);
		postJob.setExpireDate(expireDate);
		postJob.setLogo("niit-icone.png");
		postJob.setName(title);
		postJob.setType(type);
		postJob.setVacancy(vacancy);
		postJob.setSalary(salary);
		postJob.setRecruiter(recruiter);
		System.out.println(postJob);
		recruiter.getJobList().add(jobRepo.save(postJob));
		List<Job> jobListValues = jobRepo.findByRecruiterOrderByAdvertiseDateAsc(recruiter);
		if (!jobListValues.isEmpty()) {
			for (Job job1 : jobListValues) {
				System.out.println(job1);
				job1.setRecruiter(null);
				job1.setApplicaionsList(null);
			}
		}
		
		return jobListValues;
	}

}
