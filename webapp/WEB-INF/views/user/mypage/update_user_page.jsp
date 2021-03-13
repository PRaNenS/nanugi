<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mypage/update_user_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: MYPAGE</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->

	<div class="container mb-5 pt-5">
		<div class="row">
			<jsp:include page="/WEB-INF/views/user/mypage/commons/mypage_sidebar.jsp" /> <!-- 사이드바 -->
			<div class="col ms-3 me-3">
				<!-- 타이틀 -->
				<div class="row mb-3">
					<div class="col">
						<h3>정보수정</h3>
					</div>
				</div>
				
				<div class="row mb-4">
					<div class="col btn-group" role="group">
						<button class="text-center btn btn-outline-secondary pt-2 pb-2 form-control" onclick="clickTab(1)">유저 정보</button>
						<button class="text-center btn btn-outline-secondary pt-2 pb-2 form-control" onclick="clickTab(2)">기관 신청</button>
					</div>
				</div>
				
				<div class="row">
					<div class="col">
									
						<!-- 유저 정보 -->
						<div id="user_information_wrap" class="d-block">
							<h5>유저 정보</h5>
							<!-- 프로필 이미지 -->
							<form id="frm_profile" action="../member/update_profile_process.do" method="post">
								<div class="border-top border-bottom">
									<div class="row">
										<div class="col-3">
											<div class="border-end" style="background-color: #F9F9F9; height: 280px;">
												<div class="px-3 py-2">
													<h6 class="fw-bold">프로필</h6>
												</div>
											</div>
										</div>
										<div class="col-4 text-center">
											<div class="px-3 py-2"></div>
											<img id="profile_img" src="${profileImgLink }" style="width: 200px; height: 200px;">
											<input id="file" type="file" class="form-control mt-1" accept="image/*" onchange="refreshProfile()">
											<input id="input_profile" name="m_profile_img_link" type="hidden" value="${loginUser.memberVo.m_profile_img_link }">
										</div>
									</div>
								</div>
							</form>
							<!-- 비밀번호 변경 버튼 -->
							<div class="row mt-3 pb-5">
								<div class="col"></div>
								<div class="col-2">
									<button class="btn btn-primary form-control" onclick="updateProfile()">프로필 변경</button>
								</div>
							</div>
							
							<!-- 비밀번호 -->
							<div class="border-top border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">비밀번호</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_pw" name="m_pw" type="password" onblur="ckPw()" class="form-control">
											<div id="alert_pw" class="text-danger"></div>
									</div>
								</div>
							</div>
							<!-- 비밀번호 변경 버튼 -->
							<div class="row mt-3 pb-5">
								<div class="col"></div>
								<div class="col-2">
									<button class="btn btn-primary form-control" onclick="updatePw()">비밀번호 변경</button>
								</div>
							</div>
							
							<!-- 닉네임/기관명 수정 -->
							<div class="border-top border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">
													<c:if test="${loginUser.memberVo.mst_mt_no == 2 }">
														닉네임
													</c:if>
													<c:if test="${loginUser.memberVo.mst_mt_no == 3 }">
														기관명
													</c:if>
												</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_nick" name="m_nick" type="text" onblur="ckNick()" class="form-control" value="${loginUser.memberVo.m_nick }">
											<div id="alert_nick" class="text-danger"></div>
									</div>
								</div>
							</div>
							<!-- 이름/대표자명 변경 -->
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">
													<c:if test="${loginUser.memberVo.mst_mt_no == 2 }">
														이름
													</c:if>
													<c:if test="${loginUser.memberVo.mst_mt_no == 3 }">
														대표자명
													</c:if>
												</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_name" name="m_name" type="text" onblur="ckName()" class="form-control" value="${loginUser.memberVo.m_name }">
											<div id="alert_name" class="text-danger"></div>
									</div>
								</div>
							</div>
							<!-- 주소 변경 -->
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 150px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">주소</h6>
											</div>
										</div>
									</div>
									<div class="col pe-5">
										<div class="px-3 py-2"></div>
											<div class="row mb-2">
												<div class="col-2">
													<input id="input_zip" name="m_zip" type="text" onblur="ckZip()" placeholder="우편번호" class="form-control" value="${loginUser.memberVo.m_zip }">
												</div>
												<div class="col">
													<input id="input_address1" name="m_address1" type="text" onblur="ckAddress1()" placeholder="주소" class="form-control" value="${loginUser.memberVo.m_address1 }">
												</div>
											</div>
											<div class="row">
												<div class="col">
													<input id="input_address2" name="m_address2" type="text" onblur="ckAddress2()" placeholder="상세주소" class="form-control" value="${loginUser.memberVo.m_address2 }">
												</div>
											</div>
										<div id="alert_address" class="text-danger"></div>
									</div>
								</div>
							</div>
							<!-- 2차 확인 이메일 변경 -->
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">
													2차 확인 이메일
												</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_email" name="m_email" type="text" onblur="ckEmail()" class="form-control" value="${loginUser.memberVo.m_email }">
											<div id="alert_email" class="text-danger"></div>
									</div>
								</div>
							</div>
							<!-- 연락처 변경 -->
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">
													연락처
												</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_tel" name="m_tel" type="text" onblur="ckTel()" class="form-control" value="${loginUser.memberVo.m_tel }">
											<div id="alert_tel" class="text-danger"></div>
									</div>
								</div>
							</div>
							<!-- 회원정보 변경 버튼 -->
							<div class="row mt-3">
								<div class="col"></div>
								<div class="col-2">
									<button class="btn btn-primary form-control" onclick="updateUser()">회원정보 변경</button>
								</div>
							</div>
						</div>
						
						<!-- 기관신청 -->
						<div id="request_company_wrap" class="d-none">
							<h5>기관 신청</h5>
							<div class="border-top border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">기관명</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_nick_c" type="text" onblur="ckNickC()" class="form-control">
											<div id="alert_nick_c" class="text-danger"></div>
									</div>
								</div>
							</div>
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">대표자명</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_name_c" type="text" onblur="ckNameC()" class="form-control">
											<div id="alert_name_c" class="text-danger"></div>
									</div>
								</div>
							</div>
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">설립연도</h6>
											</div>
										</div>
									</div>
									<div class="col pe-4">
										<div class="py-2"></div>
										<div class="row mb-2">
											<div class="col-3">
												<input id="input_birth_year_c" type="text" onblur="ckYearC()" placeholder="년도(4자)" class="form-control">
											</div>
											<div class="col-2">
												<input id="input_birth_month_c" type="text" onblur="ckMonthC()" placeholder="월(2자)" class="form-control">
											</div>
											<div class="col-2">
												<input id="input_birth_day_c" type="text" onblur="ckDayC()" placeholder="일(2자)" class="form-control">
											</div>
											<div id="alert_birth_c" class="text-danger"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 150px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">주소</h6>
											</div>
										</div>
									</div>
									<div class="col pe-5">
										<div class="px-3 py-2"></div>
											<div class="row mb-2">
												<div class="col-2">
													<input id="input_zip_c" type="text" onblur="ckZipC()" placeholder="우편번호" class="form-control">
												</div>
												<div class="col">
													<input id="input_address1_c" type="text" onblur="ckAddress1C()" placeholder="주소" class="form-control">
												</div>
											</div>
											<div class="row">
												<div class="col">
													<input id="input_address2_c" type="text" onblur="ckAddress2C()" placeholder="상세주소" class="form-control">
												</div>
											</div>
										<div id="alert_address_c" class="text-danger"></div>
									</div>
								</div>
							</div>
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">연락처</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_tel_c" type="text" onblur="ckTelC()" placeholder="'-'없이 숫자만" class="form-control">
											<div id="alert_tel_c" class="text-danger"></div>
									</div>
								</div>
							</div>
							<div class="border-bottom">
								<div class="row">
									<div class="col-3">
										<div class="border-end" style="background-color: #F9F9F9; height: 100px;">
											<div class="px-3 py-2">
												<h6 class="fw-bold">기관고유번호</h6>
											</div>
										</div>
									</div>
									<div class="col-4">
										<div class="px-3 py-2"></div>
											<input id="input_uniqnum_c" type="text" onblur="ckUniqnumC()" class="form-control">
											<div id="alert_uniqnum_c" class="text-danger"></div>
										</div>
									</div>
								</div>
							<div class="row mt-4">
								<div class="col"></div>
								<div class="col-2">
									<c:if test="${ckRequest == 0 && loginUser.memberVo.mst_mt_no == 2}">
										<button id="btn_request" class="btn btn-primary form-control" onclick="requestCom()">신청</button>
									</c:if>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
<!-- 알림창 -->
<!-- Button trigger modal -->
<button id="btn_alert" type="button" class="btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
  Launch demo modal
</button>

<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">기관 신청</h5>
        <button id="btnClose" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div id="modalBody" class="modal-body">
        	기관유저 신청이 완료되었습니다!<br>신청이 승인되기까지 최대 7일이 소요될 수 있습니다
      </div>
      <div class="modal-footer">
        <button id="btnClose2" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<form id="frm_logout" action="../member/logout_process.do" method="get"></form>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>	
</body>
</html>