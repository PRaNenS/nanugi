<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/review_write.js"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">

<title>NANUGI: VOLUNTEER REVIEW</title>

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
		<div class="container" role="main">
		<div class="row">
			<div class="col"></div>
			<h2>후기게시글 수정</h2>
			<form id="form" action="${pageContext.request.contextPath}/volunteer/review_update_process.do" method="post">
				<div class="row">
				
				<div class="col">
					<label for="starrate"></label>
					<select name="rv_score" class="form-select" aria-label="Default select example">
						<option value="1"<c:if test="${result.reviewVolunteerVo.rv_score == 1 }"> selected</c:if>>★☆☆☆☆</option>
						<option value="2"<c:if test="${result.reviewVolunteerVo.rv_score == 2 }"> selected</c:if>>★★☆☆☆</option>
						<option value="3"<c:if test="${result.reviewVolunteerVo.rv_score == 3 }"> selected</c:if>>★★★☆☆</option>
						<option value="4"<c:if test="${result.reviewVolunteerVo.rv_score == 4 }"> selected</c:if>>★★★★☆</option>
						<option value="5"<c:if test="${result.reviewVolunteerVo.rv_score == 5 }"> selected</c:if>>★★★★★</option>
					</select>
				</div>
				
				<div class="col-8">
					<label for="title"></label>
					<input type="text" class="form-control" name="rv_title" id="title" value="${result.reviewVolunteerVo.rv_title }">
				</div>
				
				<div class="col mb-3">
					<label for="writer"></label>
					<input value="${loginUser.memberVo.m_nick }" class="form-control" readonly="readonly">
				</div>
				</div>
				
				<div class="mb-2">
					<label for="content"></label>
					<textarea class="form-control min-height" rows="13" name="rv_content" id="content">${result.reviewVolunteerVo.rv_content }</textarea>
					<input type="hidden" name="rv_no" value="${result.reviewVolunteerVo.rv_no }">
				</div>
				
				
				
			
		
			<div align="right">
				<!-- <input type="reset" class="btn btn-sm btn-secondary" value="취소"> -->
				<input type="submit" class="btn btn-dark" value="수정">
			</div>
			
		</form>
		</div>
	</div>
		

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</body>
</html>