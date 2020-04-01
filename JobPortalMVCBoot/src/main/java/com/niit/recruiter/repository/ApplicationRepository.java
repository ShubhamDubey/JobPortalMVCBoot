package com.niit.recruiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.recruiter.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
