package com.niit.recruiter.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Skills;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.JobSeekerService;
import com.niit.recruiter.service.SkillsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillsControllerTest {
	@InjectMocks
	private SkillsController skillsController;

	@Mock
	private JobSeekerService jobSeekerService;

	@Mock
	private SkillsService skillService;

	@Spy
	ModelAndView model;

	@Spy
	HttpServletRequest request;

	@Spy
	HttpSession session;

	@Test
	public void addCertificationSessionNullTest() {
		model = null;
		Skills theSkills = new Skills();
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		model = new ModelAndView("login-jobseeker");
		model.addObject("loginusers", new Users());
		ModelAndView modelAndView = skillsController.addSkills(request, theSkills);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("loginusers"));
		Assert.assertTrue(modelAndView.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void showSkillsFormSessionNullTest() {
		model = null;
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		model = new ModelAndView("login-jobseeker");
		model.addObject("loginusers", new Users());
		ModelAndView modelAndView = skillsController.showSkillsFrom(request);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("loginusers"));
		Assert.assertTrue(modelAndView.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void showSkillsFormSessionNotNullTestAndSkillsListEmpty() {
		model = null;
		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("jobseeker-skills");
		model.addObject("theSkills", new Skills());
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = new JobSeeker();
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		jobSeeker.setSkillList(new ArrayList<Skills>());
		model.addObject("skillsList", null);
		ModelAndView modelAndView = skillsController.showSkillsFrom(request);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("theSkills"));
		Assert.assertTrue(modelAndView.getModel().get("theSkills") instanceof Skills);
		Assert.assertTrue(modelAndView.getModel().containsKey("theSkills"));
	}
	@Test
	public void showSkillsFormSessionNotNullTestAndSkillsListNotEmpty() {
		model = null;
		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("jobseeker-skills");
		model.addObject("theSkills", new Skills());
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = new JobSeeker();
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		List<Skills>skillList =new ArrayList<Skills>();
		for(int i=0;i<5;i++) {
			skillList.add(new Skills());
		}
		jobSeeker.setSkillList(skillList);
		model.addObject("skillsList", jobSeeker.getSkillList());
		ModelAndView modelAndView = skillsController.showSkillsFrom(request);
		Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		Assert.assertTrue(modelAndView.getModel().containsKey("theSkills"));
		Assert.assertTrue(modelAndView.getModel().get("theSkills") instanceof Skills);
		Assert.assertTrue(modelAndView.getModel().containsKey("theSkills"));
	}
	@Test
	public void addSkillsSessionNotNullAndSkillListNullTest() {
		model = null;
		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("jobseeker-skills");
		
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = new JobSeeker();
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		Skills theSkills=new Skills();
		theSkills.setId(1);
		theSkills.setName("java");
		theSkills.setJobSeeker(jobSeeker);
		String skillsName=new String("java,C#,.net");
		when(request.getParameter("name")).thenReturn(skillsName);
		String[] expected=new String[] {"java","C#",".net"};
		String[] actual=skillsName.split(",");
		assertArrayEquals(expected, actual);
		//when(skillsName.split(",")).thenReturn(skiillsArrays);
		model.addObject("skillsList", jobSeeker.getSkillList());
		
		  ModelAndView modelAndView = skillsController.addSkills(request, theSkills);
		  Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		  Assert.assertTrue(modelAndView.getModel().containsKey("skillsList"));	
	}
	@Test
	public void addSkillsSessionNotNullAndSkillListNotNullTest() {
		model = null;
		when(request.getSession()).thenReturn(session);
		model = new ModelAndView("jobseeker-skills");
		
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = new JobSeeker();
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		Skills theSkills=new Skills();
		theSkills.setId(1);
		theSkills.setName("java");
		theSkills.setJobSeeker(jobSeeker);
		jobSeeker.setSkillList(new ArrayList<Skills>());
		System.out.println(jobSeeker.getSkillList());
		String skillsName=new String("java,C#,.net");
		when(request.getParameter("name")).thenReturn(skillsName);
		String[] expected=new String[] {"java","C#",".net"};
		String[] actual=skillsName.split(",");
		assertArrayEquals(expected, actual);
		//when(skillsName.split(",")).thenReturn(skiillsArrays);
		model.addObject("skillsList", jobSeeker.getSkillList());
		
		  ModelAndView modelAndView = skillsController.addSkills(request, theSkills);
		  Assert.assertEquals(modelAndView.getViewName(), model.getViewName());
		  Assert.assertTrue(modelAndView.getModel().containsKey("skillsList"));	
	}

}
