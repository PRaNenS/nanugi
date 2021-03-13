var pwFlg = false;
var nickFlg = false;
var nameFlg = false;
var zipFlg = false;
var address1Flg = false;
var address2Flg = false;
var emailFlg = false;
var telFlg = false;

var nickCFlg = false;
var nameCFlg = false;
var yearCFlg = false;
var monthCFlg = false;
var dayCFlg = false;
var zipCFlg = false;
var address1CFlg = false;
var address2CFlg = false;
var telCFlg = false;
var uniqnumCFlg = false;

function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};

function clickTab(tab) {
    var userInformationWrap = document.getElementById("user_information_wrap");
    var requestCompanyWrap = document.getElementById("request_company_wrap");

    if(tab == 1) {

        userInformationWrap.setAttribute("class", "d-block");
        requestCompanyWrap.setAttribute("class", "d-none");

    }else if(tab == 2) {

        userInformationWrap.setAttribute("class", "d-none");
        requestCompanyWrap.setAttribute("class", "d-block");
    }
}

function refreshProfile() {
    var file = document.getElementById("file"); // 파일 업로드 id
    var profileImg = document.getElementById("profile_img"); // 프로필 표시 영역
    var inputProfile = document.getElementById("input_profile"); // 프로필

    if(file.value) { // 업로드 프로필
        var xmlhttp = new XMLHttpRequest(); // http리퀘스트
        var formData = new FormData(); // 폼데이터 생성

        formData.append("file", file.files[0] ,file.files[0].name); // 폼데이터에 프로필 추가

        xmlhttp.onreadystatechange = function() {

            // 이미지 수신 처리
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var obj = JSON.parse(xmlhttp.responseText); // 프로필 정보
                var profileDir = obj.link; // 프로필 이미지 경로
                
                // 요소 설정
                profileImg.setAttribute("src", profileDir);
                inputProfile.setAttribute("value", profileDir);
            }
        };
    
        xmlhttp.open("post", "../mypage/get_upload_profile.do");
        xmlhttp.send(formData);

    }else { // 기본 프로필
        var defaultDir = getContextPath() + "/resources/img/default_profile.png";

        profileImg.setAttribute("src", defaultDir);
        inputProfile.setAttribute("value", defaultDir);
    }
}

function updateProfile() {
    var frmProfile = document.getElementById("frm_profile");

    frmProfile.submit();
}

function requestCom() {
    var btnRequest = document.getElementById("btn_request");
    var btnAlert = document.getElementById("btn_alert");
    var modalTitle = document.getElementById("staticBackdropLabel");
    var modalBody = document.getElementById("modalBody");
    var btnClose = document.getElementById("btnClose");
    var btnClose2 = document.getElementById("btnClose2");

    modalTitle.innerText = "기관 신청";
    modalBody.innerText = "기관유저 신청이 완료되었습니다!\n신청이 승인되기까지 최대 7일이 소요될 수 있습니다";
    btnClose.setAttribute("onclick", "");
    btnClose2.setAttribute("onclick", "");

    ckNickC();
    ckNameC();
    ckYearC();
    ckMonthC();
    ckDayC();
    ckZipC();
    ckAddress1C();
    ckAddress2C();
    ckTelC();
    ckUniqnumC();

    if(nickCFlg && nameCFlg && yearCFlg && monthCFlg && dayCFlg && zipCFlg && address1CFlg && address2CFlg && telCFlg && uniqnumCFlg) {
        var xmlhttp = new XMLHttpRequest();
        var formData = new FormData();
        var inputNick = document.getElementById("input_nick_c");
        var inputName = document.getElementById("input_name_c");
        var inputBirthYear = document.getElementById("input_birth_year_c");
        var inputBirthMonth = document.getElementById("input_birth_month_c");
        var inputBirthDay = document.getElementById("input_birth_day_c");
        var inputZip = document.getElementById("input_zip_c");
        var inputAddress1 = document.getElementById("input_address1_c");
        var inputAddress2 = document.getElementById("input_address2_c");
        var inputTel = document.getElementById("input_tel_c");
        var inputUniqnum = document.getElementById("input_uniqnum_c");

        formData.append("mrc_nick", inputNick.value);
        formData.append("mrc_name", inputName.value);
        formData.append("mrc_birth", inputBirthYear.value+"-"+inputBirthMonth.value+"-"+inputBirthDay.value);
        formData.append("mrc_zip", inputZip.value);
        formData.append("mrc_address1", inputAddress1.value);
        formData.append("mrc_address2", inputAddress2.value);
        formData.append("mrc_tel", inputTel.value);
        formData.append("mrc_uniqnum", inputUniqnum.value);

        xmlhttp.open("post", "../mypage/request_com_process.do");
        xmlhttp.send(formData);

        btnRequest.remove();
        btnAlert.click();
    }
}

function logout() {
    var frmLogout = document.getElementById("frm_logout");

    frmLogout.submit();
}

