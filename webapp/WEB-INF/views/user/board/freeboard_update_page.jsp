<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/review_write.js"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">

<title>NANUGI: COMMUNITY</title>
<style>
	
	body{
		padding-top: 100px;
		padding-bottom: 40px;
		background-image: url('${pageContext.request.contextPath }/resources/img/backgroundflower.png');
		background-size: cover;
	}
	
	
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" />
	<!-- 테이블 시작 -->
	
	<div class="contatiner" role="main">
	<div class="row">
		<div class="col"></div>
			<div class="col-9 mb-2">
		<h2>게시글 수정</h2>
		<form action="${pageContext.request.contextPath}/board/freeboard_update_process.do" method="post">
			<label for="title"></label>
			<input type="text" class="form-control" name="bf_title" id="title" value="${result.freeBoardVo.bf_title }">
			
			<div class="mb-2">
				<label for="writer"></label>
				<input value="${result.memberVo.m_nick }" class="form-control" readonly="readonly">
			</div>
			<div class="mb-2">
				<label for="content"></label>
				<textarea class="form-control min-height" name="bf_content" rows="13" id="content">${result.freeBoardVo.bf_content }</textarea>
				<input type="hidden" name="bf_no" value="${result.freeBoardVo.bf_no }">
			</div>
			
			<div align="right">
				<a href="${pageContext.request.contextPath }/board/freeboard_page.do">
					<input type="button" class="btn btn-outline-dark" value="취소"></a>
					<input type="submit" class="btn btn-dark" value="수정">
			</div>	
		</form>
		</div>
		<div class="col"></div>
		</div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
	
</body>
</html>
