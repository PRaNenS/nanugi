<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
  <div class="container">
		<h2 style="width: 300px;"><a class="none-underline" href="../manager/manager_page.do">ADMIN</a></h2>
    <ul class="navbar-nav ms-auto me-auto mb-2 mb-lg-0">
	    <li class="nav-item">
	      <a class="nav-link fs-5 fw-bold" href="../manager/manager_notice_page.do" style="width: 100px;">게시판</a>
	    </li>
	    <li class="nav-item dropdown">
	      <a class="nav-link fs-5 fw-bold" href="../manager/freeboard_list.do" style="width: 100px;">신고</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link fs-5 fw-bold" href="../manager/member_list.do" style="width: 100px;">유저관리</a>
	    </li>
    </ul>
    
    <div class="d-flex" style="width: 300px;">
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
			    		<a class="nav-link active" href="#">${loginUser.memberVo.m_nick }</a>
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
