<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="./main_page.do">DONAGI</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
      <h3>DONAGI</h3>
      <ul class="nav-item dropdown">
		    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">
			    <c:choose>
		      		<c:when test="${!empty loginUser }">
		      			${loginUser.memberVo.m_nick }
		      		</c:when>
		      		<c:otherwise>
		      			비회원
		      		</c:otherwise>
		      	</c:choose>
		    </a>
		    <li class="dropdown-menu">
		    	<c:choose>
		    		<c:when test="${!empty loginUser }">
		    			<li><a class="dropdown-item" href="../member/logout_process.do">로그아웃</a></li>
		    		</c:when>
		    		<c:otherwise>
		    			<li><a class="dropdown-item" href="../member/login_page.do">로그인</a></li>
		    		</c:otherwise>
		    	</c:choose>
		    </li>
	    </ul>
  </div>
</nav>
<div class="container-fluid">
	<div class="row">
	 	<div class="col">
	 		이미지 공간
	 	</div>
	</div>
</div>
<ul class="nav justify-content-center nav-tabs">
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">통계</a>
    <ul class="dropdown-menu">
      <li><a class="dropdown-item text-center" href="#">일일 접속자 수</a></li>
      <li><a class="dropdown-item text-center" href="#">일일 게시글 수 </a></li>
      <li><a class="dropdown-item text-center" href="#"></a></li>
    </ul>
  </li>
  <li class="nav-item dropdown">
    <a class="nav-link "  href="../manager/manager_notice_page.do" role="button" aria-expanded="false">공지사항</a>
  </li>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">신고</a>
    <ul class="dropdown-menu">
      <li><a class="dropdown-item text-center" href="#">기부</a></li>
      <li><a class="dropdown-item text-center" href="#">봉사</a></li>
      <li><a class="dropdown-item text-center" href="#">펀딩</a></li>
    </ul>
  </li>
   <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">유저 관리</a>
    <ul class="dropdown-menu">
      <li><a class="dropdown-item text-center" href="../manager/manager_update_user.do">기관 유저 신청</a></li>
    </ul>
  </li>
</ul>