package com.niit.recruiter.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.Education;
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
}
