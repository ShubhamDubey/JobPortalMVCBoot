package com.niit.recruiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {

	

}
