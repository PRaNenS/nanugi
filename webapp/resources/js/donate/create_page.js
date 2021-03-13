var titleFlg = false;
var dateFromFlg = false;
var dateToFlg = false;
var goalFlg = false;
var optionTitleFlg = false;
var contentFlg = false;
var warningTxt = "";

function refreshThumbnail() {
    var file = document.getElementById("file"); // 파일 업로드 id
    var thumnailImg = document.getElementById("thumnail_img"); // 썸네일 표시 영역
    var inputThumnail = document.getElementById("input_thumnail"); // 썸네일

    if(file.value) { // 업로드 프로필
        var xmlhttp = new XMLHttpRequest(); // http리퀘스트
        var formData = new FormData(); // 폼데이터 생성

        formData.append("file", file.files[0] ,file.files[0].name); // 폼데이터에 썸네일 추가

        xmlhttp.onreadystatechange = function() {

            // 이미지 수신 처리
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var obj = JSON.parse(xmlhttp.responseText); // 썸네일 정보
                var thumnailDir = obj.link; // 썸네일 이미지 경로
                
                // 요소 설정
                thumnailImg.setAttribute("src", thumnailDir);
                inputThumnail.setAttribute("value", thumnailDir);
            }
        };
    
        xmlhttp.open("post", "../donate/get_upload_thumbnail.do");
        xmlhttp.send(formData);

    }else { // 기본 프로필
        var defaultDir = "/resources/img/default_img.png";

        thumnailImg.setAttribute("src", defaultDir);
        inputThumnail.setAttribute("value", defaultDir);
    }
}


function createDonate() {
    var frmCreateDonate = document.getElementById("frm_create_donate");
    var btnModal = document.getElementById("btn_modal");
    var modalBody = document.getElementById("modal_body");

    warningTxt = "아래와 같은 문제가 발생하였습니다";

    ckTitle();
    ckDateFrom();
    ckDateTo();
    ckGoal();
    ckContent();

    if(titleFlg && dateFromFlg && dateToFlg && goalFlg && contentFlg) {
        var mst_dc_no = document.getElementById("input_mst_dc_no");
        var mst_d_title = document.getElementById("input_mst_d_title");
        var mst_d_date_from = document.getElementById("input_mst_d_date_from");
        var mst_d_date_to = document.getElementById("input_mst_d_date_to");
        var mst_d_goal = document.getElementById("input_mst_d_goal");
        var mst_d_content = document.getElementById("input_mst_d_content");

        mst_dc_no.setAttribute("value", document.getElementById("input_category").value);
        mst_d_title.setAttribute("value", document.getElementById("input_title").value);
        mst_d_date_from.setAttribute("value", document.getElementById("input_date_from").value);
        mst_d_date_to.setAttribute("value", document.getElementById("input_date_to").value);
        mst_d_goal.setAttribute("value", document.getElementById("input_goal").value);
        mst_d_content.setAttribute("value", document.getElementById("input_content").value);

        frmCreateDonate.submit();

    }else {

        modalBody.innerText = warningTxt;
        btnModal.click();
    }
}

function ckTitle() {
    var inputTitle = document.getElementById("input_title");

    if(inputTitle.value == "") {

        titleFlg = false;
        warningTxt += "\n기부명을 입력해주세요";

    }else {

        titleFlg = true;
    }
}

function ckDateFrom() {
    var inputDateFrom = document.getElementById("input_date_from");

    if(inputDateFrom.value == "") {
        
        dateFromFlg = false;
        warningTxt += "\n시작 기부기간을 입력해주세요";

    }else {
        var today = new Date();
        var dateFrom = new Date(inputDateFrom.value);

        if(dateFrom <= today) {

            dateFromFlg = false;
            warningTxt += "\n시작 기부기간은 오늘이후의 날짜를 선택해주세요";

        }else {

            dateFromFlg = true;
        }
    }
}

function ckDateTo() {
    var inputDateTo = document.getElementById("input_date_to");

    if(inputDateTo.value == "") {
        
        dateToFlg = false;
        warningTxt += "\n종료 기부기간을 입력해주세요";

    }else {
        var dateFrom = new Date(document.getElementById("input_date_from").value);
        var dateTo = new Date(inputDateTo.value);

        if(dateTo <= dateFrom) {

            dateToFlg = false;
            warningTxt += "\n종료 기부기간은 시작 기부기간보다 이후의 날짜를 선택해주세요";

        }else {

            dateToFlg = true;
        }
    }
}


function ckGoal() {
    var inputGoal = document.getElementById("input_goal");
    var regExp = /[0-9]{1,38}/;

    if(!regExp.test(inputGoal.value)) {

        goalFlg = false;
        inputGoal.value = 0;
        warningTxt += "\n목표 포인트를 입력해주세요";

    }else {

        if(parseInt(inputGoal.value) <= 0) {

            goalFlg = false;
            warningTxt += "\n목표 포인트는 0이상 입력해주세요";

        }else {

            goalFlg = true;
        }
    }
}


function ckContent() {
    var inputContent = document.getElementById("input_content");

    if(inputContent.value == "") {

        contentFlg = false;
        warningTxt += "\n소개글을 입력해주세요";

    }else {

        contentFlg = true;
    }
}