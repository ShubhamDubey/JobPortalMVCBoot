package com.niit.recruiter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Skills;

public interface SkillsRepository extends JpaRepository<Skills, Integer> {

	List<Skills> findByJobSeeker(JobSeeker jobSeeker);

	
}
