package com.niit.recruiter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="applied_job")
public class Application {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "jobseeker_id")
	private int jobseekerId;
	@Column(name = "job_id")
	private int jobId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJobseekerId() {
		return jobseekerId;
	}
	public void setJobseekerId(int jobseekerId) {
		this.jobseekerId = jobseekerId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Application() {}
	
	
	
}
