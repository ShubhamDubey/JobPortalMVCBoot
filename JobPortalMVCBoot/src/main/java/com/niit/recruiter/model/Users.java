package com.niit.recruiter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String email;
	private String password;
	@OneToOne(mappedBy = "users")
	private JobSeeker jobseeker;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JobSeeker getJobseeker() {
		return jobseeker;
	}
	public void setJobseeker(JobSeeker jobseeker) {
		this.jobseeker = jobseeker;
	}
	
	
}
