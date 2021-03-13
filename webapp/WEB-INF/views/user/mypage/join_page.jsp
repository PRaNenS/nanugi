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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<title>NANUGI: JOIN</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->

	<div class="container mb-5 min-height pt-5">
		<div class="row">
			<jsp:include page="/WEB-INF/views/user/mypage/commons/mypage_sidebar.jsp" /> <!-- 사이드바 -->
			<div class="col mx-3">
				<!-- 타이틀 -->
				<div class="row">
					<div class="col">
						<h3>봉사 참여 내역</h3>
					</div>
				</div>
				<!-- 주문 표시 영역 -->
				<div class="row">
					<div class="col">
						<div class="border-top border-bottom">
							<div class="row">
								<div class="col-2 fw-bold text-center">날짜</div>
								<div class="col fw-bold text-center">봉사 정보</div>
								<div class="col-2 fw-bold text-center">상태</div>
							</div>
						</div>
						
						<c:forEach items="${joinList }" var="join">
							<!-- 주문상품 -->
							<div class="row">
								<div class="col">
									<div class="border-bottom py-2">
										<div class="row">
											<!-- 날짜 -->
											<div class="col-2">
												<div class="row">
													<div class="col text-center">${join.joindate }</div>
												</div>
												<div class="row">
													<div class="col text-center">
														<a href="${pageContext.request.contextPath}/volunteer/read_page.do?mst_v_no=${join.jvo.mst_v_no}">글 보기</a>
													</div>
												</div>
											</div>
											<!-- 정보 -->
												<div class="col" >
													<div class="row">
														<!-- 펀딩이미지 -->
														<div class="col-2">
															<div class="card">
																<img class="card-img-top" src="${join.volunteer.imgvo.mst_vi_img_link }">
															</div>
														</div>
														<div class="col">
															<!-- 펀딩정보 -->
															<div class="row">
																<div class="col">
																	<div class="row">
																		<div class="col">${join.volunteer.memberVo.m_nick }</div>
																	</div>
																	<div class="row">
																		<div class="col">${join.volunteer.mstVolunteerVo.mst_v_title }</div>
																	</div>
																	<div class="row">
																		<div class="col-5">신청번호 : ${join.jvo.jv_no}</div>
																	</div>
																</div>
															</div>
															<!-- 신청 날짜 -->
															<div class="row">
																<div class="col">
																	<div class="border-top mt-1 pt-1">
																		<div class="row">
																			<div class="col-2 fw-bold">신청 날짜</div>
																			<div class="col fw-bold">${join.date.mst_vd_date } | ${join.date.mst_vd_time }</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>									
												</div>
											<!-- 상태 -->
											<div class="col-2">
												<div class="row mt-3">
													<div class="col text-center">
														${join.status.mst_vs_status }
													</div>
												</div>
												<div class="row">
													<div class="col text-center">
														<c:if test="${join.status.mst_vs_no == 1}">
															<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#cancleModal">취소</button>
														</c:if>
													</div>
												</div>
												<div class="row mt-3">
													<div class="col text-center">
														<c:if test="${!empty loginUser && join.status.mst_vs_no == 2 && join.count == 0}">
															<a href="${pageContext.request.contextPath}/volunteer/review_write_page.do?jv_no=${join.jvo.jv_no}">
																<input type="button" class="btn btn-outline-dark" value="글쓰기">
															</a>
														</c:if>
													</div>
												</div>
											</div>
										</div>		
									</div>						
								</div>
							</div>
							<div class="row mt-5">
								<div class="col">
									<div class="modal fade" id="cancleModal" aria-labelledby="deleteModalLabel" aria-hidden="true" tabindex="-1">
										<div class="modal-dialog">
									    	<div class="modal-content">
									      		<div class="modal-header">
									        		<h3 class="modal-title">봉사 신청내역 확인</h3>
									        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									     		</div>
										      	<div class="modal-body">
													봉사 참여를 취소하시겠습니까?								      	
										     	</div>
										      	<div class="modal-footer">
										        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
										        	<button type="button" class="btn btn-danger"  onclick="location.href='./delete_joinv.do?jv_no=${join.jvo.jv_no}'">참여 취소</button>
										       </div>
									    	</div>
									  	</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>