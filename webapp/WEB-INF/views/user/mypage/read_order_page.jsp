<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mypage/read_order_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: READ ORDER</title>
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
						<div class="border-bottom mb-3">
							<h3>주문 내역 조회</h3>
						</div>
					</div>
				</div>
				
				<!-- 주문상품 -->
				<div class="row">
					<div class="col">
						<div class="row">
							<div class="col">
								<h5>주문상품</h5>
							</div>
						</div>
						<div class="row">
							<!-- 이미지 -->
							<div class="col-2">
								<div class="card h-100">
									<img class="card-img-top" src="${fundingData.thumnailImg.mst_fi_img_link }">
								</div>
							</div>
							<!-- 제품정보 -->
							<div class="col">
								<div class="row">
									<div class="col">
										<div class="border-bottom fw-bold fs-5">
											<a href="../funding/read_page.do?mst_f_no=${orderData.orderVo.mst_f_no }" class="none-underline">${fundingData.mstFundingVo.mst_f_title }</a>
											<span class="badge rounded-pill bg-secondary float-end">${orderData.orderStatus.mst_os_status }</span>
										</div>
									</div>
								</div>
								<div class="row mt-1">
									<div class="col">
										<img src="${fundingData.memberVo.m_profile_img_link }" style="width: 40px; height: 40px;">
										<span class="fw-bold">${fundingData.memberVo.m_nick }</span>
									</div>
								</div>
							</div>
						</div>
						<!-- 옵션정보 -->
						<div class="row mt-2">
							<div class="col">
								<div class="border-top border-bottom">
									<div class="row">
										<div class="col fw-bolder">옵션명</div>
										<div class="col-2 fw-bolder">단가</div>
										<div class="col-1 fw-bolder">수량</div>
										<div class="col-2 fw-bolder">총금액</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row py-1">
							<div class="col">
								<c:forEach items="${orderDetailData }" var="option">
									<div class="row">
										<div class="col">${option.mstFOptionVo.mst_fo_name }</div>
										<div class="col-2 text-end">${option.mstFOptionVo.mst_fo_price } P</div>
										<div class="col-1 text-end">${option.oDetailVo.od_quantity }</div>
										<div class="col-2 text-end">${option.sumPrice } P</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="border-top fw-bold text-end fs-5">
							총 주문금액 ${totalPrice } P
						</div>
					</div>
				</div>
				
				<!-- 배송지 -->
				<div class="row mt-3">
					<div class="col">
						<h5>배송지</h5>
					</div>
				</div>
				<!-- 수취인 -->
				<div class="row">
					<div class="col-3 form-floating">
						<input id="receiver" type="text" class="form-control" value="${orderData.orderVo.of_receiver }"<c:if test="${orderData.orderVo.mst_os_no > 2 }"> disabled</c:if>>
		 				<label>수취인</label>
					</div>
					<div class="col"></div>
				</div>
				<!-- 주소 -->
				<div class="row mt-2">
					<div class="col-2 form-floating">
						<input id="zip" type="text" class="form-control" value="${orderData.orderVo.of_zip }"<c:if test="${orderData.orderVo.mst_os_no > 2 }"> disabled</c:if>>
		 				<label>우편번호</label>
		 			</div>
					<div class="col form-floating">
						<input id="address1" type="text" class="form-control" value="${orderData.orderVo.of_address1 }"<c:if test="${orderData.orderVo.mst_os_no > 2 }"> disabled</c:if>>
		 				<label>주소</label>
					</div>
				</div>
				<div class="row mt-2">
					<div class="col form-floating">
						<input id="address2" type="text" class="form-control" value="${orderData.orderVo.of_address2 }"<c:if test="${orderData.orderVo.mst_os_no > 2 }"> disabled</c:if>>
		 				<label>상세주소</label>
					</div>
				</div>
				<!-- 요청사항 -->
				<div class="row mt-2">
					<div class="col form-floating">
						<textarea id="request" class="form-control" style="height: 100px"<c:if test="${orderData.orderVo.mst_os_no > 2 }"> disabled</c:if>>${orderData.orderVo.of_request }</textarea>
		 				<label>요청사항</label>
					</div>
				</div>
				
				<div class="row mt-3">
					<div class="col"></div>
					<div class="col-2">
						<c:if test="${orderData.orderVo.mst_os_no <= 2 }">
							<button class="form-control btn btn-primary" onclick="update()">배송지 수정</button>
						</c:if>
					</div>
					<c:if test="${orderData.orderVo.mst_os_no == 0 && fundingData.fundingDateStatus == 2}">
						<div class="col-2">
							<button type="button" class="form-control btn btn-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop">주문취소</button>
						</div>
					</c:if>
					<c:if test="${orderData.orderVo.mst_os_no == 3 }">
						<div class="col-2">
							<a href="../mypage/update_delivery_process.do?of_no=${orderData.orderVo.of_no }&mst_f_no=${orderData.orderVo.mst_f_no }" class="form-control btn btn-primary">수취 확인</a>
						</div>
					</c:if>
					<c:if test="${orderData.orderVo.mst_os_no == 4 }">
						<div class="col-2">
							<a href="../review_funding/create_page.do?of_no=${orderData.orderVo.of_no }" class="form-control btn btn-primary">후기 작성</a>
						</div>
					</c:if>
				</div>
		
			</div>
		</div>
	</div>
	
<!---------------------------------------------------------------------------------------------->
	<form id="form-update" action="./update_order_process.do" method="post">
		<input name="of_no" type="hidden" value="${orderData.orderVo.of_no }">
		<input id="inp_receiver" name="of_receiver" type="hidden">
		<input id="inp_zip" name="of_zip" type="hidden">
		<input id="inp_address1" name="of_address1" type="hidden">
		<input id="inp_address2" name="of_address2" type="hidden">
		<input id="inp_request" name="of_request" type="hidden">
	</form>
<!---------------------------------------------------------------------------------------------->

	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
<!-- 경고창 포맷 -->
<!-- Button trigger modal -->
	<div class="d-none">
		<button id="btn_modal" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
		  Launch demo modal
		</button>
	</div>

<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div id="modal_body" class="modal-body text-danger"></div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
<!-- 주문취소창 -->
<!-- Button trigger modal -->
	<button id="btn_cancel" type="button" class="btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
	  Launch static backdrop modal
	</button>
	
	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Cancel Order</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body text-danger">
	        주문을 취소하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <a href="../mypage/delete_order_process.do?of_no=${orderData.orderVo.of_no }" class="btn btn-danger">주문 취소</a>
	      </div>
	    </div>
	  </div>
	</div>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>	
</body>
</html>