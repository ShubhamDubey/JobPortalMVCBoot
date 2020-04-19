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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Application;
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


	@PostMapping(value = "/saveJobSeeker")
	public ModelAndView saveJobSeeker(HttpServletRequest req, @ModelAttribute("jobseeker") Users theUsers) {
		ModelAndView modelView = null;
		JobSeeker activeUser = (JobSeeker) req.getSession().getAttribute("userId");
		if (activeUser != null) {
			activeUser.setFirstName(req.getParameter("firstName"));
			activeUser.setLastName(req.getParameter("lastName"));
			activeUser.getUsers().setEmail(req.getParameter("email"));			
			jobSeekerService.saveJobSeeker(activeUser);
			modelView = new ModelAndView("welcome");
			List<Application> deletedApplications=null;
			List<Job> jobList=jobService.getJobList();
		
			
			for(Job job1:jobList)
			{deletedApplications=new ArrayList<Application>();
			
				System.out.println("Job\t\t\t"+job1);
				for(Application application:job1.getApplicaionsList())
				{
					System.out.println("Application of "+job1.getId()+"\t"+application);
					if(application.getJobSeeker().getId()!=activeUser.getId())
					{
						System.out.println("Others Found"+"\t\t"+application);
						deletedApplications.add(application);
					}
				}
				job1.getApplicaionsList().removeAll(deletedApplications);
			}
	
//			jobList.forEach(job1->job1.getApplicaionsList().forEach(application->System.out.println(application)));

			modelView.addObject("joblist", jobList);	
		}
		
		else if (loginUsersService.findByEmail(req.getParameter("email")) == null) {
			theUsers.setRole("JobSeeeker");
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


//	@GetMapping("/appliedJob")
//	public ModelAndView appliedJob(HttpServletRequest request, @RequestParam("jobId") int theJobId) {
//		ModelAndView model = null;
//		try {
//
//			JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
//			if (activeUser == null) {
//				model = new ModelAndView("login-jobseeker");
//				model.addObject("loginusers", new LoginUsers());
//			} else {
//
//				Application checkApp = applicationService.findByJobSeekerAndJob(activeUser.getId(), theJobId);
//				if (checkApp == null) {
//					model = new ModelAndView("welcome");
//					Application app = new Application();
//					app.setJobId(theJobId);
//					app.setJobseekerId(activeUser.getId());
//					applicationService.saveApplication(app);
//					model.addObject("appliedJobmsg", "You Have Applied Job Successfully");
//				} else {
//
//					model = new ModelAndView("welcome");
//					model.addObject("appliedJobmsg", "You Have Already Applied This Job");
//				}
//				model.addObject("joblist", jobService.getJobList());
//			}
//		} catch (Exception e) {
//
//		}
//		return model;
//	}
//


	private String encryptPass(String pass) {
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = pass;
		String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
		return encodedString;
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



	
	@GetMapping("/editProfile")
	public ModelAndView editProfile(HttpServletRequest request) {
		ModelAndView model=null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		} else {
			System.out.println("name: "+activeUser.getFirstName());
			model =new ModelAndView("edit-profile");
			model.addObject("jobseeker", activeUser);
		}
		
		return model;
	}
	
	@GetMapping("/changePassword")
	public ModelAndView changePassword(HttpServletRequest request)
	{
		ModelAndView model=null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		else {
			model=new ModelAndView("change-password");
			model.addObject("jobSeeker", activeUser);
		}
		return model;
	}
	@PostMapping("updatePassword")
	public ModelAndView updatePassword(HttpServletRequest request)
	{
		ModelAndView model=null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		if (activeUser == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("loginusers", new LoginUsers());
		}
		else {
			model=new ModelAndView("change-password");
			activeUser=jobSeekerService.findById(activeUser.getId());
			model.addObject("jobSeeker", activeUser);
			
			if(activeUser.getUsers().getPassword().equals(request.getParameter("current_password")) && request.getParameter("n_password").equals(request.getParameter("re_password")))
			{
				activeUser.getUsers().setPassword(request.getParameter("n_password"));
			jobSeekerService.saveJobSeeker(activeUser);
			model.addObject("msg","SuccessFully Updated");
			}
			else
			{
				model.addObject("msg","password mismatch");
			}
			
		}
		
		return model;
	}
	@RequestMapping("/welcome")
	public ModelAndView welcomePage(HttpServletRequest request)
	{
		ModelAndView model=null;
		
		JobSeeker activeUser=(JobSeeker)request.getSession().getAttribute("userId");
		if(activeUser!=null) {
			JobSeeker jobSeeker=jobSeekerService.findById(activeUser.getId());
		List<Job> jobList = jobService.getJobList();
			model = new ModelAndView("welcome");
			model.addObject("loginusers", jobSeeker.getUsers());
			model.addObject("joblist", jobList);
		}
		else
		{}
		return model;
	}
}