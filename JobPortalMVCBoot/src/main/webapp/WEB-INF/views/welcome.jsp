<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<head>
<title>Jobs | JobBazar | JobBazar</title>
</head>

<%@ include file="header.jsp"%>

<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
response.setHeader("Pragma", "no-cache");//HTTP 1.0
response.setHeader("Expires", "0");//Proxies
%>

<!-- <h2 align="center">Welcome To JobBazar</h2>  -->
<h4 align="center">${appliedJobmsg}</h4>
<h4 align="center">${loginusers.email}</h4>

<hr color="pink" size="3" />

<section id="v2-resent-job-post">
	<div class="vertical-space-85"></div>
	<div class="container text-center">
		<h3 class="text-center">Recent Job Post</h3>
		<div class="vertical-space-30"></div>
		<p class="max-width"></p>
		<div class="vertical-space-60"></div>

		<c:forEach var="tempjoblist" items="${joblist}">
			<!-- construct an "update" link with customer id -->
			<c:url var="appliedJobLink" value="appliedJob">

				<c:param name="jobId" value="${tempjoblist.id}" />
			</c:url>
			<div class="detail">
				<div class="media display-inline text-align-center">
					<img class="mr-3" style="width: 75px; height: 75px;"
						alt="company_logo"
						src="/resources/static/images/${tempjoblist.logo}">
					<div class="media-body text-left  text-align-center">
						<h6 class="large material-icon ">
							${tempjoblist.employerEmail} looking for ${tempjoblist.name}</h6>
						<i class="font-color-black">${tempjoblist.type}:</i> <span
							class="text">${tempjoblist.description}</span> <br /> <i
							class="fa fa-briefcase"></i> <span class="text font-size">${tempjoblist.vacancy}
							Available Vacancy</span>
						<p>id ${ appList.id }</p>
						<c:choose>
							<c:when test="${tempjoblist.applicaionsList.isEmpty()}">
								<div class="float-right margin-top text-align-center">
									<a href="${appliedJobLink}" class="part-full-time">APPLY1</a>
									<p class="date-time">Deadline: ${tempjoblist.expireDate}</p>

								</div>
							</c:when>
							<c:otherwise>

								<c:forEach var="applications"
									items="${tempjoblist.applicaionsList }">
									<c:choose>
										<c:when
											test="${applications.jobSeeker.id==loginusers.jobseeker.id }">
											<c:choose>
												<c:when test="applications.status">
													<div class="float-right margin-top text-align-center">
														<a href="${appliedJobLink }" class="part-full-time">APPLY2</a>
														<p class="date-time">Deadline:
															${tempjoblist.expireDate}</p>

													</div>
												</c:when>
												<c:otherwise>
													<div class="float-right margin-top text-align-center">
														<a class="part-full-time">APPLIED</a>
														<p class="date-time">Deadline:
															${tempjoblist.expireDate}</p>

													</div>
												</c:otherwise>
											</c:choose>

										</c:when>
										
										<c:otherwise>
											<div class="float-right margin-top text-align-center">
												<a class="part-full-time" href="${appliedJobLink }">APPLY3</a>
												<p class="date-time">Deadline: ${tempjoblist.expireDate}</p>

											</div>
										</c:otherwise>

									</c:choose>


								</c:forEach>


							</c:otherwise>
						</c:choose>
						<p class="date-time">Deadline: ${tempjoblist.expireDate}</p>

					</div>
				</div>
			</div>
		</c:forEach>
		<div class="vertical-space-20"></div>
		<div class="vertical-space-25"></div>
		<!-- <div class="job-list">
                <ul class="pagination justify-content-end margin-auto">
                    <li class="page-item"><a class="page-link pdding-none" href="javascript:void(0);"><i
                                class=" material-icons keyboard_arrow_left_right">keyboard_arrow_left</i></a></li>
                    <li class="page-item"><a class="page-link active" href="javascript:void(0);">1</a></li>
                    <li class="page-item"><a class="page-link" href="javascript:void(0);">2</a></li>
                    <li class="page-item"><a class="page-link" href="javascript:void(0);">3</a></li>
                    <li class="page-item"><a class="page-link" href="javascript:void(0);">4</a></li>
                    <li class="page-item">
                        <a class="page-link pdding-none" href="javascript:void(0);"> <i
                                class=" material-icons keyboard_arrow_left_right">keyboard_arrow_right</i></a>
                    </li>
                </ul>
            </div> -->
	</div>
	<div class="vertical-space-60"></div>
</section>


<%@ include file="footer.jsp"%>