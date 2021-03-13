<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top border-bottom" style="background-color: #FFFFFF ">
  <div class="container">
		<a href="../main/main_page.do"><img  style="width: 150px;" src="<%=request.getContextPath() %>/resources/img/main_Logo.png"></a>
    <ul class="navbar-nav ms-auto me-auto mb-2 mb-lg-0">
	    <li class="nav-item">
	      <a class="nav-link fs-5 fw-bold text-center" href="../notice/list_page.do" style="width: 10rem;">소식</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link fs-5 fw-bold text-center" href="../donate/list_page.do" style="width: 10rem;">기부</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link fs-5 fw-bold text-center" href="../funding/list_page.do" style="width: 10rem;">펀딩</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link fs-5 fw-bold text-center" href="../volunteer/list_page.do" style="width: 10rem;">봉사</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link fs-5 fw-bold text-center" href="../board/freeboard_page.do" style="width: 10rem;">게시판</a>
	    </li>
	    <c:if test="${loginUser.memberVo.mst_mt_no==1 }">
	    	<li class="nav-item">
	      <a class="nav-link fs-5 fw-bold text-center" href="../manager/manager_page.do" style="width: 10rem;">관리자</a>
	    </li>
	    </c:if>
    </ul>
    
    <div class="d-flex" style="width: 15rem;">
      <ul class="navbar-nav ms-auto">
      	<c:if test="${!empty loginUser }">
	      	<li class="nav-item">
	      		<a href="../mypage/update_user_page.do">
	      			<img src="${profileImgLink }" class="rounded" style="width: 40px; height: 40px;">
	      		</a>
	      	</li>
      	</c:if>
      	<li class="nav-item">
       		<c:choose>
			    	<c:when test="${!empty loginUser }">
			    		<a class="nav-link active" href="../mypage/point_page.do">${loginUser.memberVo.m_nick }</a>
			    	</c:when>
			    	<c:otherwise>
			    		<a class="nav-link active" href="../member/login_page.do">비회원</a>
			    	</c:otherwise>
			    </c:choose>
				</li>
				<li class="nav-item">
					<c:choose>
  					<c:when test="${!empty loginUser }">
	  					<a class="nav-link" href="../member/logout_process.do">로그아웃</a>
	  				</c:when>
	  				<c:otherwise>
	  					<a class="nav-link" href="../member/login_page.do">로그인</a>
	  				</c:otherwise>
	  			</c:choose>
				</li>
			</ul>
    </div>
	</div>
</nav>
