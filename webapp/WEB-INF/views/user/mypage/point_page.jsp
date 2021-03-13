<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mypage/point_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: POINT</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
	
	<div class="container mb-5 pt-5 min-height">
		<div class="row">
			<jsp:include page="/WEB-INF/views/user/mypage/commons/mypage_sidebar.jsp" /> <!-- 사이드바 -->
			<div class="col ms-3 me-3">
				<!-- 포인트 충전 -->
				<div class="row">
					<div class="col">
						<div class="border px-4 py-4">
							<div class="row">
								<div class="col-3">
									<p class="fs-3">보유 포인트</p>
								</div>
								<div class="col-4">
									<p class="fs-3 fs-bold text-end">${mPointVo.mp_point } P</p>
								</div>
								<hr>
								<c:if test="${loginUser.memberVo.mst_mt_no == 2 }">
									<div class="row mt-3">
										<div class="col">
											<div class="btn-group">
												<button onclick="charge(5000)" class="btn btn-outline-success">+ 5,000 P</button>
												<button onclick="charge(10000)" class="btn btn-outline-success">+ 10,000 P</button>
												<button onclick="charge(20000)" class="btn btn-outline-success">+ 20,000 P</button>
												<button onclick="charge(30000)" class="btn btn-outline-success">+ 30,000 P</button>
												<button onclick="charge(40000)" class="btn btn-outline-success">+ 40,000 P</button>
												<button onclick="charge(50000)" class="btn btn-outline-success">+ 50,000 P</button>
											</div>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6">
											<div class="input-group mb-3">
											  <input id="txt_point" type="text" class="form-control text-end" value="0">
											  <button class="btn btn-primary" onclick="chargePoint()">충전</button>
											</div>
										</div>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<!-- 포인트 내역 -->
				<div class="row pt-4">
					<div class="col">
						<p class="fs-5">포인트 내역</p>
					</div>
				</div>
				<!-- 검색 -->
				<div class="row mb-2">
					<div class="col">
						<div class="border px-3 py-3">
							<div class="row justify-content-md-center">
								<div class="row">
									<div class="col-2"></div>
									<div class="col">
										<div class="row">
											<div class="col">
												<div class="input-group">
													<input id="date_from" class="form-control" type="date">
													<span class="input-group-text">~</span>
													<input id="date_to" class="form-control" type="date">
												</div>
											</div>
											<div class="col-2">
												<button class="btn btn-primary form-control" onclick="search()">조회</button>
											</div>
										</div>
									</div>
									<div class="col-2"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 내역 표시 -->
				<div class="row">
					<div class="col">
						<!-- 기록 -->
						<div class="border-top"></div>
						<c:forEach items="${recordList }" var="record">
							<div class="border-bottom">
								<div class="row px-2 py-2">
									<div class="col-1 text-center">
										<div class="position-relative" style="width: 80px; height: 60px;">
											<div class="position-absolute top-50 start-50 translate-middle">
												<c:if test="${record.type == 1}">
													<span class="badge rounded-pill bg-success px-2 py-1 fs-5">충전</span>
												</c:if>
												<c:if test="${record.type == 2}">
													<span class="badge rounded-pill bg-primary px-2 py-1 fs-5">기부</span>
												</c:if>
												<c:if test="${record.type == 3}">
													<span class="badge rounded-pill bg-info px-2 py-1 fs-5">펀딩</span>
												</c:if>
											</div>
										</div>
									</div>
									<div class="col">
										<div class="row">
											<div class="col">${record.recordDateTime }</div>
										</div>
										<div class="row">
											<div class="col-8 fw-bold">${record.mRecordPVo.m_rp_record }</div>
											<div class="col fw-bold text-end me-5">${record.mRecordPVo.m_rp_point } P</div>
										</div>
										<div class="row">
											<div class="col">${record.mRecordPVo.m_rp_object }</div>
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
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" />
	
	<form id="frm_charge" action="../mypage/charge_process.do" method="post"></form>
	<form id="frm_search" action="../mypage/point_page.do" method="post">
		<input id="input_date_from" name="search_date_from" type="hidden">
		<input id="input_date_to" name="search_date_to" type="hidden">
	</form>
	
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
	      <div id="modal_body" class="modal-body text-danger">
	      	조회날짜를 정확히 입력해주세요
	      </div>
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