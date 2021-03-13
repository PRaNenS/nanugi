<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/event/read_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: EVENT</title>
</head>
<body onload="refresh()">

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
	<jsp:include page="/WEB-INF/views/user/event/commons/event_tap.jsp" /> <!-- 소식 탭 -->
	
	<div class="container my-5 mb-5 min-height">
		<!-- 이벤트명 -->
	 	<div class="row mt-3">
			<div class="col fw-bold fs-3 text-center">
				${eventData.bEventVo.be_title}
			</div>
		</div>
		
		<!-- 기관명 -->
		<div class="row py-3">
			<div class="col text-center text-secondary">
				<img src="${eventData.memberVo.m_profile_img_link }" style="width: 40px; height: 40px;">
				<span class="fw-bold">${eventData.memberVo.m_nick }</span>
			</div>
		</div>
		
		<!-- 이벤트 기간 -->
		<div class="row">
			<div class="col text-secondary text-center">
				<c:if test="${eventData.eventStatus == 1}"><span class="badge bg-warning text-dark">준비중</span></c:if>
       	<c:if test="${eventData.eventStatus == 2}"><span class="badge bg-success">진행중</span></c:if>
       	<c:if test="${eventData.eventStatus == 3}"><span class="badge bg-secondary">종료</span></c:if>
				${eventData.eventDateFrom } ~ ${eventData.eventDateTo }
				<span class="badge rounded-pill bg-danger">D-${eventData.dDay }</span>
			</div>
		</div>
	
		<!-- 썸네일 -->
		<div class="row mt-5">
			<div class="col">
				<div class="card h-100">
					<img src="${eventData.eventThumbnail.bei_img_link }" class="card-img-top">
				</div>
			</div>
		</div>
		
		<!-- 펀딩 하위 내용 -->
		<div class="row">
			<div class="col">
				<div class="bg-light px-5 py-5">
					<div class="fs-4 fw-bold">이벤트 안내</div>
					${eventData.bEventVo.be_content }
				</div>
			</div>
		</div>
		
		<!-- 버튼 -->
		<c:if test="${loginUser.memberVo.m_no == eventData.memberVo.m_no && eventData.eventStatus == 1 }">
			<div class="row my-3">
				<div class="col"></div>
				<div class="col-2">
					<button type="button" class="btn btn-danger form-control" data-bs-toggle="modal" data-bs-target="#staticBackdrop">이벤트 삭제</button>
				</div>
			</div>
		</c:if>
		
		<!-- 댓글 -->
		<div class="row mt-4">
			<div class="col fs-4 fw-bold">리뷰 Pick</div>
		</div>
		<c:if test="${eventData.eventStatus >= 2}">
			<div class="row">
				<div class="col">
					<div class="border-top border-start border-end bg-light px-2 py-2">
						<div class="row">
							<div class="col-2">
								<img src="${profileImgLink }" style="width: 40px; height: 40px;">
								<span class="fw-bold text-secondary">${loginUser.memberVo.m_nick }</span>
							</div>
							<div class="col">
								<div class="input-group">
									<textarea id="txt_comment" rows="4" class="form-control" aria-label="With textarea"></textarea>
									<button class="btn btn-outline-secondary" style="width: 100px;" onclick="writeComment()">작성</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		
		<div class="row">
			<div class="col">
				<div class="border-bottom"></div>
				<div id="comment_container"></div>
			</div>
		</div>
		
	</div>
				
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->

<!-- 필수정보 -->	
<input id="event_no" type="hidden" value="${eventData.bEventVo.be_no }">
<input id="login_user_no" type="hidden" value="${loginUser.memberVo.m_no }">
	
<!-- 댓글포맷 -->
<div id="comment_format" class="border-bottom py-3 d-none">
	<div class="row">
		<div class="col-2">
			<img class="writer_profile" src="" style="width: 40px; height: 40px;">
			<span class="fw-bold text-secondary writer_nick"></span>
		</div>
		<div class="col">
			<div class="row">
				<div class="col text-secondary comment_date"></div>
			</div>
			<div class="row">
				<div class="col comment_content"></div>
			</div>
		</div>
		<div class="col-1">
			<button class="form-control bg-danger text-white btn_delete_comment">삭제</button>
		</div>
	</div>
</div>

<!-- 경고창 -->
<!-- 댓글 미작성 -->
<button id="btn_comment_modal" type="button" class="btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#exampleModal">
  댓글 미작성 버튼
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Comment</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-danger">
        댓글을 입력 후, 작성 버튼을 눌러주세요
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- 이벤트 삭제 -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Delete Event</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-danger">
        이벤트를 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <a class="btn btn-danger" href="../event/delete_process.do?be_no=${eventData.bEventVo.be_no }">삭제</a>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>