package com.niit.recruiter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Recruiter;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.repository.JobSeekerRepository;
import com.niit.recruiter.repository.RecruiterRepository;
import com.niit.recruiter.repository.UsersRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private JobSeekerRepository jobSeekerRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private RecruiterRepository recruiterRepo;

	@PostMapping("/addJobSeeker")
	public Users registrationJobSeeker(@RequestBody Users user) {
		System.out.println("Method Called");

		System.out.println(user.getJobseeker().getFirstName());
		System.out.println(user.getEmail());
		JobSeeker jobSeeker = user.getJobseeker();
		jobSeeker.setUsers(user);
		user.setJobseeker(jobSeeker);
//			System.out.println(jobSeeker.);
//			return new JobSeeker();

		jobSeekerRepo.save(jobSeeker);
		return user;
	}

	@PostMapping("/addRecruiter")
	public Users registrationRecruiter(@RequestBody Users user) {
		System.out.println("Registration Recruiter");
//		System.out.println(user.getRole());
		Recruiter recruiter = user.getRecruiter();
		recruiter.setUsers(user);
		user.setRecruiter(recruiter);
		recruiterRepo.save(recruiter);
		return user;
	}

	@PostMapping("/login")
	public Users userLogin(@RequestBody Users users) {

		Users user = usersRepo.findByEmailAndPassword(users.getEmail(), users.getPassword());
			System.out.println("Called");
		return user;

	}

}
