<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/volunteer/review_read_page.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">

<title>NANUGI: VOLUNTEER REVIEW</title>
<style>

	body{
		padding-top: 10px;
	}
	
	
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" />
	<br>
	<br>
	<div class="container">
	<div class="row">
		<div class="col-1"></div>
		<div class="col">
	<div class="row">
		<div class="fs-1 fw-bold col mt-5">
			${result.reviewVolunteerVo.rv_title }
		</div>
			
		<div class="row">
		<div class="info col-6 mt-2">
			<span class="username" style="font-weight: bold;">${result.memberVo.m_nick }</span>
			<span class="separator">・</span>
			<span class="date"><fmt:formatDate value="${result.reviewVolunteerVo.rv_write_date }" pattern="yyyy.MM.dd."/></span>
			<span class="starrate">
				<c:choose>
					<c:when test="${result.reviewVolunteerVo.rv_score == 1}">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${result.reviewVolunteerVo.rv_score == 2}">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${result.reviewVolunteerVo.rv_score == 3}">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${result.reviewVolunteerVo.rv_score == 4}">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${result.reviewVolunteerVo.rv_score == 5}">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="${pageContext.request.contextPath }/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
				</c:choose>
			
			</span>
		</div>
		<div class="text-end col">
				<c:if test="${!empty loginUser }">
					<c:choose>
						<c:when test="${empty result.recommendVo }">
							<a href="${pageContext.request.contextPath }/volunteer/review_recommend_process.do?rv_no=${result.reviewVolunteerVo.rv_no}">
								<input type="button" id="recommendBtn" class="btn btn-dark" value="추천👍🏼️">
							</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/volunteer/review_recommend_cancel_process.do?rv_no=${result.reviewVolunteerVo.rv_no}">
								<input type="button" id="recommendCancelBtn" class="btn btn-danger" value="👍추천취소">
							</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
			</div>
		</div>
		
		<hr>
		<div class="content col-mb-6">
			<div style="min-height: 400px;">${result.content }</div>
		</div>
		<div class="contentBtn" align="right">
			<c:if test="${!empty loginUser && loginUser.memberVo.m_no == result.reviewVolunteerVo.m_no }">
				<a href="${pageContext.request.contextPath }/volunteer/review_update_page.do?rv_no=${result.reviewVolunteerVo.rv_no}">
					<input type="button" class="btn btn-secondary" value="수정">
				</a>
				<a href="#">
					<input type="button" class="btn btn-dark" value="삭제" onclick="deleteVolunteer()">
				</a>
			</c:if>
			<c:if test="${!empty loginUser }">
				<span class="cell">
					<button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#reportRV" onclick="checkRV()">신고🚨</button>
				</span>
			</c:if>
			<!-- 봉사활동 후기 신고 모달 -->
			<div class="modal fade" id="reportRV" tabindex="-1" aria-labelledby="reportRVModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="reportRV">게시물 신고</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body" id="box">
						<textarea id="report_content" rows="3" cols="50" placeholder="신고사유 입력(최대 500자)" required="required"></textarea>
						<div class="text-danger text-start" id="messageBox">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="closeBtn" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<input type="button" id="reportBtn" class="btn btn-primary" onclick="writeReport(${result.reviewVolunteerVo.rv_no})" value="Save Reports">
				</div>
				</div>
				</div>						
			</div>
			<a href="${pageContext.request.contextPath }/volunteer/review_page.do">
				<input type="button" class="btn btn-dark" value="목록">
			</a>
		</div>
		<hr>
		<div class="comment-write col mb-3">
			<form action="${pageContext.request.contextPath }/volunteer/review_comment_process.do?rv_no=${result.reviewVolunteerVo.rv_no}" method="post" id="comment_submit">
				<div>
					<input type="hidden" name="rv_no" value="${result.reviewVolunteerVo.rv_no }">
					<textarea name="crv_content" id="comment_content" rows="2" class="form-control" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 댓글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
				</div>
				<div id="alert_comment" class="text-danger"></div>
			</form>
			<div align="right" class="mt-2">
				<input type="submit" class="btn btn-sm btn-dark" value="댓글 작성" onclick="ckComment()">
			</div>
		</div>
		<hr>
		<div class="comment-list col mb-3">
			<c:forEach items="${result.commentList }" var="comment">
				<div class="comment-info col mt-1">
					<span class="username" style="font-weight: bold;">${comment.commentMemberVo.m_nick }</span>
					<span class="separator">・</span>
	    			<span class="date"><fmt:formatDate value="${comment.commentRVVo.crv_write_date }" pattern="yyyy.MM.dd"/></span>
	    				<c:if test="${!empty loginUser && loginUser.memberVo.m_no == comment.commentRVVo.m_no }">
							<a href="${pageContext.request.contextPath }/volunteer/review_comment_delete_process.do?crv_no=${comment.commentRVVo.crv_no}&rv_no=${result.reviewVolunteerVo.rv_no}">
								<input type="button" class="btn btn-sm btn-warning mt-3" style="float: right" value="삭제">
							</a>
						</c:if>
				</div>
				<div class="comment-content col">
					<span>${comment.commentRVVo.crv_content }</span>
				</div>
				<div class="col mt-2 border-bottom"></div>
			</c:forEach>
		</div>
		<div align="center" class="mb-5 mt-5">
			<a href="${pageContext.request.contextPath }/volunteer/review_page.do">
				<input type="button" class="btn btn-md btn-dark" value="목록으로 돌아가기">
			</a>
		</div>
		</div>
		<div class="col-1"></div>
		</div>
		
	</div>
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	<!-- 삭제 -->
	<form action="${pageContext.request.contextPath }/volunteer/review_delete_process.do" id="deleteVolunteer" method="post">
		<input type="hidden" name="rv_no" value="${result.reviewVolunteerVo.rv_no}">
	</form>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</body>
</html>