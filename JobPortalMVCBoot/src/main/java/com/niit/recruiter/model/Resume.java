package com.niit.recruiter.model;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "resume",uniqueConstraints= @UniqueConstraint(columnNames = {"jobSeeker", "fileName"}))

public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fileName;
	private String fileType;
	
	@Lob
	private byte[] data;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobSeeker")
	@JsonIgnore
	private JobSeeker jobSeeker;


	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Resume() {
		super();
	}

	public Resume(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Resume [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + "]";
	}
	
	
	
	
	

}
