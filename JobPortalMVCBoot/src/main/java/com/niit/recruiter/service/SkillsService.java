package com.niit.recruiter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Skills;
import com.niit.recruiter.repository.SkillsRepository;

@Service
@Transactional
public class SkillsService {

	@Autowired
	private SkillsRepository skillsRepo;
	public void saveAll(List<Skills> skillsList) {
		// TODO Auto-generated method stub
		skillsRepo.saveAll(skillsList);
	}
	
	

	public List<Skills> findByJobSeeker(JobSeeker jobSeeker) {
		// TODO Auto-generated method stub
		return skillsRepo.findByJobSeeker(jobSeeker);
		
	}



	public void deleteAll(List<Skills> findByJobSeeker) {
		skillsRepo.deleteAll(findByJobSeeker);
		// TODO Auto-generated method stub
		
	}
}
