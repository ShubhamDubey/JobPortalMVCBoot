package com.niit.recruiter.controller;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.EducationCategory;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.EducationCategoryService;
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
	private EducationCategoryService educationCategoryService;

	@GetMapping("/db")
	public String dbInitial(ModelMap model) throws ParseException {
		// Adding Education Category

		List<EducationCategory> educationCategoryList = new ArrayList<EducationCategory>();
		educationCategoryList.add(new EducationCategory("matriculation"));
		educationCategoryList.add(new EducationCategory("Higher Secondary"));
		educationCategoryList.add(new EducationCategory("Graduation"));
		educationCategoryList.add(new EducationCategory("Post Graduation"));

		educationCategoryService.saveAll(educationCategoryList);

		// Adding Jobs
		List<Job> jobListDb = new ArrayList<Job>();
		jobListDb.add(new Job("NIIT", "niit-icone.png", "Java Developer", "35000", "15", "IT", "DevOps",
				new SimpleDateFormat("dd/MM/yyyy").parse("31/03/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("22/04/2020")));
		jobListDb.add(new Job("GOOGLE", "google-icon.png", "Dot NET Developer", "25000", "25", "IT",
				"Full Stack Developer", new SimpleDateFormat("dd/MM/yyyy").parse("31/03/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("18/04/2020")));
		jobListDb.add(new Job("AMAZON", "amazon-icone.png", "Django Developer", "45000", "35", "IT", "Web Developer",
				new SimpleDateFormat("dd/MM/yyyy").parse("31/03/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("30/04/2020")));
		jobListDb.add(new Job("LinkedIn", "linkedin-icone.png", "Data Base Manager", "45000", "15", "IT", "Sql,MySql",
				new SimpleDateFormat("dd/MM/yyyy").parse("31/03/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("15/04/2020")));

		jobService.saveAll(jobListDb);

		JobSeeker theJobSeeker = new JobSeeker();
		theJobSeeker.setFirstName("Nitesh");
		theJobSeeker.setLastName("Sahu");
		Users user = new Users();
		user.setPassword("redhat");
		user.setEmail("nitesh123@gmail.com");
		theJobSeeker.setUsers(user);
		jobSeekerService.saveJobSeeker(theJobSeeker);

		theJobSeeker = new JobSeeker();
		theJobSeeker.setFirstName("shubham");
		theJobSeeker.setLastName("dubey");
		user = new Users();
		user.setPassword("redhat");
		user.setEmail("shubham123@gmail.com");
		theJobSeeker.setUsers(user);
		jobSeekerService.saveJobSeeker(theJobSeeker);

		theJobSeeker = new JobSeeker();
		theJobSeeker.setFirstName("Deepanshu");
		theJobSeeker.setLastName("Gupta");
		user = new Users();
		user.setPassword("redhat");
		user.setEmail("deep123@gmail.com");
		theJobSeeker.setUsers(user);
		jobSeekerService.saveJobSeeker(theJobSeeker);

		theJobSeeker = new JobSeeker();
		theJobSeeker.setFirstName("Jatin");
		theJobSeeker.setLastName("Puri");
		user = new Users();
		user.setPassword("redhat");
		user.setEmail("jatin123@gmail.com");
		theJobSeeker.setUsers(user);
		jobSeekerService.saveJobSeeker(theJobSeeker);

		List<Job> jobList = jobService.getJobList();
		model.addAttribute("joblist", jobList);
		return "index";
	}

	@PostMapping(value = "/saveJobSeeker")
	public ModelAndView saveJobSeeker(HttpServletRequest req, @ModelAttribute("jobseeker") Users theUsers) {
		ModelAndView modelView = null;
		JobSeeker activeUser = (JobSeeker) req.getSession().getAttribute("userId");
		if (activeUser != null) {
			//update profile
			activeUser.setFirstName(req.getParameter("firstName"));
			activeUser.setLastName(req.getParameter("lastName"));
			Users user=activeUser.getUsers();
			user.setEmail(req.getParameter("email"));
			activeUser.setUsers(user);
			jobSeekerService.saveJobSeeker(activeUser);
			modelView = new ModelAndView("welcome");
			List<Application> deletedApplications = null;
			List<Job> jobList = jobService.getJobList();

			for (Job job1 : jobList) {
				deletedApplications = new ArrayList<Application>();

				for (Application application : job1.getApplicaionsList()) {
					if (application.getJobSeeker().getId() != activeUser.getId()) {
						deletedApplications.add(application);
					}
				}
				job1.getApplicaionsList().removeAll(deletedApplications);
			}

			modelView.addObject("joblist", jobList);
		}

		else if (loginUsersService.findByEmail(req.getParameter("email")) == null) {
			//Registration.
			theUsers.setRole("JobSeeeker");
			JobSeeker theJobSeeker = new JobSeeker();
			theJobSeeker.setFirstName(req.getParameter("firstName"));
			theJobSeeker.setLastName(req.getParameter("lastName"));
			theJobSeeker.setUsers(theUsers);
			jobSeekerService.saveJobSeeker(theJobSeeker);
			modelView = new ModelAndView("login-jobseeker");
			modelView.addObject("loginusers", new Users());
		} else {
			//if Already Exists
			modelView = new ModelAndView("register");
			modelView.addObject("jobseeker", new Users());
			modelView.addObject("alreadyEmailIdExistsError", "Email Id already Exists");
		}
		return modelView;
	}

	private String encryptPass(String pass) {
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = pass;
		String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
		return encodedString;
	}

	@GetMapping("/editProfile")
	public ModelAndView editProfile(HttpServletRequest request) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());
		} else {

			model = new ModelAndView("edit-profile");
			model.addObject("jobseeker", activeUser);
		}

		return model;
	}

	@GetMapping("/changePassword")
	public ModelAndView changePassword(HttpServletRequest request) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());
		} else {
			model = new ModelAndView("change-password");
			model.addObject("jobSeeker", activeUser);
		}
		return model;
	}

	@PostMapping("updatePassword")
	public ModelAndView updatePassword(HttpServletRequest request) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			model = new ModelAndView("change-password");
			activeUser = jobSeekerService.findById(activeUser.getId());
			model.addObject("jobSeeker", activeUser);
			String n_password = request.getParameter("n_password");
			String re_password = request.getParameter("re_password");
			if (n_password.equals(re_password)) {
				Users user = activeUser.getUsers();
				user.setPassword(request.getParameter("n_password"));
				activeUser.setUsers(user);
				jobSeekerService.saveJobSeeker(activeUser);
				model.addObject("msg", "SuccessFully Updated");
			} else {
				model.addObject("msg", "password mismatch");
			}

		}

		return model;
	}

	@RequestMapping("/welcome")
	public ModelAndView welcomePage(HttpServletRequest request) {
		ModelAndView model = null;

		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser != null) {
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			List<Job> jobList = jobService.getJobList();
			model = new ModelAndView("welcome");
			Users user = jobSeeker.getUsers();
			model.addObject("loginusers", user);
			model.addObject("joblist", jobList);
		} else {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new Users());

		}
		return model;
	}
}