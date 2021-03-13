<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/read_page.js"></script>

<title>NANUGI: VOLUNTEER</title>
</head>
<body onload="refresh()">

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/user/volunteer/commons/volunteer_tap.jsp" />
	
	<div class="container my-5 mb-5 min-height">
		<div class="row"><!-- 이미지 -->
			<div class="col-8">		
				<div class="row">
					<div class="col card" style="width: auto">
						<div class="card" >
							<img src="${volunteerData.imgvo.mst_vi_img_link}" class="card-img-top">
						</div>
					</div>
				</div>
			</div>
			<div class="col"><!-- 옵션  -->
				<div class="row">
					<div class="col">
						<span class="badge bgc">${volunteerData.vcategoryvo.mst_vc_category}</span>
						${volunteerData.date_from } ~ ${volunteerData.date_to}
						<span class="badge rounded-pill bg-danger">D-${volunteerData.dDay }</span>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col fw-bold">
						<h1>${volunteerData.mstVolunteerVo.mst_v_title}</h1>
					</div>
				</div>
				<!-- 그래프 -->
				<div class="fs-4"><fmt:formatNumber value="${volunteerData.progress }" pattern="0"/>%</div>
				<div class="progress">
					<div class="progress-bar bg-warning" role="progressbar" style="width: ${volunteerData.progress }%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
				</div>
					목표 인원 ${volunteerData.mstVolunteerVo.mst_v_goal }명				
				
				<!-- 기관명 -->
				<div class="row py-3"> 
					<div class="col">
						<img src="${volunteerData.memberVo.m_profile_img_link}" style="width: 40px; height: 40px;">
						<span class="fw-bold">${volunteerData.memberVo.m_nick }</span>
					</div>
				</div>
				<c:if test="${loginUser.memberVo.mst_mt_no == 2}">
					<div class="row mt-3">
					<div id="alert_option"></div>
					<div class="col-6 my-3">참여 날짜</div>
					<div class="col-4 my-3">오전  / 오후</div>
					<div class="col-2 my-3"><input name="add_button" type="button" value="+" onclick="addOption()" class="btn btn-outline-secondary"></div>
				</div>
				
				<div class="row"><!-- 옵션 selection -->
					<div class="col">
						<div id="option_container"  class="border-top">
													
						</div>
					</div>
				</div>
				</c:if>
				<div class="row mt-3 mb-4">
					<div class="col">
						<c:if test="${loginUser.memberVo.mst_mt_no == 2 && volunteerData.volunteerStauts == 2  }">
							<button class="btn text-white bgc fw-bold form-control" onclick="joinVolunteer()"> 봉사 참여</button>
						</c:if>
						<c:if test="${loginUser.memberVo.mst_mt_no == 2 && volunteerData.volunteerStauts == 1  }">
							<button class="btn btn-primary form-control" disabled>모집 준비중</button>
						</c:if>
						<c:if test="${loginUser.memberVo.mst_mt_no == 2 && volunteerData.volunteerStauts == 3  }">
							<button class="btn btn-primary form-control" disabled>모집 종료</button>
						</c:if>
						<c:if test="${loginUser.memberVo.mst_mt_no == 3 && volunteerData.volunteerStauts == 1 }">
							<button class="btn btn-outline-danger form-control" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
						</c:if>
						<c:if test="${loginUser.memberVo.mst_mt_no == 3 && volunteerData.volunteerStauts == 2 }">
							<a href="#"><button class="btn btn-outline-primary form-control " disabled>모집중</button></a>
						</c:if>
						<c:if test="${loginUser.memberVo.mst_mt_no == 3 && volunteerData.volunteerStauts == 3 }">
							<a href="#"><button class="btn btn-outline-secondary form-control" disabled>모집종료</button></a>
						</c:if>
					</div>
				</div>
				<div class="row mt-5">
					<div class="col">
						<div class="modal fade" id="deleteModal" aria-labelledby="deleteModalLabel" aria-hidden="true" tabindex="-1">
							<div class="modal-dialog">
						    	<div class="modal-content">
						      		<div class="modal-header">
						        		<h3 class="modal-title">봉사 삭제 확인</h3>
						        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						     		</div>
							      	<div class="modal-body">
										해당 봉사를 삭제 하시겠습니까?								      	
							     	</div>
							      	<div class="modal-footer">
							        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
							        	<button type="button" class="btn btn-danger"  onclick="location.href='./delete_process.do?mst_v_no=${volunteerData.mstVolunteerVo.mst_v_no}'">삭제</button>
							       </div>
						    	</div>
						  	</div>
						</div>
					</div>
				</div>
			</div><!-- 옵션  -->
		</div>
		<!-- 하부 탭 -->
		<nav>
		  <div class="nav nav-tabs mt-5" id="nav-tab" role="tablist">
		    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">소개글</button>
		    <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false">후기</button>
		  </div>
		</nav>
		<!-- 탭 내용 -->
		<div class="row mt-5">
			<div class="col-8">
				<div class="tab-content" id="nav-tabContent">
					<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				  		<p>${volunteerData.mstVolunteerVo.mst_v_content}</p>
					</div>
					 <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
						<div class="row">
							<div class="col md-8">
								<h2 class="pb-3 mb-3 border-bottom" style="font-style: bold;">
									여러분들의 따뜻한 목소리가 힘이 됩니다.
								</h2>
								<c:forEach items="${reviewVolList }" var="datas">
								<div onclick="location.href='../volunteer/review_read_page.do?rv_no=${datas.reviewVolunteerVo.rv_no }'" style="cursor:pointer;"> 
									<div class="row">
										<div class="col-8 mt-2">
											<h5 class="vol-post-title">${datas.reviewVolunteerVo.rv_title}</h5>
										</div>
										<div class="col-4 mt-2">
											by ${datas.memberVo.m_nick } | <fmt:formatDate pattern="yyyy-MM-dd" value="${datas.reviewVolunteerVo.rv_write_date }"/>
										</div>
										<p class="vol-post-meta">${datas.reviewVolunteerVo.rv_content}</p>
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
				<div class="row mt-3">
					<div class="col">
						<h5 class="fw-bold">봉사기간 안내</h5>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-3 text-center">봉사 날짜</div>
					<div class="col-5 text-center">시간 (오전 / 오후)</div>
				</div>
				<c:forEach items="${DateList }" var="date">
					<div class="row mt-2">
						<div class="col-3">
							<div class="row">
								<div class="col text-center">
									${date.mst_vd_date }
								</div>
							</div>
						</div>
						<div class="col-5">
							<div class="row">
								<div class="col-6 text-center">
									${date.mst_vd_time}
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<form id="submit" action="./join_page.do" method="post">
		<input name="mst_v_no" type="hidden" value="${volunteerData.mstVolunteerVo.mst_v_no}">
	</form>
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <%-- 풋터 --%>
	
<!--옵션포맷-------------------------------------------------------------------------------------->
	<div id="option_format" class="row mt-2 d-none target">		
		<div class="col-6">
			<select class="form-control date-name text-center" onchange="setTime(this)">
				<option selected>=====  날짜  =====</option>
				<c:forEach items="${DateList }" var="date">
					<option value="${date.mst_vd_no}">${date.mst_vd_date }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-4">
			<input type="text" class="form-control time" disabled>
		</div>
		<div class="col-2 mt-2">
			<button type="button" class="btn-close btn-delete" aria-label="Close" onclick="removeItem(this)"></button>
		</div>
	</div>
	
	<c:forEach items="${DateList }" var="date">
		<input id="${date.mst_vd_no }" type="hidden" value="${date.mst_vd_time }">
	</c:forEach>
<!---------------------------------------------------------------------------------------------->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>