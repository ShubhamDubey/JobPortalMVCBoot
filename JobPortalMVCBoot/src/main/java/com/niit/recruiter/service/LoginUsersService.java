package com.niit.recruiter.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.repository.LoginUserRepository;

@Service
@Transactional
public class LoginUsersService {

	@Autowired 
	private LoginUserRepository loginUserRepo;
//	public LoginUsers checkUsers(LoginUsers theLoginUsers) {
//		// TODO Auto-generated method stub
//		return loginUserRepo.findByEmailAndPassword(theLoginUsers.getEmail(),theLoginUsers.getPassword());
//	}
	public LoginUsers findByEmail(String email) {
		// TODO Auto-generated method stub
		return loginUserRepo.findByEmail(email);
	}



}
