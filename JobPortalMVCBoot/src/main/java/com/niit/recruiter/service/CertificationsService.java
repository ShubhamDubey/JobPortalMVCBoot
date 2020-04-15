package com.niit.recruiter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.Certifications;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.repository.CertificationsRepository;


@Service
@Transactional
public class CertificationsService {
	
	@Autowired
	private CertificationsRepository certRepo;

	public List<Certifications> findByJobSeeker(JobSeeker activeUser) {
		// TODO Auto-generated method stub
		return certRepo.findByJobSeeker(activeUser);

	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		certRepo.deleteById(id);
	}

	

}
