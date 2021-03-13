var idFlg = false;
var pwFlg = false;

function login() {
    var xmlhttp = new XMLHttpRequest();
    var frmLogin = document.getElementById("frm_login");
    var inputId = document.getElementById("input_id");
    var inputPw = document.getElementById("input_pw");
    var alertBox = document.getElementById("alert_box");
    var regExpId = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    var regExpPw = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/;

    // 표시 초기화
    alertBox.innerText = "";

    // 아이디 입력 확인
    if(!regExpId.test(inputId.value)) {

        idFlg = false;
        alertBox.innerText = "아이디는 이메일 형식으로 입력해주세요";

    }else {

        idFlg = true;
    }

    // 비밀번호 입력 확인
    if(!regExpPw.test(inputPw.value)) {

        pwFlg = false;
        alertBox.innerText = "비밀번호는 8~10자의 영문 대소문자, 숫자 조합으로 입력해주세요";

    }else {

        pwFlg = true;
    }

    // 로그인 처리
    if(idFlg && pwFlg) {

        xmlhttp.onreadystatechange = function() {
        
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    
                if(xmlhttp.responseText == 'true') {

                    alertBox.innerText = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다";

                }else if(xmlhttp.responseText == 'false') {

                    frmLogin.submit();
                }
            }
        };

        xmlhttp.open("post", "../member/check_login.do");
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlhttp.send("check_id=" + inputId.value + "&check_pw=" + inputPw.value);
    }
}