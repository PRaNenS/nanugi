<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/funding/read_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: FUNDING</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
	<jsp:include page="/WEB-INF/views/user/funding/commons/funding_tap.jsp" /> <!-- 펀딩 탭 -->
		
	<div class="container my-5 mb-5 min-height">
		<div class="row">
			<!-- 썸네일 -->
			<div class="col-8">
				<div class="row">
					<div class="col">
						<div class="card h-100">
							<img src="${fundingData.thumnailImg.mst_fi_img_link }" class="card-img-top">
						</div>
					</div>
				</div>
			</div>
			
			<div class="col">
				<div class="row">
					<div class="col">
						<span class="badge bgc">${fundingData.mstFCategoryVo.mst_fc_category }</span>
						${fundingData.date_from } ~ ${fundingData.date_to }
						<span class="badge bg-danger">D-${fundingData.dDay }</span>
					</div>
				</div>
			 	
			 	<!-- 펀딩명 -->
			 	<div class="row mt-3">
					<div class="col fw-bold fs-4">
						${fundingData.mstFundingVo.mst_f_title}
					</div>
				</div>
				
				<!-- 달성률 -->
				<div class="fs-3 text-end">${fundingData.percent }%</div>
				<div class="progress">
				  <div class="progress-bar bg-warning" role="progressbar" style="width: ${fundingData.percent }%" aria-valuenow="${fundingData.percent }" aria-valuemin="0" aria-valuemax="100"></div>
				</div>
				<div class="row">
					<div class="col">
						${fundingData.mstFundingVo.mst_f_goal } P 목표
					</div>
					<div class="col text-end fs-4 text-success">
						${fundingData.totalPrice } P
					</div>
				</div>
				<!-- 기관명 -->
				<div class="row py-3">
					<div class="col">
						<img src="${fundingData.memberVo.m_profile_img_link }" style="width: 40px; height: 40px;">
						<span class="fw-bold">${fundingData.memberVo.m_nick }</span>
					</div>
				</div>
				
				<!-- 리워드 추가 및 삭제 버튼-->
				<c:if test="${loginUser.memberVo.m_no == fundingData.memberVo.m_no && fundingData.fundingDateStatus == 1 }">
					<button type="button" class="btn btn-danger form-control" data-bs-toggle="modal" data-bs-target="#staticBackdrop">펀딩 삭제</button>
				</c:if>
				<c:if test="${loginUser.memberVo.mst_mt_no == 2 && fundingData.fundingDateStatus == 2}">
					<select id="select_reward" class="form-control form-select my-3" onchange="addOption()">
						<option selected value="0">== 리워드 선택 ==</option>
						<c:forEach items="${mstFOptionVoList }" var="option">
							<option value="${option.mst_fo_no }">${option.mst_fo_name }</option>
						</c:forEach>
					</select>
					<div class="row">
						<div class="col">
							<div id="option_container" class="border-top"></div>
						</div>
					</div>
					<div class="row">
						<div class="col text-end fs-3 fw-bold">
							총 금액 <span id="total_point">0</span> P
						</div>
					</div>
					<button class="btn text-white bgc fw-bold form-control my-3" onclick="joinFunding()">펀딩 참가</button>
				</c:if>
			</div>								
		</div>
		
		<!-- 하부 탭 -->
		<nav>
		  <div class="nav nav-tabs mt-5" id="nav-tab" role="tablist">
		    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">소개글</button>
		    <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false">후기</button>
		  </div>
		</nav>
		
		<!-- 펀딩 하위 내용 -->
		<div class="row mt-5">
			<div class="col-8">
				<div class="tab-content" id="nav-tabContent">
				  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				  		<p>${fundingData.mstFundingVo.mst_f_content }</p>
				  </div>
				  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
				  		<div class="row">
							<div class="col md-8">
								<h2 class="pb-3 mb-3 border-bottom" style="font-style: bold;">
									여러분들의 따뜻한 목소리가 힘이 됩니다.
								</h2>
								<c:forEach items="${frfList }" var="datas">
								<div onclick="location.href='../review_funding/read_page.do?rf_no=${datas.reviewFundingVo.rf_no }'" style="cursor:pointer;"> 
									<div class="row">
										<div class="col-9 mt-2">
											<h5 class="vol-post-title">${datas.reviewFundingVo.rf_title}</h5>
										</div>
										<div class="col mt-2">
											by ${datas.memberVo.m_nick } | ${datas.date }
										</div>
										<p class="vol-post-meta">${datas.reviewFundingVo.rf_content}</p>
										<div class="col mt border-bottom"></div>
										
									</div>
									
								</div>
								</c:forEach>
							</div>
						</div>
				  </div>
				</div>
			</div>
			<div class="col">
				<!-- 제작완료 예정일  -->
				<div class="row mt-3">
					<div class="col">
						<div class="row">
							<div class="col text-secondary">제작완료 예정일 | ${fundingData.end_date }</div>
						</div>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						<h5 class="fw-bold">리워드 안내</h5>
					</div>
				</div>
				<c:forEach items="${mstFOptionVoList }" var="option">
					<div class="row mb-2">
						<div class="col">
							<div class="border px-2 py-3">
								<div class="fs-5 mb-2">
									${option.mst_fo_name }
								</div>
								<div>
									${option.mst_fo_price } P
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>		
	</div>
				
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
	<form id="form-submit" action="../funding/order_page.do" method="post">
		<input name="mst_f_no" type="hidden" value="${fundingData.mstFundingVo.mst_f_no }">
	</form>

	<!--옵션포맷-------------------------------------------------------------------------------------->
	<div id="option_format" class="d-none">		
		<div class="py-3">
			<span class="option-name"></span>
			<button type="button" class="btn-close float-end btn-delete" aria-label="Close" onclick="removeItem(this)"></button>
			<div class="row mt-2">
				<div class="col-4">
					<div class="input-group">
						<button class="btn btn-outline-secondary" type="button" id="button-addon1" onclick="subQtt(this)">-</button>
						<input name="option_quantity" class="form-control option-quantity text-end" type="text" value="0" onkeyup="setSum(this)">
						<button class="btn btn-outline-secondary" type="button" id="button-addon1" onclick="addQtt(this)">+</button>
					</div>					
				</div>
				<div class="col text-end">
					<span class="option-price">0</span>
					<span> P</span>
				</div>
			</div>
			<input name="option_no" type="hidden" class="option-no">
		</div>
	</div>
	
	<c:forEach items="${mstFOptionVoList }" var="option">
		<input id="${option.mst_fo_no }" type="hidden" value="${option.mst_fo_price }">
	</c:forEach>
	
<!--경고창 포맷(펀딩참여)-->
	<div class="d-none">
		<!-- Button trigger modal -->
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
	      <div class="modal-body text-danger">
	        	수량을 정확히 입력해 주세요
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>

<!--경고창 포맷(펀딩삭제)-->
	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Delete</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body text-danger">
	        펀딩을 삭제하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <a href="../funding/delete_process.do?mst_f_no=${fundingData.mstFundingVo.mst_f_no }" class="btn btn-danger">삭제</a>
	      </div>
	    </div>
	  </div>
	</div>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>