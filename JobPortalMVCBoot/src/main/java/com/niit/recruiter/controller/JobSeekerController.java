package com.niit.recruiter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private LoginUsersService loginUsersService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(value = "/showRegisterForm") // @RequestMapping using in the method level ,it has default GET method
	public String showFormForAdd(ModelMap theModel) {

		theModel.addAttribute("jobseeker", new Users());
		return "register"; // return model + view name
	}

	@PostMapping(value = "/saveJobSeeker")
	public String saveCustomer(HttpServletRequest req, @ModelAttribute("jobseeker") Users theUsers) {
		JobSeeker theJobSeeker = new JobSeeker();
		theJobSeeker.setFirstName(req.getParameter("firstName"));
		theJobSeeker.setLastName(req.getParameter("lastName"));
		theJobSeeker.setUsers(theUsers);
		jobSeekerService.saveJobSeeker(theJobSeeker);
		return "success";
	}

	@RequestMapping(value = "showLoginForm") // @RequestMapping using in the method level ,it has default GET method
	public String showLoginForm(ModelMap theModel) {

		theModel.addAttribute("loginusers", new LoginUsers());
		return "login-jobseeker";
	}

	@RequestMapping(value = "loginJobSeeker", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView processLogin(@ModelAttribute LoginUsers theLoginUsers) {

		ModelAndView model = null;
		LoginUsers loginUsers = loginUsersService.checkUsers(theLoginUsers);
		if (loginUsers == null) {
			System.out.println("Cont "+loginUsers);
			model = new ModelAndView("login-jobseeker");
			model.addObject("error", "Invalid User Name Or Password");
			model.addObject("loginusers", new LoginUsers());
		} else {
			List<Job> jobList=jobService.getJobList();
			model = new ModelAndView("welcome");
			model.addObject("loginusers",loginUsers);
			model.addObject("joblist", jobList);
		}
		return model;
	}
	
	@GetMapping("/appliedJob")
	public ModelAndView appliedJob(@RequestParam("jobseekerId") int theJobSeekerId,@RequestParam("jobId") int theJobId) {
		
		Application app=new Application();
		app.setJobId(theJobId);
		app.setJobseekerId(theJobSeekerId);
		applicationService.saveApplication(app);
		ModelAndView model=new ModelAndView("welcome");
		model.addObject("appliedJobmsg", "You Have Applied Job Successfully");
		return model;
	}
	
	@GetMapping("/")
	public String indexView(ModelMap model)
	{	List<Job> jobList=jobService.getJobList();
		model.addAttribute("joblist",jobList);
		return "index";
	}
	

}