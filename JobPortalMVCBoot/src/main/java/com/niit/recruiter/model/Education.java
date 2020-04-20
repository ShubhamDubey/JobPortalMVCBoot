package com.niit.recruiter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"education_category_education_category_id","job_seeker_id"}) )
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int educationId;
	private String course;
	private String courseType;
	private String specialisation;
	private String university;
	private String passingYear;
	
	
	private Float percentage;
	@ManyToOne()
	private EducationCategory educationCategory;
	
	@ManyToOne()
	
	private JobSeeker jobSeeker;
	

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public EducationCategory getEducationCategory() {
		return educationCategory;
	}

	public void setEducationCategory(EducationCategory educationCategory) {
		this.educationCategory = educationCategory;
	}

	public Education() {
		super();
		educationCategory=new EducationCategory();
		// TODO Auto-generated constructor stub
	}

	public Education(String course, String courseType, String specialisation, String university, String passingYear) {
		super();
		this.course = course;
		this.courseType = courseType;
		this.specialisation = specialisation;
		this.university = university;
		this.passingYear = passingYear;
	}

	public Education(int educationId, String course, String courseType, String specialisation, String university,
			String passingYear) {
		super();
		this.educationId = educationId;
		this.course = course;
		this.courseType = courseType;
		this.specialisation = specialisation;
		this.university = university;
		this.passingYear = passingYear;
	}

	public int getEducationId() {
		return educationId;
	}

	public void setEducationId(int educationId) {
		this.educationId = educationId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}

	public Education(String course, String courseType, String specialisation, String university, String passingYear,
			Float percentage) {
		super();
		this.course = course;
		this.courseType = courseType;
		this.specialisation = specialisation;
		this.university = university;
		this.passingYear = passingYear;
		this.percentage = percentage;
	}
	
}
