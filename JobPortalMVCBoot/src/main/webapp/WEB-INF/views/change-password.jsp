<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="edit-profile-header.jsp"%>
${message}
<div class="col-lg-6">
	<!-- Password -->
	<form:form id="profile" action="updatePassword" method="POST"
		 modelAttribute="jobSeeker">
		<div id="changePassword">

			<h6 class="heading-small text-muted mb-4">Password</h6>
			<div class="pl-lg-4">
				<div class="row">
					<div class="col-lg-6">
						<%-- <div class="form-group">
							<label class="form-control-label" for="current_password">Current
								Password</label> <input type="password" id="current_password"
								name="current_password" class="form-control"
								placeholder="Enter Current-Password"
								value="${jobSeeker.users.password}">
						</div> --%>
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
						<input type="submit" id="submit_password" name="submit_password"
							class="form-control btn-outline-primary" value="Update Password"
							>
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