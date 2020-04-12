<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="edit-profile-header.jsp"%>
${message}
<div class="col-lg-6">
	<form:form id="profile" action="saveJobSeeker" method="POST"
		modelAttribute="jobSeeker">
		<div id="editProfile">
			<h6 class="heading-small text-muted mb-4">User information</h6>

			<div class="pl-lg-4">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="first_name">First
								name </label> <input type="text" id="first_name" name="firstName"
								class="form-control" placeholder="First name"
								value="${jobseeker.firstName}">
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="last_name">Last
								name</label> <input type="text" id="last_name" name="lastName"
								class="form-control" placeholder="Last name"
								value="${jobseeker.lastName}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="email">Email
								address</label> <input type="email" id="email" name="email"
								class="form-control" placeholder="shubham@example.com"
								value="${jobseeker.users.email}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-5">
						<div class="form-group">
							<input type="submit" id="submit_education"
								name="submit_education" class="form-control btn-outline-primary"
								value="Update">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
</div>

</div>
</section>
<%@ include file="footer.jsp"%>