package com.niit.recruiter.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
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
	public void certificationsSessionNotNullAndCertificationNotNullTest() {

		model = null;

		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("certification");
		model.addObject("certification", new Certifications());
		JobSeeker activeUser = mock(JobSeeker.class);
		JobSeeker jobSeeker = new JobSeeker();
		when(session.getAttribute("userId")).thenReturn(activeUser);
		List<Certifications> certificationsList = new ArrayList<Certifications>();
		certificationsList.add(new Certifications());
		certificationsList.add(new Certifications());
		certificationsList.add(new Certifications());
		certificationsList.add(new Certifications());
		certificationsList.add(new Certifications());
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		model.addObject("certificationList", jobSeeker.getCertificationsList());
		jobSeeker.setCertificationsList(certificationsList);
		// System.out.println("certificationList: " +
		// jobSeeker.getCertificationsList());
		// System.out.println("activeUser: "+activeUser);

		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		// System.out.println("crtificationListN: "+certificationsList);

		if (activeUser != null && !(jobSeeker.getCertificationsList().isEmpty())) {
			ModelAndView modelAndView = certificationsController.certifications(request);
			// System.out.println("activeUser: " + activeUser);
			Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
			Assert.assertTrue(modelAndView.getModel().containsKey("certification"));
			Assert.assertTrue(modelAndView.getModel().get("certification") instanceof Certifications);
			Assert.assertTrue(modelAndView.getModel().containsKey("certificationList"));
			Assert.assertTrue(modelAndView.getModel().get("certificationList") instanceof List);
		}
	}

	@Test
	public void certificationsSessionNotNullAndCertificationNullTest() {
		model = null;
		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("certification");
		model.addObject("certification", new Certifications());
		JobSeeker activeUser = mock(JobSeeker.class);
		JobSeeker jobSeeker = new JobSeeker();
		when(session.getAttribute("userId")).thenReturn(activeUser);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		jobSeeker.setCertificationsList(new ArrayList<Certifications>());
		model.addObject("certificationList", jobSeeker.getCertificationsList());
		//System.out.println("certificationList2" + jobSeeker.getCertificationsList());
		// System.out.println("certificationList1"+ activeUser.getCertificationsList());
		if (jobSeeker.getCertificationsList().isEmpty()) {
			System.out.println("certificationList2" + jobSeeker.getCertificationsList());
			ModelAndView modelAndView = certificationsController.certifications(request);
			System.out.println("activeUser: " + activeUser);
			Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
			Assert.assertTrue(modelAndView.getModel().containsKey("certification"));
			Assert.assertTrue(modelAndView.getModel().get("certification") instanceof Certifications);
			Assert.assertTrue(modelAndView.getModel().containsKey("certificationList"));

		}

	}

	@Test
	public void deleteCertificationSessionNullTest() {
		model = null;
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		model = new ModelAndView("login-jobseeker");
		model.addObject("loginusers", new Users());
		Integer Id = 3;
		ModelAndView modelAndView = certificationsController.deleteCertification(request, Id);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("loginusers"));
		Assert.assertTrue(modelAndView.getModel().get("loginusers") instanceof Users);

	}

	@Test
	public void deleteCertificationSessionNotNullTest() {
		Integer Id = 2;
		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("certification");
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = new JobSeeker();
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		List<Certifications> certificationsList = new ArrayList<Certifications>();
		for (int i = 0; i < 5; i++) {
			certificationsList.add(new Certifications());
		}
		when(certificationsService.findByJobSeeker(activeUser)).thenReturn(certificationsList);
		
		Certifications certificate = new Certifications();
		certificate = certificationsList.get(Id);
		System.out.println("jobSeekerDel "+jobSeeker);

		jobSeeker.setCertificationsList(certificationsList);

		certificationsService.deleteById(certificate.getId());

		jobSeekerService.saveJobSeeker(jobSeeker);
		List<Certifications> addCertificationList = jobSeeker.getCertificationsList();
		model.addObject("certificationList", addCertificationList);

		ModelAndView modelAndView = certificationsController.deleteCertification(request, Id);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("certificationList"));
		// Assert.assertTrue(modelAndView.getModel().get("loginusers") instanceof List);

	}
	@Test
	public void addCertificationSessionNullTest() {
		model = null;
		Certifications certificate=new Certifications();
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		model = new ModelAndView("login-jobseeker");
		model.addObject("loginusers", new Users());
		ModelAndView modelAndView = certificationsController.addCertifications(request, certificate);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("loginusers"));
	}
	@Test
	public void addCertificationSessionNotNullAndCertificationAddedSuccessTest() {
		model = new ModelAndView("certification");
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = new JobSeeker();
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		Integer id=1;
		String certificationName="Java";
		Date issueDate=new Date(id);
		Date expireDate=new Date(id);
		String url="github.com";
		List<Certifications> certificationsList=new ArrayList<Certifications>();
		for (int i = 0; i < 5; i++) {
			certificationsList.add(new Certifications());
			
		}
		jobSeeker.setCertificationsList(certificationsList);
		Certifications certificate=new Certifications();
		certificate.setId(id);
		certificate.setCertificationName(certificationName);
		certificate.setIssueDate(issueDate);
		certificate.setExpireDate(expireDate);
		certificate.setUrl(url);
		certificate.setJobSeeker(jobSeeker);
		Certifications certification=new Certifications();
		certification=certificationsService.findByUrl(certificate.getUrl());
		when(certificationsService.findByUrl(url)).thenReturn(certification);
		System.out.println("Certification: "+certification);
		System.out.println("list: "+jobSeeker.getCertificationsList());
		jobSeeker.getCertificationsList().add(certificate);
		model.addObject("msg", certificate.getCertificationName() + " sucessfully Added");
		jobSeekerService.saveJobSeeker(jobSeeker);
		verify(jobSeekerService,times(1)).saveJobSeeker(jobSeeker);
		ModelAndView modelAndView = certificationsController.addCertifications(request, certificate);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("certificationList"));
		Assert.assertTrue(modelAndView.getModel().containsKey("msg"));	
	}
	@Test
	public void addCertificationSessionNotNullAndCertificationExistTest() {
		model = new ModelAndView("certification");
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = new JobSeeker();
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		Integer id=1;
		String certificationName="Java";
		Date issueDate=new Date(id);
		Date expireDate=new Date(id);
		String url="github.com";
		List<Certifications> certificationsList=new ArrayList<Certifications>();
		for (int i = 0; i < 5; i++) {
			certificationsList.add(new Certifications());
			
		}
		jobSeeker.setCertificationsList(certificationsList);
		Certifications certificate=new Certifications();
		certificate.setId(id);
		certificate.setCertificationName(certificationName);
		certificate.setIssueDate(issueDate);
		certificate.setExpireDate(expireDate);
		certificate.setUrl(url);
		certificate.setJobSeeker(jobSeeker);
		Certifications certification=new Certifications();
		when(certificationsService.findByUrl(url)).thenReturn(certification);
		System.out.println("certification: "+certification);
		System.out.println("Certification: "+certification);
		System.out.println("list: "+jobSeeker.getCertificationsList());
		jobSeeker.getCertificationsList().add(certificate);
		model.addObject("msg", "Already Added");
		jobSeekerService.saveJobSeeker(jobSeeker);
		verify(jobSeekerService,times(1)).saveJobSeeker(jobSeeker);
		ModelAndView modelAndView = certificationsController.addCertifications(request, certificate);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("certificationList"));
		Assert.assertTrue(modelAndView.getModel().containsKey("msg"));	
	}
	
}
