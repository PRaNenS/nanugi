<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">

<script type="text/javascript">
function report(){
		
		
		var rerfContent = document.getElementById("rerf_content");
		var rfNo = ${map.reviewFundingVo.rf_no};
		
		var reportContent = document.getElementById("report_content");
		var rerfContentFLG = false;
		
		reportContent.innerText = "";
		
		if(rerfContent.value == ""){
			
			rerfContentFLG = false;
			
			reportContent.innerText = "내용을 입력해주세요";
			
		}else{
			rerfContentFLG = true;
			
			var xmlhttp = new XMLHttpRequest();
			
			xmlhttp.onreadystatechange = function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					//...
					var close = document.getElementById("closeBtn");
					
					close.click();
				}	
			};
		}
		
		xmlhttp.open("post","../review_funding/report_process.do");
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("rf_no=" + rfNo + "&rerf_content=" + rerfContent.value);
		
		document.getElementById("rerf_content").value = "";
		
		
}

function deletefunding(){
	result = confirm('삭제하시겠습니까?');

	if(result == true){
		var deletefunding = document.getElementById("deletefunding");
		deletefunding.submit();


	}else{
		return false;
	}
}

function commentRF(){
	
	var comment = document.getElementById("comment");
	var content = document.getElementById("content");
	var commentContent = document.getElementById("comment_content");
	var contentFLG = false;
	
	commentContent.innerText = "";
	
	if(content.value == ""){
		contentFLG = false;
		
		commentContent.innerText = "댓글을 작성해주세요";
		
	}else{
		contentFLG = true;
		
	}
	
	if(contentFLG){
		comment.submit();
	}
	
	
}

</script>


<title>NANUGI: FUNDING REVIEW</title>
<style>

	body{
		padding-top: 20px;
	}
	
	
