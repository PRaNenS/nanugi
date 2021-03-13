<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Banner -->
<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
		<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <c:if test="${banner2 != null }">
    	<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    </c:if>
    <c:if test="${banner3 != null}">
    	<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </c:if>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active banner-img" data-bs-interval="10000">
    	<c:choose>
    		<c:when test="${banner1 != null }">
    			<img src="${banner1.bei_img_link }" class="d-block w-100 h-100" alt="..." onclick="location.href='../event/read_page.do?be_no=${banner1.be_no}'" style="cursor: pointer;">
    		</c:when>
    		<c:otherwise>
    			<img src="<%=request.getContextPath() %>/resources/img/banner2.png" class="d-block w-100 h-100" alt="...">
    		</c:otherwise>
    	</c:choose>
    </div>
    <c:if test="${banner2 != null }">
    	<div class="carousel-item banner-img" data-bs-interval="10000">
      	<img src="${banner2.bei_img_link }" class="d-block w-100 h-100" alt="..." onclick="location.href='../event/read_page.do?be_no=${banner2.be_no}'" style="cursor: pointer;">
    	</div>
    </c:if>
    <c:if test="${banner3 != null }">
    	<div class="carousel-item banner-img" data-bs-interval="10000">
      	<img src="${banner3.bei_img_link }" class="d-block w-100 h-100" alt="..." onclick="location.href='../event/read_page.do?be_no=${banner3.be_no}'" style="cursor: pointer;">
    	</div>
    </c:if>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
  </button>
 	</div>
</div>