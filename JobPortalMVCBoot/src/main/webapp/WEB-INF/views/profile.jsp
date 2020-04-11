<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<style type="text/css">
.hide {
	display: none;
}
</style>
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
		<h1>${ msg}</h1>

		<div class="vertical-space-30"></div>

		<!-- <p class="max-width">Lorem ipsum tempus amet conubia adipiscing fermentum viverra gravida, mollis
                suspendisse pretium dictumst inceptos mattis euismod
            </p> -->
		<div class="vertical-space-60"></div>
		<div class="row">
			<div class="col-lg-4">
				<div class="container card-body">
					<div class="row ">
						<a href="#profile" class="btn btn-block btn-outline-primary"
							onclick=show_content(this.id) id="profile_id">Profile</a>
					</div>
					<div class="row ">
						<a href="#changePassword"
							class="btn btn-block btn-outline-primary"
							onclick=show_content(this.id) id="password_id">Change
							Password</a>
					</div>
					<div class="row ">
						<a href="#resume" class="btn btn-block btn-outline-primary"
							onclick=show_content(this.id) id="resume_id">Resume</a>
					</div>
					<div class="row ">
						<a href="#education" class="btn btn-block btn-outline-primary"
							onclick=show_content(this.id) id="education_id">Education</a>
					</div>
					<div class="row ">
						<a href="#certificate" class="btn btn-block btn-outline-primary"
							onclick=show_content(this.id) id="certification_id">Certifications</a>
					</div>
					<div class="row ">
						<a href="#skill" class="btn btn-block btn-outline-primary"
							onclick=show_content(this.id) id="skill_id">Skills</a>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="card-body">
					<form:form id="profile" action="" method="POST"
						enctype="multipart/form-data" modelAttribute="jobSeeker">
						<div id="editProfile">
							<h6 class="heading-small text-muted mb-4">User information</h6>

							<div class="pl-lg-4">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="first_name">First
												name </label> <input type="text" id="first_name" name="firstName"
												class="form-control" placeholder="First name"
												value="${jobSeeker.firstName}">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="last_name">Last
												name</label> <input type="text" id="last_name" name="lastName"
												class="form-control" placeholder="Last name"
												value="${jobSeeker.lastName}">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="email">Email
												address</label> <input type="email" id="email" name="email"
												class="form-control" placeholder="shubham@example.com"
												value="${jobSeeker.users.email}">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-5">
										<div class="form-group">
											<input type="button" id="submit_user_info"
												name="submit_user_info"
												class="form-control btn-outline-primary" value="Submit"
												onclick=submitfields(this.id)>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
					<!-- Education -->
					<form:form id="profile" action="" method="POST"
						modelAttribute="education">
						<div id="education">
							<h6 class="heading-small text-muted mb-4">Education</h6>
							<div class="pl-lg-4">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label class="form-control-label" for="course_type">Education
												Category</label> <select class="form-control"
												name="educationCategory.educationCategoryName">
												<option value="">Select Education Category</option>
												<c:forEach var="tempEduCat" items="${eduCat}">
													<option value="${tempEduCat.educationCategoryName}">${tempEduCat.educationCategoryName}</option>
												</c:forEach>

											</select>
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="course">Course</label>
											<input type="text" id="course" name="course"
												class="form-control" placeholder="B.SC" value="">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="university">University</label>
											<input type="text" id="university" name="university"
												class="form-control" placeholder="University of Delhi"
												value="">
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-4">
										<div class="form-group">
											<label class="form-control-label" for="specialization">Specialization</label>
											<input type="text" id="specialization" name="specialisation"
												class="form-control" placeholder="B.Sc (CS)" value="">
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="form-control-label" for="p_year">Passing
												Year</label> <input type="number" id="p_year" name="passingYear"
												class="form-control" placeholder="2020" value="">
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label class="form-control-label" for="course_type">Course
												Type</label> <select id="course_type" name="courseType"
												class="form-control">
												<option value="Full_time" selected>Full Time</option>
												<option value="Part_time">Part Time</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<input type="button" id="submit_education"
												name="submit_education"
												class="form-control btn-outline-primary"
												value="Add Education" onclick=submitfields(this.id)>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
					<!-- Certification -->
					<form:form id="profile" action="" method="POST"
						enctype="multipart/form-data" modelAttribute="jobSeeker">
						<div id="certificate">
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
												Date</label> <input type="date" id="input-country"
												class="form-control" placeholder="Enter Url" value="">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-5">
										<div class="form-group">
											<input type="button" id="submit_certificatons"
												name="submit_certificatons"
												class="form-control btn-outline-primary"
												value="Add Certification" onclick=submitfields(this.id)>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
					<!-- RESUME -->
					<form:form id="profile" action="" method="POST"
						enctype="multipart/form-data" modelAttribute="resumeFile">
						<div id="resume">
							<h6 class="heading-small text-muted mb-4">Resume</h6>
							<div class="pl-lg-4">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="resume">Upload
												Resume</label><input class="form-control" type="file" name="file"
												placeholder="Resume Upload" accept=".pdf">

										</div>
									</div>
								</div>


								<c:if test="${resume.id!=null}">
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="resume"> <a
												href="downloadResume/${resume.id}">Download
													Resume</a></label>
										</div>
									</div>
								</c:if>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="form-group">
										<input type="button" id="submit_resume" name="submit_resume"
											class="form-control btn-outline-primary" value="Upload"
											onclick=submitfields(this.id)>
									</div>
								</div>
							</div>
						</div>
				</div>
				</form:form>
				<!-- Password -->
				<form:form id="profile" action="" method="POST"
					enctype="multipart/form-data" modelAttribute="jobSeeker">
					<div id="changePassword">

						<h6 class="heading-small text-muted mb-4">Password</h6>
						<div class="pl-lg-4">
							<div class="row">
								<div class="col-lg-6">
									<div class="form-group">
										<label class="form-control-label" for="current_password">Current
											Password</label> <input type="password" id="current_password"
											name="current_password" class="form-control"
											placeholder="Enter Current-Password"
											value="${jobSeeker.users.password}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="form-group">
										<label class="form-control-label" for="n_password">New
											Password</label> <input type="password" id="n_password"
											name="n_password" class="form-control"
											placeholder="Enter New Password" value="">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="form-group">
										<label class="form-control-label" for="re_password">Re-Enter
											Password</label> <input type="password" id="re_password"
											name="re_password" class="form-control"
											placeholder="Re-Enter Password" value="">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-5"">
								<div class="form-group">
									<input type="button" id="submit_password"
										name="submit_password"
										class="form-control btn-outline-primary"
										value="Update Password" onclick=submitfields(this.id)>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>


	</div>
	</div>
	</div>
	<script>

	document.getElementById("education").style.display="none";

	document.getElementById("certificate").style.display="none";

	document.getElementById("resume").style.display="none";
	document.getElementById("changePassword").style.display="none";
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
                    form.action = "saveEducation";
                    form.submit();
                    break;
                    case "submit_user_info":
                    form.action = "ids";
                        form.submit();
                    break; 
                    default:
                        alert("Invalid Click"+ids);
                }
            }        

	        function hide(){
	        	document.getElementById("editProfile").style.display="none";

	        	document.getElementById("education").style.display="none";

	        	document.getElementById("certificate").style.display="none";

	        	document.getElementById("resume").style.display="none";
	        	document.getElementById("changePassword").style.display="none";	
	        }
            function show_content(ids){
            	//		display_none();
                switch (ids) {
                case "profile_id":hide();	document.getElementById("editProfile").style.display="block";                   
                    break;
                case "resume_id":hide();	document.getElementById("resume").style.display="block";                   
                break;
            
                case "certification_id":hide();	document.getElementById("certificate").style.display="block";                   
                break;
                case "education_id":hide();	document.getElementById("education").style.display="block";                   
                break;
                case "password_id":hide();	document.getElementById("changePassword").style.display="block";                   
                break;
                case "skill_id":hide();	document.getElementById("editProfile").style.display="block";                   
                break;
            
                default:
                    alert("Invalid Click"+id);
            }
        }       	
                
        </script>
</section>


<!--# Content END #-->

<%@ include file="footer.jsp"%>