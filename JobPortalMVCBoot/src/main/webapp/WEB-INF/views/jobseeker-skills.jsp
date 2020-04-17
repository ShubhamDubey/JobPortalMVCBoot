<%@include file="edit-profile-header.jsp"%>
${message}
<div class="col-lg-4">
	<form:form id="profile" action="addSkills" method="POST"
		modelAttribute="theSkills">
		<div id="skills">
			<h6 class="heading-small text-muted mb-4">Skills</h6>
			<div class="pl-lg-4">
				<div class="row">
					
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-country">Skills (write seprate by comma eg. skill1,skill2)</label> <textarea class="form-control" name="name" rows="4" cols="50"></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-5">
						<div class="form-group">
							<input type="submit" id="submit_certificatons"
								name="submit_certificatons"
								class="form-control btn-outline-primary"
								value="Add Skills">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>

</div>
<div class="col-lg-2">
<table class="table table-striped">
				<c:set var="count" value="0" scope="page" />
				<thead>
					<tr>
						<th>Skills</th>
				</thead>
				<tbody>
					<c:forEach var="tempSkills" items="${skillsList}">
						<c:url var="deleteLink" value="deleteCertification">
						<c:param name="skillsId"
							value="${count}" />
					</c:url>
						<tr>
							<td>
								${tempSkills.name}</td>	
				
						</tr>
						<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>
				</tbody>
			</table> 
</div>
</div>
</div>
</div>
</section>
<%@include file="footer.jsp"%>
