package com.niit.recruiter.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.JobService;
import com.niit.recruiter.service.UsersService;

@Controller

public class HomeController {
	
	@Autowired
	JobService jobService;

	@Autowired
	UsersService usersService;
	@GetMapping("/")
	public String indexView(ModelMap model) {

		List<Job> jobList = jobService.getJobList();
		model.addAttribute("joblist", jobList);
		return "index";
	}
	

	@RequestMapping(value = "/showRegisterForm") // @RequestMapping using in the method level ,it has default GET method
	public String registrationForm(ModelMap theModel) {

		theModel.addAttribute("jobseeker", new Users());
		theModel.addAttribute("alreadyEmailIdExistsError");

		return "register"; // return model + view name
	}
	
	@RequestMapping(value = "/showLoginForm") // @RequestMapping using in the method level ,it has default GET method
	public String showLoginForm(ModelMap theModel) {

		theModel.addAttribute("loginjobseeker", new Users());
		return "login-jobseeker";
	}
	@RequestMapping(value = "loginJobSeeker", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView processLogin(HttpServletRequest req, @ModelAttribute Users theJobSeekerUser) {

		ModelAndView model = null;
		StringTokenizer st = new StringTokenizer(theJobSeekerUser.getEmail(), "@");
		String s2 = st.nextToken();
	
		Users loginUsers = usersService.findByEmail(theJobSeekerUser.getEmail());
		if (loginUsers == null) {
			// email invalid
			System.out.println("Cont " + loginUsers);
			model = new ModelAndView("login-jobseeker");
			model.addObject("error", "User name not exist");
			model.addObject("loginusers", new LoginUsers());
		} else if (loginUsers.getEmail().equalsIgnoreCase(theJobSeekerUser.getEmail())
				&& loginUsers.getPassword().equals(theJobSeekerUser.getPassword())) {
			// both are correct
			System.out.println("userId " + loginUsers.getJobseeker().getId());
			req.getSession().setAttribute("userId", loginUsers.getJobseeker()); // Session Created
			req.getSession().setAttribute("username", loginUsers.getJobseeker().getFirstName());
			List<Job> jobList = jobService.getJobList();
			model = new ModelAndView("welcome");
			model.addObject("loginusers", loginUsers);
			model.addObject("joblist", jobList);
		} else {
			// both credentials are incorrect
			model = new ModelAndView("login-jobseeker");
			model.addObject("error", "Invalid User Name Or Password");
			model.addObject("loginusers", new LoginUsers());

		}
		return model;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/";
	}
}