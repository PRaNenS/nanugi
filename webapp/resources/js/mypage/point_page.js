function charge(charge) {
	var txtPoint = document.getElementById("txt_point");
	var point = parseInt(txtPoint.value);
		
	point += charge;
		
	txtPoint.value = point;
}

function chargePoint() {
	var chargePoint = document.getElementById("txt_point");
	var regExp = /[0-9]{1,38}/;

	if(regExp.test(chargePoint.value)) {

		if(parseInt(chargePoint.value) > 1) {
			var frmCharge = document.getElementById("frm_charge");
			var inputPoint = document.createElement("input");
		
			inputPoint.setAttribute("name", "charge_point");
			inputPoint.setAttribute("type", "hidden");
			inputPoint.setAttribute("value", chargePoint.value);

			frmCharge.append(inputPoint);
			
			frmCharge.submit();
		}else {

			chargePoint.value = 0;
		}
		
	}else {

		chargePoint.value = 0;
	}
}

function search() {
	var frmSearch = document.getElementById("frm_search");
	var dateFrom = document.getElementById("date_from");
	var dateTo = document.getElementById("date_to");
	var btnModal = document.getElementById("btn_modal");

	if(dateFrom.value != "" && dateTo.value == "") {
		
		btnModal.click();

	}else if(dateFrom.value == "" && dateTo.value != "") {

		btnModal.click();

	}else {

		if(dateFrom.value == "" && dateTo.value == "") {

			frmSearch.submit();

		}else if(dateFrom.value != "" && dateTo.value != "") {
			var from = new Date(dateFrom.value);
			var to = new Date(dateTo.value);

			if(from <= to) {
				var inpDateFrom = document.getElementById("input_date_from");
				var inpDateTo = document.getElementById("input_date_to");

				inpDateFrom.setAttribute("value", dateFrom.value);
				inpDateTo.setAttribute("value", dateTo.value);

				frmSearch.submit();

			}else {
				
				btnModal.click();				
			}
		}
	}
}