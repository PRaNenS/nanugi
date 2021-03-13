function find() {
    var id = document.getElementById("id");
    var email = document.getElementById("email");
    var messageId = document.getElementById("message_id");
    var messageEmail = document.getElementById("message_email");
    var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    var idFlg = false;
    var emailFlg = false;

    messageId.innerText = "";
    messageEmail.innerText = "";

    if(!emailRegExp.test(id.value)) {

        idFlg = false;
        messageId.innerText = "아이디를 이메일 형식으로 입력해주세요";

    }else {

        idFlg = true;
    }

    if(!emailRegExp.test(email.value)) {

        emailFlg = false;
        messageEmail.innerText = "2차 확인 이메일을 정확히 입력해주세요";
    
    }else {

        emailFlg = true;
    }

    if(idFlg && emailFlg) {
        var frmFind = document.getElementById("frm_find");
        var inpId = document.getElementById("inp_id");
        var inpEmail = document.getElementById("inp_email");

        inpId.setAttribute("value", id.value);
        inpEmail.setAttribute("value", email.value);

        frmFind.submit();
    }
}