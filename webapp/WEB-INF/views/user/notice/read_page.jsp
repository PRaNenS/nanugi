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
<!-- bootstrap, font awesome -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/notice/read_page.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript">
	
	var bn_no = ${result.noticeVo.bn_no};
	var m_no = ${result.memberVo.m_no};
	
	function changeHeart()
	{
		$.ajax({
			 type : "POST", 
			 url : "${pageContext.request.contextPath}/board/recommendProcess.do",
			 //dataType : "json",   
	         data : "bn_no="+bn_no+"&m_no="+m_no,
	         success : function(data)
	         {
	        	 if(data.result == 1)
	       		 {
	        		 $("#btn_recommend").attr("src","/resources/img/colored.png");
	       		 }
	        	 else if(data.result == 0)
	       		 {
	        		 $("#btn_recommend").attr("src","/resources/img/empty.png");
	       		 }
	         }
		});
	}

</script>
<title>NANUGI: NOTICE</title>
</head>
<body>
	<%-- 이벤트 상세 페이지 --%>
	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더네비게이션 -->
	<jsp:include page="/WEB-INF/views/user/event/commons/event_tap.jsp" />

	<div class="container my-5 mb-5 min-height">
			<div class="row">
			<div class="col-1"></div>
		<div class="col">
			<div class="row">
		        <div class="row">
			        <div class="fs-1 fw-bold col">${result.noticeVo.bn_title}</div>
		    	</div>
		    </div>
		    <div class="rowmt-3">
		    	<div class="col text-end">
		    		<span class="username" style="font-weight: bold;">${result.memberVo.m_nick}</span>
			    	<span class="separator">・</span>
			    	<span class="writedate"><fmt:formatDate value="${result.noticeVo.bn_write_date}" pattern="yyyy.MM.dd"/></span>
		    	</div>	
	    		
		    </div>
		    <div class="row">
		    	<div class="col"> <hr></div>
		    </div>
		    <div class="row" style="min-height: 200px;">
		    	<div class="col">
		    		${result.noticeVo.bn_content}
		    	</div>
		    </div>	
		    <div class="row">
		    	<div class="col"><hr></div>
		    </div>   		 
	   		<div class="row">
	   			<%-- <div class="col">
		    		<c:if test="${!empty loginUser }">
						<c:choose>
							<c:when test="${!empty result.noticeRecommendVo }">
								<img class="small" onclick="changeHeart()" id="btn_recommend" src="${pageContext.request.contextPath }/resources/img/colored.png" alt="" width="40" height="40">
							</c:when>
							<c:otherwise>
								<img class="small" onclick="changeHeart()" id="btn_recommend" src="${pageContext.request.contextPath }/resources/img/empty.png" alt="" width="40" height="40">
							</c:otherwise>
						</c:choose>
					</c:if>
		    	</div> --%>
			    <div class="contentBtn col" align="right">
					<a href="${pageContext.request.contextPath }/notice/list_page.do"><input type="button" class="btn btn-dark" value="목록"></a>
			    </div>
	   		</div>
	   		
		    <!-- 댓글 start-->
	      	<div class="row pt-0" style="height: 10px">
		    	<div class="col"></div>
		    </div>
		</div>
		<div class="col-1"></div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>