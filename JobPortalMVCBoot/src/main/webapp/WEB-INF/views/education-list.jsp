<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%@ include file="header.jsp"%>
<section id="v2-resent-job-post">
	<div class="vertical-space-85"></div>
	<div class="container text-center">
		<h3 class="text-center">Education Records</h3>
		<div class="vertical-space-30"></div>
		<p class="max-width"></p>
		<div class="vertical-space-60"></div>
		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Category</th>
						<th>Degree</th>
						<th>Course Type</th>
						<th>specialization</th>
						<th>University</th>
						<th>passing year</th>
				</thead>
				<tbody>
					<c:forEach var="tempEducationList" items="${educationList}">

						<tr>
							<td>
								${tempEducationList.educationCategory.educationCategoryName}</td>
							<td>${tempEducationList.course}</td>
							<td>${tempEducationList.courseType}</td>
							<td>${tempEducationList.specialisation}</td>
							<td>${tempEducationList.university}</td>
							<td>${tempEducationList.passingYear}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


	</div>

</section>
<div class="vertical-space-50"></div>

<%@ include file="footer.jsp"%>