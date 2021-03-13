<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<title>NANUGI: READ VOLUNTEER</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->

	<div class="container mb-5 min-height">
		<div class="row">
			<jsp:include page="/WEB-INF/views/user/mypage/commons/mypage_sidebar.jsp" /> <!-- 사이드바 -->
			<div class="col">
				<!-- 타이틀 -->
				<div class="row mt-5 border-bottom">
					<div class="col text-center">
						<h3>봉사 참가 리스트</h3>
					</div>
				</div>
				
					<!-- 펀딩정보 -->
				<div class="row border-bottom mt-3">
					<div class="col">
						<!--  펀딩정보 -->
						<div class="row">
							<div class="col-2">
								<div class="card">
									<img class="card-img-top" src="${volunteerData.imgvo.mst_vi_img_link}">
								</div>
							</div>
							<div class="col">
								<div class="row">
									<div class="col">${volunteerData.mstVolunteerVo.mst_v_title}</div>
								</div>
								<div class="row">
									<div class="col">${volunteerData.date_from } ~ ${volunteerData.date_to}</div>
									<div class="col">모집인원 : ${volunteerData.mstVolunteerVo.mst_v_goal }명 </div>
								</div>
								<div class="row border-bottom">
									<div class="col">
										<div class="progress" style="height: 20px;">
										  <div id="progress_bar" class="progress-bar" role="progressbar" style="width: ${volunteerData.progress }%"  aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"><fmt:formatNumber value=" ${volunteerData.progress }" pattern="0"/>%</div>
										</div>
										신청 인원 : ${volunteerData.joinCount }명
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row border-bottom">
					<div class="col-2 fw-bold text-center">신청 번호</div>
					<div class="col fw-bold text-center">신청 정보</div>
					<div class="col-2 fw-bold text-center">승인 여부</div>
				</div>
				<c:forEach items="${detailList }" var="detail">
				<!-- 참여자리스트 -->
				<div class="row mt-3">
					<div class="col">
						<div class="row border-bottom">
							<div class="col">
								<div class="row">
									<div class="col-2 text-center mt-4">${detail.detail.jv_no }</div>
									<div class="col">
										<div class="row">
											<div class="col">${detail.membervo.m_name} (${detail.membervo.m_id})</div>
											<div class="col">${detail.membervo.m_gender}</div>
										</div>
										<div class="row mt-1">
											<div class="col-2">${detail.membervo.m_address1 } ${detail.membervo.m_address2 }</div>
										</div>
										<div class="row">
											<div class="col">${detail.membervo.m_email }</div>
										</div>
											<div class="row mt-2">
												<div class="col">${detail.datevo.mst_vd_date }</div>
												<div class="col-3">${detail.datevo.mst_vd_time }</div>
											</div>
									</div>
									<div class="col-2 text-end">
										<div class="row" ></div>
										<c:choose>
											<c:when test="${detail.detail.mst_vs_no == 1}">
												<div class="row mt-2">
													<div class="col">
														<a href="${pageContext.request.contextPath}/mypage/update_status_pass.do?jv_no=${detail.detail.jv_no}"><input class="form-control btn btn-primary" type="button" value="승인"></a>
												 	</div>
												</div>
												<div class="row mt-2">
													<div class="col">
														<a href="${pageContext.request.contextPath}/mypage/update_status_fail.do?jv_no=${detail.detail.jv_no}"><input class="form-control btn btn-primary" type="button" value="거절"></a>
												 	</div>
												</div>
											</c:when>
											<c:when test="${detail.detail.mst_vs_no == 2}">
												<input class="form-control btn btn-primary mt-4" type="text" value="참가 승인됨" disabled>
											</c:when>
											<c:otherwise>
												<input class="form-control btn btn-danger mt-4" type="text" value="참가  거절됨"  disabled>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
				
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>	
</body>
</html>