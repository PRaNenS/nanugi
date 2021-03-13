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
<title>NANUGI: VOLUNTEER</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
	
	<div class="container mb-5 pt-5 min-height">
		<div class="row">
			<jsp:include page="/WEB-INF/views/user/mypage/commons/mypage_sidebar.jsp" /> <!-- 사이드바 -->
			<div class="col mx-3">
				<!-- 타이틀 -->
				<div class="row">
					<div class="col">
						<h3>봉사 조회</h3>
					</div>
				</div>
				
				<!-- 펀딩 표시 영역 -->
				<div class="row">
					<div class="col">
						<!-- 상단 제목 -->
						<div class="border-bottom border-top">
							<div class="row">
								<div class="col">
									<div class="row">
										<div class="col-2 fw-bold text-center">개설일</div>
										<div class="col fw-bold text-center">펀딩 정보</div>
										<div class="col-2 fw-bold text-center">비고</div>
									</div>
								</div>
							</div>
						</div>
						
						<c:forEach items="${volunteerList }" var="volunteer">
							<div class="row">
								<div class="col">
									<!-- 펀딩 표시 -->
									<div class="border-bottom px-2 py-2">
										<div class="row">
											<!-- 날짜 -->
											<div class="col-2">
												<div class="row">
													<div class="col text-center">${volunteer.createDate }</div>
													<a href="${pageContext.request.contextPath}/mypage/read_volunteer_page.do?mst_v_no=${volunteer.volunteerVo.mst_v_no }" class="none-underline text-center">상세내역</a>
												</div>
											</div>
											<!-- 펀딩정보 -->
											<div class="col">
												<div class="row">
													<!-- 펀딩이미지 -->
													<div class="col-2">
														<div class="card">
															<img class="card-img-top" src="${volunteer.imgvo.mst_vi_img_link }">
														</div>
													</div>
													<!-- 펀딩상세정보 -->
													<div class="col">
														<div class="row">
															<div class="col">
																${volunteer.volunteerVo.mst_v_title }
															</div>
														</div>
														<div class="row">
															<div class="col">
																<span>${volunteer.dateFrom } ~ ${volunteer.dateTo }</span>
																	<c:if test="${volunteer.volunteerStatus == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
														        	<c:if test="${volunteer.volunteerStatus == 2}"><span class="badge bg-success">진행중</span></c:if>
														        	<c:if test="${volunteer.volunteerStatus == 3}"><span class="badge bg-secondary">종료</span></c:if>
															</div>
														</div>
														<div class="row">
															<div class="col">
																목표 ${volunteer.volunteerVo.mst_v_goal}명 (${volunteer.joinCount}명)
															</div>
														</div>
														<div class="row">
															<div class="col fw-bold"><fmt:formatNumber value="${volunteer.progress }" pattern="0"/>%</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-2 text-center">
												<div class="row">
													<div class="col">
														<c:if test="${volunteer.volunteerStatus == 1}">
															<a href="${pageContext.request.contextPath}/mypage/delete_voluteer.do?mst_v_no=${volunteer.volunteerVo.mst_v_no }" ><input type="button" class="form-control btn btn-danger" value="봉사 삭제"></a>
														</c:if>
													</div>
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
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>	
</body>
</html>