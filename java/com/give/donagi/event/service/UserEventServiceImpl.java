package com.give.donagi.event.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.give.donagi.event.mapper.UserEventSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.*;

@Service
public class UserEventServiceImpl {
	@Autowired
	UserEventSQLMapper eventSQLMapper;
	@Autowired
	UserMemberSQLMapper memberSQLMapper;
	
	// 이벤트 개설
	public void insertEvent(BEventVo bEventVoParam, BEImgVo beImgVo, HashMap<String, Object> loginUser) {
		MemberVo memberVo = (MemberVo)loginUser.get("memberVo"); // 로그인 유저 정보
		int eventNo = this.eventSQLMapper.createEventKey();
		
		// 이벤트 개설 처리
		bEventVoParam.setBe_no(eventNo); // 키 생성
		bEventVoParam.setM_no(memberVo.getM_no()); // 이벤트 개설 유저 정보 설정
		
		beImgVo.setBei_no(this.eventSQLMapper.createImgKey()); // 이미지키 생성
		beImgVo.setBe_no(eventNo);
		
		this.eventSQLMapper.createEvent(bEventVoParam); // 이벤트 개설
		this.eventSQLMapper.insertEventImg(beImgVo); // 이벤트 이미지 생성
		
		System.out.println("[System] insertEvent");
	}
	
	// 이벤트 리스트 받기
	public ArrayList<HashMap<String, Object>> getBoard(HashMap<String, Object> searchMap) {
		ArrayList<HashMap<String, Object>> eventListData = new ArrayList<HashMap<String,Object>>();
		ArrayList<BEventVo> bEventVoList = this.eventSQLMapper.selectAllEvent(searchMap); // 이벤트 리스트
		CommonsDateConverter dateconverter = new CommonsDateConverter();
		
		// 이벤트 정보 재설정
		for(BEventVo bEventVo: bEventVoList) {
			HashMap<String, Object> eventList = new HashMap<String, Object>(); // 이벤트 리스트 생성
			
			// 이벤트 리스트 정보 삽입 처리
			eventList.put("bEventVo", bEventVo);
			eventList.put("memberVo", this.memberSQLMapper.selectByNo(bEventVo.getM_no()));
			eventList.put("eventDateFrom", dateconverter.getDate(bEventVo.getBe_date_from()));
			eventList.put("eventDateTo", dateconverter.getDate(bEventVo.getBe_date_to()));
			eventList.put("eventStatus", dateconverter.getDateStatus(bEventVo.getBe_date_from(), bEventVo.getBe_date_to()));
			eventList.put("thumnailImg", this.eventSQLMapper.selectImgByEventNo(bEventVo.getBe_no()));
			
			eventListData.add(eventList);
		}
		
		System.out.println("[System] getBoard");
		
		return eventListData;
	}
	
	// 페이지 정보 처리
	public int getPageCount(HashMap<String, Object> searchMap) {
		
		return this.eventSQLMapper.getPageCount(searchMap);
	}
	
	// 이벤트 정보 받기
	public HashMap<String, Object> getEvent(int be_no) {
		HashMap<String, Object> eventData = new HashMap<String, Object>(); // 이벤트 데이터
		BEventVo bEventVo = this.eventSQLMapper.selectEventByNo(be_no); // 이벤트 정보
		MemberVo memberVo = this.memberSQLMapper.selectByNo(bEventVo.getM_no()); // 이벤트 개설 유저 정보
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		
		// 이벤트 데이터 넣기
		eventData.put("bEventVo", bEventVo);
		eventData.put("memberVo", memberVo);
		eventData.put("eventDateFrom", dateConverter.getDate(bEventVo.getBe_date_from()));
		eventData.put("eventDateTo", dateConverter.getDate(bEventVo.getBe_date_to()));
		eventData.put("eventStatus", dateConverter.getDateStatus(bEventVo.getBe_date_from(), bEventVo.getBe_date_to()));
		eventData.put("eventThumbnail", this.eventSQLMapper.selectImgByEventNo(bEventVo.getBe_no()));
		eventData.put("dDay", dateConverter.getDday(bEventVo.getBe_date_to()));
		
		return eventData;
	}
	
	// 이벤트 삭제
	public void deleteEvent(int be_no) {
		
		this.eventSQLMapper.deleteEvent(be_no);
	}
	
	// 댓글 작성
	public void insertComment(CommentBEVo commentBEVo) {
		
		commentBEVo.setCbe_no(this.eventSQLMapper.createCommentKey()); // 키 생성
		this.eventSQLMapper.writeComment(commentBEVo); // 댓글 작성 처리
		
		System.out.println("[System] insertComment");
	}
	
	// 댓글 가져오기
	public ArrayList<HashMap<String, Object>> getCommentList(int be_no) {
		ArrayList<HashMap<String, Object>> commentList = new ArrayList<HashMap<String,Object>>();
		ArrayList<CommentBEVo> tempCommentList = this.eventSQLMapper.selectAllCommentByEventNo(be_no);
		
		for(CommentBEVo commentBEVo: tempCommentList) {
			HashMap<String, Object> commentData = new HashMap<String, Object>(); // 댓글 데이터
			MemberVo memberVo = this.memberSQLMapper.selectByNo(commentBEVo.getM_no()); // 댓글 작성자 정보
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			
			commentData.put("commentBEVo", commentBEVo);
			commentData.put("memberVo", memberVo);
			commentData.put("commentDate", dateConverter.getDate(commentBEVo.getCbe_write_date()));
			
			commentList.add(commentData);
		}
		
		return commentList;
	}
	
	// 댓글 삭제
	public void deleteComment(int cbe_no) {
		
		this.eventSQLMapper.deleteComment(cbe_no); // 댓글 삭제 처리
		
		System.out.println("[System] deleteComment");
	}
}
