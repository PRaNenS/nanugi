var titleFlg = false;
var contentFlg = false;

function writenotice()
{
	var frmWrite = document.getElementById("form");
	
	ckTitle();
	ckContent();
	
	if(titleFlg&&contentFlg==true)
	{
		frmWrite.submit();
	}
}


function ckTitle()
{
	var input = document.getElementById("title");
	var alertBox = document.getElementById("alert_title");
	
	
	 alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="제목을 입력해주세요";
	 	titleFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	titleFlg = true;
	 }
}

function ckContent()
{
	var input = document.getElementById("content");
	var alertBox = document.getElementById("alert_content");
	
	alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="내용을 입력해주세요";
	 	contentFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	contentFlg = true;
	 }
}