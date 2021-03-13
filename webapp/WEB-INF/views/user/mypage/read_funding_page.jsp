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
<title>NANUGI: READ FUNDING</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->

	<div class="container mb-5 min-height mt-5">
		<div class="row">
			<jsp:include page="/WEB-INF/views/user/mypage/commons/mypage_sidebar.jsp" /> <!-- 사이드바 -->
			<div class="col mx-3">
				<!-- 타이틀 -->
				<div class="row">
					<div class="col">
						<h3>펀딩 참여 조회</h3>
					</div>
				</div>
				
				<!-- 펀딩정보 -->
				<div class="row">
					<div class="col">
						<div class="border-top border-bottom px-2 py-2">
							<!--  펀딩정보 -->
							<div class="row">
								<div class="col-2">
									<div class="card">
										<img class="card-img-top" src="${funding.thumnailImg.mst_fi_img_link }">
									</div>
								</div>
								<div class="col">
									<div class="row">
										<div class="col fs-5 fw-bold">
											<a href="../funding/read_page.do?mst_f_no=${funding.mstFundingVo.mst_f_no }" class="none-underline">${funding.mstFundingVo.mst_f_title }</a>
										</div>
									</div>
									<div class="row">
										<div class="col">
											${funding.date_from } ~ ${funding.date_to } 
											<c:if test="${funding.fundingDateStatus == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
						        	<c:if test="${funding.fundingDateStatus == 2}"><span class="badge bg-success">진행중</span></c:if>
						        	<c:if test="${funding.fundingDateStatus == 3}"><span class="badge bg-secondary">종료</span></c:if>
										</div>
									</div>
									<div class="row">
										<div class="col">제작완료예정일 : ${funding.end_date } (${funding.fundingStatus.mst_fs_status })</div>
									</div>
									<div class="row">
										<div class="col">목표 : ${funding.mstFundingVo.mst_f_goal } P (${fundingPrice } P)</div>
									</div>
									<div class="row">
										<div class="col">
											<div class="border-bottom">
												<div class="row">
													<div class="col">
														<div class="progress">
														  <div class="progress-bar bg-info" role="progressbar" style="width: ${percent}%" aria-valuenow="${percent }" aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</div>
													<div class="col-1 fw-bold mb-1">${percent }%</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row mt-1">
										<div class="col">
											<c:forEach items="${options }" var="option">
												<div class="row">
													<div class="col">${option.mst_fo_name }</div>
													<div class="col-4">${option.mst_fo_price } P</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 참여자리스트 -->
				<div class="row mt-3">
					<div class="col">
						<div class="row">
							<div class="col">
								<div class="border-bottom border-top">
									<div class="row">
										<div class="col-2 fw-bold text-center">주문번호</div>
										<div class="col fw-bold text-center">주문내용</div>
										<div class="col-2 fw-bold text-center">포인트</div>
									</div>
								</div>
							</div>
						</div>
							
						<c:forEach items="${orders }" var="order">
							<div class="row">
								<div class="col">
									<div class="border-bottom py-1 px-1">
										<div class="row">
											<div class="col-2">
												<div class="row">
													<div class="col">${order.orderVo.of_no }</div>
												</div>
												<div class="row">
													<div class="col">(${order.orderStatus.mst_os_status })</div>
												</div>
											</div>
											<div class="col">
												<div class="row">
													<div class="col">${order.orderVo.of_receiver } (${order.orderer.m_nick })</div>
												</div>
												<div class="row">
													<div class="col-2">${order.orderVo.of_zip }</div>
													<div class="col">${order.orderVo.of_address1 }</div>
												</div>
												<div class="row">
													<div class="col">${order.orderVo.of_address2 }</div>
												</div>
												<div class="row border-bottom">
													<div class="col mb-1">${order.orderVo.of_request }</div>
												</div>
												<div class="row mt-1">
													<div class="col">
														<c:forEach items="${order.details }" var="detail">
															<div class="row">
																<div class="col">${detail.mstFOptionVo.mst_fo_name }</div>
																<div class="col-3">수량: ${detail.oDetailVo.od_quantity }</div>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
											<div class="col-2 text-end">${order.totalPrice } P</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
							
					</div>
				</div>
				<div class="row mt-4">
					<div class="col"></div>
					<div class="col-2">
						<c:if test="${funding.mstFundingVo.mst_fs_no == 0 && funding.fundingDateStatus == 3}">
							<a href="../mypage/update_status_process.do?mst_f_no=${funding.mstFundingVo.mst_f_no }&mst_fs_no=1" class="btn btn-primary form-control">제작시작</a>
						</c:if>
						<c:if test="${funding.mstFundingVo.mst_fs_no == 1 && funding.fundingDateStatus == 3}">
							<a href="../mypage/update_status_process.do?mst_f_no=${funding.mstFundingVo.mst_f_no }&mst_fs_no=2" class="btn btn-primary form-control">제작완료</a>
						</c:if>
						<c:if test="${funding.mstFundingVo.mst_fs_no == 2 && funding.fundingDateStatus == 3}">
							<a href="../mypage/update_status_process.do?mst_f_no=${funding.mstFundingVo.mst_f_no }&mst_fs_no=3" class="btn btn-primary form-control">배송시작</a>
						</c:if>
					</div>
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