package com.niit.recruiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Resume;

public interface ResumeRespository extends JpaRepository<Resume, Integer> {

}
