function donate() {
    var holdingPoint = document.getElementById("holding_point");
    var txtDonatePoint = document.getElementById("txt_donate_point");
    var alertBox = document.getElementById("alert_box");
    var regExp = /[0-9]{1,38}/;

    alertBox.innerText = "";

    if(!regExp.test(txtDonatePoint.value)) {

        alertBox.innerText = "기부 포인트를 정확히 입력해주세요";
        txtDonatePoint.value = 0;

    }else {

        if(parseInt(holdingPoint.value) < parseInt(txtDonatePoint.value)) {

            alertBox.innerText = "보유 포인트가 부족합니다";
    
        }else if(parseInt(txtDonatePoint.value) <= 0) {

            alertBox.innerText = "기부 포인트는 0이상의 숫자를 입력해주세요";
            txtDonatePoint.value = 0;

        }else {
            var inputDonatePoint = document.getElementById("input_donate_point");
            var btnCloseModal = document.getElementById("btn_close_modal");
            var btnConfirmModal = document.getElementById("btn_confirm_modal");

            inputDonatePoint.setAttribute("value", txtDonatePoint.value);
            btnCloseModal.click();
            btnConfirmModal.click();
        }
    }
}

function donateConfirm() {
    var frmSubmit = document.getElementById("frm_submit");

    frmSubmit.submit();
}