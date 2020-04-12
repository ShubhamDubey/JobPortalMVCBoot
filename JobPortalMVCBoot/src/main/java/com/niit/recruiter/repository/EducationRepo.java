package com.niit.recruiter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Education;
import com.niit.recruiter.model.JobSeeker;

public interface EducationRepo extends JpaRepository<Education, Integer> {

	List<Education> findByJobSeeker(JobSeeker activeUser);

	List<Education> findByJobSeekerOrderByEducationCategoryAsc(JobSeeker activeUser);

}
