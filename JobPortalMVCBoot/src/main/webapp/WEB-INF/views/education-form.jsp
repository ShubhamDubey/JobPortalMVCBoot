<%@include file="edit-profile-header.jsp"%>
${message}
<div class="col-lg-4">
	<form:form id="profile" action="saveEducation" method="POST"
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
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label" for="course">Course</label> <input
								type="text" id="course" name="course" class="form-control"
								placeholder="B.SC" value="">
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label" for="university">University</label>
							<input type="text" id="university" name="university"
								class="form-control" placeholder="University of Delhi" value="">
						</div>
					</div>
						<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label" for="university">University</label>
							<input type="text" id="percentage" name="percentage"
								class="form-control" placeholder="Enter your CGP/Percentage" value="">
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
							<input type="submit" id="submit_education"
								name="submit_education" class="form-control btn-outline-primary"
								value="Add Education">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>

</div>
<div class="col-lg-6">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Category</th>
				<th>Degree</th>
				<th>Course Type</th>

				<th>University</th>
				<th>Percentage</th>
				<th>passing year</th>
				<th colspan=2>Action</th>
		</thead>
		<tbody>
		
			<c:set var="count" value="0" scope="page" />
				<c:forEach var="tempEducationList" items="${educationList}">
					<c:url var="deleteLink" value="deleteEducation">
						<c:param name="educationId"
							value="${count}" />
					</c:url>
			
					<tr id="${tempEducationList.educationId}1">
						<td>
							${tempEducationList.educationCategory.educationCategoryName}</td>
						<td>${tempEducationList.course}</td>
						

						<td>${tempEducationList.courseType}</td>

						<td>${tempEducationList.university}  </td>
						<td>${tempEducationList.percentage }</td>
						<td>${tempEducationList.passingYear}</td>
				<%-- 	<td><button id="${count}" type="button" onclick="buttonClick(this.id)" class="btn btn-primary"   name="selectedRow">Update</button></td>  --%>
						<td><a href="${deleteLink }" class="btn btn-danger">Delete</a></td>
					</tr>
					


		<c:set var="count" value="${count + 1}" scope="page"/>
				</c:forEach>
	
		</tbody>
	</table>
</div>
</div>

</div>
</section>



<%@include file="footer.jsp"%>
