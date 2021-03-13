<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>NANUGI: JOIN COMPLETE</title>
</head>
<body>
	<div class="container min-height">
		<div class="row mt-5 mb-5">
			<div class="col text-center">
				<h1 class="display-3"><a href="../main/main_page.do" class="none-underline"><img  style="width: 300px;" src="<%=request.getContextPath() %>/resources/img/main_Logo.png"></a></h1>
				<span class="fs-5">회원정보</span>
			</div>
		</div>
		<div class="row">
			<div class="col-3"></div>
			<div class="col">
				<div class="row">
					<div class="col">
						<h1>NANUGI 회원이 되신 것을</h1>
						<h1 class="text-primary">환영합니다!</h1>
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
				<div class="row">
					<div class="col">
						NANUGI 회원으로 가입해 주셔서 감사합니다
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						NANUGI에서 기부와 관련된 활동를 마음껏 참여해보세요
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						기관이신 경우, 마이페이지에서 기관유저로의 등록신청 및 승인 이후에
					</div>
				</div>
				<div class="row">
					<div class="col">
						NANUGI에서 관련된 활동을 이용하실 수 있습니다
					</div>
				</div>
				<div class="row mt-5">
					<div class="col mt-5 mb-5">
						<a href="./login_page.do" class="btn btn-primary none-underline form-control">시작하기</a>
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