package com.niit.recruiter.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = { "cat_id","jobSeeker"}) )
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int educationId;
	private String course;
	private String courseType;
	private String specialisation;
	private String university;
	private String passingYear;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cat_id")
	@OrderColumn(name = "education_category_id")
	private EducationCategory educationCategory;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "jobSeeker")
	private JobSeeker jobSeeker;
	

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
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
}