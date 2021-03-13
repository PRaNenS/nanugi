var titleFlg = false;
var dateFromFlg = false;
var dateToFlg = false;
var dateEndFlg = false;
var goalFlg = false;
var optionTitleFlg = false;
var optionPriceFlg = false;
var contentFlg = false;
var warningTxt = "";

function refresh() {

    onloadOption();
}

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
    
        xmlhttp.open("post", "../funding/get_upload_thumbnail.do");
        xmlhttp.send(formData);

    }else { // 기본 프로필
        var defaultDir = "/resources/img/default_img.png";

        thumnailImg.setAttribute("src", defaultDir);
        inputThumnail.setAttribute("value", defaultDir);
    }
}

function onloadOption() {
    var optionFormat = document.getElementById("option_add").cloneNode(true);
    var optionContainer = document.getElementById("option_List");
	var btnDelete = optionFormat.getElementsByClassName("btn-delete");
    var inputTitle = optionFormat.getElementsByClassName("input_title");
    var inputPrice = optionFormat.getElementsByClassName("input_price");

    btnDelete[0].remove();
    inputTitle[0].setAttribute("class", "form-control option_title");
    inputPrice[0].setAttribute("class", "form-control option_price text-end");
	optionFormat.setAttribute("class","row mt-2 delete-target");
    optionFormat.removeAttribute("id");
	optionContainer.appendChild(optionFormat);
}

function addOption() {
    var optionFormat = document.getElementById("option_add").cloneNode(true);
    var optionContainer = document.getElementById("option_List");
    var inputTitle = optionFormat.getElementsByClassName("input_title");
    var inputPrice = optionFormat.getElementsByClassName("input_price");

    inputTitle[0].setAttribute("class", "form-control option_title");
    inputPrice[0].setAttribute("class", "form-control option_price text-end");
    optionFormat.setAttribute("class","row mt-2 delete-target");
    optionFormat.removeAttribute("id");
    optionContainer.appendChild(optionFormat);
}

function removeItem(option) {
    var row = option.closest(".delete-target");
    row.remove();
}

function createFunding() {
    var frmCreateFunding = document.getElementById("frm_create_funding");
    var btnModal = document.getElementById("btn_modal");
    var modalBody = document.getElementById("modal_body");

    warningTxt = "아래와 같은 문제가 발생하였습니다";

    ckTitle();
    ckDateFrom();
    ckDateTo();
    ckDateEnd();
    ckGoal();
    ckOptionTitle();
    ckOptionPrice();
    ckContent();

    if(titleFlg && dateFromFlg && dateToFlg && dateEndFlg && goalFlg && optionTitleFlg && optionPriceFlg && contentFlg) {
        var mst_fc_no = document.getElementById("input_mst_fc_no");
        var mst_f_title = document.getElementById("input_mst_f_title");
        var mst_f_date_from = document.getElementById("input_mst_f_date_from");
        var mst_f_date_to = document.getElementById("input_mst_f_date_to");
        var mst_f_date_end = document.getElementById("input_mst_f_date_end");
        var mst_f_goal = document.getElementById("input_mst_f_goal");
        var mst_f_content = document.getElementById("input_mst_f_content");
        var mst_fo_name = document.getElementsByClassName("option_title");
        var mst_fo_price = document.getElementsByClassName("option_price");

        mst_fc_no.setAttribute("value", document.getElementById("input_category").value);
        mst_f_title.setAttribute("value", document.getElementById("input_title").value);
        mst_f_date_from.setAttribute("value", document.getElementById("input_date_from").value);
        mst_f_date_to.setAttribute("value", document.getElementById("input_date_to").value);
        mst_f_date_end.setAttribute("value", document.getElementById("input_date_end").value);
        mst_f_goal.setAttribute("value", document.getElementById("input_goal").value);
        mst_f_content.setAttribute("value", document.getElementById("input_content").value);

        for(var i = 0; i < mst_fo_name.length; i++) {
            var input_mst_fo_name = document.createElement("input");
            var input_mst_fo_price = document.createElement("input");

            input_mst_fo_name.setAttribute("name", "mst_fo_name");
            input_mst_fo_name.setAttribute("type", "hidden");
            input_mst_fo_name.setAttribute("value", mst_fo_name[i].value);
            input_mst_fo_price.setAttribute("name", "mst_fo_price");
            input_mst_fo_price.setAttribute("type", "hidden");
            input_mst_fo_price.setAttribute("value", mst_fo_price[i].value);

            frmCreateFunding.append(input_mst_fo_name);
            frmCreateFunding.append(input_mst_fo_price);
        }

        frmCreateFunding.submit();

    }else {

        modalBody.innerText = warningTxt;
        btnModal.click();
    }
}

