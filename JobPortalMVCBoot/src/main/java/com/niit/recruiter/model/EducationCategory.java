package com.niit.recruiter.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="educationCategory",uniqueConstraints= {@UniqueConstraint(columnNames="educationCategoryId"),
		@UniqueConstraint(columnNames="educationCategoryName")})
public class EducationCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int educationCategoryId;

	private String educationCategoryName;
//	@OneToMany(mappedBy="educationSet")
//	private Set<Education> educationSet;
	
	

//	public Set<Education> getEducationSet() {
//		return educationSet;
//	}
//
//	public void setEducationSet(Set<Education> educationSet) {
//		this.educationSet = educationSet;
//	}

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