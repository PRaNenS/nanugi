var orderFlg = false;

// 선택 옵션 추가
function addOption() {
	var selectReward = document.getElementById("select_reward");
	var optionFormat = document.getElementById("option_format").cloneNode(true);
	var optionContainer = document.getElementById("option_container");
	var optionName = optionFormat.getElementsByClassName("option-name");
	var optionNo = optionFormat.getElementsByClassName("option-no");

	if(selectReward.value > 0) {
		optionFormat.setAttribute("class","border-bottom target");
		optionFormat.removeAttribute("id");
		optionName[0].innerText = selectReward.options[selectReward.options.selectedIndex].innerText;
		optionNo[0].setAttribute("value", selectReward.value);
		optionContainer.append(optionFormat);

		selectReward.value = 0;
	}
}

// 선택 옵션 삭제
function removeItem(tgRow) {
	var row = tgRow.closest(".target");

	row.remove();
	setTotal();
}

// 선택 옵션별 가격 변경
function setSum(target) {
	var targetRow = target.closest(".target");
	var optionNo = targetRow.getElementsByClassName("option-no");
	var price = parseInt(document.getElementById(optionNo[0].value).value);
	var optionPrice = targetRow.getElementsByClassName("option-price");
	var btnModal = document.getElementById("btn_modal");
	var regExp = /[0-9]{1,3}/;
	var sum = 0;

	if(!regExp.test(parseInt(target.value))) {

		btnModal.click();

	}else {
		if(parseInt(target.value) < 0) {

			target.value = 0;

		}else if(parseInt(target.value) > 100) {
		
			target.value = 100;
			
		}else {
			var quantity = parseInt(target.value);
	
			sum = quantity * price;
			optionPrice[0].innerText = sum;
		}
	}

	setTotal();
}

function subQtt(target) {
	var row = target.closest(".target");
	var quantity = row.getElementsByClassName("option-quantity");

	if(quantity[0].value > 0) {

		quantity[0].value--;

	}else {

		quantity[0].value = 0;
	}

	setSum(quantity[0]);
}

function addQtt(target) {
	var row = target.closest(".target");
	var quantity = row.getElementsByClassName("option-quantity");

	if(quantity[0].value < 100) {

		quantity[0].value++;

	}else {

		quantity[0].value = 100;
	}

	setSum(quantity[0]);
}

function setTotal() {
	var optionContainer = document.getElementById("option_container");
	var totalPoint = document.getElementById("total_point");
	var optionPrice = optionContainer.getElementsByClassName("option-price");
	var total = 0;

	if(optionPrice.length > 0) {
		
		for(var i = 0; i < optionPrice.length; i++) {

			total += parseInt(optionPrice[i].innerText);
		}
	}

	totalPoint.innerText = total;
}

// 펀딩참가
function joinFunding() {
	var formSubmit = document.getElementById("form-submit");
	var optionContainer = document.getElementById("option_container");
	var optionNo = optionContainer.getElementsByClassName("option-no");
	var quantity = optionContainer.getElementsByClassName("option-quantity");
	var regExp = /[0-9]{1,38}/;
	var flg = false;

	if(quantity.length > 0) {

		for(var i = 0; i < quantity.length; i++) {

			if(!regExp.test(quantity[i].value)) {
				
				flg = true;
				
			}else {

				if(parseInt(quantity[i].value) < 1) {

					flg = true;
				}
			}
		}

	}else if(quantity.length <= 0) {

		flg = true;
	}

	if(flg) {
		var btnModal = document.getElementById("btn_modal");

		orderFlg = false;
		btnModal.click();

	}else {

		orderFlg = true;

		for(var i = 0; i < optionNo.length; i++) {
			var inputOptionNo = document.createElement("input");
			var inputQuantity = document.createElement("input");
		
			inputOptionNo.setAttribute("name", "mst_fo_no");
			inputOptionNo.setAttribute("type", "hidden");
			inputOptionNo.setAttribute("value", optionNo[i].value);
			inputQuantity.setAttribute("name", "od_quantity");
			inputQuantity.setAttribute("type", "hidden");
			inputQuantity.setAttribute("value", quantity[i].value);

			formSubmit.append(inputOptionNo);
			formSubmit.append(inputQuantity);
		}
	}
	
	if(orderFlg) {

		formSubmit.submit();
	}
}