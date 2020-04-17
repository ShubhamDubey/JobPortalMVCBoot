package com.niit.recruiter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.Recruiter;

public interface JobRepository extends JpaRepository<Job,Integer > {

	List<Job> findByRecruiter(Recruiter recruiter);

}
