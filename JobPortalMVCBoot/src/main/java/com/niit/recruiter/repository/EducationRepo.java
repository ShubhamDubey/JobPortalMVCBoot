package com.niit.recruiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Education;

public interface EducationRepo extends JpaRepository<Education, Integer> {

}
