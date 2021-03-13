<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">
<title>NANUGI: COMMUNITY</title>
<style>
	body{
		padding-top: 80px;
	
	}
	

		display: block; 
		margin: 0px auto;
	
	}
</style>


</head>
<body>
	<jsp:include page="/WEB-INF/views/user/board/commons/freeboard_banner.jsp" />
	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" />
	<div class="container">
	<!-- 게시판 테이블 -->
	<div class="row">
		<div class="col-1"></div>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" class="text-center">번호</th>
						<th scope="col" class="text-center">말머리</th>
						<th scope="col" class="text-center">제목</th>
						<th scope="col" class="text-center">작성자</th>
						<th scope="col" class="text-center">조회</th>
						<th scope="col" class="text-center">추천수</th>
						<th scope="col" class="text-center">작성일</th>
					</tr>
				</thead>
				<tbody>
				<!-- 리스트 출력 -->
				<c:forEach items="${resultList }" var="data">
					<tr>
						<td style="width: 10%" class="text-center">${data.freeBoardVo.bf_no }</td>
						<td style="width: 10%" class="text-center">
							<c:if test="${data.freeBoardVo.mst_bfh_no == 1}"><span class="badge badge-primary">${data.freeBoardHeadlineVo.mst_bfh_headline }</span></c:if>
							<c:if test="${data.freeBoardVo.mst_bfh_no == 2}"><span class="badge badge-warning">${data.freeBoardHeadlineVo.mst_bfh_headline }</span></c:if>
							<c:if test="${data.freeBoardVo.mst_bfh_no == 3}"><span class="badge badge-success">${data.freeBoardHeadlineVo.mst_bfh_headline }</span></c:if>
						</td>
						<td style="width: 40%">
							<div onclick="location.href='../board/freeboard_read_page.do?bf_no=${data.freeBoardVo.bf_no }'" style="cursor: pointer;">${data.freeBoardVo.bf_title } [${data.commentCnt}]
								<c:choose>
									<c:when test="${!empty data.imgList }">
										<img width="15" height="15" src="${pageContext.request.contextPath}/resources/img/preview.png">
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</div>
				 		</td>
						<td style="width: 10%" class="text-center">${data.memberVo.m_nick }</td>
						<td style="width: 10%" class="text-center">${data.freeBoardVo.bf_viewcount }</td>
						<td style="width: 10%" class="text-center">${data.countLikeVo }</td>
						<td style="width: 10%" class="text-center">
							<fmt:formatDate pattern="yyyy.MM.dd." value="${data.freeBoardVo.bf_write_date }"/>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 게시판 테이블 끝 -->
	<!-- 검색 start -->
	<form action="${pageContext.request.contextPath }/board/freeboard_page.do" method="get">
		<div class="row mt-2 mb-3">
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
				<input type="submit" value="검색" class="btn btn-outline-dark">
			</div>
		</div>
	</form>
	<!-- 검색 end -->
	<!-- 페이징 & 글쓰기 버튼 -->
	<div class="row mt-4 mb-3">
		<div class="col-2"></div>
		<div class="col-8">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item<c:if test="${currentPage == 1}"> disabled></c:if>">
						<a class="page-link" href="${pageContext.request.contextPath }/board/freeboard_page.do?page_no=1" tabindex="-1" aria-disabled="true">&lt;&lt;</a></li>
							<c:forEach begin="${beginPage }" end="${endPage }" var="i">
								<li class="page-item<c:if test="${i == currentPage }"> active</c:if>"><a class="page-link" href="${pageContext.request.contextPath }/board/freeboard_page.do?page_no=${i }">${i }</a></li>
							</c:forEach>
					<li class="page-item<c:if test="${currentPage == pageCount }"> disabled</c:if>"><a class="page-link" href="${pageContext.request.contextPath }/board/freeboard_page.do?page_no=${pageCount }">&gt;&gt;</a></li>
				</ul>
			</nav>
		</div>
		<!-- 글쓰기 버튼 -->
		<div align="right" class="col-2 d-grid gap-2">
			<c:if test="${!empty loginUser }">
				<a href="${pageContext.request.contextPath}/board/freeboard_write_page.do">
					<input type="button" class="btn btn-dark form-control" value="글쓰기"></a>
			</c:if>
		</div>
	</div>	
	</div>
	<div class="col"></div>
		
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>