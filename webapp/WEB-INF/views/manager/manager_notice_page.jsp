<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet"> 
<title>MANAGER: NOTICE</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/manager/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/manager/commons/board_tap.jsp" />
	
	<div class="container min-height mb-5">
		<div class="row mt-3"> <!-- 내용  -->
			<div class="col">
				<div class="row mt-3">
					<div class="col">
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col" class="text-center">번호</th>
                                    <th scope="col" class="text-center">제목</th>
                                    <th scope="col" class="text-center">작성자</th>
                                    <th scope="col" class="text-center">작성일자</th>
									<th scope="col" class="text-center">비고</th>
								</tr>
							</thead>
							<tbody>
								<!-- 리스트 출력 -->
								<c:forEach items="${resultList }" var="data">
									<tr onclick="location.href='../manager/manager_read_notice.do?bn_no=${data.noticevo.bn_no}'" style="cursor:pointer">
										<td style="width: 10%" class="text-center">${data.noticevo.bn_no }</td>
										<td style="width: 40%" class="text-center">${data.noticevo.bn_title }</td>
										<td style="width: 10%" class="text-center">${data.membervo.m_nick }</td>
										<td style="width: 10%" class="text-center">${data.date }</td>
                                        <td style="width: 10%" class="text-center">
	                                        <c:if test="${data.noticeNew == true  }">
												<span class="badge badge-pill badge-info">new</span>
											</c:if>	
										</td>
									</tr>	
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col text-end">
						<a href="${pageContext.request.contextPath }/manager/manager_notice_write.do"><input type="button" class="btn btn-dark" value="공지시항 작성"></a>
					</div>
				</div>
			</div>
		</div>
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
	<jsp:include page="/WEB-INF/views/manager/commons/global_footer.jsp" /> <%-- 풋터 --%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>