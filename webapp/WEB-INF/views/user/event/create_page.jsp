<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/resources/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/event/create_page.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>NANUGI: EVENT</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/user/commons/global_nav.jsp" /> <!-- 헤더 네비게이션 -->
	<jsp:include page="/WEB-INF/views/user/event/commons/event_tap.jsp" /> <!-- 소식 탭 -->
	
	<!-- 타이틀 -->
	<div style="background-image: url('<%=request.getContextPath()%>/resources/img/background.png');">
		<div class="container mb-4">
			<table style="height: 100px;">
				<tbody>
					<tr>
						<td class="align-middle"><h2>이벤트 개설</h2></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="container mb-5 min-height">
		<div class="row">
			<div class="col">
				<div class="row">
					<!-- 이미지 부분 -->
					<div class="col-8">
						<div class="row">
							<div class="col">
								<div id="thumnail_container" class="card">
									<img id="thumnail_img" class="card-img-top w-100" src="<%=request.getContextPath() %>/resources/img/default_img.png">
									<div class="card-body">
										<input id="file" type="file" class="form-control" accept="image/*" onchange="refreshThumbnail()">
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col">					 	
					 	<div class="row mt-3"> <!-- 이벤트명 -->
							<div class="col">
								<h5>이벤트명</h5>
							  <input id="input_title" type="text" class="form-control">
							</div>
						</div>
						<div class="row mt-3"><!-- 이벤트 기간  -->
							<div class="col">
								<div class="row">
									<div class="col">
										<h5>이벤트  기간</h5>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="input-group">
											<input id="input_date_from" type="date" class="form-control">
											<span class="input-group-text">~</span>
											<input id="input_date_to" type="date" class="form-control">
										</div>
									</div>
								</div>
							</div>
						</div>																		
					</div>					
				</div>

				<!-- 내용 -->
				<div class="row mt-3">
					<div class="col">
						<h5>소개글</h5>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<textarea id="input_content" class="form-control" rows="10"></textarea>
					</div>
				</div>
				<div class="row mt-3 mb-4">
					<div class="col"></div>
					<div class="col-2">
						<button class="btn btn-outline-primary form-control" onclick="create()">개설</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/user/commons/global_footer.jsp" /> <!-- 풋터 -->
	
<form id="frm_create_event" action="../event/create_process.do">
	<input id="input_thumnail" name="bei_img_link" type="hidden" value="<%=request.getContextPath() %>/resources/img/default_img.png">
	<input id="input_be_title" name="be_title" type="hidden">
	<input id="input_be_date_from" name="be_date_from" type="hidden">
	<input id="input_be_date_to" name="be_date_to" type="hidden">
	<input id="input_be_content" name="be_content" type="hidden">
</form>
	
<!-- Button trigger modal -->
<button id="btn_modal" type="button" class="btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div id="modal_body" class="modal-body text-danger">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>