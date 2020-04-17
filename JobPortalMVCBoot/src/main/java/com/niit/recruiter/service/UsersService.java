package com.niit.recruiter.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.repository.UsersRepository;


@Service
@Transactional
public class UsersService {
	@Autowired
	private UsersRepository usersRepo;
	
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return usersRepo.findByEmail(email);
	}

	public JobSeeker findByJobSeeker(JobSeeker jobseeker) {
		// TODO Auto-generated method stub
		return null;
	}

}
