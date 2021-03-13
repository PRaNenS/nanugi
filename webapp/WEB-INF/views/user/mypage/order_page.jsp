<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mypage/order_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: ORDER</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->

	<div class="container mb-5 min-height pt-5">
		<div class="row">
			<jsp:include page="/WEB-INF/views/user/mypage/commons/mypage_sidebar.jsp" /> <!-- 사이드바 -->
			<div class="col mx-3">
				<!-- 타이틀 -->
				<div class="row">
					<div class="col">
						<h3>주문 내역</h3>
					</div>
				</div>
				<!-- 주문 표시 영역 -->
				<div class="row">
					<div class="col">
						<div class="border-top border-bottom">
							<div class="row">
								<div class="col-2 fw-bold text-center">날짜</div>
								<div class="col fw-bold text-center">상품 정보</div>
								<div class="col-2 fw-bold text-center">상태</div>
							</div>
						</div>
					
						<c:forEach items="${orderList }" var="order">
							<!-- 주문상품 -->
							<div class="row">
								<div class="col">
									<div class="border-bottom py-2">
										<div class="row">
											<!-- 날짜 -->
											<div class="col-2">
												<div class="row">
													<div class="col text-center">${order.orderDate }</div>
												</div>
												<div class="row">
													<div class="col text-center">
														<a href="../mypage/read_order_page.do?of_no=${order.orderVo.of_no }" class="none-underline">상세내역</a>
													</div>
												</div>
											</div>
											<!-- 정보 -->
											<div class="col">
												<div class="row">
													<!-- 펀딩이미지 -->
													<div class="col-2">
														<div class="card">
															<img class="card-img-top" src="${order.funding.thumnailImg.mst_fi_img_link }">
														</div>
													</div>
													<div class="col">
														<!-- 펀딩정보 -->
														<div class="row">
															<div class="col">
																<div class="row">
																	<div class="col">${order.funding.memberVo.m_nick }</div>
																</div>
																<div class="row">
																	<div class="col fs-5 fw-bold">
																		<a href="../funding/read_page.do?mst_f_no=${order.orderVo.mst_f_no }" class="none-underline">${order.funding.mstFundingVo.mst_f_title }</a>
																	</div>
																</div>
																<div class="row">
																	<div class="col">주문번호 : ${order.orderVo.of_no }</div>
																</div>
															</div>
														</div>
														<!-- 옵션정보 -->
														<div class="row">
															<div class="col">
																<div class="border-top mt-1 pt-1">
																	<div class="row">
																		<div class="col fw-bold">${order.totalPrice } P</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- 상태 -->
											<div class="col-2">
												<div class="row">
													<div class="col text-center">
														<c:choose>
															<c:when test="${order.funding.fundingDateStatus == 1 }">준비중</c:when>
															<c:when test="${order.funding.fundingDateStatus == 2 }">진행중</c:when>
															<c:otherwise>${order.status.mst_os_status }</c:otherwise>
														</c:choose>
													</div>
												</div>
												<div class="row">
													<div class="col text-center">
														<c:if test="${order.orderVo.mst_os_no == 0 && order.funding.fundingDateStatus == 2}">
															<a href="#" class="none-underline" onclick="viewModal(${order.orderVo.of_no })">주문취소</a>
														</c:if>
														<c:if test="${order.orderVo.mst_os_no == 3 }">
															<a href="../mypage/update_delivery_process.do?of_no=${order.orderVo.of_no }&mst_f_no=${order.orderVo.mst_f_no }" class="none-underline">수취확인</a>
														</c:if>
														<c:if test="${order.orderVo.mst_os_no == 4 && order.reviewCount == 0 }">
															<a href="../review_funding/create_page.do?of_no=${order.orderVo.of_no }" class="none-underline">후기작성</a>
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
	
<!-- 주문취소창 -->
	<!-- Button trigger modal -->
	<button id="btn_modal" type="button" class="btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
	  Launch static backdrop modal
	</button>
	
	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Cancel</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        주문을 취소하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <a id="btn_cancel" class="btn btn-danger">주문취소</a>
	      </div>
	    </div>
	  </div>
	</div>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>