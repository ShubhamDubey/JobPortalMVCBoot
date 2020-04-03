
package com.niit.recruiter.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.repository.ApplicationRepository;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository repo;
	public void saveApplication(Application app) {
		// TODO Auto-generated method stub
		repo.save(app);
	}
	public Application findByJobseekerIdAndJobId(int jobSeekerId,int jobId) {
		return repo.findByJobseekerIdAndJobId(jobSeekerId,jobId);
	}

}
