<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<head>
<title>Jobs | JobBazar | JobBazar</title>
</head>

<%@ include file="header.jsp"%>

<section id="v2-resent-job-post">
	<div class="vertical-space-85"></div>
	<div class="container text-center">
		<div class="row col-lg-3">
			<a href="welcome" class="btn btn-block btn-outline-primary"
				id="profile_id">Back</a>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Applied Date</th>
					<th>Job</th>
					<th>Company Name</th>
					<th>Description</th>
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
						<td><a href="${deleteLink }" class="btn btn-danger">Not Interested</a></td>

					</tr>



				</c:forEach>

			</tbody>
		</table>
	</div>
</section>
<%@ include file="footer.jsp"%>