function updatePw() {
    
    ckPw();

    if(pwFlg) {
        var xmlhttp = new XMLHttpRequest();
        var formData = new FormData();
        var inputPw = document.getElementById("input_pw");
        var btnAlert = document.getElementById("btn_alert");
        var modalTitle = document.getElementById("staticBackdropLabel");
        var modalBody = document.getElementById("modalBody");
        var btnClose = document.getElementById("btnClose");
        var btnClose2 = document.getElementById("btnClose2");

        formData.append("m_pw", inputPw.value);
        
        modalTitle.innerText = "비밀번호 변경";
        modalBody.innerText = "비밀번호가 변경되었습니다\n재접속 후 이용해주시기 바랍니다";
        btnClose.setAttribute("onclick", "logout()");
        btnClose2.setAttribute("onclick", "logout()");

        xmlhttp.open("post", "../member/update_pw_process.do");
        xmlhttp.send(formData);

        btnAlert.click();
    }
}

function updateUser() {

    ckNick();
    ckName();
    ckZip();
    ckAddress1();
    ckAddress2();
    ckEmail();
    ckTel();

    if(nickFlg && nameFlg && zipFlg && address1Flg && address2Flg && emailFlg && telFlg) {
        var xmlhttp = new XMLHttpRequest();
        var formData = new FormData();
        var inputNick = document.getElementById("input_nick");
        var inputName = document.getElementById("input_name");
        var inputZip = document.getElementById("input_zip");
        var inputAddress1 = document.getElementById("input_address1");
        var inputAddress2 = document.getElementById("input_address2");
        var inputEmail = document.getElementById("input_email");
        var inputTel = document.getElementById("input_tel");
        var btnAlert = document.getElementById("btn_alert");
        var modalTitle = document.getElementById("staticBackdropLabel");
        var modalBody = document.getElementById("modalBody");
        var btnClose = document.getElementById("btnClose");
        var btnClose2 = document.getElementById("btnClose2");

        formData.append("m_nick", inputNick.value);
        formData.append("m_name", inputName.value);
        formData.append("m_zip", inputZip.value);
        formData.append("m_address1", inputAddress1.value);
        formData.append("m_address2", inputAddress2.value);
        formData.append("m_email", inputEmail.value);
        formData.append("m_tel", inputTel.value);
        
        modalTitle.innerText = "회원정보 변경";
        modalBody.innerText = "회원정보가 변경되었습니다\n재접속 후 이용해주시기 바랍니다";
        btnClose.setAttribute("onclick", "logout()");
        btnClose2.setAttribute("onclick", "logout()");

        xmlhttp.open("post", "../member/update_user_process.do");
        xmlhttp.send(formData);

        btnAlert.click();
    }
}

function ckPw() {
    var inputPw = document.getElementById("input_pw");
    var alertBox = document.getElementById("alert_pw");
    var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/;

    alertBox.innerText = "";

    if(!regExp.test(inputPw.value)) {
        alertBox.innerText = "비밀번호는 8~10자의 영문 대소문자, 숫자 조합으로 입력해주세요";
        inputPw.value = "";
        inputPw.focus();

        pwFlg = false;

    }else {
        
        pwFlg = true;
    }
}

function ckNick() {
    var inputNick = document.getElementById("input_nick");
    var alertBox = document.getElementById("alert_nick");

    alertBox.innerText = "";

    if(inputNick.value == "") {
        alertBox.innerText = "닉네임을 입력해주세요";

        nickFlg = false;

    }else {

        nickFlg = true;
    }
}

function ckName() {
    var inputName = document.getElementById("input_name");
    var alertBox = document.getElementById("alert_name");

    alertBox.innerText = "";

    if(inputName.value == "") {
        alertBox.innerText = "이름을 입력해주세요";
        
        nameFlg = false;

    }else {

        nameFlg = true;
    }
}

function ckZip() {
    var inputZip = document.getElementById("input_zip");
    var alertBox = document.getElementById("alert_address");
    var regExp = /[0-9]{5}/;

    alertBox.innerText = "";

    if(!regExp.test(inputZip.value)) {
        alertBox.innerText = "우편번호를 입력해주세요";
        
        zipFlg = false;

    }else {

        zipFlg = true;
    }
}

function ckAddress1() {
    var inputAddress1 = document.getElementById("input_address1");
    var alertBox = document.getElementById("alert_address");

    alertBox.innerText = "";

    if(inputAddress1.value == "") {
        alertBox.innerText = "주소를 입력해주세요";
        
        address1Flg = false;

    }else {

        address1Flg = true;
    }
}

function ckAddress2() {
    var inputAddress2 = document.getElementById("input_address2");
    var alertBox = document.getElementById("alert_address");

    alertBox.innerText = "";

    if(inputAddress2.value == "") {
        alertBox.innerText = "상세주소를 입력해주세요";
        
        address2Flg = false;

    }else {

        address2Flg = true;
    }
}

