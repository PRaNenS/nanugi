<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/member/login_page.js"></script>
<title>NANUGI: LOGIN</title>
</head>

<style>

 	body{
	
		background-image: url('${pageContext.request.contextPath }/resources/img/login3.png');
		background-size: cover;
	}
	
</style>
<body>
	<div class="container">
		<div class="row min-height">
			<div class="col-4"></div>
			<div class="col">
				<div class="row mt-5">
					<div class="col text-center mt-5">
						<h1 class="display-3"><a href="../main/main_page.do" class="none-underline"><img  style="width: 300px;" src="<%=request.getContextPath() %>/resources/img/main_Logo.png"></a></h1>
					</div>
				</div>
				<form id="frm_login" action="../member/login_process.do" method="post">
					<div class="row mt-5">
						<div class="col">
							<div class="form-floating">
							  <input id="input_id" name="m_id" type="email" class="form-control" placeholder="name@example.com">
							  <label for="floatingInput">아이디</label>
							</div>
						</div>
					</div>
					<div class="row mt-3">
						<div class="col">
							<div class="form-floating">
							  <input id="input_pw" name="m_pw" type="password" class="form-control" placeholder="Password" onkeypress="if(event.keyCode==13){login();}">
							  <label for="floatingPassword">비밀번호</label>
							</div>
						</div>
					</div>
					<div id="alert_box" class="text-danger"></div>
				</form>
				<div class="row mb-3 mt-4">
					<div class="col">
						<button class="form-control btn btn-light " onclick="login()">로그인</button>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<hr>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="row mt-2 mb-5">
							<p class="col text-center">
								<a href="../member/find_id_page.do" class="text-secondary none-underline">아이디 찾기</a>
							</p>
							<p class="col text-center">
								<a href="../member/find_pw_page.do" class="text-secondary none-underline">비밀번호 찾기</a>
							</p>
							<p class="col text-center">
								<a href="./join_page.do" class="text-secondary none-underline">회원가입</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-4"></div>
		</div>
	</div>
	
<jsp:include page="/WEB-INF/views/user/member/commons/member_footer.jsp" /> <%-- 풋터 --%>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>