function ckTitle() {
    var inputTitle = document.getElementById("input_title");

    if(inputTitle.value == "") {

        titleFlg = false;
        warningTxt += "\n펀딩명을 입력해주세요";

    }else {

        titleFlg = true;
    }
}

function ckDateFrom() {
    var inputDateFrom = document.getElementById("input_date_from");

    if(inputDateFrom.value == "") {
        
        dateFromFlg = false;
        warningTxt += "\n시작 펀딩기간을 입력해주세요";

    }else {
        var today = new Date();
        var dateFrom = new Date(inputDateFrom.value);

        if(dateFrom <= today) {

            dateFromFlg = false;
            warningTxt += "\n시작 펀딩기간은 오늘이후의 날짜를 선택해주세요";

        }else {

            dateFromFlg = true;
        }
    }
}

function ckDateTo() {
    var inputDateTo = document.getElementById("input_date_to");

    if(inputDateTo.value == "") {
        
        dateToFlg = false;
        warningTxt += "\n종료 펀딩기간을 입력해주세요";

    }else {
        var dateFrom = new Date(document.getElementById("input_date_from").value);
        var dateTo = new Date(inputDateTo.value);

        if(dateTo <= dateFrom) {

            dateToFlg = false;
            warningTxt += "\n종료 펀딩기간은 시작 펀딩기간보다 이후의 날짜를 선택해주세요";

        }else {

            dateToFlg = true;
        }
    }
}

function ckDateEnd() {
    var inputDateEnd = document.getElementById("input_date_end");

    if(inputDateEnd.value == "") {
        
        dateEndFlg = false;
        warningTxt += "\n제작완료 예정일을 입력해주세요";

    }else {
        var dateTo = new Date(document.getElementById("input_date_to").value);
        var dateEnd = new Date(inputDateEnd.value);

        if(dateEnd <= dateTo) {

            dateEndFlg = false;
            warningTxt += "\n제작완료 예정일은 종료 펀딩기간보다 이후의 날짜를 선택해주세요";

        }else {

            dateEndFlg = true;
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

function ckOptionTitle() {
    var optionTitle = document.getElementsByClassName("option_title");
    var ckFlg = false;

    for(var i = 0; i < optionTitle.length; i++) {

        if(optionTitle[i].value == "") {

            ckFlg = true;
        }
    }
    
    if(ckFlg) {

        optionFlg = false;
        warningTxt += "\n리워드명을 입력해주세요";

    }else {

        optionTitleFlg = true;
    }
}

function ckOptionPrice() {
    var optionPrice = document.getElementsByClassName("option_price");
    var ckFlg = false;

    for(var i = 0; i < optionPrice.length; i++) {
        var regExp = /[0-9]{1,38}/;

        if(!regExp.test(optionPrice[i].value)) {

            ckFlg = true;
            optionPrice[i].value = 0;

        }else {

            if(parseInt(optionPrice[i].value) < 1) {

                ckFlg = true;
            }
        }
    }
    
    if(ckFlg) {

        optionPriceFlg = false;
        warningTxt += "\n리워드 포인트를 입력해주세요";

    }else {

        optionPriceFlg = true;
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