<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<style type="text/css">

</style>
<title>Jobs | JobBazar | JobBazar</title>
</head>

<%@include file="header.jsp"%>
<section id="v2-resent-job-post">
	<div class="vertical-space-50"></div>
	<div class="container">
		<h3 class="text-center">User Profile</h3>
		
		<h1>${ msg}</h1>

		<div class="vertical-space-30"></div>

		<!-- <p class="max-width">Lorem ipsum tempus amet conubia adipiscing fermentum viverra gravida, mollis
                suspendisse pretium dictumst inceptos mattis euismod
            </p> -->
		<div class="vertical-space-60"></div>
	<div class="row">
			<div class="col-lg-2">
				<div class="">
						<a href="welcome" class="btn btn-block btn-outline-primary"
							 id="profile_id">Back</a>
					</div>
				<div class="container card-body">
					<div class="row ">
						<a href="editProfile" class="btn btn-block btn-outline-primary"
							 id="profile_id">Profile</a>
					</div><br>
					<div class="row">
						<a href="changePassword"
							class="btn btn-block btn-outline-primary"
							 id="password_id">Change
							Password</a>
					</div><br>
					<div class="row ">
						<a href="showResumeForm1" class="btn btn-block btn-outline-primary"
							>Resume</a>
					</div><br>
					<div class="row ">
						<a href="showEducationForm" class="btn btn-block btn-outline-primary"
							>Education</a>
					</div><br>
					<div class="row ">
						<a href="certification" class="btn btn-block btn-outline-primary"
							 id="certification_id">Certifications</a>
					</div><br>
					<div class="row ">
						<a href="showSkillsForm" class="btn btn-block btn-outline-primary"
							 id="skill_id">Skills</a>
					</div><br>
				</div>
			</div>

	
	