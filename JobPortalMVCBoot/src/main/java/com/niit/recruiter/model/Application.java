package com.niit.recruiter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="applied_job",uniqueConstraints= @UniqueConstraint(columnNames = {"id","job_id","job_seeker_id","status"}))
public class Application {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date appliedDate;
	
	@ManyToOne()
	private Job job;

	@ManyToOne()
	private JobSeeker jobSeeker;

	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public Job getJob() {
		return job;
	}
	public void setJob(Job jobId) {
		this.job = jobId;
	}
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public Application() {}
	public Date getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	@Override
	public String toString() {
		return "Application [id=" + id + ", appliedDate=" + appliedDate + ", status=" + status + "]";
	}
	public Application(Date appliedDate, Boolean status) {
		super();
		this.appliedDate = appliedDate;
		this.status = status;
	}
	
	
}
