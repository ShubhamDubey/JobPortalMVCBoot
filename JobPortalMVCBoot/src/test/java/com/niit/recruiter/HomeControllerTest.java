package com.niit.recruiter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.controller.HomeController;
import com.niit.recruiter.model.Job;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.JobService;
import com.niit.recruiter.service.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

	@InjectMocks
	HomeController homeController;

	@Mock
	UsersService usersService;

	@Spy
	ModelMap modelMap;

	@Mock
	JobService jobService;

	@Spy
	ModelAndView model;

	@Spy
	HttpServletRequest request;

	@Spy
	HttpSession session;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void processLoginEmailInvalidAndPasswordTest() {
		Users theJobSeekerUser = mock(Users.class);
		model = null;
		String email = "nitesh123@gmail.com";
		String password = "redhat";
		theJobSeekerUser.setEmail("nitesh1234@gmail.com");
		theJobSeekerUser.setPassword("123");
		when(request.getSession()).thenReturn(session);
		session.setAttribute("userId", theJobSeekerUser);
		when(usersService.findByEmail(email)).thenReturn(theJobSeekerUser);
		Users loginUsers = mock(Users.class);
		if (!(email.equals(theJobSeekerUser.getEmail()) && password.equals(theJobSeekerUser.getPassword())))
			model = new ModelAndView("login-jobseeker");
		model.addObject("error", "Invalid User Name Or Password");
		model.addObject("loginusers", loginUsers);
		ModelAndView modelAndView1 = homeController.processLogin(request, theJobSeekerUser);
		Assert.assertEquals(modelAndView1.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView1.getModel().get("loginusers") instanceof Users);
		Assert.assertTrue(modelAndView1.getModel().containsKey("error"));
		System.out.println("3");

	}



	@Test
	public void processLoginTestRightUser() {
		Users theJobSeekerUser = new Users();
		model = null;

		String email = "nitesh123@gmail.com";
		String password = "redhat";
		theJobSeekerUser.setEmail(email);
		theJobSeekerUser.setPassword("redhat");
		when(usersService.findByEmail(email)).thenReturn(theJobSeekerUser);
		Users chkUsers = usersService.findByEmail(email);
		Assert.assertEquals(chkUsers.getEmail(), email);
		Assert.assertEquals(chkUsers.getPassword(), password);
		Users loginUsers = mock(Users.class);
		when(request.getSession()).thenReturn(session);
		JobSeeker jobSeeker = mock(JobSeeker.class);
		when(loginUsers.getJobseeker()).thenReturn(jobSeeker);
		if (email.equalsIgnoreCase(theJobSeekerUser.getEmail()) && password.equals(theJobSeekerUser.getPassword())) {
			session.setAttribute("userId", jobSeeker); // Session Created
			List<Job> theJobList = new ArrayList<Job>();
			Job mockJob1 = mock(Job.class);
			Job mockJob2 = mock(Job.class);
			Job mockJob3 = mock(Job.class);
			Job mockJob4 = mock(Job.class);
			Job mockJob5 = mock(Job.class);
			theJobList.add(mockJob1);
			theJobList.add(mockJob2);
			theJobList.add(mockJob3);
			theJobList.add(mockJob4);
			theJobList.add(mockJob5);
			model = new ModelAndView("welcome");
			model.addObject("loginusers", loginUsers);
			model.addObject("joblist", theJobList);
			ModelAndView modelAndView = homeController.processLogin(request, theJobSeekerUser);
			Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
			Assert.assertTrue(modelAndView.getModel().containsKey("joblist"));
			Assert.assertTrue(modelAndView.getModel().get("joblist") instanceof List);
			Assert.assertTrue(modelAndView.getModel().containsKey("loginusers"));
			System.out.println("2");
		}
	}

		@Test
	public void processLoginEmailInvalidTest() {
		Users theJobSeekerUser = mock(Users.class);
		model = null;
		String email = "nitesh123@gmail.com";
		String password = "redhat";
		theJobSeekerUser.setEmail(email);
		theJobSeekerUser.setPassword(password);

		when(request.getSession()).thenReturn(session);
		session.setAttribute("userId", theJobSeekerUser);
		when(usersService.findByEmail(email)).thenReturn(theJobSeekerUser);
		Users loginUsers = null;
		when(usersService.findByEmail(email)).thenReturn(loginUsers);
		if (loginUsers == null) {
			model = new ModelAndView("login-jobseeker");
			model.addObject("error", "User name not exist");
			model.addObject("loginusers", loginUsers);
			ModelAndView modelAndView = homeController.processLogin(request, theJobSeekerUser);
			Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
			Assert.assertTrue(modelAndView.getModel().containsKey("error"));
			Assert.assertTrue(modelAndView.getModel().get("loginusers") instanceof Users);
			System.out.println("1");
		}

	}

	@Test
	public void indexViewTest() {
		Assert.assertEquals(homeController.indexView(modelMap), "index");
	}

	@Test
	public void registrationFormTest() {
		Assert.assertEquals(homeController.registrationForm(modelMap), "register");
	}

	@Test
	public void showLoginFormTest() {
		Assert.assertEquals(homeController.showLoginForm(modelMap), "login-jobseeker");
	}

	@Test
	public void logoutTest() {
		when(request.getSession()).thenReturn(session);
		Assert.assertEquals(homeController.logout(request), "redirect:/");
	}

}
