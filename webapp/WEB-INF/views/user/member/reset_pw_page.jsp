<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/member/reset_pw_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: RESET PW</title>
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
									${memberVo.m_id }계정의 비밀번호 재설정
								</div>
							</div>
							<div class="row mb-4">
								<div class="col text-secondary">
									찾으시는 계정의 비밀번호를 재설정합니다<br>찾으시는 계정의 비밀번호를 아래의 빈칸에 입력해주세요
								</div>
							</div>
							<div class="row">
								<div class="col-4 fw-bold">
									재설정 비밀번호
								</div>
								<div class="col-5">
									<input id="password" class="form-control" type="password">
									<div id="message_password" class="text-danger"></div>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-4 fw-bold">
									비밀번호 재확인
								</div>
								<div class="col-5">
									<input id="confirm_pw" class="form-control" type="password" onblur="ckpw()">
									<div id="message_confirm" class="text-danger"></div>
								</div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col"></div>
							<div class="col-4">
								<button class="form-control btn text-white" style="background-color: #5769F4" onclick="reset()">재설정</button>
							</div>
							<div class="col"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	
	<form id="frm_reset" action="../member/reset_pw_process.do" method="post">
		<input name="m_no" type="hidden" value="${memberVo.m_no }">
		<input id="inp_pw" name="m_pw" type="hidden">
		<input name="m_nick" type="hidden" value="${memberVo.m_nick }">
	</form>
	
	<jsp:include page="/WEB-INF/views/user/member/commons/member_footer.jsp" />

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>