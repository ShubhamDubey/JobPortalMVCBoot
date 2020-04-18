package com.niit.recruiter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Certifications;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.CertificationsService;
import com.niit.recruiter.service.JobSeekerService;

@Controller
public class CertificationsController {
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private CertificationsService certificationService;

	@RequestMapping("/certification")
	public ModelAndView certifications(HttpServletRequest request) {
		ModelAndView model = null;
		HttpSession session=request.getSession();
		JobSeeker activeUser = (JobSeeker) session.getAttribute("userId");
		
		if (activeUser != null) {
			model = new ModelAndView("certification");
			model.addObject("certification", new Certifications());
			if (activeUser.getCertificationsList() == null) 
			{
				model.addObject("certificationList", null);
			} else 
			{
				JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
				model.addObject("certificationList", jobSeeker.getCertificationsList());
			}
		} else {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());
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
	@RequestMapping("/deleteCertification")
	public ModelAndView deleteEducation(HttpServletRequest request,@RequestParam("educationId") Integer Id)
	{
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		ModelAndView model = null;
		try {
			
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());

			} else {
				model = new ModelAndView("certification");
				JobSeeker jobSeeker=jobSeekerService.findById(activeUser.getId());
				List<Certifications> certificationsList=certificationService.findByJobSeeker(activeUser);
				
				Certifications certificate=certificationsList.get(Id);
				if(certificationsList.remove(Id))
				{
					System.out.println("Updated List Set");
					jobSeeker.setCertificationsList(certificationsList);
				}
				certificationService.deleteById(certificate.getId());
				
				jobSeekerService.saveJobSeeker(jobSeeker);
				model.addObject("certificationList", jobSeeker.getCertificationsList());
			}
		} catch (Exception e) {
			model = new ModelAndView("certification");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			jobSeeker = jobSeekerService.findById(activeUser.getId());
			model.addObject("certificationList", jobSeeker.getCertificationsList());		}

		return model;
		
	}

}