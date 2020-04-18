package com.niit.recruiter;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.niit.recruiter.controller.HomeController;
import com.niit.recruiter.service.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { HomeController.class })
public class HomeControllerTest {

	@InjectMocks
	HomeController homeController;

	@Mock
	UsersService usersService;

	@Spy
	Model model;
	@Spy
	ModelMap modelMap;

	@Spy
	HttpServletRequest req;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void viewRegisterPageTest() {
		Assert.assertEquals(homeController.indexView(modelMap), "index");
	}

}
