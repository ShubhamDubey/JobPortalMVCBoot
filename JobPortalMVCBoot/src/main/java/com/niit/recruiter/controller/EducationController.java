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
import com.niit.recruiter.model.Users;
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
			model.addObject("loginusers", new Users());
		} else {
			List<Education> educationList = educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);

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

		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());

		}

		else {

			model = new ModelAndView("education-form");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			model.addObject("education", new Education());
			String submittedEducationCategory = education.getEducationCategory().getEducationCategoryName();
			EducationCategory educationCategory = educationCategoryService
					.findByEducationCategoryName(submittedEducationCategory);
			Education educationStatus = educationService.findByEducationCategoryAndJobSeeker(educationCategory,
					jobSeeker);
			if (educationStatus == null) {
				model.addObject("msg",
						"Sucessfully  " + education.getEducationCategory().getEducationCategoryName() + " Record");

//				JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());

				education.setEducationCategory(educationCategory);
				List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
				model.addObject("eduCat", eduCat);
				jobSeeker.getEducationSet().add(educationService.save(education));
				jobSeekerService.saveJobSeeker(jobSeeker);
				List<Education> educationList = educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
				model.addObject("educationList", educationList);
			} else {

				List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
				model.addObject("eduCat", eduCat);
				List<Education> educationList = educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
				model.addObject("educationList", educationList);
				model.addObject("msg", "Already have" + education.getCourse());

			}
		}

		return model;
	}

	@RequestMapping("/deleteEducation")
	public ModelAndView deleteEducation(HttpServletRequest request, @RequestParam("educationId") Integer Id) {
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		ModelAndView model = null;

		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());

		} else {
			model = new ModelAndView("education-form");
			model.addObject("education", new Education());
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			List<Education> educationList = educationService.findByJobSeekerOrderByEducationCategoryAsc(jobSeeker);

			Education education = educationList.get(Id);
			educationList.remove(Id);
			jobSeeker.setEducationSet(educationList);

			educationService.deleteById(education.getEducationId());

			jobSeekerService.saveJobSeeker(jobSeeker);
			educationList = educationService.findByJobSeekerOrderByEducationCategoryAsc(jobSeeker);
			model.addObject("educationList", educationList);
			List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
			model.addObject("eduCat", eduCat);
		}
//		} catch (Exception e) {
//			model = new ModelAndView("education-form");
//			List<Education> educationList = educationService.findByJobSeekerOrderByEducationCategoryAsc(activeUser);
//			model.addObject("educationList", educationList);
//			List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
//			model.addObject("eduCat", eduCat);
//		}

		return model;

	}

}
