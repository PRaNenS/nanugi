
var headlineflg = false;
var contentflg = false;
var titleflg = false;


// 작성버튼
function formSubmit(){
    var frm_write = document.getElementById("form");
    ckContent();
    ckTitle();
    ckHeadline();

    if(headlineflg && contentflg && titleflg){
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

function ckHeadline(){
    var headline = document.getElementById("headline");
    var box = document.getElementById("alert_headline");
    box.innerText = "";
    
    if(parseInt(headline.options[headline.selectedIndex].value) == 0){
        box.innerText = "말머리를 선택해주세요";
        headlineflg = false;

    }else if(parseInt(headline.options[headline.selectedIndex].value) >= 1 && parseInt(headline.options[headline.selectedIndex].value) <= 3){
        headlineflg = true;

    }
}
