package com.niit.recruiter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.model.Resume;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.ResumeService;

@Controller
public class ResumeController {

	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private ResumeService resumeService;

	@GetMapping("/showResumeForm1")
	public ModelAndView showResumeForm(HttpServletRequest request) {
		ModelAndView model = null;
		try {
			System.out.println("Above Session");
			JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
			System.out.println("Bellow Session " + activeUser);
			if (activeUser == null) {
				System.out.println("Inside If");
				model = new ModelAndView("login-jobseeker");
				model.addObject("loginusers", new LoginUsers());
				return model;
			}
			model = new ModelAndView("resume-upload");
			JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
			System.out.println("above " + jobSeeker.getResume());// Find out Resume JobSeeker
			System.out.println("bellow");
			if (jobSeeker.getResume() == null) {
				model = new ModelAndView("resume-upload");
			} else {
				model.addObject("resumeId", jobSeeker.getResume().getId());// NULL POINTER EXCEPTION GENRATE WHEN RESUME
																			// NOT PRESENT
				model.addObject("resumeName", jobSeeker.getResume().getFileName());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		}
		return model;
	}

	@PostMapping("/uploadResume1")
	public ModelAndView uploadResume(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		ModelAndView model = null;
		JobSeeker activeUser = (JobSeeker) request.getSession().getAttribute("userId");
		JobSeeker jobSeeker = jobSeekerService.findById(activeUser.getId());
		try {

			// Update Resume
			model = new ModelAndView("resume-upload");
			if (jobSeeker.getResume() == null) {
				Resume resume=resumeService.storeFile(file, new Resume());
				resume.setJobSeeker(jobSeeker);
				jobSeekerService.saveJobSeeker(jobSeeker);
				model.addObject("msg", "Resume Uploaded Successfully");
				model.addObject("resumeId", jobSeeker.getResume().getId());
				model.addObject("resumeName", jobSeeker.getResume().getFileName());
			} else {
				Resume resume=resumeService.storeFile(file, resumeService.getFile(jobSeeker.getResume().getId()));
				resume.setJobSeeker(jobSeeker);
				
				jobSeekerService.saveJobSeeker(jobSeeker);
				model.addObject("resumeId", jobSeeker.getResume().getId());
				model.addObject("resumeName", jobSeeker.getResume().getFileName());
				model.addObject("msg", "Resume Updated Successfully");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		}
		return model;
	}

	@GetMapping("/downloadResume1/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int fileName, HttpServletRequest request) {
		// Load file as Resource
		Resume resumeFile = resumeService.getFile(fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(resumeFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resumeFile.getFileName() + "\"")
				.body(new ByteArrayResource(resumeFile.getData()));
	}
}
