<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-lg-2">
	<div id="profile_wrap" class="pt-4 pb-4 border-bottom" style="height: 200px; background-color: #848891;">
		<div class="mx-auto" style="height: 100px; width: 100px;">
			<img class="w-100 h-100 rounded" src="${profileImgLink }">
		</div>
		<div class="text-white text-center">${loginUser.memberVo.m_nick }님</div>
		<div class="text-white text-center">${loginUser.mstMTypeVo.mst_mt_type }</div>
	</div>
	<div id="summary_wrap" class="pt-4 pb-4 ps-3 pe-3" style="height: 120px; background-color: #848891;">
		<div class="text-white text-center mt-2 mb-2">보유포인트</div>
		<div class="text-white text-center mt-2 mb-2">${mPointVo.mp_point } P</div>
	</div>
	<div id="side_bar" class="border ps-3 pe-3 pt-4 pb-4" style="background-color: #FAFAFA;">
		<div class="fs-6">
			<a href="../mypage/point_page.do" class="none-underline text-secondary">포인트</a>
		</div>
		<hr>
		<div class="fs-6">
			<a href="../mypage/update_user_page.do" class="none-underline text-secondary">정보 수정</a>
		</div>
		<hr>
		<div class="fs-6">
			<c:if test="${loginUser.memberVo.mst_mt_no == 3 }">
				<a href="../mypage/funding_page.do" class="none-underline text-secondary">펀딩 내역</a>	
			</c:if>
			<c:if test="${loginUser.memberVo.mst_mt_no == 2 }">
				<a href="../mypage/order_page.do" class="none-underline text-secondary">주문 내역</a>
			</c:if>
		</div>
		<hr>
		<c:choose>
			<c:when test="${loginUser.memberVo.mst_mt_no == 2}">
				<div class="fs-6">
					<a href="../mypage/join_page.do" class="none-underline text-secondary">봉사 내역</a>	
				</div>
			</c:when>
			<c:otherwise>
				<div class="fs-6">
					<a href="../mypage/volunteer_page.do" class="none-underline text-secondary">봉사 내역</a>	
				</div>
			</c:otherwise>
		</c:choose>
	
	</div>
</div>