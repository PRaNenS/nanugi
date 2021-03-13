var pwFlg = false;
var confirmFlg = false;

function reset() {
    var inputPw = document.getElementById("password");
    var alertBox = document.getElementById("message_password");
    var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/;

    alertBox.innerText = "";
    ckpw();

    if(!regExp.test(inputPw.value)) {
        alertBox.innerText = "비밀번호는 8~10자의 영문 대소문자, 숫자 조합으로 입력해주세요";
        inputPw.value = "";

        pwFlg = false;

    }else {
        
        pwFlg = true;
    }

    if(pwFlg && confirmFlg) {
        var frmReset = document.getElementById("frm_reset");
        var inpPw = document.getElementById("inp_pw");

        inpPw.setAttribute("value", inputPw.value);

        frmReset.submit();
    }
}

function ckpw() {
    var inputPw = document.getElementById("password");
    var confirmPw = document.getElementById("confirm_pw");
    var messagePw = document.getElementById("message_confirm");
    var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/;

    messagePw.innerText = "";

    if(!regExp.test(confirmPw.value)) {
        messagePw.innerText = "비밀번호 재확인은 8~10자의 영문 대소문자, 숫자 조합으로 입력해주세요";
        confirmPw.value = "";

        confirmFlg = false;

    }else {

        if(inputPw.value == confirmPw.value) {

            confirmFlg = true;

        }else {

            confirmFlg = false;
            messagePw.innerText = "입력한 비밀번호가 일치하지 않습니다";
        }
    }
}