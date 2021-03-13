<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/join_page.js"></script>

<title>NANUGI: VOLUNTEER</title>
</head>	
<body onload="setTab(4)">

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/user/volunteer/commons/volunteer_tap.jsp" />
	
	
	<div class="container mt-5 mb-5 min-height">
		<!-- 주문상품 -->
		<div class="row border-bottom">
			<div class="col">
				<div class="row">
					
				</div>
				<div class="row">
					<!-- 이미지 -->
					<div class="col-2 card h-100">
						<img class="card-img-top" src="${volunteerData.imgvo.mst_vi_img_link }">
					</div>
					<!-- 제품정보 -->
					<div class="col">
						<div class="row">
							<div class="col fw-bolder fs-3 border-bottom">${volunteerData.mstVolunteerVo.mst_v_title }</div>
						</div>
						<div class="row">
							<div class="col">${volunteerData.memberVo.m_nick }</div>
						</div>
					</div>
				</div>
				<!-- 옵션정보 -->
				<div class="row">
					<div class="col-4 fw-bolder">봉사활동 날짜</div>
					<div class="col-2 fw-bolder">시간</div>
				</div>
				<c:forEach items="${dateList }" var="date">
					<div class="row">
						<div class="col-4">${date.mstVDateVo.mst_vd_date }</div>
						<div class="col-2">${date.mstVDateVo.mst_vd_time }</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<!-- 배송지 -->
		<div class="row mt-3">
			<div class="col">
				<h5>인적사항</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-3 form-floating mb-1">
				<input id="userName" type="text" class="form-control" value="${loginUser.memberVo.m_name }" disabled>
 				<label class="ml-1">참가자명</label>
			</div>
			<div class="col"></div>
		</div>
			<div class="row">
			<div class="col-3 form-floating">
				<input id="birth" type="text" class="form-control" value="${birth }" disabled>
 				<label class="ml-1">생년월일</label>
			</div>
			<div class="col-3 form-floating">
				<input id="gender" type="text" class="form-control" value="${loginUser.memberVo.m_gender }" disabled>
 				<label class="ml-1">성별</label>
			</div>
		</div>
		<!-- 주소 -->
		<div class="row mt-2">
			<div class="col-2 form-floating">
				<input id="zip" type="text" class="form-control" value="${loginUser.memberVo.m_zip }" disabled>
 				<label class="ml-1">우편번호</label>
 			</div>
			<div class="col form-floating">
				<input id="address1" type="text" class="form-control" value="${loginUser.memberVo.m_address1 }" disabled>
 				<label class="ml-1">주소</label>
			</div>
		</div>
		<div class="row mt-2">
			<div class="col form-floating">
				<input id="address2" type="text" class="form-control" value="${loginUser.memberVo.m_address2 }" disabled>
 				<label class="ml-1">상세주소</label>
			</div>
		</div>		
		<div class="row mt-3">
			<div class="col"></div>
			<div class="col-2">
				<a><button type="button" class="form-control btn text-white bgc fw-bold" data-bs-toggle="modal" data-bs-target="#joinModal" >참가</button></a>
			</div>
		</div>
		<div class="modal fade" id="joinModal" aria-labelledby="joinModalLabel" aria-hidden="true" tabindex="-1">
			<div class="modal-dialog">
		    	<div class="modal-content">
		      		<div class="modal-header">
		        		<h3 class="modal-title">봉사 신청내역 확인</h3>
		        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		     		</div>
			      	<div class="modal-body">
				      	<div class="row mt-3">
			        		<div class="col"><h5 class="fw-bolder">봉사활동 주최 기관</h5></div>
			        		<div class="col"><h5 class="fw-bolder">봉사활동명</h5></div>
			        	</div>
				      	<div class="row mt-4">
							<!-- 제품정보 -->
							<div class="col">${volunteerData.memberVo.m_nick }</div>
							<div class="col">${volunteerData.mstVolunteerVo.mst_v_title }</div>
						</div>
			        	<div class="row mt-3">
			        		<div class="col"><h5 class="fw-bolder">신청 날짜</h5></div>
			        		<div class="col"><h5 class="fw-bolder">봉사 활동 장소</h5></div>
			        	</div>
			        	<div class="row mt-4">
			        		<div class="col">
			        			<c:forEach items="${dateList }" var="date" >
										<div class="col">${date.mstVDateVo.mst_vd_date } ${date.mstVDateVo.mst_vd_time }</div>
								</c:forEach>
			        		</div>
			        		<div class="col">
			        			${volunteerData.mstVolunteerVo.mst_v_place }
			        		</div>
			        	</div>
			     	</div>
			      	<div class="modal-footer">
			        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
			        	<button type="button" class="btn text-white bgc fw-bold"  onclick="join()">참가</button>
			       </div>
		    	</div>
		  	</div>
		</div>
	</div>
<!---------------------------------------------------------------------------------------------->
	<form id="form-join" action="./join_process.do" method="post">
		<input name="mst_v_no" type="hidden" value="${volunteerData.mstVolunteerVo.mst_v_no }">
		<c:forEach items="${dateList }" var="date">
			<input name="mst_vd_no" type="hidden" value="${date.mstVDateVo.mst_vd_no}">
		</c:forEach>
	</form>
<!---------------------------------------------------------------------------------------------->
		
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <%-- 풋터 --%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>