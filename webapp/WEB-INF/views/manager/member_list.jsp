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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet"> 
<title>MANAGER: USER</title>
</head>
<body>
	<%-- 이벤트 리스트 페이지 --%>
	<jsp:include page="/WEB-INF/views/manager/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/manager/commons/user_tap.jsp" />

	
	<div class="container mt-5">
		<div class="row mt-5 min-height"> <!-- 내용  -->
			<div class="col-1"></div>
			<div class="col-10">
				<table class="table table-hover">
						<thead>
							<tr>
								<th>No</th>
								<th>회원 구분</th>
								<th>회원명</th>
								<th>회원 닉네임</th>
								<th>가입 날짜</th>
								<th>회원 상태 </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultList }" var="data">
										<tr>
											<td>${data.member.m_no }</td>
											<td >${data.typevo.mst_mt_type }</td>
											<td>${data.member.m_name }</td>
											<td>${data.member.m_nick}</td>
											<td>${data.date }</td>
											<td>
												<c:if test="${data.limit >= 1 }">
													<input class=" btn btn-danger" type="button" value="정지 회원" disabled="disabled">
												</c:if>
												<c:if test="${data.limit != 1 }">
													<input class=" btn btn-primary" type="button" value="정상 회원" disabled="disabled">
												</c:if>
											</td>								
										</tr>
							</c:forEach>	
						</tbody>
					</table>
			</div>
			<div class="col-1"></div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/manager/commons/global_footer.jsp" /> <%-- 풋터 --%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>