// 펀딩참여
function order() {
    var formOrder = document.getElementById("form-order");
    var userPoint = document.getElementById("user-point");
    var totalPrice = document.getElementById("total-price");
    var messageBox = document.getElementById("message-box");
    var submitFlg = false;
    var receiverFlg = false;
    var zipFlg = false;
    var address1Flg = false;
    var address2Flg = false;
    var requestFlg = false;

    if(parseInt(userPoint.value) < parseInt(totalPrice.value)) {
        
        messageBox.innerText = "보유 포인트가 부족합니다";

    }else {
        var receiver = document.getElementById("receiver");
        var zip = document.getElementById("zip");
        var address1 = document.getElementById("address1");
        var address2 = document.getElementById("address2");
        var request = document.getElementById("request");
        var inputReceiver = document.createElement("input");
        var inputZip = document.createElement("input");
        var inputAddress1 = document.createElement("input");
        var inputAddress2 = document.createElement("input");
        var inputRequest = document.createElement("input");
        var btnModal = document.getElementById("btn_modal");
        var modalBody = document.getElementById("modal_body");
        var messageStr = "다음과 같은 문제가 발생하였습니다";
        var zipRegExp = /[0-9]{5}/;

        submitFlg = true;

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

        if(receiverFlg && zipFlg && address1Flg && address2Flg && requestFlg && submitFlg) {

            inputReceiver.setAttribute("name", "of_receiver");
            inputReceiver.setAttribute("type", "hidden");
            inputReceiver.setAttribute("value", receiver.value);
            inputZip.setAttribute("name", "of_zip");
            inputZip.setAttribute("type", "hidden");
            inputZip.setAttribute("value", zip.value);
            inputAddress1.setAttribute("name", "of_address1");
            inputAddress1.setAttribute("type", "hidden");
            inputAddress1.setAttribute("value", address1.value);
            inputAddress2.setAttribute("name", "of_address2");
            inputAddress2.setAttribute("type", "hidden");
            inputAddress2.setAttribute("value", address2.value);
            inputRequest.setAttribute("name", "of_request");
            inputRequest.setAttribute("type", "hidden");
            inputRequest.setAttribute("value", request.value);

            formOrder.append(inputReceiver);
            formOrder.append(inputZip);
            formOrder.append(inputAddress1);
            formOrder.append(inputAddress2);
            formOrder.append(inputRequest);

            formOrder.submit();
        
        }else {

            modalBody.innerText = messageStr;
            btnModal.click();
        }
    }
}