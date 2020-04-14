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

@Entity
@Table(name="applied_job",uniqueConstraints= @UniqueConstraint(columnNames = {"job_id_id","job_seeker_id"}))
public class Application {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date appliedDate;
	
	@ManyToOne()
	private Job jobId;

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

	
	public Job getJobId() {
		return jobId;
	}
	public void setJobId(Job jobId) {
		this.jobId = jobId;
	}
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public Application() {}
	
	
	
}
