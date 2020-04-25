package com.niit.recruiter.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.Application;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.ApplicationService;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.JobService;
import com.niit.recruiter.service.LoginUsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationControllerTest {
	@InjectMocks
	ApplicationController applicationController;

	@Mock
	private JobSeekerService jobSeekerService;

	@Mock
	private LoginUsersService loginUsersService;

	@Mock
	private JobService jobService;
	@Mock
	private ApplicationService applicationService;
	@Spy
	HttpServletRequest request;

	@Spy
	HttpSession session;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void appliedJobTestSessionNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		Integer theJobId = 1;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = applicationController.appliedJob(request, theJobId);
		Assert.assertEquals("login-jobseeker", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("loginusers"));
		Assert.assertTrue(model.getModel().get("loginusers") instanceof Users);
	}

	@Test	
	public void appliedJobTestSessionNotNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = mock(JobSeeker.class);
		jobSeeker.setId(anyInt());
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		Optional<Job> jobOptional=Optional.of(mock(Job.class));
		when(jobService.findById(anyInt())).thenReturn(jobOptional);
		Job job1=jobOptional.get();
		Job job=mock(Job.class);
		
		Application application1=mock(Application.class);
		application1.setJobSeeker(jobSeeker);
		application1.setJob(job);
		Application application2=mock(Application.class);
		application1.setJobSeeker(mock(JobSeeker.class));
		application1.setJob(job);
		List<Application> applicationList=mock(ArrayList.class);
		applicationList.add(application1);
		applicationList.add(application2);
		job.setApplicaionsList(applicationList);
		Job job2=mock(Job.class);
		job2.setApplicaionsList(mock(List.class));
		List<Job> jobList = new ArrayList<Job>();
		jobList.add(job);
		jobList.add(mock(Job.class));
		jobList.add(job2);
		
		List<Application> deletedApplication=mock(ArrayList.class);
		when(jobService.getJobList()).thenReturn(jobList);
		for(Job job3:jobList)
		{
				deletedApplication=mock(List.class);
			for(Application application:job3.getApplicaionsList())
			{
				if(application.getJobSeeker().getId()!=jobSeeker.getId())
				{
					deletedApplication.add(application);
				}
			}
			job3.getApplicaionsList().removeAll(deletedApplication);
		}
		ModelAndView model = applicationController.appliedJob(request, 0);
		Assert.assertEquals("welcome", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("appliedJobmsg"));
		Assert.assertTrue(model.getModel().get("appliedJobmsg") instanceof String);
		Assert.assertTrue(model.getModel().containsKey("joblist"));
		Assert.assertTrue(model.getModel().get("joblist") instanceof List<?>);

	}

	@Test
	public void listAppliedJobTestAndSessionNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = applicationController.listAppliedJob(request);
		Assert.assertEquals("login-jobseeker", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("loginusers"));
		Assert.assertTrue(model.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void listAppliedJobTestAndSessionNotNull() {

		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = applicationController.listAppliedJob(request);
		Assert.assertEquals("applied-jobs", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("appliedJobList"));
		Assert.assertTrue(model.getModel().get("appliedJobList") instanceof List<?>);
	}

	@Test
	public void deleteApplicationJobTestAndSessionNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		Integer id = 1;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = applicationController.deleteApplication(request, id);
		Assert.assertEquals("login-jobseeker", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("loginusers"));
		Assert.assertTrue(model.getModel().get("loginusers") instanceof Users);
	}
	@Test
	public void deleteApplicationJobTestAndSessionNotNull()  throws Exception{
		when(request.getSession()).thenReturn(session);		
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = mock(JobSeeker.class);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);		
		List<Application> applicationList = mock(List.class);
		applicationList.add(mock(Application.class));
		applicationList.add(mock(Application.class));
		

		
		when(applicationService.findByJobSeekerAndStatus(jobSeeker, true)).thenReturn(applicationList);
		Application application=applicationList.get(anyInt());
		when(applicationList.get(anyInt())).thenReturn(application);
		
		ModelAndView model = applicationController.deleteApplication(request,1);

		Assert.assertEquals("applied-jobs", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("appliedJobList"));
		Assert.assertTrue(model.getModel().get("appliedJobList") instanceof List<?>);
	}
}
