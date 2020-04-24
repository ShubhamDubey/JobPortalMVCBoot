package com.niit.recruiter.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
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

import com.niit.recruiter.model.Education;
import com.niit.recruiter.model.EducationCategory;
import com.niit.recruiter.model.JobSeeker;
import com.niit.recruiter.model.Users;
import com.niit.recruiter.service.EducationCategoryService;
import com.niit.recruiter.service.EducationService;
import com.niit.recruiter.service.JobSeekerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EducationControllerTest {
	@InjectMocks
	EducationController educationController;

	@Mock
	JobSeekerService jobSeekerService;
	@Mock
	EducationService educationService;
	@Mock
	EducationCategoryService educationCategoryService;
	@Spy
	HttpServletRequest request;

	@Spy
	HttpSession session;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void showEducationFormSessionNullTest() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = educationController.showEducationForm(request);
		Assert.assertEquals("login-jobseeker", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("loginusers"));
		Assert.assertTrue(model.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void showEducationFormSessionNotNullTest() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);

		ModelAndView model = educationController.showEducationForm(request);
		Assert.assertEquals("education-form", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("eduCat"));
		Assert.assertTrue(model.getModel().get("eduCat") instanceof List<?>);
		Assert.assertTrue(model.getModel().containsKey("educationList"));
		Assert.assertTrue(model.getModel().get("educationList") instanceof List<?>);
		Assert.assertTrue(model.getModel().containsKey("education"));
		Assert.assertTrue(model.getModel().get("education") instanceof Education);

	}

	@Test
	public void addEducationSessionNullTest() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		Education education=mock(Education.class);
		when(session.getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = educationController.addEducation(request, education);;
		Assert.assertEquals("login-jobseeker", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("loginusers"));
		Assert.assertTrue(model.getModel().get("loginusers") instanceof Users);
	}

	@Test
	public void addEducationSessionNotNullEducationNotExistTest() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);

		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = mock(JobSeeker.class);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		EducationCategory educationCategory1=spy(EducationCategory.class);
		educationCategory1.setEducationCategoryName("matriculation");
		Education education = spy(Education.class);
		education.setEducationCategory(educationCategory1);
		String submittedEducationCategory ="matriculation";
	
		EducationCategory educationCategory = mock(EducationCategory.class);
		
		when(educationCategoryService.findByEducationCategoryName(submittedEducationCategory))
				.thenReturn(educationCategory);
		
		Education educationStatus = null;

		when(educationService.findByEducationCategoryAndJobSeeker(educationCategory, jobSeeker))
				.thenReturn(educationStatus);
		ModelAndView model = educationController.addEducation(request, education);
		System.out.println(model.getViewName());
		Assert.assertEquals("education-form", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("msg"));
		Assert.assertTrue(model.getModel().get("msg") instanceof String);
		Assert.assertTrue(model.getModel().containsKey("educationList"));
		Assert.assertTrue(model.getModel().get("educationList") instanceof List<?>);
		Assert.assertTrue(model.getModel().get("eduCat") instanceof List<?>);
		Assert.assertTrue(model.getModel().containsKey("educationList"));
		Assert.assertTrue(model.getModel().containsKey("education"));
		Assert.assertTrue(model.getModel().get("education") instanceof Education);

	}
	@Test
	public void addEducationSessionNotNullEducationExistTest() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = mock(JobSeeker.class);

		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = mock(JobSeeker.class);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		EducationCategory educationCategory1=spy(EducationCategory.class);
		educationCategory1.setEducationCategoryName("matriculation");
		Education education = spy(Education.class);
		education.setEducationCategory(educationCategory1);
		String submittedEducationCategory ="matriculation";
	
		EducationCategory educationCategory = mock(EducationCategory.class);
		
		when(educationCategoryService.findByEducationCategoryName(submittedEducationCategory))
				.thenReturn(educationCategory);
		
		Education educationStatus = mock(Education.class);

		when(educationService.findByEducationCategoryAndJobSeeker(educationCategory, jobSeeker))
				.thenReturn(educationStatus);
		ModelAndView model = educationController.addEducation(request, education);
		System.out.println(model.getViewName());
		Assert.assertEquals("education-form", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("msg"));
		Assert.assertTrue(model.getModel().get("msg") instanceof String);
		Assert.assertTrue(model.getModel().containsKey("educationList"));
		Assert.assertTrue(model.getModel().get("educationList") instanceof List<?>);
		Assert.assertTrue(model.getModel().get("eduCat") instanceof List<?>);
		Assert.assertTrue(model.getModel().containsKey("educationList"));
		Assert.assertTrue(model.getModel().containsKey("education"));
		Assert.assertTrue(model.getModel().get("education") instanceof Education);

	}
	
	@Test
	public void deleteEducationSessionNullTest() {
		when(request.getSession()).thenReturn(session);
		JobSeeker activeUser = null;
		Integer id=1;
		when(session.getAttribute("userId")).thenReturn(activeUser);
		ModelAndView model = educationController.deleteEducation(request, id);;
		Assert.assertEquals("login-jobseeker", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("loginusers"));
		Assert.assertTrue(model.getModel().get("loginusers") instanceof Users);
	}
	@Test
	public void deleteEducationSessionNotNullTest() {
		when(request.getSession()).thenReturn(session);
		Integer id=1;
		JobSeeker activeUser = mock(JobSeeker.class);
		
		when(session.getAttribute("userId")).thenReturn(activeUser);
		JobSeeker jobSeeker = mock(JobSeeker.class);
		when(jobSeekerService.findById(activeUser.getId())).thenReturn(jobSeeker);
		List<Education> educationList =mock(ArrayList.class);
		when(educationService.findByJobSeekerOrderByEducationCategoryAsc(jobSeeker)).thenReturn(educationList);
		Education education=mock(Education.class);
		when(educationList.get(id)).thenReturn(education);
		when(educationService.findByJobSeekerOrderByEducationCategoryAsc(jobSeeker)).thenReturn(educationList);
		ModelAndView model = educationController.deleteEducation(request, id);
		Assert.assertEquals("education-form", model.getViewName());
		Assert.assertTrue(model.getModel().containsKey("educationList"));
		Assert.assertTrue(model.getModel().get("educationList") instanceof List<?>);
		Assert.assertTrue(model.getModel().get("eduCat") instanceof List<?>);
		Assert.assertTrue(model.getModel().containsKey("educationList"));
		Assert.assertTrue(model.getModel().containsKey("education"));
		Assert.assertTrue(model.getModel().get("education") instanceof Education);
	}
}
