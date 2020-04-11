package com.niit.recruiter.controller;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

//	@GetMapping("/")
//	public String indexView(ModelMap model) {
//
//		List<Job> jobList = jobService.getJobList();
//		model.addAttribute("joblist", jobList);
//		return "index";
//	}

//	@RequestMapping(value = "/showRegisterForm") // @RequestMapping using in the method level ,it has default GET method
//	public String showFormForAdd(ModelMap theModel) {
//
//		theModel.addAttribute("jobseeker", new Users());
//		theModel.addAttribute("alreadyEmailIdExistsError");
//
//		return "register"; // return model + view name
//	}

	@PostMapping(value = "/saveJobSeeker")
	public ModelAndView saveJobSeeker(HttpServletRequest req, @ModelAttribute("jobseeker") Users theUsers) {
		ModelAndView modelView = null;
		JobSeeker activeUser = (JobSeeker) req.getSession().getAttribute("userId");
		if (activeUser != null) {
			activeUser.setFirstName(req.getParameter("firstName"));
			activeUser.setLastName(req.getParameter("lastName"));
			activeUser.getUsers().setEmail(req.getParameter("email"));
			activeUser.getUsers().setPassword(req.getParameter("password"));
			jobSeekerService.saveJobSeeker(activeUser);
			modelView = new ModelAndView("welcome");
			modelView.addObject("joblist", jobService.getJobList());	
		}
		
		else if (loginUsersService.findByEmail(req.getParameter("email")) == null) {
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

//	@RequestMapping(value = "showLoginForm") // @RequestMapping using in the method level ,it has default GET method
//	public String showLoginForm(ModelMap theModel) {
//
//		theModel.addAttribute("loginjobseeker", new Users());
//		return "login-jobseeker";
//	}

//	@RequestMapping(value = "loginJobSeeker", method = { RequestMethod.POST, RequestMethod.GET })
//	public ModelAndView processLogin(HttpServletRequest req, @ModelAttribute Users theJobSeekerUser) {
//
//		ModelAndView model = null;
//		StringTokenizer st = new StringTokenizer(theJobSeekerUser.getEmail(), "@");
//		String s2 = st.nextToken();
//		Users loginUsers = usersService.findByEmail(theJobSeekerUser.getEmail());
//		if (loginUsers == null) {
//			// email invalid
//			System.out.println("Cont " + loginUsers);
//			model = new ModelAndView("login-jobseeker");
//			model.addObject("error", "User name not exist");
//			model.addObject("loginusers", new LoginUsers());
//		} else if (loginUsers.getEmail().equalsIgnoreCase(theJobSeekerUser.getEmail())
//				&& loginUsers.getPassword().equals(theJobSeekerUser.getPassword())) {
//			// both are correct
//			System.out.println("userId " + loginUsers.getJobseeker().getId());
//			req.getSession().setAttribute("userId", loginUsers.getJobseeker().getId()); // Session Created
//			req.getSession().setAttribute("username", loginUsers.getJobseeker().getFirstName());
//			List<Job> jobList = jobService.getJobList();
//			model = new ModelAndView("welcome");
//			model.addObject("loginusers", loginUsers);
//			model.addObject("joblist", jobList);
//		} else {
//			// both credentials are incorrect
//			model = new ModelAndView("login-jobseeker");
//			model.addObject("error", "Invalid User Name Or Password");
//			model.addObject("loginusers", new LoginUsers());
//
//		}
//		return model;
//	}

	@GetMapping("/appliedJob")
	public ModelAndView appliedJob(HttpServletRequest request, @RequestParam("jobId") int theJobId) {
		ModelAndView model = null;
		try {

			JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());
			} else {

				Application checkApp = applicationService.findByJobseekerIdAndJobId(activeUser.getId(), theJobId);
				if (checkApp == null) {
					model = new ModelAndView("welcome");
					Application app = new Application();
					app.setJobId(theJobId);
					app.setJobseekerId(activeUser.getId());
					applicationService.saveApplication(app);
					model.addObject("appliedJobmsg", "You Have Applied Job Successfully");
				} else {

					model = new ModelAndView("welcome");
					model.addObject("appliedJobmsg", "You Have Already Applied This Job");
				}
				model.addObject("joblist", jobService.getJobList());
			}
		} catch (Exception e) {

		}
		return model;
	}

