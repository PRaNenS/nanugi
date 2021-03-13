

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


function deleteBoard(){
	result = confirm('삭제하시겠습니까?');

	if(result == true){
		var deleteBoard = document.getElementById("deleteBoard");
		deleteBoard.submit();


	}else{
		return false;

	}
}