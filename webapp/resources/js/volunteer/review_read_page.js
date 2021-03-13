
function writeReport(contentNo) {
    var reportContent = document.getElementById("report_content");
    var messageBox = document.getElementById("messageBox");
    messageBox.innerText = "";

    if(reportContent.value == ""){
        messageBox.innerText = "내용을 입력해주세요";
    }else{
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            var closeBtn = document.getElementById("closeBtn");
            closeBtn.click();

        }
    };  
    
    xmlhttp.open("post", "./report_process.do");
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("rv_no=" + contentNo + "&rerv_content=" + reportContent.value);        
    }
}


function writeCommentReport() {
    var cReportContent = document.getElementById("comment_report_content").value;
    var cNo = document.getElementById("cbf_no").value;
    var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function() {
        if(xmlhttp.readyStat == 4 && xmlhttp.status == 200){
        }
    };
    
    xmlhttp.open("post", "${pageContext.request.contextPath}/freeboard/comment_report_process.do");
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("cbf_no=" + cNo + "&cr_content=" + cReportContent);
}

function checkRV(){	
    var content = document.getElementById("report_content");
    content.value = "";
   }


// 작성버튼
function ckComment(){
	var input = document.getElementById("comment_content");
	var alertBox = document.getElementById("alert_comment");
	
	alertBox.innerText = "";
	 
	if(input.value == ""){
	 	alertBox.innerText="댓글을 입력해주세요";
	}
     
    else{
        var comment = document.getElementById("comment_submit");
        comment.submit();
    
        input.value = "";
    }
    

}

function deleteVolunteer(){
	result = confirm('삭제하시겠습니까?');

	if(result == true){
		var deleteVolunteer = document.getElementById("deleteVolunteer");
		deleteVolunteer.submit();


	}else{
		return false;
	}
}