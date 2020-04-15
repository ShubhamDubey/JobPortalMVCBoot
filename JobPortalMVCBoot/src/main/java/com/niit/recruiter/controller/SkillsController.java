package com.niit.recruiter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.model.Skills;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.SkillsService;

@Controller
public class SkillsController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private SkillsService skillService;
	
	@RequestMapping("/showSkillsForm")
	public ModelAndView showSkillsFrom(HttpServletRequest request) {
		ModelAndView model=null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		
		if (activeUser != null) {
			JobSeeker jobSeeker=jobSeekerService.findById(activeUser.getId());
			model=new ModelAndView("jobseeker-skills");
			model.addObject("theSkills", new Skills());
			if(activeUser.getSkillList()!=null) {
				model.addObject("skillsList", jobSeeker.getSkillList());
			}
			else {
				model.addObject("skillsList",null);
			}
		}
		else {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}

		return model;
	}
	@PostMapping("/addSkills")
	public ModelAndView addSkills(HttpServletRequest request,@ModelAttribute("theSkills") Skills theSkills) {
		ModelAndView model=null;	
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if(activeUser!=null) {
			String skillsName=request.getParameter("name");
			System.out.println("Skills Name: "+skillsName);
			String skiillsArrays[] =skillsName.split(",");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			List <Skills> skillsList=new ArrayList<Skills>();
			if(jobSeeker.getSkillList()!=null)
			{
				
				skillService.deleteAll(skillService.findByJobSeeker(jobSeeker));
				
			}
			for(String skill:skiillsArrays){
				skillsList.add(new Skills(skill));
			}
			jobSeeker.setSkillList(skillsList);
			jobSeekerService.saveJobSeeker(jobSeeker);
			skillService.saveAll(skillsList);
			jobSeekerService.saveJobSeeker(jobSeeker);
			model=new ModelAndView("jobseeker-skills");
			model.addObject("skillsList", jobSeeker.getSkillList());
		}
		else {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
				return model;
	}

}
