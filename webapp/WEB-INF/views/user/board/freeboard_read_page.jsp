<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="${board.name}, 게시물 - ${article.title }" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/freeboard/freeboard_page.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/freeboard/read_page.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">
<title>NANUGI: COMMUNITY</title>
<style>
	body{
		
		padding-top: 80px;
	}
</style>

</head>
<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" />
<body>
	<div class="container">
		<div class="row">
			<div class="col-1"></div>
		<div class="col">
			<div class="row">
			<div class="head-wrapper col">
		        <div class="row mt-4">
		        	<div class="col">
		        		<span class="badge badge-pill badge-dark">${result.freeBoardHeadlineVo.mst_bfh_headline }</span>
			        </div>
		        </div>
	        </div>
	        <div class="row">
		        <div class="fs-1 fw-bold col">${result.freeBoardVo.bf_title }</div>
	    	</div>
	    	 <div class="row">
		    	<div class="col mt-3">
		    		<span class="username" style="font-weight: bold;">${result.memberVo.m_nick}</span>
			    	<span class="separator">・</span>
			    	<span class="writedate"><fmt:formatDate value="${result.freeBoardVo.bf_write_date }" pattern="yyyy.MM.dd"/></span>
		    	</div>	
		   
	    	<div class="col text-end">
		    		<c:if test="${!empty loginUser }">
						<c:choose>
							<c:when test="${empty result.likeVo }">
								<a href="${pageContext.request.contextPath }/board/freeboard_like_process.do?bf_no=${result.freeBoardVo.bf_no}">
									<input type="button" id="likeBtn" class="btn btn-dark" value="추천👍🏼"></a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath }/board/freeboard_like_cancel_process.do?bf_no=${result.freeBoardVo.bf_no}">
									<input type="button" id="likeCancelBtn" class="btn btn-danger" value="👍추천취소"></a>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${!empty result.hateVo }">
								<a href="${pageContext.request.contextPath }/board/freeboard_hate_cancel_process.do?bf_no=${result.freeBoardVo.bf_no}">
									<input type="button" id="hateCancelBtn" class="btn btn-danger" value="👎비추천취소">
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath }/board/freeboard_hate_process.do?bf_no=${result.freeBoardVo.bf_no }">
									<input type="button" id="hateBtn" class="btn btn-dark" value="비추천👎">
								</a>
							</c:otherwise>
						</c:choose>
					</c:if>
		    	</div>
		    	 </div>
		    
		    <div class="row">
		    	<div class="col"><hr></div>
		    </div>
	   		<div class="row">
				<div class="col">
					<!-- 이미지 출력 -->
					<c:forEach items="${result.BFImgLinkVo }" var="img">
						<img src="${img.bfil_path }" class="align-center" style="width: 900px; height: 100%; display: block; margin: 0 auto;">
					</c:forEach>
				</div>	   		
	   		</div>
	   		<div class="row mt-4">
			    <div class="content col mb-6">
			    	<div style="min-height: 300px;">${result.content }
					</div>
			    </div>
	   		</div>
	   		 
	   		<div class="row">
			    <div class="contentBtn col mt-5" align="right">
			    	<c:if test="${!empty loginUser && loginUser.memberVo.m_no == result.freeBoardVo.m_no }">
						<a href="${pageContext.request.contextPath }/board/freeboard_update_page.do?bf_no=${result.freeBoardVo.bf_no}">
							<input type="button" class="btn btn-secondary" value="수정"></a>
						<a href="#">
							<input type="button" class="btn btn-dark" value="삭제" onclick="deleteBoard()">
						</a>
					</c:if>
					<c:if test="${!empty loginUser }">
						<span class="cell">
							<a><button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#reportModal" onclick="check()">신고🚨</button></a>
						</span>
					</c:if>	
					<!-- 자유게시판 신고 모달 -->
					<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="reportModal">게시물 신고</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<div class="modal-body" id="box">
									<textarea id="report_content" rows="3" cols="50" placeholder="신고사유 입력(최대 500자)" required="required"></textarea>
									<div class="text-danger text-start" id="messageBox">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" id="closeBtn" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
									<input type="button" id="reportBtn" class="btn btn-primary" onclick="writeReport(${result.freeBoardVo.bf_no})" value="Save Reports">
								</div>
							</div>
						</div>						
					</div>
					<a href="${pageContext.request.contextPath }/board/freeboard_page.do"><input type="button" class="btn btn-dark" value="목록"></a>
			    </div>
	   		</div>
	   		<div class="row">
		    	<div class="col"><hr></div>
		    </div>
		    <!-- 댓글 start-->
		    <div class="row">
		    	<div class="col">
		    		 <div class="comment-write col mb-3">
				    	<form action="${pageContext.request.contextPath}/board/freeboard_comment_process.do?bf_no=${result.freeBoardVo.bf_no }" id="comment_submit" method="post">
					    	<div>
					    		<input type="hidden" name="bf_no" value="${result.freeBoardVo.bf_no }">
					    		<textarea name="cbf_content" id="comment_content" class="form-control" rows="2" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 댓글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
				    		</div>
				   			<div id="alert_comment" class="text-danger"></div>
				  	 	</form>
				    	<div align="right" class="mt-3">
				    		<input type="button" class="btn btn-sm btn-dark" onclick="ckComment()" value="댓글 작성">
				    	</div>
				   	</div>
		    	</div>
		    </div>
		    <div class="row">
		    	<div class="col"><hr></div>
		    </div>
		    <!-- 댓글 리스트 출력 -->
		     <div class="row">
		    	<div class="comment-list">
			    	<c:forEach items="${result.commentList}" var="comment">
			    		<div class="comment-info col">
			    			<span class="username" style="font-weight: bold;">${comment.memberVo.m_nick }</span>
			    			<span class="separator">・</span>
			    			<span class="date"><fmt:formatDate value="${comment.freeBoardCommentVo.cbf_write_date }" pattern="yyyy.MM.dd"/></span>
			    				<c:if test="${!empty loginUser && loginUser.memberVo.m_no == comment.freeBoardCommentVo.m_no }">
									<a href="${pageContext.request.contextPath }/board/freeboard_comment_delete_process.do?cbf_no=${comment.freeBoardCommentVo.cbf_no}&bf_no=${result.freeBoardVo.bf_no}">
										<input type="button" class="btn btn-sm btn-warning mt-3" style="float: right" value="삭제">
									</a>
			    				</c:if>
			    		</div>
			    		
			    		<div class="comment-content col">
			    			<span>${comment.freeBoardCommentVo.cbf_content }</span>
			    			<div class="col mt-2 border-bottom"></div>
			    		</div>
			    		</c:forEach>
				</div>
		    </div>
		      <div class="row pt-0" style="height: 10px">
		    	<div class="col"></div>
		    </div>
		    <div class="row">
		    	<div class="col">
		    		<div align="center" class="mb-5 mt-5">
						<a href="${pageContext.request.contextPath }/board/freeboard_page.do">
							<input type="button" class="btn btn-md btn-dark" value="목록으로 돌아가기">
						</a>
					</div>
		    	</div>
		    </div>
		</div>
		</div>
		<div class="col-1"></div>
    </div>
 </div><!-- container -->  
	    
	<form action="${pageContext.request.contextPath }/board/freeboard_delete_process.do" id="deleteBoard" method="post">
		<input type="hidden" name="bf_no" value="${result.freeBoardVo.bf_no}">
	</form>
	    	
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
  
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>