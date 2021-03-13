function setTab(ck) {
    var subTabContainer = document.getElementById("sub_tab_container");
    var childCount = subTabContainer.childNodes.length;

    // 탭 초기화
    for(var i = 0; i < childCount; i++) {
        subTabContainer.childNodes[0].remove();

    }

    if(ck == 1) {
        var introduceSelfTab = document.createElement("li");
        var introduceSelf = document.createElement("a");
        var introduceComTab = document.createElement("li");
        var introduceCom = document.createElement("a");
        var donateStatusTab = document.createElement("li");
        var donateStatus = document.createElement("a");

        introduceSelfTab.setAttribute("class", "nav-item tab-item");
        introduceSelf.setAttribute("class", "nav-link item-link fs-6");
        introduceSelf.setAttribute("href", "#");
        introduceComTab.setAttribute("class", "nav-item tab-item");
        introduceCom.setAttribute("class", "nav-link item-link fs-6");
        introduceCom.setAttribute("href", "#");
        donateStatusTab.setAttribute("class", "nav-item tab-item");
        donateStatus.setAttribute("class", "nav-link item-link fs-6");
        donateStatus.setAttribute("href", "#");

        introduceSelf.innerText = "도네기란?";
        introduceCom.innerText = "협력기관 소개";
        donateStatus.innerText = "기부현황";

        introduceSelfTab.append(introduceSelf);
        introduceComTab.append(introduceCom);
        donateStatusTab.append(donateStatus);
        subTabContainer.append(introduceSelfTab);
        subTabContainer.append(introduceComTab);
        subTabContainer.append(donateStatusTab);
    }

    if(ck == 2) {
        var noticeTab = document.createElement("li");
        var notice = document.createElement("a");
        var eventTab = document.createElement("li");
        var event = document.createElement("a");

        noticeTab.setAttribute("class", "nav-item tab-item");
        notice.setAttribute("class", "nav-link item-link fs-6");
        notice.setAttribute("href", "#");
        eventTab.setAttribute("class", "nav-item tab-item");
        event.setAttribute("class", "nav-link item-link fs-6");
        event.setAttribute("href", "../event/list_page.do");

        notice.innerText = "공지사항";
        event.innerText = "이벤트";

        noticeTab.append(notice);
        eventTab.append(event);
        subTabContainer.append(noticeTab);
        subTabContainer.append(eventTab);
    }

    if(ck == 3) {
        var donateTab = document.createElement("li");
        var donate = document.createElement("a");
        var volunteerTab = document.createElement("li");
        var volunteer = document.createElement("a");

        donateTab.setAttribute("class", "nav-item tab-item");
        donate.setAttribute("class", "nav-link item-link fs-6");
        donate.setAttribute("href", "#");
        volunteerTab.setAttribute("class", "nav-item tab-item");
        volunteer.setAttribute("class", "nav-link item-link fs-6");
        volunteer.setAttribute("href", "#");

        donate.innerText = "기부";
        volunteer.innerText = "봉사";

        donateTab.append(donate);
        volunteerTab.append(volunteer);
        subTabContainer.append(donateTab);
        subTabContainer.append(volunteerTab);
    }

    if(ck == 4) {
        var tradeTab = document.createElement("li");
        var trade = document.createElement("a");

        tradeTab.setAttribute("class", "nav-item tab-item");
        trade.setAttribute("class", "nav-link item-link fs-6");
        trade.setAttribute("href", "../funding/list_page.do");

        trade.innerText = "거래";

        tradeTab.append(trade);
        subTabContainer.append(tradeTab);
    }

    if(ck == 5) {
        var freeBoardTab = document.createElement("li");
        var freeBoard = document.createElement("a");
        var reviewTab = document.createElement("li");
        var review = document.createElement("a");

        freeBoardTab.setAttribute("class", "nav-item tab-item");
        freeBoard.setAttribute("class", "nav-link item-link fs-6");
        freeBoard.setAttribute("href", "#");
        reviewTab.setAttribute("class", "nav-item tab-item");
        review.setAttribute("class", "nav-link item-link fs-6");
        review.setAttribute("href", "#");

        freeBoard.innerText = "자유게시판";
        review.innerText = "후기게시판";

        freeBoardTab.append(freeBoard);
        reviewTab.append(review);
        subTabContainer.append(freeBoardTab);
        subTabContainer.append(reviewTab);
    }

    if(ck == 6) {
        var qnaTab = document.createElement("li");
        var qna = document.createElement("a");
        var questionTab = document.createElement("li");
        var question = document.createElement("a");

        qnaTab.setAttribute("class", "nav-item tab-item");
        qna.setAttribute("class", "nav-link item-link fs-6");
        qna.setAttribute("href", "#");
        questionTab.setAttribute("class", "nav-item tab-item");
        question.setAttribute("class", "nav-link item-link fs-6");
        question.setAttribute("href", "#");

        qna.innerText = "Q&A";
        question.innerText = "문의";

        qnaTab.append(qna);
        questionTab.append(question);
        subTabContainer.append(qnaTab);
        subTabContainer.append(questionTab);
    }
}