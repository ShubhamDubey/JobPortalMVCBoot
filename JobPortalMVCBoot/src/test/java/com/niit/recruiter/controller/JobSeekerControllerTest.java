package com.niit.recruiter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.niit.recruiter.model.LoginUsers;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.EducationCategoryService;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.JobService;
import com.niit.recruiter.service.LoginUsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobSeekerControllerTest {

	@InjectMocks
	private JobSeekerController jobSeekerController;

	@Mock
	private JobSeekerService jobSeekerService;
	@Mock
	private LoginUsersService loginUsersService;

	@Mock
	private JobService jobService;
	@Mock
	private EducationCategoryService educationCategoryService;
	@Spy
	HttpServletRequest request;

	@Spy
	HttpSession session;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveJobSeekerTestSessionNotNull()
	{
		Users users=mock(Users.class);
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser=mock(JobSeeker.class);
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
	
	Users user=mock(Users.class);
	when(activeUser.getUsers()).thenReturn(user);
	
	Job job=mock(Job.class);
	
	Application application1=mock(Application.class);
	application1.setJobSeeker(activeUser);
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
	List<Application> deletedApplication=mock(ArrayList.class);
	List<Job> jobList = new ArrayList<Job>();
	jobList.add(job);
	jobList.add(mock(Job.class));
	jobList.add(job2);

	when(jobService.getJobList()).thenReturn(jobList);
	
	for(Job job3:jobList)
	{
			deletedApplication=mock(List.class);
		for(Application application:job3.getApplicaionsList())
		{
			if(application.getJobSeeker().getId()!=activeUser.getId())
			{
				deletedApplication.add(application);
			}
		}
		job3.getApplicaionsList().removeAll(deletedApplication);
	}
	
		ModelAndView model=jobSeekerController.saveJobSeeker(request, users);
		assertEquals("welcome", model.getViewName());
		assertTrue(model.getModel().containsKey("joblist"));
		assertTrue(model.getModel().get("joblist") instanceof List<?>);
		
	}
	@Test
	public void saveJobSeekerTestSessionNullAndUserNull()
	{
		Users users=mock(Users.class);
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser=null;
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		LoginUsers user=null;
		when(loginUsersService.findByEmail(request.getParameter("email"))).thenReturn(user);
		
		ModelAndView model=jobSeekerController.saveJobSeeker(request, users);
		assertEquals("success", model.getViewName());
		
	}
	@Test
	public void saveJobSeekerTestSessionNullAndUserRegisteredAlredy()
	{
		Users users=mock(Users.class);
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser=null;
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		LoginUsers user=mock(LoginUsers.class);
		when(loginUsersService.findByEmail(request.getParameter("email"))).thenReturn(user);
		
		ModelAndView model=jobSeekerController.saveJobSeeker(request, users);
		assertEquals("register", model.getViewName());
		assertTrue(model.getModel().containsKey("jobseeker"));
		assertTrue(model.getModel().get("jobseeker") instanceof Users);
		assertTrue(model.getModel().containsKey("alreadyEmailIdExistsError"));
		assertTrue(model.getModel().get("alreadyEmailIdExistsError") instanceof String);
	}
	@Test
	public void welcomePageTestSessionNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = jobSeekerController.welcomePage(request);
		assertEquals("login-jobseeker", model.getViewName());
		assertTrue(model.getModel().containsKey("loginusers"));
		assertTrue(model.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void welcomePageTestSessionNotNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = mock(JobSeeker.class);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		List<Job> jobList = mock(List.class);
		when(jobService.getJobList()).thenReturn(jobList);
		ModelAndView model = jobSeekerController.welcomePage(request);
		assertEquals("welcome", model.getViewName());

		assertTrue(model.getModel().containsKey("loginusers"));
		assertTrue(model.getModel().containsKey("joblist"));
		// assertTrue(model.getModel().get("loginusers") instanceof Users);

		// assertTrue(model.getModel().get("joblist") instanceof List<?>);

	}

	@Test
	public void changePasswordTestSessionNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = jobSeekerController.changePassword(request);
		assertEquals("login-jobseeker", model.getViewName());
		assertTrue(model.getModel().containsKey("loginusers"));
		assertTrue(model.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void changePasswordTestSessionNotNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = jobSeekerController.changePassword(request);
		assertEquals("change-password", model.getViewName());
		assertTrue(model.getModel().containsKey("jobSeeker"));
		assertTrue(model.getModel().get("jobSeeker") instanceof JobSeeker);
	}

	@Test
	public void updatePasswordTestSessionNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = jobSeekerController.updatePassword(request);
		assertEquals("login-jobseeker", model.getViewName());
		assertTrue(model.getModel().containsKey("loginusers"));
		// assertTrue(model.getModel().get("loginusers") instanceof Users);

	}

	@Test
	public void updatePasswordTestSessionNotNullAndPasswordMatch() {
		when(request.getSession()).thenReturn(session);

		JobSeeker activeUser = mock(JobSeeker.class);
		String n_password = "123";
		when(request.getParameter("n_password")).thenReturn(n_password);
		String re_password = "123";
		when(request.getParameter("re_password")).thenReturn(re_password);

		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(activeUser);


			Users user = mock(Users.class);

			when(activeUser.getUsers()).thenReturn(user);

		ModelAndView model = jobSeekerController.updatePassword(request);

		assertEquals("change-password", model.getViewName());
		assertTrue(model.getModel().containsKey("msg"));
		assertTrue(model.getModel().get("msg") instanceof String);

	}

	@Test
	public void updatePasswordTestSessionNotNullAndPasswordMisMatch() {
		when(request.getSession()).thenReturn(session);

		JobSeeker activeUser = mock(JobSeeker.class);
		String n_password = "1234";
		when(request.getParameter("n_password")).thenReturn(n_password);
		String re_password = "123";
		when(request.getParameter("re_password")).thenReturn(re_password);

		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(activeUser);




			Users user = mock(Users.class);

			when(activeUser.getUsers()).thenReturn(user);

		ModelAndView model = jobSeekerController.updatePassword(request);

		assertEquals("change-password", model.getViewName());
		assertTrue(model.getModel().containsKey("msg"));
		assertTrue(model.getModel().get("msg") instanceof String);

	}

	@Test
	public void editProfileTestSessionNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = jobSeekerController.editProfile(request);
		assertEquals("login-jobseeker", model.getViewName());
		assertTrue(model.getModel().containsKey("loginusers"));
		assertTrue(model.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void editProfileTestSessionNotNull() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(request.getSession().getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = jobSeekerController.editProfile(request);
		assertEquals("edit-profile", model.getViewName());
		assertTrue(model.getModel().containsKey("jobseeker"));
		assertTrue(model.getModel().get("jobseeker") instanceof JobSeeker);
	}
}
