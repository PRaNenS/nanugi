function setStatus() {
    var selectStatus = document.getElementById("select_status");
    var inpStatus = document.getElementById("inp_status");

    inpStatus.setAttribute("value", selectStatus.options[selectStatus.selectedIndex].value);

    search();
}

function searchCategory(categoryNo) {
    var inpCategory = document.getElementById("inp_category");

    inpCategory.setAttribute("value", categoryNo);

    search();
}

function searchPage(page) {
    var inpPageNum = document.getElementById("inp_page_num");

    inpPageNum.setAttribute("value", page);

    search();
}

function search() {
    var frmSearch = document.getElementById("frm_search");

    frmSearch.submit();
}