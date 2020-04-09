package com.niit.recruiter.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.repository.JobSeekerRepository;

@Service
@Transactional
public class JobSeekerService {

	@Autowired
	private JobSeekerRepository repo;

	public void saveJobSeeker(JobSeeker theJobSeeker) {
		// TODO Auto-generated method stub
		repo.save(theJobSeeker);
	}

	public JobSeeker findById(Integer activeUser) {
		// TODO Auto-generated method stub
		return repo.findById(activeUser).get();
	}

}
