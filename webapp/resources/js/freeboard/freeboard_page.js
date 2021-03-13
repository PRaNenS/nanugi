
function writeReport(contentNo) {
    console.log(contentNo);
		
    var reportContent = document.getElementById("report_content");
    
    var messageBox = document.getElementById("messageBox");
    messageBox.innerText = "";

    if(reportContent.value == "")
        {
            messageBox.innerText = "내용을 입력해주세요";
        }
        else
        {
            var xmlhttp = new XMLHttpRequest();
    
            xmlhttp.onreadystatechange = function() {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
                    
                    var closeBtn = document.getElementById("closeBtn");

                    closeBtn.click();

                }
            };
            
            xmlhttp.open("post", "./report_process.do");
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("bf_no=" + contentNo + "&rbf_content=" + reportContent.value);
             
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

function check()
{	
    var content = document.getElementById("report_content");
    content.value = "";
   
}




