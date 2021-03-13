<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>NANUGI: LOGIN FAILED</title>
</head>
<body>
	<div class="container min-height">
		<div class="row mt-5 mb-5">
			<div class="col text-center">
				<a href="../main/main_page.do" class="fs-4 none-underline">NANUGI</a>
				<span class="fs-5">이용정지</span>
			</div>
		</div>
		<div class="row">
			<div class="col-3"></div>
			<div class="col">
				<div class="row">
					<div class="col">
						<h1>사용자 로그인이</h1>
						<h1 class="text-danger">불가합니다.</h1>
					</div>
				</div>
				<div class="row mt-3 mb-3">
					<div class="col">
						<hr>
					</div>
				</div>
				<div class="row">
					<div class="col fw-bold">
						안녕하세요, <span class="text-primary">${userNick }</span>회원님
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						<span class="text-danger fw-bold">게시글 신고 제재</span>로 인해 
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						<span class="text-danger fw-bold">${date}</span>일까지 사이트 이용이 중지되었습니다.
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						정지기간이 지난 뒤 다시 사이트 이용이 가능하십니다.
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						깨끗한 인터넷 문화를 위해 노력해주시기 바랍니다.
					</div>
				</div>
				<div class="row mt-5">
					<div class="col mt-5 mb-5">
						<a href="../main/main_page.do" class="btn btn-primary none-underline form-control">메인페이지로</a>
					</div>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/user/member/commons/member_footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>