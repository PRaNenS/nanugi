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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/notice/read_page.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet"> 
<title>MANAGER: NOTICE</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/manager/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/manager/commons/board_tap.jsp" />
	
		<div class="container min-height mb-5" role="main">
			<div class="row">
				<div class="col"></div>
				<div class="col-10 mt-3">
					<h2>자유롭게 글을 남겨보세요!</h2>
					<form id="form" action="${pageContext.request.contextPath}/manager/write_notice_process.do" method="post">
						<div class="row mt-3">
							<div class="col">
								<label for="title"></label>
								<input type="text" class="form-control" name="bn_title" id="title" placeholder="제목을 입력하세요" onblur="ckTitle()">
								<div id="alert_title" class="text-danger"></div>
							</div>
							<div class="col-2">
								<label for="writer"></label>
								<input value="${loginUser.memberVo.m_nick }" class="form-control" id="writer" readonly="readonly">
							</div>
						</div>
						
						<div class="mt-3">
							<label for="content"></label>
							<textarea class="form-control min-height" rows="9" name="bn_content" id="content" placeholder="내용을 입력하세요" onblur="ckContent()"></textarea>
							<div id="alert_content" class="text-danger"></div>
						</div>
					</form>
					
					<div class="row">
						<div class="col">
							<div align="right" class="mt-3">
								<input type="button" value="취소" class="btn btn-dark" onClick="location.href='../manager/manager_notice_page.do'">
								<input type="button" value="작성" class="btn btn-dark" onclick="writenotice()">
							</div>
						</div>
					</div>
				</div>
				<div class="col"></div>
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/manager/commons/global_footer.jsp" /> <%-- 풋터 --%>
	
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>