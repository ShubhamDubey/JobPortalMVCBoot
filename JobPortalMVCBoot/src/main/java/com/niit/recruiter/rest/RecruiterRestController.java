package com.niit.recruiter.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.recruiter.model.Users;
import com.niit.recruiter.repository.UsersRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localHost:8080"})
@RestController
@RequestMapping("/api/recruiter")
public class RecruiterRestController {

	@Autowired
	private UsersRepository usersRepo;


	@PostMapping
	public Map<String,String> profile(@RequestBody Map<String,String> users)
	{
		System.out.println("Recruiter Profile Called");
		Map<String,String> profileResponse=new HashMap<String,String>();
		if(users!=null)
		{
			Integer id=Integer.parseInt(users.get("id"));
			
			Users userObj=usersRepo.findById(id).get();
			profileResponse.put("firstname",userObj.getRecruiter().getFirstName());
			profileResponse.put("lastname",userObj.getRecruiter().getLastName());
			profileResponse.put("password",userObj.getPassword());
			profileResponse.put("email",userObj.getEmail());
			profileResponse.put("id",userObj.getId()+"");
		}
		return profileResponse;
	}	
	
	@PostMapping(value="/updateprofile")
	public Map<String,String> updateProfile(@RequestBody Map<String,String> users)
	{
		System.out.println("Recruiter Update Profile Called");
		Map<String,String> profileResponse=new HashMap<String,String>();
		System.out.println("DATA"+users.get("firstname"));
		if(users!=null)
		{		
			Integer id=Integer.parseInt(users.get("id"));
			String firstName=users.get("firstname");
			String lastName=users.get("lastname");
			String email=users.get("email");
			Users user=usersRepo.findById(id).get();
			
			user.setEmail(email);
			user.getRecruiter().setFirstName(firstName);
			user.getRecruiter().setLastName(lastName);
			usersRepo.save(user);
		}
		else
		{
			users=null;
		}
		return users;
	}	
	@PostMapping("/changepassword")
	public Map<String,String> changePassword(@RequestBody Map<String,String> users)
	{
		System.out.println("Recruiter Update Profile Called");
		Map<String,String> profileResponse=new HashMap<String,String>();
		System.out.println("DATA"+users.get("firstname"));
		if(users!=null)
		{		
			Integer id=Integer.parseInt(users.get("id"));
		
			String password=users.get("updatedpassword");
			Users user=usersRepo.findById(id).get();
			user.setPassword(password);
			usersRepo.save(user);
			users=users;
			
		}
		else
		{
			users=null;
		}
		return users;
	}	
}
