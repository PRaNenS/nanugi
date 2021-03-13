function refresh() {

    refreshThumbnail();
}

function refreshThumbnail() {
    var xmlhttp = new XMLHttpRequest(); // http리퀘스트
    var file = document.getElementById("file"); // 파일 업로드 id
    var formData = new FormData(); // 폼데이터 생성

    formData.append("file", file.files[0] ,file.files[0].name); // 폼데이터에 썸네일 추가

    xmlhttp.onreadystatechange = function() {

        // 이미지 수신 처리
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var obj = JSON.parse(xmlhttp.responseText); // 썸네일 정보
            var thumbnailDir = obj.link; // 썸네일 이미지 경로
            var thumbnailContainer = document.getElementById("thumnail_container"); // 썸네일 표시 영역
            var thumbnail = document.getElementById("thumbnail"); // 썸네일
            var inputDir = document.getElementById("input_Container"); // 썸네일 경로

            // 썸네일 표시 초기화
            if(thumbnailContainer.childNodes[0] != undefined) {

                thumbnailContainer.childNodes[0].remove();
            }
                
            // 요소 설정
            thumbnail.setAttribute("src", thumbnailDir);
            thumbnail.setAttribute("value",thumbnailDir);
            inputDir.setAttribute("name", "mst_vi_img_link");
            inputDir.setAttribute("type", "hidden");
            inputDir.setAttribute("value", thumbnailDir);

            // 요소 위치 설정
            thumbnailContainer.append(inputDir);
        }
    };

    xmlhttp.open("post", "./get_upload_thumbnail.do");
	xmlhttp.send(formData);
}

function add_option()
{
    var div = document.getElementById("option_add").cloneNode(true);		
    div.setAttribute("class","row mt-2 delete-target");
    div.removeAttribute("id");
    document.getElementById("option_List").appendChild(div);
}

function remove_item(data)
{
    var row = data.closest(".delete-target");
    row.remove();
}

var titleFlg = false;
var fromFlg = false;
var toFlg = false;
var dateFlg = false;
var timeFlg = false;
var placeFlg = false;
var goalFlg = false;
var contentFlg = false;
var imgFlg = false;


function createVolunteer()
{
	var frmCreate = document.getElementById("frm_create");
	
	ckTitle();
	ckDatefrom();	
	ckDateto();	
	ckGoal();	
	ckPlace();	
	ckDate();	
	ckTime();	
	ckContent();
	ckImg();
	
    if(titleFlg && fromFlg && toFlg && dateFlg && timeFlg && placeFlg && goalFlg && imgFlg && contentFlg) 
    {
        frmCreate.submit();
    }
}

function ckImg()
{
	var input = document.getElementById("input_Container");
	var alertBox = document.getElementById("alert_img");
	
	 alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="이미지를 업로드해주세요";
	 	imgFlg = false;
	 }
	 else
	 {
	 	imgFlg = true;
	 }
}

function ckTitle()
{
	var input = document.getElementById("input_title");
	var alertBox = document.getElementById("alert_title");
	
	
	 alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="봉사활동명을 입력해주세요";
	 	titleFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	titleFlg = true;
	 }
}

function getTimeStamp() {

    var d = new Date();
    var s =
        leadingZeros(d.getFullYear(), 4) + '-' +
        leadingZeros(d.getMonth() + 1, 2) + '-' +
        leadingZeros(d.getDate(), 2);

    return s;
}

function leadingZeros(n, digits) {

    var zero = '';
    n = n.toString();

    if (n.length < digits) {
        for (i = 0; i < digits - n.length; i++)
            zero += '0';
    }
    return zero + n;
}

function ckDatefrom()
{
	var input = document.getElementById("input_from");
	var alertBox = document.getElementById("alert_from");
	var today = getTimeStamp();
	
	 alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="모집날짜를 입력해주세요";
	 	fromFlg = false;
	 	input.focus();
	 }
	  else if(input.value < today)
	 {
	 	alertBox.innerText="다른 날짜를 입력해주세요";
	 	toFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	fromFlg = true;
	 }
}

function ckDateto()
{
	var from = document.getElementById("input_from");
	var input = document.getElementById("input_to");
	var alertBox = document.getElementById("alert_to");
	
	 alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="모집날짜를 입력해주세요";
	 	toFlg = false;
	 	input.focus();
	 }
	 else if(input.value < from.value)
	 {
	 	alertBox.innerText="다른 날짜를 입력해주세요";
	 	toFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	toFlg = true;
	 }
}

function ckGoal()
{
	var input = document.getElementById("input_goal");
	var alertBox = document.getElementById("alert_goal");
	var regExp = /^\d{2}$/;
	
	alertBox.innerText = "";
	
	 if(!regExp.test(input.value))
	 {
	 	alertBox.innerText="100명 미만으로 입력해주세요";
	 	goalFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	goalFlg = true;
	 }
}

function ckPlace()
{
	var input = document.getElementById("input_place");
	var alertBox = document.getElementById("alert_place");
	
	alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="장소를 입력해주세요";
	 	placeFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	placeFlg = true;
	 }
}

function ckDate()
{
	var input = document.getElementById("input_date");
	var alertBox = document.getElementById("alert_date");
	var regExp =  /(^(19|20)\d{2})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/; 

	
	
	 alertBox.innerText = "";
	 
	 if(!regExp.test(input.value))
	 {
	 	alertBox.innerText="날짜형식에 맞춰 입력해주세요";
	 	dateFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	dateFlg = true;
	 }
}

function ckTime()
{
	var input = document.getElementById("input_time");
	var alertBox = document.getElementById("alert_time");
	
	 alertBox.innerText = "";
	 
	 if(input.value == "")
	 {
	 	alertBox.innerText="시간대를 입력해주세요";
	 	timeFlg = false;
	 	input.focus();
	 }
	 else
	 {
	 	timeFlg = true;
	 }
}

function ckContent()
{
	var input = document.getElementById("input_content");
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

