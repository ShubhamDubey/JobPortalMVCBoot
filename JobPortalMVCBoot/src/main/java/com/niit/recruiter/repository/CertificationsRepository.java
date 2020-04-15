package com.niit.recruiter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Certifications;
import com.niit.recruiter.model.JobSeeker;

public interface CertificationsRepository extends JpaRepository<Certifications, Integer> {

	List<Certifications> findByJobSeeker(JobSeeker activeUser);

}
