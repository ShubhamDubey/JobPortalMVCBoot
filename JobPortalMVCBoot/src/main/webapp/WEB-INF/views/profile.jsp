<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<head>
<title>Jobs | JobBazar | JobBazar</title>
</head>

<%@ include file="header.jsp"%>

<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
response.setHeader("Pragma", "no-cache");//HTTP 1.0
response.setHeader("Expires", "0");//Proxies
%>

<!--# Content  #-->

<section id="v2-resent-job-post">
	<div class="vertical-space-85"></div>
	<div class="container">
		<h3 class="text-center">User Profile</h3>
		<div class="vertical-space-30"></div>
		<!-- <p class="max-width">Lorem ipsum tempus amet conubia adipiscing fermentum viverra gravida, mollis
                suspendisse pretium dictumst inceptos mattis euismod
            </p> -->
		<div class="vertical-space-60"></div>

		<div class="card-body">
			<form id="profile" action="" method="POST">
				<h6 class="heading-small text-muted mb-4">User information</h6>
				<div class="pl-lg-4">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="first_name">First
									name</label> <input type="text" id="first_name" name="first_name"
									class="form-control" placeholder="First name" value="">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="last_name">Last
									name</label> <input type="text" id="last_name" name="last_name"
									class="form-control" placeholder="Last name" value="">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="email">Email
									address</label> <input type="email" id="email" name="email"
									class="form-control" placeholder="shubham@example.com">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<input type="button" id="submit_user_info"
									name="submit_user_info"
									class="form-control btn-outline-primary" value="Submit"
									onclick=submitfields(this.id)>
							</div>
						</div>
					</div>
				</div>
				<hr class="my-4" />

				<!-- Education -->
				<h6 class="heading-small text-muted mb-4">Education</h6>
				<div class="pl-lg-4">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="course">Course</label> <input
									type="text" id="course" name="course" class="form-control"
									placeholder="B.SC" value="">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="university">University</label>
								<input type="text" id="university" name="university"
									class="form-control" placeholder="University of Delhi" value="">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label" for="specialization">Specialization</label>
								<input type="text" id="specialization" name="specialization"
									class="form-control" placeholder="B.Sc (CS)" value="">
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label" for="p_year">Passing
									Year</label> <input type="number" id="p_year" name="p_year"
									class="form-control" placeholder="2020" value="">
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label" for="course_type">Course
									Type</label> <select id="course_type" name="course_type"
									class="form-control">
									<option value="Full_time" selected>Full Time</option>
									<option value="Part_time">Part Time</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<input type="button" id="submit_education"
									name="submit_education"
									class="form-control btn-outline-primary" value="Add Education"
									onclick=submitfields(this.id)>
							</div>
						</div>
					</div>
				</div>
				<hr class="my-4" />
				<!-- Certification -->
				<h6 class="heading-small text-muted mb-4">Certifications</h6>
				<div class="pl-lg-4">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="input-city">Title</label>
								<input type="text" id="input-city" class="form-control"
									placeholder="Google Cloud Developer" value="">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="input-country">Url</label>
								<input type="url" id="input-country" class="form-control"
									placeholder="Enter Url" value="">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="input-city">Issue
									Date</label> <input type="date" id="input-city" class="form-control"
									placeholder="Google Cloud Developer" value="">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="input-country">Expiry
									Date</label> <input type="date" id="input-country" class="form-control"
									placeholder="Enter Url" value="">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<input type="button" id="submit_certificatons"
									name="submit_certificatons"
									class="form-control btn-outline-primary"
									value="Add Certification" onclick=submitfields(this.id)>
							</div>
						</div>
					</div>
				</div>

				<hr class="my-4" />
				<!-- Certification -->
				<h6 class="heading-small text-muted mb-4">Resume</h6>
				<div class="pl-lg-4">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label class="form-control-label" for="resume">Upload
									Resume</label> <input type="file" id="resume" name="resume"
									accept=".pdf" class="form-control" placeholder="" value="">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<input type="button" id="submit_resume" name="submit_resume"
									class="form-control btn-outline-primary" value="Upload"
									onclick=submitfields(this.id)>
							</div>
						</div>
					</div>
				</div>

				<hr class="my-4" />
				<!-- Password -->
				<h6 class="heading-small text-muted mb-4">Password</h6>
				<div class="pl-lg-4">
					<div class="row">
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label" for="current_password">Current
									Password</label> <input type="password" id="current_password"
									name="current_password" class="form-control"
									placeholder="Enter Current-Password" value="">
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label" for="n_password">New
									Password</label> <input type="password" id="n_password"
									name="n_password" class="form-control"
									placeholder="Enter New Password" value="">
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label" for="re_password">Re-Enter
									Password</label> <input type="password" id="re_password"
									name="re_password" class="form-control"
									placeholder="Re-Enter Password" value="">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<input type="button" id="submit_password" name="submit_password"
									class="form-control btn-outline-primary"
									value="Update Password" onclick=submitfields(this.id)>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
            form = document.getElementById("profile");
            function submitfields(ids) {
                switch (ids) {
                    case "submit_password":
                        form.action = "ids";
                        form.submit();
                        break;
                    case "submit_resume":
                    form.action = "uploadResume";
                        form.submit();
                        break;
                    case "submit_certificatons":
                    form.action = "ids";
                        form.submit();
                        break;
                    case "submit_education":
                    form.action = "addEducation";
                        form.submit();
                    break;
                    case "submit_user_info":
                    form.action = "ids";
                        form.submit();
                    break; 
                    default:
                        alert("Invalid Click");
                }
            }             
        </script>
</section>


<!--# Content END #-->

<%@ include file="footer.jsp"%>