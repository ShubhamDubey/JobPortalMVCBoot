<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="header.jsp"%>


<!--# Register START #-->

<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100">
			<div class="login100-pic js-tilt" data-tilt>
				<img alt="img" src="/resources/static/images/img-01.png" />
			</div>

			<form:form action="saveEducation"
				cssClass="login100-form validate-form" modelAttribute="education">
				<span class="login100-form-title" style="color: red"> ${msg}
				</span>
				<span class="login100-form-title"> Education Details </span>

				<div class="wrap-input100 validate-input"
					data-validate="Select Course required">
					<select class="input100"
						name="educationCategory.educationCategoryName">
						<option value="">Select Education Category</option>
						<c:forEach var="tempEduCat" items="${eduCat}">
							<option value="${tempEduCat.educationCategoryName}">${tempEduCat.educationCategoryName}</option>
						</c:forEach>

					</select> <span class="focus-input100"></span> <span class="symbol-input100">
						<i class="fa fa-arrow-right" aria-hidden="true"></i>
					</span>
				</div>
				<div class="wrap-input100 validate-input"
					data-validate="Name is required">
					<input class="input100" type="text" name="course"
						placeholder="Enter your Degree"> <span
						class="focus-input100"></span> <span class="symbol-input100">
						<i class="fa fa-arrow-right" aria-hidden="true"></i>
					</span>
				</div>
				<div class="wrap-input100 validate-input"
					data-validate="Kindly select course Type">
					<select class="input100" name="courseType">
						<option>Select course type</option>
						<option value="full_time">Full Time</option>
						<option value="part_time">Part Time</option>
						<option value="correspondence">Correspondence/Distance
							learning</option>
					</select> <span class="focus-input100"></span> <span class="symbol-input100">
						<span class="symbol-input100"> <i class="fa fa-arrow-right"
							aria-hidden="true"></i>
					</span>
					</span>
				</div>
				<div class="wrap-input100 validate-input"
					data-validate="Enter specialisation">
					<input class="input100" type="text" name="specialisation"
						placeholder="Enter specialisation "> <span
						class="focus-input100"></span> <span class="symbol-input100">
						<i class="fa fa-arrow-right" aria-hidden="true"></i>
					</span>
				</div>
				<div class="wrap-input100 validate-input"
					data-validate="Enter university name">
					<input class="input100" type="text" name="university"
						placeholder="Enter university name"> <span
						class="focus-input100"></span> <span class="symbol-input100">
						<i class="fa fa-arrow-right" aria-hidden="true"></i>
					</span>
				</div>
				<div class="wrap-input100 validate-input"
					data-validate="Enter passing year">
					<input class="input100" type="Number" name="passingYear"
						placeholder="Enter passing year"> <span
						class="focus-input100"></span> <span class="symbol-input100">
						<i class="fa fa-arrow-right" aria-hidden="true"></i>
					</span>
				</div>
				<div class="container-login100-form-btn">
					<button class="login100-form-btn">Add Education</button>
				</div>
			</form:form>
			<div class="float-right margin-top text-align-center">
			<a  href="viewEducation" class="part-full-time">Education Details</a>
			</div>
		</div>
	</div>
</div>


<%@ include file="footer.jsp"%>