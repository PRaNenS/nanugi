<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/commons/commons.js"></script>
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet"> 
<title>MANAGER: NOTICE</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/manager/commons/global_nav.jsp" /> <%-- 헤더 네비게이션 --%>
	<jsp:include page="/WEB-INF/views/manager/commons/board_tap.jsp" />
	
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
			    	<span class="writedate">${result.date }</span>
		    	</div>	
	    		
		    </div>
		    <div class="row">
		    	<div class="col"> <hr></div>
		    </div>
		    <div class="row content-height">
		    	<div class="col">
		    		${result.noticeVo.bn_content}
		    	</div>
		    </div>	
		    <div class="row">
		    	<div class="col"><hr></div>
		    </div>   		 
	   		<div class="row">
				<div class="contentBtn col" align="right">
					<a href="${pageContext.request.contextPath}/manager/delete_content_process.do?bn_no=${result.noticeVo.bn_no}"><input type="button" class="btn btn-danger" value="삭제"></a>
					<a href="${pageContext.request.contextPath}/manager/manager_update_notice.do?bn_no=${result.noticeVo.bn_no }"><input type="button" class="btn btn-dark" value="수정"></a>
					<a href="${pageContext.request.contextPath }/manager/manager_notice_page.do"><input type="button" class="btn btn-dark" value="목록"></a>
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
	<jsp:include page="/WEB-INF/views/manager/commons/global_footer.jsp" /> <%-- 풋터 --%>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script></body>
</html>