package com.niit.recruiter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.Job;
import com.niit.recruiter.repository.JobRepository;

@Service
@Transactional
public class JobService {

	@Autowired(required=true)
	private JobRepository jobRepo;
	
	
	public List<Job> getJobList() {
	
		return jobRepo.findAll();
	}

	

}
