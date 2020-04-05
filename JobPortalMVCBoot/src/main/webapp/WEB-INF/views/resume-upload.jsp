<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>


<!--# Register START #-->

<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100">
			<div class="login100-pic js-tilt" data-tilt>
				<img alt="img" src="/resources/static/images/img-01.png" />
			</div>
			<form:form action="uploadResume"
				cssClass="login100-form validate-form" modelAttribute="resumeFile" enctype="multipart/form-data">
				<span class="login100-form-title" style="color: red">
					${msg} </span>
				<span class="login100-form-title"> Resume Upload </span>
			
				<div class="wrap-input100 validate-input"
					data-validate="Name is required">
					<input class="input100" type="file" name="file"
						placeholder="Resume Upload"> <span class="focus-input100"></span>
					<span class="symbol-input100"> <i class="fa fa-user"
						aria-hidden="true"></i>
					</span>
				</div>

				<div class="container-login100-form-btn">
					<button class="login100-form-btn">Register</button>
				</div>

				<div class="text-center p-t-12">
					<span class="txt1"> Forgot </span> <a class="txt2" href="#">
						Password? </a>
				</div>

				<div class="text-center p-t-8">
					<a class="txt2" href="showLoginForm"> Go to Login <i
						class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
					</a>
				</div>
			</form:form>
		</div>
	</div>
</div>


<!--# Register END #-->
<%@include file="footer.jsp"%>
    