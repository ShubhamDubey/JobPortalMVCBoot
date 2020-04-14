package com.niit.recruiter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Certifications;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.service.JobSeekerService;

@Controller
public class CertificationsController {
	@Autowired
	private JobSeekerService jobSeekerService;

	@RequestMapping("/certification")
	public ModelAndView certifications(HttpServletRequest request) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		
		if (activeUser != null) {
			model = new ModelAndView("certification");
			model.addObject("certification", new Certifications());
			if (activeUser.getCertificationsList() == null) {
				model.addObject("certificationList", null);
			} else {JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			

				model.addObject("certificationList", jobSeeker.getCertificationsList());
			}
		} else {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		return model;
	}

	@RequestMapping("/addCertification")
	public ModelAndView certifications(HttpServletRequest request,
			@ModelAttribute("certification") Certifications certificate) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		
		if (activeUser != null) {
			
		
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			
			jobSeeker.getCertificationsList().add(certificate);
			jobSeekerService.saveJobSeeker(jobSeeker);
			model = new ModelAndView("certification");
			model.addObject("msg", certificate.getCertificationName() + " sucessfully Added");
			model.addObject("certification", new Certifications());
			if (activeUser.getCertificationsList() == null) {
				model.addObject("certificationList", null);
			} else {
				 jobSeeker = jobSeekerService.findById(activeUser.getId());
				model.addObject("certificationList", jobSeeker.getCertificationsList());
			}
		} else {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		return model;
	}
}