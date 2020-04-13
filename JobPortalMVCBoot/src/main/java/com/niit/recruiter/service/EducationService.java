package com.niit.recruiter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.Education;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.repository.EducationRepo;

@Service
@Transactional
public class EducationService {

	@Autowired
	private EducationRepo educationRepo;
	public Education save(Education education)
	{
		return educationRepo.save(education);
	}
	public List<Education> findByJobSeeker(JobSeeker activeUser) {
		// TODO Auto-generated method stub
		return educationRepo.findByJobSeeker(activeUser);
	}
	public List<Education> findByJobSeekerOrderByEducationCategoryAsc(JobSeeker activeUser) {
		// TODO Auto-generated method stub
		return educationRepo.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
	}
	

	public void deleteById(int educationId) {
		// TODO Auto-generated method stub
		 educationRepo.deleteById(educationId);
	}
}
