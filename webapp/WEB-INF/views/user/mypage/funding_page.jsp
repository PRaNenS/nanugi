<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: FUNDING</title>
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
						<h3>펀딩 조회</h3>
					</div>
				</div>
				
				<!-- 펀딩 표시 영역 -->
				<div class="row">
					<div class="col">
						<div class="border-bottom border-top">
						<!-- 상단 제목 -->
						<div class="row">
							<div class="col">
								<div class="row">
									<div class="col-2 fw-bold text-center">개설일</div>
									<div class="col fw-bold text-center">펀딩 정보</div>
									<div class="col-2 fw-bold text-center">상태</div>
								</div>
							</div>
						</div>
					</div>
						
						<c:forEach items="${fundingList }" var="funding">
							<!-- 펀딩 표시 -->
							<div class="row">
								<div class="col">
									<div class="border-bottom px-2 py-2">
										<div class="row">
										
											<!-- 날짜 -->
											<div class="col-2">
												<div class="row">
													<div class="col text-center">${funding.createDate }</div>
													<a href="./read_funding_page.do?mst_f_no=${funding.fundingVo.mst_f_no }" class="none-underline text-center">상세내역</a>
												</div>
											</div>
											<!-- 펀딩정보 -->
											<div class="col">
												<div class="row">
													<!-- 펀딩이미지 -->
													<div class="col-2">
														<div class="card">
															<img class="card-img-top" src="${funding.thumnailImg.mst_fi_img_link }">
														</div>
													</div>
													<!-- 펀딩상세정보 -->
													<div class="col">
														<div class="row">
															<div class="col">
																<a href="../funding/read_page.do?mst_f_no=${funding.fundingVo.mst_f_no }" class="none-underline">${funding.fundingVo.mst_f_title }</a>
															</div>
														</div>
														<div class="row">
															<div class="col">
																<span>${funding.dateFrom } ~ ${funding.dateTo }</span>
																<c:if test="${funding.fundingDateStatus == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
											        	<c:if test="${funding.fundingDateStatus == 2}"><span class="badge bg-success">진행중</span></c:if>
											        	<c:if test="${funding.fundingDateStatus == 3}"><span class="badge bg-secondary">종료</span></c:if>
															</div>
														</div>
														<div class="row">
															<div class="col">목표 : ${funding.fundingVo.mst_f_goal } P (${funding.totalPrice } P)</div>
														</div>
														<div class="row">
															<div class="col fw-bold">${funding.percent }%</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-2 text-center">
												<div class="row">
													<div class="col">${funding.fundingStatus.mst_fs_status }</div>
												</div>
												<div class="row">
													<div class="col">
														<c:if test="${funding.fundingVo.mst_fs_no == 0 && funding.fundingDateStatus == 3}">
															<a href="../mypage/update_status_process.do?mst_f_no=${funding.fundingVo.mst_f_no }&mst_fs_no=1" class="none-underline">제작시작</a>
														</c:if>
														<c:if test="${funding.fundingVo.mst_fs_no == 1 && funding.fundingDateStatus == 3}">
															<a href="../mypage/update_status_process.do?mst_f_no=${funding.fundingVo.mst_f_no }&mst_fs_no=2" class="none-underline">제작완료</a>
														</c:if>
														<c:if test="${funding.fundingVo.mst_fs_no == 2 && funding.fundingDateStatus == 3}">
															<a href="../mypage/update_status_process.do?mst_f_no=${funding.fundingVo.mst_f_no }&mst_fs_no=3" class="none-underline">배송시작</a>
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
	
	<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">평가</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
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