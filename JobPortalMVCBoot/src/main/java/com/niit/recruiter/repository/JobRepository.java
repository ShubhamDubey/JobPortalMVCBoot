package com.niit.recruiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Job;

public interface JobRepository extends JpaRepository<Job,Integer > {

}
