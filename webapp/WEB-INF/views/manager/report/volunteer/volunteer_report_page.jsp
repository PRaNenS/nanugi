<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet"> 
<title>MANAGER: REPORT</title>

</head>
<body>
	<jsp:include page="/WEB-INF/views/manager/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/manager/commons/report_tap.jsp" />
	
	<div class="container mt-5">
		<div class="row mt-3">
			<div class="col text-center fw-bold fs-1" >봉사활동 후기글 신고</div>
		</div>
		<div class="row mt-5 min-height"> <!-- 내용  -->
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" class="text-center">접수번호</th>
							<th scope="col" class="text-center">신고내용</th>
							<th scope="col" class="text-center">신고자</th>
							<th scope="col" class="text-center">신고접수일</th>
							<th scope="col" class="text-center">제재</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList }" var="datas">
							<tr>
								<td style="width: 10%" class="text-center">${datas.reportRvVo.rerv_no }</td>
								<td onclick="location.href='../volunteer/review_read_page.do?rv_no=${datas.rvvo.rv_no}'" style="cursor:pointer; width: 40%" class="text-center">${datas.reportRvVo.rerv_content }</td>
								<td style="width: 10%" class="text-center">${datas.memberVo.m_nick }</td>
								<td style="width: 10%" class="text-center">
									<fmt:formatDate pattern="yyyy.MM.dd." value="${datas.reportRvVo.rerv_date }"/>
								</td>
								<c:if test="${datas.confirm ==0 }">
									<td style="width: 10%" class="text-center">
										<a href="${pageContext.request.contextPath}/manager/volunteer_penalty_process.do?rv_no=${datas.rvvo.rv_no}"><input class="btn btn-danger" type="button" value="제재"></a>	
									</td>
								</c:if>
								<c:if test="${datas.confirm !=0 }">
									<td style="width: 10%" class="text-center">
										<input class="btn btn-danger" type="button" value="제제 승인" disabled>
									</td>
								</c:if>	
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
	</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>