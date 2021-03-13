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
<script type="text/javascript">
	

	
	

</script>

<style>

	body{
		padding-top: 50px;
		padding-bottom: 40px;
		background-image: url('${pageContext.request.contextPath }/resources/img/backgroundflower.png');
		background-size: cover;
	}
	


</style>
</head>
<body>
	<article>
		<div class="container" role="main">
			<h2>봉사하면서 느낀 점을 적어주세요!</h2>
			<form id="form" action="${pageContext.request.contextPath}/volunteer/review_write_process.do" method="post" enctype="multipart/form-data">
				<div class="row mt-3">
					<div class="col">
						<label for="starrate"></label>
						<select name="rv_score" class="form-select" id="starrate" aria-label="Default select example" onblur="ckStarrate()">
							<option value="0">평점 선택</option>
							<option value="1">★☆☆☆☆</option>
							<option value="2">★★☆☆☆</option>
							<option value="3">★★★☆☆</option>
							<option value="4">★★★★☆</option>
							<option value="5">★★★★★</option>
						</select>
						
					</div>
				
					<div class="col-9">
						<label for="title"></label>
						<input type="text" class="form-control" name="rv_title" id="title" placeholder="제목을 입력하세요" onblur="ckTitle()">
						<div id="alert_title" class="text-danger"></div>
					</div>
					
					<div class="col mb-3">
						<label for="writer"></label>
						<input value="${loginUser.memberVo.m_nick }" class="form-control" id="writer" readonly="readonly"/>
					</div>
				</div>
				
				
				
				<div class="mb-3">
					<label for="content"></label>
					<textarea class="form-control min-height" rows="15" name="rv_content" id="content" placeholder="내용을 입력하세요" onblur="ckContent()"></textarea>
					<div id="alert_content" class="text-danger"></div>
					<div id="alert_starrate" class="text-danger"></div>
				</div>
				
				
				<input type="hidden" name="jv_no" value="${param.jv_no }">
			</form>
		
			<div align="right">
			<button type="button" class="btn btn-outline-dark" id="btnList" onclick="location.href='${pageContext.request.contextPath }/mypage/join_page.do'">돌아가기</button>
				<button type="button" class="btn btn-dark" id="btnSave" onclick="formSubmit()">저장</button>
			</div>
			</div>
		
	</article>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</body>
</html>