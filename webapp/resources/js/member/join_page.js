var idFlg = false;
var pwFlg = false;
var pwConfirmFlg = false;
var nickFlg = false;
var nameFlg = false;
var yearFlg = false;
var monthFlg = false;
var dayFlg = false;
var zipFlg = false;
var address1Flg = false;
var address2Flg = false;
var emailFlg = false;
var telFlg = false;

function join() {
    var frmJoin = document.getElementById("frm_join");

    ckId();
    ckPw();
    ckPwConfirm();
    ckNick();
    ckName();
    ckYear();
    ckMonth();
    ckDay();
    ckZip();
    ckAddress1();
    ckAddress2();
    ckEmail();
    ckTel();

    if(idFlg && pwFlg && pwConfirmFlg && nickFlg && nameFlg && yearFlg && monthFlg && dayFlg && zipFlg && address1Flg && address2Flg && emailFlg && telFlg) {
        var inputBirthYear = document.getElementById("input_birth_year");
        var inputBirthMonth = document.getElementById("input_birth_month");
        var inputBirthDay = document.getElementById("input_birth_day");
        var inputBirth = document.createElement("input");

        inputBirth.setAttribute("name", "m_birth");
        inputBirth.setAttribute("type", "hidden");
        inputBirth.setAttribute("value", inputBirthYear.value +"-"+ inputBirthMonth.value +"-"+ inputBirthDay.value);
        frmJoin.append(inputBirth);

        frmJoin.submit();
    }
}

function ckId() {
    var inputId = document.getElementById("input_id");
    var alertBox = document.getElementById("alert_id");
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    var xmlhttp = new XMLHttpRequest();

    alertBox.setAttribute("class", "text-danger");

    if(!regExp.test(inputId.value)) {
        alertBox.innerText = "아이디는 이메일 형식으로 입력해주세요";
        inputId.focus();

        idFlg = false;

    }else {

        xmlhttp.onreadystatechange = function() {
        
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    
                if(xmlhttp.responseText == 'true') {
                    alertBox.innerText = "사용 불가능한 아이디입니다";
                    inputId.focus();

                    idFlg = false;

                }else {
                    alertBox.innerText = "사용 가능한 아이디입니다";
                    alertBox.setAttribute("class", "text-success");

                    idFlg = true;
                }
            }
        };

        xmlhttp.open("post", "../member/exist_id.do");
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlhttp.send("check_id=" + inputId.value);
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

function ckPwConfirm() {
    var inputPw = document.getElementById("input_pw");
    var inputPwConfirm = document.getElementById("input_pw_confirm");
    var alertBox = document.getElementById("alert_pw_confirm");

    alertBox.innerText = "";

    if(inputPw.value != inputPwConfirm.value) {
        alertBox.innerText = "비밀번호를 다시 확인해주세요";
        inputPwConfirm.value = "";

        pwConfirmFlg = false;

    }else {

        pwConfirmFlg = true;
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

function ckYear() {
    var inputYear = document.getElementById("input_birth_year");
    var alertBox = document.getElementById("alert_birth");
    var regExp = /[0-9]{4}/;

    alertBox.innerText = "";

    if(!regExp.test(inputYear.value)) {
        alertBox.innerText = "태어난 년도 4자리를 입력해주세요";
        
        yearFlg = false;

    }else {

        yearFlg = true;
    }
}

function ckMonth() {
    var inputMonth = document.getElementById("input_birth_month");
    var alertBox = document.getElementById("alert_birth");
    var regExp = /^[0]{1}?[1-9]{1}|^[1]{1}?[0-2]{1}/;

    alertBox.innerText = "";

    if(!regExp.test(inputMonth.value)) {
        alertBox.innerText = "태어난 월 2자리를 입력해주세요";
        
        monthFlg = false;

    }else {

        monthFlg = true;
    }
}

function ckDay() {
    var inputDay = document.getElementById("input_birth_day");
    var alertBox = document.getElementById("alert_birth");
    var regExp = /^[0]{1}?[1-9]{1}|^[1-2]{1}?[0-9]{1}|^[3]{1}?[0-1]{1}/;

    alertBox.innerText = "";

    if(!regExp.test(inputDay.value)) {
        alertBox.innerText = "태어난 일 2자리를 입력해주세요";
        
        dayFlg = false;

    }else {

        dayFlg = true;
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