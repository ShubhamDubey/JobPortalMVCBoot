package com.niit.recruiter.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

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

	
	@GetMapping("/")
	public String indexView(ModelMap model) {
		List<Job> jobList = jobService.getJobList();
		model.addAttribute("joblist", jobList);
		return "index";
	}
	
	@RequestMapping(value = "/showRegisterForm") // @RequestMapping using in the method level ,it has default GET method
	public String showFormForAdd(ModelMap theModel) {

		theModel.addAttribute("jobseeker", new Users());
		theModel.addAttribute("alreadyEmailIdExistsError");

		return "register"; // return model + view name
	}

	@PostMapping(value = "/saveJobSeeker")
	public ModelAndView saveCustomer(HttpServletRequest req, @ModelAttribute("jobseeker") Users theUsers) {
		ModelAndView modelView = null;

		if (loginUsersService.findByEmail(req.getParameter("email")) == null) {
			JobSeeker theJobSeeker = new JobSeeker();
			theJobSeeker.setFirstName(req.getParameter("firstName"));
			theJobSeeker.setLastName(req.getParameter("lastName"));
			theJobSeeker.setUsers(theUsers);
			jobSeekerService.saveJobSeeker(theJobSeeker);
			modelView = new ModelAndView("sucess");
		} else {
			modelView = new ModelAndView("register");
			modelView.addObject("jobseeker", new Users());
			modelView.addObject("alreadyEmailIdExistsError", "Email Id already Exists");
		}
		return modelView;
	}

	@RequestMapping(value = "showLoginForm") // @RequestMapping using in the method level ,it has default GET method
	public String showLoginForm(ModelMap theModel) {

		theModel.addAttribute("loginusers", new LoginUsers());
		return "login-jobseeker";
	}

	@RequestMapping(value = "loginJobSeeker", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView processLogin(HttpServletRequest req, @ModelAttribute LoginUsers theLoginUsers) {

		ModelAndView model = null;
		StringTokenizer st = new StringTokenizer(theLoginUsers.getEmail(), "@");
		String s2 = st.nextToken();
		LoginUsers loginUsers = loginUsersService.findByEmail(theLoginUsers.getEmail());
		
		System.out.println("Id:"+theLoginUsers.getEmail());
		if (loginUsers == null) {
			// email invalid
			System.out.println("Cont " + loginUsers);
			model = new ModelAndView("login-jobseeker");
			model.addObject("error", "User name not exist");
			model.addObject("loginusers", new LoginUsers());
		} else if (loginUsers.getEmail().equalsIgnoreCase(theLoginUsers.getEmail())
				&& loginUsers.getPassword().equals(theLoginUsers.getPassword())) {
			// both are correct
			req.getSession().setAttribute("userId", loginUsers.getId()); // Session Created
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

	@GetMapping("/appliedJob")
	public ModelAndView appliedJob(@RequestParam("jobseekerId") int theJobSeekerId,
			@RequestParam("jobId") int theJobId) {
		ModelAndView model = new ModelAndView("welcome");
		
		Application checkApp=applicationService.findByJobseekerIdAndJobId(theJobSeekerId, theJobId);
		if(checkApp==null)
		{
			Application app = new Application();
			app.setJobId(theJobId);
			app.setJobseekerId(theJobSeekerId);
			applicationService.saveApplication(app);
			model.addObject("appliedJobmsg", "You Have Applied Job Successfully");		
		}
		else {
			model.addObject("appliedJobmsg", "You Have Already Applied This Job");
			}
		model.addObject("joblist", jobService.getJobList());
		return model;
	}



	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/";
	}

	private String encryptPass(String pass) {
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = pass;
		String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
		return encodedString;
	}
}