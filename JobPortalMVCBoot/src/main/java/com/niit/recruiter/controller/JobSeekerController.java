package com.niit.recruiter.controller;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.Education;
import com.niit.recruiter.model.EducationCategory;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.model.Resume;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.ApplicationService;
import com.niit.recruiter.service.EducationCategoryService;
import com.niit.recruiter.service.EducationService;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.JobService;
import com.niit.recruiter.service.LoginUsersService;
import com.niit.recruiter.service.ResumeService;
import com.niit.recruiter.service.UsersService;

@Controller
public class JobSeekerController {

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

	@Autowired
	private EducationService educationService;
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
			modelView = new ModelAndView("success");
		} else {
			modelView = new ModelAndView("register");
			modelView.addObject("jobseeker", new Users());
			modelView.addObject("alreadyEmailIdExistsError", "Email Id already Exists");
		}
		return modelView;
	}

	@RequestMapping(value = "showLoginForm") // @RequestMapping using in the method level ,it has default GET method
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
			req.getSession().setAttribute("userId", loginUsers.getJobseeker().getId()); // Session Created
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

		Application checkApp = applicationService.findByJobseekerIdAndJobId(theJobSeekerId, theJobId);
		if (checkApp == null) {
			Application app = new Application();
			app.setJobId(theJobId);
			app.setJobseekerId(theJobSeekerId);
			applicationService.saveApplication(app);
			model.addObject("appliedJobmsg", "You Have Applied Job Successfully");
		} else {
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

	@GetMapping("/showResumeForm")
	public ModelAndView showResumeForm(HttpServletRequest request) {
		ModelAndView model = null;
		Integer activeUser = (Integer) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}

		else {
			JobSeeker jobSeeker=jobSeekerService.findById(activeUser);
			model = new ModelAndView("resume-upload");
			model.addObject("resumeId", jobSeeker.getResume().getId());
			model.addObject("resumeName", jobSeeker.getResume().getFileName());
			model.addObject("resume", new Resume());
		}
		return model;
	}

	@PostMapping("/uploadResume")
	public ModelAndView uploadResume(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		ModelAndView model = null;
		System.out.println("File :"+file);
		Integer activeUser = (Integer) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}

		else {
			JobSeeker jobSeeker=jobSeekerService.findById(activeUser);
			jobSeeker.setResume(resumeService.storeFile(file));
			jobSeekerService.saveJobSeeker(jobSeeker);
			model = new ModelAndView("resume-upload");
			model.addObject("msg", "Resume Uploaded Successfully");
		}
		return model;
	}

	@GetMapping("/downloadResume/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int fileName, HttpServletRequest request) {
		// Load file as Resource
		Resume resumeFile = resumeService.getFile(fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(resumeFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resumeFile.getFileName() + "\"")
				.body(new ByteArrayResource(resumeFile.getData()));
	}

	@GetMapping("/educationForm")
	public ModelAndView showEducationForm(HttpServletRequest request) {
		ModelAndView model = null;
		Integer activeUser = (Integer) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			model = new ModelAndView("education-form");
			List<EducationCategory> eduCat=educationCategoryService.findAll();
			model.addObject("eduCat", eduCat);
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser);
			model.addObject("education", new Education());
		}

		return model;
	}

	@PostMapping("/saveEducation")
	public ModelAndView addEducation(HttpServletRequest request, @ModelAttribute("education") Education education) {
		ModelAndView model = null;
		try {
			Integer activeUser = (Integer) request.getSession().getAttribute("userId");
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());

			} else {
				model = new ModelAndView("education-form");
				model.addObject("msg",
						"Sucessfully  " + education.getEducationCategory().getEducationCategoryName() + " Record");

				JobSeeker jobSeeker = jobSeekerService.findById(activeUser);
				String submittedEducationCategory = education.getEducationCategory().getEducationCategoryName();
				EducationCategory educationCategory = educationCategoryService
						.findByEducationCategoryName(submittedEducationCategory);
				education.setEducationCategory(educationCategory);

				jobSeeker.getEducationSet().add(educationService.save(education));
				jobSeekerService.saveJobSeeker(jobSeeker);
			}
		} catch (Exception e) {
			model = new ModelAndView("education-form");
			model.addObject("msg", "Already added");
		}

		return model;
	}

	@GetMapping("/viewEducation")
	public ModelAndView viewEducationRecord(HttpServletRequest request) {
		ModelAndView model = null;
		Integer activeUser = (Integer) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			model = new ModelAndView("education-list");

			JobSeeker jobSeeker = jobSeekerService.findById(activeUser);
			model.addObject("educationList", jobSeeker.getEducationSet());
		}
		return model;

	}
}