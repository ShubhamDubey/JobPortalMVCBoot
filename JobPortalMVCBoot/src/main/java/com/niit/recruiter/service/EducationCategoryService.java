package com.niit.recruiter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.EducationCategory;
import com.niit.recruiter.repository.EducationCategoryRepository;

@Service
@Transactional
public class EducationCategoryService {

	@Autowired
	private EducationCategoryRepository educationCategoryRepo;
	
	public List<EducationCategory> saveAll(List<EducationCategory> educationCategoryList)
	{
		return educationCategoryRepo.saveAll(educationCategoryList);
	}

	public EducationCategory findByEducationCategoryName(String educationCategoryName) {
		// TODO Auto-generated method stub
		return educationCategoryRepo.findByEducationCategoryName(educationCategoryName).get();
	}

	public List<EducationCategory> findAll() {
		// TODO Auto-generated method stub
		return educationCategoryRepo.findAll();
	}

	public List<EducationCategory> findAllByOrderByEducationCategoryIdAsc() {
		// TODO Auto-generated method stub
		return educationCategoryRepo.findAllByOrderByEducationCategoryIdAsc();
	}
	
}
