function refresh() {
	var xmlhttp = new XMLHttpRequest();
	var eventNo = document.getElementById("event_no");
				
	xmlhttp.onreadystatechange = function() {
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var commentList = JSON.parse(xmlhttp.responseText);
			var commentContainer = document.getElementById("comment_container");
			var childCount = commentContainer.childNodes.length;
			
			for(var i = 0; i < childCount; i++) {
				commentContainer.childNodes[0].remove();
			}
			
			for(comment of commentList) {
				var loginUserNo = document.getElementById("login_user_no");
				var commentFormat = document.getElementById("comment_format").cloneNode(true);
				var writerProfile = commentFormat.getElementsByClassName("writer_profile");
				var writerNick = commentFormat.getElementsByClassName("writer_nick");
				var commentDate = commentFormat.getElementsByClassName("comment_date");
				var commentContent = commentFormat.getElementsByClassName("comment_content");
				var btnDeleteComment = commentFormat.getElementsByClassName("btn_delete_comment");

				commentFormat.setAttribute("class", "border-bottom py-3");
				writerProfile[0].setAttribute("src", comment.memberVo.m_profile_img_link);
				writerNick[0].innerText = comment.memberVo.m_nick;
				commentDate[0].innerText = comment.commentDate;
				commentContent[0].innerText = comment.commentBEVo.cbe_content;

				if(parseInt(loginUserNo.value) == parseInt(comment.memberVo.m_no)) {

					btnDeleteComment[0].setAttribute("onclick", "deleteComment(" + comment.commentBEVo.cbe_no + ")");
				
				}else {

					btnDeleteComment[0].remove();
				}
				
				commentFormat.removeAttribute("id");

				commentContainer.append(commentFormat);
			}
		}
	};
	
	xmlhttp.open("get", "../event/get_comment_list.do?be_no=" + eventNo.value);
	xmlhttp.send();
}

function writeComment() {
	var xmlhttp = new XMLHttpRequest();
	var eventNo = document.getElementById("event_no");
	var txtComment = document.getElementById("txt_comment");
		
	if(txtComment.value == "") {
		var btnCommentModal = document.getElementById("btn_comment_modal");

		btnCommentModal.click();

	}else {

		xmlhttp.onreadystatechange = function() {
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	
				txtComment.value = "";
				refresh();
			}
		};
					
		xmlhttp.open("post", "../event/write_comment_process.do");
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("be_no=" + eventNo.value + "&cbe_content=" + txtComment.value);
	}
}

function deleteComment(commentNo) {
	var xmlhttp = new XMLHttpRequest();
		
	xmlhttp.onreadystatechange = function() {
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			refresh();
		}
	};
				
	xmlhttp.open("post", "../event/delete_comment_process.do");
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("cbe_no=" + commentNo);
}