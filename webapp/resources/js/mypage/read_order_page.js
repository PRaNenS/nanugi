var receiverFlg = false;
var zipFlg = false;
var address1Flg = false;
var address2Flg = false;
var requestFlg = false;

function update() {
    var formUpdate = document.getElementById("form-update");
    var receiver = document.getElementById("receiver");
    var zip = document.getElementById("zip");
    var address1 = document.getElementById("address1");
    var address2 = document.getElementById("address2");
    var request = document.getElementById("request");
    var btnModal = document.getElementById("btn_modal");
    var modalBody = document.getElementById("modal_body");
    var zipRegExp = /[0-9]{5}/;
    var messageStr = "다음과 같은 문제가 발생하였습니다";

    if(receiver.value == "") {
        receiverFlg = false;
        messageStr += "\n수취인을 입력해주세요";
    }else {
        receiverFlg = true;
    }

    if(!zipRegExp.test(zip.value)) {
        zipFlg = false;
        messageStr += "\n우편번호를 정확히 입력해주세요";
    }else {
        zipFlg = true;
    }

    if(address1.value == "") {
        address1Flg = false;
        messageStr += "\n주소를 입력해주세요";
    }else {
        address1Flg = true;
    }

    if(address2.value == "") {
        address2Flg = false;
        messageStr += "\n상세주소를 입력해주세요";
    }else {
        address2Flg = true;
    }

    if(request.value == "") {
        requestFlg = false;
        messageStr += "\n요청사항을 입력해주세요";
    }else {
        requestFlg = true;
    }

    if(receiverFlg && zipFlg && address1Flg && address2Flg && requestFlg) {
        var inpReceiver = document.getElementById("inp_receiver");
        var inpZip = document.getElementById("inp_zip");
        var inpAddress1 = document.getElementById("inp_address1");
        var inpAddress2 = document.getElementById("inp_address2");
        var inpRequest = document.getElementById("inp_request");

        inpReceiver.setAttribute("value", receiver.value);
        inpZip.setAttribute("value", zip.value);
        inpAddress1.setAttribute("value", address1.value);
        inpAddress2.setAttribute("value", address2.value);
        inpRequest.setAttribute("value", request.value);
    
        formUpdate.submit();

    }else {

        modalBody.innerText = messageStr;
        btnModal.click();
    }
}