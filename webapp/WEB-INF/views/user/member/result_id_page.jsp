<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: FIND ID</title>
</head>
<body>
	<div class="container min-height">
		<div class="row mt-5 mb-5">
			<div class="col text-center">
				<a href="/" class="fs-4 none-underline">NANUGI</a>
				<span class="fs-5">회원정보</span>
			</div>
		</div>
		<div class="row">
			<div class="col-3"></div>
			<div class="col">
				<div class="row">
					<div class="col">
						<h1>찾으시는 계정정보가</h1>
						<h1 class="text-primary">존재합니다!</h1>
					</div>
				</div>
				<div class="row mt-3 mb-3">
					<div class="col">
						<hr>
					</div>
				</div>
				<div class="row">
					<div class="col fw-bold">
						찾으시는 계정의 아이디는, <span class="text-primary">${memberVo.m_id }</span>입니다
					</div>
				</div>
				<div class="row">
					<div class="col">
						안녕하세요, ${memberVo.m_nick }회원님
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

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>