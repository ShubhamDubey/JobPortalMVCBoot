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
							<input type="text" id="input-city" name="certificationName"
								class="form-control" placeholder="Google Cloud Developer"
								value="">
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-country">Url</label>
							<input type="url" id="input-country" name="url"
								class="form-control" placeholder="Enter Url" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-city">Issue
								Date</label> <input type="date" id="input-city" class="form-control"
								placeholder="Google Cloud Developer" name="issueDate" value="">
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<label class="form-control-label" for="input-country">Expiry
								Date</label> <input type="date" id="input-country" class="form-control"
								name="expireDate" placeholder="Enter Url" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-5">
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
<div class="col-lg-6">
<table class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Issue Date</th>
						<th>Expire Date</th>
						<th>Certification</th>
				
				</thead>
				<tbody>
					<c:forEach var="tempCertification" items="${certificationList}">

						<tr>
							<td>
								${tempCertification.certificationName}</td>
							<td>
								${tempCertification.expireDate}</td>
							<td>
								${tempCertification.issueDate}</td>
					
							<td width=10px><a href="${tempCertification.url}">view Certificate</a></td>
				
						</tr>
					</c:forEach>
				</tbody>
			</table>
</div>
</div>
</div>
</div>
</section>
<%@include file="footer.jsp"%>
