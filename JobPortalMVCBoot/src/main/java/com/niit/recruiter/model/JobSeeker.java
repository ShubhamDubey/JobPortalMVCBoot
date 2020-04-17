package com.niit.recruiter.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "jobseeker",uniqueConstraints= @UniqueConstraint(columnNames = {"id", "users_id"}))

public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id")
	@JsonIgnore
	private Users users;
	// cascade = CascadeType.ALL we can't use this because if we use and user delete
	// Education details Delete our record delete
	@OneToMany(targetEntity=Education.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "job_seeker_id")
	private List<Education> educationSet;
	
	
	@OneToOne(mappedBy="jobSeeker")
	private Resume resume;
 
	@OneToMany(targetEntity=Skills.class,cascade=CascadeType.ALL)
	@JoinColumn(name="job_seeker_id")
	private List<Skills> skillList;
	
	@OneToMany(targetEntity=Certifications.class,cascade=CascadeType.ALL)
	@JoinColumn(name="job_seeker_id")
	private List<Certifications> certificationsList;

	@OneToMany(targetEntity=Application.class,cascade=CascadeType.ALL)
	@JoinColumn(name="job_seeker_id")
	private List<Application> appliedJobs;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Education> getEducationSet() {
		return educationSet;
	}

	public void setEducationSet(List<Education> educationSet) {
		this.educationSet = educationSet;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public List<Skills> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skills> skillList) {
		this.skillList = skillList;
	}

	public List<Certifications> getCertificationsList() {
		return certificationsList;
	}

	public void setCertificationsList(List<Certifications> certificationsList) {
		this.certificationsList = certificationsList;
	}

	public List<Application> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(List<Application> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}
	
	
	

}