</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더네비게이션 -->
	<br>
	<br>
	<div class="container">
	<div class="row">
	<div class="col-1"></div>
	<div class="col">
	<div class="row">
		<div class="fs-1 fw-bold col mt-5">
			${map.reviewFundingVo.rf_title }</div>
			
		<div class="row">
		<div class="info col-6 mt-2">
			<span class="username" style="font-weight: bold;">${map.memberVo.m_nick }</span>
			<span class="separator">・</span>
			<span class="date"><fmt:formatDate value="${map.reviewFundingVo.rf_write_date }" pattern="yyyy.MM.dd."/></span>
			<span class="starrate">
				<c:choose>
					<c:when test="${map.reviewFundingVo.rf_score == 1}">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${map.reviewFundingVo.rf_score == 2}">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${map.reviewFundingVo.rf_score == 3}">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${map.reviewFundingVo.rf_score == 4}">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
					<c:when test="${map.reviewFundingVo.rf_score == 5}">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
						<img src="<%=request.getContextPath() %>/resources/img/star.png" style="height: 15px; width: 15px">
					</c:when>
				</c:choose>
			</span>
		</div>
		<div class="text-end col">
				<c:if test="${!empty loginUser }">
			    	<c:choose>
						<c:when test="${!empty map.resultReCommendVo }">
							<a href="../review_funding/delete_recommend_process.do?rf_no=${map.reviewFundingVo.rf_no }">
							<input type="button" class="btn btn-danger" value="👍추천취소">
							</a>
						</c:when>
						<c:otherwise>
							<a href="../review_funding/insert_recommend_process.do?rf_no=${map.reviewFundingVo.rf_no }">
							<input type="button" class="btn btn-dark" value="추천👍🏼">
							</a>
						</c:otherwise>
					</c:choose>
					</c:if>
			</div>
			</div>
			
			</div>
		<hr>
		<div class="content col-mb-6">
			<div style="min-height: 400px;">${map.contentRf }</div>
		</div>
		<div class="contentBtn" align="right">
			<c:if test="${!empty loginUser && loginUser.memberVo.m_no == map.reviewFundingVo.m_no }">
				<a href="../review_funding/update_page.do?rf_no=${map.reviewFundingVo.rf_no }">
					<input type="button" class="btn btn-secondary" value="수정"></a>
				
				<a href="#">
				<input type="button" class="btn btn-dark" value="삭제" onclick="deletefunding()">
				</a>
			</c:if>
			<c:if test="${!empty loginUser }">
				<span class="cell">
					<a><button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#reportModal">신고🚨</button></a>
				</span>
			</c:if>
			<!-- 봉사활동 후기 신고 모달 -->
			<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header" >
							<h5 class="modal-title" id="reportModal">게시물 신고</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body" id="box">
							<textarea id="rerf_content" rows="3" cols="50" placeholder="신고사유 입력(최대 500자)" required="required"></textarea>
							<div id="report_content" class="text-danger text-start"></div>
						</div>
						<div class="modal-footer">
							<button type="button" id="closeBtn" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<input type="button" id="reportBtn" class="btn btn-primary" onclick="report()" value="Save Reports">
						</div>
					</div>
				</div>						
			</div>
			<c:choose>
	    		<c:when test="${!empty loginUser && loginUser.memberVo.mst_mt_no == 1 }">
	    			<a href="../manager/report_page.do"><input type="button" class="btn btn-dark" value="신고목록"></a>
	    		</c:when>
	    		<c:otherwise>
	    			<a href="../review_funding/list_page.do"><input type="button" class="btn btn-dark" value="목록"></a>
	    		</c:otherwise>
    		</c:choose>
		</div>
		<hr>
		<div class="comment-write col mb-3">
			<form action="../review_funding/write_comment_process.do?rf_no=${map.reviewFundingVo.rf_no }" id="comment" method="post">
				<c:if test="${!empty loginUser }">
		    		<div>
		    			<input type="hidden" name="rf_no" value="${map.reviewFundingVo.rf_no }">
		    			<textarea name="crf_content" id="content" class="form-control" rows="2" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 댓글 작성 시 타인에 대한 배려와 책임을 담아주세요."></textarea>
	    				<div id="comment_content" class="text-danger"></div>
	    			</div>
	    	</form>
	    		<div align="right" class="mt-2">
	    			<input type="button" class="btn btn-sm btn-dark" value="댓글 작성" onclick="commentRF()">
	    		</div>
	    		</c:if>
		</div>
		<hr>
		<div class="comment-list col mb-3">
			<c:forEach items="${Commentmap}" var="comment">
				<div class="comment-info col mt-1">
					<span class="username" style="font-weight: bold;">${comment.memberVo.m_nick }</span>
					<span class="separator">・</span>
	    			<span class="date"><fmt:formatDate value="${comment.commentRFVo.crf_write_date }" pattern="yyyy.MM.dd"/></span>
	    				<c:if test="${!empty loginUser && loginUser.memberVo.m_no == comment.commentRFVo.m_no }">
							<a href="../review_funding/delete_comment_process.do?crf_no=${comment.commentRFVo.crf_no }&rf_no=${map.reviewFundingVo.rf_no}">
								<input type="button" class="btn btn-sm btn-warning mt-3" style="float: right" value="삭제">
							</a>
						</c:if>
				</div>
				<div class="comment-content col">
					<span>${comment.commentRFVo.crf_content}</span>
				</div>
				<div class="col mt-2 border-bottom"></div>
			</c:forEach>
		</div>
		<div align="center" class="mb-5 mt-5">
			<a href="../review_funding/list_page.do">
				<input type="button" class="btn btn-md btn-dark" value="목록으로 돌아가기">
			</a>
		</div>
		</div>
		<div class="col-1"></div>
	</div>	
	</div>

<form action="../review_funding/delete_process.do" id="deletefunding" method="post">
		<input type="hidden" name="rf_no" value="${map.reviewFundingVo.rf_no}">
	</form>

	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <%-- 풋터 --%>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>