
var starrateflg = false;
var contentflg = false;
var titleflg = false;

// 작성버튼
function formSubmit(){
    var frm_write = document.getElementById("form");
    ckContent();
    ckTitle();
    ckStarrate();

    if(starrateflg && contentflg && titleflg){
        frm_write.submit();
    
    }
}

function ckContent(){
    var content = document.getElementById("content");
    var box = document.getElementById("alert_content");

    box.innerText = "";

    if(content.value == ""){
        box.innerText ="내용을 입력해주세요";
        contentflg = false;
    }else{
        contentflg = true;
    }
}

function ckTitle(){
    var title = document.getElementById("title");
    var box = document.getElementById("alert_title");

    box.innerText = "";

    if(title.value == "") {
        box.innerText = "제목을 입력해주세요";
        titleflg = false;
    }else{
        titleflg = true;
    }
}


function ckStarrate(){
    var starrate = document.getElementById("starrate");
    var box = document.getElementById("alert_starrate");
    box.innerText = "";

    if(parseInt(starrate.options[starrate.selectedIndex].value) == 0){
        box.innerText = "별점을 선택해주세요";
        starrateflg = false;

    }else if(parseInt(starrate.options[starrate.selectedIndex].value) >= 1 && parseInt(starrate.options[starrate.selectedIndex].value) <= 5){
        starrateflg = true;

    }
}
