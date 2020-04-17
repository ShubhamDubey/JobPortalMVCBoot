package com.niit.recruiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {

}
