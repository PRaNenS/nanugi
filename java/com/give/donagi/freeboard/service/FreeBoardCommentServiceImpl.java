package com.give.donagi.freeboard.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.freeboard.mapper.FreeBoardCommentSQLMapper;
import com.give.donagi.freeboard.mapper.FreeBoardSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.vo.CommentBFVo;
import com.give.donagi.vo.MemberVo;

@Service
public class FreeBoardCommentServiceImpl {
	
	@Autowired
	private FreeBoardCommentSQLMapper freeBoardCommentSqlMapper;
	
	@Autowired
	private UserMemberSQLMapper userMemberSqlMapper;
	
	
	
	
	// 코멘트 작성
		public void writeComment(CommentBFVo vo) {
			freeBoardCommentSqlMapper.insertComment(vo);
			
		}
		
	// 코멘트 리스트
	public ArrayList<HashMap<String, Object>> getCommentList(int contentNo){
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ArrayList<CommentBFVo> commentList = freeBoardCommentSqlMapper.selectComment(contentNo);
			
		for(CommentBFVo freeBoardCommentVo : commentList) {
			MemberVo memberVo = userMemberSqlMapper.selectByNo(freeBoardCommentVo.getM_no());
				
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("freeBoardCommentVo", freeBoardCommentVo);
				
			result.add(map);
			
		}
		
		return result;
		
	}
	
	// 댓글 수정
	public void updateComment(CommentBFVo vo) {
		freeBoardCommentSqlMapper.updateComment(vo);
	}
			
		
	// 댓글 삭제
	public void deleteComment(int cbf_no) {
		freeBoardCommentSqlMapper.deleteComment(cbf_no);
	}
	
		
}
