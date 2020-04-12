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
import com.niit.recruiter.service.ApplicationService;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.JobService;
import com.niit.recruiter.service.LoginUsersService;
import com.niit.recruiter.service.ResumeService;
import com.niit.recruiter.service.UsersService;

@Controller
public class CertificationsController {
	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private LoginUsersService loginUsersService;

	@Autowired
	private JobService jobService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/certification")
	public ModelAndView certifications(HttpServletRequest request)
	{
		ModelAndView model=null;
		JobSeeker activeUser=(JobSeeker) request.getSession().getAttribute("userId");
		if(activeUser!=null)
		{
			model=new ModelAndView("certification");
			model.addObject("certification",new Certifications());
			if(activeUser.getCertificationsList()==null)
			{
			model.addObject("certificationList",null);
			}
			else
			{
			//	model.addObject("certificationList",activeUser.getCertificationsList());
			}
		}
		else
		{
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		return model;
	}
	
	@RequestMapping("/addCertification")
	public ModelAndView certifications(HttpServletRequest request,@ModelAttribute("certification") Certifications certificate)
	{
		ModelAndView model=null;
		JobSeeker activeUser=(JobSeeker) request.getSession().getAttribute("userId");
		if(activeUser!=null)
		{
			List<Certifications> certificationList=new ArrayList<Certifications>();
			certificationList.add(certificate);
			activeUser.setCertificationsList(certificationList);
			jobSeekerService.saveJobSeeker(activeUser);
			model=new ModelAndView("certification");
			model.addObject("msg",certificate.getCertificationName()+" sucessfully Added");
			model.addObject("certification",new Certifications());
			if(activeUser.getCertificationsList()==null)
			{
			model.addObject("certificationList",null);
			}
			else
			{
			//	model.addObject("certificationList",activeUser.getCertificationsList());
			}
		}
		else
		{
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		return model;
	}
}