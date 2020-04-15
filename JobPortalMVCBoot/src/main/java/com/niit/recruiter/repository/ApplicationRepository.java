package com.niit.recruiter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	

	List<Application> findByJobSeeker(JobSeeker jobSeeker);

	List<Application> findByJobSeekerAndJob(JobSeeker jobSeeker, Job job);

	List<Application> findByJobSeekerAndJobAndStatus(JobSeeker jobSeeker, Job job, boolean b);

	List<Application> findByJobSeekerAndStatus(JobSeeker jobSeeker, boolean b);



}
