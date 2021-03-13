<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/freeboard/write_page.js"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">

<title>NANUGI: COMMUNITY</title>

<style>

	body{
		padding-top:100px;
		background-image: url('${pageContext.request.contextPath }/resources/img/backgroundflower.png');
		background-size: cover;
	}

</style>

<script type="text/javascript">
	
	
	
</script>


</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" />
	<article>
		<div class="container min-height" role="main">
			<h2>자유롭게 글을 남겨보세요!</h2>
			<form id="form" action="${pageContext.request.contextPath}/board/freeboard_write_process.do" method="post" enctype="multipart/form-data">
				<div class="row mt-3">
					<div class="col">
					<label for="headline"></label>
					<select name="mst_bfh_no" id="headline" class="form-select" aria-label="Default select example" onblur="ckHeadline()">
						<option value="0">말머리</option>
						<option value="1">정보</option>
						<option value="2">잡담</option>
						<option value="3">질문</option>
					</select>
					
					</div>
					<div class="col-9">
						<label for="title"></label>
						<input type="text" class="form-control" name="bf_title" id="title" placeholder="제목을 입력하세요" onblur="ckTitle()">
						<div id="alert_title" class="text-danger"></div>
					</div>
					<div class="col">
						<label for="writer"></label>
						<input value="${loginUser.memberVo.m_nick }" class="form-control" id="writer" readonly="readonly">
					</div>
				</div>
				
				<div class="mt-3">
					<label for="content"></label>
					<textarea class="form-control min-height" rows="14" name="bf_content" id="content" placeholder="내용을 입력하세요" onblur="ckContent()"></textarea>
					<div id="alert_content" class="text-danger"></div>
					<div id="alert_headline" class="text-danger"></div>
				</div>
				
				<div>
					<label for="file"></label><br>
					<input type="file" name="files" class="form-control" id="attach-file" accept="image/*">
				</div>
			</form>
			
			<div align="right" class="mt-3 mb-5">
				<button type="button" class="btn btn-outline-dark" id="btnList" onclick="location.href='${pageContext.request.contextPath }/board/freeboard_page.do'">돌아가기</button>
				<button type="button" class="btn btn-dark" id="btnSave" onclick="formSubmit()">저장</button>
			</div>
		</div>
	</article>
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>