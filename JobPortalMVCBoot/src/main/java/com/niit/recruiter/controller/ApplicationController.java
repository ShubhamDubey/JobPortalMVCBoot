package com.niit.recruiter.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.service.ApplicationService;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.JobService;
import com.niit.recruiter.service.LoginUsersService;
@Controller
public class ApplicationController {
	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private LoginUsersService loginUsersService;

	@Autowired
	private JobService jobService;

	@Autowired
	private ApplicationService applicationService;

	@GetMapping("/appliedJob")
	public ModelAndView appliedJob(HttpServletRequest request, @RequestParam("jobId") int theJobId) {
		ModelAndView model = null;
		try {

			JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());
			} else {
				Job job=jobService.findById(theJobId).get();
				System.out.println(job.getName());
				JobSeeker jobSeeker=jobSeekerService.findById(activeUser.getId());
				System.out.println(jobSeeker.getFirstName());
				List<Application> checkApp = applicationService.findByJobSeekerAndJobAndStatus(jobSeeker,job,false);
				
				/*
				 * if (checkApp.isEmpty()) { // new Job model = new ModelAndView("welcome");
				 * Application app = new Application();
				 * System.out.println("Runningdalsjflkadsjflasd"); app.setJob(job);
				 * app.setStatus(false); app.setAppliedDate(new Date());
				 * 
				 * app.setJobSeeker(jobSeeker); applicationService.saveApplication(app);
				 * model.addObject("appliedJobmsg", "You Have Applied Job Successfully"); } else
				 * {
				 * 
				 * model = new ModelAndView("welcome"); model.addObject("appliedJobmsg",
				 * "You Have Already Applied This Job"); }
				 */				model.addObject("joblist", jobService.getJobList());
			}
		} catch (Exception e) {
				
		}
		return model;
	}


	@RequestMapping("/appliedJobListing")
	public ModelAndView listAppliedJob(HttpServletRequest request)
	{
		ModelAndView model=null;
		
		JobSeeker activeUser=(JobSeeker)request.getSession().getAttribute("userId");
		if(activeUser!=null)
		{
			model=new ModelAndView("applied-jobs");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			model.addObject("appliedJobList",applicationService.findByJobSeekerAndStatus(jobSeeker,false));
		}
		else
		{
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		return model;
	}
	
	@RequestMapping("deleteApplication")
	public ModelAndView deleteApplication(HttpServletRequest request,@RequestParam("applicationId") Integer id)
	{

		ModelAndView model=null;
		
		JobSeeker activeUser=(JobSeeker)request.getSession().getAttribute("userId");
		if(activeUser==null)
		{
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		else
		{
			
			model=new ModelAndView("applied-jobs");
		JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
		
		Application application=applicationService.findByJobSeekerAndStatus(jobSeeker,false).get(id);
		application.setStatus(true);
		System.out.println(application.getStatus()+"Status");
		applicationService.saveApplication(application);
		
		model.addObject("appliedJobList",applicationService.findByJobSeekerAndStatus(jobSeeker,false));
		
		}
		return model;
	}
}
