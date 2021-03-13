<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/member/find_pw_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: FIND PW</title>
</head>
<body>
	<div class="container">
		<div class="row min-height mb-5">
			<div class="col-3"></div>
			<div class="col">
				<div class="row my-5">
					<div class="col text-center mt-5">
						<h1 class="display-3"><a href="../main/main_page.do" class="none-underline"><img  style="width: 300px;" src="<%=request.getContextPath() %>/resources/img/main_Logo.png"></a></h1>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="border px-5 py-5">
							<div class="row">
								<div class="col fw-bold fs-4">
									회원정보에 등록한 2차 확인 이메일로 인증
								</div>
							</div>
							<div class="row mb-4">
								<div class="col text-secondary">
									회원정보에 등록한 2차 확인 이메일과 입력한 이메일이 같아야<br>비밀번호를 찾을 수 있습니다
								</div>
							</div>
							<div class="row">
								<div class="col-4 fw-bold">
									아이디
								</div>
								<div class="col-5">
									<input id="id" class="form-control" type="text">
									<div id="message_id" class="text-danger"></div>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-4 fw-bold">
									2차 확인 이메일
								</div>
								<div class="col-5">
									<input id="email" class="form-control" type="text" placeholder="@email.com">
									<div id="message_email" class="text-danger"></div>
								</div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col"></div>
							<div class="col-4">
								<button class="form-control btn text-white" style="background-color: #5769F4" onclick="find()">다음</button>
							</div>
							<div class="col"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	
	<form id="frm_find" action="../member/find_pw_process.do" method="post">
		<input id="inp_id" name="inp_id" type="hidden">
		<input id="inp_email" name="inp_email" type="hidden">
	</form>
	
	<jsp:include page="/WEB-INF/views/user/member/commons/member_footer.jsp" />

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>