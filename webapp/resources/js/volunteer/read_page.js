function refresh() {
	onloadOption();
}


// 선택 옵션 초기화
function onloadOption() {
	var optionFormat = document.getElementById("option_format").cloneNode(true);
	var optionContainer = document.getElementById("option_container");
	var btnDelete = optionFormat.getElementsByClassName("btn-delete");

	btnDelete[0].remove();
	optionFormat.setAttribute("class","row mt-2 target");
	optionFormat.removeAttribute("id");
	optionContainer.append(optionFormat);
}

// 선택 옵션 추가
function addOption() {
	var optionFormat = document.getElementById("option_format").cloneNode(true);
	var optionContainer = document.getElementById("option_container");

	optionFormat.setAttribute("class","row mt-2 target format");
	optionFormat.removeAttribute("id");
	optionContainer.append(optionFormat);
}


// 선택 옵션 삭제
function removeItem(tgRow) {
	var row = tgRow.closest(".target");

	row.remove();
}

function setTime(target) {
	var DateNo = target.value;
	var targetRow = target.closest(".target");
	var inputDate = targetRow.getElementsByClassName("time");
	var time = document.getElementById(DateNo).value;

	inputDate[0].setAttribute("value", time);
}

function joinVolunteer()
{
	var formSubmit = document.getElementById("submit");
	var optionContainer = document.getElementById("option_container");
	var dateNo = optionContainer.getElementsByClassName("date-name");
	var time = optionContainer.getElementsByClassName("time");
	var submitFlg = true;

	for(var i = 0; i < dateNo.length; i++) {
		var flg = false;

		if(time[i].value == "") {

			flg = true;
		
		}else {
			var selectDateNo = document.createElement("input");
	
			selectDateNo.setAttribute("name", "mst_vd_no");
			selectDateNo.setAttribute("type", "hidden");
			selectDateNo.setAttribute("value", dateNo[i].value);
			
			formSubmit.append(selectDateNo);

			flg = false;
		}

		if(flg) {

			submitFlg = false;
		}
	}
	
	if(submitFlg) {

		formSubmit.submit();
	}
}