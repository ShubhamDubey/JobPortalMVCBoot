<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<head>
<title>Jobs | JobBazar | JobBazar</title>
</head>

<%@ include file="header.jsp"%>

<section id="v2-resent-job-post">
	<div class="vertical-space-85"></div>
	<div class="container text-center">
	<h3 class="text-center">Applied Jobs</h3>
	<div class="vertical-space-30"></div>
		<div class="row col-lg-2">
			<a href="welcome" class="btn btn-block btn-outline-primary"
				id="profile_id">Back</a>
		</div>
		<br>
		<table class="table table-striped"
			style="font-family: Poppins-Regular;">
			<thead>
				<tr>
					<th>Applied Date</th>
					<th>Job</th>
					<th>Company Name</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="0" scope="page" />
				<c:forEach var="tempApplicationList" items="${appliedJobList}">
					<c:url var="deleteLink" value="deleteApplication">
						<c:param name="applicationId" value="${count}" />
					</c:url>
					<tr>
						<td>${tempApplicationList.appliedDate}</td>
						<td>${tempApplicationList.job.name}</td>
						<td>${tempApplicationList.job.employerEmail}</td>
						<td>${tempApplicationList.job.description}</td>
						<td><a href="${deleteLink }" class="btn btn-danger">Not
								Interested</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</section>
<div class="vertical-space-85"></div>
<%@ include file="footer.jsp"%>