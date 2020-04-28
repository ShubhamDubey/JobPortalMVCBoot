<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Home | JobBazar</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="/resources/static/images/favicon.png" />
<!--===============================================================================================-->
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- Customised CSS -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/animate.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/hamburgers.min.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/select2.min.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/util.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/jobliststyle.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/main.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/matrialize.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/navstyle.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/static/css/custom.css"/>">
</head>

<body>
	<%
		if (session.getAttribute("userId") == null) {
	%>

	<header class="sticky-top">
		<!--- Navbar --->
		<nav class="navbar navbar-expand-lg">
			<div class="container">
				<a class="navbar-brand text-white" href="/"><i
					class="fa fa-graduation-cap fa-lg mr-2"></i>JobBazar</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#nvbCollapse" aria-controls="nvbCollapse">
					<span class="fa fa-bars" style="color: #ffffff;"></span>
				</button>
				<div class="collapse navbar-collapse" id="nvbCollapse">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item pl-1"><a class="nav-link"
							href="showRegisterForm"><i class="fa fa-user-plus fa-fw mr-1"></i>Register</a></li>
						<!--  	<li class="nav-item pl-1"><a class="nav-link"
							href="about.html"><i class="fa fa-th-list fa-fw mr-1"></i>About</a>
						</li> -->
						<li class="nav-item pl-1"><a class="nav-link"
							href="showLoginForm"><i class="fa fa-sign-in fa-fw mr-1"></i>Login</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<!--# Navbar #-->
	</header>
	<%
		} else {
	%>
	<header class="sticky-top">
		<!--- Navbar --->
		<nav class="navbar navbar-expand-lg">
			<div class="container">
				<a class="navbar-brand text-white" href="/"><i
					class="fa fa-graduation-cap fa-lg mr-2"></i>JobBazar</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#nvbCollapse" aria-controls="nvbCollapse">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="nvbCollapse">
					<ul class="navbar-nav ml-auto">
						<!-- <li class="nav-item pl-1"><a class="nav-link"
							href="about.html"><i class="fa fa-th-list fa-fw mr-1"></i>About</a>
						</li>  -->
						<li class="nav-item pl-1"><a class="nav-link"
							href="appliedJobListing"><i class="fa fa-th-list fa-fw mr-1"></i>View
								applied Job</a></li>
						<li class="nav-item pl-1 dropdown">
							<button class="nav-link dropdown-toggle"
								style="font-family: Poppins-Regular;" type="button"
								id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-user fa-fw mr-1"></i>${userId.firstName}</button>
							<div class="dropdown-menu">
								<a class="nav-link" href="editProfile"> <i
									class="fa fa-user fa-fw mr-1"> </i>Edit Profile
								</a> <a class="nav-link" href="logout"> <i
									class="fa fa-sign-out fa-fw mr-1"> </i>Logout
								</a>
							</div>
						</li>
				</div>
			</div>
		</nav>
		<!--# Navbar #-->
	</header>
	<%
		}
	%>