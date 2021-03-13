<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/member/join_page.js"></script>
<title>NANUGI: JOIN</title>
</head>
<body>
	<div class="container mb-3">
		<div class="row mb-4">
			<div class="col">
				<h1 class="text-center display-3">
					<a href="../main/main_page.do" class="none-underline"><img  style="width: 300px;" src="<%=request.getContextPath() %>/resources/img/main_Logo.png"></a>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-4"></div>
			<div class="col">
				<form id="frm_join" action="../member/join_process.do" method="post">
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 class="fw-bold">아이디</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<input id="input_id" name="m_id" type="email" onblur="ckId()" class="form-control" placeholder="@email.com">
							</div>
						</div>
						<div id="alert_id" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 class="fw-bold">비밀번호</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<input id="input_pw" name="m_pw" type="password" onblur="ckPw()" class="form-control">
							</div>
						</div>
						<div id="alert_pw" class="text-danger"></div>
					</div>
					<div class="mb-4">
						<div class="row">
							<div class="col"><h6 class="fw-bold">비밀번호 재확인</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<input id="input_pw_confirm" type="password" onblur="ckPwConfirm()" class="form-control">
							</div>
						</div>
						<div id="alert_pw_confirm" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 id="nick" class="fw-bold">닉네임</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<input id="input_nick" name="m_nick" type="text" onblur="ckNick()" class="form-control">
							</div>	
						</div>
						<div id="alert_nick" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 id="name" class="fw-bold">이름</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<input id="input_name" name="m_name" type="text" onblur="ckName()" class="form-control">
							</div>
						</div>
						<div id="alert_name" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 id="birth" class="fw-bold">생년월일</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<div>
								  <input id="input_birth_year" type="text" onblur="ckYear()" class="form-control" placeholder="년 (4자)">
								</div>
							</div>
							<div class="col-3">
								<div>
								  <input id="input_birth_month" type="text" onblur="ckMonth()" class="form-control" placeholder="월 (2자)">
								</div>
							</div>
							<div class="col-3">
								<div>
								  <input id="input_birth_day" type="text" onblur="ckDay()" class="form-control" placeholder="일 (2자)">
								</div>
							</div>
						</div>
						<div id="alert_birth" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 class="fw-bold">성별</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<select id="input_gender" name="m_gender" class="form-select">
									<option value="M">남자</option>
									<option value="F">여자</option>
								</select>
							</div>
						</div>
						<div id="alert_gender" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 class="fw-bold">주소</h6></div>
						</div>
						<div class="row mb-1">
							<div class="col-4">
								<input id="input_zip" name="m_zip" type="text" onblur="ckZip()" class="form-control" placeholder="우편번호">
							</div>
							<div class="col">
								<input id="input_address1" name="m_address1" type="text" onblur="ckAddress1()" class="form-control" placeholder="주소">
							</div>
						</div>
						<div class="row">
							<div class="col">
								<input id="input_address2" name="m_address2" type="text" onblur="ckAddress2()" class="form-control" placeholder="상세주소">
							</div>
						</div>
						<div id="alert_address" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 class="fw-bold">2차 확인 이메일</h6></div>
						</div>
						<div class="row">
							<div id="div_email" class="col">
								<input id="input_email" name="m_email" type="email" onblur="ckEmail()" class="form-control" placeholder="@email.com">
							</div>
						</div>
						<div id="alert_email" class="text-danger"></div>
					</div>
					<div class="mb-2">
						<div class="row">
							<div class="col"><h6 class="fw-bold">연락처</h6></div>
						</div>
						<div class="row">
							<div class="col">
								<input id="input_tel" name="m_tel" type="text" onblur="ckTel()" class="form-control" placeholder="'-'없이 입력">
							</div>
						</div>
						<div id="alert_tel" class="text-danger"></div>
					</div>
					<input name="m_uniqnum" type="hidden" value="N">
					<input name="m_profile_img_link" type="hidden" value="<%=request.getContextPath() %>/resources/img/default_profile.png">
				</form>
				
				<div class="row mb-5 mt-5">
					<div class="col">
						<button class="form-control btn text-white" style="background-color: #5769F4" onclick="join()">회원가입</button>
					</div>
				</div>
				
			</div>
			<div class="col-4"></div>
		</div>
		<jsp:include page="/WEB-INF/views/user/member/commons/member_footer.jsp" />
	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>