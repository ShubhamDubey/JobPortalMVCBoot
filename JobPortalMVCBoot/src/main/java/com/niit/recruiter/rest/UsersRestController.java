package com.niit.recruiter.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.Recruiter;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.repository.JobSeekerRepository;
import com.niit.recruiter.repository.RecruiterRepository;
import com.niit.recruiter.repository.UsersRepository;

@CrossOrigin(origins = { "http://localhost:4200", "http://localHost:8080" })
@RestController
@RequestMapping("/api/users")
public class UsersRestController {

	@Autowired
	private JobSeekerRepository jobSeekerRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private RecruiterRepository recruiterRepo;

	@PostMapping("/addJobSeeker")
	public Map<String, String> registrationJobSeeker(@RequestBody Map<String, String> user) {
		System.out.println("Method Called");

		String firstName = user.get("firstName");
		String lastName = user.get("lastName");
		String email = user.get("email");
		String password = user.get("password");
		System.out.println(firstName + "\t" + lastName + "\t" + email + "\t" + password);
//		JobSeeker jobSeeker = user.getJobseeker();
//		jobSeeker.setUsers(user);
//		user.setJobseeker(jobSeeker);
////			System.out.println(jobSeeker.);
////			return new JobSeeker();
//
//		jobSeekerRepo.save(jobSeeker);
		return user;
	}

	@PostMapping("/addRecruiter")
	public Map<String, Integer> registrationRecruiter(@RequestBody Map<String, String> user) {
		Map<String, Integer> registerId = new HashMap<String, Integer>();
		if (!user.isEmpty()) {
			String firstName = user.get("firstName");
			String lastName = user.get("lastName");
			String email = user.get("email");
			String password = user.get("password");

			System.out.println(firstName + "\t" + lastName + "\t" + email + "\t" + password);

			Users users = new Users(email, password, "Recruiter");
			Recruiter recruiter = new Recruiter(firstName, lastName);
			recruiter.setUsers(usersRepo.save(users));
			recruiter = recruiterRepo.save(recruiter);

			registerId.put("Id", recruiter.getUsers().getId());

		}
		return registerId;
	}

	@PostMapping("/login")
	public Map<String, String> userLogin(@RequestBody Map<String, String> users) {

		Map<String, String> response = new HashMap<String, String>();
		Users user = usersRepo.findByEmailAndPassword(users.get("email"), users.get("password"));
		System.out.println("userLoginCalled");
		System.out.println(user);
		if(user!=null) {
		response.put("id", user.getId() + "");
		response.put("role", user.getRole());
		}
		else
		{
			response.put("userId",null);
		}
		return response;

	}

}
