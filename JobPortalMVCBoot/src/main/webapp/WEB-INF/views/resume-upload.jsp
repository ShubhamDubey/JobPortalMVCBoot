<%@include file="edit-profile-header.jsp"%>
${message}
	<div class="col-lg-6">
<form:form id="profile" action="uploadResume1" method="POST"
	enctype="multipart/form-data" modelAttribute="resumeFile">
	<div id="resume">
		<h6 class="heading-small text-muted mb-4">Resume</h6>
		<div class="pl-lg-4">
			<div class="row">
				<div class="col-lg-6">
					<div class="form-group">
						<label class="form-control-label" for="resume">Upload
							Resume</label><input class="form-control" type="file" name="file"
							placeholder="Resume Upload" accept=".pdf">

					</div>
				</div>
			</div>


			<c:if test="${resume.id!=null}">
				<div class="col-lg-6">
					<div class="form-group">
						<label class="form-control-label" for="resume"> <a
							href="downloadResume1/${resume.id}">Download Resume</a></label>
					</div>
				</div>
			</c:if>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<input type="submit" id="submit_resume" name="submit_resume"
						class="form-control btn-outline-primary" value="Upload">
				</div>
				<div class="form-group">
					<c:if test="${resumeId!=null}">
						<a class="part-full-time btn btn-outline-primary"
							href="downloadResume/${resumeId}">Download: ${resumeName}</a>
					</c:if>
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
<%@include file="footer.jsp"%>
