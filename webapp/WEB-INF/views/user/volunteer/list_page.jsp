<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/list_page.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

<title>NANUGI: VOLUNTEER</title>
</head>
<body>
	<%-- 펀딩 리스트 페이지 --%>
	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/user/volunteer/commons/volunteer_tap.jsp" />

	<div class="container  mb-5 min-height">
		<jsp:include page="/WEB-INF/views/user/commons/global_banner.jsp" />
		<div class="row">
			<div class="col">
				<div class="row">
					<div class="col">
						<div class="text-center my-4">
							<button class="btn btn-outline-secondary" style="width: 130px;" onclick="searchCategory(0)">전체</button>
							<c:forEach items="${categoryList }" var="category">
								<button class="btn btn-outline-secondary" style="width: 130px;" onclick="searchCategory(${category.mst_vc_no })">${category.mst_vc_category }</button>
							</c:forEach>
						</div>
					</div>
				</div>
					<%-- 검색 및 버튼 --%>
					<div class="row mt-1">
						<div class="col">
							<div class="row">
								<div class="col-2">
									<select id="select_status" class="form-control" onchange="setStatus()">
										<option value="1"<c:if test="${searchStatus == 1}"> selected</c:if>>준비중</option>
										<option value="2"<c:if test="${searchStatus == 2}"> selected</c:if>>진행중</option>
										<option value="3"<c:if test="${searchStatus == 3}"> selected</c:if>>종료</option>
									</select>
								</div>
								<div class="col"></div>
								<div class="col-2"><!-- 펀딩 개설 -->
									<c:if test="${loginUser.memberVo.mst_mt_no == 3 }">
										<a href="./create_page.do" class="form-control btn btn-outline-primary">봉사활동 개설</a>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				<%-- 이벤트 리스트 카드 영역 --%>
				<div class="row mt-3">
					<div class="col">
						<div id="card_container" class="row row-cols-1 row-cols-md-4 g-4">
							<%-- 이벤트 카드 --%>
							<c:forEach items="${volunteerList }" var="volunteer">
							  <div class="col" onclick="location.href='../volunteer/read_page.do?mst_v_no=${volunteer.mstVolunteerVo.mst_v_no }'" style="cursor:pointer;">
							    <div class="card h-100">
						      	<img src="${volunteer.imgvo.mst_vi_img_link }" class="card-img-top" style="height: 13rem;">
							      <div class="card-body">
						        	<h5 class="card-title">
						        		${volunteer.mstVolunteerVo.mst_v_title }
						        	</h5>
							        <p class="card-text">
							        	<div>
								        	${volunteer.dateFrom } ~ ${volunteer.dateTo }
								        	<c:if test="${volunteer.volunteerStauts == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
								        	<c:if test="${volunteer.volunteerStauts == 2}"><span class="badge bg-success">진행중</span></c:if>
								        	<c:if test="${volunteer.volunteerStauts == 3}"><span class="badge bg-secondary">종료</span></c:if>
								        	
							        	</div>
							        	<div>
						        			${volunteer.memberVo.m_nick }
							        	</div>
							        </p>
							      </div>
							    </div>
							  </div>
							</c:forEach>
						</div>
					</div>
					<%-- 페이징 --%>
					<div class="row mt-3">
						<div class="col">
							<nav aria-label="Page navigation example">
							  <ul class="pagination justify-content-center">
							  	<li class="page-item<c:if test="${currentPage <= 1}"> disabled</c:if>">
							      <a class="page-link" href="./list_page.do?page_num=1" aria-disabled="true">&lt;&lt;</a>
							    </li>
							    <li class="page-item<c:if test="${beginPage <= 1 }"> disabled</c:if>"><a class="page-link" href="./list_page.do?page_num=${beginPage-1 }">&lt;</a></li>
									<c:forEach begin="${beginPage }" end="${endPage }" var="page">
							    	<li class="page-item<c:if test="${page == currentPage }"> active</c:if>"><a class="page-link" href="./list_page.do?page_num=${page }">${page }</a></li>
									</c:forEach>
							    <li class="page-item<c:if test="${endPage >= pageCount }"> disabled</c:if>"><a class="page-link" href="./list_page.do?page_num=${endPage+1 }">&gt;</a></li>
							    <li class="page-item<c:if test="${currentPage >= pageCount }"> disabled</c:if>">
							      <a class="page-link" href="./list_page.do?page_num=${pageCount }">&gt;&gt;</a>
							    </li>
							  </ul>
							</nav>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	
	<form id="frm_search" action="./list_page.do" method="post">
		<input id="inp_category" name="categoryNo" type="hidden" value="${categoryNo }">
		<input id="inp_status" name="search_status" type="hidden" value="${searchStatus }">
		<input id="inp_page_num" name="page_num" type="hidden" value="1">	
	</form>
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <%-- 풋터 --%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>