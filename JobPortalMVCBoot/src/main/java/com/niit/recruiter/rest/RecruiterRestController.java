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
import com.niit.recruiter.repository.UsersRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/recruiter")
public class RecruiterRestController {
	@Autowired
	private UsersRepository usersRepo;
	private Recruiter removeMapping( Recruiter recruiter)
	{
		recruiter.setUsers(null);
		recruiter.setJobList(null);
		return recruiter;
	}
	
	
	@PostMapping("/recruiterProfile")
	public Map<String, String> recruietrProfile(@RequestBody Users users) {
		Map<String,String> response=new HashMap<String, String>();
		System.out.println(users.getId());
		users=usersRepo.findById(users.getId()).get();
		Recruiter recruiter=users.getRecruiter();
		System.out.println("recruiter "+recruiter);
		System.out.println("users "+users);
		response.put("firstName",recruiter.getFirstName());
		response.put("lastName",recruiter.getLastName());
		response.put("email",users.getEmail());
		return response;
	}


	
	
}