//	@GetMapping("/logout")
//	public String logout(HttpServletRequest req) {
//		req.getSession().invalidate();
//		return "redirect:/";
//	}

	private String encryptPass(String pass) {
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = pass;
		String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
		return encodedString;
	}

	@GetMapping("/showResumeForm")
	public ModelAndView showResumeForm(HttpServletRequest request) {
		ModelAndView model = null;
		try {
			System.out.println("Above Session");
			JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
			System.out.println("Bellow Session "+activeUser);
			if (activeUser == null) {
				System.out.println("Inside If");
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());
				return model;
			}
			model = new ModelAndView("resume-upload");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			System.out.println("above " + jobSeeker.getResume().getId());
			System.out.println("bellow");
			model.addObject("resumeId", jobSeeker.getResume().getId());
			model.addObject("resumeName", jobSeeker.getResume().getFileName());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			model = new ModelAndView("resume-upload");
		}
		return model;
	}

	@PostMapping("/uploadResume")
	public ModelAndView uploadResume(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
		try {
			
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());
				return model;
			}
			
			model = new ModelAndView("resume-upload");
			jobSeeker.setResume(resumeService.storeFile(file, resumeService.getFile(jobSeeker.getResume().getId())));
			jobSeekerService.saveJobSeeker(jobSeeker);
			model.addObject("msg", "Resume Updated Successfully");
			model.addObject("resumeId", jobSeeker.getResume().getId());
			model.addObject("resumeName", jobSeeker.getResume().getFileName());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			jobSeeker.setResume(resumeService.storeFile(file, new Resume()));
			jobSeekerService.saveJobSeeker(jobSeeker);
			model.addObject("msg", "Resume Uploaded Successfully");
			model.addObject("resumeId", jobSeeker.getResume().getId());
			model.addObject("resumeName", jobSeeker.getResume().getFileName());
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
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			model = new ModelAndView("education-form");
			List<EducationCategory> eduCat = educationCategoryService.findAll();
			model.addObject("eduCat", eduCat);
			model.addObject("education", new Education());
		}

		return model;
	}

	@PostMapping("/saveEducation")
	public ModelAndView addEducation(HttpServletRequest request, @ModelAttribute("education") Education education) {
		ModelAndView model = null;
		try {
			JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());

			} else {
				model = new ModelAndView("profile");
				System.out.println();
				model.addObject("msg",
						"Sucessfully  " + education.getEducationCategory().getEducationCategoryName() + " Record");

				JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
				String submittedEducationCategory = education.getEducationCategory().getEducationCategoryName();
				EducationCategory educationCategory = educationCategoryService
						.findByEducationCategoryName(submittedEducationCategory);
				education.setEducationCategory(educationCategory);

				jobSeeker.getEducationSet().add(educationService.save(education));
				jobSeekerService.saveJobSeeker(jobSeeker);
			}
		} catch (Exception e) {
			model = new ModelAndView("profile");
			model.addObject("msg", "Already added" + e.getMessage() + education.getCourse());
		}

		return model;
	}

	@GetMapping("/viewEducation")
	public ModelAndView viewEducationRecord(HttpServletRequest request) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			model = new ModelAndView("education-list");

			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			model.addObject("educationList", jobSeeker.getEducationSet());
		}
		return model;

	}

	// Shubham

	@GetMapping("/profile")
	public ModelAndView showProfile(HttpServletRequest request) {
		ModelAndView model = null;

		try {
			JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
			if (activeUser == null) {
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());
			} else {
				model = new ModelAndView("profile");
				List<EducationCategory> eduCat = educationCategoryService.findAllByOrderByEducationCategoryIdAsc();
				model.addObject("eduCat", eduCat);
			
				JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());

				System.out.println(jobSeeker.getFirstName());
				model.addObject("jobSeeker", jobSeeker);

				model.addObject("education", new Education());

				System.out.println("above " + jobSeeker.getResume().getId());
				System.out.println("bellow");
				model.addObject("resumeId", jobSeeker.getResume().getId());
				model.addObject("resumeName", jobSeeker.getResume().getFileName());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			model = new ModelAndView("profile");
		}
		return model;

	}
	@GetMapping("/editProfile")
	public ModelAndView editProfile(HttpServletRequest request) {
		ModelAndView model=null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			System.out.println("name: "+activeUser.getFirstName());
			model =new ModelAndView("jobseeker-profile");
			model.addObject("jobseeker", activeUser);
		}
		
		return model;
	}
	
}