function ckEmail() {
    var inputEmail = document.getElementById("input_email");
    var alertBox = document.getElementById("alert_email");
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    alertBox.innerText = "";

    if(!regExp.test(inputEmail.value)) {
        alertBox.innerText = "2차 확인 이메일을 입력해주세요";
        
        emailFlg = false;

    }else {

        emailFlg = true;
    }
}

function ckTel() {
    var inputTel = document.getElementById("input_tel");
    var alertBox = document.getElementById("alert_tel");
    var regExp = /^[0-9]{9,11}/;

    alertBox.innerText = "";

    if(!regExp.test(inputTel.value)) {
        alertBox.innerText = "연락처를 입력해주세요";
        
        telFlg = false;

    }else {

        telFlg = true;
    }
}

function ckNickC() {
    var inputNick = document.getElementById("input_nick_c");
    var alertBox = document.getElementById("alert_nick_c");

    alertBox.innerText = "";

    if(inputNick.value == "") {
        alertBox.innerText = "닉네임을 입력해주세요";

        nickCFlg = false;

    }else {

        nickCFlg = true;
    }
}

function ckNameC() {
    var inputName = document.getElementById("input_name_c");
    var alertBox = document.getElementById("alert_name_c");

    alertBox.innerText = "";

    if(inputName.value == "") {
        alertBox.innerText = "이름을 입력해주세요";
        
        nameCFlg = false;

    }else {

        nameCFlg = true;
    }
}

function ckYearC() {
    var inputBirthYear = document.getElementById("input_birth_year_c");
    var alertBox = document.getElementById("alert_birth_c");
    var regExp = /[0-9]{4}/;

    alertBox.innerText = "";

    if(!regExp.test(inputBirthYear.value)) {
        alertBox.innerText = "태어난 년도 4자리를 입력해주세요";
        
        yearCFlg = false;

    }else {

        yearCFlg = true;
    }
}

function ckMonthC() {
    var inputBirthMonth = document.getElementById("input_birth_month_c");
    var alertBox = document.getElementById("alert_birth_c");
    var regExp = /^[0]{1}?[1-9]{1}|^[1]{1}?[0-2]{1}/;

    alertBox.innerText = "";

    if(!regExp.test(inputBirthMonth.value)) {
        alertBox.innerText = "태어난 월 2자리를 입력해주세요";
        
        monthCFlg = false;

    }else {

        monthCFlg = true;
    }
}

function ckDayC() {
    var inputBirthDay = document.getElementById("input_birth_day_c");
    var alertBox = document.getElementById("alert_birth_c");
    var regExp = /^[0]{1}?[1-9]{1}|^[1-2]{1}?[0-9]{1}|^[3]{1}?[0-1]{1}/;

    alertBox.innerText = "";

    if(!regExp.test(inputBirthDay.value)) {
        alertBox.innerText = "태어난 일 2자리를 입력해주세요";
        
        dayCFlg = false;

    }else {

        dayCFlg = true;
    }
}

function ckZipC() {
    var inputZip = document.getElementById("input_zip_c");
    var alertBox = document.getElementById("alert_address_c");
    var regExp = /[0-9]{5}/;

    alertBox.innerText = "";

    if(!regExp.test(inputZip.value)) {
        alertBox.innerText = "우편번호를 입력해주세요";
        
        zipCFlg = false;

    }else {

        zipCFlg = true;
    }
}

function ckAddress1C() {
    var inputAddress1 = document.getElementById("input_address1_c");
    var alertBox = document.getElementById("alert_address_c");

    alertBox.innerText = "";

    if(inputAddress1.value == "") {
        alertBox.innerText = "주소를 입력해주세요";
        
        address1CFlg = false;

    }else {

        address1CFlg = true;
    }
}

function ckAddress2C() {
    var inputAddress2 = document.getElementById("input_address2_c");
    var alertBox = document.getElementById("alert_address_c");

    alertBox.innerText = "";

    if(inputAddress2.value == "") {
        alertBox.innerText = "상세주소를 입력해주세요";
        
        address2CFlg = false;

    }else {

        address2CFlg = true;
    }
}

function ckTelC() {
    var inputTel = document.getElementById("input_tel_c");
    var alertBox = document.getElementById("alert_tel_c");
    var regExp = /^[0-9]{9,11}/;

    alertBox.innerText = "";

    if(!regExp.test(inputTel.value)) {
        alertBox.innerText = "연락처를 입력해주세요";
        
        telCFlg = false;

    }else {

        telCFlg = true;
    }
}

function ckUniqnumC() {
    var inputUniqnum = document.getElementById("input_uniqnum_c");
    var alertBox = document.getElementById("alert_uniqnum_c");

    alertBox.innerText = "";

    if(inputUniqnum.value == "") {
        alertBox.innerText = "기관고유번호를 입력해주세요";
        
        uniqnumCFlg = false;

    }else {

        uniqnumCFlg = true;
    }
}