<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

<title>NANUGI: MAIN</title>
</head>
<body>
	
	<div class="container pb-5">
		<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
		<jsp:include page="/WEB-INF/views/user/commons/global_banner.jsp" /> <!-- 헤더 배너 -->
		<div class="row">
			<div class="col">
				<div class="row row-cols-1 row-cols-md-4 g-4 mt-4">
				  <div class="col">
				    <div class="card h-100" onclick="location.href='../donate/list_page.do'" style="cursor: pointer; background-color: #5B6884">
				      <div class="card-body">
				      	<h4 class="card-title my-3"><span class="badge bg-secondary">기부</span></h4>
				        <div class="card-text text-white">
				      		<div class="row">
				      			<div class="col py-2">
				      				<h3>당신의 참여가<br>변화의 시작입니다</h3>
				      			</div>
				      		</div>
				      		<div class="row">
				      			<div class="col">&gt;더보기</div>
				      		</div>
				      		<hr class="my-5">
				        	<div class="row">
				        		<div class="col-5">
				        			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
											  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
											</svg>
											참여
										</div>
				        		<div class="col">${donateStatistics.donateParticipantCount } 명</div>
				        	</div>
				        	<div class="row">
				        		<div class="col-5">
				        			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cash" viewBox="0 0 16 16">
											  <path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"/>
											  <path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"/>
											</svg>
				        			기부금액
				        		</div>
				        		<div class="col">${donateStatistics.donateTotalPoint } P</div>
				        	</div>
				        </div>
				      </div>
				    </div>
				  </div>
				   <!-- 기부 리스트 표시 -->
					<c:forEach items="${donateList }" var="donate">
					  <div class="col">
					    <div class="card h-100 bg-light" onclick="location.href='../donate/read_page.do?mst_d_no=${donate.mstDonateVo.mst_d_no }'" style="cursor: pointer;">
				      	<img src="${donate.thumnailImg.mst_di_img_link }" class="card-img-top" style="height: 13rem;">
					      <div class="card-body">
				        	<h5 class="card-title text">
				        		${donate.mstDonateVo.mst_d_title }
				        	</h5>
					        <p class="card-text">
					        	<div>
						        	${donate.dateFrom } ~ ${donate.dateTo } 
						        	<c:if test="${donate.donateStatus == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
						        	<c:if test="${donate.donateStatus == 2}"><span class="badge bg-success">진행중</span></c:if>
						        	<c:if test="${donate.donateStatus == 3}"><span class="badge bg-secondary">종료</span></c:if>
					        	</div>
					        	<div class="text-secondary">
					        		${donate.memberVo.m_nick }
					        	</div>
					        	<div class="progress">
										  <div class="progress-bar bg-warning" role="progressbar" style="width: ${donate.percent }%" aria-valuenow="${donate.percent }" aria-valuemin="0" aria-valuemax="100"></div>
										</div>
										<div class="row">
											<div class="col-3">${donate.percent }%</div>
											<div class="col text-end">${donate.totalPoint } P</div>
										</div>
					        </p>
					      </div>
					    </div>
					  </div>
					</c:forEach>
				</div>
				
				<div class="row row-cols-1 row-cols-md-4 g-4 mt-4">  
				 <div class="col">
				    <div class="card h-100"  onclick="location.href='../volunteer/list_page.do'" style="cursor: pointer; background-color: #3C9870">
				      <div class="card-body">
				      	<h4 class="card-title my-3"><span class="badge bg-secondary">봉사</span></h4>
				        <div class="card-text text-white">
				      		<div class="row">
				      			<div class="col py-2">
				      				<h3>백마디 말보다<br>한번의  행동으로</h3>
				      			</div>
				      		</div>
				      		<div class="row">
				      			<div class="col">&gt;더보기</div>
				      		</div>
				      		<hr class="my-5">
				        	<div class="row">
				        		<div class="col-5">
				        			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
											  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
											</svg>
											참여
										</div>
				        		<div class="col">${volunteerStatistics.volunteerParticipantCount } 명</div>
				        	</div>
				        </div>
				      </div>
				    </div>
				  </div>
				   <c:forEach items="${volunteerList }" var="volunteer">
				  <div class="col">
				    <div class="card h-100 bg-light " onclick="location.href='../volunteer/read_page.do?mst_v_no=${volunteer.mstVolunteerVo.mst_v_no }'" style="cursor: pointer;">
				      <img style="height: 13rem; " src="${volunteer.imgvo.mst_vi_img_link  }" class="card-img-top" alt="...">
				      <div class="card-body">
				        <h5 class="card-title">${volunteer.mstVolunteerVo.mst_v_title }</h5>
				        <p class="card-text">
					        	<div>
						        	${volunteer.dateFrom } ~ ${volunteer.dateTo } 
						        	<c:if test="${volunteer.volunteerStauts == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
						        	<c:if test="${volunteer.volunteerStauts == 2}"><span class="badge bg-success">진행중</span></c:if>
						        	<c:if test="${volunteer.volunteerStauts == 3}"><span class="badge bg-secondary">종료</span></c:if>
					        	</div>
					        	<div class="text-secondary">
					        		${volunteer.memberVo.m_nick }
					        	</div>
					        	<%-- <div class="progress">
								  <div class="progress-bar" role="progressbar" style="width: ${funding.percent }%" aria-valuenow="${funding.percent }" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<div class="row">
									<div class="col-3">${funding.percent }%</div>
									<div class="col text-end">${funding.totalPoint } P</div>
								</div> --%>
					        </p>
				      </div>
				    </div>
				  </div>					
			  </c:forEach>
		
				 </div>
				  <!-- 펀딩 -->
				<div class="row row-cols-1 row-cols-md-4 g-4 mt-4">
				  <div class="col">
				    <div class="card h-100 bgc" onclick="location.href='../funding/list_page.do'" style="cursor: pointer;">
				      <div class="card-body">
				      	<h4 class="card-title my-3"><span class="badge bg-secondary">펀딩</span></h4>
				        <div class="card-text text-white">
				      		<div class="row">
				      			<div class="col py-2">
				      				<h3>가치있는  소비가<br>세상을 바꾸는 이들을<br>응원합니다</h3>
				      			</div>
				      		</div>
				      		<div class="row">
				      			<div class="col">&gt;더보기</div>
				      		</div>
				      		<hr class="my-5">
				        	<div class="row">
				        		<div class="col-5">
				        			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
											  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
											</svg>
											참여
										</div>
				        		<div class="col">${fundingStatistics.fundingParticipantCount } 명</div>
				        	</div>
				        	<div class="row">
				        		<div class="col-5">
				        			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cash" viewBox="0 0 16 16">
											  <path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"/>
											  <path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"/>
											</svg>
				        			참여금액
				        		</div>
				        		<div class="col">${fundingStatistics.fundingTotalPoint } P</div>
				        	</div>
				        </div>
				      </div>
				    </div>
				  </div>
				  <!-- 펀딩 리스트 표시 -->
					<c:forEach items="${fundingList }" var="funding">
					  <div class="col">
					    <div class="card h-100 bg-light" onclick="location.href='../funding/read_page.do?mst_f_no=${funding.mstFundingVo.mst_f_no }'" style="cursor: pointer;">
				      	<img src="${funding.thumnailImg.mst_fi_img_link }" class="card-img-top" style="height: 13rem;">
					      <div class="card-body">
				        	<h5 class="card-title">
				        		${funding.mstFundingVo.mst_f_title }
				        	</h5>
					        <p class="card-text">
					        	<div>
						        	${funding.dateFrom } ~ ${funding.dateTo } 
						        	<c:if test="${funding.fundingStatus == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
						        	<c:if test="${funding.fundingStatus == 2}"><span class="badge bg-success">진행중</span></c:if>
						        	<c:if test="${funding.fundingStatus == 3}"><span class="badge bg-secondary">종료</span></c:if>
					        	</div>
					        	<div class="text-secondary">
					        		${funding.memberVo.m_nick }
					        	</div>
					        	<div class="progress">
										  <div class="progress-bar bg-warning" role="progressbar" style="width: ${funding.percent }%" aria-valuenow="${funding.percent }" aria-valuemin="0" aria-valuemax="100"></div>
										</div>
										<div class="row">
											<div class="col-3">${funding.percent }%</div>
											<div class="col text-end">${funding.totalPoint } P</div>
										</div>
					        </p>
					      </div>
					    </div>
					  </div>
					</c:forEach>
				  
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>