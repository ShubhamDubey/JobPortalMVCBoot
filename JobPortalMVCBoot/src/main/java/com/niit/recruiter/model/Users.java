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
	private Integer id;
	private String email;
	private String password;
	@OneToOne(mappedBy = "users")
	private JobSeeker jobseeker;

	@OneToOne(mappedBy = "users")
	private Recruiter recruiter;
	
	private String role;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	public Users(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
}
