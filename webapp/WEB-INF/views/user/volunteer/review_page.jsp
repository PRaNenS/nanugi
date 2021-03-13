<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/review_write.js"></script>

<title>NANUGI: VOLUNTEER REVIEW</title>



</head>
<body>
	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" />
	<jsp:include page="/WEB-INF/views/user/volunteer/commons/volunteer_tap.jsp" />
	<jsp:include page="/WEB-INF/views/user/volunteer/commons/reviewV_banner.jsp" />
	<article>
		<div class="container">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" class="text-center">번호</th>
							<th scope="col" class="text-center">제목</th>
							<th scope="col" class="text-center">작성자</th>
							<th scope="col" class="text-center">추천수</th>
							<th scope="col" class="text-center">작성일</th>
						</tr>
					</thead>
					<!-- 리스트 출력 -->
					<tbody>
						<c:forEach items="${reviewList }" var="data">
							<tr>
								<td style="width: 5%" class="text-center">${data.reviewVolunteerVo.rv_no }</td>
								<td style="width: 25%">
									<div onclick="location.href='../volunteer/review_read_page.do?rv_no=${data.reviewVolunteerVo.rv_no }'" style="cursor: pointer;">
									${data.reviewVolunteerVo.rv_title}[${data.commentCount }]
									</div>	
								</td>
								<td style="width: 5%" class="text-center">${data.memberVo.m_nick }</td>
								<td style="width: 5%" class="text-center">${data.recommendCount }</td>
								<td style="width: 5%" class="text-center">
									<fmt:formatDate pattern="yyyy.MM.dd." value="${data.reviewVolunteerVo.rv_write_date }"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>					
				</table>
				
				
				<!-- 검색 start-->
				<form action="${pageContext.request.contextPath }/volunteer/review_page.do" method="get">
				<div class="row mt-5 mb-3">
						<div class="col-2">
							<select name="search_type" class="form-control">
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="writer">작성자</option>
							</select>
						</div>
					<div class="col-8">
						<input name="search_word" type="text" class="form-control">
					</div>
						<div class="col-2 d-grid gap-2">
							<input type="submit" value="검색" class="btn btn-dark">
						</div>
					</div>
				</form>
				<!-- 검색 end -->
				<!-- 페이징 -->
				<div class="row mt-4 mb-4">
					<div class="text-center">
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center">
								<li class="page-item <c:if test="${currentPage == 1}"> disabled</c:if>">
									<a class="page-link" href="${pageContext.request.contextPath }/volunteer/review_page.do?page_no=1" tabindex="-1" aria-disabled="true">&lt;&lt;</a>
								</li>
								<c:forEach begin="${beginPage }" end="${endPage }" var="i">
									<li class="page-item<c:if test="${i == currentPage }"> active</c:if>"><a class="page-link" href="${pageContext.request.contextPath }/volunteer/review_page.do?page_no=${i }">${i }</a></li>
								</c:forEach>
								<li class="page-item<c:if test="${currentPage == pageCount }"> disabled</c:if>">
									<a class="page-link" href="${pageContext.request.contextPath }/volunteer/review_page.do?page_no=${pageCount }">&gt;&gt;</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
				<!-- 페이징 end -->
			</div>
		
	</article>
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
	
	
	
	
	
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</body>
</html>