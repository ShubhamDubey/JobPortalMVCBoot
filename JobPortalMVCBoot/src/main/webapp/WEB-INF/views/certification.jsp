<%@include file="edit-profile-header.jsp"%>
${message}
<div class="col-lg-4">
	<form:form id="profile" action="addCertification" method="POST"
		modelAttribute="certification">
		<div id="certificate">
			<h6 class="heading-small text-muted mb-4">Certifications</h6>
			<div class="pl-lg-4">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-city">Title</label>
							<input type="text" id="input-city" name="certificationName" required="required"
								class="form-control" placeholder="Google Cloud Developer"
								value="">
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-country">Url</label>
							<input type="url" id="input-country" name="url"  required="required"
								class="form-control" placeholder="Enter Url" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-city">Issue
								Date</label> <input type="date" id="input-city" class="form-control" required="required"
								placeholder="Google Cloud Developer" name="issueDate" value="">
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-country">Expiry
								Date</label> <input type="date" id="input-country" class="form-control" required="required"
								name="expireDate" placeholder="Enter Url" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<input type="submit" id="submit_certificatons"
								name="submit_certificatons"
								class="form-control btn-outline-primary"
								value="Add Certification">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
<span
	style="display: inline-block; border-left: 1px solid #ccc; margin: 0 -7px; height: 400px;"></span>

<div class="col-lg-6">
	<table class="table table-striped">
		<c:set var="count" value="0" scope="page" />
		<thead>
			<tr>
				<th>Name</th>
				<th>Issue Date</th>
				<th>Expire Date</th>
				<th>Certification</th>
				<th>Action</th>
		</thead>
		<tbody>
			<c:forEach var="tempCertification" items="${certificationList}">
				<c:url var="deleteLink" value="deleteCertification">
					<c:param name="educationId" value="${count}" />
				</c:url>
				<tr>
					<td>${tempCertification.certificationName}</td>
					<td>${tempCertification.expireDate}</td>
					<td>${tempCertification.issueDate}</td>

					<td width=10px><a class="btn btn-info" target="_blank"  href="${tempCertification.url}">view</a></td>
					<td><a href="${deleteLink }" class="btn btn-danger">Delete</a></td>

				</tr>
				<c:set var="count" value="${count + 1}" scope="page" />
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
</div>
</div>
</section>
<div class="vertical-space-50"></div>
<%@include file="footer.jsp"%>
