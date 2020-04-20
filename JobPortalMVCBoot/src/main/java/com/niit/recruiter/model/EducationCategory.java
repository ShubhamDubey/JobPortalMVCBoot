package com.niit.recruiter.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "educationCategory", uniqueConstraints = { @UniqueConstraint(columnNames = "educationCategoryId"),
		@UniqueConstraint(columnNames = "educationCategoryName") })
public class EducationCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int educationCategoryId;

	private String educationCategoryName;
	@OneToMany(targetEntity=Education.class)

	@JoinColumn(name="education_category_education_category_id")
	private List<Education> educationList;
	
	public int getEducationCategoryId() {
		return educationCategoryId;
	}

	public void setEducationCategoryId(int educationCategoryId) {
		this.educationCategoryId = educationCategoryId;
	}

	public String getEducationCategoryName() {
		return educationCategoryName;
	}

	public void setEducationCategoryName(String educationCategoryName) {
		this.educationCategoryName = educationCategoryName;
	}

	public EducationCategory(String educationCategoryName, Set<Education> education) {
		super();
		this.educationCategoryName = educationCategoryName;

	}

	public EducationCategory(int educationCategoryId, String educationCategoryName, Set<Education> education) {
		super();
		this.educationCategoryId = educationCategoryId;
		this.educationCategoryName = educationCategoryName;

	}

	public EducationCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EducationCategory(String educationCategoryName) {
		super();
		this.educationCategoryName = educationCategoryName;
	}

	@Override
	public String toString() {
		return "EducationCategory [educationCategoryId=" + educationCategoryId + ", educationCategoryName="
				+ educationCategoryName + "]";
	}



	

}