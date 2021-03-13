<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/funding/order_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: ORDER</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
	<jsp:include page="/WEB-INF/views/user/funding/commons/funding_tap.jsp" /> <!-- 펀딩 탭 -->
	
	<div class="container mt-5 mb-5 min-height">
		<!-- 주문상품 -->
		<div class="row border-bottom">
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
							<div class="col fw-bolder border-bottom">${fundingData.mstFundingVo.mst_f_title }</div>
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
				<div class="row mt-2 border-top">
					<div class="col fw-bolder">옵션명</div>
					<div class="col-2 fw-bolder">단가</div>
					<div class="col-1 fw-bolder">수량</div>
					<div class="col-2 fw-bolder">총금액</div>
				</div>
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
		<div class="row">
			<div class="col"></div>
			<div class="col fw-bolder fs-5 text-end">총 주문금액 ${totalPrice } P</div>
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
				<input id="receiver" type="text" class="form-control" value="${loginUser.memberVo.m_name }">
 				<label>수취인</label>
			</div>
			<div class="col"></div>
		</div>
		<!-- 주소 -->
		<div class="row mt-2">
			<div class="col-2 form-floating">
				<input id="zip" type="text" class="form-control" value="${loginUser.memberVo.m_zip }">
 				<label>우편번호</label>
 			</div>
			<div class="col form-floating">
				<input id="address1" type="text" class="form-control" value="${loginUser.memberVo.m_address1 }">
 				<label>주소</label>
			</div>
		</div>
		<div class="row mt-2">
			<div class="col form-floating">
				<input id="address2" type="text" class="form-control" value="${loginUser.memberVo.m_address2 }">
 				<label>상세주소</label>
			</div>
		</div>
		<!-- 요청사항 -->
		<div class="row mt-2">
			<div class="col form-floating">
				<textarea id="request" class="form-control" style="height: 100px"></textarea>
 				<label>요청사항</label>
			</div>
		</div>
		
		<!-- 결제정보 -->
		<div class="row mt-3">
			<div class="col">
				<h5>결제정보</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-2">소지포인트</div>
			<div class="col <c:if test="${mPointVo.mp_point < totalPrice }">text-danger</c:if>">${mPointVo.mp_point } P</div>
		</div>
		<div class="row border-bottom">
			<div class="col-2">거래포인트</div>
			<div class="col">${totalPrice } P</div>
		</div>
		<div class="row">
			<div class="col-2 fw-bold">거래후 잔여포인트</div>
			<div class="col fw-bold">${resultPoint } P</div>
		</div>
		
		<div class="row"><div id="message-box" class="col text-danger"></div></div> <!-- 경고메세지 -->
		
		<div class="row">
			<div class="col"></div>
			<div class="col-2">
				<button class="form-control btn bgc text-white fw-bold" onclick="order()">참가</button>
			</div>
		</div>

	</div>
<!---------------------------------------------------------------------------------------------->
	<form id="form-order" action="./order_process.do" method="post">
		<input id="user-point" type="hidden" value="${mPointVo.mp_point }">
		<input id="total-price" name="total_point" type="hidden" value="${totalPrice }">
		<input name="mst_f_no" type="hidden" value="${fundingData.mstFundingVo.mst_f_no }">
		<c:forEach items="${orderDetailData }" var="option">
			<input name="mst_fo_no" type="hidden" value="${option.mstFOptionVo.mst_fo_no }">
			<input name="od_quantity" type="hidden" value="${option.oDetailVo.od_quantity }">
		</c:forEach>
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

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>