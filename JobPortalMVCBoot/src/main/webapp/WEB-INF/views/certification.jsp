<%@include file="edit-profile-header.jsp"%>
	${message}
	
	<form:form id="profile" action="addCertification" method="POST"
						 modelAttribute="certification">
						<div id="certificate">
							<h6 class="heading-small text-muted mb-4">Certifications</h6>
							<div class="pl-lg-4">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="input-city">Title</label>
											<input type="text" id="input-city" name="certificationName" class="form-control"
												placeholder="Google Cloud Developer" value="">
										</div>
									</div>
									<div class="col-lg-6">
										<div class="form-group">
											<label class="form-control-label" for="input-country">Url</label>
											<input type="url" id="input-country" name="url" class="form-control"
												placeholder="Enter Url" value="">
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
												Date</label> <input type="date" id="input-country"
												class="form-control" name="expireDate" placeholder="Enter Url" value="">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-5">
										<div class="form-group">
											<input type="submit" id="submit_certificatons"
												name="submit_certificatons"
												class="form-control btn-outline-primary"
												value="Add Certification" >
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
<%-- 	<c:forEach var="tempCertificate" items="${certificationList}">
	<h1>${tempCertificate.certificationName}</h1>
	</c:forEach> --%>
					</div>
					</div>

</div>
</section>
<%@include file="footer.jsp"%>
