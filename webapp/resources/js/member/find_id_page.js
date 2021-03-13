function find() {
    var name = document.getElementById("name");
    var tel = document.getElementById("tel");
    var messageName = document.getElementById("message_name");
    var messageTel = document.getElementById("message_tel");
    var telRegExp = /[0-9]{9,11}/;
    var nameFlg = false;
    var telFlg = false;

    messageName.innerText = "";
    messageTel.innerText = "";

    if(name.value == "") {

        nameFlg = false;
        messageName.innerText = "이름을 입력해주세요";

    }else {

        nameFlg = true;
    }

    if(!telRegExp.test(tel.value)) {

        telFlg = false;
        messageTel.innerText = "휴대전화번호를 정확히 입력해주세요";
    
    }else {

        telFlg = true;
    }

    if(nameFlg && telFlg) {
        var frmFind = document.getElementById("frm_find");
        var inpName = document.getElementById("inp_name");
        var inpTel = document.getElementById("inp_tel");

        inpName.setAttribute("value", name.value);
        inpTel.setAttribute("value", tel.value);

        frmFind.submit();
    }
}