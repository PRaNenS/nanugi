<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/donate/read_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: DONATE</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
		
	<div class="container my-5 mb-5 min-height">
		<div class="row">
			<!-- 썸네일 -->
			<div class="col-8">
				<div class="row">
					<div class="col">
						<div class="card h-100">
							<img src="${donateData.thumnailImg.mst_di_img_link }" class="card-img-top">
						</div>
					</div>
				</div>
			</div>
			
			<div class="col">
				<div class="row">
					<div class="col">
						<span class="badge bgc">${donateData.mstDCategoryVo.mst_dc_category }</span>
						${donateData.date_from } ~ ${donateData.date_to }
						<span class="badge rounded-pill bg-danger">D-${donateData.dDay }</span>
					</div>
				</div>
			 	
			 	<!-- 기부명 -->
			 	<div class="row mt-3">
					<div class="col fw-bold fs-4">
						${donateData.mstDonateVo.mst_d_title}
					</div>
				</div>
				
				<!-- 달성률 -->
				<div class="fs-3 text-end">${donateData.percent }%</div>
				<div class="progress">
				  <div class="progress-bar bg-warning" role="progressbar" style="width: ${donateData.percent }%" aria-valuenow="${donateData.percent }" aria-valuemin="0" aria-valuemax="100"></div>
				</div>
				<div class="row">
					<div class="col">
						${donateData.mstDonateVo.mst_d_goal } P 목표
					</div>
					<div class="col text-end fs-4 text-success">
						${donateData.totalPoint } P
					</div>
				</div>
				<!-- 기관명 -->
				<div class="row py-3">
					<div class="col">
						<img src="${donateData.memberVo.m_profile_img_link }" style="width: 40px; height: 40px;">
						<span class="fw-bold">${donateData.memberVo.m_nick }</span>
					</div>
				</div>
				
				<!-- 기부 부분 -->
				<c:if test="${loginUser.memberVo.mst_mt_no == 2 && donateData.donateStatus == 2 }">
					<div class="row">
						<div class="col">
							<button type="button" class="btn form-control bgc text-white fw-bold" data-bs-toggle="modal" data-bs-target="#staticBackdrop">기부</button>
						</div>
					</div>
				</c:if>
			</div>								
		</div>
		
		<!-- 하부 탭 -->
		<nav>
		  <div class="nav nav-tabs mt-4" id="nav-tab" role="tablist">
		    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">소개글</button>
		  </div>
		</nav>
		
		<!-- 펀딩 하위 내용 -->
		<div class="row">
			<div class="col-8">
				<div class="tab-content" id="nav-tabContent">
				  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				  	${donateData.mstDonateVo.mst_d_content }
				  </div>
				</div>
			</div>
			<div class="col">
				<!-- 제작완료 예정일  -->
				<div class="row mt-3">
					<div class="col">
						<div class="row">
							<div class="col text-secondary">모금 종료일 | ${donateData.date_to }</div>
						</div>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						<h5 class="fw-bold">기부 참여자 안내</h5>
					</div>
				</div>
				<c:forEach items="${donatorList }" var="donator">
					<div class="row mb-2">
						<div class="col">
							<div class="border px-2 py-3">
								<div class="fs-5 mb-2">
									<img src="${donator.donatorData.m_profile_img_link }" class="rounded" style="width: 30px; height: 30px;"> ${donator.donatorData.m_nick }
								</div>
								<div>
									${donator.donateVo.dd_donate_point } P
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>		
	</div>
				
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
	<form id="frm_submit" action="../donate/donate_process.do" method="post">
		<input name="mst_d_no" type="hidden" value="${donateData.mstDonateVo.mst_d_no }">
		<input id="input_donate_point" name="dd_donate_point" type="hidden">
	</form>
	
	<input id="holding_point" type="hidden" value="${mPointVo.mp_point }">
	
<!-- 기부 확인창 -->
<button id="btn_confirm_modal" type="button" class="btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#exampleModal">기부</button>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Donate</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
         기부하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary" onclick="donateConfirm()">확인</button>
      </div>
    </div>
  </div>
</div>

<!-- 기부창 -->
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Donate Complete</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="fs-5 mb-2">
					보유 포인트: ${mPointVo.mp_point } P
				</div>
				<div class="input-group">
					<input id="txt_donate_point" class="form-control text-end" type="text" value="0">
					<span class="input-group-text">P</span>
				</div>
				<div id="alert_box" class="text-danger"></div>
      </div>
      <div class="modal-footer">
        <button id="btn_close_modal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary" onclick="donate()">기부</button>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>