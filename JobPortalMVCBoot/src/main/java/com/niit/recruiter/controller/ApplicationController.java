package com.niit.recruiter.controller;

import java.util.ArrayList;
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
import com.niit.recruiter.model.Users;
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

		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());
		} else {
			model = new ModelAndView("welcome");
			Job job = jobService.findById(theJobId).get();

			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			List<Application> deletedApplications = null;
			Application app = new Application();
			app.setJob(job);
			app.setJobSeeker(jobSeeker);
			app.setAppliedDate(new Date());
			app.setStatus(true);
			applicationService.saveApplication(app);
			model.addObject("appliedJobmsg", "You Have Applied Job Successfully");

			List<Job> jobList = jobService.getJobList();

			for (Job job1 : jobList) {
				deletedApplications = new ArrayList<Application>();

				for (Application application : job1.getApplicaionsList()) {
					if (application.getJobSeeker().getId() != jobSeeker.getId()) {
						deletedApplications.add(application);
					}
				}
				job1.getApplicaionsList().removeAll(deletedApplications);
			}
			model.addObject("joblist", jobList);
		}

		return model;
	}

	@RequestMapping("/appliedJobListing")
	public ModelAndView listAppliedJob(HttpServletRequest request) {
		ModelAndView model = null;

		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser != null) {
			model = new ModelAndView("applied-jobs");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			model.addObject("appliedJobList", applicationService.findByJobSeekerAndStatus(jobSeeker, true));
		} else {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());
		}
		return model;
	}

	@RequestMapping("deleteApplication")
	public ModelAndView deleteApplication(HttpServletRequest request, @RequestParam("applicationId") Integer id) {

		ModelAndView model = null;

		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());
		} else {

			model = new ModelAndView("applied-jobs");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			List<Application> applicationList = applicationService.findByJobSeekerAndStatus(jobSeeker, true);
			Application application = applicationList.get(id);
			applicationService.deleteApplication(application);

			model.addObject("appliedJobList", applicationService.findByJobSeekerAndStatus(jobSeeker, true));

		}
		return model;
	}
}
