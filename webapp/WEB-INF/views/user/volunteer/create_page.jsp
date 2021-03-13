<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/create_page.js"></script>
<title>NANUGI: VOLUNTEER</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/user/volunteer/commons/volunteer_tap.jsp" />
	
	<div style="background-color: #5769F4">
		<div class="container mb-4">
			<table style="height: 100px; background-color: #5769F4">
				<tbody>
					<tr>
						<td class="align-middle text-white"><h2>봉사 개설</h2></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="container mb-5 my-5 min-height">
		<div class="row">
			<div class="col">
				<form id="frm_create" action="../volunteer/create_process.do" method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="col">
							<div class="row">
								<div class="col">
									<h5>카테고리</h5>
								</div>
							</div>
							<div class="row">
								<div class="col-6">
									<select name="mst_vc_no" class="form-control">
										<c:forEach items="${categoryList }" var="category">
											<option value="${category.mst_vc_no }">${category.mst_vc_category }</option>
										</c:forEach>
									</select>
								</div>
							</div>	
						 	<div class="row mt-3"> <!-- 봉사활동명 -->
								<div class="col">
									<h5>봉사활동명</h5>
								  <input id="input_title" name="mst_v_title" type="text" class="form-control" onblur="ckTitle()">
								</div>
								<div id="alert_title" class="text-danger"></div>
							</div>
							<div class="row mt-3"> <!-- 기관명 -->
								<div class="col">
									<h5>기관명</h5>
									<input class="form-control" type="text" disabled value="${loginUser.memberVo.m_nick }">
								</div>
							</div>
							<div class="row mt-3"><!-- 모집 기간  -->
								<div class="col">
									<div class="row">
										<div class="col">
											<h5>봉사모집  기간</h5>
										</div>
									</div>
									<div class="row">
										<div class="col">
											<input id="input_from" name="mst_v_from" type="date" class="form-control" onblur="ckDatefrom()">
											<div id="alert_from" class="text-danger"></div>
										</div>
										
										<div class="col">
											<input id="input_to" name="mst_v_to" type="date" class="form-control" onblur="ckDateto()">
											<div id="alert_to" class="text-danger"></div>
										</div>
									</div>
								</div>
							</div>
							

							<div class="row mt-3">
								<div class="col">
									<div class="row">
										<div class="col">
											<h5>모집 인원</h5>
										</div>
									</div>
									<div class="row">
										<div class="col">
											<div class="input-group">
												<input id="input_goal" name="mst_v_goal" type="text" class="form-control" placeholder="모집 인원 수" onblur="ckGoal()">
												<span class="input-group-text">명</span>
											</div>
											<div id="alert_goal" class="text-danger"></div>
										</div>
									</div>
								</div>

								<div class="col">
									<div class="row">
										<div class="col">
											<h5>봉사활동 장소</h5>
										</div>
									</div>
									<div class="row">
										<div class="col">
											<div class="input-group">
												<input id="input_place" name="mst_v_place" type="text" class="form-control" placeholder="봉사활동 장소" onblur="ckPlace()">
											</div>
											<div id="alert_place" class="text-danger"></div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row mt-3">
								<div class="col">
								</div>
							</div>							
						</div>
							
						<%-- 이미지 부분 --%>
						<div class="col">
							<div class="row">
								<div class="col">
									<h5>이미지 업로드</h5>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<input id="file" type="file" class="form-control" accept="image/*" onchange="refreshThumbnail()">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="card"><img id="thumbnail" class="float-end card-img-top" height="500" width="500" ></div>
								</div>
								<div id="thumnail_container" class="card h-100" onload="ckImg()">
									<input id="input_Container" type="hidden">
								</div>
								<div id="alert_img" class="text-danger"></div>
							</div>
						</div>
					</div>

					<div class="row mt-3">
						<div class="col">
							<div class="row">
								<div class="col-6">
									<h5>봉사활동 기간</h5>
								</div>
								<!-- 옵션 추가 버튼 -->
								<div class="col-4">
									<input name="add_button" type="button" value="+" onclick="add_option()" class="btn btn-outline-secondary">
								</div>
								<div class="col-2">
								</div>
							</div>

								<!-- 옵션 줄 -->
								<div class="row mt-2 delete-target" id="option_add">
									<div class="col"><!-- 봉사활동 기간 -->
										<input id="input_date" name="mst_vd_date" type="text" class="form-control"  placeholder="yyyy-mm-dd" onblur="ckDate()">
										<div id="alert_date" class="text-danger"></div>
									</div>
									<div class="col">
										<div class="row">
											<div class="col-6">
												<div class="row">
													<div class="col"><!-- 오전/오후 -->				
															<input id="input_time" name="mst_vd_time" type="text" class="form-control" placeholder="오전/오후" onblur="ckTime()">
															<div id="alert_time" class="text-danger"></div>
													</div>
													<div class="col-1">
														<input type="button" value="삭제"  class="btn btn-outline-danger" onclick="remove_item(this)">
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 여기에 추가   -->
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="row mt-1">				
								<div class="col">
									<div id="option_List"></div>
								</div>
							</div>
						</div>					
					</div>					

					<div class="row mt-3"><!-- 내용 -->
						<div class="col">
							<h5>내용</h5>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<textarea id="input_content" name="mst_v_content" class="form-control" rows="10" onblur="ckContent()"></textarea>
						</div>
						<div id="alert_content" class="text-danger"></div>
					</div>
				</form>
					<div class="row mt-3 mb-4">
						<div class="col"></div>
						<div class="col-2">
							<button class="form-control btn btn-primary" onclick="createVolunteer()">개 설</button>
						</div>
					</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <%-- 풋터 --%>
	
	<!-- 상품 옵션 추가 포맷 -->
	<div class="row mt-2 d-none delete-target" id="option_add">
		<div class="col"><!-- 봉사활동 기간 -->
			<input id="input_date" name="mst_vd_date" type="text" class="form-control"  placeholder="yyyy-mm-dd">
		</div>
		<div class="col">
			<div class="row">
				<div class="col-6">
					<div class="row">
						<div class="col"><!-- 오전/오후 -->
							<div class="input-group">
								<input name="mst_vd_time" type="text" class="form-control" placeholder="오전 /오후">
							</div>
						</div>
						<div class="col-1">
							<input type="button" value="삭제"  class="btn btn-outline-danger" onclick="remove_item(this)">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>