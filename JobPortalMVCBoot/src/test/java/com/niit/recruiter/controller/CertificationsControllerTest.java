package com.niit.recruiter.controller;

import static org.mockito.Mockito.mock;
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

import com.niit.recruiter.model.Certifications;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.CertificationsService;
import com.niit.recruiter.service.JobSeekerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CertificationsControllerTest {

	@InjectMocks
	CertificationsController certificationsController;

	@Mock
	JobSeekerService jobSeekerService;

	@Mock
	CertificationsService certificationsService;

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
	public void certificationsSessionNullTest() {

		model = null;
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		model = new ModelAndView("login-jobseeker");
		model.addObject("loginusers", new Users());
		ModelAndView modelAndView = certificationsController.certifications(request);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("loginusers"));
		Assert.assertTrue(modelAndView.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void certificationsSessionNotNullAndCertificationNullTest() {

		model = null;
		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("certification");
		model.addObject("certification", new Certifications());
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		List<Certifications> certificationsList=new ArrayList<Certifications>();
		certificationsList.add(mock(Certifications.class));
		certificationsList.add(mock(Certifications.class));
		certificationsList.add(mock(Certifications.class));
		certificationsList.add(mock(Certifications.class));
		certificationsList.add(mock(Certifications.class));
		model.addObject("certificationList", certificationsList);
		System.out.println("activeUser: " + certificationsList);
		JobSeeker jobSeeker=mock(JobSeeker.class);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		if (activeUser != null) {
			ModelAndView modelAndView = certificationsController.certifications(request);
			System.out.println("activeUser: " + activeUser);
			Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
			Assert.assertTrue(modelAndView.getModel().containsKey("certification"));
			Assert.assertTrue(modelAndView.getModel().get("certification") instanceof Certifications);
			Assert.assertTrue(modelAndView.getModel().containsKey("certificationList"));
			Assert.assertTrue(modelAndView.getModel().get("certificationList") instanceof List);
		}
	}

}
