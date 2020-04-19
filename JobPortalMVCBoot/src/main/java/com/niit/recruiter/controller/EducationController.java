package com.niit.recruiter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Education;
import com.niit.recruiter.model.EducationCategory;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.service.EducationCategoryService;
import com.niit.recruiter.service.EducationService;
import com.niit.recruiter.service.JobSeekerService;

@Controller
public class EducationController {
	@Autowired
	private JobSeekerService jobSeekerService;



	@Autowired
	private EducationService educationService;
	@Autowired
	private EducationCategoryService educationCategoryService;

	@RequestMapping("/showEducationForm")
	public ModelAndView showEducationForm(HttpServletRequest request) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			List<Education> educationList=educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
			
			model = new ModelAndView("education-form");
			List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
			model.addObject("eduCat", eduCat);
			model.addObject("education", new Education());
			model.addObject("educationList", educationList);
		}

		return model;
	}

	@PostMapping("/saveEducation")
	public ModelAndView addEducation(HttpServletRequest request, @ModelAttribute("education") Education education) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		try {
			
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());

			} else {
				model = new ModelAndView("education-form");
				System.out.println();
				model.addObject("msg",
						"Sucessfully  " + education.getEducationCategory().getEducationCategoryName() + " Record");

				JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
				String submittedEducationCategory = education.getEducationCategory().getEducationCategoryName();
				EducationCategory educationCategory = educationCategoryService
						.findByEducationCategoryName(submittedEducationCategory);

				education.setEducationCategory(educationCategory);
				List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
				model.addObject("eduCat", eduCat);
				jobSeeker.getEducationSet().add(educationService.save(education));
				jobSeekerService.saveJobSeeker(jobSeeker);
				List<Education> educationList=educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
				model.addObject("educationList", educationList);
			}
		} catch (Exception e) {
			model = new ModelAndView("education-form");
			List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
			model.addObject("eduCat", eduCat);
			List<Education> educationList=educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
			model.addObject("educationList", educationList);
			model.addObject("msg", "Already have" + education.getCourse());
		}

		return model;
	}


	
	
	@RequestMapping("/deleteEducation")
	public ModelAndView deleteEducation(HttpServletRequest request,@RequestParam("educationId") Integer Id)
	{
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		ModelAndView model = null;
		try {
			
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());

			} else {
				model = new ModelAndView("education-form");
				JobSeeker jobSeeker=jobSeekerService.findById(activeUser.getId());
				List<Education> educationList=educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
				
				Education education=educationList.get(Id);
				if(educationList.remove(Id))
				{
					System.out.println("Updated List Set");
					jobSeeker.setEducationSet(educationList);	
				}
				educationService.deleteById(education.getEducationId());
				
				jobSeekerService.saveJobSeeker(jobSeeker);
				 educationList=educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
				model.addObject("educationList", educationList);
				List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
				model.addObject("eduCat", eduCat);
			}
		} catch (Exception e) {
			model = new ModelAndView("education-form");
			List<Education> educationList=educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
				model.addObject("educationList", educationList);
				List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
				model.addObject("eduCat", eduCat);
					}

		return model;
		
	}
	
	@RequestMapping("/update")
	public ModelAndView updateEducation(HttpServletRequest request)
	{
		ModelAndView model=null;
		JobSeeker jobSeeker=(JobSeeker)request.getSession().getAttribute("userId");
		if(jobSeeker==null)
		{
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());

		}
		else
		{
			model=new ModelAndView("education-form");
//			System.out.println(education.getPassingYear());
			String [] row=request.getParameterValues("course");
			for(String row1:row)
				System.out.println(row1);
			
			//System.out.println("Printed Data"+request.getParameter(value));
		}			
		return model;
	}
}
