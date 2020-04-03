package com.niit.recruiter.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.LoginUsers;




public interface LoginUserRepository extends JpaRepository<LoginUsers,Integer > {

//	public LoginUsers findByEmailAndPassword(String email,String password);

	public LoginUsers findByEmail(String email);
}
