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
<title>NANUGI: Warning</title>
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
						<h1>찾으시는 계정의 정보가</h1>
						<h1 class="text-primary">존재하지 않습니다!</h1>
					</div>
				</div>
				<div class="row mt-3 mb-3">
					<div class="col">
						<hr>
					</div>
				</div>
				<div class="row">
					<div class="col fw-bold">
						입력 정보를 다시 한번 확인해주세요
					</div>
				</div>
				<div class="row">
					<div class="col">
						입력 정보가 정확하지 않을 경우, 아이디 및 비밀번호를 찾으실 수 없습니다
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						다시 한번, 찾으시는 계정의 정보를 확인하신 후,
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						아래 링크를 통해 NANUGI 고객센터에 문의해주시기 바랍니다
					</div>
				</div>
				<div class="row">
					<div class="col">
						<a href="#">http://www.nanugi.com/center</a>
					</div>
				</div>
				<div class="row mt-5">
					<div class="col mt-5 mb-5">
						<a href="./login_page.do" class="btn btn-primary none-underline form-control">로그인 화면으로</a>
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