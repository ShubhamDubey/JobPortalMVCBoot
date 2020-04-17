
package com.niit.recruiter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.repository.ApplicationRepository;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository repo;
	public Application saveApplication(Application app) {
		// TODO Auto-generated method stub
		return repo.save(app);
	}

	public List<Application> findByJobSeekerAndJob(JobSeeker jobSeeker, Job job) {
		// TODO Auto-generated method stub
		return repo.findByJobSeekerAndJob(jobSeeker,job);
	}

	public List<Application> findByJobSeeker(JobSeeker jobSeeker) {
		// TODO Auto-generated method stub
		return repo.findByJobSeeker(jobSeeker);
	}

	public List<Application> findByJobSeekerAndJobAndStatus(JobSeeker jobSeeker, Job job, boolean b) {
		// TODO Auto-generated method stub
		return repo.findByJobSeekerAndJobAndStatus(jobSeeker,job,b);
	}

	public List<Application> findByJobSeekerAndStatus(JobSeeker jobSeeker, boolean b) {
		// TODO Auto-generated method stub
		return repo.findByJobSeekerAndStatus(jobSeeker, b);
	}

	public Application findByJobSeekerAndJobAndStatus(JobSeeker jobSeeker, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}




}
